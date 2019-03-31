/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "potholeallreport")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Potholeallreport.findAll", query = "SELECT p FROM Potholeallreport p")
    , @NamedQuery(name = "Potholeallreport.findByWorkid", query = "SELECT p FROM Potholeallreport p WHERE p.workid = :workid")
    , @NamedQuery(name = "Potholeallreport.findByPotholelocation", query = "SELECT p FROM Potholeallreport p WHERE p.potholelocation = :potholelocation")
    , @NamedQuery(name = "Potholeallreport.findByPotholeseverity", query = "SELECT p FROM Potholeallreport p WHERE p.potholeseverity = :potholeseverity")
    , @NamedQuery(name = "Potholeallreport.findByCreateddate", query = "SELECT p FROM Potholeallreport p WHERE p.createddate = :createddate")
    , @NamedQuery(name = "Potholeallreport.findByPotholestatus", query = "SELECT p FROM Potholeallreport p WHERE p.potholestatus = :potholestatus")
    , @NamedQuery(name = "Potholeallreport.findByRepairstatus", query = "SELECT p FROM Potholeallreport p WHERE p.repairstatus = :repairstatus")
    , @NamedQuery(name = "Potholeallreport.findByWorkorderstatus", query = "SELECT p FROM Potholeallreport p WHERE p.workorderstatus = :workorderstatus")
    , @NamedQuery(name = "Potholeallreport.findByRepairordertype", query = "SELECT p FROM Potholeallreport p WHERE p.repairordertype = :repairordertype")
    , @NamedQuery(name = "Potholeallreport.findByWorkordertype", query = "SELECT p FROM Potholeallreport p WHERE p.workordertype = :workordertype")
    , @NamedQuery(name = "Potholeallreport.findByReportingpersonkey", query = "SELECT p FROM Potholeallreport p WHERE p.reportingpersonkey = :reportingpersonkey")
    , @NamedQuery(name = "Potholeallreport.findByRepairpoc", query = "SELECT p FROM Potholeallreport p WHERE p.repairpoc = :repairpoc")
    , @NamedQuery(name = "Potholeallreport.findByWorkorderpoc", query = "SELECT p FROM Potholeallreport p WHERE p.workorderpoc = :workorderpoc")
    , @NamedQuery(name = "Potholeallreport.findByPotholeupdatedtime", query = "SELECT p FROM Potholeallreport p WHERE p.potholeupdatedtime = :potholeupdatedtime")
    , @NamedQuery(name = "Potholeallreport.findByRepairupdatedtime", query = "SELECT p FROM Potholeallreport p WHERE p.repairupdatedtime = :repairupdatedtime")
    , @NamedQuery(name = "Potholeallreport.findByWorkorderupdatedtime", query = "SELECT p FROM Potholeallreport p WHERE p.workorderupdatedtime = :workorderupdatedtime")
    , @NamedQuery(name = "Potholeallreport.findByPotholeclosedtime", query = "SELECT p FROM Potholeallreport p WHERE p.potholeclosedtime = :potholeclosedtime")
    , @NamedQuery(name = "Potholeallreport.findByRepairclosedtime", query = "SELECT p FROM Potholeallreport p WHERE p.repairclosedtime = :repairclosedtime")
    , @NamedQuery(name = "Potholeallreport.findByWorkorderclosedtime", query = "SELECT p FROM Potholeallreport p WHERE p.workorderclosedtime = :workorderclosedtime")
    , @NamedQuery(name = "Potholeallreport.findByPotholecomments", query = "SELECT p FROM Potholeallreport p WHERE p.potholecomments = :potholecomments")
    , @NamedQuery(name = "Potholeallreport.findByRepaircomments", query = "SELECT p FROM Potholeallreport p WHERE p.repaircomments = :repaircomments")
    , @NamedQuery(name = "Potholeallreport.findByWorkordercomments", query = "SELECT p FROM Potholeallreport p WHERE p.workordercomments = :workordercomments")})
