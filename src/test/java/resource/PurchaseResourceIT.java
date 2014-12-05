/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Order;
import entity.OrderedTicket;
import entity.Route;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.client.WebTarget;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author OLEG
 */
public class PurchaseResourceIT extends MyJerseyIT {

    @Test
    public void testPlaceOrder() throws Exception {
        final WebTarget target = target().
                path("purchase");

//        File file = new File("xxx");
//        try (FileOutputStream output = new FileOutputStream(file)) {
//            file.createNewFile();
//            output.flush();
//            output.close();
//        }
        ObjectMapper mapper = new ObjectMapper();
        Route route = new Route(5);
        route.setName("555");
        mapper.writeValue(new File("route.json"), route);
        Route route2 = mapper.readValue(new File("route.json"), Route.class);
        System.out.println("route = " + route2);
        
//        OrderedTicket orderedTicket = mapper.readValue(new File("profiles/integration-test/order_1.json"), OrderedTicket.class);
        Order order =  mapper.readValue(new File("profiles/integration-test/order_1.json"), Order.class);
        System.out.println(order);

        try (FileInputStream is = new FileInputStream("profiles/integration-test/order_1.json")) {

            Map<String, Object> jaxbProperties = new HashMap<>(2);
            jaxbProperties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");

            jaxbProperties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);

            JAXBContext jaxbContext = JAXBContext.newInstance(Route.class);
            //Customer customer = (Customer) jaxbContext.createUnmarshaller().unmarshal(instanceDoc);

            route = new Route(5);
            route.setName("555");

            Marshaller m = jaxbContext.createMarshaller();
//            m.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");

            m.marshal(route, System.out);

//            System.out.println(orderedTicket.getTicketData());
//            JsonReader jsonReader = Json.createReader(is);
//
//            JsonObject jsonObject = jsonReader.readObject();
//            System.out.println(jsonObject);
//            
//            OrderedTicket orderedTicket = (OrderedTicket) unmarshaller.unmarshal(is);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertEquals(200, 200);
        //JsonObject json = Json.createParser(is)
        //Response r = target.path("").request().po

//        Response response = target.
//                path("signup").
//                request(MediaType.APPLICATION_JSON_TYPE).
//                post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
//        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}
