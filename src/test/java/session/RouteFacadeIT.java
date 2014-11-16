/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Route;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Notreal
 */
public class RouteFacadeIT {

    private static EJBContainer container;

    public RouteFacadeIT() {
    }

    @BeforeClass
    public static void setUpClass() {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        System.out.println("Opening the container");

    }

    @AfterClass
    public static void tearDownClass() {
        container.close();
        System.out.println("Closing the container");

    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class RouteFacade.
     */
    @Test
    @Ignore
    public void testCreate() throws Exception {
        System.out.println("create");
        Route entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        RouteFacade instance = (RouteFacade) container.getContext().lookup("java:global/classes/RouteFacade");
        instance.create(entity);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of edit method, of class RouteFacade.
     */
    @Test
    @Ignore
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
    @Test
    @Ignore
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
    @Test
    public void testFind() throws Exception {
        System.out.println("find");
        Object id = 1;
        RouteFacade instance = (RouteFacade) container.getContext().lookup("java:global/classes/RouteFacade");
        String expName = "kavkaz_krym"; 
        Route result = instance.find(id);
        System.out.println("result: " + result);
        assertEquals(expName, result.getName());
    }

    /**
     * Test of findAll method, of class RouteFacade.
     */
    @Test
    @Ignore
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
    @Test
    @Ignore
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
