package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.model.DBAccess;
import com.gadroves.gsisinve.model.entities.TbArticulo;
import com.gadroves.gsisinve.model.entities.TbFacturaCompra;
import com.gadroves.gsisinve.model.entities.TbLineaCompra;
import com.gadroves.gsisinve.model.entities.TbProveedor;
import com.gadroves.gsisinve.utils.DialogBox;
import com.gadroves.gsisinve.utils.InitData;
import com.gadroves.gsisinve.utils.UtilsUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.eclipse.persistence.indirection.IndirectList;
import org.jinq.orm.stream.JinqStream;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

/**
 * Created by geykel on 08/05/2015.
 */
public class CompraController implements Initializable, InitData<TbFacturaCompra> {
    @FXML
    ComboBox<TbProveedor> CB_CodigoProv;
    @FXML
    TextField TF_Consecutivo;
    @FXML
    DatePicker DP_Fecha;
    @FXML
    TextField TF_CodigoArticulo;
    @FXML
    TextField TF_Cantidad;
    @FXML
    TextField TF_Precio;
    @FXML
    TextField TF_Impuesto;
    @FXML
    TextField TF_Descuento;
    @FXML
    TableView<TbLineaCompra> TB_LineaCompra;
    @FXML
    TableColumn<TbLineaCompra, String> Col_Codigo;
    @FXML
    TableColumn<TbLineaCompra, Integer> Col_Cantidad;
    @FXML
    TableColumn<TbLineaCompra, Double> Col_Precio;
    @FXML
    TableColumn<TbLineaCompra, Double> Col_Impuesto;
    @FXML
    TableColumn<TbLineaCompra, Double> Col_Descuento;

    private ObservableList<TbLineaCompra> lineas = FXCollections.observableArrayList();
    private boolean update = false;

    private double subTotal() {
        return lineas.stream()
                .parallel()
                .mapToDouble(value -> (value.getPrecio()*(1 - value.getDescuento()/100)) * value.getCantidad())
                .sum();
    }

    private double impuesto() {
        return lineas.stream()
                .parallel()
                .mapToDouble(value -> (value.getPrecio() * (value.getImp() / 100) * value.getCantidad()))
                .sum();
    }

    private double total() {
        return impuesto() + subTotal();
    }

    private Stage getStage() {
        return (Stage) CB_CodigoProv.getScene().getWindow();
    }

    @FXML
    private void limpiar() {
        lineas.clear();
        TF_Cantidad.clear();
        TF_CodigoArticulo.clear();
        TF_Consecutivo.clear();
        TF_Descuento.clear();
        TF_Impuesto.clear();
        TF_Precio.clear();
        TF_Cantidad.setEditable(true);
        TF_CodigoArticulo.setEditable(true);
        TF_Consecutivo.setEditable(true);
        TF_Descuento.setEditable(true);
        TF_Impuesto.setEditable(true);
        TF_Precio.setEditable(true);
        DP_Fecha.setEditable(true);
        //CB_CodigoProv.setEditable(true);
        update = false;
    }

    private void validarLineaCompra() throws Exception {
        if (TF_CodigoArticulo.getText().isEmpty() || TF_Cantidad.getText().isEmpty()
                || TF_Precio.getText().isEmpty() || TF_Impuesto.getText().isEmpty())
            throw new Exception("Debe llenar todos los campos.!!!");
        String codArt = TF_CodigoArticulo.getText();
        JinqStream<TbArticulo> str = DBAccess.getInstance().Stream(TbArticulo.class)
                .where(art -> art.getId().equals(codArt));
        if (str.count() < 1) throw new Exception("El código del articulo no esta registrado.!!");
    }

    @FXML
    private void agregarLinea() {
        try {
            validarLineaCompra();
            TbLineaCompra lc = new TbLineaCompra()
                    .setCantidad(Integer.parseInt(TF_Cantidad.getText()))
                    .setCodArt(TF_CodigoArticulo.getText())
                    .setDescuento(TF_Descuento.getText().isEmpty() ? 0 : Double.parseDouble(TF_Descuento.getText()))
                    .setImp(Double.parseDouble(TF_Impuesto.getText()))
                    .setPrecio(Double.parseDouble(TF_Precio.getText()));
            lineas.add(lc);
            TF_Cantidad.clear();
            TF_CodigoArticulo.clear();
            TF_Descuento.clear();
            TF_Impuesto.clear();
            TF_Precio.clear();
        } catch (Exception e) {
            DialogBox.Excepcion(getStage(), "Se ha generado una excepción", e);
        }
    }

