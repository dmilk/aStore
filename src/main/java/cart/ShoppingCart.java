/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import entity.Ticket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OLEG
 */
public class ShoppingCart {
    
    List<ShoppingCartItem> items;
    int numberOfItems;
    double total;

    public ShoppingCart() {
        items = new ArrayList<ShoppingCartItem>();
        numberOfItems = 0;
        total = 0;
    }
    
    public synchronized void addItem(Ticket ticket, String ticketData) {
        ShoppingCartItem item = new ShoppingCartItem(ticket, ticketData);
        items.add(item);
    }
    
    public synchronized List<ShoppingCartItem> getItems() {
        return items;
    }
    
    public synchronized int getNumberOfItems() {
        return items.size();
    }
    
    public synchronized double getSubtotal() {
        double amount = 0;
        for(ShoppingCartItem item : items) {
            Ticket ticket = item.getTicket();
            amount += ticket.getPrice().doubleValue();
        }
        return amount;
    }
    
    public synchronized void calculateTotal() {
        double amount = 0;
        amount = this.getSubtotal();
        total = amount;
    }
    
    public synchronized double getTotal() {
        calculateTotal();
        return total;
    }
    
    public synchronized void clear() {
        items.clear();
        numberOfItems = 0;
        total = 0;
    }
    
}
