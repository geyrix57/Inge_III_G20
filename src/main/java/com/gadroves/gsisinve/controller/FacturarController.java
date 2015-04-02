/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.utils.CustomDate;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Aaron
 */
public class FacturarController implements Initializable {

    @FXML
    Label fecha;
    @FXML
    Label hora;
    @FXML
    RadioButton entregasi;
    @FXML
    RadioButton entregano;
    @FXML
    TextArea direccion;
    @FXML
    TextField costenvio;

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
    }

}
