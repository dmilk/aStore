/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import auth.AuthAccessElement;
import entity.CustomerOrder;
import entity.User;
import java.util.Collection;
import java.util.Collections;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @PermitAll
    public Collection<CustomerOrder> findAll(@HeaderParam(AuthAccessElement.PARAM_AUTH_TOKEN) String authToken) {
        User user = userFacade.findByToken(authToken);
        if (user != null) {
            return user.getCustomerOrderCollection();
        } else
        {
            return Collections.EMPTY_LIST;
        }
    }
    
}
