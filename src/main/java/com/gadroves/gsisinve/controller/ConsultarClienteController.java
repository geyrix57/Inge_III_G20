package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.UI.CustomWindow;
import com.gadroves.gsisinve.model.DBAccess;
import com.gadroves.gsisinve.model.entities.TbClienteCuenta;
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
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by geykel on 28/04/2015.
 */
public class ConsultarClienteController implements Initializable {

    @FXML
    TextField TF_Buscar;
    @FXML
    TableView<TbClienteCuenta> Tb_InfoCliente;
    @FXML
    TableColumn<TbClienteCuenta, String> Col_codigo;
    @FXML
    TableColumn<TbClienteCuenta, String> Col_nombre;

    private ObservableList<TbClienteCuenta> datos = FXCollections.observableArrayList();
    private FilteredList<TbClienteCuenta> filteredData = null;

    private void openCliente(TbClienteCuenta cc) {
        try {
            new CustomWindow(R.loadScreen("cliente", cc))
                    .show()
                    .setMaximize(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void iniTbInfoCliente() {
        Tb_InfoCliente.setItems(datos);
        Col_codigo.setCellValueFactory(new PropertyValueFactory<TbClienteCuenta, String>("id"));
        Col_nombre.setCellValueFactory(new PropertyValueFactory<TbClienteCuenta, String>("nombre"));
        filteredData = new FilteredList<>(datos, p -> true);
        SortedList<TbClienteCuenta> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(Tb_InfoCliente.comparatorProperty());
        Tb_InfoCliente.setItems(sortedData);

        Tb_InfoCliente.setOnMouseClicked(event -> {
            if (event.getClickCount() > 1) {
                TbClienteCuenta cp = Tb_InfoCliente.getSelectionModel().getSelectedItem();
                if (cp != null) this.openCliente(cp);
            }
        });
    }

    @FXML
    private void cargarDatos() {
        datos.addAll(DBAccess.getInstance().Stream(TbClienteCuenta.class).toList());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniTbInfoCliente();
        TF_Buscar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(clienteCuenta -> {
                if (newValue == null || newValue.isEmpty()) return true;
                return clienteCuenta.getId().toLowerCase().contains(newValue.toLowerCase()) ||
                        clienteCuenta.getNombre().toLowerCase().contains(newValue.toLowerCase());
            });
        });
    }
}
