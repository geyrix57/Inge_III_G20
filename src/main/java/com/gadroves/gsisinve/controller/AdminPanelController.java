package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.UI.controller.ControlledScreen;
import com.gadroves.gsisinve.UI.controller.ScreensController;
import com.gadroves.gsisinve.UI.window.CustomWindow;
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
public class AdminPanelController implements Initializable, ControlledScreen {

    private ScreensController myController;

    @FXML
    private WebView browser;

    @FXML
    private void openFacturar(ActionEvent event) {
        try {
            new CustomWindow(myController.loadScreen("facturar"))
                    .show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openStock(ActionEvent event) {
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
        this.browser.getEngine().load("https://calendar.google.com");
    }

}
