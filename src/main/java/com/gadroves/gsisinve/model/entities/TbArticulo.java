package com.gadroves.gsisinve.model.entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_Articulo", schema = "", catalog = "sisgradoves")
public class TbArticulo {
    private String id;
    private String desc;
    private double cost;
    private double util;
    private boolean grav;
    private boolean estado;
    private Integer categoriaId;

    private TbArticuloProveedor tbArticuloProveedorById;
    private Collection<TbInventario> tbInventariosById;
    private Collection<TbLineaFac> tbLineaFacsById;
    private TbCategoria tbCategoriaByCategoriaId;
    private Collection<TbResolucionGarantia> tbResolucionGarantiasById;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "desc", nullable = true, insertable = true, updatable = true, length = 255)
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Basic
    @Column(name = "cost", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "util", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getUtil() {
        return util;
    }

    public void setUtil(double util) {
        this.util = util;
    }

    @Basic
    @Column(name = "grav", nullable = false, insertable = true, updatable = true)
    public boolean getGrav() {
        return grav;
    }

    public void setGrav(boolean grav) {
        this.grav = grav;
    }

    @Basic
    @Column(name = "estado", nullable = false, insertable = true, updatable = true)
    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Basic
    @Column(name = "categoria_id", nullable = true, insertable = true, updatable = true)
    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TbArticulo)) return false;

        TbArticulo that = (TbArticulo) o;

        if (Double.compare(that.cost, cost) != 0) return false;
        if (Double.compare(that.util, util) != 0) return false;
        if (grav != that.grav) return false;
        if (estado != that.estado) return false;
        if (!id.equals(that.id)) return false;
        return desc.equals(that.desc);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id.hashCode();
        result = 31 * result + desc.hashCode();
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(util);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (grav ? 1 : 0);
        result = 31 * result + (estado ? 1 : 0);
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

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "t_id", insertable = false, updatable = false)
    public TbCategoria getTbCategoriaByCategoriaId() {
        return tbCategoriaByCategoriaId;
    }

    public void setTbCategoriaByCategoriaId(TbCategoria tbCategoriaByCategoriaId) {
        this.tbCategoriaByCategoriaId = tbCategoriaByCategoriaId;
    }

    @OneToMany(mappedBy = "tbArticuloByIdProducto")
    public Collection<TbResolucionGarantia> getTbResolucionGarantiasById() {
        return tbResolucionGarantiasById;
    }

    public void setTbResolucionGarantiasById(Collection<TbResolucionGarantia> tbResolucionGarantiasById) {
        this.tbResolucionGarantiasById = tbResolucionGarantiasById;
    }
}
