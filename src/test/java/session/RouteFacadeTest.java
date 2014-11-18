/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Route;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Notreal
 */
public class RouteFacadeTest {

    private RouteFacade routeFacade;
    private EntityManager em;
    private Route route;
    private List<Route> routeList;
    private CriteriaBuilder criteriaBuilder;
    private javax.persistence.criteria.CriteriaQuery criteriaQuery;
    private TypedQuery<Route> typedQuery;

    public RouteFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        em = mock(EntityManager.class);
        routeFacade = new RouteFacade();
        routeFacade.setEm(em);
        
        criteriaBuilder = mock(CriteriaBuilder.class);
        when(em.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        
        criteriaQuery = mock(javax.persistence.criteria.CriteriaQuery.class);
        when(em.getCriteriaBuilder().createQuery()).thenReturn(criteriaQuery);
        
        typedQuery = mock(TypedQuery.class);
        when(em.createQuery(criteriaQuery)).thenReturn(typedQuery);
        
        route = new Route();
        routeList = new ArrayList<>(Arrays.asList(
                new Route(1),
                new Route(2),
                new Route(3)
        ));
        
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class RouteFacade.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCreate() throws Exception {
        routeFacade.create(route);
        verify(em).persist(route);
    }

    /**
     * Test of edit method, of class RouteFacade.
     */
    @Test
    public void testEdit() throws Exception {
        routeFacade.edit(route);
        verify(em).merge(route);
    }

    /**
     * Test of remove method, of class RouteFacade.
     */
    @Test
    public void testRemove() throws Exception {
        routeFacade.remove(route);
        verify(em).remove(em.merge(route));
    }

    /**
     * Test of find method, of class RouteFacade.
     */
    @Test
    public void testFind() throws Exception {
        Route newRoute = new Route(7);

        when(em.find(Route.class, 7)).thenReturn(newRoute);
        Route expResult = em.find(Route.class, 7);

        verify(em).find(Route.class, 7);
        assertEquals(expResult, newRoute);
    }

    /**
     * Test of findAll method, of class RouteFacade.
     */
    @Test
    public void testFindAll() throws Exception {
        EntityManager em = routeFacade.getEntityManager();

        List<Route> list = new ArrayList<>();
        list.add(new Route());
        list.add(new Route());

        when(em.createQuery(criteriaQuery).getResultList()).thenReturn(list);

        List<Route> expResult = routeFacade.findAll();
        System.out.println(expResult);
        verify(em, times(2)).getCriteriaBuilder();
        verify(criteriaBuilder).createQuery();
        verify(em, times(2)).createQuery(criteriaQuery);

        verify(typedQuery).getResultList();
        assertEquals(expResult, list);
    }
}
