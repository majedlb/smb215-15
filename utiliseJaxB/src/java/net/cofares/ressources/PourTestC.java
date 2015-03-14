/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.ressources;

import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import net.cofares.data.Datas;
import net.cofares.objects.TestA;
import net.cofares.objects.TestC;

/**
 * REST Web Service
 *
 * @author pascalfares
 */
@Path("test")
public class PourTestC {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PourTestC
     */
    public PourTestC() {
    }

    /**
     * Retrieves representation of an instance of net.cofares.ressources.PourTestC
     * @return an instance of net.cofares.objects.TestC
     */
    @GET
    @Produces("application/json")
    public Collection<TestC> getAll() {
        return Datas.findAll();
    }
    
    @GET
    @Produces("application/json")
    @Path("{id}")
    public TestC findl(@PathParam("id") int id) {
        return Datas.find(id);
    }
    @GET
    @Produces("application/json")
    @Path("init")
    public Collection<TestC> initGet() {    
       Datas.initDatas();
       return Datas.findAll();
    }
    
    @GET
    @Produces("text/plain")
    @Path("ping")
    public String pingGet() {    
       return "Ok working";
    }

    @GET
    @Produces("application/json")
    @Path("pinga")
    public TestA pingAGet() {    
       return new TestA(1, "un dans A");
    }
    
    @GET
    @Produces("application/json")
    @Path("pingc")
    public TestC pingCGet() {    
       return new TestC(1, "un", 1, "un dans A");
    }
    /**
     * PUT method for updating or creating an instance of PourTestC
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public TestC putJson(TestC content) {
        Datas.create(content);
        return content;
    }
}
