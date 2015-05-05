/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaapps;

import java.util.List;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpaapps.control.PayementlogJpaController;
import org.eclipse.persistence.config.PersistenceUnitProperties;

/**
 *
 * @author pascalfares
 */
public class JPAApps {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Properties pros = new Properties();

        pros.setProperty(PersistenceUnitProperties.ECLIPSELINK_PERSISTENCE_XML,
                "META-INF/persistence.xml");

        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("JPAAppsPU", pros);
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAAppsPU");
        PayementlogJpaController pc = new PayementlogJpaController(emf);

        List<Payementlog> lpl = pc.findPayementlogEntities();

        for (Payementlog pl : lpl) {
            System.out.println(pl);
        }
    }

}
