/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.io.IOException;
import java.security.Principal;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Notreal
 */
//@Provider
//@PreMatching
public class SecurityRequestFilter implements ContainerRequestFilter {

    @Override
    public void filter(final ContainerRequestContext requestContext)
            throws IOException {
        requestContext.setSecurityContext(new SecurityContext() {

            @Override
            public Principal getUserPrincipal() {
                return new Principal() {

                    @Override
                    public String getName() {
                        return "newPrincipal9";
                    }
                };
            }
 
            @Override
            public boolean isUserInRole(final String role) {
                System.out.println("role = " + role);
                return true;
            }

            @Override
            public boolean isSecure() {
                System.out.println("isSecure");
                return true;
            }

            @Override
            public String getAuthenticationScheme() {
                System.out.println("getAuthenticationScheme");
                return "";
            }
        });
    }
}
