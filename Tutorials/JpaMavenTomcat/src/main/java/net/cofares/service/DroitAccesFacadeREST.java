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
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.PathSegment;
import net.cofares.control.DroitAccesJpaController;
import net.cofares.control.exceptions.NonexistentEntityException;
import net.cofares.jpamaventomcat.DroitAcces;
import net.cofares.jpamaventomcat.DroitAccesPK;

/**
 *
 * @author pascalfares
 */

@Path("da")
public class DroitAccesFacadeREST  {
    private EntityManager em;

    
    private DroitAccesJpaController dac;
    public DroitAccesFacadeREST() {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("net.cofares_JpaMavenTomcat_war_0.0PU");
        dac = new DroitAccesJpaController(emf);
        em = dac.getEntityManager();
    }

    @POST
    
    @Consumes({"application/xml", "application/json"})
    public void create(DroitAcces entity) {
        try {
            dac.create(entity);
        } catch (Exception ex) {
            Logger.getLogger(DroitAccesFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") PathSegment id, DroitAcces entity) {
        try {
            dac.edit(entity);
        } catch (Exception ex) {
            Logger.getLogger(DroitAccesFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        //net.cofares.jpamaventomcat.DroitAccesPK key = getPrimaryKey(id);
        //dac.destroy(key);
        
    }

   

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public List<DroitAcces> findByUserName(@PathParam("id") String id) {
        //net.cofares.jpamaventomcat.DroitAccesPK key = getPrimaryKey(id);
        return dac.findDAByUserName(id);
    }
    @GET
    
    @Produces({"application/xml", "application/json"})
    public List<DroitAcces> findAll() {
        return dac.findDroitAccesEntities();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<DroitAcces> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return dac.findDroitAccesEntities(from, to);
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(dac.getDroitAccesCount());
    }

   
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
