/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author OLEG
 */
@Entity
@Table(name = "ordered_ticket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderedTicket.findAll", query = "SELECT o FROM OrderedTicket o"),
    @NamedQuery(name = "OrderedTicket.findByCustomerOrderId", query = "SELECT o FROM OrderedTicket o WHERE o.orderedTicketPK.customerOrderId = :customerOrderId"),
    @NamedQuery(name = "OrderedTicket.findByTicketId", query = "SELECT o FROM OrderedTicket o WHERE o.orderedTicketPK.ticketId = :ticketId"),
    @NamedQuery(name = "OrderedTicket.findByTicketData", query = "SELECT o FROM OrderedTicket o WHERE o.ticketData = :ticketData")})
public class OrderedTicket implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrderedTicketPK orderedTicketPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "TICKET_DATA")
    private String ticketData;
    @JoinColumn(name = "TICKET_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ticket ticket;
    @JoinColumn(name = "CUSTOMER_ORDER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CustomerOrder customerOrder;

    public OrderedTicket() {
    }

    public OrderedTicket(OrderedTicketPK orderedTicketPK) {
        this.orderedTicketPK = orderedTicketPK;
    }

    public OrderedTicket(OrderedTicketPK orderedTicketPK, String ticketData) {
        this.orderedTicketPK = orderedTicketPK;
        this.ticketData = ticketData;
    }

    public OrderedTicket(int customerOrderId, int ticketId) {
        this.orderedTicketPK = new OrderedTicketPK(customerOrderId, ticketId);
    }

    public OrderedTicketPK getOrderedTicketPK() {
        return orderedTicketPK;
    }

    public void setOrderedTicketPK(OrderedTicketPK orderedTicketPK) {
        this.orderedTicketPK = orderedTicketPK;
    }

    public String getTicketData() {
        return ticketData;
    }

    public void setTicketData(String ticketData) {
        this.ticketData = ticketData;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderedTicketPK != null ? orderedTicketPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderedTicket)) {
            return false;
        }
        OrderedTicket other = (OrderedTicket) object;
        if ((this.orderedTicketPK == null && other.orderedTicketPK != null) || (this.orderedTicketPK != null && !this.orderedTicketPK.equals(other.orderedTicketPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.OrderedTicket[ orderedTicketPK=" + orderedTicketPK + " ]";
    }
    
}
