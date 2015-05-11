package com.gadroves.gsisinve.model.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by geykel on 01/04/2015.
 */
public class TbFacturasCuentaPagarPK implements Serializable {
    private String cuenta;
    private String factura;

    @Column(name = "cuenta", nullable = false, insertable = true, updatable = true, length = 32)
    @Id
    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    @Column(name = "factura", nullable = false, insertable = true, updatable = true, length = 32)
    @Id
    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbFacturasCuentaPagarPK that = (TbFacturasCuentaPagarPK) o;

        if (cuenta != null ? !cuenta.equals(that.cuenta) : that.cuenta != null) return false;
        return !(factura != null ? !factura.equals(that.factura) : that.factura != null);

    }

    @Override
    public int hashCode() {
        int result = cuenta != null ? cuenta.hashCode() : 0;
        result = 31 * result + (factura != null ? factura.hashCode() : 0);
        return result;
    }
}
