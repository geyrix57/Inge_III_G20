package com.gadroves.gsisinve.model.entities;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_Pago_Cuenta_Pagar", schema = "", catalog = "sisgradoves")
public class TbPagoCuentaPagar {
    private String cuentaPago;
    private double monto;
    private Integer factura;
    private Date fechaPago;
    private int pagoId;

    private CuentaAPagar cuentaAPagarByCuentaPago;
    private TbFacturaCompra tbFacturaCompraByFactura;

    @Basic
    @Column(name = "cuenta_pago", nullable = false, insertable = true, updatable = true, length = 32)
    public String getCuentaPago() {
        return cuentaPago;
    }

    public void setCuentaPago(String cuentaPago) {
        this.cuentaPago = cuentaPago;
    }

    @Basic
    @Column(name = "monto", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Basic
    @Column(name = "factura", nullable = true, insertable = true, updatable = true)
    public Integer getFactura() {
        return factura;
    }

    public void setFactura(Integer factura) {
        this.factura = factura;
    }

    @Basic
    @Column(name = "fecha_pago", nullable = false, insertable = true, updatable = true)
    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "pago_id", nullable = false, insertable = true, updatable = true)
    public int getPagoId() {
        return pagoId;
    }

    public void setPagoId(int pagoId) {
        this.pagoId = pagoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbPagoCuentaPagar that = (TbPagoCuentaPagar) o;

        if (Double.compare(that.monto, monto) != 0) return false;
        if (pagoId != that.pagoId) return false;
        if (cuentaPago != null ? !cuentaPago.equals(that.cuentaPago) : that.cuentaPago != null) return false;
        if (factura != null ? !factura.equals(that.factura) : that.factura != null) return false;
        return !(fechaPago != null ? !fechaPago.equals(that.fechaPago) : that.fechaPago != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = cuentaPago != null ? cuentaPago.hashCode() : 0;
        temp = Double.doubleToLongBits(monto);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (factura != null ? factura.hashCode() : 0);
        result = 31 * result + (fechaPago != null ? fechaPago.hashCode() : 0);
        result = 31 * result + pagoId;
        return result;
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "cuenta_pago", referencedColumnName = "cuenta", nullable = false, insertable = false, updatable = false), @JoinColumn(name = "cuenta_pago", referencedColumnName = "cuenta", nullable = false)})
    public CuentaAPagar getCuentaAPagarByCuentaPago() {
        return cuentaAPagarByCuentaPago;
    }

    public void setCuentaAPagarByCuentaPago(CuentaAPagar cuentaAPagarByCuentaPago) {
        this.cuentaAPagarByCuentaPago = cuentaAPagarByCuentaPago;
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "factura", referencedColumnName = "id", insertable = false, updatable = false), @JoinColumn(name = "factura", referencedColumnName = "id")})
    public TbFacturaCompra getTbFacturaCompraByFactura() {
        return tbFacturaCompraByFactura;
    }

    public void setTbFacturaCompraByFactura(TbFacturaCompra tbFacturaCompraByFactura) {
        this.tbFacturaCompraByFactura = tbFacturaCompraByFactura;
    }
}
