package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.model.DBAccess;
import com.gadroves.gsisinve.model.entities.TbClienteCuenta;
import com.gadroves.gsisinve.model.entities.TbContactoCliente;
import com.gadroves.gsisinve.utils.DialogBox;
import com.gadroves.gsisinve.utils.InitData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.eclipse.persistence.indirection.IndirectList;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by geykel on 26/04/2015.
 */
public class ClienteController implements Initializable, InitData<TbClienteCuenta> {

    @FXML
    TextField TF_Codigo;
    @FXML
    TextField TF_Nombre;
    @FXML
    ComboBox<String> CB_TipoContacto;
    @FXML
    TextField TF_InfoContacto;
    @FXML
    TableView<TbContactoCliente> TB_InfoContacto;
    @FXML
    TableColumn<TbContactoCliente, String> Col_TipoContacto;
    @FXML
    TableColumn<TbContactoCliente, String> Col_InfoContacto;

    private ObservableList<TbContactoCliente> contact = FXCollections.observableArrayList();
    private List<TbContactoCliente> addContact = null;
    private List<TbContactoCliente> removeContact = null;
    private TbClienteCuenta refered = null;
    private boolean update = false;

    private Stage getStage() {
        return (Stage) TF_Codigo.getParent().getScene().getWindow();
    }

    private void intTbInfoContacto() {
        TB_InfoContacto.setEditable(true);
        Col_TipoContacto.setCellValueFactory(new PropertyValueFactory("tipo"));
        Col_InfoContacto.setCellValueFactory(new PropertyValueFactory("valor"));
        Col_InfoContacto.setCellFactory(TextFieldTableCell.forTableColumn());
        Col_InfoContacto.setOnEditCommit(event -> event.getRowValue().setValor(event.getNewValue()));
        TB_InfoContacto.setOnKeyPressed(event -> {
            TbContactoCliente cc = TB_InfoContacto.getSelectionModel().getSelectedItem();
            if (cc != null && event.getCode().equals(KeyCode.DELETE)) {
                if (update) {
                    if (addContact.contains(cc)) addContact.remove(cc);
                    else removeContact.add(cc);
                }
                contact.remove(cc);
            }
        });
        TB_InfoContacto.setItems(contact);
    }

    private void validarCampos() throws Exception {
        if (TF_Codigo.getText().isEmpty() || TF_Nombre.getText().isEmpty())
            throw new Exception("Debe llenar todos los campos.!!");
    }

    @FXML
    private void agregarInfoContacto() {
        if (CB_TipoContacto.getValue() != null && !TF_InfoContacto.getText().isEmpty()) {
            TbContactoCliente cc = new TbContactoCliente()
                    .setTipo(CB_TipoContacto.getValue())
                    .setValor(TF_InfoContacto.getText());
            if (update) addContact.add(cc);
            contact.add(cc);
        } else DialogBox.Error(this.getStage(), "Debe llenar los dos campos.!!");
    }

    @FXML
    private void nuevo() {
        contact.clear();
        TF_Codigo.clear();
        TF_InfoContacto.clear();
        TF_Nombre.clear();
        TF_Codigo.setEditable(true);
        if (update) {
            addContact.clear();
            removeContact.clear();
        }
        update = false;
    }

    @FXML
    private void imprimir() {

    }

    @FXML
    private void aceptar() {
        DBAccess dbAccess = DBAccess.getInstance();
        try {
            validarCampos();
            dbAccess.getTransaction().begin();
            if (update) {
                dbAccess.Update(refered.setNombre(TF_Nombre.getText()));
                for (TbContactoCliente cc : removeContact) dbAccess.Delete(cc);
                for (TbContactoCliente cc : addContact) dbAccess.Insert(cc);
                for (TbContactoCliente cc : contact)
                    if (!addContact.contains(cc)) dbAccess.Update(cc);

                refered.getTbContactoClienteById().removeAll(removeContact);
                refered.getTbContactoClienteById().addAll(addContact);
            } else {
                TbClienteCuenta cc = new TbClienteCuenta()
                        .setId(TF_Codigo.getText())
                        .setNombre(TF_Nombre.getText());
                dbAccess.Insert(cc);
                cc.setTbContactoClienteById(new IndirectList<>());
                for (TbContactoCliente c : contact) {
                    dbAccess.Insert(c.setTbClienteCuentaByIdCliente(cc));
                    cc.getTbContactoClienteById().add(c);
                }
            }
            DialogBox.Informativo(this.getStage(), "El proveedor se ha guardado correctamente.!");
            if (this.update) this.cancelar();//Cierra ventana
            else nuevo();
            dbAccess.getTransaction().commit();
        } catch (Exception e) {
            DialogBox.Excepcion(this.getStage(), "Se ha generedo una excepción.", e);
        }
    }

    @FXML
    private void cancelar() {
        this.getStage().close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        intTbInfoContacto();
        CB_TipoContacto.getItems().addAll("Teléfono", "Celular", "Fax", "E-mail", "Dirección");
    }

    @Override
    public void initData(TbClienteCuenta obj) {
        refered = obj;
        update = true;
        TF_Codigo.setEditable(false);
        TF_Codigo.setText(refered.getId());
        TF_Nombre.setText(refered.getNombre());
        contact.addAll(refered.getTbContactoClienteById());
    }

}
