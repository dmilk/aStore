/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import entity.Ticket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import session.CategoryFacade;
import session.TicketFacade;

/**
 * REST Web Service
 *
 * @author OLEG
 */
@Stateless
@Path("ticket")
public class TicketREST {
    
//    @PersistenceContext(unitName = "aStorePU")
//    private EntityManager em;

    @EJB
    TicketFacade ticketFacade;

    @EJB
    CategoryFacade categoryFacade;

    /**
     * Creates a new instance of TicketREST
     */
    public TicketREST() {
    }

    /**
     * Retrieves representation of an instance of rest.TicketREST
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public List<Ticket> finaAll() {
        //TODO return proper representation object
        return ticketFacade.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Collection<Ticket> findByCategory(@PathParam("id") Integer categoryId) {
        List<Ticket> tickets = new ArrayList<Ticket>(categoryFacade.find(categoryId).getTicketCollection());
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
