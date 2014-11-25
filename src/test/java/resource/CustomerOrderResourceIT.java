/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import auth.AuthAccessElement;
import entity.CustomerOrder;
import java.util.List;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Notreal
 * mvn failsafe:integration-test -PIT
 */
public class CustomerOrderResourceIT extends MyJerseyIT {
    
    /**
     * Test of findAll method, of class CustomerOrderResource.
     */
    @Test
    public void testFindAll() {
        final WebTarget target = target().
                path("order");
        String authToken = "22642480-7d61-4fd2-9b10-7f03f69fb656";
        
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE).header(AuthAccessElement.PARAM_AUTH_TOKEN, authToken).get();
        assertEquals(200, response.getStatus());
        
        List<CustomerOrder> customerOrderList = response.readEntity(new GenericType<List<CustomerOrder>>() {
        });
        System.out.println(customerOrderList);
        assertFalse(customerOrderList.isEmpty());
    }
    
}
