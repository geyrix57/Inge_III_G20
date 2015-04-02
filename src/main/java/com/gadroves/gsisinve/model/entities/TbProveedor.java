package com.gadroves.gsisinve.model.entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_Proveedor", schema = "", catalog = "sisgradoves")
public class TbProveedor {
    private String codigo;
    private String nombre;
    private String direccion;
    private boolean estado;
    private CuentaAPagar cuentaAPagarByCodigo;
    private Collection<TbArticuloProveedor> tbArticuloProveedorsByCodigo;
    private TbContactoProveedores tbContactoProveedoresByCodigo;
    private Collection<TbFacturaCompra> tbFacturaComprasByCodigo;

    @Id
    @Column(name = "codigo", nullable = false, insertable = true, updatable = true, length = 32)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Basic
    @Column(name = "nombre", nullable = false, insertable = true, updatable = true, length = 32)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "direccion", nullable = false, insertable = true, updatable = true, length = 64)
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
        if (!(o instanceof TbProveedor)) return false;

        TbProveedor that = (TbProveedor) o;

        if (estado != that.estado) return false;
        if (!codigo.equals(that.codigo)) return false;
        if (!nombre.equals(that.nombre)) return false;
        return direccion.equals(that.direccion);

    }

    @Override
    public int hashCode() {
        int result = codigo.hashCode();
        result = 31 * result + nombre.hashCode();
        result = 31 * result + direccion.hashCode();
        result = 31 * result + (estado ? 1 : 0);
        return result;
    }

    @OneToOne(mappedBy = "tbProveedorByCuenta")
    public CuentaAPagar getCuentaAPagarByCodigo() {
        return cuentaAPagarByCodigo;
    }

    public void setCuentaAPagarByCodigo(CuentaAPagar cuentaAPagarByCodigo) {
        this.cuentaAPagarByCodigo = cuentaAPagarByCodigo;
    }

    @OneToMany(mappedBy = "tbProveedorByProvId")
    public Collection<TbArticuloProveedor> getTbArticuloProveedorsByCodigo() {
        return tbArticuloProveedorsByCodigo;
    }

    public void setTbArticuloProveedorsByCodigo(Collection<TbArticuloProveedor> tbArticuloProveedorsByCodigo) {
        this.tbArticuloProveedorsByCodigo = tbArticuloProveedorsByCodigo;
    }

    @OneToOne(mappedBy = "tbProveedorByIdProveedor")
    public TbContactoProveedores getTbContactoProveedoresByCodigo() {
        return tbContactoProveedoresByCodigo;
    }

    public void setTbContactoProveedoresByCodigo(TbContactoProveedores tbContactoProveedoresByCodigo) {
        this.tbContactoProveedoresByCodigo = tbContactoProveedoresByCodigo;
    }

    @OneToMany(mappedBy = "tbProveedorByProvId")
    public Collection<TbFacturaCompra> getTbFacturaComprasByCodigo() {
        return tbFacturaComprasByCodigo;
    }

    public void setTbFacturaComprasByCodigo(Collection<TbFacturaCompra> tbFacturaComprasByCodigo) {
        this.tbFacturaComprasByCodigo = tbFacturaComprasByCodigo;
    }
}
