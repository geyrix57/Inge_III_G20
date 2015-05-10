package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.UI.CustomWindow;
import com.gadroves.gsisinve.model.DBAccess;
import com.gadroves.gsisinve.model.entities.TbClienteCuenta;
import com.gadroves.gsisinve.utils.R;
import com.gadroves.gsisinve.utils.UtilsUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by geykel on 28/04/2015.
 */
public class ConsultarClienteController implements Consultar<TbClienteCuenta> {

    @FXML
    TextField TF_Buscar;
    @FXML
    TableView<TbClienteCuenta> Tb_InfoCliente;
    @FXML
    TableColumn<TbClienteCuenta, String> Col_codigo;
    @FXML
    TableColumn<TbClienteCuenta, String> Col_nombre;

    private ObservableList<TbClienteCuenta> datos = FXCollections.observableArrayList();

    @Override
    public void openRegistryWindow(TbClienteCuenta tbClienteCuenta) {
        try {
            new CustomWindow(R.loadScreen("cliente", tbClienteCuenta))
                    .show()
                    .setMaximize(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doubleClick(MouseEvent event) {
        if (event.getClickCount() > 1) {
            TbClienteCuenta cp = Tb_InfoCliente.getSelectionModel().getSelectedItem();
            if (cp != null) openRegistryWindow(cp);
        }
    }

    @Override
    public void cargarDatos() {
        datos.clear();
        TF_Buscar.clear();
        datos.addAll(DBAccess.getInstance().Stream(TbClienteCuenta.class).toList());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UtilsUI.setUpColumnsValues(new String[]{"id", "nombre"}, new TableColumn[]{Col_codigo, Col_nombre});
        UtilsUI.textFieldFilter(Tb_InfoCliente, datos, TF_Buscar, (tbClienteCuenta, str) -> {
            if (str == null || str.isEmpty()) return true;
            return tbClienteCuenta.getId().toLowerCase().contains(str.toLowerCase()) ||
                    tbClienteCuenta.getNombre().toLowerCase().contains(str.toLowerCase());
        });
    }
}
