package com.gadroves.gsisinve.utils;

import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;


/**
 * Created by geykel on 12/04/2015.
 */
public class DialogBox {

    public static void Informativo(Stage stage, String ms) {
        Dialogs.create()
                .owner(stage)
                .title("Informativo")
                .masthead(null)
                .message(ms)
                .showInformation();
    }

    public static void Error(Stage stage, String ms) {
        Dialogs.create()
                .owner(stage)
                .title("Error")
                .masthead(null)
                .message(ms)
                .showError();
    }

    public static void Precaucion(Stage stage, String ms) {
        Dialogs.create()
                .owner(stage)
                .title("Precaución")
                .masthead(null)
                .message(ms)
                .showWarning();
    }

    public static void Excepcion(Stage stage, String ms, Exception e) {
        Dialogs.create()
                .owner(stage)
                .title("Excepcion Generada")
                .masthead(null)
                .message(ms)
                .showException(e);
    }

}
