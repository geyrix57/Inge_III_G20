package com.gadroves.gsisinve;
/**
 * Created by geykel on 04/03/2015.
 */

import com.gadroves.gsisinve.controller.LoginUserController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/fxml/LoginUser.fxml"));
        primaryStage.setScene(new Scene((Pane) loader.load()));
        primaryStage.setResizable(false);
        loader.<LoginUserController>getController().initData(primaryStage);
        /*ArrayList<Bodega> arrayList = new ArrayList<>();
        arrayList.add(new Bodega("", "1"));
        List<Articulo> articulos = ArticuloDAO.getInstance().select().AllInBodegaList(arrayList);
        for (Articulo ar : articulos) Mensajes.InformationDialog(ar.getCodigo() + " " + ar.getDescripcion(), primaryStage);*/
        primaryStage.show();
    }
}
