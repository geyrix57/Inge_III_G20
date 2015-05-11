/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.model.DBAccess;
import com.gadroves.gsisinve.model.entities.*;
import com.gadroves.gsisinve.utils.CustomDate;
import com.gadroves.gsisinve.utils.DialogBox;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import org.controlsfx.dialog.Dialogs;
import org.jinq.orm.stream.JinqStream;

import javax.persistence.Query;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//import java.sql.Date;

/**
 * FXML Controller class
 *
 * @author Grupo 20
 */
public class FacturarController implements Initializable {
    /***const***/
    final private String tel = "Teléfono";
    final private String email = "E-mail";
    final private String fax = "Fax";
    final private String dir = "Dirección";
    final private String cel = "Celular";
/**************GUI ELEMENTS****************************************/
    @FXML    Label fecha;
    @FXML    Label hora;
    @FXML    RadioButton entregasi;
    @FXML    RadioButton entregano;
    @FXML    TextArea direccion;
    @FXML    TextField TF_CostEnvio;
    @FXML    ComboBox<String> CB_TipoPago;
    @FXML    Label LBL_Abono;
    @FXML    HBox HBX_Abono;
    @FXML    TextField TF_Abono;
    @FXML    TextField TF_ClientID;
    @FXML    TextField TF_NombClient;
    @FXML    TextField TF_TelCLient;
    @FXML    TableView<TbLineaFac> TB_LineasCompra;
        @FXML    TableColumn<TbLineaFac, Integer> TC_ArtCant;
        @FXML    TableColumn<TbLineaFac, String>  TC_ArtDesc;
        @FXML    TableColumn<TbLineaFac, Double>  TC_ArtPrec;
        @FXML    TableColumn<TbLineaFac, Double>  TC_ArtDis;
        @FXML    TableColumn<TbLineaFac, Double>  TC_ArtTot;
    @FXML    Label LBL_SubTotal;
    @FXML    TextField TF_NewCod;
    @FXML    Label LBL_Total;
    @FXML    Label LBL_FacNum;
    /**************Bussiness Elements*********************************/
    DBAccess dbAccess;
    TbCLienteFactura cLienteFactura;
    TbClienteCuenta clienteCuenta;
    DoubleProperty subTotal = new SimpleDoubleProperty();
    DoubleProperty Total = new SimpleDoubleProperty();
    String codBodega = "0";
    /*****************************************************************/
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dbAccess = DBAccess.getInstance();
        LBL_FacNum.setText(Integer.toString(NextAuto()));
        CustomDate customdate = new CustomDate(fecha.textProperty(), hora.textProperty());
        customdate.start();
        ToggleGroup group = new ToggleGroup();
        this.entregasi.setToggleGroup(group);
        this.entregasi.setUserData("S");
        this.entregano.setToggleGroup(group);
        this.entregano.setUserData("N");
        this.entregano.setSelected(true);
        group.selectToggle(group.getToggles().get(1));
        direccion.setDisable(true);
        TF_CostEnvio.setDisable(true);
        group.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) -> {
            if (group.getSelectedToggle() != null) {
                if ("N".equals(group.getSelectedToggle().getUserData().toString())) {
                    direccion.setDisable(true);
                    TF_CostEnvio.setDisable(true);
                } else {
                    direccion.setDisable(false);
                    TF_CostEnvio.setDisable(false);
                }
            }
        });
        TF_CostEnvio.setText("0");
        TF_CostEnvio.setOnKeyTyped(value -> {
            if (!value.getCharacter().matches("[0-9]+")) value.consume();
        });
        HBX_Abono.setVisible(false);
        CB_TipoPago.setItems(FXCollections.observableArrayList("Contado", "Credito"));
        CB_TipoPago.getSelectionModel().selectFirst();
        LBL_Abono.visibleProperty().bind(HBX_Abono.visibleProperty());
        CB_TipoPago.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue.intValue() == 0) HBX_Abono.setVisible(false);
                    else HBX_Abono.setVisible(true);
                }
        );
        TF_Abono.setOnKeyTyped(value -> {
            if (!value.getCharacter().matches("[0-9]+")) value.consume();
        });
        TF_Abono.setText("0");
        TF_ClientID.setOnAction(value -> loadClientInfo());
        TF_ClientID.focusedProperty().addListener(
                (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                    if (!newValue) loadClientInfo();
                }
        );
        /** Table Column **/
        TB_LineasCompra.setEditable(true);
        TC_ArtDis.setCellValueFactory(new PropertyValueFactory<>("disc"));
        TC_ArtCant.setCellValueFactory(new PropertyValueFactory<>("quant"));
        TC_ArtDesc.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        TC_ArtTot.setCellValueFactory(new PropertyValueFactory<>("total"));
        TC_ArtPrec.setCellValueFactory(new PropertyValueFactory<>("p_unitario"));
        TC_ArtDis.setCellFactory(TextFieldTableCell.<TbLineaFac, Double>forTableColumn(new myStringConverter()));
        TC_ArtCant.setCellFactory(TextFieldTableCell.<TbLineaFac, Integer>forTableColumn(new myStringIntConverter()));
        TB_LineasCompra.getItems().addListener(
                (ListChangeListener.Change<? extends TbLineaFac> c) -> {
                    updateSubTotal();
                    while (c.next()) {
                        c.getAddedSubList().forEach(lf -> lf.totalProperty().addListener(
                                (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                                    updateSubTotal();
                                }));
                    }
                });
        TB_LineasCompra.setOnKeyPressed(value -> {
            if (KeyCode.DELETE == value.getCode()) deleteFromTVLineasFactura();
        });
        LBL_SubTotal.setText("0");
        TF_NewCod.setOnAction((ActionEvent event) -> {
                    String artId = TF_NewCod.getText();
                    JinqStream<TbArticulo> artSearch = dbAccess.Stream(TbArticulo.class).where(a -> a.getId().equals(artId));
                    if (artSearch.count() != 1) {
                        DialogBox.Error((Stage) HBX_Abono.getScene().getWindow(), "NO Existe Articulo");
                        TF_NewCod.clear();
                        return;
                    }
                    TbArticulo articulo = artSearch.getOnlyValue();
                    if (TB_LineasCompra.getItems().stream().filter(a -> a.getArtId().equals(articulo.getId())).count() > 0)
                        TB_LineasCompra.getItems().stream().filter(a -> a.getArtId().equals(articulo.getId())).forEach(lf -> lf.setQuant(lf.getQuant() + 1));
                    else TB_LineasCompra.getItems().add(new TbLineaFac(articulo));
                    TF_NewCod.clear();
                }
        );
        LBL_SubTotal.textProperty().bind(subTotal.asString());
        DoubleProperty envio = new SimpleDoubleProperty(0);
        StringConverter<Number> converter = new NumberStringConverter();
        Bindings.bindBidirectional(TF_CostEnvio.textProperty(),envio,converter);
        Total.bind(subTotal.add(envio));
        LBL_Total.textProperty().bind(Total.asString());
    }
    @FXML void deleteFromTVLineasFactura(){
        int lineaFac = TB_LineasCompra.getSelectionModel().getSelectedIndex();
        if(lineaFac > -1){
            TB_LineasCompra.getItems().remove(lineaFac);
        }
    }
    private void  updateSubTotal(){
        double val = TB_LineasCompra.getItems().stream().mapToDouble(f -> f.getTotal()).sum();
        subTotal.setValue(val);
    }
    @FXML
    private void loadClientInfo(){
        this.clienteCuenta = null;
        cLienteFactura = null;
        String cid = TF_ClientID.getText().trim();
        TF_ClientID.setText(cid);
        if(cid == null || cid.trim().equals("")) return;
        JinqStream<TbClienteCuenta> stream = (dbAccess.Stream(TbClienteCuenta.class).where(c -> c.getId().equals(cid)));
        if (stream.count() < 1) {
            DialogBox.Error((Stage) HBX_Abono.getScene().getWindow(), "No Existe Cliente");
            TF_ClientID.clear();
            TF_NombClient.clear();
            TF_TelCLient.clear();
            return;
        }
        clienteCuenta = stream.getOnlyValue();
        String tel = "No Tiene Tel";
        TF_NombClient.setText(clienteCuenta.getNombre());
        List<TbContactoCliente> contactoClienteList = clienteCuenta.getTbContactoClienteById().stream().filter(t -> !t.getTipo().equals(dir)).collect(Collectors.toList());
        List<TbContactoCliente> contactoAddClienteList = clienteCuenta.getTbContactoClienteById().stream().filter(t -> t.getTipo().equals(dir)).collect(Collectors.toList());
        if (contactoClienteList.size() > 0) tel = contactoClienteList.get(0).getValor();
        TF_TelCLient.setText(tel);
        cLienteFactura = new TbCLienteFactura();
        cLienteFactura.setAddress("");
        cLienteFactura.setContact("");
        cLienteFactura.setId(clienteCuenta.getId());
        cLienteFactura.setName(clienteCuenta.getNombre());
    }
    @FXML
    private void Facturar(){
        //EntityManager em = dbAccess.getEm();
        //Hay Items?
        /**Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        return null;
                    }
                };
            }
        };**/
        if(TB_LineasCompra.getItems().size() < 1){
            DialogBox.Error((Stage)HBX_Abono.getScene().getWindow(),"La Factura debe contener almenos un elemento");
            return;
        }
        Stream<TbLineaFac> lineaFacStream = TB_LineasCompra.getItems().stream();
        //se cargo una cuenta
        if(clienteCuenta == null)
            if(TF_NombClient.getText().trim().equals("")) TF_NombClient.setText("default Client");
        //Se utilizo el ciente por defecto
        if(cLienteFactura == null){
            cLienteFactura = new TbCLienteFactura();
            cLienteFactura.setAddress(direccion.getText());
            cLienteFactura.setName(TF_NombClient.getText());
            cLienteFactura.setId("");
            cLienteFactura.setContact(TF_TelCLient.getText());
        }else{
            //No entonces ajustar
            cLienteFactura = new TbCLienteFactura();
            cLienteFactura.setAddress(direccion.getText());
            cLienteFactura.setName(TF_NombClient.getText());
            cLienteFactura.setId(clienteCuenta.getId());
            cLienteFactura.setContact(TF_TelCLient.getText());
        }
        //TypedQuery<Integer> lasid = dbAccess.TypedsqlQuery("LAST_INSERT_ID()",Integer.class);
        TbFacturaVenta facturaVenta = new TbFacturaVenta();
        facturaVenta.setAddress(cLienteFactura.getAddress());
        java.util.Date d1 = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date date = new  java.sql.Date(d1.getTime());
        facturaVenta.setFacDate(date);
        facturaVenta.setImpuestos(0);
        facturaVenta.setSub(subTotal.doubleValue());
        facturaVenta.setTotal(Total.doubleValue());
        /*******************Transacction********************************/
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        dbAccess.getTransaction().begin();
                        try {
                            updateTitle("Guardando Factura");
                            updateProgress(0, 100);
                            updateMessage("Guardando Factura");
                            dbAccess.Insert(facturaVenta);
                            dbAccess.getTransaction().commit();
                            updateProgress(10, 100);
                            dbAccess.getTransaction().begin();
                            Query lasid = dbAccess.nativeSqlQuery("SELECT LAST_INSERT_ID()");
                            int idFac = ((BigInteger) lasid.getSingleResult()).intValue();
                            lineaFacStream.forEach(lf -> lf.setFacId(idFac));
                            updateMessage("Guardando Componetes de Factura");
                            updateProgress(15,100);
                            TB_LineasCompra.getItems().stream().forEach(lf -> {
                                try {
                                    dbAccess.Insert(lf);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                            cLienteFactura.setFacId(idFac);
                            dbAccess.Insert(cLienteFactura);
                            /****Mod inventario*/
                            updateMessage("Modificando Inventario");
                            updateProgress(90,100);
                            Query update;
                            for (TbLineaFac lineaFac : TB_LineasCompra.getItems()) {
                                update = dbAccess.nativeSqlQuery("UPDATE sisgradoves.TB_Inventario SET  quant = quant - " + lineaFac.getQuant() + " WHERE  code_art = \'" + lineaFac.getArtId() + "\'  AND code_bod = \'" + codBodega + "\';");
                                update.executeUpdate();
                            }
                            /*********************/
                            updateMessage("Persistiendo Cambios");
                            dbAccess.getTransaction().commit();
                            updateProgress(95, 100);
                            updateMessage("Limpiado Interfaz");
                            updateMessage("Generando Factura");
                            //TODO Generar Factura
                            Platform.runLater(() -> cleanAllFields());
                            Platform.runLater(() -> LBL_FacNum.setText(String.valueOf(NextAuto())));
                            Platform.runLater(() -> DialogBox.Informativo((Stage) HBX_Abono.getScene().getWindow(), "Listo"));
                            updateProgress(100,100);
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }
                        return null;
                    }
                };
            }
        };
        /*****************************************************************/
        Dialogs.create().title("Nueva Factura").message("GenerandoFactura").owner((Stage)HBX_Abono.getScene().getWindow()).showWorkerProgress(service);
        service.start();
    }
    void cleanAllFields(){
        TF_NombClient.clear();
        subTotal.setValue(0);
        this.direccion.clear();
        this.TF_ClientID.clear();
        this.TF_CostEnvio.setText("0");
        TB_LineasCompra.getItems().clear();
        TF_TelCLient.clear();

    }
    @FXML void test(){
        cleanAllFields();
    }
    int NextAuto(){
        Query nextAuto = dbAccess.nativeSqlQuery("SELECT `auto_increment` FROM INFORMATION_SCHEMA.TABLES WHERE table_name = 'TB_Factura_Venta'");
        return ((BigInteger)nextAuto.getSingleResult()).intValue();
    }
}
class myStringConverter extends StringConverter<Double>{

        @Override
        public String toString(Double object) {
            return object.toString();
        }

        @Override
        public Double fromString(String string) {
            return  Double.valueOf(string);
        }
}
class myStringIntConverter extends StringConverter<Integer>{

    @Override
    public String toString(Integer object) {
        return object.toString();
    }

    @Override
    public Integer fromString(String string) {
        return  Integer.valueOf(string);
    }
}