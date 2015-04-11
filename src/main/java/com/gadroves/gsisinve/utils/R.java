package com.gadroves.gsisinve.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 * Created by geykel on 05/03/2015.
 */
public class R {

    private static Properties IdFxml = null;

    public static URL getFxml(String name) throws IOException {
        if (IdFxml == null) {
            IdFxml = new Properties();
            InputStream ips = R.class.getClassLoader().getResourceAsStream("views/ViewId.properties");
            IdFxml.load(ips);
        }
        String p = IdFxml.getProperty(name);
        if (p == null) {
            throw new NoSuchElementException("No se encontro la clave " + name + " en ViewId.properties");
        }
        return R.class.getClassLoader().getResource("views/fxml/" + p + ".fxml");
    }

    public static Node loadScreen(String name) throws IOException {
        FXMLLoader myLoader = new FXMLLoader(getFxml(name));
        Node loadScreen = myLoader.load();
        return loadScreen;
    }

    //Lo voy a dejar asi por el momento pero hay que cambiarlo
    public static InputStream getDBPropertiesAsStream() {
        return R.class.getClassLoader().getResourceAsStream("database/connection.properties");
    }
}
