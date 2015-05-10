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
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import org.jinq.orm.stream.JinqStream;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * FXML Controller class
 *
 * @author Aaron
 */
public class FacturarController implements Initializable {
    /**const***/
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
    @FXML    TextField costenvio;
    @FXML    ComboBox<String> CB_TipoPago;
    @FXML    Label LBL_Abono;
    @FXML    HBox HBX_Abono;
    @FXML    TextField TF_Abono;
    @FXML    TextField TF_ClientID;
    @FXML    TextField TF_NombClient;
    @FXML    TextField TF_TelCLient;
    @FXML    TableView<TbLineaFac> TB_LineasCompra;
        //@FXML    TableColumn<TbLineaFac, String> TC_CodArt;
        @FXML    TableColumn<TbLineaFac, Integer> TC_ArtCant;
        @FXML    TableColumn<TbLineaFac, String>  TC_ArtDesc;
        @FXML    TableColumn<TbLineaFac, Double>  TC_ArtPrec;
        @FXML    TableColumn<TbLineaFac, Double>  TC_ArtDis;
        @FXML    TableColumn<TbLineaFac, Double>  TC_ArtTot;
    @FXML    Label LBL_SubTotal;
    @FXML    TextField TF_NewCod;
    @FXML    Label LBL_Total;
    /**************Bussiness Elements*********************************/
    DBAccess dbAccess;
    TbCLienteFactura cLienteFactura;
    DoubleProperty subTotal = new SimpleDoubleProperty();
    /*****************************************************************/
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dbAccess = DBAccess.getInstance();
        CustomDate customdate = new CustomDate(fecha.textProperty(), hora.textProperty());
        customdate.start();
        ToggleGroup group = new ToggleGroup();
        this.entregasi.setToggleGroup(group);
        this.entregasi.setUserData("S");
        this.entregano.setToggleGroup(group);
        this.entregano.setUserData("N");
        group.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) -> {
            if (group.getSelectedToggle() != null) {
                if ("N".equals(group.getSelectedToggle().getUserData().toString())) {
                    direccion.setDisable(true);
                    costenvio.setDisable(true);
                } else {
                    direccion.setDisable(false);
                    costenvio.setDisable(false);
                }
            }
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
        TF_ClientID.setOnAction(value -> {
            String cid = TF_ClientID.getText();
            JinqStream<TbClienteCuenta> stream = (dbAccess.Stream(TbClienteCuenta.class).where(c -> c.getId().equals(cid)));
            if (stream.count() < 1) {
                DialogBox.Error((Stage) HBX_Abono.getScene().getWindow(), "No Existe Cliente");
                return;
            }
            TbClienteCuenta clienteCuenta = stream.getOnlyValue();
            String tel = "No Tiene Tel";
            TF_NombClient.setText(clienteCuenta.getNombre());
            List<TbContactoCliente> contactoClienteList = clienteCuenta.getTbContactoClienteById().stream().filter(t -> !t.getTipo().equals(dir)).collect(Collectors.toList());
            List<TbContactoCliente> contactoAddClienteList = clienteCuenta.getTbContactoClienteById().stream().filter(t -> t.getTipo().equals(dir)).collect(Collectors.toList());
            if (contactoClienteList.size() > 0) tel = contactoClienteList.get(0).getValor();
            TF_TelCLient.setText(tel);
        });
        /** Table Column **/
        TB_LineasCompra.setEditable(true);
        TC_ArtDis.setCellValueFactory(new PropertyValueFactory<TbLineaFac, Double>("disc"));
        TC_ArtCant.setCellValueFactory(new PropertyValueFactory<TbLineaFac, Integer>("quant"));
        TC_ArtDesc.setCellValueFactory(new PropertyValueFactory<TbLineaFac, String>("descripcion"));
        TC_ArtTot.setCellValueFactory(new PropertyValueFactory<TbLineaFac, Double>("total"));
        TC_ArtPrec.setCellValueFactory(new PropertyValueFactory<TbLineaFac, Double>("p_unitario"));
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
        LBL_Total.textProperty().bind(subTotal.asString());
    }
    @FXML void deleteFromTVLineasFactura(){
        int lineaFac = TB_LineasCompra.getSelectionModel().getSelectedIndex();
        if(lineaFac > -1){
            TB_LineasCompra.getItems().remove(lineaFac);
        }
    }
    private void  updateSubTotal(){
        double val = TB_LineasCompra.getItems().stream().mapToDouble(f->f.getTotal()).sum();
        subTotal.setValue(val);
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