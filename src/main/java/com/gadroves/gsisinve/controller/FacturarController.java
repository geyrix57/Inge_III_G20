/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.controller.UI.ControlledScreen;
import com.gadroves.gsisinve.controller.UI.ScreensController;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;
import com.gadroves.gsisinve.utils.CustomDate;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
/**
 * FXML Controller class
 *
 * @author Aaron
 */
public class FacturarController implements Initializable, ControlledScreen {

    @FXML
    Label fecha;
    @FXML
    Label hora;
    @FXML
    RadioButton entregasi;
    @FXML
    RadioButton entregano;
    
    private ScreensController myController;

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CustomDate customdate= new CustomDate(fecha.textProperty(),hora.textProperty()); 
        customdate.start();
        ToggleGroup group = new ToggleGroup();
        this.entregasi.setToggleGroup(group);
        this.entregano.setToggleGroup(group);
        
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        this.myController=screenPage;
    }

}
