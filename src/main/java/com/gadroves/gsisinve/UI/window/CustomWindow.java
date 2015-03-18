package com.gadroves.gsisinve.UI.window;


import com.gadroves.gsisinve.utils.R;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Created by geykel on 11/03/2015.
 */
public class CustomWindow {

    public CustomWindow(Node clientArea){
        this.stage = new Stage();
        this.clientArea = clientArea;
        this.clientArea.setCursor(Cursor.DEFAULT);
    }

    public CustomWindow(Stage st, Node clientArea) {
        this.stage = st;
        this.clientArea = clientArea;
        this.clientArea.setCursor(Cursor.DEFAULT);
    }

    public void show() throws IOException {
        build();
        this.stage.show();
        new ResizeListener(this.stage, stage.getHeight(),stage.getWidth());
    }

    private void setActionToButton(WindowController wc) {
        wc.getClose().setOnAction(ev->{
            stage.close();
        });
        wc.getMinimize().setOnAction(ev->{
            stage.setIconified(true);
        });
        wc.getMaximize().setOnAction(ev -> {
            stage.setMaximized(!stage.isMaximized());
        });
    }

    private Parent loadWindow() throws IOException {
        FXMLLoader Loader = new FXMLLoader(R.getFxml("window"));
        Parent window = Loader.load();
        setActionToButton ((WindowController)Loader.getController());
        return  window;
    }

    private void build() throws IOException {
        AnchorPane root = (AnchorPane)loadWindow();
        root.getChildren().addAll(clientArea);
        root.setTopAnchor(clientArea, 30d);
        root.setBottomAnchor(clientArea, 5d);
        root.setLeftAnchor(clientArea, 5d);
        root.setRightAnchor(clientArea, 5d);
        Scene sc = new Scene(root);
        stage.setScene(sc);
        sc.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
    }

    final private Node clientArea;
    final private Stage stage;
}
