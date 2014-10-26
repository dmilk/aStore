/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth;

import java.io.Serializable;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Notreal
 */
@XmlRootElement
public class AuthAccessElement implements Serializable {
    
    public static final String PARAM_AUTH_TOKEN = "aStore-auth-token";
 
    private String authToken;
    private Set<String> authPermission;
 
    public AuthAccessElement() {
    }

    public AuthAccessElement(String authToken, Set<String> authPermissionSet) {
        this.authToken = authToken;
        this.authPermission = authPermissionSet;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Set<String> getAuthPermissionSet() {
        return authPermission;
    }

    public void setAuthPermissionSet(Set<String> authPermissionSet) {
        this.authPermission = authPermissionSet;
    }

}
