package com.gadroves.gsisinve.model.entities;

import javafx.beans.property.*;

import javax.persistence.*;

/**
 * Created by geykel on 17/04/2015.
 */
@Entity
@Table(name = "TB_Linea_Fac", schema = "", catalog = "sisgradoves")
@IdClass(TbLineaFacPK.class)
public class TbLineaFac {
    private IntegerProperty facId = new SimpleIntegerProperty();
    private StringProperty artId = new SimpleStringProperty();
    private IntegerProperty quant = new SimpleIntegerProperty();
    private DoubleProperty disc = new SimpleDoubleProperty();
    private DoubleProperty p_unitario = new SimpleDoubleProperty();
    private StringProperty descripcion = new SimpleStringProperty();
    private DoubleProperty total = new SimpleDoubleProperty();
    /*
    private IntegerProperty art_rel_id = new SimpleIntegerProperty();

    @Basic
    @Column(name = "art_rel_id", nullable = true, insertable = true, updatable = true)
    public int getArt_rel_id() {
        return art_rel_id.get();
    }

    public IntegerProperty art_rel_idProperty() {
        return art_rel_id;
    }

    public void setArt_rel_id(int art_rel_id) {
        this.art_rel_id.set(art_rel_id);
    }
    * */

    public TbLineaFac() {
        this.total.bind(quant.multiply(p_unitario).subtract(quant.multiply(p_unitario).multiply(disc.divide(100))));
    }
    public TbLineaFac(TbArticulo articulo) {
        this.artId.setValue(articulo.getId());
        this.descripcion.setValue(articulo.getDesc());
        this.p_unitario.setValue(articulo.getCost());
        this.quant.setValue(1);
        this.total.bind(quant.multiply(p_unitario).subtract(quant.multiply(p_unitario).multiply(disc.divide(100))));
    }

    private TbFacturaVenta tbFacturaVentaByFacId;
    private TbArticuloProveedor tbArticuloProveedorByArtRelId;
    private TbArticulo tbArticuloByArtId;

    @Id
    @Column(name = "fac_id", nullable = false, insertable = true, updatable = true)
    public int getFacId() {
        return facId.get();
    }

    public void setFacId(int facId) {
        this.facId.setValue(facId);
    }

    @Id
    @Column(name = "art_id", nullable = false, insertable = true, updatable = true, length = 32)
    public String getArtId() {
        return artId.get();
    }

    public void setArtId(String artId) {
        this.artId.setValue(artId);
    }

    @Basic
    @Column(name = "quant", nullable = false, insertable = true, updatable = true)
    public int getQuant() {
        return quant.get();
    }

    public void setQuant(int quant) {
        this.quant.setValue(quant);
    }

    @Basic
    @Column(name = "disc", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getDisc() {
        return disc.doubleValue();
    }

    public void setDisc(double disc) {
        this.disc.set(disc);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TbLineaFac)) return false;

        TbLineaFac that = (TbLineaFac) o;

        if (facId != that.facId) return false;
        if (quant != that.quant) return false;
        if (Double.compare(that.disc.get(), disc.doubleValue()) != 0) return false;
        return artId.equals(that.artId);

    }

    public IntegerProperty facIdProperty() {
        return facId;
    }

    public StringProperty artIdProperty() {
        return artId;
    }

    public IntegerProperty quantProperty() {
        return quant;
    }

    public DoubleProperty discProperty() {
        return disc;
    }
    @Basic
    @Column(name = "p_unitario", nullable = false, insertable = true, updatable = true)
    public double getP_unitario() {
        return p_unitario.get();
    }

    public DoubleProperty p_unitarioProperty() {
        return p_unitario;
    }

    public void setP_unitario(double p_unitario) {
        this.p_unitario.set(p_unitario);
    }
    @Basic
    @Column(name = "descripcion", nullable = false, insertable = true, updatable = true, length = 128)
    public String getDescripcion() {
        return descripcion.get();
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }
    @Basic
    @Column(name = "total", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getTotal() {
        return total.get();
    }

    public DoubleProperty totalProperty() {
        return total;
    }

    public void setTotal(double total) {
        this.total.set(total);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = facId.get();
        result = 31 * result + artId.hashCode();
        result = 31 * result + quant.get();
        temp = Double.doubleToLongBits(disc.getValue());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "fac_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public TbFacturaVenta getTbFacturaVentaByFacId() {
        return tbFacturaVentaByFacId;
    }

    public void setTbFacturaVentaByFacId(TbFacturaVenta tbFacturaVentaByFacId) {
        this.tbFacturaVentaByFacId = tbFacturaVentaByFacId;
    }

    @ManyToOne
    @JoinColumn(name = "art_rel_id", referencedColumnName = "rel_id", insertable = false, updatable = false)
    public TbArticuloProveedor getTbArticuloProveedorByArtRelId() {
        return tbArticuloProveedorByArtRelId;
    }

    public void setTbArticuloProveedorByArtRelId(TbArticuloProveedor tbArticuloProveedorByArtRelId) {
        this.tbArticuloProveedorByArtRelId = tbArticuloProveedorByArtRelId;
    }

    @ManyToOne
    @JoinColumn(name = "art_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public TbArticulo getTbArticuloByArtId() {
        return tbArticuloByArtId;
    }

    public void setTbArticuloByArtId(TbArticulo tbArticuloByArtId) {
        this.tbArticuloByArtId = tbArticuloByArtId;
    }
}
