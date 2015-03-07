package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.controller.UI.ControlledScreen;
import com.gadroves.gsisinve.controller.UI.ScreensController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by geykel on 07/03/2015.
 */
public class MainController implements Initializable, ControlledScreen {

    private ScreensController myController;

    @Override
    public void setScreenParent(ScreensController screenPage) {
        this.myController = screenPage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
