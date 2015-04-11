package com.gadroves.gsisinve.model.entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by geykel on 11/04/2015.
 */
@Entity
@Table(name = "TB_Categoria", schema = "", catalog = "sisgradoves")
public class TbCategoria {
    private int tId;
    private String catName;
    private String catDesc;

    private Collection<TbArticulo> tbArticulosByTId;

    @Id
    @Column(name = "t_id", nullable = false, insertable = true, updatable = true)
    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    @Basic
    @Column(name = "cat_name", nullable = false, insertable = true, updatable = true, length = 50)
    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    @Basic
    @Column(name = "cat_desc", nullable = false, insertable = true, updatable = true, length = 128)
    public String getCatDesc() {
        return catDesc;
    }

    public void setCatDesc(String catDesc) {
        this.catDesc = catDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbCategoria that = (TbCategoria) o;

        if (tId != that.tId) return false;
        if (catName != null ? !catName.equals(that.catName) : that.catName != null) return false;
        return !(catDesc != null ? !catDesc.equals(that.catDesc) : that.catDesc != null);

    }

    @Override
    public int hashCode() {
        int result = tId;
        result = 31 * result + (catName != null ? catName.hashCode() : 0);
        result = 31 * result + (catDesc != null ? catDesc.hashCode() : 0);
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
