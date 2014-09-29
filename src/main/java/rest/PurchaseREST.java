/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import cart.ShoppingCart;
import entity.CustomerOrder;
import entity.Ticket;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import session.OrderManager;
import session.TicketFacade;

/**
 * REST Web Service
 *
 * @author OLEG
 */
@Path("purchase")
public class PurchaseREST {

    @EJB
    private OrderManager orderManager;

    @EJB
    private TicketFacade ticketFacade;

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public OrderConfirmationJson placeOrder(OrderJson orderJson) {
        System.out.println("OrderJson\n");
        System.out.println(orderJson);

        int orderId = 0;
        orderId = orderManager.placeOrder(orderJson.firstName, orderJson.secondName, orderJson.email, orderJson.phone,
                orderJson.routeId, convertJsonCart(orderJson.cart));

        Map orderMap = orderManager.getOrderDetails(orderId);
        return new OrderConfirmationJson(((CustomerOrder) orderMap.get("orderRecord")).getConfirmationNumber());
    }

    private ShoppingCart convertJsonCart(List<TicketJson> tickets) {
        ShoppingCart shoppingCart = new ShoppingCart();
        for (TicketJson ticketJson : tickets) {
            Ticket ticket = ticketFacade.find(ticketJson.id);
            shoppingCart.addItem(ticket, ticketJson.ticketData);
        }
        return shoppingCart;
    }

    public PurchaseREST() {
    }

    public static class OrderJson {

        public String firstName;
        public String secondName;
        public String email;
        public String phone;
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
