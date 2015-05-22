package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.model.DBAccess;
import com.gadroves.gsisinve.model.entities.TbCLienteFactura;
import com.gadroves.gsisinve.model.entities.TbFacturaVenta;
import com.gadroves.gsisinve.model.entities.TbLineaFac;
import com.gadroves.gsisinve.utils.DialogBox;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

/**
 * Created by Casa on 10/05/2015.
 */
public class ConsultarFacturaController implements Initializable {
    /**FXML Elements******************************************************************/
    @FXML    TableView<FactClientUnion> TV_Facturas;
        @FXML    TableColumn<FactClientUnion,String> TC_NumFac;
        @FXML    TableColumn<FactClientUnion,String> TC_Fecha;
        @FXML    TableColumn<FactClientUnion,String> TC_Impuestos;
        @FXML    TableColumn<FactClientUnion,String> TC_SubTotal;
        @FXML    TableColumn<FactClientUnion,String> TC_Total;
        @FXML    TableColumn<FactClientUnion,String> TC_Cliente;
        @FXML    TableColumn<FactClientUnion,String>TC_Autorizacion;
    @FXML    VBox VBox_Fechas;
    @FXML    TextField TF_FacId;

    @FXML    TableView<TbLineaFac> TB_LineasCompra;
        @FXML    TableColumn<TbLineaFac, Integer> TC_ArtCant;
        @FXML    TableColumn<TbLineaFac, String>  TC_ArtDesc;
        @FXML    TableColumn<TbLineaFac, Double>  TC_ArtPrec;
        @FXML    TableColumn<TbLineaFac, Double>  TC_ArtDis;
        @FXML    TableColumn<TbLineaFac, Double>  TC_ArtTot;
    /**Business Elements**************************************************************/
    DBAccess dbAccess = DBAccess.getInstance();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        TV_Facturas.setEditable(false);
        TC_NumFac.setCellValueFactory(param -> param.getValue().getFacturaVenta().idProperty().asString());
        TC_Fecha.setCellValueFactory(param -> new SimpleStringProperty(dateFormat.format(param.getValue().getFacturaVenta().getFacDate())));
        TC_Impuestos.setCellValueFactory(param -> param.getValue().getFacturaVenta().impuestosProperty().asString());
        TC_SubTotal.setCellValueFactory(param -> param.getValue().getFacturaVenta().subProperty().asString());
        TC_Total.setCellValueFactory(param -> param.getValue().getFacturaVenta().totalProperty().asString());
        TC_Cliente.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getcLienteFactura().getName()));
        TC_Autorizacion.setCellValueFactory(param -> param.getValue().getFacturaVenta().autorizationProperty());
        /***************Lineas Factura Table ************************************************/
        TC_ArtDis.setCellValueFactory(new PropertyValueFactory<>("disc"));
        TC_ArtCant.setCellValueFactory(new PropertyValueFactory<>("quant"));
        TC_ArtDesc.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        TC_ArtTot.setCellValueFactory(new PropertyValueFactory<>("total"));
        TC_ArtPrec.setCellValueFactory(new PropertyValueFactory<>("p_unitario"));
        TC_ArtDis.setCellFactory(TextFieldTableCell.<TbLineaFac, Double>forTableColumn(new myStringConverter()));
        TC_ArtCant.setCellFactory(TextFieldTableCell.<TbLineaFac, Integer>forTableColumn(new myStringIntConverter()));

        /**Hide content**/
        VBox_Fechas.setVisible(false);
        /****************/
        TF_FacId.setOnKeyTyped(k->{if(!k.getCharacter().matches("[0-9]+")) k.consume();});
        TF_FacId.setOnAction(event -> {
            if(TF_FacId.getText().trim().equals("")) return;
            int facid = Integer.valueOf(TF_FacId.getText());
            long count = dbAccess.Stream(TbFacturaVenta.class).where(c->c.getId() == facid).count();
            if(count != 1) {
                DialogBox.Error((Stage)VBox_Fechas.getScene().getWindow(),"No Existen Facturas con ese ID");
                TV_Facturas.getItems().clear();
                TB_LineasCompra.getItems().clear();
                TF_FacId.clear();
                return;
            }
            TbFacturaVenta facturaVenta = dbAccess.Stream(TbFacturaVenta.class).where(c->c.getId() == facid).getOnlyValue();
            int factnum = facturaVenta.getId();
            TbCLienteFactura cLienteFactura = facturaVenta.getTbCLienteFacturaById();//= dbAccess.Stream(TbCLienteFactura.class).where(c->c.getFacId() == factnum).getOnlyValue();
            FactClientUnion clientUnion = new FactClientUnion(cLienteFactura,facturaVenta);
            TV_Facturas.getItems().clear();
            TF_FacId.clear();
            TV_Facturas.getItems().add(clientUnion);
            TB_LineasCompra.getItems().clear();
            TB_LineasCompra.getItems().addAll(facturaVenta.getTbLineaFacsById());
        });
    }
    @FXML void Salir(){
        ((Stage) VBox_Fechas.getScene().getWindow()).close();
    }
}
class FactClientUnion{
    TbCLienteFactura cLienteFactura;
    TbFacturaVenta facturaVenta;

    public FactClientUnion(TbCLienteFactura cLienteFactura, TbFacturaVenta facturaVenta) {
        this.cLienteFactura = cLienteFactura;
        this.facturaVenta = facturaVenta;
    }

    public TbCLienteFactura getcLienteFactura() {
        return cLienteFactura;
    }

    public void setcLienteFactura(TbCLienteFactura cLienteFactura) {
        this.cLienteFactura = cLienteFactura;
    }

    public TbFacturaVenta getFacturaVenta() {
        return facturaVenta;
    }

    public void setFacturaVenta(TbFacturaVenta facturaVenta) {
        this.facturaVenta = facturaVenta;
    }
}