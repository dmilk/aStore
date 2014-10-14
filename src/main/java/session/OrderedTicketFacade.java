/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.OrderedTicket;
import java.util.List;
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
    
//    public List<OrderedTicket> findByOrderId(Object id) {
//        return em.createNamedQuery("OrderedTicket.findByCustomerOrderId").setParameter("customerOrderId", id).getResultList();
//    }
    
}
