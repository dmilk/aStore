/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.setco.astore.service;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import ru.setco.astore.entity.Category;
import ru.setco.astore.session.CategoryFacade;

/**
 * REST Web Service
 *
 * @author OLEG
 */
@Path("cat")
public class CategoryResource {
    
    @EJB
    CategoryFacade categoryFacade;

    public CategoryResource() {
    }
    
    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Category entity) {
        categoryFacade.create(entity);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Category> findAll() {
        return categoryFacade.findAll();
    }

    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
