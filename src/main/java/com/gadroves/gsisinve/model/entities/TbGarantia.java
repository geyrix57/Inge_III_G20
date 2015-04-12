package com.gadroves.gsisinve.model.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_Garantia", schema = "", catalog = "sisgradoves")
public class TbGarantia {
    private int consecutivo;
    private Date fechaCreacion;
    private Date fechaVencimiento;
    private boolean estado;
    private int facturaAsociada;

    private TbFacturaVenta tbFacturaVentaByFacturaAsociada;
    private Collection<TbResolucionGarantia> tbResolucionGarantiasByConsecutivo;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
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
        if (!(o instanceof TbGarantia)) return false;

        TbGarantia that = (TbGarantia) o;

        if (consecutivo != that.consecutivo) return false;
        if (estado != that.estado) return false;
        if (facturaAsociada != that.facturaAsociada) return false;
        if (!fechaCreacion.equals(that.fechaCreacion)) return false;
        return fechaVencimiento.equals(that.fechaVencimiento);

    }

    @Override
    public int hashCode() {
        int result = consecutivo;
        result = 31 * result + fechaCreacion.hashCode();
        result = 31 * result + fechaVencimiento.hashCode();
        result = 31 * result + (estado ? 1 : 0);
        result = 31 * result + facturaAsociada;
        return result;
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "factura_asociada", referencedColumnName = "id", nullable = false, insertable = false, updatable = false), @JoinColumn(name = "factura_asociada", referencedColumnName = "id", nullable = false)})
    public TbFacturaVenta getTbFacturaVentaByFacturaAsociada() {
        return tbFacturaVentaByFacturaAsociada;
    }

    public void setTbFacturaVentaByFacturaAsociada(TbFacturaVenta tbFacturaVentaByFacturaAsociada) {
        this.tbFacturaVentaByFacturaAsociada = tbFacturaVentaByFacturaAsociada;
    }

    @OneToMany(mappedBy = "tbGarantiaByIdGarantia")
    public Collection<TbResolucionGarantia> getTbResolucionGarantiasByConsecutivo() {
        return tbResolucionGarantiasByConsecutivo;
    }

    public void setTbResolucionGarantiasByConsecutivo(Collection<TbResolucionGarantia> tbResolucionGarantiasByConsecutivo) {
        this.tbResolucionGarantiasByConsecutivo = tbResolucionGarantiasByConsecutivo;
    }
}
