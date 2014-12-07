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

}
