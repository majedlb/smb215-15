/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import net.cofares.control.UsersJpaController;
import net.cofares.control.exceptions.IllegalOrphanException;
import net.cofares.control.exceptions.NonexistentEntityException;
import net.cofares.jpamaventomcat.Users;

/**
 *
 * @author pascalfares
 */
@Path("users")
public class UsersFacadeREST {
    private EntityManager em;
    private UsersJpaController uac;
    public UsersFacadeREST() {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("net.cofares_JpaMavenTomcat_war_0.0PU");
        uac = new UsersJpaController(emf);
        em = uac.getEntityManager();
    }

    @POST
    
    @Consumes({"application/xml", "application/json"})
    public void create(Users entity) {
        uac.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, Users entity) {
        try {
            uac.edit(entity);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UsersFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UsersFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        try {
            uac.destroy(id);
        } catch (IllegalOrphanException | NonexistentEntityException ex) {
            Logger.getLogger(UsersFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Users find(@PathParam("id") Long id) {
        //return uac.findUsers(id);
        return uac.findUsers(id);
        
    }

    @GET
   
    @Produces({"application/xml", "application/json"})
    public List<Users> findAll() {
        return uac.findUsersEntities();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Users> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return uac.findUsersEntities(from, to);
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(uac.getUsersCount());
    }

  
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
