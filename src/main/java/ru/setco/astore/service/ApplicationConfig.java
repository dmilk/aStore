/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.setco.astore.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author OLEG
 */
@javax.ws.rs.ApplicationPath("rs")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ru.setco.astore.NewCrossOriginResourceSharingFilter.class);
        resources.add(ru.setco.astore.service.CategoryFacadeREST.class);
        resources.add(ru.setco.astore.service.CategoryResource.class);
        resources.add(ru.setco.astore.service.TicketByCategoryFacadeREST.class);
        resources.add(ru.setco.astore.service.TicketFacadeREST.class);
    }
    
}
