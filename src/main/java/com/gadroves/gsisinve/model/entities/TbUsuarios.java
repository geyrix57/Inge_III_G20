package com.gadroves.gsisinve.model.entities;

import javax.persistence.*;
import java.util.Arrays;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_Usuarios", schema = "", catalog = "sisgradoves")
public class TbUsuarios {
    private String id;
    private byte[] pass;
    private int nivel;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 16)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pass", nullable = false, insertable = true, updatable = true)
    public byte[] getPass() {
        return pass;
    }

    public void setPass(byte[] pass) {
        this.pass = pass;
    }

    @Basic
    @Column(name = "nivel", nullable = false, insertable = true, updatable = true)
    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbUsuarios that = (TbUsuarios) o;

        if (nivel != that.nivel) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return Arrays.equals(pass, that.pass);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (pass != null ? Arrays.hashCode(pass) : 0);
        result = 31 * result + nivel;
        return result;
    }
}
