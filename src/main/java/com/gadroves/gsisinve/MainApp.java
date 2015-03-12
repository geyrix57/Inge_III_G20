package com.gadroves.gsisinve;
/**
 * Created by geykel on 04/03/2015.
 */


import com.gadroves.gsisinve.UI.controller.ScreensController;
import com.gadroves.gsisinve.UI.window.CustomWindow;
import javafx.application.Application;
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
            CustomWindow window = new CustomWindow(primaryStage,mainContainer);
            window.show(860,660);
        }
    }
}
