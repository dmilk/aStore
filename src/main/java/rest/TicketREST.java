/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import entity.Ticket;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import session.TicketFacade;

/**
 * REST Web Service
 *
 * @author OLEG
 */
@Stateless
@Path("ticket")
public class TicketREST {

    @EJB
    TicketFacade ticketFacade;

    /**
     * Creates a new instance of TicketREST
     */
    public TicketREST() {
    }

    /**
     * Retrieves representation of an instance of rest.TicketREST
     * @return an instance of java.lang.String
     */
    @GET
    @Produces({"application/xml","application/json"})
    public List<Ticket> getXml() {
        //TODO return proper representation object
        return ticketFacade.findAll();
    }

    /**
     * PUT method for updating or creating an instance of TicketREST
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
