/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaapps.control;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpaapps.Payementlog;
import jpaapps.control.exceptions.NonexistentEntityException;
import jpaapps.control.exceptions.PreexistingEntityException;

/**
 *
 * @author pascalfares
 */
public class PayementlogJpaController implements Serializable {

    public PayementlogJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Payementlog payementlog) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(payementlog);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPayementlog(payementlog.getPayId()) != null) {
                throw new PreexistingEntityException("Payementlog " + payementlog + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Payementlog payementlog) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            payementlog = em.merge(payementlog);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = payementlog.getPayId();
                if (findPayementlog(id) == null) {
                    throw new NonexistentEntityException("The payementlog with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Payementlog payementlog;
            try {
                payementlog = em.getReference(Payementlog.class, id);
                payementlog.getPayId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The payementlog with id " + id + " no longer exists.", enfe);
            }
            em.remove(payementlog);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Payementlog> findPayementlogEntities() {
        return findPayementlogEntities(true, -1, -1);
    }

    public List<Payementlog> findPayementlogEntities(int maxResults, int firstResult) {
        return findPayementlogEntities(false, maxResults, firstResult);
    }

    private List<Payementlog> findPayementlogEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Payementlog.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Payementlog findPayementlog(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Payementlog.class, id);
        } finally {
            em.close();
        }
    }

    public int getPayementlogCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Payementlog> rt = cq.from(Payementlog.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
