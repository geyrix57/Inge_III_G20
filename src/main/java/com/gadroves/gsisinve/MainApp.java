package com.gadroves.gsisinve;
/**
 * Created by geykel on 04/03/2015.
 */


import com.gadroves.gsisinve.controller.UI.ScreensController;
import com.gadroves.gsisinve.model.beans.Articulo;
import com.gadroves.gsisinve.model.beans.Bodega;
import com.gadroves.gsisinve.model.daos.ArticuloDAO;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainApp extends Application {

    private ScreensController mainContainer = new ScreensController();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        if(mainContainer.setScreen("login")) {
            Group root = new Group();
            root.getChildren().addAll(mainContainer);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        /*ArrayList<Bodega> arrayList = new ArrayList<>();
        arrayList.add(new Bodega("", "1"));
        List<Articulo> articulos = ArticuloDAO.getInstance().select().AllInBodegaList(arrayList);
        for (Articulo ar : articulos) Mensajes.InformationDialog(ar.getCodigo() + " " + ar.getDescripcion(), primaryStage);*/
    }
}
