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
        if (!(o instanceof TbFacturaVenta)) return false;

        TbFacturaVenta that = (TbFacturaVenta) o;

        if (id != that.id) return false;
        if (Double.compare(that.sub.get(), sub.get()) != 0) return false;
        if (Double.compare(that.total.get(), total.get()) != 0) return false;
        if (Double.compare(that.impuestos.get(), impuestos.get()) != 0) return false;
        if (!facDate.equals(that.facDate)) return false;
        return address.equals(that.address);
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

    /*public String getDateText() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateText.setValue(dateFormat.format(this.facDate));
        return dateText.get();
    }

    public StringProperty dateTextProperty() {
        return dateText;
    }

    public void setDateText(String dateText) {
        this.dateText.set(dateText);
    }*/

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id.get();
        result = 31 * result + facDate.hashCode();
        temp = Double.doubleToLongBits(sub.getValue());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(total.getValue());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(impuestos.getValue());
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
