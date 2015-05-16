/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.jpameven;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author pascalfares
 */
@Entity
@Table(name = "Pays")
@NamedQueries({
    @NamedQuery(name = "Pays.findAll", query = "SELECT p FROM Pays p")})
public class Pays implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPays")
    private Integer idPays;
    @Column(name = "NomPays")
    private String nomPays;
    @Column(name = "pays_iso_code_2")
    private String paysIsoCode2;
    @Column(name = "pays_iso_code_3")
    private String paysIsoCode3;
    @Column(name = "NomPaysAr")
    private String nomPaysAr;
    @Column(name = "Nationalite")
    private String nationalite;
    @Column(name = "NationaliteAr")
    private String nationaliteAr;
    @Column(name = "NoPays")
    private Short noPays;

    public Pays() {
    }

    public Pays(Integer idPays) {
        this.idPays = idPays;
    }

    public Integer getIdPays() {
        return idPays;
    }

    public void setIdPays(Integer idPays) {
        this.idPays = idPays;
    }

    public String getNomPays() {
        return nomPays;
    }

    public void setNomPays(String nomPays) {
        this.nomPays = nomPays;
    }

    public String getPaysIsoCode2() {
        return paysIsoCode2;
    }

    public void setPaysIsoCode2(String paysIsoCode2) {
        this.paysIsoCode2 = paysIsoCode2;
    }

    public String getPaysIsoCode3() {
        return paysIsoCode3;
    }

    public void setPaysIsoCode3(String paysIsoCode3) {
        this.paysIsoCode3 = paysIsoCode3;
    }

    public String getNomPaysAr() {
        return nomPaysAr;
    }

    public void setNomPaysAr(String nomPaysAr) {
        this.nomPaysAr = nomPaysAr;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getNationaliteAr() {
        return nationaliteAr;
    }

    public void setNationaliteAr(String nationaliteAr) {
        this.nationaliteAr = nationaliteAr;
    }

    public Short getNoPays() {
        return noPays;
    }

    public void setNoPays(Short noPays) {
        this.noPays = noPays;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPays != null ? idPays.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pays)) {
            return false;
        }
        Pays other = (Pays) object;
        if ((this.idPays == null && other.idPays != null) || (this.idPays != null && !this.idPays.equals(other.idPays))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.cofares.jpameven.Pays[ idPays=" + idPays + " ]";
    }
    
}
