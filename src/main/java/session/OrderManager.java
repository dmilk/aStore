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
public class OrderManager {
    
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
    public int placeOrder(String firstName, String lastName, String email, String phone, int userId, int routeId, 
            ShoppingCart cart) 
    {
        try {
            Route route = routeFacade.find(routeId);
            User user = userFacade.find(userId);
            Order customerOrder = addOrder(firstName, lastName, email, phone, user, route, cart);
            addOrderedItems(customerOrder, cart);
            return customerOrder.getId();
        } catch (Exception e) {
            System.out.println(e);
            context.setRollbackOnly();
            return 0;
        }
    }

    private Order addOrder(String firstName, String lastName, String email, String phone, User user, 
            Route route, ShoppingCart cart) 
    {
        Order customerOrder = new Order();
        customerOrder.setFirstName(firstName);
        customerOrder.setLastName(lastName);
        customerOrder.setEmail(email);
        customerOrder.setPhone(phone);
        customerOrder.setUser(user);
        customerOrder.setRoute(route);
        customerOrder.setAmount(BigDecimal.valueOf(cart.getTotal()));
        
        Random random = new Random();
        int confirmationNumber = random.nextInt(Integer.MAX_VALUE);
        customerOrder.setConfirmationNumber(confirmationNumber);
        
        em.persist(customerOrder);
        
        return customerOrder;
    }
    
    private void addOrderedItems(Order customerOrder, ShoppingCart cart) {
        em.flush();
        
        List<ShoppingCartItem> items = cart.getItems();
        for(ShoppingCartItem item : items) {
            int ticketId = item.getTicket().getId();
            
            OrderedTicket orderedTicket = new OrderedTicket();
            orderedTicket.setOrder(customerOrder);
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
       
        for (OrderedTicket orderedTicket:  orderedTickets) {
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
