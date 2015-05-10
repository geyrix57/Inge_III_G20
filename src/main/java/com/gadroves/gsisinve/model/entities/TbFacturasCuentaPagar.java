package com.gadroves.gsisinve.model.entities;

import javax.persistence.*;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_Facturas_Cuenta_Pagar", schema = "", catalog = "sisgradoves")
@IdClass(TbFacturasCuentaPagarPK.class)
public class TbFacturasCuentaPagar {
    private String cuenta;
    private String factura;
    private double monto;
    private CuentaAPagar cuentaAPagarByCuenta;
    private TbFacturaCompra tbFacturaCompraByFactura;

    @Id
    @Column(name = "cuenta", nullable = false, insertable = true, updatable = true, length = 32)
    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    @Id
    @Column(name = "factura", nullable = false, insertable = true, updatable = true, length = 32)
    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    @Basic
    @Column(name = "monto", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbFacturasCuentaPagar that = (TbFacturasCuentaPagar) o;

        if (Double.compare(that.monto, monto) != 0) return false;
        if (!cuenta.equals(that.cuenta)) return false;
        return factura.equals(that.factura);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = cuenta.hashCode();
        result = 31 * result + factura.hashCode();
        temp = Double.doubleToLongBits(monto);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "cuenta", referencedColumnName = "cuenta", nullable = false, insertable = false, updatable = false)
    public CuentaAPagar getCuentaAPagarByCuenta() {
        return cuentaAPagarByCuenta;
    }

    public void setCuentaAPagarByCuenta(CuentaAPagar cuentaAPagarByCuenta) {
        this.cuentaAPagarByCuenta = cuentaAPagarByCuenta;
    }

    @ManyToOne
    @JoinColumn(name = "factura", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public TbFacturaCompra getTbFacturaCompraByFactura() {
        return tbFacturaCompraByFactura;
    }

    public void setTbFacturaCompraByFactura(TbFacturaCompra tbFacturaCompraByFactura) {
        this.tbFacturaCompraByFactura = tbFacturaCompraByFactura;
    }
}
