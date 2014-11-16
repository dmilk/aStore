/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import entity.Route;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Notreal
 */
public class TestRESTTest {
    Route route;
    
    public TestRESTTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        System.out.println("setUp");
        route = new Route(1, "test");
    }
    
    @After
    public void tearDown() {
        System.out.println("tearDown");
        route = null;
    }

    /**
     * Test of test1 method, of class TestREST.
     */
    @Test
    public void testTest1() {
//        System.out.println("test1");
//        HttpServletRequest request = null;
//        TestREST instance = new TestREST();
//        String expResult = "";
//        String result = instance.test1(request);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of testAll method, of class TestREST.
     */
    @Test
    public void testTestAll() {
//        System.out.println("testAll");
//        TestREST instance = new TestREST();
//        Route expResult = route;
//        Route result = instance.testAll();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of testRole method, of class TestREST.
     */
    @Test
    public void testTestRole() {
//        System.out.println("testRole");
//        TestREST instance = new TestREST();
//        String expResult = "";
//        String result = instance.testRole();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of testRoleAm method, of class TestREST.
     */
    @Test
    public void testTestRoleAm() {
//        System.out.println("testRoleAm");
//        TestREST instance = new TestREST();
//        Route expResult = null;
//        Route result = instance.testRoleAm();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of testRole2 method, of class TestREST.
     */
    @Test
    public void testTestRole2() {
//        System.out.println("testRole2");
//        TestREST instance = new TestREST();
//        Cat expResult = null;
//        Cat result = instance.testRole2();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
