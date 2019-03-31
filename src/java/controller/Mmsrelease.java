/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pammt
 */
@Entity
@Table(name = "mmsrelease")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mmsrelease.findAll", query = "SELECT m FROM Mmsrelease m")
    , @NamedQuery(name = "Mmsrelease.findById", query = "SELECT m FROM Mmsrelease m WHERE m.id = :id")
    , @NamedQuery(name = "Mmsrelease.findBySprintversion", query = "SELECT m FROM Mmsrelease m WHERE m.sprintversion = :sprintversion")
    , @NamedQuery(name = "Mmsrelease.findByUpdatedat", query = "SELECT m FROM Mmsrelease m WHERE m.updatedat = :updatedat")})
public class Mmsrelease implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "sprintversion")
    private String sprintversion;
    @Basic(optional = false)
    @Column(name = "updatedat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedat;

    public Mmsrelease() {
    }

    public Mmsrelease(Integer id) {
        this.id = id;
    }

    public Mmsrelease(Integer id, Date updatedat) {
        this.id = id;
        this.updatedat = updatedat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSprintversion() {
        return sprintversion;
    }

    public void setSprintversion(String sprintversion) {
        this.sprintversion = sprintversion;
    }

    public Date getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(Date updatedat) {
        this.updatedat = updatedat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mmsrelease)) {
            return false;
        }
        Mmsrelease other = (Mmsrelease) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.Mmsrelease[ id=" + id + " ]";
    }
    
}
