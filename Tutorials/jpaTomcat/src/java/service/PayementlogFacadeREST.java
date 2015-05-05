/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import net.cofares.controle.PayementlogJpaController;
import net.cofares.entity.Payementlog;

/**
 *
 * @author pascalfares
 */
@Path("pl")
public class PayementlogFacadeREST  {
    //@PersistenceContext(unitName = "jpaTomcatPU")
    private EntityManager em;
    
    private PayementlogJpaController pc;

    public PayementlogFacadeREST() {     
        
        pc = new PayementlogJpaController("jpaTomcatPU");
        em=pc.getEntityManager();
    }

    @POST
  
    @Consumes({"application/xml", "application/json"})
    public void create(Payementlog entity) {
        try {
            pc.create(entity);
        } catch (Exception ex) {
            Logger.getLogger(PayementlogFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, Payementlog entity) {
        try {
            pc.edit(entity);
        } catch (Exception ex) {
            Logger.getLogger(PayementlogFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        //pc.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Payementlog find(@PathParam("id") String id) {
        return pc.find(id);
    }

    @GET
   
    @Produces({"application/xml", "application/json"})
    public List<Payementlog> findAll() {
        return pc.findAll();
    }

    

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(pc.count());
    }

  
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
