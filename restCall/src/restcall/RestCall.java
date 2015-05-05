/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restcall;

import net.cofares.call.NewJerseyClient;
import net.cofares.objects.TestA;
import net.cofares.objects.TestC;

/**
 *
 * @author pascalfares
 */
public class RestCall {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NewJerseyClient restCall = new NewJerseyClient();
        
        //Les ping
        System.out.println(restCall.pingGet());
        TestA ta = restCall.pingAGet(TestA.class);
        System.out.println(ta);
        TestC tc = restCall.pingAGet(TestC.class);
        System.out.println(tc);
        
        //Initialisation de test
        TestC[] ltc = restCall.initGet(TestC[].class);
        for (TestC c : ltc){
            System.out.println(c);
        }
        
        restCall.putJson(tc, TestC.class);
    }
    
}
