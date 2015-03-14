/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.data;

import java.util.ArrayList;
import java.util.HashMap;
import net.cofares.objects.TestC;

/**
 *
 * @author pascalfares
 */
public class Datas {
    
    /**
     * Represente les données
     */
    public static HashMap<Integer,TestC> datasTest;
    
    public Datas(){
        //Initialisations pour test
        datasTest = new HashMap<>();
        datasTest.put(1, new TestC(1, "Un", 101, "Un pour A"));
        datasTest.put(2, new TestC(2, "Deux", 102, "Deux A"));
        datasTest.put(3, new TestC(3, "Trois", 103, "Trois   A"));
        datasTest.put(4, new TestC(4, "Quatre", 104, "Quatre  A"));
    }
    
    public ArrayList<TestC> findAll(){
        return new ArrayList(datasTest.values());
    }
    
    public TestC find(int id) {
        return datasTest.get(id);
    }
    
    public void create(TestC tc){
        //Faudrais verifier que tc.getX() n'existe pas
        datasTest.put(tc.getX(),tc);
    }
    
    public void update(int id, TestC tc){
        //Faudrait verifier que id existe
        datasTest.put(id,tc);
    }
    
    public void delete(int id){
        datasTest.remove(id);
    }
}