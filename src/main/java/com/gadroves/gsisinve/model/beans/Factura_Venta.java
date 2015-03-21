package com.gadroves.gsisinve.model.beans;

import javafx.beans.property.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author aaron
 */
public class Factura_Venta {
    static  SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    public Factura_Venta(int numero, String fecha, double impuestos, double subtotal, double total,String direccion) {
        this.numero = new SimpleIntegerProperty(numero);
        this.fecha = new SimpleStringProperty(fecha);
        this.impuestos = new SimpleDoubleProperty(impuestos);
        this.subtotal = new SimpleDoubleProperty(subtotal);
        this.total = new SimpleDoubleProperty(total);
        this.direccion = new SimpleStringProperty(direccion);
        this.lineas = new ArrayList<>();
        cliente_factura = null;
    }
    public Factura_Venta(int numero, Date fecha, double impuestos, double subtotal, double total,String direccion) {
        this.numero = new SimpleIntegerProperty(numero);
        this.fecha = new SimpleStringProperty(df.format(fecha));
        this.impuestos = new SimpleDoubleProperty(impuestos);
        this.subtotal = new SimpleDoubleProperty(subtotal);
        this.total = new SimpleDoubleProperty(total);
        this.direccion = new SimpleStringProperty(direccion);
        cliente_factura = null;
        this.lineas = new ArrayList<>();
    }

    public Factura_Venta() {
        this.numero = new SimpleIntegerProperty(0);
        this.fecha = new SimpleStringProperty(null);
        this.impuestos = new SimpleDoubleProperty(0);
        this.subtotal = new SimpleDoubleProperty(0);
        this.total = new SimpleDoubleProperty(0);
        this.lineas = new ArrayList<>();
        this.direccion = new SimpleStringProperty("");
        cliente_factura = null;
    }

    public int getNumero() {
        return numero.get();
    }

    public IntegerProperty numeroProperty() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero.set(numero);
    }

    public String getFecha() {
        return fecha.get();
    }

    public StringProperty fechaProperty() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha.set(fecha);
    }

    public double getImpuestos() {
        return impuestos.get();
    }

    public DoubleProperty impuestosProperty() {
        return impuestos;
    }

    public void setImpuestos(double impuestos) {
        this.impuestos.set(impuestos);
    }

    public double getSubtotal() {
        return subtotal.get();
    }

    public DoubleProperty subtotalProperty() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal.set(subtotal);
    }

    public double getTotal() {
        return total.get();
    }

    public DoubleProperty totalProperty() {
        return total;
    }

    public void setTotal(double total) {
        this.total.set(total);
    }

    public Date getFechaD() throws ParseException {
       return df.parse(this.getFecha());
    }
    public void setDate(Date d){
        this.setFecha(df.format(d));
    }
    public List<Linea_Factura> getLineas() {
        return lineas;
    }

    public String getDireccion() {
        return direccion.get();
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public Cliente_Factura getCliente_factura() {
        return cliente_factura;
    }

    public void setCliente_factura(Cliente_Factura cliente_factura) {
        this.cliente_factura = cliente_factura;
    }

    /**
     * public void agregarLinea(Linea_Factura lf){
     * lf.setNumero_factura(-1);
     * }*
     */
    public void setLineas(List<Linea_Factura> lineas) {
        this.lineas = lineas;
    }

    private IntegerProperty numero;
    private StringProperty fecha;
    private DoubleProperty impuestos;
    private DoubleProperty subtotal;
    private DoubleProperty total;
    private StringProperty direccion;
    private Cliente_Factura cliente_factura;
    private List<Linea_Factura> lineas;
}
