/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.lang.reflect.Field;
import javax.persistence.Basic;
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
@Table(name = "personreport")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personreport.findAll", query = "SELECT p FROM Personreport p")
    , @NamedQuery(name = "Personreport.findByPersonkey", query = "SELECT p FROM Personreport p WHERE p.personkey = :personkey")
    , @NamedQuery(name = "Personreport.findByFirstname", query = "SELECT p FROM Personreport p WHERE p.firstname = :firstname")
    , @NamedQuery(name = "Personreport.findByLastname", query = "SELECT p FROM Personreport p WHERE p.lastname = :lastname")
    , @NamedQuery(name = "Personreport.findByAddress", query = "SELECT p FROM Personreport p WHERE p.address = :address")
    , @NamedQuery(name = "Personreport.findByPhone", query = "SELECT p FROM Personreport p WHERE p.phone = :phone")
    , @NamedQuery(name = "Personreport.findByAlternatephone", query = "SELECT p FROM Personreport p WHERE p.alternatephone = :alternatephone")
    , @NamedQuery(name = "Personreport.findByEmailaddress", query = "SELECT p FROM Personreport p WHERE p.emailaddress = :emailaddress")
    , @NamedQuery(name = "Personreport.findByCreateddate", query = "SELECT p FROM Personreport p WHERE p.createddate = :createddate")
    , @NamedQuery(name = "Personreport.findByUpdateddate", query = "SELECT p FROM Personreport p WHERE p.updateddate = :updateddate")})
public class Personreport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "personkey")
    private String personkey;
    @Column(name = "firstname")
    private String firstname;
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
    @Column(name = "createddate")
    private String createddate;
    @Column(name = "updateddate")
    private String updateddate;

    public Personreport() {
    }
    
    protected void setStringField(String fieldName, String value) throws NoSuchFieldException, IllegalAccessException {
        try {
            Field fpField = getClass().getDeclaredField(fieldName);
            fpField.set(this, value);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
        }
    }
  
    public String getPersonkey() {
        return personkey;
    }
    
    public void setPersonkey(String personkey) {
        this.personkey = personkey;
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

    public String getCreateddate() {
        return createddate;
    }

    public void setCreateddate(String createddate) {
        this.createddate = createddate;
    }

    public String getUpdateddate() {
        return updateddate;
    }

    public void setUpdateddate(String updateddate) {
        this.updateddate = updateddate;
    }
    
}