    @FXML
    private void doubleClick(MouseEvent event) {
    }

    @FXML
    private void deleteLine(KeyEvent event) {
        TbLineaCompra lc = TB_LineaCompra.getSelectionModel().getSelectedItem();
        if (event.getCode().equals(KeyCode.DELETE) && lc != null) {
            if (!update) lineas.remove(lc);
        }
    }

    private void validarCamposFactura() throws Exception {
        if (CB_CodigoProv.getValue() == null || TF_Consecutivo.getText().isEmpty() || DP_Fecha.getValue() == null)
            throw new Exception("Debe llenar todos los campos.!!!");
    }

    @FXML
    private void guardar() {
        if (update) return;
        try {
            validarCamposFactura();
            DBAccess dbAccess = DBAccess.getInstance();
            dbAccess.getTransaction().begin();
            TbFacturaCompra fc = new TbFacturaCompra()
                    .setFecha(Date.valueOf(DP_Fecha.getValue()))
                    .setId(TF_Consecutivo.getText())
                    .setImp(impuesto())
                    .setProvId(CB_CodigoProv.getValue().getCodigo())
                    .setSaldo(total())
                    .setSubTotal(subTotal())
                    .setTotal(total());
            fc.setTbProveedorByProvId(CB_CodigoProv.getValue());
            fc.setTbLineaComprasById(new IndirectList<>());
            fc.getTbLineaComprasById().addAll(lineas);
            dbAccess.Insert(fc);

            for (TbLineaCompra lc : lineas) {
                String codArt = lc.getCodArt();

                /*TbArticuloProveedor ArtProv = new TbArticuloProveedor();
                ArtProv.setArtId(codArt);
                ArtProv.setProvId(fc.getProvId());
                ArtProv.setPrecioCompra(lc.getPrecio());
                ArtProv.setTbArticuloByArtId(dbAccess.Stream(TbArticulo.class).where(obj -> obj.getId().equals(codArt)).getOnlyValue());
                ArtProv.setTbProveedorByProvId(CB_CodigoProv.getValue());
                dbAccess.Insert(ArtProv);*/

                lc.setFacOrigen(TF_Consecutivo.getText());
                lc.setTbFacturaCompraByFacOrigen(fc);
                //lc.setTbArticuloProveedorByCodArt(ArtProv);
                dbAccess.Insert(lc.setFacOrigen(fc.getId()));
            }
            dbAccess.getTransaction().commit();
            DialogBox.Informativo(this.getStage(), "La factura se ha guardado correctamente.!");
            limpiar();
        } catch (Exception e) {
            e.printStackTrace();
            DialogBox.Excepcion(getStage(), "Se ha generado una excepción.!!", e);
        }
    }

    @FXML
    private void cancelar() {
        getStage().close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] properties = {"codArt", "cantidad", "precio", "imp", "descuento"};
        TableColumn[] columns = {Col_Codigo, Col_Cantidad, Col_Precio, Col_Impuesto, Col_Descuento};
        UtilsUI.setUpColumnsValues(properties, columns);
        CB_CodigoProv.setConverter(new ProveedorConverter());
        CB_CodigoProv.getItems().addAll(DBAccess.getInstance().Stream(TbProveedor.class).toList());
        TB_LineaCompra.setItems(lineas);
    }

    @Override
    public void initData(TbFacturaCompra obj) {
        update = true;
        lineas.addAll(obj.getTbLineaComprasById());
        TF_Cantidad.setEditable(false);
        TF_CodigoArticulo.setEditable(false);
        TF_Consecutivo.setEditable(false);
        TF_Consecutivo.setText(obj.getId());
        TF_Descuento.setEditable(false);
        TF_Impuesto.setEditable(false);
        TF_Precio.setEditable(false);
        DP_Fecha.setEditable(false);
        DP_Fecha.setValue(obj.getFecha().toLocalDate());
        CB_CodigoProv.setEditable(false);
        CB_CodigoProv.setValue(obj.getTbProveedorByProvId());
    }
}

class ProveedorConverter extends StringConverter<TbProveedor> {

    @Override
    public String toString(TbProveedor object) {
        return object.getCodigo() + " - " + object.getNombre();
    }

    @Override
    public TbProveedor fromString(String string) {
        return null;
    }
}