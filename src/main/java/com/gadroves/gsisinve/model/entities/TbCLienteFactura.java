package com.gadroves.gsisinve.model.entities;

import javax.persistence.*;

/**
 * Created by geykel on 01/04/2015.
 */
@Entity
@Table(name = "TB_CLiente_Factura", schema = "", catalog = "sisgradoves")
public class TbCLienteFactura {
    private String name;
    private String address;
    private String id;
    private int facId;
    private String contact;
    private TbFacturaVenta tbFacturaVentaByFacId;

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "address", nullable = true, insertable = true, updatable = true, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "id", nullable = true, insertable = true, updatable = true, length = 10)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    @Column(name = "fac_id", nullable = false, insertable = true, updatable = true)
    public int getFacId() {
        return facId;
    }

    public void setFacId(int facId) {
        this.facId = facId;
    }

    @Basic
    @Column(name = "contact", nullable = true, insertable = true, updatable = true, length = 64)
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbCLienteFactura that = (TbCLienteFactura) o;

        if (facId != that.facId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return !(contact != null ? !contact.equals(that.contact) : that.contact != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + facId;
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "fac_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public TbFacturaVenta getTbFacturaVentaByFacId() {
        return tbFacturaVentaByFacId;
    }

    public void setTbFacturaVentaByFacId(TbFacturaVenta tbFacturaVentaByFacId) {
        this.tbFacturaVentaByFacId = tbFacturaVentaByFacId;
    }
}
