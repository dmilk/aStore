/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import auth.AuthAccessElement;
import auth.AuthInfo;
import entity.Role;
import entity.User;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Notreal
 */
@Stateless
public class AuthService {

    @EJB
    UserFacade userFacade;

    public AuthAccessElement login(AuthInfo authInfo) {
        
        User user = userFacade.findByLoginAndPassword(authInfo.getLogin(), authInfo.getPassword());

        if (user != null) {
            user.setToken(UUID.randomUUID().toString());
            userFacade.edit(user);

            Set<String> authPermissionSet = new HashSet<>();
            Collection<Role> roles = user.getRoleCollection();
            for (Role role : roles) {
                authPermissionSet.add(role.getName());
            }

            return new AuthAccessElement(user.getToken(), authPermissionSet);
        }
        return null;
    }

    public boolean isAuthorized(String authToken, Set<String> rolesAllowed) {

        User user = userFacade.findByToken(authToken);

        if (user != null) {
            for (Role role : user.getRoleCollection()) {
                if (rolesAllowed.contains(role.getName())) {
                    return true;
                }
            }
        }
        
        return false;
    }

}
