package com.gadroves.gsisinve.model.entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_Contacto_Proveedores", schema = "", catalog = "sisgradoves")
public class TbContactoProveedores {
    private int idCons;
    private String idProvedor;
    private StringProperty tipo = new SimpleStringProperty();
    private StringProperty valor = new SimpleStringProperty();

    private TbProveedor tbProveedorByIdProvedor;

    public StringProperty tipoProperty() {
        return tipo;
    }

    public StringProperty valorProperty() {
        return valor;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_cons", nullable = false, insertable = true, updatable = true)
    public int getIdCons() {
        return idCons;
    }

    public TbContactoProveedores setIdCons(int idCons) {
        this.idCons = idCons;
        return this;
    }

    @Basic
    @Column(name = "id_provedor", nullable = false, insertable = true, updatable = true, length = 22)
    public String getIdProvedor() {
        return idProvedor;
    }

    public TbContactoProveedores setIdProvedor(String idProvedor) {
        this.idProvedor = idProvedor;
        return this;
    }

    @Basic
    @Column(name = "tipo", nullable = true, insertable = true, updatable = true, length = 12)
    public String getTipo() {
        return tipo.get();
    }

    public TbContactoProveedores setTipo(String tipo) {
        this.tipo.set(tipo);
        return this;
    }

    @Basic
    @Column(name = "valor", nullable = true, insertable = true, updatable = true, length = 64)
    public String getValor() {
        return valor.get();
    }

    public TbContactoProveedores setValor(String valor) {
        this.valor.set(valor);
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "id_provedor", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
    public TbProveedor getTbProveedorByIdProvedor() {
        return tbProveedorByIdProvedor;
    }

    public TbContactoProveedores setTbProveedorByIdProvedor(TbProveedor tbProveedorByIdProvedor) {
        this.tbProveedorByIdProvedor = tbProveedorByIdProvedor;
        return this;
    }

    @Override
    public String toString() {
        return "TbContactoProveedores{" +
                "idCons=" + idCons +
                ", idProvedor='" + idProvedor + '\'' +
                ", tipo=" + tipo.get() +
                ", valor=" + valor.get() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TbContactoProveedores)) return false;

        TbContactoProveedores that = (TbContactoProveedores) o;

        if (idCons != that.idCons) return false;
        if (!idProvedor.equals(that.idProvedor)) return false;
        if (!tipo.equals(that.tipo)) return false;
        return valor.equals(that.valor);

    }

    @Override
    public int hashCode() {
        int result = idCons;
        result = 31 * result + idProvedor.hashCode();
        result = 31 * result + tipo.hashCode();
        result = 31 * result + valor.hashCode();
        return result;
    }
}
