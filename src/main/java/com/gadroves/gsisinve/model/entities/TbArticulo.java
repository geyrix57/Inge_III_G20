package com.gadroves.gsisinve.model.entities;

import javafx.beans.property.*;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_Articulo", schema = "", catalog = "sisgradoves")
public class TbArticulo {
    private StringProperty id = new SimpleStringProperty();
    private StringProperty desc = new SimpleStringProperty();
    private DoubleProperty cost = new SimpleDoubleProperty();
    private DoubleProperty util = new SimpleDoubleProperty();
    private BooleanProperty grav = new SimpleBooleanProperty();
    private BooleanProperty estado = new SimpleBooleanProperty();
    private Integer categoriaId;

    private TbArticuloProveedor tbArticuloProveedorById;
    private Collection<TbInventario> tbInventariosById;
    private Collection<TbLineaFac> tbLineaFacsById;
    private TbCategoria tbCategoriaByCategoriaId;
    private Collection<TbResolucionGarantia> tbResolucionGarantiasById;

    public StringProperty categoriaProperty() {
        if (tbCategoriaByCategoriaId == null) return null;
        return tbCategoriaByCategoriaId.catNameProperty();
    }

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty descProperty() {
        return desc;
    }

    public DoubleProperty costProperty() {
        return cost;
    }

    public DoubleProperty utilProperty() {
        return util;
    }

    public BooleanProperty gravProperty() {
        return grav;
    }

    public BooleanProperty estadoProperty() {
        return estado;
    }

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 32)
    public String getId() {
        return id.get();
    }

    public TbArticulo setId(String id) {
        this.id.set(id);
        return this;
    }

    @Basic
    @Column(name = "\"desc\"", nullable = true, insertable = true, updatable = true, length = 255)
    public String getDesc() {
        return desc.get();
    }

    public TbArticulo setDesc(String desc) {
        this.desc.set(desc);
        return this;
    }

    @Basic
    @Column(name = "cost", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getCost() {
        return cost.get();
    }

    public TbArticulo setCost(double cost) {
        this.cost.set(cost);
        return this;
    }

    @Basic
    @Column(name = "util", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getUtil() {
        return util.get();
    }

    public TbArticulo setUtil(double util) {
        this.util.set(util);
        return this;
    }

    @Basic
    @Column(name = "grav", nullable = false, insertable = true, updatable = true)
    public boolean getGrav() {
        return grav.get();
    }

    public TbArticulo setGrav(boolean grav) {
        this.grav.set(grav);
        return this;
    }

    @Basic
    @Column(name = "estado", nullable = false, insertable = true, updatable = true)
    public boolean getEstado() {
        return estado.get();
    }

    public TbArticulo setEstado(boolean estado) {
        this.estado.set(estado);
        return this;
    }

    @Basic
    @Column(name = "categoria_id", nullable = true, insertable = true, updatable = true)
    public Integer getCategoriaId() {
        return categoriaId;
    }

    public TbArticulo setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TbArticulo)) return false;

        TbArticulo that = (TbArticulo) o;

        if (!id.equals(that.id)) return false;
        if (!desc.equals(that.desc)) return false;
        if (!cost.equals(that.cost)) return false;
        if (!util.equals(that.util)) return false;
        if (!grav.equals(that.grav)) return false;
        if (!estado.equals(that.estado)) return false;
        return categoriaId.equals(that.categoriaId);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + desc.hashCode();
        result = 31 * result + cost.hashCode();
        result = 31 * result + util.hashCode();
        result = 31 * result + grav.hashCode();
        result = 31 * result + estado.hashCode();
        result = 31 * result + categoriaId.hashCode();
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

    public TbArticulo setTbCategoriaByCategoriaId(TbCategoria tbCategoriaByCategoriaId) {
        this.tbCategoriaByCategoriaId = tbCategoriaByCategoriaId;
        return this;
    }

    @OneToMany(mappedBy = "tbArticuloByIdProducto")
    public Collection<TbResolucionGarantia> getTbResolucionGarantiasById() {
        return tbResolucionGarantiasById;
    }

    public void setTbResolucionGarantiasById(Collection<TbResolucionGarantia> tbResolucionGarantiasById) {
        this.tbResolucionGarantiasById = tbResolucionGarantiasById;
    }
}
