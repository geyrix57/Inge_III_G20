package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.model.DBAccess;
import com.gadroves.gsisinve.model.entities.TbArticulo;
import com.gadroves.gsisinve.model.entities.TbCategoria;
import com.gadroves.gsisinve.utils.DialogBox;
import com.gadroves.gsisinve.utils.InitData;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by geykel on 16/04/2015.
 */
public class ArticuloController implements Initializable, InitData<TbArticulo> {

    @FXML
    ComboBox<TbCategoria> CB_Categoria;
    @FXML
    TextField TF_Codigo;
    @FXML
    TextField TF_Desc;
    @FXML
    TextField TF_Costo;
    @FXML
    TextField TF_Util;
    @FXML
    CheckBox ChB_Gravamen;
    @FXML
    CheckBox ChB_Activo;

    private boolean update = false;
    private TbArticulo refered = null;
    private DBAccess dbAccess = DBAccess.getInstance();

    private Stage getStage() {
        return (Stage) TF_Codigo.getParent().getScene().getWindow();
    }

    private void validateNumericTextField(TextField field, boolean dec) {
        field.lengthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                char ch = field.getText().charAt(oldValue.intValue());
                if (!(ch >= '0' && ch <= '9' || (dec && ch == '.')))
                    field.setText(field.getText().substring(0, field.getText().length() - 1));
            }
        });
    }

    private void initCbCategoria() {
        CB_Categoria.setItems(FXCollections.observableArrayList(dbAccess.Stream(TbCategoria.class).toList()));
        CB_Categoria.setCellFactory(param -> new ListCell<TbCategoria>() {
            @Override
            protected void updateItem(TbCategoria item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) setGraphic(null);
                else setText(item.getCatName());
            }
        });
        CB_Categoria.setConverter(new StringConverter<TbCategoria>() {
            @Override
            public String toString(TbCategoria object) {
                if (object == null) return null;
                return object.getCatName();
            }

            @Override
            public TbCategoria fromString(String string) {
                return null;
            }
        });
    }

    private void camposValidos() throws Exception {
        String s = TF_Codigo.getText();
        if (!update && dbAccess.Stream(TbArticulo.class).where(tbArticulo -> tbArticulo.getId().equals(s)).count() > 0)
            throw new Exception("El código del articulo ingresado ya existe. Debe ingresar uno nuevo.");
        if (!(CB_Categoria.getValue() != null && TF_Codigo.getText() != null && !TF_Codigo.getText().isEmpty() && TF_Desc.getText() != null && !TF_Desc.getText().isEmpty()))
            throw new Exception("Debe llenar todos los campos.!!");
    }

    @FXML
    private void limpiar() {
        TF_Codigo.clear();
        TF_Codigo.setEditable(true);
        TF_Costo.clear();
        TF_Desc.clear();
        TF_Util.clear();
        ChB_Activo.setSelected(false);
        ChB_Gravamen.setSelected(false);
        update = false;
    }

    @FXML
    private void aceptar() {
        try {
            camposValidos();
            TbCategoria cat = CB_Categoria.getValue();
            dbAccess.getTransaction().begin();
            if (update) {
                dbAccess.Update(refered.setCategoriaId(cat.gettId())
                        .setTbCategoriaByCategoriaId(cat)
                        .setCost(Double.parseDouble(TF_Costo.getText()))
                        .setUtil(Double.parseDouble(TF_Util.getText()))
                        .setDesc(TF_Desc.getText())
                        .setEstado(ChB_Activo.isSelected())
                        .setGrav(ChB_Gravamen.isSelected()));
            } else {
                dbAccess.Insert(new TbArticulo()
                        .setId(TF_Codigo.getText())
                        .setCategoriaId(cat.gettId())
                        .setTbCategoriaByCategoriaId(cat)
                        .setCost(Double.parseDouble(TF_Costo.getText()))
                        .setUtil(Double.parseDouble(TF_Util.getText()))
                        .setDesc(TF_Desc.getText())
                        .setEstado(ChB_Activo.isSelected())
                        .setGrav(ChB_Gravamen.isSelected()));
            }
            dbAccess.getTransaction().commit();
            DialogBox.Informativo(getStage(), "El articulo se guardó exitosamente.!!");
            limpiar();
        } catch (Exception e) {
            DialogBox.Excepcion(getStage(), "Se ha generado una excepción.", e);
        }
    }

    @FXML
    private void cancelar() {
        this.getStage().close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCbCategoria();
        validateNumericTextField(TF_Costo, true);
        validateNumericTextField(TF_Util, true);
    }

    @Override
    public void initData(TbArticulo obj) {
        refered = obj;
        update = true;
        CB_Categoria.getSelectionModel().select(refered.getTbCategoriaByCategoriaId());
        TF_Codigo.setEditable(false);
        TF_Codigo.setText(refered.getId());
        TF_Desc.setText(refered.getDesc());
        TF_Costo.setText(String.valueOf(refered.getCost()));
        TF_Util.setText(String.valueOf(refered.getUtil()));
        ChB_Activo.setSelected(refered.getEstado());
        ChB_Gravamen.setSelected(refered.getGrav());
    }

}
