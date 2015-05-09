/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.model.DBAccess;
import com.gadroves.gsisinve.model.entities.TbCLienteFactura;
import com.gadroves.gsisinve.model.entities.TbClienteCuenta;
import com.gadroves.gsisinve.model.entities.TbContactoCliente;
import com.gadroves.gsisinve.utils.CustomDate;
import com.gadroves.gsisinve.utils.DialogBox;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
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

    /**************Bussiness Elements*********************************/
    DBAccess dbAccess;
    TbCLienteFactura cLienteFactura;
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
        TF_ClientID.setOnAction(value->{
            String cid = TF_ClientID.getText();
            //System.out.println(cid);
            JinqStream<TbClienteCuenta> stream = (dbAccess.Stream(TbClienteCuenta.class).where(c->c.getId().equals(cid)));
            if(stream.count() < 1) {
                DialogBox.Error((Stage) HBX_Abono.getScene().getWindow(), "No Existe Cliente");
                return;
            }
            TbClienteCuenta clienteCuenta = stream.getOnlyValue();
            String tel = "No Tiene Tel";
            TF_NombClient.setText(clienteCuenta.getNombre());
            List<TbContactoCliente> contactoClienteList = clienteCuenta.getTbContactoClienteById().stream().filter(t -> !t.getTipo().equals(dir)).collect(Collectors.toList());
            List<TbContactoCliente> contactoAddClienteList = clienteCuenta.getTbContactoClienteById().stream().filter(t->t.getTipo().equals(dir)).collect(Collectors.toList());
            if(contactoClienteList.size() > 0) tel = contactoClienteList.get(0).getValor();
            TF_TelCLient.setText(tel);

        });

    }

}
