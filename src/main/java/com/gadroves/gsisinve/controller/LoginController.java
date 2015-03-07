
package com.gadroves.gsisinve.controller;


import com.gadroves.gsisinve.controller.UI.ControlledScreen;
import com.gadroves.gsisinve.controller.UI.ScreensController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable, ControlledScreen {

    private ScreensController myController;

    @FXML
    TextField username;
    @FXML
    PasswordField password;

    @FXML
    private void Login(ActionEvent event) {

        String username1 = username.getText();
        String password1 = password.getText();
        
        if (username1.equals("") || password1.equals("")) {
            System.out.println("Debe llenar los campos");
        } else {
            System.out.println(username1 +" "+ password1);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        this.myController = screenPage;
    }

}
