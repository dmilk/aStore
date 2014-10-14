/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NamedQuery(name = "OrderedTicket.findById", query = "SELECT o FROM OrderedTicket o WHERE o.id = :id"),
    @NamedQuery(name = "OrderedTicket.findByTicketData", query = "SELECT o FROM OrderedTicket o WHERE o.ticketData = :ticketData")
//            @NamedQuery(name = "OrderedTicket.findByCustomerOrderId", query = "SELECT o FROM OrderedTicket o WHERE o.customerOrder = :customerOrder")
        })
public class OrderedTicket implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "TICKET_DATA")
    private String ticketData;
    @JoinColumn(name = "CUSTOMER_ORDER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CustomerOrder customerOrder;
    @JoinColumn(name = "TICKET_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Ticket ticket;

    public OrderedTicket() {
    }

    public OrderedTicket(Integer id) {
        this.id = id;
    }

    public OrderedTicket(Integer id, String ticketData) {
        this.id = id;
        this.ticketData = ticketData;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTicketData() {
        return ticketData;
    }

    public void setTicketData(String ticketData) {
        this.ticketData = ticketData;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderedTicket)) {
            return false;
        }
        OrderedTicket other = (OrderedTicket) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.OrderedTicket[ id=" + id + " ]";
    }
    
}
