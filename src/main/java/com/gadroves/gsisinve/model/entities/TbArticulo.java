package com.gadroves.gsisinve.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_Articulo", schema = "", catalog = "sisgradoves")
public class TbArticulo {
    private String id;
    private String desc;
    private BigDecimal cost;
    private BigDecimal util;
    private byte grav;
    private byte estado;
    private TbArticuloProveedor tbArticuloProveedorById;
    private Collection<TbInventario> tbInventariosById;
    private Collection<TbLineaFac> tbLineaFacsById;

    @Id
    @Column(name = "\"id\"", nullable = false, insertable = true, updatable = true, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "\"desc\"", nullable = true, insertable = true, updatable = true, length = 255)
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Basic
    @Column(name = "cost", nullable = false, insertable = true, updatable = true, precision = 3)
    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "util", nullable = false, insertable = true, updatable = true, precision = 3)
    public BigDecimal getUtil() {
        return util;
    }

    public void setUtil(BigDecimal util) {
        this.util = util;
    }

    @Basic
    @Column(name = "grav", nullable = false, insertable = true, updatable = true)
    public byte getGrav() {
        return grav;
    }

    public void setGrav(byte grav) {
        this.grav = grav;
    }

    @Basic
    @Column(name = "estado", nullable = false, insertable = true, updatable = true)
    public byte getEstado() {
        return estado;
    }

    public void setEstado(byte estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbArticulo that = (TbArticulo) o;

        if (grav != that.grav) return false;
        if (estado != that.estado) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (desc != null ? !desc.equals(that.desc) : that.desc != null) return false;
        if (cost != null ? !cost.equals(that.cost) : that.cost != null) return false;
        if (util != null ? !util.equals(that.util) : that.util != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (util != null ? util.hashCode() : 0);
        result = 31 * result + (int) grav;
        result = 31 * result + (int) estado;
        return result;
    }

    @OneToOne(mappedBy = "tbArticuloByArtId")
    public TbArticuloProveedor getTbArticuloProveedorById() {
        return tbArticuloProveedorById;
    }

    public void setTbArticuloProveedorById(TbArticuloProveedor tbArticuloProveedorById) {
        this.tbArticuloProveedorById = tbArticuloProveedorById;
    }

    @OneToMany(mappedBy = "tbArticuloByCodeArt")
    public Collection<TbInventario> getTbInventariosById() {
        return tbInventariosById;
    }

    public void setTbInventariosById(Collection<TbInventario> tbInventariosById) {
        this.tbInventariosById = tbInventariosById;
    }

    @OneToMany(mappedBy = "tbArticuloByArtId")
    public Collection<TbLineaFac> getTbLineaFacsById() {
        return tbLineaFacsById;
    }

    public void setTbLineaFacsById(Collection<TbLineaFac> tbLineaFacsById) {
        this.tbLineaFacsById = tbLineaFacsById;
    }
}
