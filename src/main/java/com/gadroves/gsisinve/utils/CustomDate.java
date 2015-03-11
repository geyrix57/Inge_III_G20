/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gadroves.gsisinve.utils;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Aaron
 */
public class CustomDate {
    StringProperty fecha;
    StringProperty hora;
    public CustomDate(StringProperty fecha, StringProperty hora){
        this.fecha=new SimpleStringProperty();
        fecha.bind(this.fecha);
        this.hora=new SimpleStringProperty();
        hora.bind(this.hora);
    }
    
    public void start() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                Calendar time = Calendar.getInstance();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm:ss a");
                                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
                                hora.set(simpleDateFormat.format(time.getTime()));
                                fecha.set(simpleDateFormat2.format(time.getTime()));
                                
                            }
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
