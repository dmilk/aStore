package session;

import entity.Order;
import entity.OrderedTicket;
import entity.Route;
import entity.User;
import java.math.BigDecimal;
import java.util.List;
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
public class OrderService {

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
    
    @EJB
    ConfirmationNumberService confirmationNumberService;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int placeOrder(Order order, User user) {
        try {
            // check incoming route
            List<OrderedTicket> orderedTickets = order.getOrderedTicketCollection();
            Route route = routeFacade.find(order.getRoute().getId());
            order.setUser(user);
            order.setRoute(route);
            order.setAmount(getTolal(orderedTickets));
            
            int confirmationNumber = confirmationNumberService.get();
            order.setConfirmationNumber(confirmationNumber);

            order.setOrderedTicketCollection(null); // Collections.EMPTY_LIST

            em.persist(order);
            
            addOrderedItems(order, orderedTickets);
            return order.getConfirmationNumber();
            
        } catch (Exception e) {
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

    private void addOrderedItems(Order order, List<OrderedTicket> orderedTickets) {
        em.flush();

        for (OrderedTicket item : orderedTickets) {
            OrderedTicket orderedTicket = new OrderedTicket();
            orderedTicket.setOrder(order);
            orderedTicket.setTicket(item.getTicket());
            orderedTicket.setTicketData(item.getTicketData());

            em.persist(orderedTicket);
        }
    }

}
