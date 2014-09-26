/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import cart.ShoppingCart;
import cart.ShoppingCartItem;
import entity.Customer;
import entity.CustomerOrder;
import entity.OrderedTicket;
import entity.OrderedTicketPK;
import entity.Route;
import entity.Ticket;
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
    CustomerOrderFacade customerOrderFacade;
    
    @EJB
    OrderedTicketFacade orderedTicketFacade;
    
    @EJB
    TicketFacade ticketFacade;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int placeOrder(String first_name, String last_name, String email, String phone, int routeId, ShoppingCart cart) {
        try {
            Customer customer = addCustomer(first_name, last_name, email, phone);
            Route route = routeFacade.find(routeId);
            CustomerOrder customerOrder = addOrder(customer, route, cart);
            addOrderedItems(customerOrder, cart);
            return customerOrder.getId();
        } catch (Exception e) {
            context.setRollbackOnly();
            return 0;
        }
    }

    private Customer addCustomer(String first_name, String last_name, String email, String phone) {
        Customer customer = new Customer();
        customer.setFirstName(first_name);
        customer.setLastName(last_name);
        customer.setEmail(email);
        customer.setPhone(phone);
        
        em.persist(customer);
        
        return customer;
    }
    
    private CustomerOrder addOrder(Customer customer, Route route, ShoppingCart cart) {
        CustomerOrder order = new CustomerOrder();
        order.setCustomer(customer);
        order.setRoute(route);
        order.setAmount(BigDecimal.valueOf(cart.getTotal()));
        
        Random random = new Random();
        int cn = random.nextInt(Integer.MAX_VALUE);
        order.setConfirmationNumber(cn);
        
        em.persist(order);
        
        return order;
    }
    
    private void addOrderedItems(CustomerOrder order, ShoppingCart cart) {
        em.flush();
        
        List<ShoppingCartItem> items = cart.getItems();
        for(ShoppingCartItem item : items) {
            int ticketId = item.getTicket().getId();
            
            OrderedTicketPK orderedTicketPK = new OrderedTicketPK();
            orderedTicketPK.setCustomerOrderId(order.getId());
            orderedTicketPK.setTicketId(ticketId);
            
            OrderedTicket orderedTicket = new OrderedTicket(orderedTicketPK);
            orderedTicket.setTicketData(item.getTicketData());
            
            em.persist(orderedTicket);
        }
    }
    
    public Map getOrderDetails(int orderId) {
        Map orderMap = new HashMap();
        
        CustomerOrder order = customerOrderFacade.find(orderId);
        
        Customer customer = order.getCustomer();
        
        List<OrderedTicket> orderedTickets = orderedTicketFacade.findByOrderId(orderId);
        
        Route route = order.getRoute();

        List<Ticket> tickets = new ArrayList<Ticket>();
       
        for (OrderedTicket orderedTicket:  orderedTickets) {
            Ticket ticket = ticketFacade.find(orderedTicket.getOrderedTicketPK().getTicketId());
            tickets.add(ticket);
        }
        
        orderMap.put("orderRecord", order);
        orderMap.put("customer", customer);
        orderMap.put("route", route);
        orderMap.put("orderedTickets", orderedTickets);
        orderMap.put("tickets", tickets);
                
        return orderMap;
    }

}