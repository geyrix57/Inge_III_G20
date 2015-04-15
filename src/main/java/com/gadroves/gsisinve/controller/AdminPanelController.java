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

    private CustomWindow openWindow(String name) {
        try {
            return new CustomWindow(R.loadScreen(name)).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    private void openConsultarProveedor() {
        openWindow("consultarProveedor");
    }

    @FXML
    private void openProveedor() {
        openWindow("proveedor").setMaximize(false);
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

    @FXML
    private void openBodega() { openWindow("buscarBodega");}

    @FXML
    private void openAdmBodega(){ openWindow("admbodega");}

    @FXML
    private void openCategoria() {
        openWindow("categoria");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //this.browser.getEngine().load("https://calendar.google.com");
        /*SimpleBooleanProperty log = new SimpleBooleanProperty(true);
        this.browser.getEngine().documentProperty().addListener((observable, oldDoc, doc) -> {
            if (doc != null && log.get()) {
                browser.getEngine().executeScript("document.getElementById('Email').value='GRUPO20SISGRADOV'");
                browser.getEngine().executeScript("document.getElementById('Passwd').value='sisgradov'");
                browser.getEngine().executeScript("document.forms['gaia_loginform'].submit();");
                log.set(false);
            }
        });*/
    }

}
