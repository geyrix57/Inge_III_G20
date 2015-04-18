package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.model.DBAccess;
import com.gadroves.gsisinve.model.entities.TbCategoria;
import com.gadroves.gsisinve.utils.DialogBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by geykel on 14/04/2015.
 */
public class CategoriaController implements Initializable {

    @FXML
    TextField Tf_nombre;
    @FXML
    TextArea Tf_descripcion;
    @FXML
    TextField Tf_buscar;
    @FXML
    TableView<TbCategoria> Tb_categorias;
    @FXML
    TableColumn<TbCategoria, String> Col_descripcion;
    @FXML
    TableColumn<TbCategoria, String> Col_nombre;

    private boolean update = false;
    private TbCategoria updateCategoria = null;
    private ObservableList<TbCategoria> categorias = FXCollections.observableArrayList();
    private FilteredList<TbCategoria> filteredData;

    private Stage getStage() {
        return (Stage) Tf_nombre.getParent().getScene().getWindow();
    }

    private void initTbCategorias() {
        Col_descripcion.setCellValueFactory(new PropertyValueFactory<TbCategoria, String>("catDesc"));
        Col_nombre.setCellValueFactory(new PropertyValueFactory<TbCategoria, String>("catName"));

        filteredData = new FilteredList(this.categorias, p -> true);
        SortedList<TbCategoria> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(Tb_categorias.comparatorProperty());
        Tb_categorias.setItems(sortedData);

        Tb_categorias.setOnMouseClicked(event -> {
            if (event.getClickCount() > 1) {
                this.updateCategoria = Tb_categorias.getSelectionModel().getSelectedItem();
                this.update = true;
                this.Tf_nombre.setText(updateCategoria.getCatName());
                this.Tf_descripcion.setText(updateCategoria.getCatDesc());
            }
        });
    }

    private void initTfBuscar() {
        Tf_buscar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(categoria -> {
                if (newValue == null || newValue.isEmpty()) return true;
                return categoria.getCatName().toLowerCase().contains(newValue.toLowerCase());
            });
        });
    }

    private boolean camposValidos() {
        return (Tf_nombre != null && !Tf_nombre.getText().isEmpty() &&
                Tf_descripcion != null && !Tf_descripcion.getText().isEmpty());
    }

    private void limpiarCampos() {
        this.update = false;
        Tf_nombre.clear();
        Tf_descripcion.clear();
    }

    private void guardarDatos() throws Exception {
        String s = Tf_nombre.getText();
        if (this.camposValidos()) {
            if (DBAccess.getInstance().Stream(TbCategoria.class).where(categoria -> categoria.getCatName().equals(s)).count() > 0)
                throw new Exception("El nombre de la categoria ya existe. Ingrese uno nuevo.");
            DBAccess.getInstance().getTransaction().begin();
            TbCategoria c = new TbCategoria()
                    .setCatName(this.Tf_nombre.getText())
                    .setCatDesc(this.Tf_descripcion.getText());
            DBAccess.getInstance().Insert(c);
            DBAccess.getInstance().getTransaction().commit();
            this.categorias.add(c);
        } else throw new Exception("Debe llenar todos los campos.!!");
    }

    private void actualizarDatos() throws Exception {
        if (this.camposValidos()) {
            DBAccess.getInstance().getTransaction().begin();
            DBAccess.getInstance().Update(this.updateCategoria
                    .setCatName(this.Tf_nombre.getText())
                    .setCatDesc(this.Tf_descripcion.getText()));
            DBAccess.getInstance().getTransaction().commit();
        } else throw new Exception("Debe llenar todos los campos.!!");
    }

    @FXML
    private void aceptar() {
        try {
            if (!this.update) {
                guardarDatos();
            } else {
                actualizarDatos();
                this.update = false;
            }
            this.limpiarCampos();
            DialogBox.Informativo(getStage(), "La categoria se guardó exitosamente.!!");
        } catch (Exception e) {
            DialogBox.Excepcion(getStage(), "Se ha generado una excepción.!!", e);
        }
    }

    @FXML
    private void cargarDatos() {
        this.categorias.clear();
        this.categorias.addAll(DBAccess.getInstance().Stream(TbCategoria.class).toList());
    }

    @FXML
    private void nuevo() {
        this.limpiarCampos();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTbCategorias();
        initTfBuscar();
    }
}
