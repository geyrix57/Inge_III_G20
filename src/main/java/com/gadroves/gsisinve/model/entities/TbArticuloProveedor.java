package com.gadroves.gsisinve.model.entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_Articulo_Proveedor", schema = "", catalog = "sisgradoves")
public class TbArticuloProveedor {
    private String artId;
    private String provId;
    private double precioCompra;
    private TbArticulo tbArticuloByArtId;
    private TbProveedor tbProveedorByProvId;
    private Collection<TbLineaCompra> tbLineaComprasByArtId;

    @Id
    @Column(name = "art_id", nullable = false, insertable = true, updatable = true, length = 32)
    public String getArtId() {
        return artId;
    }

    public void setArtId(String artId) {
        this.artId = artId;
    }

    @Basic
    @Column(name = "prov_id", nullable = false, insertable = true, updatable = true, length = 32)
    public String getProvId() {
        return provId;
    }

    public void setProvId(String provId) {
        this.provId = provId;
    }

    @Basic
    @Column(name = "precio_compra", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbArticuloProveedor that = (TbArticuloProveedor) o;

        if (Double.compare(that.precioCompra, precioCompra) != 0) return false;
        if (artId != null ? !artId.equals(that.artId) : that.artId != null) return false;
        if (provId != null ? !provId.equals(that.provId) : that.provId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = artId != null ? artId.hashCode() : 0;
        result = 31 * result + (provId != null ? provId.hashCode() : 0);
        temp = Double.doubleToLongBits(precioCompra);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @OneToOne
    @JoinColumn(name = "art_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public TbArticulo getTbArticuloByArtId() {
        return tbArticuloByArtId;
    }

    public void setTbArticuloByArtId(TbArticulo tbArticuloByArtId) {
        this.tbArticuloByArtId = tbArticuloByArtId;
    }

    @ManyToOne
    @JoinColumn(name = "prov_id", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
    public TbProveedor getTbProveedorByProvId() {
        return tbProveedorByProvId;
    }

    public void setTbProveedorByProvId(TbProveedor tbProveedorByProvId) {
        this.tbProveedorByProvId = tbProveedorByProvId;
    }

    @OneToMany(mappedBy = "tbArticuloProveedorByCodArt")
    public Collection<TbLineaCompra> getTbLineaComprasByArtId() {
        return tbLineaComprasByArtId;
    }

    public void setTbLineaComprasByArtId(Collection<TbLineaCompra> tbLineaComprasByArtId) {
        this.tbLineaComprasByArtId = tbLineaComprasByArtId;
    }
}
