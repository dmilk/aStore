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
 *♠
 * @author Notreal
 */
@Provider
@PreMatching
public class SecurityRequestFilter implements ContainerRequestFilter {
    
//    @EJB
//    private AuthService authService;
    
//    @Context
//    private ResourceInfo resourceInfo;
    

    @Override
    public void filter(final ContainerRequestContext requestContext) throws IOException {
//        final String authToken = requestContext.getHeaderString(AuthAccessElement.PARAM_AUTH_TOKEN);
        
        requestContext.setSecurityContext(new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
                return new Principal() {
                    @Override
                    public String getName() {
                        return "Jersey";
                    }
                };
            }

            @Override
            public boolean isUserInRole(final String role) {
                return "user".equals(role);

//                Method methodInvoked = resourceInfo.getResourceMethod();
//                if (methodInvoked.isAnnotationPresent(RolesAllowed.class)) {
//                Set<String> rolesAllowed = new HashSet<String>();
//                rolesAllowed.add(role);
//                if (authService.isAuthorized(authToken, rolesAllowed))
//                    return true;
//                return false;
//                }
//                return true;
            }

            @Override
            public boolean isSecure() {
                return false;
            }

            @Override
            public String getAuthenticationScheme() {
                return null;
            }
        });
    }
}