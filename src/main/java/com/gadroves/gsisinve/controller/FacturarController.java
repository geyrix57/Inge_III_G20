/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.utils.CustomDate;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Aaron
 */
public class FacturarController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        TF_Abono.setOnKeyTyped((value) -> {
                    if (!value.getCharacter().matches("[0-9]+")) value.consume();
                }
        );
    }

}
