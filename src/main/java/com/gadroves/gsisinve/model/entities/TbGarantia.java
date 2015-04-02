package com.gadroves.gsisinve.model.entities;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_Garantia", schema = "", catalog = "sisgradoves")
public class TbGarantia {
    private int consecutivo;
    private Date fechaCreacion;
    private Date fechaVencimiento;
    private short estado;
    private int facturaAsociada;
    private TbFacturaVenta tbFacturaVentaByFacturaAsociada;

    @Id
    @Column(name = "consecutivo", nullable = false, insertable = true, updatable = true)
    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    @Basic
    @Column(name = "fecha_creacion", nullable = false, insertable = true, updatable = true)
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Basic
    @Column(name = "fecha_vencimiento", nullable = false, insertable = true, updatable = true)
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Basic
    @Column(name = "estado", nullable = false, insertable = true, updatable = true)
    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    @Basic
    @Column(name = "factura_asociada", nullable = false, insertable = true, updatable = true)
    public int getFacturaAsociada() {
        return facturaAsociada;
    }

    public void setFacturaAsociada(int facturaAsociada) {
        this.facturaAsociada = facturaAsociada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbGarantia that = (TbGarantia) o;

        if (consecutivo != that.consecutivo) return false;
        if (estado != that.estado) return false;
        if (facturaAsociada != that.facturaAsociada) return false;
        if (fechaCreacion != null ? !fechaCreacion.equals(that.fechaCreacion) : that.fechaCreacion != null)
            return false;
        if (fechaVencimiento != null ? !fechaVencimiento.equals(that.fechaVencimiento) : that.fechaVencimiento != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = consecutivo;
        result = 31 * result + (fechaCreacion != null ? fechaCreacion.hashCode() : 0);
        result = 31 * result + (fechaVencimiento != null ? fechaVencimiento.hashCode() : 0);
        result = 31 * result + (int) estado;
        result = 31 * result + facturaAsociada;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "factura_asociada", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public TbFacturaVenta getTbFacturaVentaByFacturaAsociada() {
        return tbFacturaVentaByFacturaAsociada;
    }

    public void setTbFacturaVentaByFacturaAsociada(TbFacturaVenta tbFacturaVentaByFacturaAsociada) {
        this.tbFacturaVentaByFacturaAsociada = tbFacturaVentaByFacturaAsociada;
    }
}
