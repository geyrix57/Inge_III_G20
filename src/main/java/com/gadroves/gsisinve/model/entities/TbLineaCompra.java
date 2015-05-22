package com.gadroves.gsisinve.model.entities;

import javafx.beans.property.*;

import javax.persistence.*;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_linea_compra", schema = "", catalog = "sisgradoves")
@IdClass(TbLineaCompraPK.class)
public class TbLineaCompra {
    private int numero;
    private StringProperty codArt = new SimpleStringProperty();
    private IntegerProperty cantidad = new SimpleIntegerProperty();
    private DoubleProperty precio = new SimpleDoubleProperty();
    private DoubleProperty imp = new SimpleDoubleProperty();
    private DoubleProperty descuento = new SimpleDoubleProperty();

    private String facOrigen;
    //private TbArticuloProveedor tbArticuloProveedorByCodArt;
    private TbFacturaCompra tbFacturaCompraByFacOrigen;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "numero", nullable = false, insertable = true, updatable = true)
    public int getNumero() {
        return numero;
    }

    public TbLineaCompra setNumero(int numero) {
        this.numero = numero;
        return this;
    }

    @Basic
    @Column(name = "cod_art", nullable = false, insertable = true, updatable = true, length = 32)
    public String getCodArt() {
        return codArt.get();
    }

    public TbLineaCompra setCodArt(String codArt) {
        this.codArt.set(codArt);
        return this;
    }

    public StringProperty codArtProperty() {
        return codArt;
    }

    @Basic
    @Column(name = "cantidad", nullable = false, insertable = true, updatable = true)
    public int getCantidad() {
        return cantidad.get();
    }

    public TbLineaCompra setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
        return this;
    }

    public IntegerProperty cantidadProperty() {
        return cantidad;
    }

    @Basic
    @Column(name = "precio", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getPrecio() {
        return precio.get();
    }

    public TbLineaCompra setPrecio(double precio) {
        this.precio.set(precio);
        return this;
    }

    public DoubleProperty precioProperty() {
        return precio;
    }

    @Basic
    @Column(name = "imp", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getImp() {
        return imp.get();
    }

    public TbLineaCompra setImp(double imp) {
        this.imp.set(imp);
        return this;
    }

    public DoubleProperty impProperty() {
        return imp;
    }

    @Basic
    @Column(name = "descuento", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getDescuento() {
        return descuento.get();
    }

    public TbLineaCompra setDescuento(double descuento) {
        this.descuento.set(descuento);
        return this;
    }

    public DoubleProperty descuentoProperty() {
        return descuento;
    }

    @Id
    @Column(name = "fac_origen", nullable = false, insertable = true, updatable = true, length = 32)
    public String getFacOrigen() {
        return facOrigen;
    }

    public TbLineaCompra setFacOrigen(String facOrigen) {
        this.facOrigen = facOrigen;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbLineaCompra that = (TbLineaCompra) o;

        if (numero != that.numero) return false;
        if (!codArt.equals(that.codArt)) return false;
        if (!cantidad.equals(that.cantidad)) return false;
        if (!precio.equals(that.precio)) return false;
        if (!imp.equals(that.imp)) return false;
        if (!descuento.equals(that.descuento)) return false;
        return facOrigen.equals(that.facOrigen);

    }

    @Override
    public int hashCode() {
        int result = numero;
        result = 31 * result + codArt.hashCode();
        result = 31 * result + cantidad.hashCode();
        result = 31 * result + precio.hashCode();
        result = 31 * result + imp.hashCode();
        result = 31 * result + descuento.hashCode();
//        result = 31 * result + facOrigen.hashCode();
        return result;
    }

    /*@ManyToOne
    @JoinColumn(name = "cod_art", referencedColumnName = "rel_id", nullable = false, insertable = false, updatable = false)
    public TbArticuloProveedor getTbArticuloProveedorByCodArt() {
        return tbArticuloProveedorByCodArt;
    }

    public void setTbArticuloProveedorByCodArt(TbArticuloProveedor tbArticuloProveedorByCodArt) {
        this.tbArticuloProveedorByCodArt = tbArticuloProveedorByCodArt;
    }*/

    @ManyToOne
    @JoinColumn(name = "fac_origen", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public TbFacturaCompra getTbFacturaCompraByFacOrigen() {
        return tbFacturaCompraByFacOrigen;
    }

    public void setTbFacturaCompraByFacOrigen(TbFacturaCompra tbFacturaCompraByFacOrigen) {
        this.tbFacturaCompraByFacOrigen = tbFacturaCompraByFacOrigen;
    }
}
