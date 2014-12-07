package resource;

import entity.Ticket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import session.CategoryFacade;
import session.TicketFacade;

/**
 * REST Web Service
 *
 * @author OLEG
 */
@Path("ticket")
public class TicketResource {
    
    @EJB
    TicketFacade ticketFacade;

    @EJB
    CategoryFacade categoryFacade;

    public TicketResource() {
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Ticket> finaAll() {
        return ticketFacade.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Ticket> findByCategory(@PathParam("id") Integer categoryId) {
        List<Ticket> tickets = new ArrayList<>(categoryFacade.find(categoryId).getTicketCollection());
        Collections.sort(tickets, TicketNameComparator);
        return tickets;
    }

    public static Comparator<Ticket> TicketNameComparator = new Comparator<Ticket>() {
        @Override
        public int compare(Ticket t1, Ticket t2) {
            String ticketName1 = t1.getName();
            String ticketName2 = t2.getName();
            return ticketName1.compareTo(ticketName2);
        }
    };

}
