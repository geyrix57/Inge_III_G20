package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.model.DBAccess;
import com.gadroves.gsisinve.model.entities.TbBodega;
import com.gadroves.gsisinve.utils.DialogBox;
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
    TbBodega refered = null;

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
                refered = ref;
            }catch (Exception es){
                TF_Bodega_desc.setText("");
                update = false;
                refered = null;
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
                    refered = ref;
                }catch (Exception es){
                    TF_Bodega_desc.setText("");
                    update = false;
                    refered = null;
                }
            }
        });
    }
    @FXML
    private void guardar() throws Exception {
        DBAccess dbAccess = DBAccess.getInstance();
        if(update) {
            dbAccess.getTransaction().begin();
            refered.setDesc(TF_Bodega_desc.getText());
            dbAccess.Update(refered);
            dbAccess.getTransaction().commit();
        }
        else{
            dbAccess.getTransaction().begin();
            TbBodega tb = new TbBodega();
            tb.setCode(TF_Bodega_ID.getText());
            tb.setDesc(TF_Bodega_desc.getText());
            dbAccess.Insert(tb);
            dbAccess.getTransaction().commit();
            refered = tb;
            update = true;
        }
    }
    @FXML
    private void eliminarBD() throws Exception{
        DBAccess dbAccess = DBAccess.getInstance();
        if(refered != null) {
            dbAccess.getTransaction().begin();
            dbAccess.Delete(refered);
            dbAccess.getTransaction().commit();
            TF_Bodega_ID.setText("");
            TF_Bodega_desc.setText("");
        }
        else DialogBox.Error(null,"No hay ningun Objeto Seleccionado");
    }
}
