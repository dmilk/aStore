/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import entity.Category;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import session.CategoryFacade;

/**
 * REST Web Service
 *
 * @author Notreal
 */
@Path("category")
public class CategoryREST {
    
    @EJB
    CategoryFacade categoryFacade;

    public CategoryREST() {
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<Category> findAll() {
        return categoryFacade.findAll();
    }
}
