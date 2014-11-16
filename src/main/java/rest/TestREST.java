/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import auth.Cat;
import entity.Route;
import java.util.Enumeration;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import session.RouteFacade;

/**
 *
 * @author OLEG
 */
@Path("test")
public class TestREST {
    
    @EJB
    private RouteFacade routeFacade;
    
    @GET
    public String test1(@Context HttpServletRequest request) {
        
        JsonObjectBuilder builder = Json.createObjectBuilder();
        
        Enumeration<String> header = request.getHeaderNames();
        while (header.hasMoreElements()) {
            String headerName = header.nextElement();
            builder.add(headerName, request.getHeader(headerName));
        }

        return builder.build().toString();
    }
    
    @GET
    @Path("a")
    @PermitAll
    public Route testAll() {
        //Route route = new Route(13, "new_route");
        Route route = routeFacade.find(1);
        
        System.out.println(route);
        return route;
    }
    
    @GET
    @Path("r")
    @RolesAllowed({"user"})
    public String testRole() {
        System.out.println("role user");
        //List<Route> routes = routeFacade.findAll();
        //System.out.println(routes);
        return "routes";
    }
    
    @GET
    @Path("am")
    @RolesAllowed({"user"})
    public Route testRoleAm() {
        Route route = routeFacade.find(1);
        return route;
    }    
    
    @GET
    @Path("r2")
    @RolesAllowed({"admin"})
    public Cat testRole2() {
        System.out.println("role admin");
        return new Cat();
    }
    
}
