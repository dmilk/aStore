package resource;

import entity.Ticket;
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
public class TicketResourceIT extends MyJerseyIT {
    /**
     * Test of finaAll method, of class TicketResource.
     */
    @Test
    public void testFinaAll() {
        final WebTarget target = target().
                path("ticket");

        Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();

        assertEquals(200, response.getStatus());
        List<Ticket> tickets = response.readEntity(new GenericType<List<Ticket>>() {
        });
        assertTrue(tickets.size() > 0);

        response = target.request(MediaType.APPLICATION_XML_TYPE).get();
        assertEquals(200, response.getStatus());
    }

    /**
     * Test of findByCategory method, of class TicketResource.
     */
    @Test
    public void testFindByCategory() {
        final WebTarget target = target().
                path("ticket");
        
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();

        assertEquals(200, response.getStatus());
        List<Ticket> tickets = response.readEntity(new GenericType<List<Ticket>>() {
        });
        Integer ticketsSize = tickets.size();
        assertTrue(ticketsSize > 0);
        
        response = target.path("1").request(MediaType.APPLICATION_JSON_TYPE).get();
        tickets = response.readEntity(new GenericType<List<Ticket>>() {
        });
        assertTrue(tickets.size() > 0);
        
        // Not found
        response = target.path(ticketsSize.toString()).request(MediaType.APPLICATION_JSON_TYPE).get();
        assertEquals(500, response.getStatus());
    }

}
