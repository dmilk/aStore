/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import entity.Ticket;

/**
 *
 * @author OLEG
 */
public class ShoppingCartItem {
    
    Ticket ticket;
    String ticketData;

    public ShoppingCartItem(Ticket ticket, String ticketData) {
        this.ticket = ticket;
        this.ticketData = ticketData;
    }

    public Ticket getTicket() {
        return ticket;
    }
    
    public double getTotal() {
        double amount = 0;
        amount = ticket.getPrice().doubleValue();
        return amount;
    }

    public String getTicketData() {
        return ticketData;
    }
    
    
}
