/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import auth.AuthAccessElement;
import entity.Order;
import java.util.List;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Notreal mvn failsafe:integration-test -PIT
 */
public class OrderResourceIT extends MyJerseyIT {

    /**
     * Test of findAll method, of class CustomerOrderResource.
     */
    @Test
    public void testFindAll() {
        final WebTarget target = target().
                path("order");
        String authToken = "fe3301b3-3eb4-4260-8824-87e5de00928d";

        Response response = target.
                request(MediaType.APPLICATION_JSON_TYPE).
                header(AuthAccessElement.PARAM_AUTH_TOKEN, authToken).
                get();

        assertEquals(200, response.getStatus());

        List<Order> customerOrderList = response.readEntity(new GenericType<List<Order>>() {
        });
        System.out.println(customerOrderList);
        assertFalse(customerOrderList.isEmpty());
    }

}
