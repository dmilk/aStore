/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth;

import entity.Customer;
import entity.Role;
import entity.User;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import session.UserFacade;

/**
 *
 * @author Notreal
 */
@Stateless
public class AuthServiceBean implements AuthService {

    @EJB
    UserFacade userFacade;

    @Override
    public AuthAccessElement login(AuthInfo authInfo) {
        User user = userFacade.findByLoginAndPassword(authInfo.getLogin(), authInfo.getPassword());

        if (user != null) {
            user.setUuid(UUID.randomUUID().toString());
            userFacade.edit(user);

            Set<String> authPermissionSet = new HashSet<String>();
            Collection<Role> roles = user.getRoleCollection();
            for (Role role : roles) {
                authPermissionSet.add(role.getName());
            }

            return new AuthAccessElement(authInfo.getLogin(), user.getUuid(), authPermissionSet);
        }
        return null;
    }

    @Override
    public boolean isAuthorized(String authId, String authToken, Set<String> rolesAllowed) {

        User user = userFacade.findByAuthIdAndAuthToken(authId, authToken);

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