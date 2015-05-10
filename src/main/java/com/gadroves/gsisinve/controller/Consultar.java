package com.gadroves.gsisinve.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

/**
 * Created by geykel on 10/05/2015.
 */
public interface Consultar<T> extends Initializable{
    void openRegistryWindow(T t);
    @FXML
    void doubleClick(MouseEvent event);
    @FXML
    void cargarDatos();
}
