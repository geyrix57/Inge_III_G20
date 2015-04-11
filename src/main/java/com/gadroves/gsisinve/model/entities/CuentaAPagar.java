package com.gadroves.gsisinve.model.entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "Cuenta_a_pagar", schema = "", catalog = "sisgradoves")
public class CuentaAPagar {
    private String cuenta;
    private double total;
    private double saldo;
    private boolean estado;

    private TbProveedor tbProveedorByCuenta;
    private Collection<TbFacturasCuentaPagar> tbFacturasCuentaPagarsByCuenta;
    private Collection<TbPagoCuentaPagar> tbPagoCuentaPagarsByCuenta;

    @Id
    @Column(name = "cuenta", nullable = false, insertable = true, updatable = true, length = 32)
    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    @Basic
    @Column(name = "total", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CuentaAPagar)) return false;

        CuentaAPagar that = (CuentaAPagar) o;

        if (Double.compare(that.total, total) != 0) return false;
        if (Double.compare(that.saldo, saldo) != 0) return false;
        if (estado != that.estado) return false;
        return cuenta.equals(that.cuenta);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = cuenta.hashCode();
        temp = Double.doubleToLongBits(total);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(saldo);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (estado ? 1 : 0);
        return result;
    }

    @OneToOne
    @JoinColumns({@JoinColumn(name = "cuenta", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false), @JoinColumn(name = "cuenta", referencedColumnName = "codigo", nullable = false)})
    public TbProveedor getTbProveedorByCuenta() {
        return tbProveedorByCuenta;
    }

    public void setTbProveedorByCuenta(TbProveedor tbProveedorByCuenta) {
        this.tbProveedorByCuenta = tbProveedorByCuenta;
    }

    @OneToMany(mappedBy = "cuentaAPagarByCuenta")
    public Collection<TbFacturasCuentaPagar> getTbFacturasCuentaPagarsByCuenta() {
        return tbFacturasCuentaPagarsByCuenta;
    }

    public void setTbFacturasCuentaPagarsByCuenta(Collection<TbFacturasCuentaPagar> tbFacturasCuentaPagarsByCuenta) {
        this.tbFacturasCuentaPagarsByCuenta = tbFacturasCuentaPagarsByCuenta;
    }

    @OneToMany(mappedBy = "cuentaAPagarByCuentaPago")
    public Collection<TbPagoCuentaPagar> getTbPagoCuentaPagarsByCuenta() {
        return tbPagoCuentaPagarsByCuenta;
    }

    public void setTbPagoCuentaPagarsByCuenta(Collection<TbPagoCuentaPagar> tbPagoCuentaPagarsByCuenta) {
        this.tbPagoCuentaPagarsByCuenta = tbPagoCuentaPagarsByCuenta;
    }
}
