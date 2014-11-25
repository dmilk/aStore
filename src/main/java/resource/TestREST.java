/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import auth.Cat;
import entity.Route;
import java.util.Enumeration;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import session.RouteFacade;
import session.TestService;

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
    
    @EJB
    TestService testService;
    
    @GET
    @Path("c")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRoute() {
        int id = testService.createRoute();
        return Response.ok(id).build();
    }
    
    @GET
    @Path("t")
    public String testT() {
        return "Hello";
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
