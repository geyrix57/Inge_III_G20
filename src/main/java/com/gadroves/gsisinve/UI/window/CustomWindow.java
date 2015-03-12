package com.gadroves.gsisinve.UI.window;


import com.gadroves.gsisinve.utils.R;
import javafx.fxml.FXMLLoader;
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

    public CustomWindow(Stage st, Node clientArea) {
        this.stage = st;
        this.clientArea = clientArea;
    }

    public void show(double width, double height) throws IOException {
        build(width, height);
        this.stage.show();
    }

    private void setActionToButton(WindowController wc) {
        wc.getClose().setOnAction(ev->{
            stage.close();
        });
        wc.getMinimize().setOnAction(ev->{
            stage.setIconified(true);
        });
        wc.getMaximize().setOnAction(ev -> {
            stage.setMaximized(true);
        });
    }

    private Parent loadWindow() throws IOException {
        FXMLLoader Loader = new FXMLLoader(R.getFxml("window"));
        Parent window = Loader.load();
        setActionToButton ((WindowController)Loader.getController());
        return  window;
    }

    private void build(double width, double height) throws IOException {
        AnchorPane root = (AnchorPane)loadWindow();
        root.getChildren().addAll(clientArea);
        root.setTopAnchor(clientArea, 35d);
        root.setBottomAnchor(clientArea, 0d);
        root.setLeftAnchor(clientArea, 0d);
        root.setRightAnchor(clientArea, 0d);
        Scene sc = new Scene(root);
        stage.setScene(sc);
        sc.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        resizeListener = new ResizeListener(this.stage, sc, height, width);
    }

    private ResizeListener resizeListener;
    final private Node clientArea;
    final private Stage stage;
}
