package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.model.DBAccess;
import com.gadroves.gsisinve.model.entities.TbBodega;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Casa on 14/04/2015.
 */
public class AdmBodegaController implements Initializable {
    boolean update = false;

    @FXML
    TextField TF_Bodega_desc;
    @FXML
    TextField TF_Bodega_ID;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EventHandler rr = (e)->{
            DBAccess dbAccess = DBAccess.getInstance();
            String s = TF_Bodega_ID.getText();
            try{
                TbBodega ref = dbAccess.Stream(TbBodega.class).where(tb->tb.getCode().equals(s)).getOnlyValue();
                TF_Bodega_desc.setText(ref.getDesc());
                update = true;
            }catch (Exception es){
                TF_Bodega_desc.setText("");
                update = false;
            }
        };
        TF_Bodega_ID.setOnAction(rr);
        TF_Bodega_ID.focusedProperty().addListener((arg,oldv,newv)->{
            if(!newv){
                DBAccess dbAccess = DBAccess.getInstance();
                String s = TF_Bodega_ID.getText();
                try{
                    TbBodega ref = dbAccess.Stream(TbBodega.class).where(tb->tb.getCode().equals(s)).getOnlyValue();
                    TF_Bodega_desc.setText(ref.getDesc());
                    update = true;
                }catch (Exception es){
                    TF_Bodega_desc.setText("");
                    update = false;
                }
            }
        });
    }
}
