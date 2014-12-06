/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import auth.AuthAccessElement;
import entity.Order;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import resource.PurchaseResource.OrderConfirmationJson;

/**
 *
 * @author OLEG
 */
public class PurchaseResourceIT extends MyJerseyIT {
    
    private static Unmarshaller unmarshaller;
    
    @BeforeClass
    public static void oneTimeSetUp() throws JAXBException {
        Map<String, Object> jaxbProperties = new HashMap<>(2);
        jaxbProperties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
        jaxbProperties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
        JAXBContext jc = JAXBContext.newInstance(new Class[]{Order.class},
                jaxbProperties);
        unmarshaller = jc.createUnmarshaller();
        
    }
    

    @Test
    public void testPlaceOrderWoAuthToken() throws JAXBException {
        final WebTarget target = target().
                path("purchase");

        StreamSource json = new StreamSource("profiles/integration-test/order_1.json");
        Order order = unmarshaller.unmarshal(json, Order.class).getValue();
        System.out.println(order);
        
        Entity e = Entity.entity(order, MediaType.APPLICATION_JSON_TYPE);
        System.out.println(e);
        
        Response response = target.
                request(MediaType.APPLICATION_JSON_TYPE).
                post(Entity.entity(order, MediaType.APPLICATION_JSON_TYPE + ";charset=UTF-8;"));
        
        assertEquals(200, response.getStatus());
//        OrderConfirmationJson ocj = response.readEntity(OrderConfirmationJson.class);
//        int confirmationNumber = ocj.orderId;
        String orderConfirmationString = response.readEntity(String.class);
        int confirmationNumber = Integer.valueOf(orderConfirmationString);
        assertTrue(confirmationNumber > 0);
    }
    
    @Test
    public void testPlaceOrderAllTicketsWithAuthToken() throws JAXBException {
        final WebTarget target_purchase = target().
                path("purchase");
        
        final String authToken = "fe3301b3-3eb4-4260-8824-87e5de00928d";
        final Integer confirmationNumber;
        
        StreamSource json = new StreamSource("profiles/integration-test/order_2.json");
        Order order = unmarshaller.unmarshal(json, Order.class).getValue();
        
        Response response = target_purchase.
                request(MediaType.APPLICATION_JSON_TYPE).
                header(AuthAccessElement.PARAM_AUTH_TOKEN, authToken).
                post(Entity.entity(order, MediaType.APPLICATION_JSON_TYPE + ";charset=UTF-8;"));
        
        assertEquals(200, response.getStatus());
        confirmationNumber = response.readEntity(Integer.class);
        
// check order
        final WebTarget target_order = target().
                path("order");

        response = target_order.
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
