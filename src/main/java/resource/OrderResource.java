package resource;

import auth.AuthAccessElement;
import entity.Order;
import java.util.Collection;
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
    OrderFacade orderFacade;

    @EJB
    UserFacade userFacade;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Order> findAll(@HeaderParam(AuthAccessElement.PARAM_AUTH_TOKEN) String authToken) {
        return userFacade.getOrderCollection(authToken);
    }

    @GET
    @Path("xdrlkjhgksdtjhkjkthlkhkrthprtfhrturtyuhjrtujrtudrhywrtuwr6ju")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Order> findByRoute() {
        return orderFacade.findAll1();
    }
    
}
