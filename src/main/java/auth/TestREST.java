/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth;

import auth.Salt;
import entity.Route;
import java.util.Enumeration;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import session.RouteFacade;

/**
 *
 * @author Notreal
 */
//@Stateless
@Path("test")
public class TestREST {
    private static final Logger LOG = Logger.getLogger(TestREST.class.getName());
    
    @Context
    SecurityContext securityContext;
    
    @EJB
    private RouteFacade routeFacade;
 
//    @EJB
//    ReportManager reportManager;

    public TestREST() {
    }

    @GET
    @Path("s")
    public String getUserPrincipal() {
        return securityContext.getUserPrincipal().getName();
    }


//    @GET
//    @Path("x")
//    @Produces(MediaType.MULTIPART_FORM_DATA)
//    public Response textExcel() {
//        
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        reportManager.generateExcel(baos);
//        byte[] data = baos.toByteArray();
//        try {
//            baos.close();
//        } catch (IOException ex) {
//            LOG.log(Level.SEVERE, null, ex);
//        }
//        ResponseBuilder response = Response.ok(data);
//        Date date = new Date();
//        response.header("Content-Disposition", "attachment; filename=report_" + date + ".xls");
//        return response.build();
//    }
    
    @GET
    @Path("am")
    @RolesAllowed({"user"})
    //@Produces({MediaType.APPLICATION_JSON})
    @Produces("application/json")
    public Response testKavkazManager() {
        //List<Route> routes = routeFacade.findAll();
        Route route = new Route(13, "xxx");
        
        return Response.ok(route).build();
    }
    
    @GET
    @Path("bm")
//    @GoatsAllowed("user")
    @RolesAllowed({"manager2"})
    public String testKrymManager() {
        return "success Krym Manager";
    }
    
    @GET
    @Path("cm")
    @RolesAllowed({"user"})
    public String testUser() {
        return "success user";
    }
    

    @GET
    @Path("t")
//    @Produces({"application/xml", "application/json"})
    public String testPath() {
        return "new UserJson()";
    }

    @GET
    @Path("s")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalt() {
        Salt salt = new Salt();
        return Response.ok(salt).build();

    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String test(@Context HttpServletRequest request) {
        JsonObjectBuilder builder = Json.createObjectBuilder();

        if (request.getUserPrincipal() != null) {
            builder.add("UserPrincipal", request.getUserPrincipal().toString());
        }

        builder.add("Header", "");
        Enumeration<String> header = request.getHeaderNames();
        while (header.hasMoreElements()) {
            String headerName = header.nextElement();
            builder.add(headerName, request.getHeader(headerName));
        }

        String testHeader = request.getHeader("xxx-auth-id");
        if (testHeader != null) {
            System.out.println("\n\n\n" + testHeader + "\n\n\n");
        }

        return builder.build().toString();
    }

}
