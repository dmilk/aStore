/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import entity.Route;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import session.RouteFacade;

/**
 * REST Web Service
 *
 * @author OLEG
 */
@Stateless
@Path("route")
public class RouteREST {
    
    @EJB
    RouteFacade routeFacade;

    public RouteREST() {
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Route> findAll() {
        return routeFacade.findAll();
    }

    /**
     * PUT method for updating or creating an instance of RouteREST
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
