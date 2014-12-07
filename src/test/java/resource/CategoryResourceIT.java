package resource;

import entity.Category;
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
public class CategoryResourceIT extends MyJerseyIT {
    
    /**
     * Test of findAll method, of class CategoryResource.
     */
    @Test
    public void testFindAll() {
        final WebTarget target = target().
                path("category");

        Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();

        assertEquals(200, response.getStatus());
        List<Category> categories = response.readEntity(new GenericType<List<Category>>() {});
        assertTrue(categories.size() > 0);
        
        response = target.request(MediaType.APPLICATION_XML_TYPE).get();
        assertEquals(200, response.getStatus());
    }
    
}
