
package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.model.DBAccess;
import com.gadroves.gsisinve.model.entities.TbBodega;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class BodegaController implements Initializable {

    @FXML
    TextField TF_Filter;
    @FXML
    TableView<TbBodega> TV_Bodegas;
    @FXML
    TableColumn<TbBodega,String> TVC_Codigo;
    @FXML
    TableColumn<TbBodega,String> TVC_Descripcion;
    @FXML
    Button BTN_PrintMe;

    ObservableList<TbBodega> bodegas = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DBAccess dbAccess = DBAccess.getInstance();
        TVC_Codigo.setCellValueFactory(new PropertyValueFactory("code"));
        TVC_Descripcion.setCellValueFactory(new PropertyValueFactory("desc"));
        bodegas.addAll(dbAccess.Stream(TbBodega.class).toList());
        TV_Bodegas.setItems(bodegas);
    }    

    @FXML
    private void PrintMe(){

    }

    /*
    Ideas pa imprimir
    * A4 Portrait Page, Below is my css: .A4Portrait { padding:30px; background-color:white; width:21cm; height:27.5cm; margin-left:auto; margin-right:auto; box-shadow: 0px 0px 30px rgba(50, 50, 50, 0.75); }
    * http://stackoverflow.com/questions/7355025/create-pdf-with-java
    * */
}
