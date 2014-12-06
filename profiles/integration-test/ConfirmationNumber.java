/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import javax.ejb.Singleton;

/**
 *
 * @author Notreal
 */
@Singleton
public class ConfirmationNumber {

    private BlockingQueue<Integer> queue;

    public ConfirmationNumber() {
        queue = new ArrayBlockingQueue<>(100);
    }

    public int get() throws InterruptedException {
        if (queue.isEmpty()) {
            fillSet();
        }
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int result = (month + 1) * 10000 + day * 100 + queue.take();
        return result;
    }

    private void fillSet() {
        for (int i = 0; i < queue.size(); i++) {
            queue.add(i);
        }
    }

}
