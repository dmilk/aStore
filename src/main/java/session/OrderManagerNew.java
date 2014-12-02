/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import cart.ShoppingCart;
import cart.ShoppingCartItem;
import entity.Order;
import entity.OrderedTicket;
import entity.Route;
import entity.Ticket;
import entity.User;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author OLEG
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class OrderManagerNew {

    @PersistenceContext(name = "aStorePU")
    private EntityManager em;

    @Resource
    private SessionContext context;

    @EJB
    RouteFacade routeFacade;

    @EJB
    UserFacade userFacade;

    @EJB
    OrderFacade orderFacade;

    @EJB
    OrderedTicketFacade orderedTicketFacade;

    @EJB
    TicketFacade ticketFacade;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
//    public int placeOrder(String firstName, String lastName, String email, String phone, int userId, int routeId, 
//            List<OrderedTicket> orderedTickets) 
    public int placeOrder(Order order, User user) {
        try {
            // check incoming route
            List<OrderedTicket> orderedTickets = order.getOrderedTicketCollection();
            Route route = routeFacade.find(order.getRoute().getId());
            order.setUser(user);
            order.setRoute(route);
            order.setAmount(getTolal(orderedTickets));
            
            Random random = new Random();
            int confirmationNumber = random.nextInt(Integer.MAX_VALUE);
            order.setConfirmationNumber(confirmationNumber);
            order.setOrderedTicketCollection(null);

            em.persist(order);
            
            addOrderedItems(order, orderedTickets);
            return order.getId();
            

//            Route route = routeFacade.find(routeId);
//            User user = userFacade.find(userId);
//            Order order = addOrder(firstName, lastName, email, phone, user, route, orderedTickets);
//            addOrderedItems(order, orderedTickets);
//            return order.getId();
        } catch (Exception e) {
            System.out.println(e);
            context.setRollbackOnly();
            return 0;
        }
    }

    private BigDecimal getTolal(List<OrderedTicket> orderedTickets) {
        BigDecimal total = new BigDecimal(0);
        for (OrderedTicket orderedTicket : orderedTickets) {
            total = total.add(orderedTicket.getTicket().getPrice());
        }
        return total;

    }

    private Order addOrder(String firstName, String lastName, String email, String phone, User user,
            Route route, List<OrderedTicket> orderedTickets) {
        Order order = new Order();
        order.setFirstName(firstName);
        order.setLastName(lastName);
        order.setEmail(email);
        order.setPhone(phone);
        order.setUser(user);
        order.setRoute(route);
        order.setAmount(BigDecimal.valueOf(1));
//        order.setAmount(BigDecimal.valueOf(cart.getTotal()));

        Random random = new Random();
        int confirmationNumber = random.nextInt(Integer.MAX_VALUE);
        order.setConfirmationNumber(confirmationNumber);

        em.persist(order);

        return order;
    }

    private void addOrderedItems(Order order, List<OrderedTicket> orderedTickets) {
        em.flush();

//        List<ShoppingCartItem> items = cart.getItems();
//        for(ShoppingCartItem item : items) {
        for (OrderedTicket item : orderedTickets) {
            int ticketId = item.getTicket().getId();

            OrderedTicket orderedTicket = new OrderedTicket();
            orderedTicket.setOrder(order);
            orderedTicket.setTicket(item.getTicket());
            orderedTicket.setTicketData(item.getTicketData());

            em.persist(orderedTicket);
        }
    }

    public Map getOrderDetails(int orderId) {
        Map orderMap = new HashMap();

        Order customerOrder = orderFacade.find(orderId);

        List<OrderedTicket> orderedTickets = customerOrder.getOrderedTicketCollection();

        Route route = customerOrder.getRoute();

        List<Ticket> tickets = new ArrayList<Ticket>();

        for (OrderedTicket orderedTicket : orderedTickets) {
            Ticket ticket = orderedTicket.getTicket();
            tickets.add(ticket);
        }

        orderMap.put("orderRecord", customerOrder);
        orderMap.put("route", route);
        orderMap.put("orderedTickets", orderedTickets);
        orderMap.put("tickets", tickets);

        return orderMap;
    }

}
