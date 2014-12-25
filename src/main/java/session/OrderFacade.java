package session;

import entity.Order;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author OLEG
 */
@Stateless
public class OrderFacade extends AbstractFacade<Order> {
    @PersistenceContext(unitName = "aStorePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderFacade() {
        super(Order.class);
    }
    
    public Collection<Order> findAll1() {
        return em.createNamedQuery("Order.findAll1").getResultList();
    }
    
}
