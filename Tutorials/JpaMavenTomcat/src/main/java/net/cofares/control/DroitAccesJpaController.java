
package net.cofares.control;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import net.cofares.control.exceptions.NonexistentEntityException;
import net.cofares.control.exceptions.PreexistingEntityException;
import net.cofares.jpamaventomcat.DroitAcces;
import net.cofares.jpamaventomcat.DroitAccesPK;
import net.cofares.jpamaventomcat.Users;

/**
 *
 * @author pascalfares
 */
public class DroitAccesJpaController implements Serializable {

    public DroitAccesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DroitAcces droitAcces) throws PreexistingEntityException, Exception {
        if (droitAcces.getDroitAccesPK() == null) {
            droitAcces.setDroitAccesPK(new DroitAccesPK());
        }
        droitAcces.getDroitAccesPK().setUserId(droitAcces.getUsers().getUserId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users users = droitAcces.getUsers();
            if (users != null) {
                users = em.getReference(users.getClass(), users.getUserId());
                droitAcces.setUsers(users);
            }
            em.persist(droitAcces);
            if (users != null) {
                users.getDroitAccesList().add(droitAcces);
                users = em.merge(users);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDroitAcces(droitAcces.getDroitAccesPK()) != null) {
                throw new PreexistingEntityException("DroitAcces " + droitAcces + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DroitAcces droitAcces) throws NonexistentEntityException, Exception {
        droitAcces.getDroitAccesPK().setUserId(droitAcces.getUsers().getUserId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DroitAcces persistentDroitAcces = em.find(DroitAcces.class, droitAcces.getDroitAccesPK());
            Users usersOld = persistentDroitAcces.getUsers();
            Users usersNew = droitAcces.getUsers();
            if (usersNew != null) {
                usersNew = em.getReference(usersNew.getClass(), usersNew.getUserId());
                droitAcces.setUsers(usersNew);
            }
            droitAcces = em.merge(droitAcces);
            if (usersOld != null && !usersOld.equals(usersNew)) {
                usersOld.getDroitAccesList().remove(droitAcces);
                usersOld = em.merge(usersOld);
            }
            if (usersNew != null && !usersNew.equals(usersOld)) {
                usersNew.getDroitAccesList().add(droitAcces);
                usersNew = em.merge(usersNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DroitAccesPK id = droitAcces.getDroitAccesPK();
                if (findDroitAcces(id) == null) {
                    throw new NonexistentEntityException("The droitAcces with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DroitAccesPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DroitAcces droitAcces;
            try {
                droitAcces = em.getReference(DroitAcces.class, id);
                droitAcces.getDroitAccesPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The droitAcces with id " + id + " no longer exists.", enfe);
            }
            Users users = droitAcces.getUsers();
            if (users != null) {
                users.getDroitAccesList().remove(droitAcces);
                users = em.merge(users);
            }
            em.remove(droitAcces);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DroitAcces> findDAByUserName(String un) {
        EntityManager em = getEntityManager();
        TypedQuery<DroitAcces> query
                = em.createNamedQuery("DroitAcces.findByUserName", DroitAcces.class);
        query.setParameter("userName", un);
        List<DroitAcces> results = query.getResultList();
        return results;
    }

    public List<DroitAcces> findDroitAccesEntities() {
        return findDroitAccesEntities(true, -1, -1);
    }

    public List<DroitAcces> findDroitAccesEntities(int maxResults, int firstResult) {
        return findDroitAccesEntities(false, maxResults, firstResult);
    }

    private List<DroitAcces> findDroitAccesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DroitAcces.class));
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

    public DroitAcces findDroitAcces(DroitAccesPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DroitAcces.class, id);
        } finally {
            em.close();
        }
    }

    public int getDroitAccesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DroitAcces> rt = cq.from(DroitAcces.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
