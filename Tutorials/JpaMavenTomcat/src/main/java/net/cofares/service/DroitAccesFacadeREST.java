/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.PathSegment;
import net.cofares.jpamaventomcat.DroitAcces;
import net.cofares.jpamaventomcat.DroitAccesPK;

/**
 *
 * @author pascalfares
 */

@Path("droitacces")
public class DroitAccesFacadeREST extends AbstractFacade<DroitAcces> {
    @PersistenceContext(unitName = "net.cofares_JpaMavenTomcat_war_0.0PU")
    private EntityManager em;

    private DroitAccesPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;userId=userIdValue;typedroit=typedroitValue;spec=specValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        net.cofares.jpamaventomcat.DroitAccesPK key = new net.cofares.jpamaventomcat.DroitAccesPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> userId = map.get("userId");
        if (userId != null && !userId.isEmpty()) {
            key.setUserId(new java.lang.Long(userId.get(0)));
        }
        java.util.List<String> typedroit = map.get("typedroit");
        if (typedroit != null && !typedroit.isEmpty()) {
            key.setTypedroit(typedroit.get(0));
        }
        java.util.List<String> spec = map.get("spec");
        if (spec != null && !spec.isEmpty()) {
            key.setSpec(new java.lang.Short(spec.get(0)));
        }
        return key;
    }

    public DroitAccesFacadeREST() {
        super(DroitAcces.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(DroitAcces entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") PathSegment id, DroitAcces entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        net.cofares.jpamaventomcat.DroitAccesPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public DroitAcces find(@PathParam("id") PathSegment id) {
        net.cofares.jpamaventomcat.DroitAccesPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<DroitAcces> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<DroitAcces> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
