/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author OLEG
 */
@Embeddable
public class OrderedTicketPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUSTOMER_ORDER_ID")
    private int customerOrderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TICKET_ID")
    private int ticketId;

    public OrderedTicketPK() {
    }

    public OrderedTicketPK(int customerOrderId, int ticketId) {
        this.customerOrderId = customerOrderId;
        this.ticketId = ticketId;
    }

    public int getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(int customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) customerOrderId;
        hash += (int) ticketId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderedTicketPK)) {
            return false;
        }
        OrderedTicketPK other = (OrderedTicketPK) object;
        if (this.customerOrderId != other.customerOrderId) {
            return false;
        }
        if (this.ticketId != other.ticketId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.OrderedTicketPK[ customerOrderId=" + customerOrderId + ", ticketId=" + ticketId + " ]";
    }
    
}
