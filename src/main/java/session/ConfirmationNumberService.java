/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import javax.ejb.Singleton;

/**
 *
 * @author Notreal
 */
@Singleton
public class ConfirmationNumberService {

    private Set<Integer> set;

    public ConfirmationNumberService() {
        set = new HashSet<>();
    }

    public int get() {
        if (set.isEmpty()) {
            fillSet();
        }
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int setNumber = 0;

        synchronized(set) {
            Iterator<Integer> iterator = set.iterator();
            if (iterator.hasNext()) {
                setNumber = iterator.next();
                iterator.remove();
            }
        }
        
        int result = (month + 1) * 10000 + day * 100 + setNumber;
        return result;
    }

    private void fillSet() {
        synchronized (set) {
            for (int i = 0; i < 100; i++) {
                set.add(i);
            }
        }
    }

}
