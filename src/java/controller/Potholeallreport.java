/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.lang.reflect.Field;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
    , @NamedQuery(name = "Potholeallreport.findBySeverity", query = "SELECT p FROM Potholeallreport p WHERE p.severity = :severity")
    , @NamedQuery(name = "Potholeallreport.findByCreateddate", query = "SELECT p FROM Potholeallreport p WHERE p.createddate = :createddate")
    , @NamedQuery(name = "Potholeallreport.findByPotholestatus", query = "SELECT p FROM Potholeallreport p WHERE p.potholestatus = :potholestatus")
    , @NamedQuery(name = "Potholeallreport.findByRepairstatus", query = "SELECT p FROM Potholeallreport p WHERE p.repairstatus = :repairstatus")
    , @NamedQuery(name = "Potholeallreport.findByWorkorderstatus", query = "SELECT p FROM Potholeallreport p WHERE p.workorderstatus = :workorderstatus")
    , @NamedQuery(name = "Potholeallreport.findByRepairordertype", query = "SELECT p FROM Potholeallreport p WHERE p.repairordertype = :repairordertype")
    , @NamedQuery(name = "Potholeallreport.findByWorkordertype", query = "SELECT p FROM Potholeallreport p WHERE p.workordertype = :workordertype")
    , @NamedQuery(name = "Potholeallreport.findByReportingpersonkey", query = "SELECT p FROM Potholeallreport p WHERE p.reportingpersonkey = :reportingpersonkey")
    , @NamedQuery(name = "Potholeallreport.findByRepairpoc", query = "SELECT p FROM Potholeallreport p WHERE p.repairpoc = :repairpoc")
    , @NamedQuery(name = "Potholeallreport.findByWorkorderpoc", query = "SELECT p FROM Potholeallreport p WHERE p.workorderpoc = :workorderpoc")
    , @NamedQuery(name = "Potholeallreport.findByPotholeupdateddate", query = "SELECT p FROM Potholeallreport p WHERE p.potholeupdateddate = :potholeupdateddate")
    , @NamedQuery(name = "Potholeallreport.findByRepairupdateddate", query = "SELECT p FROM Potholeallreport p WHERE p.repairupdateddate = :repairupdateddate")
    , @NamedQuery(name = "Potholeallreport.findByWorkorderupdateddae", query = "SELECT p FROM Potholeallreport p WHERE p.workorderupdateddae = :workorderupdateddae")
    , @NamedQuery(name = "Potholeallreport.findByPotholeclosedtime", query = "SELECT p FROM Potholeallreport p WHERE p.potholeclosedtime = :potholeclosedtime")
    , @NamedQuery(name = "Potholeallreport.findByRepairclosedtime", query = "SELECT p FROM Potholeallreport p WHERE p.repairclosedtime = :repairclosedtime")
    , @NamedQuery(name = "Potholeallreport.findByWorkorderclosedtime", query = "SELECT p FROM Potholeallreport p WHERE p.workorderclosedtime = :workorderclosedtime")
    , @NamedQuery(name = "Potholeallreport.findByPotholecomments", query = "SELECT p FROM Potholeallreport p WHERE p.potholecomments = :potholecomments")
    , @NamedQuery(name = "Potholeallreport.findByRepaircomments", query = "SELECT p FROM Potholeallreport p WHERE p.repaircomments = :repaircomments")
    , @NamedQuery(name = "Potholeallreport.findByWorkordercomments", query = "SELECT p FROM Potholeallreport p WHERE p.workordercomments = :workordercomments")})
public class Potholeallreport implements Serializable {

    @Column(name = "workid")
    private String workid;
    @Column(name = "potholelocation")
    private String potholelocation;
    @Column(name = "severity")
    private String severity;
    @Column(name = "createddate")
    private String createddate;
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
    @Column(name = "potholeupdateddate")
    private String potholeupdateddate;
    @Column(name = "repairupdateddate")
    private String repairupdateddate;
    @Column(name = "workorderupdateddae")
    private String workorderupdateddae;
    @Column(name = "potholeclosedtime")
    private String potholeclosedtime;
    @Column(name = "repairclosedtime")
    private String repairclosedtime;
    @Column(name = "workorderclosedtime")
    private String workorderclosedtime;
    @Column(name = "potholecomments")
    private String potholecomments;
    @Column(name = "repaircomments")
    private String repaircomments;
    @Column(name = "workordercomments")
    private String workordercomments;
    @Id
    private Long id;

    protected void setStringField(String fieldName, String value) throws NoSuchFieldException, IllegalAccessException {
    try {
            Field fpField = getClass().getDeclaredField(fieldName);
            fpField.set(this, value);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
        }
    }
        
    public Potholeallreport() {
    }

    public String getWorkid() {
        return workid;
    }

    public void setWorkid(String workid) {
        this.workid = workid;
    }

    public String getPotholelocation() {
        return potholelocation;
    }

    public void setPotholelocation(String potholelocation) {
        this.potholelocation = potholelocation;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getCreateddate() {
        return createddate;
    }

    public void setCreateddate(String createddate) {
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

    public String getPotholeupdateddate() {
        return potholeupdateddate;
    }

    public void setPotholeupdateddate(String potholeupdateddate) {
        this.potholeupdateddate = potholeupdateddate;
    }

    public String getRepairupdateddate() {
        return repairupdateddate;
    }

    public void setRepairupdateddate(String repairupdateddate) {
        this.repairupdateddate = repairupdateddate;
    }

    public String getWorkorderupdateddae() {
        return workorderupdateddae;
    }

    public void setWorkorderupdateddae(String workorderupdateddae) {
        this.workorderupdateddae = workorderupdateddae;
    }

    public String getPotholeclosedtime() {
        return potholeclosedtime;
    }

    public void setPotholeclosedtime(String potholeclosedtime) {
        this.potholeclosedtime = potholeclosedtime;
    }

    public String getRepairclosedtime() {
        return repairclosedtime;
    }

    public void setRepairclosedtime(String repairclosedtime) {
        this.repairclosedtime = repairclosedtime;
    }

    public String getWorkorderclosedtime() {
        return workorderclosedtime;
    }

    public void setWorkorderclosedtime(String workorderclosedtime) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
