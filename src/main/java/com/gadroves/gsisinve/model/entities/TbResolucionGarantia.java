package com.gadroves.gsisinve.model.entities;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by geykel on 11/04/2015.
 */
@Entity
@Table(name = "TB_Resolucion_Garantia", schema = "", catalog = "sisgradoves")
@IdClass(TbResolucionGarantiaPK.class)
public class TbResolucionGarantia {
    private int idGarantia;
    private String idProducto;
    private String resolucion;
    private Date fecha;

    private TbArticulo tbArticuloByIdProducto;
    private TbGarantia tbGarantiaByIdGarantia;

    @Id
    @Column(name = "id_garantia", nullable = false, insertable = true, updatable = true)
    public int getIdGarantia() {
        return idGarantia;
    }

    public void setIdGarantia(int idGarantia) {
        this.idGarantia = idGarantia;
    }

    @Id
    @Column(name = "id_producto", nullable = false, insertable = true, updatable = true, length = 32)
    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    @Basic
    @Column(name = "resolucion", nullable = false, insertable = true, updatable = true, length = 32)
    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
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

        TbResolucionGarantia that = (TbResolucionGarantia) o;

        if (idGarantia != that.idGarantia) return false;
        if (idProducto != null ? !idProducto.equals(that.idProducto) : that.idProducto != null) return false;
        if (resolucion != null ? !resolucion.equals(that.resolucion) : that.resolucion != null) return false;
        return !(fecha != null ? !fecha.equals(that.fecha) : that.fecha != null);

    }

    @Override
    public int hashCode() {
        int result = idGarantia;
        result = 31 * result + (idProducto != null ? idProducto.hashCode() : 0);
        result = 31 * result + (resolucion != null ? resolucion.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public TbArticulo getTbArticuloByIdProducto() {
        return tbArticuloByIdProducto;
    }

    public void setTbArticuloByIdProducto(TbArticulo tbArticuloByIdProducto) {
        this.tbArticuloByIdProducto = tbArticuloByIdProducto;
    }

    @ManyToOne
    @JoinColumn(name = "id_garantia", referencedColumnName = "consecutivo", nullable = false, insertable = false, updatable = false)
    public TbGarantia getTbGarantiaByIdGarantia() {
        return tbGarantiaByIdGarantia;
    }

    public void setTbGarantiaByIdGarantia(TbGarantia tbGarantiaByIdGarantia) {
        this.tbGarantiaByIdGarantia = tbGarantiaByIdGarantia;
    }
}
