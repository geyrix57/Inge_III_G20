package com.gadroves.gsisinve.model.entities;

import javafx.beans.property.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_Factura_Venta", schema = "", catalog = "sisgradoves")
public class TbFacturaVenta {
    private IntegerProperty id = new SimpleIntegerProperty();
    private Date facDate;
    private DoubleProperty sub = new SimpleDoubleProperty();
    private DoubleProperty total = new SimpleDoubleProperty();
    private DoubleProperty impuestos = new SimpleDoubleProperty();
    private StringProperty address = new SimpleStringProperty();
    private StringProperty autorization = new SimpleStringProperty("");
    //private StringProperty dateText = new SimpleStringProperty();

    private TbCLienteFactura tbCLienteFacturaById;
    private Collection<TbCobro> tbCobrosById;
    private Collection<TbGarantia> tbGarantiasById;
    private Collection<TbLineaFac> tbLineaFacsById;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "\"id\"", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id.getValue();
    }

    public void setId(int id) {
        this.id.setValue(id);
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
        return sub.doubleValue();
    }

    public void setSub(double sub) {
        this.sub.setValue(sub);
    }

    @Basic
    @Column(name = "total", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getTotal() {
        return total.doubleValue();
    }

    public void setTotal(double total) {
        this.total.setValue(total);
    }

    @Basic
    @Column(name = "address", nullable = true, insertable = true, updatable = true, length = 255)
    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.setValue(address);
    }

    @Basic
    @Column(name = "impuestos", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getImpuestos() {
        return impuestos.getValue();
    }

    public void setImpuestos(double impuestos) {
        this.impuestos.setValue(impuestos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbFacturaVenta that = (TbFacturaVenta) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (facDate != null ? !facDate.equals(that.facDate) : that.facDate != null) return false;
        if (sub != null ? !sub.equals(that.sub) : that.sub != null) return false;
        if (total != null ? !total.equals(that.total) : that.total != null) return false;
        if (impuestos != null ? !impuestos.equals(that.impuestos) : that.impuestos != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (autorization != null ? !autorization.equals(that.autorization) : that.autorization != null) return false;
        if (tbCLienteFacturaById != null ? !tbCLienteFacturaById.equals(that.tbCLienteFacturaById) : that.tbCLienteFacturaById != null)
            return false;
        if (tbCobrosById != null ? !tbCobrosById.equals(that.tbCobrosById) : that.tbCobrosById != null) return false;
        if (tbGarantiasById != null ? !tbGarantiasById.equals(that.tbGarantiasById) : that.tbGarantiasById != null)
            return false;
        return !(tbLineaFacsById != null ? !tbLineaFacsById.equals(that.tbLineaFacsById) : that.tbLineaFacsById != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (facDate != null ? facDate.hashCode() : 0);
        result = 31 * result + (sub != null ? sub.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (impuestos != null ? impuestos.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (autorization != null ? autorization.hashCode() : 0);
        result = 31 * result + (tbCLienteFacturaById != null ? tbCLienteFacturaById.hashCode() : 0);
        result = 31 * result + (tbCobrosById != null ? tbCobrosById.hashCode() : 0);
        result = 31 * result + (tbGarantiasById != null ? tbGarantiasById.hashCode() : 0);
        result = 31 * result + (tbLineaFacsById != null ? tbLineaFacsById.hashCode() : 0);
        return result;
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public DoubleProperty subProperty() {
        return sub;
    }

    public DoubleProperty totalProperty() {
        return total;
    }

    public DoubleProperty impuestosProperty() {
        return impuestos;
    }

    public StringProperty addressProperty() {
        return address;
    }


    @Basic
    @Column(name = "autorizacion", nullable = true, insertable = true, updatable = true, length = 32)
    public String getAutorization() {
        return autorization.get();
    }

    public StringProperty autorizationProperty() {
        return autorization;
    }

    public void setAutorization(String autorization) {
        this.autorization.set(autorization);
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
