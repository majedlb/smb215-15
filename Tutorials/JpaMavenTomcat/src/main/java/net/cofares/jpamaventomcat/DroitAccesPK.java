/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.jpamaventomcat;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author pascalfares
 */
@Embeddable
public class DroitAccesPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "UserId")
    private long userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "typedroit")
    private String typedroit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "spec")
    private short spec;

    public DroitAccesPK() {
    }

    public DroitAccesPK(long userId, String typedroit, short spec) {
        this.userId = userId;
        this.typedroit = typedroit;
        this.spec = spec;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTypedroit() {
        return typedroit;
    }

    public void setTypedroit(String typedroit) {
        this.typedroit = typedroit;
    }

    public short getSpec() {
        return spec;
    }

    public void setSpec(short spec) {
        this.spec = spec;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (typedroit != null ? typedroit.hashCode() : 0);
        hash += (int) spec;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DroitAccesPK)) {
            return false;
        }
        DroitAccesPK other = (DroitAccesPK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if ((this.typedroit == null && other.typedroit != null) || (this.typedroit != null && !this.typedroit.equals(other.typedroit))) {
            return false;
        }
        if (this.spec != other.spec) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DroitAccesPK[ userId=" + userId + ", typedroit=" + typedroit + ", spec=" + spec + " ]";
    }
    
}
