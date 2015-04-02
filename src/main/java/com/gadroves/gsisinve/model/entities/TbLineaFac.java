package com.gadroves.gsisinve.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_Linea_Fac", schema = "", catalog = "sisgradoves")
@IdClass(TbLineaFacPK.class)
public class TbLineaFac {
    private int facId;
    private String artId;
    private short quant;
    private BigDecimal disc;
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
    public short getQuant() {
        return quant;
    }

    public void setQuant(short quant) {
        this.quant = quant;
    }

    @Basic
    @Column(name = "disc", nullable = false, insertable = true, updatable = true, precision = 3)
    public BigDecimal getDisc() {
        return disc;
    }

    public void setDisc(BigDecimal disc) {
        this.disc = disc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbLineaFac that = (TbLineaFac) o;

        if (facId != that.facId) return false;
        if (quant != that.quant) return false;
        if (artId != null ? !artId.equals(that.artId) : that.artId != null) return false;
        if (disc != null ? !disc.equals(that.disc) : that.disc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = facId;
        result = 31 * result + (artId != null ? artId.hashCode() : 0);
        result = 31 * result + (int) quant;
        result = 31 * result + (disc != null ? disc.hashCode() : 0);
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
