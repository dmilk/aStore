/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import auth.AuthAccessElement;
import auth.AuthInfo;
import auth.Salt;
import entity.User;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Notreal
 */
public class UserResourceIT extends MyJerseyIT {

    /**
     * Test of signup method, of class UserResource.
     */
    @Test
    public void testSignup() {
        final WebTarget target = target().
                path("user");
        
        final User user = new User();
        user.setFirstName("testName");
        user.setLastName("testName2");
        user.setEmail("test@test");
        user.setPhone("123-123-123");
        
        Response response = target.
                path("signup").
                request(MediaType.APPLICATION_JSON_TYPE).
                post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    /**
     * Test of setPassword method, of class UserResource.
     */
    @Test
    public void testSetPassword() {
        final WebTarget target = target().
                path("user");

        final AuthInfo invalidAuthInfo = new AuthInfo("am@am", "123");
        Response response = target.
                path("password").
                request(MediaType.APPLICATION_JSON_TYPE).
                post(Entity.entity(invalidAuthInfo, MediaType.APPLICATION_JSON_TYPE));
        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());

        final AuthInfo invalidAuthInfo2 = new AuthInfo("", "");
        response = target.
                path("password").
                request(MediaType.APPLICATION_JSON_TYPE).
                post(Entity.entity(invalidAuthInfo2, MediaType.APPLICATION_JSON_TYPE));
        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    /**
     * Test of getSalt method, of class UserResource.
     */
    @Test
    public void testGetSalt() {
        final WebTarget target = target().
                path("user");

        final String someEmail = "1@1";
        final String existEmail = "oleg@setco.ru";
        final String existSalt = "17ffad89-7e96-4538-98ab-be0f01c7eb8b";

        Response response = target.path("salt").queryParam("email", someEmail).request(MediaType.APPLICATION_JSON_TYPE).get();
        assertEquals(200, response.getStatus());

        Salt salt1 = response.readEntity(Salt.class);
        System.out.println("salt1 =" + salt1);

        response = target.path("salt").queryParam("email", someEmail).request(MediaType.APPLICATION_JSON_TYPE).get();
        assertEquals(200, response.getStatus());

        Salt salt2 = response.readEntity(Salt.class);
        System.out.println("salt2 =" + salt2);

        assertEquals(salt1, salt2);

        response = target.path("salt").queryParam("email", existEmail).request(MediaType.APPLICATION_JSON_TYPE).get();
        assertEquals(200, response.getStatus());

        salt1 = response.readEntity(Salt.class);
        System.out.println("salt1 =" + salt1);

        assertEquals(salt1, new Salt(existSalt));
    }

    /**
     * Test of login method, of class UserResource.
     */
    @Test
    public void testLogin() {
        final WebTarget target = target().
                path("user");

        final AuthInfo authInfo = new AuthInfo("am@am", "34b1fdfe553ebdbe30c08a3b7760f6fda066cfaab8948fbec3dc045d116ebb83");

        Response response = target.
                path("login").
                request(MediaType.APPLICATION_JSON_TYPE).
                post(Entity.entity(authInfo, MediaType.APPLICATION_JSON_TYPE));
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        final AuthInfo invalidAuthInfo = new AuthInfo("am@am", "");
        response = target.
                path("login").
                request(MediaType.APPLICATION_JSON_TYPE).
                post(Entity.entity(invalidAuthInfo, MediaType.APPLICATION_JSON_TYPE));
        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());

        final AuthInfo invalidAuthInfo2 = new AuthInfo("", "");
        response = target.
                path("login").
                request(MediaType.APPLICATION_JSON_TYPE).
                post(Entity.entity(invalidAuthInfo2, MediaType.APPLICATION_JSON_TYPE));
        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    /**
     * Test of getUserInfo method, of class UserResource.
     */
    @Test
    public void testGetUserInfo() {
        // {"email":"oleg@setco.ru","firstName":"Олег","lastName":"Сорокин","phone":"7755529"}
        final WebTarget target = target().
                path("user");
        String authToken = "22642480-7d61-4fd2-9b10-7f03f69fb656";

        Response response = target.path("info").request(MediaType.APPLICATION_JSON_TYPE).header(AuthAccessElement.PARAM_AUTH_TOKEN, authToken).get();
        assertEquals(200, response.getStatus());

        System.out.println(response);
        User user = response.readEntity(User.class);
        assertNotNull(user);

        //should not be NULL
        assertNotNull(user.getEmail());
        assertNotNull(user.getFirstName());
        assertNotNull(user.getLastName());
        assertNotNull(user.getPhone());

        //should be NULL
        assertNull(user.getId());
        assertFalse(user.getActive());
        assertNull(user.getCustomerOrderCollection());
        assertNull(user.getDateCreated());
        assertNull(user.getPassword());
        assertNull(user.getRoleCollection());
        assertNull(user.getSalt());
        assertNull(user.getToken());
    }

    /**
     * Test of getUuid method, of class UserResource.
     */
//    @Test
//    @Ignore
//    public void testGetUuid() {
//        System.out.println("getUuid");
//        UserResource instance = new UserResource();
//        String expResult = "";
//        String result = instance.getUuid();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
