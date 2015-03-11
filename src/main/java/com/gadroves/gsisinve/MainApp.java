package com.gadroves.gsisinve;
/**
 * Created by geykel on 04/03/2015.
 */


import com.gadroves.gsisinve.controller.UI.ScreensController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class MainApp extends Application {

    private ScreensController mainContainer = new ScreensController();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        if(mainContainer.setScreen("main")) {
            AnchorPane root = new AnchorPane(mainContainer);
            root.setTopAnchor(mainContainer,0d);
            root.setBottomAnchor(mainContainer, 0d);
            root.setLeftAnchor(mainContainer, 0d);
            root.setRightAnchor(mainContainer, 0d);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("GSISINVE");
            primaryStage.show();
        }
    }
}
