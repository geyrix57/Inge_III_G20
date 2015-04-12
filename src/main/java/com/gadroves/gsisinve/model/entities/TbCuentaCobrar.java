package com.gadroves.gsisinve.model.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_Cuenta_Cobrar", schema = "", catalog = "sisgradoves")
public class TbCuentaCobrar {
    private String cliente;
    private double saldo;
    private boolean estado;
    private Date fechaApertura;
    private Date fechaSiguiente;
    private Collection<TbCobro> tbCobrosByCliente;
    private TbClienteCuenta tbClienteCuentaByCliente;
    private TbPagoCuentaCobrar tbPagoCuentaCobrarByCliente;

    @Id
    @Column(name = "cliente", nullable = false, insertable = true, updatable = true, length = 32)
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    @Basic
    @Column(name = "saldo", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
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
    @Column(name = "fecha_apertura", nullable = false, insertable = true, updatable = true)
    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    @Basic
    @Column(name = "fecha_siguiente", nullable = true, insertable = true, updatable = true)
    public Date getFechaSiguiente() {
        return fechaSiguiente;
    }

    public void setFechaSiguiente(Date fechaSiguiente) {
        this.fechaSiguiente = fechaSiguiente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TbCuentaCobrar)) return false;

        TbCuentaCobrar that = (TbCuentaCobrar) o;

        if (Double.compare(that.saldo, saldo) != 0) return false;
        if (estado != that.estado) return false;
        if (!cliente.equals(that.cliente)) return false;
        if (!fechaApertura.equals(that.fechaApertura)) return false;
        return fechaSiguiente.equals(that.fechaSiguiente);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = cliente.hashCode();
        temp = Double.doubleToLongBits(saldo);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (estado ? 1 : 0);
        result = 31 * result + fechaApertura.hashCode();
        result = 31 * result + fechaSiguiente.hashCode();
        return result;
    }

    @OneToMany(mappedBy = "tbCuentaCobrarByCuentaCliente")
    public Collection<TbCobro> getTbCobrosByCliente() {
        return tbCobrosByCliente;
    }

    public void setTbCobrosByCliente(Collection<TbCobro> tbCobrosByCliente) {
        this.tbCobrosByCliente = tbCobrosByCliente;
    }

    @OneToOne
    @JoinColumns({@JoinColumn(name = "cliente", referencedColumnName = "id", nullable = false, insertable = false, updatable = false), @JoinColumn(name = "cliente", referencedColumnName = "id", nullable = false)})
    public TbClienteCuenta getTbClienteCuentaByCliente() {
        return tbClienteCuentaByCliente;
    }

    public void setTbClienteCuentaByCliente(TbClienteCuenta tbClienteCuentaByCliente) {
        this.tbClienteCuentaByCliente = tbClienteCuentaByCliente;
    }

    @OneToOne(mappedBy = "tbCuentaCobrarByCuenta")
    public TbPagoCuentaCobrar getTbPagoCuentaCobrarByCliente() {
        return tbPagoCuentaCobrarByCliente;
    }

    public void setTbPagoCuentaCobrarByCliente(TbPagoCuentaCobrar tbPagoCuentaCobrarByCliente) {
        this.tbPagoCuentaCobrarByCliente = tbPagoCuentaCobrarByCliente;
    }
}
