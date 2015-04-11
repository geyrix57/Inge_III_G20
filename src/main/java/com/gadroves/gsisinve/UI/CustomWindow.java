package com.gadroves.gsisinve.UI;

import com.gadroves.gsisinve.utils.R;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Created by geykel on 11/03/2015.
 */
public class CustomWindow {

    final private Node clientArea;
    final private Stage stage;

    public CustomWindow(Node clientArea) {
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
        showEffect();
        new ResizeListener(this.stage, stage.getHeight(), stage.getWidth());
    }

    private void showEffect() {
        Timeline fadeIn = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(this.clientArea.opacityProperty(), 0.0)),
                new KeyFrame(new Duration(2500), new KeyValue(this.clientArea.opacityProperty(), 1.0)));
        fadeIn.play();
    }

    private void setActionToButton(WindowController wc) {
        wc.getClose().setOnAction(ev -> {
            stage.close();
        });
        wc.getMinimize().setOnAction(ev -> {
            stage.setIconified(true);
        });
        wc.getMaximize().setOnAction(ev -> {
            stage.setMaximized(!stage.isMaximized());
        });
    }

    private Parent loadWindow() throws IOException {
        FXMLLoader Loader = new FXMLLoader(R.getFxml("window"));
        Parent window = Loader.load();
        setActionToButton(Loader.getController());
        return window;
    }

    private void build() throws IOException {
        AnchorPane root = (AnchorPane) loadWindow();
        root.getChildren().addAll(clientArea);
        AnchorPane.setTopAnchor(clientArea, 30d);
        AnchorPane.setBottomAnchor(clientArea, 5d);
        AnchorPane.setLeftAnchor(clientArea, 5d);
        AnchorPane.setRightAnchor(clientArea, 5d);
        Scene sc = new Scene(root);
        stage.setScene(sc);
        sc.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
    }
}
