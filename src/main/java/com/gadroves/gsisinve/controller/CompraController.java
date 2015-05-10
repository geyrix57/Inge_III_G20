package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.model.entities.TbFacturaCompra;
import com.gadroves.gsisinve.utils.InitData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by geykel on 08/05/2015.
 */
public class CompraController implements Initializable, InitData<TbFacturaCompra> {
    @FXML
    TextField TF_CodigoProv;
    @FXML
    TextField TF_Consecutivo;
    @FXML
    DatePicker DP_Fecha;
    @FXML
    TextField TF_CodigoArticulo;

    private boolean update = false;
    private TbFacturaCompra referred = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void initData(TbFacturaCompra obj) {
        referred = obj;
        update = true;
    }
}
