package com.gadroves.gsisinve.model.entities;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_Cobro", schema = "", catalog = "sisgradoves")
public class TbCobro {
    private int numRecibo;
    private String cuentaCliente;
    private Date fecha;
    private int facturaAsociada;
    private double monto;
    private TbCuentaCobrar tbCuentaCobrarByCuentaCliente;
    private TbFacturaVenta tbFacturaVentaByFacturaAsociada;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "num_recibo", nullable = false, insertable = true, updatable = true)
    public int getNumRecibo() {
        return numRecibo;
    }

    public void setNumRecibo(int numRecibo) {
        this.numRecibo = numRecibo;
    }

    @Basic
    @Column(name = "cuenta_cliente", nullable = false, insertable = true, updatable = true, length = 12)
    public String getCuentaCliente() {
        return cuentaCliente;
    }

    public void setCuentaCliente(String cuentaCliente) {
        this.cuentaCliente = cuentaCliente;
    }

    @Basic
    @Column(name = "fecha", nullable = false, insertable = true, updatable = true)
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Basic
    @Column(name = "factura_asociada", nullable = false, insertable = true, updatable = true)
    public int getFacturaAsociada() {
        return facturaAsociada;
    }

    public void setFacturaAsociada(int facturaAsociada) {
        this.facturaAsociada = facturaAsociada;
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
        if (!(o instanceof TbCobro)) return false;

        TbCobro tbCobro = (TbCobro) o;

        if (numRecibo != tbCobro.numRecibo) return false;
        if (facturaAsociada != tbCobro.facturaAsociada) return false;
        if (Double.compare(tbCobro.monto, monto) != 0) return false;
        if (!cuentaCliente.equals(tbCobro.cuentaCliente)) return false;
        return fecha.equals(tbCobro.fecha);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = numRecibo;
        result = 31 * result + cuentaCliente.hashCode();
        result = 31 * result + fecha.hashCode();
        result = 31 * result + facturaAsociada;
        temp = Double.doubleToLongBits(monto);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "cuenta_cliente", referencedColumnName = "cliente", nullable = false, insertable = false, updatable = false), @JoinColumn(name = "cuenta_cliente", referencedColumnName = "cliente", nullable = false)})
    public TbCuentaCobrar getTbCuentaCobrarByCuentaCliente() {
        return tbCuentaCobrarByCuentaCliente;
    }

    public void setTbCuentaCobrarByCuentaCliente(TbCuentaCobrar tbCuentaCobrarByCuentaCliente) {
        this.tbCuentaCobrarByCuentaCliente = tbCuentaCobrarByCuentaCliente;
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
