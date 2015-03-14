/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.objects;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pascalfares
 */
@XmlRootElement
class TestA implements Serializable{
    private int idA;
    private String valeur;

    public TestA() {
        
    }
    public TestA(int id, String v){
        idA=id;
        valeur = new String(v);
    }
    
    public TestA(TestA ta){
        idA=ta.getIdA();
        valeur = new String(ta.getValeur());
    }
    /**
     * @return the idA
     */
    @XmlElement
    public int getIdA() {
        return idA;
    }

    /**
     * @param idA the idA to set
     */
    public void setIdA(int idA) {
        this.idA = idA;
    }

    /**
     * @return the valeur
     */
    @XmlElement
    public String getValeur() {
        return valeur;
    }

    /**
     * @param valeur the valeur to set
     */
    public void setValeur(String valeur) {
        this.valeur = valeur;
    }
}
