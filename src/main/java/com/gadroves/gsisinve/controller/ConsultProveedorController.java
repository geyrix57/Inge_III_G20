/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.UI.CustomWindow;
import com.gadroves.gsisinve.model.DBAccess;
import com.gadroves.gsisinve.model.entities.TbProveedor;
import com.gadroves.gsisinve.utils.R;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Aaron
 */
public class ConsultProveedorController implements Initializable {

    @FXML
    TextField Tf_buscar;
    @FXML
    TableView<TbProveedor> Tb_InfoProveedor;
    @FXML
    TableColumn<TbProveedor, String> Col_codigo;
    @FXML
    TableColumn<TbProveedor, String> Col_nombre;
    @FXML
    TableColumn<TbProveedor, String> Col_direccion;
    @FXML
    TableColumn<TbProveedor, Boolean> Col_estado;

    private ObservableList<TbProveedor> proveedores = FXCollections.observableArrayList();
    private FilteredList<TbProveedor> filteredData = null;

    private Stage getStage() {
        return (Stage) Tf_buscar.getParent().getScene().getWindow();
    }

    private void openProveedor(TbProveedor prov) {
        try {
            new CustomWindow(R.loadScreen("proveedor", prov))
                    .show()
                    .setMaximize(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initTbInfoProveedor() {
        Col_codigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        Col_nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        Col_direccion.setCellValueFactory(new PropertyValueFactory("direccion"));
        Col_estado.setCellValueFactory(new PropertyValueFactory("estado"));
        Col_estado.setCellFactory(CheckBoxTableCell.forTableColumn(Col_estado));

        filteredData = new FilteredList<>(this.proveedores, p -> true);
        SortedList<TbProveedor> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(Tb_InfoProveedor.comparatorProperty());
        Tb_InfoProveedor.setItems(sortedData);

        Tb_InfoProveedor.setOnMouseClicked(event -> {
            if (event.getClickCount() > 1) {
                TbProveedor cp = Tb_InfoProveedor.getSelectionModel().getSelectedItem();
                if (cp != null) this.openProveedor(cp);
            }
        });
    }

    private void initTfBuscar() {
        Tf_buscar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(tbProveedor -> {
                if (newValue == null || newValue.isEmpty()) return true;
                return tbProveedor.getCodigo().toLowerCase().contains(newValue.toLowerCase()) ||
                        tbProveedor.getNombre().toLowerCase().contains(newValue.toLowerCase());
            });
        });
    }

    @FXML
    private void cargarDatos() {
        this.proveedores.clear();
        this.proveedores.addAll(DBAccess.getInstance().Stream(TbProveedor.class).toList());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTbInfoProveedor();
        initTfBuscar();
    }

}
