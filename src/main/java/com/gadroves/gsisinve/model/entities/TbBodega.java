package com.gadroves.gsisinve.model.entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_Bodega", schema = "", catalog = "sisgradoves")
public class TbBodega {
    private String code;
    private String desc;
    private Collection<TbInventario> tbInventariosByCode;

    @Id
    @Column(name = "code", nullable = false, insertable = true, updatable = true, length = 5)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "desc", nullable = false, insertable = true, updatable = true, length = 56)
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbBodega tbBodega = (TbBodega) o;

        if (code != null ? !code.equals(tbBodega.code) : tbBodega.code != null) return false;
        return !(desc != null ? !desc.equals(tbBodega.desc) : tbBodega.desc != null);

    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "tbBodegaByCodeBod")
    public Collection<TbInventario> getTbInventariosByCode() {
        return tbInventariosByCode;
    }

    public void setTbInventariosByCode(Collection<TbInventario> tbInventariosByCode) {
        this.tbInventariosByCode = tbInventariosByCode;
    }
}
