/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth;

import java.io.Serializable;
import java.util.UUID;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Notreal
 */
@XmlRootElement
public class Salt implements Serializable {
    private String salt;
    
    public Salt() {
        this.salt = UUID.randomUUID().toString();
    }

    public Salt(String salt) {
        this.salt = salt;
    }
    
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return salt;
    }

}
