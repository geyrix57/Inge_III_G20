package com.gadroves.gsisinve.model.entities;

import javax.persistence.*;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_Inventario", schema = "", catalog = "sisgradoves")
@IdClass(TbInventarioPK.class)
public class TbInventario {
    private String codeArt;
    private String codeBod;
    private int quant;
    private int minQuant;
    private int maxQuant;
    private TbArticulo tbArticuloByCodeArt;
    private TbBodega tbBodegaByCodeBod;

    @Id
    @Column(name = "code_art", nullable = false, insertable = true, updatable = true, length = 32)
    public String getCodeArt() {
        return codeArt;
    }

    public void setCodeArt(String codeArt) {
        this.codeArt = codeArt;
    }

    @Id
    @Column(name = "code_bod", nullable = false, insertable = true, updatable = true, length = 5)
    public String getCodeBod() {
        return codeBod;
    }

    public void setCodeBod(String codeBod) {
        this.codeBod = codeBod;
    }

    @Basic
    @Column(name = "quant", nullable = false, insertable = true, updatable = true)
    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    @Basic
    @Column(name = "min_quant", nullable = false, insertable = true, updatable = true)
    public int getMinQuant() {
        return minQuant;
    }

    public void setMinQuant(int minQuant) {
        this.minQuant = minQuant;
    }

    @Basic
    @Column(name = "max_quant", nullable = false, insertable = true, updatable = true)
    public int getMaxQuant() {
        return maxQuant;
    }

    public void setMaxQuant(int maxQuant) {
        this.maxQuant = maxQuant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbInventario that = (TbInventario) o;

        if (quant != that.quant) return false;
        if (minQuant != that.minQuant) return false;
        if (maxQuant != that.maxQuant) return false;
        if (codeArt != null ? !codeArt.equals(that.codeArt) : that.codeArt != null) return false;
        if (codeBod != null ? !codeBod.equals(that.codeBod) : that.codeBod != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codeArt != null ? codeArt.hashCode() : 0;
        result = 31 * result + (codeBod != null ? codeBod.hashCode() : 0);
        result = 31 * result + quant;
        result = 31 * result + minQuant;
        result = 31 * result + maxQuant;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "code_art", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public TbArticulo getTbArticuloByCodeArt() {
        return tbArticuloByCodeArt;
    }

    public void setTbArticuloByCodeArt(TbArticulo tbArticuloByCodeArt) {
        this.tbArticuloByCodeArt = tbArticuloByCodeArt;
    }

    @ManyToOne
    @JoinColumn(name = "code_bod", referencedColumnName = "code", nullable = false, insertable = false, updatable = false)
    public TbBodega getTbBodegaByCodeBod() {
        return tbBodegaByCodeBod;
    }

    public void setTbBodegaByCodeBod(TbBodega tbBodegaByCodeBod) {
        this.tbBodegaByCodeBod = tbBodegaByCodeBod;
    }
}
