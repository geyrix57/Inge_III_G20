package com.gadroves.gsisinve.model.entities;

import javax.persistence.*;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_linea_compra", schema = "", catalog = "sisgradoves")
@IdClass(TbLineaCompraPK.class)
public class TbLineaCompra {
    private int numero;
    private String codArt;
    private int cantidad;
    private double precio;
    private double imp;
    private double descuento;
    private int facOrigen;
    private TbArticuloProveedor tbArticuloProveedorByCodArt;
    private TbFacturaCompra tbFacturaCompraByFacOrigen;

    @Id
    @Column(name = "numero", nullable = false, insertable = true, updatable = true)
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Basic
    @Column(name = "cod_art", nullable = false, insertable = true, updatable = true, length = 32)
    public String getCodArt() {
        return codArt;
    }

    public void setCodArt(String codArt) {
        this.codArt = codArt;
    }

    @Basic
    @Column(name = "cantidad", nullable = false, insertable = true, updatable = true)
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Basic
    @Column(name = "precio", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Basic
    @Column(name = "imp", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getImp() {
        return imp;
    }

    public void setImp(double imp) {
        this.imp = imp;
    }

    @Basic
    @Column(name = "descuento", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    @Id
    @Column(name = "fac_origen", nullable = false, insertable = true, updatable = true)
    public int getFacOrigen() {
        return facOrigen;
    }

    public void setFacOrigen(int facOrigen) {
        this.facOrigen = facOrigen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbLineaCompra that = (TbLineaCompra) o;

        if (numero != that.numero) return false;
        if (cantidad != that.cantidad) return false;
        if (Double.compare(that.precio, precio) != 0) return false;
        if (Double.compare(that.imp, imp) != 0) return false;
        if (Double.compare(that.descuento, descuento) != 0) return false;
        if (facOrigen != that.facOrigen) return false;
        return !(codArt != null ? !codArt.equals(that.codArt) : that.codArt != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = numero;
        result = 31 * result + (codArt != null ? codArt.hashCode() : 0);
        result = 31 * result + cantidad;
        temp = Double.doubleToLongBits(precio);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(imp);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(descuento);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + facOrigen;
        return result;
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "cod_art", referencedColumnName = "art_id", nullable = false, insertable = false, updatable = false), @JoinColumn(name = "cod_art", referencedColumnName = "art_id", nullable = false)})
    public TbArticuloProveedor getTbArticuloProveedorByCodArt() {
        return tbArticuloProveedorByCodArt;
    }

    public void setTbArticuloProveedorByCodArt(TbArticuloProveedor tbArticuloProveedorByCodArt) {
        this.tbArticuloProveedorByCodArt = tbArticuloProveedorByCodArt;
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "fac_origen", referencedColumnName = "id", nullable = false, insertable = false, updatable = false), @JoinColumn(name = "fac_origen", referencedColumnName = "id", nullable = false)})
    public TbFacturaCompra getTbFacturaCompraByFacOrigen() {
        return tbFacturaCompraByFacOrigen;
    }

    public void setTbFacturaCompraByFacOrigen(TbFacturaCompra tbFacturaCompraByFacOrigen) {
        this.tbFacturaCompraByFacOrigen = tbFacturaCompraByFacOrigen;
    }
}
