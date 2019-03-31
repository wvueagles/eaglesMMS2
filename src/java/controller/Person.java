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
@Table(name = "person")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
    , @NamedQuery(name = "Person.findByReportingpersonkey", query = "SELECT p FROM Person p WHERE p.reportingpersonkey = :reportingpersonkey")
    , @NamedQuery(name = "Person.findByFirstname", query = "SELECT p FROM Person p WHERE p.firstname = :firstname")
    , @NamedQuery(name = "Person.findByLastname", query = "SELECT p FROM Person p WHERE p.lastname = :lastname")
    , @NamedQuery(name = "Person.findByAddress", query = "SELECT p FROM Person p WHERE p.address = :address")
    , @NamedQuery(name = "Person.findByPhone", query = "SELECT p FROM Person p WHERE p.phone = :phone")
    , @NamedQuery(name = "Person.findByAlternatephone", query = "SELECT p FROM Person p WHERE p.alternatephone = :alternatephone")
    , @NamedQuery(name = "Person.findByEmailaddress", query = "SELECT p FROM Person p WHERE p.emailaddress = :emailaddress")
    , @NamedQuery(name = "Person.findByCreatedat", query = "SELECT p FROM Person p WHERE p.createdat = :createdat")
    , @NamedQuery(name = "Person.findByUpdatedat", query = "SELECT p FROM Person p WHERE p.updatedat = :updatedat")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "reportingpersonkey")
    private String reportingpersonkey;
    @Basic(optional = false)
    @Column(name = "firstname")
    private String firstname;
    @Basic(optional = false)
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "alternatephone")
    private String alternatephone;
    @Column(name = "emailaddress")
    private String emailaddress;
    @Basic(optional = false)
    @Column(name = "createdat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdat;
    @Basic(optional = false)
    @Column(name = "updatedat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedat;

    public Person() {
    }

    public Person(String reportingpersonkey) {
        this.reportingpersonkey = reportingpersonkey;
    }

    public Person(String reportingpersonkey, String firstname, String lastname, Date createdat, Date updatedat) {
        this.reportingpersonkey = reportingpersonkey;
        this.firstname = firstname;
        this.lastname = lastname;
        this.createdat = createdat;
        this.updatedat = updatedat;
    }

    public String getReportingpersonkey() {
        return reportingpersonkey;
    }

    public void setReportingpersonkey(String reportingpersonkey) {
        this.reportingpersonkey = reportingpersonkey;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAlternatephone() {
        return alternatephone;
    }

    public void setAlternatephone(String alternatephone) {
        this.alternatephone = alternatephone;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reportingpersonkey != null ? reportingpersonkey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.reportingpersonkey == null && other.reportingpersonkey != null) || (this.reportingpersonkey != null && !this.reportingpersonkey.equals(other.reportingpersonkey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.Person[ reportingpersonkey=" + reportingpersonkey + " ]";
    }
    
}
