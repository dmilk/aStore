/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import entity.Route;
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
 */
public class RouteResourceIT extends MyJerseyIT {
    
    @Test
    public void testFindAll() throws Exception {
        final WebTarget target = target().
                path("route");

        Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();

        assertEquals(200, response.getStatus());
        List<Route> routes = response.readEntity(new GenericType<List<Route>>() {
        });
        assertTrue(routes.size() > 0);

        response = target.request(MediaType.APPLICATION_XML_TYPE).get();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testCreateRoute() throws Exception {
        final WebTarget target = target().
                path("route");
        
        Response response = target.path("new").request(MediaType.APPLICATION_JSON_TYPE).get();
        assertEquals(200, response.getStatus());
        
        final Route route = response.readEntity(Route.class);
        assertNotNull(route.getId());
        
        assertEquals(204, target.path("" + route.getId()).request(MediaType.APPLICATION_JSON_TYPE).delete().getStatus());
    }
    
}
