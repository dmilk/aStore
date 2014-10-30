/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import auth.AuthAccessElement;
import entity.User;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import session.CustomerOrderFacade;
import session.UserFacade;

/**
 *
 * @author Notreal
 */
@Stateless
@Path("order")
public class CustomerOrderREST {
    
    @EJB
    CustomerOrderFacade customerOrderFacade;
    
    @EJB
    UserFacade userFacade;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response findAll(@Context HttpServletRequest request) {
//    public Collection<CustomerOrder> findAll(@Context HttpServletRequest request) {
        String authToken = request.getHeader(AuthAccessElement.PARAM_AUTH_TOKEN);
        User user = userFacade.findByToken(authToken);
        if (user !=null) {
            return Response.ok(user.getCustomerOrderCollection()).build();
        } 
        else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
