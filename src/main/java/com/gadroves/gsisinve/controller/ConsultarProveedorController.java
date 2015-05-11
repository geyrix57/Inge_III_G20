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
import com.gadroves.gsisinve.utils.UtilsUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Aaron
 */
public class ConsultarProveedorController implements Consultar<TbProveedor> {

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

    @Override
    public void openRegistryWindow(TbProveedor tbProveedor) {
        try {
            new CustomWindow(R.loadScreen("proveedor", tbProveedor))
                    .show()
                    .setMaximize(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doubleClick(MouseEvent event) {
        if (event.getClickCount() > 1) {
            TbProveedor cp = Tb_InfoProveedor.getSelectionModel().getSelectedItem();
            if (cp != null) openRegistryWindow(cp);
        }
    }

    @Override
    public void cargarDatos() {
        this.proveedores.clear();
        this.proveedores.addAll(DBAccess.getInstance().Stream(TbProveedor.class).toList());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UtilsUI.setUpColumnsValues(new String[]{"codigo", "nombre", "direccion", "estado"},
                new TableColumn[]{Col_codigo, Col_nombre, Col_direccion, Col_estado});
        Col_estado.setCellFactory(CheckBoxTableCell.forTableColumn(Col_estado));

        UtilsUI.textFieldFilter(Tb_InfoProveedor, proveedores, Tf_buscar, (tbProveedor, str) -> {
            if (str == null || str.isEmpty()) return true;
            return tbProveedor.getCodigo().toLowerCase().contains(str.toLowerCase()) ||
                    tbProveedor.getNombre().toLowerCase().contains(str.toLowerCase());
        });
    }

}
