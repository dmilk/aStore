/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth;

import java.util.Set;

/**
 *
 * @author Notreal
 */
public interface AuthService {
    
     public AuthAccessElement login(AuthInfo authInfo);
     public boolean isAuthorized(String authId, String authToken, Set<String> rolesAllowed);
    
}
