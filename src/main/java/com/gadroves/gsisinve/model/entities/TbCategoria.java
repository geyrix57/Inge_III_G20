package com.gadroves.gsisinve.model.entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by geykel on 11/04/2015.
 */
@Entity
@Table(name = "TB_Categoria", schema = "", catalog = "sisgradoves")
public class TbCategoria {
    private int tId;
    private StringProperty catName = new SimpleStringProperty();
    private StringProperty catDesc = new SimpleStringProperty();

    private Collection<TbArticulo> tbArticulosByTId;

    public StringProperty catNameProperty() {
        return catName;
    }

    public StringProperty catDescProperty() {
        return catDesc;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "t_id", nullable = false, insertable = true, updatable = true)
    public int gettId() {
        return tId;
    }

    public TbCategoria settId(int tId) {
        this.tId = tId;
        return this;
    }

    @Basic
    @Column(name = "cat_name", nullable = false, insertable = true, updatable = true, length = 50)
    public String getCatName() {
        return catName.get();
    }

    public TbCategoria setCatName(String catName) {
        this.catName.set(catName);
        return this;
    }

    @Basic
    @Column(name = "cat_desc", nullable = false, insertable = true, updatable = true, length = 128)
    public String getCatDesc() {
        return catDesc.get();
    }

    public TbCategoria setCatDesc(String catDesc) {
        this.catDesc.set(catDesc);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TbCategoria)) return false;

        TbCategoria that = (TbCategoria) o;

        if (tId != that.tId) return false;
        if (!catName.equals(that.catName)) return false;
        return catDesc.equals(that.catDesc);

    }

    @Override
    public int hashCode() {
        int result = tId;
        result = 31 * result + catName.get().hashCode();
        result = 31 * result + catDesc.get().hashCode();
        return result;
    }

    @OneToMany(mappedBy = "tbCategoriaByCategoriaId")
    public Collection<TbArticulo> getTbArticulosByTId() {
        return tbArticulosByTId;
    }

    public void setTbArticulosByTId(Collection<TbArticulo> tbArticulosByTId) {
        this.tbArticulosByTId = tbArticulosByTId;
    }

}
