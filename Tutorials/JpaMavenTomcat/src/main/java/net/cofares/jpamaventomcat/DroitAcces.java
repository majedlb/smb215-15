/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.jpamaventomcat;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pascalfares
 */
@Entity
@Table(name = "DroitAcces")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DroitAcces.findAll", query = "SELECT d FROM DroitAcces d"),
    @NamedQuery(name = "DroitAcces.findByUserId", query = "SELECT d FROM DroitAcces d WHERE d.droitAccesPK.userId = :userId"),
    @NamedQuery(name = "DroitAcces.findByUserName", query = "SELECT d FROM DroitAcces d WHERE d.users.username = :userName"),   
    @NamedQuery(name = "DroitAcces.findByTypedroit", query = "SELECT d FROM DroitAcces d WHERE d.droitAccesPK.typedroit = :typedroit"),
    @NamedQuery(name = "DroitAcces.findBySpec", query = "SELECT d FROM DroitAcces d WHERE d.droitAccesPK.spec = :spec")})
public class DroitAcces implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DroitAccesPK droitAccesPK;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public DroitAcces() {
    }

    public DroitAcces(DroitAccesPK droitAccesPK) {
        this.droitAccesPK = droitAccesPK;
    }

    public DroitAcces(long userId, String typedroit, short spec) {
        this.droitAccesPK = new DroitAccesPK(userId, typedroit, spec);
    }

    public DroitAccesPK getDroitAccesPK() {
        return droitAccesPK;
    }

    public void setDroitAccesPK(DroitAccesPK droitAccesPK) {
        this.droitAccesPK = droitAccesPK;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (droitAccesPK != null ? droitAccesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DroitAcces)) {
            return false;
        }
        DroitAcces other = (DroitAcces) object;
        if ((this.droitAccesPK == null && other.droitAccesPK != null) || (this.droitAccesPK != null && !this.droitAccesPK.equals(other.droitAccesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DroitAcces[ droitAccesPK=" + droitAccesPK + ":" + users+ " ]";
    }
    
}
