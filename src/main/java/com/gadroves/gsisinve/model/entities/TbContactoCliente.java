package com.gadroves.gsisinve.model.entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;

/**
 * Created by geykel on 26/04/2015.
 */
@Entity
@Table(name = "TB_Contacto_Cliente", schema = "", catalog = "sisgradoves")
public class TbContactoCliente {
    private StringProperty tipo = new SimpleStringProperty();
    private StringProperty valor = new SimpleStringProperty();
    private int idCons;
    private TbClienteCuenta tbClienteCuentaByIdCliente;

    public StringProperty tipoProperty() {
        return tipo;
    }

    public StringProperty valorProperty() {
        return valor;
    }

    @Basic
    @Column(name = "tipo", nullable = false, insertable = true, updatable = true, length = 12)
    public String getTipo() {
        return tipo.get();
    }

    public TbContactoCliente setTipo(String tipo) {
        this.tipo.set(tipo);
        return this;
    }

    @Basic
    @Column(name = "valor", nullable = false, insertable = true, updatable = true, length = 32)
    public String getValor() {
        return valor.get();
    }

    public TbContactoCliente setValor(String valor) {
        this.valor.set(valor);
        return this;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_cons", nullable = false, insertable = true, updatable = true)
    public int getIdCons() {
        return idCons;
    }

    public void setIdCons(int idCons) {
        this.idCons = idCons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbContactoCliente that = (TbContactoCliente) o;

        if (idCons != that.idCons) return false;
        if (tipo != null ? !tipo.equals(that.tipo) : that.tipo != null) return false;
        return !(valor != null ? !valor.equals(that.valor) : that.valor != null);

    }

    @Override
    public int hashCode() {
        int result = tipo != null ? tipo.hashCode() : 0;
        result = 31 * result + (valor != null ? valor.hashCode() : 0);
        result = 31 * result + idCons;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id", nullable = false, insertable = true, updatable = true)
    public TbClienteCuenta getTbClienteCuentaByIdCliente() {
        return tbClienteCuentaByIdCliente;
    }

    public TbContactoCliente setTbClienteCuentaByIdCliente(TbClienteCuenta tbClienteCuentaByIdCliente) {
        this.tbClienteCuentaByIdCliente = tbClienteCuentaByIdCliente;
        return this;
    }
}
