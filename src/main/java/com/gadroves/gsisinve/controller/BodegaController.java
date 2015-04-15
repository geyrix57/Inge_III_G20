
package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.model.DBAccess;
import com.gadroves.gsisinve.model.entities.TbBodega;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    TableView<TbBodega> TV_Bodegas;
    @FXML
    TableColumn<TbBodega,String> TVC_Codigo;
    @FXML
    TableColumn<TbBodega,String> TVC_Descripcion;
    @FXML
    Button BTN_PrintMe;

    ObservableList<TbBodega> bodegas = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DBAccess dbAccess = DBAccess.getInstance();
        TVC_Codigo.setCellValueFactory(new PropertyValueFactory("code"));
        TVC_Descripcion.setCellValueFactory(new PropertyValueFactory("desc"));
        bodegas.addAll(dbAccess.Stream(TbBodega.class).toList());
        TV_Bodegas.setItems(bodegas);
    }    

    @FXML
    private void PrintMe() throws Exception{
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
        stringBuffer.append("<head>");
        stringBuffer.append("<style>" +
        "@page"+
         "{"+
           "size: 21cm 29.7cm;"+
           "margin: 1cm;"+
           "-fs-flow-top: \"header\";"+
           "-fs-flow-bottom: \"footer\";"+
           "-fs-flow-left: \"left\";"+
           "-fs-flow-right: \"right\";"+
           "border: thin solid black;"+ "" +
           "/*padding: 1em;*/"+
        "}"+
        ".A4Portrait {"+
            "/*padding:30px;*/"+
            "background-color:white;"+
            "width:21cm;"+
            "height:29.7cm;"+
            "margin-left:auto;"+
            "margin-right:auto;"+
            "}"+
            "table {"+
            "width: 16cm;"+
            "margin: 0 auto;"+
            "border-collapse: collapse;"+
        "}"+
        "table, td, th {"+
            "border: 1px solid black;"+
            "text-align: center;"+
        "}"+
        "</style>");
        stringBuffer.append("</head>");
        stringBuffer.append("<body>");
        stringBuffer.append("<table>");
        stringBuffer.append("<tr>");
        stringBuffer.append("<th> Codigo </th>");
        stringBuffer.append("<th> Descripcion </th>");
        stringBuffer.append("</tr>");
        for(TbBodega tb : TV_Bodegas.getItems()){
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