public class Potholeallreport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "workid")
    private Integer workid;
    @Column(name = "potholelocation")
    private String potholelocation;
    @Column(name = "potholeseverity")
    private Integer potholeseverity;
    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Column(name = "potholestatus")
    private String potholestatus;
    @Column(name = "repairstatus")
    private String repairstatus;
    @Column(name = "workorderstatus")
    private String workorderstatus;
    @Column(name = "repairordertype")
    private String repairordertype;
    @Column(name = "workordertype")
    private String workordertype;
    @Column(name = "reportingpersonkey")
    private String reportingpersonkey;
    @Column(name = "repairpoc")
    private String repairpoc;
    @Column(name = "workorderpoc")
    private String workorderpoc;
    @Column(name = "potholeupdatedtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date potholeupdatedtime;
    @Column(name = "repairupdatedtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date repairupdatedtime;
    @Column(name = "workorderupdatedtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date workorderupdatedtime;
    @Column(name = "potholeclosedtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date potholeclosedtime;
    @Column(name = "repairclosedtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date repairclosedtime;
    @Column(name = "workorderclosedtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date workorderclosedtime;
    @Column(name = "potholecomments")
    private String potholecomments;
    @Column(name = "repaircomments")
    private String repaircomments;
    @Column(name = "workordercomments")
    private String workordercomments;

    public Potholeallreport() {
    }

    public Integer getWorkid() {
        return workid;
    }

    public void setWorkid(Integer workid) {
        this.workid = workid;
    }

    public String getPotholelocation() {
        return potholelocation;
    }

    public void setPotholelocation(String potholelocation) {
        this.potholelocation = potholelocation;
    }

    public Integer getPotholeseverity() {
        return potholeseverity;
    }

    public void setPotholeseverity(Integer potholeseverity) {
        this.potholeseverity = potholeseverity;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public String getPotholestatus() {
        return potholestatus;
    }

    public void setPotholestatus(String potholestatus) {
        this.potholestatus = potholestatus;
    }

    public String getRepairstatus() {
        return repairstatus;
    }

    public void setRepairstatus(String repairstatus) {
        this.repairstatus = repairstatus;
    }

    public String getWorkorderstatus() {
        return workorderstatus;
    }

    public void setWorkorderstatus(String workorderstatus) {
        this.workorderstatus = workorderstatus;
    }

    public String getRepairordertype() {
        return repairordertype;
    }

    public void setRepairordertype(String repairordertype) {
        this.repairordertype = repairordertype;
    }

    public String getWorkordertype() {
        return workordertype;
    }

    public void setWorkordertype(String workordertype) {
        this.workordertype = workordertype;
    }

    public String getReportingpersonkey() {
        return reportingpersonkey;
    }

    public void setReportingpersonkey(String reportingpersonkey) {
        this.reportingpersonkey = reportingpersonkey;
    }

    public String getRepairpoc() {
        return repairpoc;
    }

    public void setRepairpoc(String repairpoc) {
        this.repairpoc = repairpoc;
    }

    public String getWorkorderpoc() {
        return workorderpoc;
    }

    public void setWorkorderpoc(String workorderpoc) {
        this.workorderpoc = workorderpoc;
    }

    public Date getPotholeupdatedtime() {
        return potholeupdatedtime;
    }

    public void setPotholeupdatedtime(Date potholeupdatedtime) {
        this.potholeupdatedtime = potholeupdatedtime;
    }

    public Date getRepairupdatedtime() {
        return repairupdatedtime;
    }

    public void setRepairupdatedtime(Date repairupdatedtime) {
        this.repairupdatedtime = repairupdatedtime;
    }

    public Date getWorkorderupdatedtime() {
        return workorderupdatedtime;
    }

    public void setWorkorderupdatedtime(Date workorderupdatedtime) {
        this.workorderupdatedtime = workorderupdatedtime;
    }

    public Date getPotholeclosedtime() {
        return potholeclosedtime;
    }

    public void setPotholeclosedtime(Date potholeclosedtime) {
        this.potholeclosedtime = potholeclosedtime;
    }

    public Date getRepairclosedtime() {
        return repairclosedtime;
    }

    public void setRepairclosedtime(Date repairclosedtime) {
        this.repairclosedtime = repairclosedtime;
    }

    public Date getWorkorderclosedtime() {
        return workorderclosedtime;
    }

    public void setWorkorderclosedtime(Date workorderclosedtime) {
        this.workorderclosedtime = workorderclosedtime;
    }

    public String getPotholecomments() {
        return potholecomments;
    }

    public void setPotholecomments(String potholecomments) {
        this.potholecomments = potholecomments;
    }

    public String getRepaircomments() {
        return repaircomments;
    }

    public void setRepaircomments(String repaircomments) {
        this.repaircomments = repaircomments;
    }

    public String getWorkordercomments() {
        return workordercomments;
    }

    public void setWorkordercomments(String workordercomments) {
        this.workordercomments = workordercomments;
    }
    
}
