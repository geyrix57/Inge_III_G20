package com.gadroves.gsisinve.model.entities;

import javafx.beans.property.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_Factura_Compra", schema = "", catalog = "sisgradoves")
public class TbFacturaCompra {
    private StringProperty id = new SimpleStringProperty();
    private StringProperty provId = new SimpleStringProperty();
    private ObjectProperty<Date> fecha = new SimpleObjectProperty<>();
    private DoubleProperty subTotal = new SimpleDoubleProperty();
    private DoubleProperty total = new SimpleDoubleProperty();
    private DoubleProperty imp = new SimpleDoubleProperty();
    private DoubleProperty saldo = new SimpleDoubleProperty();

    private TbProveedor tbProveedorByProvId;
    private Collection<TbFacturasCuentaPagar> tbFacturasCuentaPagarsById;
    private Collection<TbPagoCuentaPagar> tbPagoCuentaPagarsById;
    private Collection<TbLineaCompra> tbLineaComprasById;

    @Id
    @Column(name = "\"id\"", nullable = false, insertable = true, updatable = true, length = 32)
    public String getId() {
        return id.get();
    }

    public TbFacturaCompra setId(String id) {
        this.id.set(id);
        return this;
    }

    public StringProperty idProperty() {
        return id;
    }

    @Basic
    @Column(name = "prov_id", nullable = false, insertable = true, updatable = true, length = 32)
    public String getProvId() {
        return provId.get();
    }

    public TbFacturaCompra setProvId(String provId) {
        this.provId.set(provId);
        return this;
    }

    public StringProperty provIdProperty() {
        return provId;
    }

    @Basic
    @Column(name = "fecha", nullable = false, insertable = true, updatable = true)
    public Date getFecha() {
        return fecha.get();
    }

    public TbFacturaCompra setFecha(Date fecha) {
        this.fecha.set(fecha);
        return this;
    }

    @Basic
    @Column(name = "subTotal", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getSubTotal() {
        return subTotal.get();
    }

    public TbFacturaCompra setSubTotal(double subTotal) {
        this.subTotal.set(subTotal);
        return this;
    }

    public DoubleProperty subTotalProperty() {
        return subTotal;
    }

    @Basic
    @Column(name = "total", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getTotal() {
        return total.get();
    }

    public TbFacturaCompra setTotal(double total) {
        this.total.set(total);
        return this;
    }

    public DoubleProperty totalProperty() {
        return total;
    }

    @Basic
    @Column(name = "imp", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getImp() {
        return imp.get();
    }

    public TbFacturaCompra setImp(double imp) {
        this.imp.set(imp);
        return this;
    }

    public DoubleProperty impProperty() {
        return imp;
    }

    @Basic
    @Column(name = "saldo", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getSaldo() {
        return saldo.get();
    }

    public TbFacturaCompra setSaldo(double saldo) {
        this.saldo.set(saldo);
        return this;
    }

    public DoubleProperty saldoProperty() {
        return saldo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbFacturaCompra that = (TbFacturaCompra) o;

        if (!id.equals(that.id)) return false;
        if (!provId.equals(that.provId)) return false;
        if (!fecha.equals(that.fecha)) return false;
        if (!subTotal.equals(that.subTotal)) return false;
        if (!total.equals(that.total)) return false;
        if (!imp.equals(that.imp)) return false;
        return saldo.equals(that.saldo);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + provId.hashCode();
        result = 31 * result + fecha.hashCode();
        result = 31 * result + subTotal.hashCode();
        result = 31 * result + total.hashCode();
        result = 31 * result + imp.hashCode();
        result = 31 * result + saldo.hashCode();
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
