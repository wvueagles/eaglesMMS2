/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "potholes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Potholes_1.findAll", query = "SELECT p FROM Potholes_1 p")
    , @NamedQuery(name = "Potholes_1.findByWorkid", query = "SELECT p FROM Potholes_1 p WHERE p.workid = :workid")
    , @NamedQuery(name = "Potholes_1.findByStatus", query = "SELECT p FROM Potholes_1 p WHERE p.status = :status")
    , @NamedQuery(name = "Potholes_1.findByLocation", query = "SELECT p FROM Potholes_1 p WHERE p.location = :location")
    , @NamedQuery(name = "Potholes_1.findBySeverity", query = "SELECT p FROM Potholes_1 p WHERE p.severity = :severity")
    , @NamedQuery(name = "Potholes_1.findByReportingpersonkey", query = "SELECT p FROM Potholes_1 p WHERE p.reportingpersonkey = :reportingpersonkey")
    , @NamedQuery(name = "Potholes_1.findByCreatedat", query = "SELECT p FROM Potholes_1 p WHERE p.createdat = :createdat")
    , @NamedQuery(name = "Potholes_1.findByUpdatedat", query = "SELECT p FROM Potholes_1 p WHERE p.updatedat = :updatedat")
    , @NamedQuery(name = "Potholes_1.findByClosedat", query = "SELECT p FROM Potholes_1 p WHERE p.closedat = :closedat")
    , @NamedQuery(name = "Potholes_1.findByComments", query = "SELECT p FROM Potholes_1 p WHERE p.comments = :comments")})
public class Potholes implements Serializable {

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "potholes")
    private Repair repair;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "potholes")
    private Workorder workorder;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "workid")
    private Integer workid;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @Column(name = "location")
    private String location;
    @Basic(optional = false)
    @Column(name = "severity")
    private int severity;
    @Column(name = "reportingpersonkey")
    private String reportingpersonkey;
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

    public Potholes() {
    }

    public Potholes(Integer workid) {
        this.workid = workid;
    }

    public Potholes(Integer workid, String status, String location, int severity, Date createdat, Date updatedat) {
        this.workid = workid;
        this.status = status;
        this.location = location;
        this.severity = severity;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public String getReportingpersonkey() {
        return reportingpersonkey;
    }

    public void setReportingpersonkey(String reportingpersonkey) {
        this.reportingpersonkey = reportingpersonkey;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (workid != null ? workid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Potholes)) {
            return false;
        }
        Potholes other = (Potholes) object;
        if ((this.workid == null && other.workid != null) || (this.workid != null && !this.workid.equals(other.workid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.Potholes_1[ workid=" + workid + " ]";
    }

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }

    public Workorder getWorkorder() {
        return workorder;
    }

    public void setWorkorder(Workorder workorder) {
        this.workorder = workorder;
    }
    
}
