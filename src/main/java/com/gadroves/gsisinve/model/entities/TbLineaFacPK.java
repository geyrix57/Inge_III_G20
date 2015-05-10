package com.gadroves.gsisinve.model.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by geykel on 01/04/2015.
 */
public class TbLineaFacPK implements Serializable {
    private int facId;
    private String artId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbLineaFacPK that = (TbLineaFacPK) o;

        if (facId != that.facId) return false;
        if (artId != null ? !artId.equals(that.artId) : that.artId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = facId;
        result = 31 * result + (artId != null ? artId.hashCode() : 0);
        return result;
    }
}
