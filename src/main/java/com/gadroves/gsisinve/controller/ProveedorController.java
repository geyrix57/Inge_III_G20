/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.model.DBAccess;
import com.gadroves.gsisinve.model.entities.TbProveedor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Aaron
 */
public class ProveedorController implements Initializable {

    @FXML
    RadioButton activo;
    @FXML
    RadioButton inactivo;
    @FXML
    TextField nombre;
    @FXML
    TextField codigo;
    @FXML
    TextArea direccion;

    private ToggleGroup group;
    private TbProveedor prov;

    private Stage getStage() {
        return (Stage) activo.getParent().getScene().getWindow();
    }

    private void closeWindow() {
        getStage().close();
    }

    private void inicializarProveedor() {
        prov = new TbProveedor();
        codigo.textProperty().bindBidirectional(prov.codigoProperty());
        nombre.textProperty().bindBidirectional(prov.nombreProperty());
        direccion.textProperty().bindBidirectional(prov.direccionProperty());
        prov.estadoProperty().bindBidirectional(this.activo.selectedProperty());
    }

    private void validar() throws Exception {
        if (!(codigo.getText() != null && !codigo.getText().isEmpty() &&
                nombre.getText() != null && !nombre.getText().isEmpty() &&
                direccion.getText() != null && !direccion.getText().isEmpty() &&
                (activo.isSelected() || inactivo.isSelected())))
            throw new Exception("Debe llenar todos los campos.!!");
    }

    @FXML
    private void aceptar() {
        try {
            validar();

            DBAccess.getInstance().getTransaction().begin();
            DBAccess.getInstance().Insert(prov);
            DBAccess.getInstance().getTransaction().commit();

            Dialogs.create()
                    .owner(this.getStage())
                    .title("Notificación")
                    .masthead(null)
                    .message("El proveedor ha sido registrado correctamente.!")
                    .showInformation();

        } catch (Exception e) {
            Dialogs.create()
                    .owner(this.getStage())
                    .title("Exception Dialog")
                    .masthead("Look, an Exception Dialog")
                    .message("Ooops, there was an exception!")
                    .showException(e);
        }
    }

    @FXML
    private void cancelar() {
        this.closeWindow();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarProveedor();
        group = new ToggleGroup();
        this.activo.setToggleGroup(group);
        this.inactivo.setToggleGroup(group);
    }

}
