/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import net.cofares.objects.TestC;

/**
 *
 * @author pascalfares
 * Implémenter un CRUD avec des données sous forme de clé valeur (aliste)
 * ici utilisation de HashMap pour modélisé les (clé,valeur)
 * 
 */
public class Datas {
    
    /**
     * Represente les données
     */
    public static HashMap<Integer,TestC> datasTest;
    
    public static void initDatas(){
        //Initialisations pour test
        datasTest = new HashMap<>();
        datasTest.put(1, new TestC(1, "Un", 101, "Un pour A"));
        datasTest.put(2, new TestC(2, "Deux", 102, "Deux A"));
        datasTest.put(3, new TestC(3, "Trois", 103, "Trois   A"));
        datasTest.put(4, new TestC(4, "Quatre", 104, "Quatre  A"));
    }
    
    public static Collection<TestC> findAll(){
        return datasTest.values();
    }
    
    public static TestC find(int id) {
        return datasTest.get(id);
    }
    
    public static void create(TestC tc){
        //Faudrais verifier que tc.getX() n'existe pas
        datasTest.put(tc.getX(),tc);
    }
    
    public static void update(int id, TestC tc){
        //Faudrait verifier que id existe
        datasTest.put(id,tc);
    }
    
    public static void delete(int id){
        datasTest.remove(id);
    }
}