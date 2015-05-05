/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pascalfares
 */
@Entity
@Table(name = "EXEMPLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exemple.findAll", query = "SELECT e FROM Exemple e"),
    @NamedQuery(name = "Exemple.findByIdEXEMPLE", query = "SELECT e FROM Exemple e WHERE e.idEXEMPLE = :idEXEMPLE"),
    @NamedQuery(name = "Exemple.findByNom", query = "SELECT e FROM Exemple e WHERE e.nom = :nom"),
    @NamedQuery(name = "Exemple.findByPass", query = "SELECT e FROM Exemple e WHERE e.pass = :pass"),
    @NamedQuery(name = "Exemple.findByCommentaire", query = "SELECT e FROM Exemple e WHERE e.commentaire = :commentaire")})
public class Exemple implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idEXEMPLE")
    private Integer idEXEMPLE;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "pass")
    private String pass;
    @Size(max = 45)
    @Column(name = "commentaire")
    private String commentaire;

    public Exemple() {
    }

    public Exemple(Integer idEXEMPLE) {
        this.idEXEMPLE = idEXEMPLE;
    }

    public Exemple(Integer idEXEMPLE, String nom, String pass) {
        this.idEXEMPLE = idEXEMPLE;
        this.nom = nom;
        this.pass = pass;
    }

    public Integer getIdEXEMPLE() {
        return idEXEMPLE;
    }

    public void setIdEXEMPLE(Integer idEXEMPLE) {
        this.idEXEMPLE = idEXEMPLE;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEXEMPLE != null ? idEXEMPLE.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exemple)) {
            return false;
        }
        Exemple other = (Exemple) object;
        if ((this.idEXEMPLE == null && other.idEXEMPLE != null) || (this.idEXEMPLE != null && !this.idEXEMPLE.equals(other.idEXEMPLE))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.cofares.Exemple[ idEXEMPLE=" + idEXEMPLE + " ]";
    }
    
}
