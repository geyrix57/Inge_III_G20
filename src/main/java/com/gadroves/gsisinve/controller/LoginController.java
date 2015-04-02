package com.gadroves.gsisinve.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    TextField username;
    @FXML
    PasswordField password;

    @FXML
    private void Login(ActionEvent event) {
        String username1 = username.getText();
        String password1 = password.getText();
        if (username1.equals("") || password1.equals("")) {

        } else {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
