package com.gadroves.gsisinve;
/**
 * Created by geykel on 04/03/2015.
 */


import com.gadroves.gsisinve.controller.UI.ScreensController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;


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
            //primaryStage.setResizable(false);
            primaryStage.setTitle("GSISINVE");
            //primaryStage.initStyle(StageStyle.UTILITY);
            primaryStage.show();

            mainContainer.prefWidthProperty().bind(scene.widthProperty());
            mainContainer.prefHeightProperty().bind(scene.heightProperty());
        }
    }
}
