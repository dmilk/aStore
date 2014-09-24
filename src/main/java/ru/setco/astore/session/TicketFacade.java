/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.setco.astore.session;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Root;
import ru.setco.astore.entity.Category;
import ru.setco.astore.entity.Ticket;
import ru.setco.astore.entity.Ticket_;

/**
 *
 * @author OLEG
 */
@Stateless
public class TicketFacade extends AbstractFacade<Ticket> {
    @PersistenceContext(unitName = "aStorePU")
    private EntityManager em;
    
    @EJB
    CategoryFacade cf;
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TicketFacade() {
        super(Ticket.class);
    }
    
    public List<Ticket> findByCategory(Integer id) {
        System.out.println("********************");
        javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery(Ticket.class);
        Root<Ticket> ticket = cq.from(Ticket.class);
        Category c = cf.find(id);
        cq.where(cb.equal(ticket.get(Ticket_.categoryId), c));
        return getEntityManager().createQuery(cq).getResultList(); 
    }
    
}
