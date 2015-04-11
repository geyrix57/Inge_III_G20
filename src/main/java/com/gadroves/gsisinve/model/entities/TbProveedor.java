package com.gadroves.gsisinve.model.entities;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_Proveedor", schema = "", catalog = "sisgradoves")
public class TbProveedor {
    private StringProperty codigo = new SimpleStringProperty();
    private StringProperty nombre = new SimpleStringProperty();
    private StringProperty direccion = new SimpleStringProperty();
    private BooleanProperty estado = new SimpleBooleanProperty();

    private CuentaAPagar cuentaAPagarByCodigo;
    private Collection<TbArticuloProveedor> tbArticuloProveedorsByCodigo;
    private TbContactoProveedores tbContactoProveedoresByCodigo;
    private Collection<TbFacturaCompra> tbFacturaComprasByCodigo;

    public StringProperty codigoProperty() {
        return codigo;
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public BooleanProperty estadoProperty() {
        return estado;
    }

    @Id
    @Column(name = "codigo", nullable = false, insertable = true, updatable = true, length = 32)
    public String getCodigo() {
        return codigo.get();
    }

    public void setCodigo(String codigo) {
        this.codigo.set(codigo);
    }

    @Basic
    @Column(name = "nombre", nullable = false, insertable = true, updatable = true, length = 32)
    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    @Basic
    @Column(name = "direccion", nullable = false, insertable = true, updatable = true, length = 64)
    public String getDireccion() {
        return direccion.get();
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    @Basic
    @Column(name = "estado", nullable = false, insertable = true, updatable = true)
    public boolean getEstado() {
        return estado.get();
    }

    public void setEstado(boolean estado) {
        this.estado.set(estado);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TbProveedor)) return false;

        TbProveedor that = (TbProveedor) o;

        if (!codigo.equals(that.codigo)) return false;
        if (!nombre.equals(that.nombre)) return false;
        if (!direccion.equals(that.direccion)) return false;
        return estado.equals(that.estado);

    }

    @Override
    public String toString() {
        return "TbProveedor{" +
                "codigo=" + codigo.get() +
                ", nombre=" + nombre.get() +
                ", direccion=" + direccion.get() +
                ", estado=" + estado.get() +
                '}';
    }

    @Override
    public int hashCode() {
        int result = codigo.hashCode();
        result = 31 * result + nombre.hashCode();
        result = 31 * result + direccion.hashCode();
        result = 31 * result + estado.hashCode();
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
