package com.gadroves.gsisinve;
/**
 * Created by geykel on 04/03/2015.
 */

import com.gadroves.gsisinve.model.beans.Articulo;
import com.gadroves.gsisinve.model.beans.Bodega;
import com.gadroves.gsisinve.model.daos.ArticuloDAO;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainApp extends Application {

    public static void main(String[] args) {
        ArrayList<Bodega> arrayList = new ArrayList<>();
        arrayList.add(new Bodega("", "1"));
        List<Articulo> articulos = ArticuloDAO.getInstance().select().AllInBodegaList(arrayList);
        for (Articulo ar : articulos) System.out.println(ar.toString());
        //launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
