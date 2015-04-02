package com.gadroves.gsisinve.model.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by geykel on 01/04/2015.
 */
public class TbLineaCompraPK implements Serializable {
    private int numero;
    private int facOrigen;

    @Column(name = "numero", nullable = false, insertable = true, updatable = true)
    @Id
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Column(name = "fac_origen", nullable = false, insertable = true, updatable = true)
    @Id
    public int getFacOrigen() {
        return facOrigen;
    }

    public void setFacOrigen(int facOrigen) {
        this.facOrigen = facOrigen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbLineaCompraPK that = (TbLineaCompraPK) o;

        if (numero != that.numero) return false;
        if (facOrigen != that.facOrigen) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numero;
        result = 31 * result + facOrigen;
        return result;
    }
}
