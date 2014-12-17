package resource;

import entity.Route;
import static entity.Ticket_.id;
import entity.User;
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
import session.UserFacade;

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
    UserFacade userFacade;
    
    @GET
    @Path("c")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchUser0() {
        User user0 = userFacade.find(0);
        System.out.println(user0);
        return Response.ok(user0.getFirstName()).build();
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
    
}
