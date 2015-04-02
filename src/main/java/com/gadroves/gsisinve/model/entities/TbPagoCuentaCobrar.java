package com.gadroves.gsisinve.model.entities;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_Pago_Cuenta_Cobrar", schema = "", catalog = "sisgradoves")
public class TbPagoCuentaCobrar {
    private String cuenta;
    private double monto;
    private Date fecha;
    private TbCuentaCobrar tbCuentaCobrarByCuenta;

    @Id
    @Column(name = "cuenta", nullable = false, insertable = true, updatable = true, length = 12)
    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    @Basic
    @Column(name = "monto", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Basic
    @Column(name = "fecha", nullable = false, insertable = true, updatable = true)
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbPagoCuentaCobrar that = (TbPagoCuentaCobrar) o;

        if (Double.compare(that.monto, monto) != 0) return false;
        if (cuenta != null ? !cuenta.equals(that.cuenta) : that.cuenta != null) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = cuenta != null ? cuenta.hashCode() : 0;
        temp = Double.doubleToLongBits(monto);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "cuenta", referencedColumnName = "cliente", nullable = false, insertable = false, updatable = false)
    public TbCuentaCobrar getTbCuentaCobrarByCuenta() {
        return tbCuentaCobrarByCuenta;
    }

    public void setTbCuentaCobrarByCuenta(TbCuentaCobrar tbCuentaCobrarByCuenta) {
        this.tbCuentaCobrarByCuenta = tbCuentaCobrarByCuenta;
    }
}
