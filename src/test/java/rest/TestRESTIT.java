/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

//import com.sun.jersey.test.framework.JerseyTest;
import java.net.URI;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Notreal
 */
public class TestRESTIT {

    public static final URI BASE_URI = UriBuilder.fromUri("http://localhost").port(8081).build();
    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {

        ResourceConfig rc = new ResourceConfig(TestREST.class);
        server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);

        server.start();
        Client c = ClientBuilder.newClient();
        target = c.target(BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testT() {
        System.out.println("test");
        String responseMsg = target.path("test/t").request().get(String.class);
        System.out.println(responseMsg);
        assertEquals("Hello", responseMsg);
    }
    
}
