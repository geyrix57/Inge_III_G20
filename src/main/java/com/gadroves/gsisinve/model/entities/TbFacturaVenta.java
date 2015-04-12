package com.gadroves.gsisinve.model.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_Factura_Venta", schema = "", catalog = "sisgradoves")
public class TbFacturaVenta {
    private int id;
    private Date facDate;
    private double sub;
    private double total;
    private double impuestos;
    private String address;

    private TbCLienteFactura tbCLienteFacturaById;
    private Collection<TbCobro> tbCobrosById;
    private Collection<TbGarantia> tbGarantiasById;
    private Collection<TbLineaFac> tbLineaFacsById;

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
    @Column(name = "fac_date", nullable = false, insertable = true, updatable = true)
    public Date getFacDate() {
        return facDate;
    }

    public void setFacDate(Date facDate) {
        this.facDate = facDate;
    }

    @Basic
    @Column(name = "sub", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getSub() {
        return sub;
    }

    public void setSub(double sub) {
        this.sub = sub;
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
    @Column(name = "address", nullable = true, insertable = true, updatable = true, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "impuestos", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(double impuestos) {
        this.impuestos = impuestos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TbFacturaVenta)) return false;

        TbFacturaVenta that = (TbFacturaVenta) o;

        if (id != that.id) return false;
        if (Double.compare(that.sub, sub) != 0) return false;
        if (Double.compare(that.total, total) != 0) return false;
        if (Double.compare(that.impuestos, impuestos) != 0) return false;
        if (!facDate.equals(that.facDate)) return false;
        return address.equals(that.address);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + facDate.hashCode();
        temp = Double.doubleToLongBits(sub);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(total);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(impuestos);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + address.hashCode();
        return result;
    }

    @OneToOne(mappedBy = "tbFacturaVentaByFacId")
    public TbCLienteFactura getTbCLienteFacturaById() {
        return tbCLienteFacturaById;
    }

    public void setTbCLienteFacturaById(TbCLienteFactura tbCLienteFacturaById) {
        this.tbCLienteFacturaById = tbCLienteFacturaById;
    }

    @OneToMany(mappedBy = "tbFacturaVentaByFacturaAsociada")
    public Collection<TbCobro> getTbCobrosById() {
        return tbCobrosById;
    }

    public void setTbCobrosById(Collection<TbCobro> tbCobrosById) {
        this.tbCobrosById = tbCobrosById;
    }

    @OneToMany(mappedBy = "tbFacturaVentaByFacturaAsociada")
    public Collection<TbGarantia> getTbGarantiasById() {
        return tbGarantiasById;
    }

    public void setTbGarantiasById(Collection<TbGarantia> tbGarantiasById) {
        this.tbGarantiasById = tbGarantiasById;
    }

    @OneToMany(mappedBy = "tbFacturaVentaByFacId")
    public Collection<TbLineaFac> getTbLineaFacsById() {
        return tbLineaFacsById;
    }

    public void setTbLineaFacsById(Collection<TbLineaFac> tbLineaFacsById) {
        this.tbLineaFacsById = tbLineaFacsById;
    }

}
