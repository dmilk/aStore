package entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
    @Column(name = "SUPPORTING_DOCUMENT_DATA")
    private String supportingDocumentData;
    @Size(max = 30)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Size(max = 40)
    @Column(name = "LAST_NAME")
    private String lastName;
    @Size(max = 30)
    @Column(name = "MIDDLE_NAME")
    private String middleName;
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @JoinColumn(name = "SUPPORTING_DOCUMENT_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private SupportingDocument supportingDocumentId;
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Order order;
    @JoinColumn(name = "TICKET_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Ticket ticket;

    public OrderedTicket() {
    }

    public OrderedTicket(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
        return "entity.OrderedTicket[ id=" + id + " ]"    }

    public String getSupportingDocumentData() {
        return supportingDocumentData;
    }

    public void setSupportingDocumentData(String supportingDocumentData) {
        this.supportingDocumentData = supportingDocumentData;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public SupportingDocument getSupportingDocumentId() {
        return supportingDocumentId;
    }

    public void setSupportingDocumentId(SupportingDocument supportingDocumentId) {
        this.supportingDocumentId = supportingDocumentId;
;
    }

}
