/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import auth.AuthAccessElement;
import cart.ShoppingCart;
import entity.Order;
import entity.Ticket;
import entity.User;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import session.OrderManager;
import session.TicketFacade;
import session.UserFacade;

/**
 * REST Web Service
 *
 * @author OLEG
 */
@Path("purchase")
public class PurchaseResource {

    @EJB
    private OrderManager orderManager;

    @EJB
    private TicketFacade ticketFacade;
    
    @EJB
    private UserFacade userFacade;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
//    public OrderConfirmationJson placeOrder(@Context HttpServletRequest request, OrderJson orderJson) {
    public OrderConfirmationJson placeOrder(@Context HttpServletRequest request, Order order) {        
        int orderId = 0;
        int userId = 0;
        String authToken = request.getHeader(AuthAccessElement.PARAM_AUTH_TOKEN);
        User user = userFacade.findByToken(authToken);
        if (user != null)
            userId = user.getId();
        
        System.out.println(order);
        return new OrderConfirmationJson(1);
        
//        orderId = orderManager.placeOrder(orderJson.firstName, orderJson.lastName, orderJson.email, orderJson.phone,
//                userId, orderJson.routeId, convertJsonCart(orderJson.cart));
//
//        Map orderMap = orderManager.getOrderDetails(orderId);
//         return new OrderConfirmationJson(((Order) orderMap.get("orderRecord")).getConfirmationNumber());
    }

    private ShoppingCart convertJsonCart(List<TicketJson> tickets) {
        ShoppingCart shoppingCart = new ShoppingCart();
        for (TicketJson ticketJson : tickets) {
            Ticket ticket = ticketFacade.find(ticketJson.id);
            shoppingCart.addItem(ticket, ticketJson.ticketData);
        }
        return shoppingCart;
    }

    public PurchaseResource() {
    }

    public static class OrderJson {

        public String firstName;
        public String lastName;
        public String email;
        public String phone;
//        public int userId;
        public int routeId;
        public List<TicketJson> cart;

        public OrderJson() {
        }
    }

    public static class TicketJson {

        public int id;
        public String ticketData;

        public TicketJson() {
        }

        public TicketJson(int id, String ticketData) {
            this.id = id;
            this.ticketData = ticketData;
        }

    }

    public static class OrderConfirmationJson {

        public int orderId;

        public OrderConfirmationJson() {
        }

        public OrderConfirmationJson(int orderId) {
            this.orderId = orderId;
        }

        @Override
        public String toString() {
            return "OrderConfirmationJson{" + "orderId=" + orderId + '}';
        }
    }

}
