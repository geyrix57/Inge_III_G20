package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.UI.controller.ControlledScreen;
import com.gadroves.gsisinve.UI.controller.ScreensController;
import com.gadroves.gsisinve.UI.window.CustomWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Callback;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by geykel on 07/03/2015.
 */
public class AdminPanelController implements Initializable, ControlledScreen {

    private ScreensController myController;
    private WebEngine webEngine;

    @FXML
    private WebView browser;

    @FXML
    private void openFacturar(ActionEvent event) {
        //myController.setScreen("facturar");
        try {
            new CustomWindow(myController.loadScreen("facturar"))
                    .show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openStock(ActionEvent event) {
        //myController.setScreen("stock");
        try {
            new CustomWindow(myController.loadScreen("stock"))
                    .show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        this.myController = screenPage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.webEngine = this.browser.getEngine();
        webEngine.load("https://hotmail.com");
    }

}
