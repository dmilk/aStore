/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import entity.Route;
import java.util.List;
import javax.annotation.security.DenyAll;
import javax.ejb.EJB;
import javax.validation.constraints.DecimalMin;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import session.RouteFacade;

/**
 * REST Web Service
 *
 * @author OLEG
 */
@Path("route")
public class RouteResource {
    
    @EJB
    RouteFacade routeFacade;

    public RouteResource() {
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Route> findAll() {
        return routeFacade.findAll();
    }
    
    @GET
    @Path("new")
    @Produces(MediaType.APPLICATION_JSON)
    public Route createRoute() {
        Route route = new Route();
        route.setName("newRoute");
        routeFacade.create(route);
        return route;
    }
    
    @DELETE
    @Path("{id}")
    public void deleteRoute(
            @DecimalMin(value = "0")
            @PathParam("id") final int id) {
        System.out.println("Delete = " + id);
        routeFacade.remove(routeFacade.find(id));
    }
    
}
