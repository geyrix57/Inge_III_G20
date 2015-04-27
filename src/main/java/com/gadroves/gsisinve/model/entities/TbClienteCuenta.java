package com.gadroves.gsisinve.model.entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_Cliente_Cuenta", schema = "", catalog = "sisgradoves")
public class TbClienteCuenta {
    private String id;
    private String nombre;
    private TbCuentaCobrar tbCuentaCobrarById;
    private Collection<TbContactoCliente> tbContactoClienteById;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 12)
    public String getId() {
        return id;
    }

    public TbClienteCuenta setId(String id) {
        this.id = id;
        return this;
    }

    @Basic
    @Column(name = "nombre", nullable = true, insertable = true, updatable = true, length = 32)
    public String getNombre() {
        return nombre;
    }

    public TbClienteCuenta setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TbClienteCuenta)) return false;

        TbClienteCuenta that = (TbClienteCuenta) o;

        if (!id.equals(that.id)) return false;
        return nombre.equals(that.nombre);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + nombre.hashCode();
        return result;
    }

    @OneToOne(mappedBy = "tbClienteCuentaByCliente")
    public TbCuentaCobrar getTbCuentaCobrarById() {
        return tbCuentaCobrarById;
    }

    public TbClienteCuenta setTbCuentaCobrarById(TbCuentaCobrar tbCuentaCobrarById) {
        this.tbCuentaCobrarById = tbCuentaCobrarById;
        return this;
    }

    @OneToMany(mappedBy = "tbClienteCuentaByIdCliente")
    public Collection<TbContactoCliente> getTbContactoClienteById() {
        return tbContactoClienteById;
    }

    public TbClienteCuenta setTbContactoClienteById(Collection<TbContactoCliente> tbContactoClienteById) {
        this.tbContactoClienteById = tbContactoClienteById;
        return this;
    }
}
