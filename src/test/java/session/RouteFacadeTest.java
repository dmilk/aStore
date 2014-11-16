/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Route;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Notreal
 */
public class RouteFacadeTest {

    @Mock
    RouteFacade routeFacade;
    
    @Mock
    EntityManager em;

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
        //routeFacade = new RouteFacade();
        em = mock(EntityManager.class);
        //routeFacade.setEm(em);
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
        Query query = mock(Query.class);
        when(em.createNamedQuery("Route.findAll")).thenReturn(query);
        
        //when(em.createNamedQuery("Route.findAll", Route.class)).thenReturn(query);
        
        List<Route> dummyResult = new ArrayList<Route>();
        when(query.getResultList()).thenReturn(dummyResult);
        
        List<Route> result = routeFacade.findAll();
        
        verify(em).createNamedQuery("Role.findAll", Route.class);
        verify(query).getResultList();
        assertSame(dummyResult, result);
    }

    /**
     * Test of edit method, of class RouteFacade.
     */
    @Ignore
    @Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        Route entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        RouteFacade instance = (RouteFacade) container.getContext().lookup("java:global/classes/RouteFacade");
        instance.edit(entity);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class RouteFacade.
     */
    @Ignore
    @Test
    public void testRemove() throws Exception {
        System.out.println("remove");
        Route entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        RouteFacade instance = (RouteFacade) container.getContext().lookup("java:global/classes/RouteFacade");
        instance.remove(entity);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class RouteFacade.
     */
    @Ignore
    @Test
    public void testFind() throws Exception {

    }

    /**
     * Test of findAll method, of class RouteFacade.
     */
    @Ignore
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        RouteFacade instance = (RouteFacade) container.getContext().lookup("java:global/classes/RouteFacade");
        List<Route> expResult = null;
        List<Route> result = instance.findAll();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findRange method, of class RouteFacade.
     */
    @Ignore
    @Test
    public void testFindRange() throws Exception {
        System.out.println("findRange");
        int[] range = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        RouteFacade instance = (RouteFacade) container.getContext().lookup("java:global/classes/RouteFacade");
        List<Route> expResult = null;
        List<Route> result = instance.findRange(range);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of count method, of class RouteFacade.
     */
    @Test
    @Ignore
    public void testCount() throws Exception {
        System.out.println("count");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        RouteFacade instance = (RouteFacade) container.getContext().lookup("java:global/classes/RouteFacade");
        int expResult = 0;
        int result = instance.count();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
