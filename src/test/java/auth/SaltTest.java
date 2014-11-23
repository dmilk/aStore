/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Notreal
 */
public class SaltTest {
    
    public SaltTest() {
    }

    /**
     * Test of getConstFakeSalt method, of class Salt.
     */
    @Test
    public void testGetConstFakeSalt() {
        String email = "1@1";
        Salt salt1 = new Salt();
        Salt salt2 = new Salt();
        salt1.setConstFakeSalt(email);
        System.out.println("salt1 = " + salt1);
        for(int i = 0; i < 10; i++) {
            salt2.setConstFakeSalt(email);
            assertEquals(salt1, salt2);
        }
        salt2.setConstFakeSalt("1@11");
        assertFalse(salt1.equals(salt2));
    }

}
