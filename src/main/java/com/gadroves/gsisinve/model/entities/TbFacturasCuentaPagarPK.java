package com.gadroves.gsisinve.model.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by geykel on 01/04/2015.
 */
public class TbFacturasCuentaPagarPK implements Serializable {
    private String cuenta;
    private int factura;

    @Column(name = "cuenta", nullable = false, insertable = true, updatable = true, length = 32)
    @Id
    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    @Column(name = "factura", nullable = false, insertable = true, updatable = true)
    @Id
    public int getFactura() {
        return factura;
    }

    public void setFactura(int factura) {
        this.factura = factura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbFacturasCuentaPagarPK that = (TbFacturasCuentaPagarPK) o;

        if (factura != that.factura) return false;
        if (cuenta != null ? !cuenta.equals(that.cuenta) : that.cuenta != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cuenta != null ? cuenta.hashCode() : 0;
        result = 31 * result + factura;
        return result;
    }
}
