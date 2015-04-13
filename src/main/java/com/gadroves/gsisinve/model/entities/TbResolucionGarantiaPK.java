package com.gadroves.gsisinve.model.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by geykel on 11/04/2015.
 */
public class TbResolucionGarantiaPK implements Serializable {
    private int idGarantia;
    private String idProducto;

    @Column(name = "id_garantia", nullable = false, insertable = true, updatable = true)
    @Id
    public int getIdGarantia() {
        return idGarantia;
    }

    public void setIdGarantia(int idGarantia) {
        this.idGarantia = idGarantia;
    }

    @Column(name = "id_producto", nullable = false, insertable = true, updatable = true, length = 32)
    @Id
    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbResolucionGarantiaPK that = (TbResolucionGarantiaPK) o;

        if (idGarantia != that.idGarantia) return false;
        return !(idProducto != null ? !idProducto.equals(that.idProducto) : that.idProducto != null);

    }

    @Override
    public int hashCode() {
        int result = idGarantia;
        result = 31 * result + (idProducto != null ? idProducto.hashCode() : 0);
        return result;
    }
}
