package com.gadroves.gsisinve.model.entities;

import javax.persistence.*;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_Contacto_Proveedores", schema = "", catalog = "sisgradoves")
public class TbContactoProveedores {
    private String idProveedor;
    private String tipo;
    private String valor;
    private TbProveedor tbProveedorByIdProveedor;

    @Id
    @Column(name = "id_proveedor", nullable = false, insertable = true, updatable = true, length = 32)
    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Basic
    @Column(name = "tipo", nullable = true, insertable = true, updatable = true, length = 12)
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Basic
    @Column(name = "valor", nullable = true, insertable = true, updatable = true, length = 64)
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbContactoProveedores that = (TbContactoProveedores) o;

        if (idProveedor != null ? !idProveedor.equals(that.idProveedor) : that.idProveedor != null) return false;
        if (tipo != null ? !tipo.equals(that.tipo) : that.tipo != null) return false;
        return !(valor != null ? !valor.equals(that.valor) : that.valor != null);

    }

    @Override
    public int hashCode() {
        int result = idProveedor != null ? idProveedor.hashCode() : 0;
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        result = 31 * result + (valor != null ? valor.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumns({@JoinColumn(name = "id_proveedor", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false), @JoinColumn(name = "id_proveedor", referencedColumnName = "codigo", nullable = false)})
    public TbProveedor getTbProveedorByIdProveedor() {
        return tbProveedorByIdProveedor;
    }

    public void setTbProveedorByIdProveedor(TbProveedor tbProveedorByIdProveedor) {
        this.tbProveedorByIdProveedor = tbProveedorByIdProveedor;
    }
}
