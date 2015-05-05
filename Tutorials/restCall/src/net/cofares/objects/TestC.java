/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.objects;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pascalfares
 */
@XmlRootElement
public class TestC implements Serializable{
    private int x;
    private String s;
    private TestA ta;
    
    public TestC() {
        
    }
    public TestC (int id, String v, TestA ta) {
        x=id;
        s=new String(v);
        this.ta = new TestA(ta);
    }
    
    public TestC (int id, String v, int idA, String va) {
        x=id;
        s=new String(v);
        ta = new TestA(idA,va);
    }
    /**
     * @return the x
     */
   
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the s
     */
    
    public String getS() {
        return s;
    }

    /**
     * @param s the s to set
     */
    public void setS(String s) {
        this.s = s;
    }

    /**
     * @return the ta
     */
    public TestA getTa() {
        return ta;
    }

    /**
     * @param ta the ta to set
     */
    public void setTa(TestA ta) {
        this.ta = ta;
    }

    public String toString() {
        return "TestC: "+x+" val="+s+" TA="+ta; 
    }
    
}
