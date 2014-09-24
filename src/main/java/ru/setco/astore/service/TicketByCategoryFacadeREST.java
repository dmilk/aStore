/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.setco.astore.service;

import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import ru.setco.astore.entity.Category;
import ru.setco.astore.entity.Ticket;
import ru.setco.astore.session.CategoryFacade;
import ru.setco.astore.session.TicketFacade;

/**
 * REST Web Service
 *
 * @author OLEG
 */
@Path("t")
public class TicketByCategoryFacadeREST {

    @EJB
    private CategoryFacadeREST categoryFacadeREST;
    
    @EJB
    private TicketFacade ticketFacade;
//    private CategoryFacade categoryFacade;

    public TicketByCategoryFacadeREST() {
    }

//    @GET
//    @Path("{id}")
//    @Produces({"application/xml", "application/json"})
//    public JsonArray geTicketByCategory(@PathParam("id") Integer categoryId) {
//        JsonArrayBuilder jb = Json.createArrayBuilder();
//        Category selectedCategory = categoryFacadeREST.find(categoryId);
//        if (selectedCategory != null) {
//            for (Ticket ticket : selectedCategory.getTicketCollection()) {
//                JsonObjectBuilder job = Json.createObjectBuilder().
//                        add("id", ticket.getId()).
//                        add("name", ticket.getName()).
//                        add("price", ticket.getPrice());
//                jb.add(job);
//            }
//        }
//        return jb.build();
//    }
    @GET
    @Produces({"application/xml", "application/json"})
    public Collection<Category> findQq()  {
        return categoryFacadeREST.findAll();
    }
    
    
    
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Collection<Ticket> findQ(@PathParam("id") Integer id)  {
//        return ticketFacade.findByCategory(id);
        return categoryFacadeREST.find(id).getTicketCollection();
    
    }

}
