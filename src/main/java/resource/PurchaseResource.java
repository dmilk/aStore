package resource;

import auth.AuthAccessElement;
import entity.Order;
import entity.User;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;
import session.OrderService;
import session.UserFacade;

/**
 * REST Web Service
 *
 * @author OLEG
 */
@Path("purchase")
public class PurchaseResource {

    @EJB
    private OrderService orderService;

    @EJB
    private UserFacade userFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public ConfirmationNumber placeOrder(@Context HttpServletRequest request, Order order) {        
        String authToken = request.getHeader(AuthAccessElement.PARAM_AUTH_TOKEN);
        User user = userFacade.findByToken(authToken);
        
        int confirmationNumber = orderService.placeOrder(order, user);
        
        return new ConfirmationNumber(confirmationNumber);
    }
    
    static class ConfirmationNumber {
        public int orderId;

        public ConfirmationNumber() {
        }

        public ConfirmationNumber(int orderId) {
            this.orderId = orderId;
        }
    }

}
