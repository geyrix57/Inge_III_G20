package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.model.DBAccess;
import com.gadroves.gsisinve.model.entities.TbBodega;
import com.gadroves.gsisinve.model.entities.TbInventario;
import com.google.common.collect.Collections2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by geykel on 21/04/2015.
 */
public class InventarioController implements Initializable {

    @FXML
    TextField TF_IdArticulo;
    @FXML
    ComboBox<String> CB_IdBodega;
    @FXML
    TableView<TbInventario> TB_Inventario;
    @FXML
    TableColumn<TbInventario, String> Col_IdBodega;
    @FXML
    TableColumn<TbInventario, String> Col_IdArticulo;
    @FXML
    TableColumn<TbInventario, String> Col_DescArticulo;
    @FXML
    TableColumn<TbInventario, Integer> Col_Cantidad;

    private ObservableList<TbInventario> articulos = FXCollections.observableArrayList();
    private FilteredList<TbInventario> filtered;
    private List<TbInventario> datos;

    private void initTbArticulos() {
        Col_IdBodega.setCellValueFactory(new PropertyValueFactory<>("codeBodega"));
        Col_IdArticulo.setCellValueFactory(new PropertyValueFactory<>("codeArticulo"));
        Col_DescArticulo.setCellValueFactory(new PropertyValueFactory<>("descArticulo"));
        Col_Cantidad.setCellValueFactory(new PropertyValueFactory<>("quant"));

        filtered = new FilteredList<>(this.articulos, p -> true);
        SortedList<TbInventario> sortedData = new SortedList<>(filtered);
        sortedData.comparatorProperty().bind(TB_Inventario.comparatorProperty());
        TB_Inventario.setItems(sortedData);
        TF_IdArticulo.textProperty().addListener((observable, oldValue, newValue) -> {
            filtered.setPredicate(tbInventario ->
                            newValue == null || newValue.isEmpty() || tbInventario.getCodeArt().toLowerCase().contains(newValue.toLowerCase())
            );
        });
        CB_IdBodega.valueProperty().addListener((observable, oldValue, newValue) -> {
            articulos.clear();
            if (newValue.equals("Todas las Bodegas"))
                articulos.addAll(datos);
            else
                articulos.addAll(Collections2.filter(datos, tbInventario -> tbInventario.getCodeBod().equals(newValue)));
        });
    }

    @FXML
    private void cargarDatos() {
        articulos.clear();
        datos = DBAccess.getInstance().Stream(TbInventario.class).toList();
        articulos.addAll(datos);
        CB_IdBodega.getItems().clear();
        CB_IdBodega.getItems().addAll(DBAccess.getInstance().Stream(TbBodega.class).select(tbBodega -> tbBodega.getCode()).toList());
        CB_IdBodega.getItems().add(0, "Todas las Bodegas");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTbArticulos();
    }

}
