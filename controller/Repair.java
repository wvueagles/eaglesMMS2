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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pammt
 */
@Entity
@Table(name = "repair")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Repair.findAll", query = "SELECT r FROM Repair r")
    , @NamedQuery(name = "Repair.findByWorkid", query = "SELECT r FROM Repair r WHERE r.workid = :workid")
    , @NamedQuery(name = "Repair.findByStatus", query = "SELECT r FROM Repair r WHERE r.status = :status")
    , @NamedQuery(name = "Repair.findByType", query = "SELECT r FROM Repair r WHERE r.type = :type")
    , @NamedQuery(name = "Repair.findByPoc", query = "SELECT r FROM Repair r WHERE r.poc = :poc")
    , @NamedQuery(name = "Repair.findByCreatedat", query = "SELECT r FROM Repair r WHERE r.createdat = :createdat")
    , @NamedQuery(name = "Repair.findByUpdatedat", query = "SELECT r FROM Repair r WHERE r.updatedat = :updatedat")
    , @NamedQuery(name = "Repair.findByClosedat", query = "SELECT r FROM Repair r WHERE r.closedat = :closedat")
    , @NamedQuery(name = "Repair.findByComments", query = "SELECT r FROM Repair r WHERE r.comments = :comments")})
public class Repair implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "workid")
    private Integer workid;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @Column(name = "poc")
    private String poc;
    @Basic(optional = false)
    @Column(name = "createdat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdat;
    @Basic(optional = false)
    @Column(name = "updatedat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedat;
    @Column(name = "closedat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closedat;
    @Column(name = "comments")
    private String comments;
    @JoinColumn(name = "workid", referencedColumnName = "workid", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Potholes potholes;

    public Repair() {
    }

    public Repair(Integer workid) {
        this.workid = workid;
    }

    public Repair(Integer workid, String status, String type, Date createdat, Date updatedat) {
        this.workid = workid;
        this.status = status;
        this.type = type;
        this.createdat = createdat;
        this.updatedat = updatedat;
    }

    public Integer getWorkid() {
        return workid;
    }

    public void setWorkid(Integer workid) {
        this.workid = workid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoc() {
        return poc;
    }

    public void setPoc(String poc) {
        this.poc = poc;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public Date getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(Date updatedat) {
        this.updatedat = updatedat;
    }

    public Date getClosedat() {
        return closedat;
    }

    public void setClosedat(Date closedat) {
        this.closedat = closedat;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Potholes getPotholes() {
        return potholes;
    }

    public void setPotholes(Potholes potholes) {
        this.potholes = potholes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (workid != null ? workid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Repair)) {
            return false;
        }
        Repair other = (Repair) object;
        if ((this.workid == null && other.workid != null) || (this.workid != null && !this.workid.equals(other.workid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.Repair[ workid=" + workid + " ]";
    }
    
}
