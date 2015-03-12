package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.UI.controller.ControlledScreen;
import com.gadroves.gsisinve.UI.controller.ScreensController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by geykel on 07/03/2015.
 */
public class AdminPanelController implements Initializable, ControlledScreen {

    private ScreensController myController;

    @FXML
    private void openFacturar(ActionEvent event) {
        myController.setScreen("facturar");
    }

    @FXML
    private void openStock(ActionEvent envet) {
        myController.setScreen("stock");
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        this.myController = screenPage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
