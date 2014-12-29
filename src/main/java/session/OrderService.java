package session;

import entity.Order;
import entity.OrderedTicket;
import entity.Route;
import entity.SupportingDocument;
import entity.Ticket;
import entity.User;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    SupportingDocumentFacade supportingDocumentFacade;

    @EJB
    ConfirmationNumberService confirmationNumberService;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int placeOrder(Order order, User user) {
        try {
            List<OrderedTicket> orderedTickets = order.getOrderedTicketCollection();
            Route route = routeFacade.find(order.getRoute().getId());

            if (user == null) {
                user = userFacade.find(0);
            }

            Order newOrder = new Order();
            newOrder.setFirstName(order.getFirstName());
            newOrder.setLastName(order.getLastName());
            newOrder.setEmail(order.getEmail());
            newOrder.setPhone(order.getPhone());

            newOrder.setUser(user);
            newOrder.setRoute(route);
            // m.b. re-calculate after persist?
            newOrder.setAmount(getTolal(orderedTickets));

            int confirmationNumber = confirmationNumberService.get();
            newOrder.setConfirmationNumber(confirmationNumber);

            newOrder.setOrderedTicketCollection(null); // Collections.EMPTY_LIST

            em.persist(newOrder);
            addOrderedItems(newOrder, orderedTickets);
            em.refresh(newOrder);

            return newOrder.getConfirmationNumber();

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

    private void addOrderedItems(Order order, List<OrderedTicket> orderedTickets) throws ParseException {
        em.flush();

        for (OrderedTicket orderedTicket : orderedTickets) {
            OrderedTicket newOrderedTicket = new OrderedTicket();
            Ticket ticket = ticketFacade.find(orderedTicket.getTicket().getId());
            SupportingDocument supportingDocument = supportingDocumentFacade.find(orderedTicket.getSupportingDocument().getId());

            newOrderedTicket.setFirstName(orderedTicket.getFirstName());
            newOrderedTicket.setLastName(orderedTicket.getLastName());
            newOrderedTicket.setMiddleName(orderedTicket.getMiddleName());
            // Date must be parsed from dobString
            DateFormat format = new SimpleDateFormat("yyyy.MM.dd");
            Date date = format.parse(orderedTicket.getDobString());

            newOrderedTicket.setDob(date);

            newOrderedTicket.setOrder(order);
            newOrderedTicket.setTicket(ticket);
            newOrderedTicket.setSupportingDocument(supportingDocument);
            newOrderedTicket.setSupportingDocumentData(orderedTicket.getSupportingDocumentData());

            em.persist(newOrderedTicket);
        }
// add 24/12/14
        em.flush();
    }

}
