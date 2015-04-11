package com.gadroves.gsisinve.UI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by geykel on 11/03/2015.
 */
public class WindowController implements Initializable {

    @FXML
    private Button minimize;
    @FXML
    private Button maximize;
    @FXML
    private Button close;

    public Button getMinimize() {
        return this.minimize;
    }

    public Button getMaximize() {
        return this.maximize;
    }

    public Button getClose() {
        return this.close;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
