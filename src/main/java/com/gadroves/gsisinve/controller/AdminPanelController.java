package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.UI.CustomWindow;
import com.gadroves.gsisinve.utils.R;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by geykel on 07/03/2015.
 */
public class AdminPanelController implements Initializable {

    @FXML
    private WebView browser;

    private void openWindow(String name) {
        try {
            new CustomWindow(R.loadScreen(name))
                    .show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openConsultarProveedor() {
        openWindow("consultarProveedor");
    }

    @FXML
    private void openProveedor() {
        openWindow("proveedor");
    }

    @FXML
    private void openGarantia() {
        openWindow("garantia");
    }

    @FXML
    private void openConsultarFacturas() {
        openWindow("consultarFactura");
    }

    @FXML
    private void openFacturar(ActionEvent event) {
        openWindow("facturar");
    }

    @FXML
    private void openStock(ActionEvent event) {
        openWindow("stock");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.browser.getEngine().load("https://calendar.google.com");
    }

}
