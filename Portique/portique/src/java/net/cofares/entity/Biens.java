/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "BIENS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Biens.findAll", query = "SELECT b FROM Biens b"),
    @NamedQuery(name = "Biens.findByCle", query = "SELECT b FROM Biens b WHERE b.cle = :cle"),
    @NamedQuery(name = "Biens.findByCodeBien", query = "SELECT b FROM Biens b WHERE b.codeBien = :codeBien"),
    @NamedQuery(name = "Biens.findByNomBien", query = "SELECT b FROM Biens b WHERE b.nomBien = :nomBien"),
    @NamedQuery(name = "Biens.findByCommentaire", query = "SELECT b FROM Biens b WHERE b.commentaire = :commentaire")})
public class Biens implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cle")
    private Integer cle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "codeBien")
    private String codeBien;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nomBien")
    private String nomBien;
    @Size(max = 255)
    @Column(name = "commentaire")
    private String commentaire;
    @Lob
    @Size(max = 65535)
    @Column(name = "specifications")
    private String specifications;

    public Biens() {
    }

    public Biens(Integer cle) {
        this.cle = cle;
    }

    public Biens(Integer cle, String codeBien, String nomBien) {
        this.cle = cle;
        this.codeBien = codeBien;
        this.nomBien = nomBien;
    }
    
    public Biens(String codeBien, String nomBien) {
        this.cle = null; //Auto increment
        this.codeBien = codeBien;
        this.nomBien = nomBien;
    }

    public Integer getCle() {
        return cle;
    }

    public void setCle(Integer cle) {
        this.cle = cle;
    }

    public String getCodeBien() {
        return codeBien;
    }

    public void setCodeBien(String codeBien) {
        this.codeBien = codeBien;
    }

    public String getNomBien() {
        return nomBien;
    }

    public void setNomBien(String nomBien) {
        this.nomBien = nomBien;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cle != null ? cle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Biens)) {
            return false;
        }
        Biens other = (Biens) object;
        if ((this.cle == null && other.cle != null) || (this.cle != null && !this.cle.equals(other.cle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.cofares.entity.Biens[ cle=" + cle + " ]";
    }
    
}
