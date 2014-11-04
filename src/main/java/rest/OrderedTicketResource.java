/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import auth.AuthAccessElement;
import entity.CustomerOrder;
import entity.OrderedTicket;
import entity.User;
import java.util.Collection;
import java.util.Collections;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import session.OrderedTicketFacade;
import session.RouteFacade;
import session.UserFacade;

/**
 * REST Web Service
 *
 * @author Notreal
 */
@Path("orderedTicket")
public class OrderedTicketResource {
    
    @EJB
    UserFacade userFacade;
    
    @EJB
    OrderedTicketFacade orderedTicketFacade;
    
    @EJB
    RouteFacade routeFacade;

    public OrderedTicketResource() {
    }
    
    @GET
    @Produces({"application/xml", "application/json"})
    public Collection<OrderedTicket> findByCustomerOrder(@HeaderParam(AuthAccessElement.PARAM_AUTH_TOKEN) String authToken,
//    public Collection<Route> findByCustomerOrder(@HeaderParam(AuthAccessElement.PARAM_AUTH_TOKEN) String authToken,
            @QueryParam("cn") Integer confirmationNumber) {
//        return orderedTicketFacade.findAll();
//        return routeFacade.findAll();
        User user = userFacade.findByToken(authToken);
        if (user != null) {
            for(CustomerOrder customerOrder : user.getCustomerOrderCollection()) {
                if (customerOrder.getConfirmationNumber() == confirmationNumber)
                    return customerOrder.getOrderedTicketCollection();
            }
        }
        return Collections.EMPTY_LIST;
    }
}
