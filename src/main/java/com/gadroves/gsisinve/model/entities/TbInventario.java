package com.gadroves.gsisinve.model.entities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

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
    private IntegerProperty quant = new SimpleIntegerProperty();
    private IntegerProperty minQuant = new SimpleIntegerProperty();
    private IntegerProperty maxQuant = new SimpleIntegerProperty();
    private TbArticulo tbArticuloByCodeArt;
    private TbBodega tbBodegaByCodeBod;

    public StringProperty codeBodegaProperty() {
        return tbBodegaByCodeBod.codeProperty();
    }

    public StringProperty codeArticuloProperty() {
        return tbArticuloByCodeArt.idProperty();
    }

    public StringProperty descArticuloProperty() {
        return tbArticuloByCodeArt.descProperty();
    }

    public IntegerProperty quantProperty() {
        return quant;
    }

    public IntegerProperty minQuantProperty() {
        return minQuant;
    }

    public IntegerProperty maxQuantProperty() {
        return maxQuant;
    }

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
        return quant.get();
    }

    public void setQuant(int quant) {
        this.quant.set(quant);
    }

    @Basic
    @Column(name = "min_quant", nullable = false, insertable = true, updatable = true)
    public int getMinQuant() {
        return minQuant.get();
    }

    public void setMinQuant(int minQuant) {
        this.minQuant.set(minQuant);
    }

    @Basic
    @Column(name = "max_quant", nullable = false, insertable = true, updatable = true)
    public int getMaxQuant() {
        return maxQuant.get();
    }

    public void setMaxQuant(int maxQuant) {
        this.maxQuant.set(maxQuant);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TbInventario)) return false;

        TbInventario that = (TbInventario) o;

        if (!codeArt.equals(that.codeArt)) return false;
        if (!codeBod.equals(that.codeBod)) return false;
        if (!quant.equals(that.quant)) return false;
        if (!minQuant.equals(that.minQuant)) return false;
        return maxQuant.equals(that.maxQuant);

    }

    @Override
    public int hashCode() {
        int result = codeArt.hashCode();
        result = 31 * result + codeBod.hashCode();
        result = 31 * result + quant.hashCode();
        result = 31 * result + minQuant.hashCode();
        result = 31 * result + maxQuant.hashCode();
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
