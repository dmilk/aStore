package session;

import entity.OrderedTicket;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author OLEG
 */
@Stateless
public class OrderedTicketFacade extends AbstractFacade<OrderedTicket> {
    @PersistenceContext(unitName = "aStorePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderedTicketFacade() {
        super(OrderedTicket.class);
    }
   
}
