/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.model.DBAccess;
import com.gadroves.gsisinve.model.entities.TbContactoProveedores;
import com.gadroves.gsisinve.model.entities.TbProveedor;
import com.gadroves.gsisinve.utils.DialogBox;
import com.gadroves.gsisinve.utils.InitData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.eclipse.persistence.indirection.IndirectList;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Aaron
 */
public class ProveedorController implements Initializable, InitData<TbProveedor> {

    @FXML
    RadioButton activo;
    @FXML
    RadioButton inactivo;
    @FXML
    TextField nombre;
    @FXML
    TextField codigo;
    @FXML
    TextField Tf_Info;
    @FXML
    TextArea direccion;
    @FXML
    ComboBox Cb_Tipo;
    @FXML
    TableView<TbContactoProveedores> Tb_InfoContacto;
    @FXML
    TableColumn<TbContactoProveedores, String> Col_Tipo;
    @FXML
    TableColumn<TbContactoProveedores, String> Col_Info;

    private ToggleGroup group;
    private ObservableList<TbContactoProveedores> InformacionContacto = FXCollections.observableArrayList();

    private boolean update = false;
    private TbProveedor updateProveedor = null;
    private List<TbContactoProveedores> eliminar = null;
    private List<TbContactoProveedores> agregar = null;

    private Stage getStage() {
        return (Stage) activo.getParent().getScene().getWindow();
    }

    private void initTbInfoContacto() {
        Tb_InfoContacto.setEditable(true);
        Col_Info.setCellValueFactory(new PropertyValueFactory("valor"));
        Col_Tipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        Col_Info.setCellFactory(TextFieldTableCell.forTableColumn());
        Col_Info.setOnEditCommit(event -> event.getRowValue().setValor(event.getNewValue()));
        Tb_InfoContacto.setOnKeyPressed(event -> {
            TbContactoProveedores cp = Tb_InfoContacto.getSelectionModel().getSelectedItem();
            if (event.getCode().equals(KeyCode.DELETE) && cp != null) {
                if (this.update) {
                    if (agregar.contains(cp)) agregar.remove(cp);
                    else eliminar.add(cp);
                }
                this.InformacionContacto.remove(cp);
            }
        });
    }

    private void initRadioButtons() {
        group = new ToggleGroup();
        this.activo.setUserData(true);
        this.inactivo.setUserData(false);
        this.activo.setToggleGroup(group);
        this.inactivo.setToggleGroup(group);
        group.selectToggle(activo);
    }

    private void camposValidos() throws Exception {
        if (!(codigo.getText() != null && !codigo.getText().isEmpty() && nombre.getText() != null && !nombre.getText().isEmpty() &&
                direccion.getText() != null && !direccion.getText().isEmpty() && (activo.isSelected() || inactivo.isSelected())))
            throw new Exception("Debe llenar todos los campos.!!");
    }

    private void bindingTbProveedor() {
        codigo.setText(this.updateProveedor.getCodigo());
        nombre.setText(this.updateProveedor.getNombre());
        direccion.setText(this.updateProveedor.getDireccion());
        group.selectToggle(this.updateProveedor.getEstado() ? activo : inactivo);
        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            this.updateProveedor.setEstado((boolean) newValue.getUserData());
        });
    }

    @FXML
    private void agregarInfoContacto() {
        if (Cb_Tipo.getValue() != null && Tf_Info.getText() != null && !Tf_Info.getText().isEmpty()) {
            TbContactoProveedores cp = new TbContactoProveedores()
                    .setTipo((String) Cb_Tipo.getValue())
                    .setValor(Tf_Info.getText());
            if (this.update)
                agregar.add(cp.setIdProvedor(this.updateProveedor.getCodigo()));
            InformacionContacto.add(cp);
            Tf_Info.clear();
        } else DialogBox.Error(this.getStage(), "Debe llenar todos los campos.!!");
    }

    @FXML
    private void aceptar() {
        try {
            camposValidos();
            DBAccess.getInstance().getTransaction().begin();
            if (this.update) {
                DBAccess.getInstance().Update(
                        updateProveedor.setDireccion(direccion.getText())
                                .setEstado((boolean) group.getSelectedToggle().getUserData())
                                .setNombre(nombre.getText()));
                for (TbContactoProveedores cp : eliminar) DBAccess.getInstance().Delete(cp);
                for (TbContactoProveedores cp : agregar) DBAccess.getInstance().Insert(cp);
                for (TbContactoProveedores cp : InformacionContacto)
                    if (!agregar.contains(cp)) DBAccess.getInstance().Update(cp);

                updateProveedor.getTbContactoProveedoresByCodigo().removeAll(eliminar);
                updateProveedor.getTbContactoProveedoresByCodigo().addAll(agregar);
            } else {
                TbProveedor prov = new TbProveedor()
                        .setCodigo(codigo.getText())
                        .setDireccion(direccion.getText())
                        .setNombre(nombre.getText())
                        .setEstado((boolean) group.getSelectedToggle().getUserData());
                DBAccess.getInstance().Insert(prov);
                prov.setTbContactoProveedoresByCodigo(new IndirectList<>());
                for (TbContactoProveedores cp : InformacionContacto) {
                    DBAccess.getInstance().Insert(cp.setIdProvedor(prov.getCodigo()).setTbProveedorByIdProvedor(prov));
                    prov.getTbContactoProveedoresByCodigo().add(cp);
                }
            }
            DBAccess.getInstance().getTransaction().commit();
            DialogBox.Informativo(this.getStage(), "El proveedor se ha guardado correctamente.!");
            if (this.update) this.cancelar();//Cierra ventana
            else nuevo();
        } catch (Exception e) {
            DialogBox.Excepcion(this.getStage(), "Se ha generedo una excepción", e);
        }
    }

    @FXML
    private void cancelar() {
        this.getStage().close();
    }

    @FXML
    private void nuevo() {
        InformacionContacto.clear();
        nombre.clear();
        codigo.clear();
        codigo.setEditable(true);
        direccion.clear();
        Tf_Info.clear();
        group.selectToggle(activo);
        if (update) {
            agregar.clear();
            eliminar.clear();
        }
        update = false;
    }

    @FXML
    private void imprimir() {

    }

    @Override
    public void initData(TbProveedor obj) {
        this.updateProveedor = obj;
        this.update = true;
        codigo.setEditable(false);
        bindingTbProveedor();
        this.InformacionContacto.addAll(obj.getTbContactoProveedoresByCodigo());
        eliminar = new ArrayList();
        agregar = new ArrayList();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTbInfoContacto();
        initRadioButtons();
        Cb_Tipo.getItems().addAll("Telefono", "Email", "Fax", "Celular");
        Tb_InfoContacto.setItems(InformacionContacto);
    }

}
