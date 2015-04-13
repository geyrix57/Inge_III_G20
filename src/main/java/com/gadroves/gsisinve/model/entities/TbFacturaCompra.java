package com.gadroves.gsisinve.model.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_Factura_Compra", schema = "", catalog = "sisgradoves")
public class TbFacturaCompra {
    private int id;
    private String provId;
    private Date fecha;
    private double subTotal;
    private double total;
    private double imp;
    private double saldo;
    private TbProveedor tbProveedorByProvId;
    private Collection<TbFacturasCuentaPagar> tbFacturasCuentaPagarsById;
    private Collection<TbPagoCuentaPagar> tbPagoCuentaPagarsById;
    private Collection<TbLineaCompra> tbLineaComprasById;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "fecha", nullable = false, insertable = true, updatable = true)
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Basic
    @Column(name = "subTotal", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    @Basic
    @Column(name = "total", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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
    @Column(name = "saldo", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbFacturaCompra that = (TbFacturaCompra) o;

        if (id != that.id) return false;
        if (Double.compare(that.subTotal, subTotal) != 0) return false;
        if (Double.compare(that.total, total) != 0) return false;
        if (Double.compare(that.imp, imp) != 0) return false;
        if (Double.compare(that.saldo, saldo) != 0) return false;
        if (provId != null ? !provId.equals(that.provId) : that.provId != null) return false;
        return !(fecha != null ? !fecha.equals(that.fecha) : that.fecha != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (provId != null ? provId.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        temp = Double.doubleToLongBits(subTotal);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(total);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(imp);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(saldo);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "prov_id", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
    public TbProveedor getTbProveedorByProvId() {
        return tbProveedorByProvId;
    }

    public void setTbProveedorByProvId(TbProveedor tbProveedorByProvId) {
        this.tbProveedorByProvId = tbProveedorByProvId;
    }

    @OneToMany(mappedBy = "tbFacturaCompraByFactura")
    public Collection<TbFacturasCuentaPagar> getTbFacturasCuentaPagarsById() {
        return tbFacturasCuentaPagarsById;
    }

    public void setTbFacturasCuentaPagarsById(Collection<TbFacturasCuentaPagar> tbFacturasCuentaPagarsById) {
        this.tbFacturasCuentaPagarsById = tbFacturasCuentaPagarsById;
    }

    @OneToMany(mappedBy = "tbFacturaCompraByFactura")
    public Collection<TbPagoCuentaPagar> getTbPagoCuentaPagarsById() {
        return tbPagoCuentaPagarsById;
    }

    public void setTbPagoCuentaPagarsById(Collection<TbPagoCuentaPagar> tbPagoCuentaPagarsById) {
        this.tbPagoCuentaPagarsById = tbPagoCuentaPagarsById;
    }

    @OneToMany(mappedBy = "tbFacturaCompraByFacOrigen")
    public Collection<TbLineaCompra> getTbLineaComprasById() {
        return tbLineaComprasById;
    }

    public void setTbLineaComprasById(Collection<TbLineaCompra> tbLineaComprasById) {
        this.tbLineaComprasById = tbLineaComprasById;
    }
}
