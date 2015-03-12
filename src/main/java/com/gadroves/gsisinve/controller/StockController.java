package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.UI.controller.ControlledScreen;
import com.gadroves.gsisinve.UI.controller.ScreensController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by geykel on 11/03/2015.
 */
public class StockController implements Initializable, ControlledScreen {

    private ScreensController myController;

    @Override
    public void setScreenParent(ScreensController screenPage) {
        this.myController = screenPage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

