package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.UI.CustomWindow;
import com.gadroves.gsisinve.model.DBAccess;
import com.gadroves.gsisinve.model.entities.TbFacturaCompra;
import com.gadroves.gsisinve.utils.R;
import com.gadroves.gsisinve.utils.UtilsUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.jinq.orm.stream.JinqStream;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

/**
 * Created by geykel on 10/05/2015.
 */
public class ConsultarCompraController implements Consultar<TbFacturaCompra> {

    @FXML
    TableView<TbFacturaCompra> TB_Factura;
    @FXML
    TableColumn<TbFacturaCompra, Date> Col_Fecha;
    @FXML
    TableColumn<TbFacturaCompra, String> Col_CodigoProveedor;
    @FXML
    TableColumn<TbFacturaCompra, String> Col_Consecutivo;
    @FXML
    TableColumn<TbFacturaCompra, Double> Col_Impuesto;
    @FXML
    TableColumn<TbFacturaCompra, Double> Col_Total;
    @FXML
    TableColumn<TbFacturaCompra, Double> Col_Saldo;
    @FXML
    TextField TF_Buscar;
    @FXML
    DatePicker Fecha_Inicio;
    @FXML
    DatePicker Fecha_Final;

    private ObservableList<TbFacturaCompra> datos = FXCollections.observableArrayList();

    @FXML
    private void buscarPorFechas() {
        Date ini = Date.valueOf(Fecha_Inicio.getValue());
        Date fin = Date.valueOf(Fecha_Final.getValue());
        datos.clear();
        JinqStream<TbFacturaCompra> str = DBAccess.getInstance().Stream(TbFacturaCompra.class)
                .where(obj -> obj.getFecha().after(ini) && obj.getFecha().before(fin));
        datos.addAll(str.toList());
    }

    @Override
    public void openRegistryWindow(TbFacturaCompra tbFacturaCompra) {
        try {
            new CustomWindow(R.loadScreen("compra", tbFacturaCompra)).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doubleClick(MouseEvent event) {
        System.out.print("hola");
        if (event.getClickCount() > 1) {
            TbFacturaCompra fc = TB_Factura.getSelectionModel().getSelectedItem();
            openRegistryWindow(fc);
        }
    }

    @Override
    public void cargarDatos() {
        datos.clear();
        datos.addAll(DBAccess.getInstance().Stream(TbFacturaCompra.class).toList());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] properties = {"id", "provId", "fecha", "total", "imp", "saldo"};
        TableColumn[] columns = {Col_Consecutivo, Col_CodigoProveedor, Col_Fecha, Col_Total, Col_Impuesto, Col_Saldo};
        UtilsUI.setUpColumnsValues(properties, columns);
        UtilsUI.textFieldFilter(TB_Factura, datos, TF_Buscar, (tbFacturaCompra, str) -> {
            if (str == null || str.isEmpty()) return true;
            return tbFacturaCompra.getId().toLowerCase().contains(str.toLowerCase()) ||
                    tbFacturaCompra.getProvId().toLowerCase().contains(str.toLowerCase());
        });
    }
}
