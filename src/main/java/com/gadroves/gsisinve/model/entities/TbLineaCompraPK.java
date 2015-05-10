package com.gadroves.gsisinve.model.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by geykel on 01/04/2015.
 */
public class TbLineaCompraPK implements Serializable {
    private int numero;
    private String facOrigen;

    @Column(name = "numero", nullable = false, insertable = true, updatable = true)
    @Id
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Column(name = "fac_origen", nullable = false, insertable = true, updatable = true, length = 32)
    @Id
    public String getFacOrigen() {
        return facOrigen;
    }

    public void setFacOrigen(String facOrigen) {
        this.facOrigen = facOrigen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbLineaCompraPK that = (TbLineaCompraPK) o;

        if (numero != that.numero) return false;
        return !(facOrigen != null ? !facOrigen.equals(that.facOrigen) : that.facOrigen != null);

    }

    @Override
    public int hashCode() {
        int result = numero;
        result = 31 * result + (facOrigen != null ? facOrigen.hashCode() : 0);
        return result;
    }
}
