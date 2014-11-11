/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import filter.NewCrossOriginResourceSharingFilter;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Notreal
 */
@ApplicationPath("rest")
public class MyApplication extends ResourceConfig {
    public MyApplication() {
        packages("rest");
        register(NewCrossOriginResourceSharingFilter.class);
//        register(SecurityEntityFilteringFeature.class);
    }
}
