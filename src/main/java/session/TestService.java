/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Route;
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
 * @author Notreal
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TestService {

    @PersistenceContext(name = "aStorePU")
    private EntityManager em;

    @Resource
    private SessionContext context;

    @EJB
    RouteFacade routeFacade;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int createRoute() {
        try {
            Route route = new Route();
            route.setName("testServiceRoute");
            routeFacade.create(route);
//            em.persist(route);
            em.flush();
            return route.getId();
        } catch (Exception e) {
            System.out.println(e);
            context.setRollbackOnly();
            return 0;
        }
    }
}
