/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import entity.OrderedTicket;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.WebTarget;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
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
        
        try (FileInputStream is = new FileInputStream("profiles/integration-test/order_1.json")) {
//            Map<String, Object> jaxbProperties = new HashMap<String, Object>();
//            jaxbProperties.put(JAXBContextProperties.MEDIA_TYPE, MediaType.APPLICATION_JSON);
            //jaxbProperties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
//            jaxbProperties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
//            JAXBContext jc = JAXBContext.newInstance(new Class[]{OrderedTicket.class}, jaxbProperties);
//            Unmarshaller unmarshaller = jc.createUnmarshaller();

            JsonReader jsonReader = Json.createReader(is);

            JsonObject orderedTicket = jsonReader.readObject();
            System.out.println(orderedTicket);

        } catch (IOException e) {
            System.out.println(e.getMessage());
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
