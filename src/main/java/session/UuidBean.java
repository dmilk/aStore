/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.UUID;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

/**
 *
 * @author Notreal
 */
@Stateless
public class UuidBean {
    private String uuid;

    public UuidBean() {
        uuid = UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }
}
