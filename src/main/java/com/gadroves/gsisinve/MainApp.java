package com.gadroves.gsisinve;

/**
 * Created by geykel on 04/03/2015.
 */

import com.gadroves.gsisinve.UI.CustomWindow;
import com.gadroves.gsisinve.utils.R;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;


public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        new CustomWindow(primaryStage, R.loadScreen("main"))
                .show();
    }

}
