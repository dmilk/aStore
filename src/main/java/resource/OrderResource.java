/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import auth.AuthAccessElement;
import entity.Order;
import entity.User;
import java.util.Collection;
import java.util.Collections;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import session.OrderFacade;
import session.UserFacade;

/**
 *
 * @author Notreal
 */
@Path("order")
public class OrderResource {

    @EJB
    OrderFacade customerOrderFacade;

    @EJB
    UserFacade userFacade;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Order> findAll(@HeaderParam(AuthAccessElement.PARAM_AUTH_TOKEN) String authToken) {
        return userFacade.getCustomerOrderCollection(authToken);
//        User user = userFacade.findByToken(authToken);
//        if (user != null) {
//            return user.getCustomerOrderCollection();
//        } else
//        {
//            return Collections.EMPTY_LIST;
//        }
    }
    
}
