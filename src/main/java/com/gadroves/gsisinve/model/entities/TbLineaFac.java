package com.gadroves.gsisinve.model.entities;

import javax.persistence.*;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_Linea_Fac", schema = "", catalog = "sisgradoves")
@IdClass(TbLineaFacPK.class)
public class TbLineaFac {
    private int facId;
    private String artId;
    private boolean quant;
    private double disc;
    private TbArticulo tbArticuloByArtId;
    private TbFacturaVenta tbFacturaVentaByFacId;

    @Id
    @Column(name = "fac_id", nullable = false, insertable = true, updatable = true)
    public int getFacId() {
        return facId;
    }

    public void setFacId(int facId) {
        this.facId = facId;
    }

    @Id
    @Column(name = "art_id", nullable = false, insertable = true, updatable = true, length = 32)
    public String getArtId() {
        return artId;
    }

    public void setArtId(String artId) {
        this.artId = artId;
    }

    @Basic
    @Column(name = "quant", nullable = false, insertable = true, updatable = true)
    public boolean getQuant() {
        return quant;
    }

    public void setQuant(boolean quant) {
        this.quant = quant;
    }

    @Basic
    @Column(name = "disc", nullable = false, insertable = true, updatable = true, precision = 0)
    public double getDisc() {
        return disc;
    }

    public void setDisc(double disc) {
        this.disc = disc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TbLineaFac)) return false;

        TbLineaFac that = (TbLineaFac) o;

        if (facId != that.facId) return false;
        if (quant != that.quant) return false;
        if (Double.compare(that.disc, disc) != 0) return false;
        return artId.equals(that.artId);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = facId;
        result = 31 * result + artId.hashCode();
        result = 31 * result + (quant ? 1 : 0);
        temp = Double.doubleToLongBits(disc);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "art_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public TbArticulo getTbArticuloByArtId() {
        return tbArticuloByArtId;
    }

    public void setTbArticuloByArtId(TbArticulo tbArticuloByArtId) {
        this.tbArticuloByArtId = tbArticuloByArtId;
    }

    @ManyToOne
    @JoinColumn(name = "fac_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public TbFacturaVenta getTbFacturaVentaByFacId() {
        return tbFacturaVentaByFacId;
    }

    public void setTbFacturaVentaByFacId(TbFacturaVenta tbFacturaVentaByFacId) {
        this.tbFacturaVentaByFacId = tbFacturaVentaByFacId;
    }
}
