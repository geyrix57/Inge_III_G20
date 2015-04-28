package com.gadroves.gsisinve;

/**
 * Created by geykel on 04/03/2015.
 */

import com.gadroves.gsisinve.UI.CustomWindow;
import com.gadroves.gsisinve.model.DBAccess;
import com.gadroves.gsisinve.utils.R;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;


public class MainApp extends Application {

    public static void main(String[] args) {
        new Thread(() -> DBAccess.getInstance()).start();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setOnHiding(event -> Platform.exit());
        new CustomWindow(primaryStage, R.loadScreen("main")).show();
    }

}
