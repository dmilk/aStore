/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

//import com.sun.jersey.test.framework.JerseyTest;
import entity.Route;
import java.net.URI;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.external.ExternalTestContainerFactory;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 *
 * @author Notreal
 */
public class TestRESTIT extends JerseyTest {

    private final static Route ROUTE_1;

    static {
        ROUTE_1 = new Route();
        ROUTE_1.setId(1);
        ROUTE_1.setName("One");
    }

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);

        final MyApplication application = new MyApplication();
        application.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        return application;
    }

    @Override
    protected void configureClient(final ClientConfig config) {
        super.configureClient(config);

        config.register(MoxyJsonFeature.class);
    }
    
    @Override
    protected URI getBaseUri() {
        final UriBuilder baseUriBuilder = UriBuilder.fromUri(super.getBaseUri()).path("aStore");
        final boolean externalFactoryInUse = getTestContainerFactory() instanceof ExternalTestContainerFactory;
        return externalFactoryInUse ? baseUriBuilder.path("rest").build() : baseUriBuilder.build();
    }
    
    @Test
    public void testContactDoesNotExist() throws Exception {
        final WebTarget target = target().
                path("test");

        // GET
        System.out.println("target = " + target);
        Response response = target.path("a").request(MediaType.APPLICATION_JSON_TYPE).get();
        System.out.println(response);
        Route route = response.readEntity(Route.class);
        System.out.println(route);

        // http://localhost:8080/aStore/rest/test/t
        assertEquals(200, response.getStatus()); 

//        Set<String> violationsMessageTemplates = getValidationMessageTemplates(response);
//        assertEquals(1, violationsMessageTemplates.size());
//        assertTrue(violationsMessageTemplates.contains("{contact.does.not.exist}"));
//
//        // DELETE
//        response = target.path("1").request(MediaType.APPLICATION_JSON_TYPE).delete();
//
//        assertEquals(500, response.getStatus());
//
//        violationsMessageTemplates = getValidationMessageTemplates(response);
//        assertEquals(1, violationsMessageTemplates.size());
//        assertTrue(violationsMessageTemplates.contains("{contact.does.not.exist}"));
    }


}
