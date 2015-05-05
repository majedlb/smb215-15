/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.resources;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author pascalfares
 */
@Path("hello1")
public class Racine {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Racine
     */
    public Racine() {
    }

    /**
     * Retrieves representation of an instance of net.cofares.resources.Racine
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    public String getXml() {
        //TODO return proper representation object
        return "Notre premier service hello smb215  2015";
    }

    @PUT
    @Produces("text/plain")
    @Consumes("text/plain")
    public String putXml() {
        //TODO return proper representation object
        return "Notre premier put service hello smb215  2015";
    }
  
}
