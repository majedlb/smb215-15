/*
 * A ajouter dans le code générer par netbeans dans le cas de serveur d'application
 * Profil web simple (sans ejb ni JTA)
 */
package net.cofares.entity.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


/**
 *
 * @author pascalfares
 * @param <T>
 */
public abstract class AbstractFacadeNoT<T> extends AbstractFacade<T> {
    
    private EntityManager em;
    
    public AbstractFacadeNoT(Class<T> entityClass) {
        super(entityClass);
    }
    
     protected abstract EntityManagerFactory getEntityManagerFactory();
     
     protected EntityManager getEntityManager() {
         if (em==null)
          em= getEntityManagerFactory().createEntityManager();
         return em;
     }
     
    @Override
     public void create(T entity) {
         getEntityManager().getTransaction().begin();
         super.create(entity);
         getEntityManager().getTransaction().commit();
    }

    @Override
    public void edit(T entity) {
        getEntityManager().getTransaction().begin();
        super.edit(entity);
        getEntityManager().getTransaction().begin();
    }

    @Override
    public void remove(T entity) {
        getEntityManager().getTransaction().begin();
        super.remove(entity);
        getEntityManager().getTransaction().begin();
    }
}
