/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.control;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import net.cofares.jpamaventomcat.DroitAcces;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import net.cofares.control.exceptions.IllegalOrphanException;
import net.cofares.control.exceptions.NonexistentEntityException;
import net.cofares.jpamaventomcat.Users;

/**
 *
 * @author pascalfares
 */
public class UsersJpaController implements Serializable {

    public UsersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Users users) {
        if (users.getDroitAccesList() == null) {
            users.setDroitAccesList(new ArrayList<DroitAcces>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<DroitAcces> attachedDroitAccesList = new ArrayList<DroitAcces>();
            for (DroitAcces droitAccesListDroitAccesToAttach : users.getDroitAccesList()) {
                droitAccesListDroitAccesToAttach = em.getReference(droitAccesListDroitAccesToAttach.getClass(), droitAccesListDroitAccesToAttach.getDroitAccesPK());
                attachedDroitAccesList.add(droitAccesListDroitAccesToAttach);
            }
            users.setDroitAccesList(attachedDroitAccesList);
            em.persist(users);
            for (DroitAcces droitAccesListDroitAcces : users.getDroitAccesList()) {
                Users oldUsersOfDroitAccesListDroitAcces = droitAccesListDroitAcces.getUsers();
                droitAccesListDroitAcces.setUsers(users);
                droitAccesListDroitAcces = em.merge(droitAccesListDroitAcces);
                if (oldUsersOfDroitAccesListDroitAcces != null) {
                    oldUsersOfDroitAccesListDroitAcces.getDroitAccesList().remove(droitAccesListDroitAcces);
                    oldUsersOfDroitAccesListDroitAcces = em.merge(oldUsersOfDroitAccesListDroitAcces);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Users users) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users persistentUsers = em.find(Users.class, users.getUserId());
            List<DroitAcces> droitAccesListOld = persistentUsers.getDroitAccesList();
            List<DroitAcces> droitAccesListNew = users.getDroitAccesList();
            List<String> illegalOrphanMessages = null;
            for (DroitAcces droitAccesListOldDroitAcces : droitAccesListOld) {
                if (!droitAccesListNew.contains(droitAccesListOldDroitAcces)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DroitAcces " + droitAccesListOldDroitAcces + " since its users field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<DroitAcces> attachedDroitAccesListNew = new ArrayList<DroitAcces>();
            for (DroitAcces droitAccesListNewDroitAccesToAttach : droitAccesListNew) {
                droitAccesListNewDroitAccesToAttach = em.getReference(droitAccesListNewDroitAccesToAttach.getClass(), droitAccesListNewDroitAccesToAttach.getDroitAccesPK());
                attachedDroitAccesListNew.add(droitAccesListNewDroitAccesToAttach);
            }
            droitAccesListNew = attachedDroitAccesListNew;
            users.setDroitAccesList(droitAccesListNew);
            users = em.merge(users);
            for (DroitAcces droitAccesListNewDroitAcces : droitAccesListNew) {
                if (!droitAccesListOld.contains(droitAccesListNewDroitAcces)) {
                    Users oldUsersOfDroitAccesListNewDroitAcces = droitAccesListNewDroitAcces.getUsers();
                    droitAccesListNewDroitAcces.setUsers(users);
                    droitAccesListNewDroitAcces = em.merge(droitAccesListNewDroitAcces);
                    if (oldUsersOfDroitAccesListNewDroitAcces != null && !oldUsersOfDroitAccesListNewDroitAcces.equals(users)) {
                        oldUsersOfDroitAccesListNewDroitAcces.getDroitAccesList().remove(droitAccesListNewDroitAcces);
                        oldUsersOfDroitAccesListNewDroitAcces = em.merge(oldUsersOfDroitAccesListNewDroitAcces);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = users.getUserId();
                if (findUsers(id) == null) {
                    throw new NonexistentEntityException("The users with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users users;
            try {
                users = em.getReference(Users.class, id);
                users.getUserId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The users with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<DroitAcces> droitAccesListOrphanCheck = users.getDroitAccesList();
            for (DroitAcces droitAccesListOrphanCheckDroitAcces : droitAccesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the DroitAcces " + droitAccesListOrphanCheckDroitAcces + " in its droitAccesList field has a non-nullable users field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(users);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Users> findUsersEntities() {
        return findUsersEntities(true, -1, -1);
    }

    public List<Users> findUsersEntities(int maxResults, int firstResult) {
        return findUsersEntities(false, maxResults, firstResult);
    }

    private List<Users> findUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Users.class));
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

    public Users findUsers(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Users.class, id);
        } finally {
            em.close();
        }
    }

    public List<Users> findUsersByDroit(String td) {
        EntityManager em = getEntityManager();
        TypedQuery<Users> query
                = em.createNamedQuery("Users.findByDroit", Users.class);
        query.setParameter("tDroit", td);
        List<Users> results = query.getResultList();
        return results;
    }

    public int getUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Users> rt = cq.from(Users.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
