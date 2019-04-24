/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Objects;
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
    , @NamedQuery(name = "Person.findByPersonkey", query = "SELECT p FROM Person p WHERE p.personkey = :personkey")
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
    @Column(name = "personkey")
    private String personkey;
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

    public Person(String personkey) {
        this.personkey = personkey;
    }

    //min for insert
    public Person(String personkey, String firstname, String lastname) {
        this.personkey = personkey;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    
    //max for full update
    public Person(String personkey, String firstname, String lastname, String address, String emailaddress, String alternatephone, String phone) {
        this.personkey = personkey;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.emailaddress = emailaddress;
        this.alternatephone = alternatephone;
        this.phone = phone;
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
        hash += (personkey != null ? personkey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (!Objects.equals(this.personkey, other.personkey)) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.alternatephone, other.alternatephone)) {
            return false;
        }
        if (!Objects.equals(this.emailaddress, other.emailaddress)) {
            return false;
        }
        if (!Objects.equals(this.createdat, other.createdat)) {
            return false;
        }
        if (!Objects.equals(this.updatedat, other.updatedat)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.Person[ personkey=" + personkey + " ]";
    }  
}
