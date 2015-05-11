package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.UI.CustomWindow;
import com.gadroves.gsisinve.model.DBAccess;
import com.gadroves.gsisinve.model.entities.TbArticulo;
import com.gadroves.gsisinve.model.entities.TbCategoria;
import com.gadroves.gsisinve.utils.R;
import com.gadroves.gsisinve.utils.UtilsUI;
import com.google.common.collect.Collections2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by geykel on 17/04/2015.
 */
public class ConsultarArticulosController implements Consultar<TbArticulo> {

    @FXML
    TextField TF_Buscar;
    @FXML
    ComboBox<String> CB_Categorias;
    @FXML
    TableView<TbArticulo> TB_Articulos;
    @FXML
    TableColumn<TbArticulo, String> Col_Codigo;
    @FXML
    TableColumn<TbArticulo, String> Col_Desc;
    @FXML
    TableColumn<TbArticulo, String> Col_Categoria;
    @FXML
    TableColumn<TbArticulo, Boolean> Col_Gravamen;
    @FXML
    TableColumn<TbArticulo, Boolean> Col_Estado;

    private ObservableList<TbArticulo> articulos = FXCollections.observableArrayList();
    private List<TbArticulo> datos;

    @Override
    public void openRegistryWindow(TbArticulo tbArticulo) {
        try {
            new CustomWindow(R.loadScreen("articulo", tbArticulo)).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doubleClick(MouseEvent event) {
        if (event.getClickCount() > 1) {
            TbArticulo art = TB_Articulos.getSelectionModel().getSelectedItem();
            if (art != null) openRegistryWindow(art);
        }
    }

    @Override
    public void cargarDatos() {
        articulos.clear();
        datos = DBAccess.getInstance().Stream(TbArticulo.class).toList();
        articulos.addAll(datos);
        CB_Categorias.getItems().clear();
        CB_Categorias.getItems().addAll(DBAccess.getInstance().Stream(TbCategoria.class).select(categoria -> categoria.getCatName()).toList());
        CB_Categorias.getItems().add(0, "Todas las Categorías");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UtilsUI.setUpColumnsValues(new String[]{"categoria", "id", "desc", "estado", "grav"},
                new TableColumn[]{Col_Categoria, Col_Codigo, Col_Desc, Col_Estado, Col_Gravamen});
        Col_Estado.setCellFactory(CheckBoxTableCell.forTableColumn(Col_Estado));
        Col_Gravamen.setCellFactory(CheckBoxTableCell.forTableColumn(Col_Gravamen));

        UtilsUI.textFieldFilter(TB_Articulos, articulos, TF_Buscar, (tbArticulo, str) -> {
            if (str == null || str.isEmpty()) return true;
            return tbArticulo.getId().toLowerCase().contains(str.toLowerCase());
        });

        CB_Categorias.valueProperty().addListener((observable, oldValue, newValue) -> {
            articulos.clear();
            if (newValue.equals("Todas las Categorías"))
                articulos.addAll(datos);
            else
                articulos.addAll(Collections2.filter(datos, tbArticulo -> {
                    if (tbArticulo.categoriaProperty() != null)
                        return tbArticulo.categoriaProperty().get().equals(newValue);
                    return false;
                }));
        });
    }
}