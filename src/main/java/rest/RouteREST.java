/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import entity.Route;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import session.RouteFacade;

/**
 * REST Web Service
 *
 * @author OLEG
 */
@PermitAll
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
}
