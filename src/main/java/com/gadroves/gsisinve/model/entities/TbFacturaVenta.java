package com.gadroves.gsisinve.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
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
    private BigDecimal sub;
    private BigDecimal total;
    private BigDecimal tax;
    private String address;
    private TbCLienteFactura tbCLienteFacturaById;
    private Collection<TbCobro> tbCobrosById;
    private Collection<TbGarantia> tbGarantiasById;
    private Collection<TbLineaFac> tbLineaFacsById;

    @Id
    @Column(name = "\"id\"", nullable = false, insertable = true, updatable = true)
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
    @Column(name = "sub", nullable = false, insertable = true, updatable = true, precision = 3)
    public BigDecimal getSub() {
        return sub;
    }

    public void setSub(BigDecimal sub) {
        this.sub = sub;
    }

    @Basic
    @Column(name = "total", nullable = false, insertable = true, updatable = true, precision = 3)
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Basic
    @Column(name = "tax", nullable = false, insertable = true, updatable = true, precision = 3)
    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    @Basic
    @Column(name = "address", nullable = true, insertable = true, updatable = true, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbFacturaVenta that = (TbFacturaVenta) o;

        if (id != that.id) return false;
        if (facDate != null ? !facDate.equals(that.facDate) : that.facDate != null) return false;
        if (sub != null ? !sub.equals(that.sub) : that.sub != null) return false;
        if (total != null ? !total.equals(that.total) : that.total != null) return false;
        if (tax != null ? !tax.equals(that.tax) : that.tax != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (facDate != null ? facDate.hashCode() : 0);
        result = 31 * result + (sub != null ? sub.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (tax != null ? tax.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
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
