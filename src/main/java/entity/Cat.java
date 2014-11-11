/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Notreal
 */
//@Entity
@XmlRootElement
public class Cat {

    private String name;
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public static Cat instance() {
        final Cat entity = new Cat();
        entity.setName("Kitty");
        
        return entity;

    }
}
