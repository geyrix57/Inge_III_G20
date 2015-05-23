package com.gadroves.gsisinve.controller;

import com.gadroves.gsisinve.model.DBAccess;
import com.gadroves.gsisinve.model.entities.TbFacturaVenta;
import com.gadroves.gsisinve.utils.DialogBox;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.List;


/**
 * Created by Casa on 22/05/2015.
 */
public class FacturarporFechaController implements Initializable {

    /**********************GUI Elements*************************/
    @FXML    DatePicker DP_FInicial;
    @FXML    DatePicker DP_FFinal;
    @FXML    Button BTN_Buscar;
    @FXML    TableView<TbFacturaVenta> TV_Facturas;
    @FXML    TableColumn<TbFacturaVenta,Integer> TC_ID;
    @FXML    TableColumn<TbFacturaVenta, java.util.Date> TC_FacDate;
    @FXML    TableColumn<TbFacturaVenta,Double> TC_Total;
    @FXML    TableColumn<TbFacturaVenta,Double> TC_Imp;
    @FXML    TableColumn<TbFacturaVenta,String> TC_Auth;
    @FXML    TableColumn<TbFacturaVenta,String> TC_Sub;
    /*---------------------------------------------------------*/
    @FXML
    AnchorPane AP_MainContainer;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TC_ID.setCellValueFactory(new PropertyValueFactory<TbFacturaVenta, Integer>("id"));
        TC_FacDate.setCellValueFactory(new PropertyValueFactory<TbFacturaVenta, java.util.Date>("facDate"));
        TC_Total.setCellValueFactory(new PropertyValueFactory<TbFacturaVenta, Double>("total"));
        TC_Imp.setCellValueFactory(new PropertyValueFactory<TbFacturaVenta, Double>("impuestos"));
        TC_Auth.setCellValueFactory(new PropertyValueFactory<TbFacturaVenta, String>("autorization"));
        TC_Sub.setCellValueFactory(new PropertyValueFactory<TbFacturaVenta, String>("sub"));


    }
    @FXML void loadData(){
        if(DP_FFinal.getValue() == null || DP_FInicial.getValue()== null){
            DialogBox.Error((Stage)AP_MainContainer.getScene().getWindow(),"Debe Seleccionar Las Fechas");
            return;
        }
        if(DP_FFinal.getValue().isBefore(DP_FInicial.getValue())) {
            DialogBox.Error((Stage)AP_MainContainer.getScene().getWindow(),"la Fecha Final debe ser Despues de la Inicial");
            return;
        }
        java.util.Date ini = Date.from(DP_FInicial.getValue().minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.util.Date end = Date.from(DP_FFinal.getValue().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<TbFacturaVenta> facturaVentas = DBAccess.getInstance().Stream(TbFacturaVenta.class).where(tb->tb.getFacDate().after(ini)&&tb.getFacDate().before(end)).toList();
        TV_Facturas.getItems().clear();
        TV_Facturas.getItems().addAll(facturaVentas);
    }
    @FXML void PrintTable() throws DocumentException, IOException {
        Font header = new Font(Font.FontFamily.HELVETICA,18,Font.BOLD);
        Font normalBold = new Font(Font.FontFamily.HELVETICA,12,Font.BOLD);
        Font normal = new Font(Font.FontFamily.HELVETICA,12);

        //Rotate rotation = new Rotate();
        String fileName = "Reporte_"+LocalDate.now().toString()+".pdf" ;
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
        Rotate rotation = new Rotate();
        writer.setPageEvent(rotation);
        //rotation.setRotation(PdfPage.LANDSCAPE);
        // step 3
        document.open();
        // step 4
        Paragraph title = new Paragraph("Gadroves S.A Reporte Facturas",header);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(new Chunk("DE "+ DP_FInicial.getValue().toString() + " A " + DP_FFinal.getValue().toString(),normal));
        document.add(new Paragraph(" ")); document.add(new Paragraph(" "));
        /****************Tabla************************************/
        PdfPTable table = new PdfPTable(6);

        PdfPCell c1 = new PdfPCell(new Phrase("ID"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Fecha"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Impuestos"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Sub Total"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Total"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("N° Autorizacion"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        //table.setHeaderRows(1);
        PdfPCell ID;
        PdfPCell Fecha;
        PdfPCell imp;
        PdfPCell Sub;
        PdfPCell Tot;
        PdfPCell Auth;
        for(TbFacturaVenta lineaFac : TV_Facturas.getItems()){
            ID = new PdfPCell(new Phrase(String.valueOf(lineaFac.getId())));
            Fecha = new PdfPCell(new Phrase(lineaFac.getFacDate().toString()));
            imp = new PdfPCell(new Phrase(String.valueOf(lineaFac.getImpuestos())));
            Sub = new PdfPCell(new Phrase(String.valueOf(lineaFac.getSub())));
            Tot = new PdfPCell(new Phrase(String.valueOf(lineaFac.getTotal())));
            Auth = new PdfPCell(new Phrase(lineaFac.getAutorization()));
            table.addCell(ID);
            table.addCell(Fecha);
            table.addCell(imp);
            table.addCell(Sub);
            table.addCell(Tot);
            table.addCell(Auth);
        }
       // float[] columnWidths = new float[] {10f,10f,10f,10f,10f,10f};
        table.setWidthPercentage(90);
        document.add(table);
        /*--------------------------------------------------------*/
        document.close();
        Desktop.getDesktop().open(new File(fileName));
    }
    @FXML void closeWindow(){
        ((Stage)AP_MainContainer.getScene().getWindow()).close();
    }
}
class Rotate extends PdfPageEventHelper {
    protected PdfNumber rotation = PdfPage.PORTRAIT;
    public void setRotation(PdfNumber rotation) {
        this.rotation = rotation;
    }
    public void onEndPage(PdfWriter writer, Document document) {
        writer.addPageDictEntry(PdfName.ROTATE, rotation);
    }
}
