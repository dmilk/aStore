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
    
    public static final String PARAM_AUTH_ID = "aStore-auth-id";
    public static final String PARAM_AUTH_TOKEN = "aStore-auth-token";
 
    private String authId;
    private String authToken;
    private Set<String> authPermission;
 
    public AuthAccessElement() {
    }

    public AuthAccessElement(String authId, String authToken, Set<String> authPermissionSet) {
        this.authId = authId;
        this.authToken = authToken;
        this.authPermission = authPermissionSet;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
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
