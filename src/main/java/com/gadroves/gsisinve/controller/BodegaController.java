
package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.model.DBAccess;
import com.gadroves.gsisinve.model.entities.TbBodega;
import com.gadroves.gsisinve.utils.DialogBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class BodegaController implements Initializable {

    @FXML
    TextField TF_Filter;
    @FXML
    TextField TF_Bodega_ID;
    @FXML
    TextField TF_Bodega_desc;
    @FXML
    TableView<TbBodega> TV_Bodegas;
    @FXML
    TableColumn<TbBodega, String> TVC_Codigo;
    @FXML
    TableColumn<TbBodega, String> TVC_Descripcion;

    private boolean update = false;
    private TbBodega refered = null;
    ObservableList<TbBodega> bodegas = FXCollections.observableArrayList();
    FilteredList<TbBodega> filteredData;

    private Stage getStage() {
        return (Stage) TF_Filter.getParent().getScene().getWindow();
    }

    private void initTbBodegas() {
        TVC_Codigo.setCellValueFactory(new PropertyValueFactory("code"));
        TVC_Descripcion.setCellValueFactory(new PropertyValueFactory("desc"));

        filteredData = new FilteredList(this.bodegas, p -> true);
        SortedList<TbBodega> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(TV_Bodegas.comparatorProperty());
        TV_Bodegas.setItems(sortedData);

        TV_Bodegas.setOnMouseClicked(event -> {
            if (event.getClickCount() > 1) {
                update = true;
                this.refered = TV_Bodegas.getSelectionModel().getSelectedItem();
                TF_Bodega_ID.setText(refered.getCode());
                TF_Bodega_ID.setEditable(false);
                TF_Bodega_desc.setText(refered.getDesc());
            }
        });

        TV_Bodegas.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.DELETE)) {
                refered = TV_Bodegas.getSelectionModel().getSelectedItem();
                bodegas.remove(refered);
                eliminarBD();
                if (update) limpiar();
            }
        });
    }

    private void initTFilter() {
        TF_Filter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(bodega -> {
                if (newValue == null || newValue.isEmpty()) return true;
                return bodega.getCode().toLowerCase().contains(newValue.toLowerCase()) ||
                        bodega.getDesc().toLowerCase().contains(newValue.toLowerCase());
            });
        });
    }

    private void camposValidos() throws Exception {
        String s = TF_Bodega_ID.getText();
        if (!update && DBAccess.getInstance().Stream(TbBodega.class).where(tb -> tb.getCode().equals(s)).count() > 0)
            throw new Exception("El número de bodega ingresado ya existe. Debe ingresar uno nuevo.");
        if (!(TF_Bodega_ID != null && TF_Bodega_desc != null && !TF_Bodega_desc.getText().isEmpty() && !TF_Bodega_ID.getText().isEmpty()))
            throw new Exception("Debe llenar todos los campos.!!");
    }

    private void limpiar() {
        this.TF_Bodega_ID.clear();
        this.TF_Bodega_desc.clear();
        TF_Bodega_ID.setEditable(true);
        update = false;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTbBodegas();
        initTFilter();
    }

    @FXML
    private void cargarDatos() {
        bodegas.clear();
        bodegas.addAll(DBAccess.getInstance().Stream(TbBodega.class).toList());
    }

    @FXML
    private void guardar() {
        try {
            camposValidos();
            DBAccess.getInstance().getTransaction().begin();
            if (update) {
                DBAccess.getInstance().Update(refered.setDesc(TF_Bodega_desc.getText()));
                update = false;
            } else {
                TbBodega bodega = new TbBodega();
                DBAccess.getInstance().Insert(bodega
                        .setCode(TF_Bodega_ID.getText())
                        .setDesc(TF_Bodega_desc.getText()));
                bodegas.add(bodega);
            }
            DBAccess.getInstance().getTransaction().commit();
            DialogBox.Informativo(getStage(), "La bodega se guardó exitosamente.!!");
            limpiar();
        } catch (Exception e) {
            DialogBox.Excepcion(getStage(), "Se ha generado una Excepción.", e);
        }
    }

    private void eliminarBD() {
        try {
            DBAccess.getInstance().getTransaction().begin();
            DBAccess.getInstance().Delete(refered);
            DBAccess.getInstance().getTransaction().commit();
        } catch (Exception e) {
            DialogBox.Excepcion(getStage(), "Excepción generada.", e);
        }
    }

    @FXML
    private void nuevo() {
        limpiar();
    }

    @FXML
    private void PrintMe() throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
        stringBuffer.append("<head>");
        stringBuffer.append("<style>" +
                "@page" +
                "{" +
                "size: 21cm 29.7cm;" +
                "margin: 1cm;" +
                "-fs-flow-top: \"header\";" +
                "-fs-flow-bottom: \"footer\";" +
                "-fs-flow-left: \"left\";" +
                "-fs-flow-right: \"right\";" +
                "border: thin solid black;" + "" +
                "/*padding: 1em;*/" +
                "}" +
                ".A4Portrait {" +
                "/*padding:30px;*/" +
                "background-color:white;" +
                "width:21cm;" +
                "height:29.7cm;" +
                "margin-left:auto;" +
                "margin-right:auto;" +
                "}" +
                "table {" +
                "width: 16cm;" +
                "margin: 0 auto;" +
                "border-collapse: collapse;" +
                "}" +
                "table, td, th {" +
                "border: 1px solid black;" +
                "text-align: center;" +
                "}" +
                "</style>");
        stringBuffer.append("</head>");
        stringBuffer.append("<body>");
        stringBuffer.append("<table>");
        stringBuffer.append("<tr>");
        stringBuffer.append("<th> Codigo </th>");
        stringBuffer.append("<th> Descripcion </th>");
        stringBuffer.append("</tr>");
        for (TbBodega tb : TV_Bodegas.getItems()) {
            stringBuffer.append("<tr>");
            stringBuffer.append("<td>").append(tb.getCode()).append("</td>");
            stringBuffer.append("<td>").append(tb.getDesc()).append("</td>");
            stringBuffer.append("</tr>");
        }
        stringBuffer.append("</table>");
        stringBuffer.append("</body>");
        stringBuffer.append("</html>");
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new BufferedInputStream(new ByteArrayInputStream(stringBuffer.toString().getBytes())));

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(doc, null);

        String outputFile = "temp.pdf";
        OutputStream os = new FileOutputStream(outputFile);
        renderer.layout();
        renderer.createPDF(os);
        File file = new File("temp.pdf");
        Desktop.getDesktop().open(file);
        // if(file.delete()){}
        os.close();
    }

    /*
    Ideas pa imprimir
    * A4 Portrait Page, Below is my css: .A4Portrait { padding:30px; background-color:white; width:21cm; height:27.5cm; margin-left:auto; margin-right:auto; box-shadow: 0px 0px 30px rgba(50, 50, 50, 0.75); }
    * http://stackoverflow.com/questions/7355025/create-pdf-with-java
    * */
}
