package com.gadroves.gsisinve.model.entities;

import javax.persistence.*;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_Cliente_Cuenta", schema = "", catalog = "sisgradoves")
public class TbClienteCuenta {
    private String id;
    private String nombre;
    private String email;
    private String tel;
    private String direccion;
    private TbCuentaCobrar tbCuentaCobrarById;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 12)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nombre", nullable = true, insertable = true, updatable = true, length = 32)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "email", nullable = true, insertable = true, updatable = true, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "tel", nullable = true, insertable = true, updatable = true, length = 16)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "direccion", nullable = true, insertable = true, updatable = true, length = 64)
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbClienteCuenta that = (TbClienteCuenta) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) return false;
        return !(direccion != null ? !direccion.equals(that.direccion) : that.direccion != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        return result;
    }

    @OneToOne(mappedBy = "tbClienteCuentaByCliente")
    public TbCuentaCobrar getTbCuentaCobrarById() {
        return tbCuentaCobrarById;
    }

    public void setTbCuentaCobrarById(TbCuentaCobrar tbCuentaCobrarById) {
        this.tbCuentaCobrarById = tbCuentaCobrarById;
    }
}
