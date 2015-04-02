package com.gadroves.gsisinve.model.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by geykel on 01/04/2015.
 */
public class TbInventarioPK implements Serializable {
    private String codeArt;
    private String codeBod;

    @Column(name = "code_art", nullable = false, insertable = true, updatable = true, length = 32)
    @Id
    public String getCodeArt() {
        return codeArt;
    }

    public void setCodeArt(String codeArt) {
        this.codeArt = codeArt;
    }

    @Column(name = "code_bod", nullable = false, insertable = true, updatable = true, length = 5)
    @Id
    public String getCodeBod() {
        return codeBod;
    }

    public void setCodeBod(String codeBod) {
        this.codeBod = codeBod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbInventarioPK that = (TbInventarioPK) o;

        if (codeArt != null ? !codeArt.equals(that.codeArt) : that.codeArt != null) return false;
        if (codeBod != null ? !codeBod.equals(that.codeBod) : that.codeBod != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codeArt != null ? codeArt.hashCode() : 0;
        result = 31 * result + (codeBod != null ? codeBod.hashCode() : 0);
        return result;
    }
}
