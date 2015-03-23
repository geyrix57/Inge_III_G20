/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.UI.controller.ControlledScreen;
import com.gadroves.gsisinve.UI.controller.ScreensController;
import com.gadroves.gsisinve.utils.CustomDate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;

/**
 * FXML Controller class
 *
 * @author Aaron
 */
public class FacturarController implements Initializable, ControlledScreen {

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

    private ScreensController myController;

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

    @Override
    public void setScreenParent(ScreensController screenPage) {
        this.myController = screenPage;
    }

}
