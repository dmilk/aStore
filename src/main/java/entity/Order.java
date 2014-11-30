/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Notreal
 */
@Entity
@Table(name = "\"ORDER\"")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Order.findAll", query = "SELECT c FROM Order c"),
    @NamedQuery(name = "Order.findById", query = "SELECT c FROM Order c WHERE c.id = :id"),
    @NamedQuery(name = "Order.findByAmount", query = "SELECT c FROM Order c WHERE c.amount = :amount"),
    @NamedQuery(name = "Order.findByDateCreated", query = "SELECT c FROM Order c WHERE c.dateCreated = :dateCreated"),
    @NamedQuery(name = "Order.findByConfirmationNumber", query = "SELECT c FROM Order c WHERE c.confirmationNumber = :confirmationNumber"),
    @NamedQuery(name = "Order.findByFirstName", query = "SELECT c FROM Order c WHERE c.firstName = :firstName"),
    @NamedQuery(name = "Order.findByLastName", query = "SELECT c FROM Order c WHERE c.lastName = :lastName"),
    @NamedQuery(name = "Order.findByEmail", query = "SELECT c FROM Order c WHERE c.email = :email"),
    @NamedQuery(name = "Order.findByPhone", query = "SELECT c FROM Order c WHERE c.phone = :phone")})
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "DATE_CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONFIRMATION_NUMBER")
    private int confirmationNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "LAST_NAME")
    private String lastName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "EMAIL")
    private String email;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "PHONE")
    private String phone;
    @JoinColumn(name = "ROUTE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Route route;
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderedTicket> orderedTicketCollection;

    public Order() {
    }

    public Order(Integer id) {
        this.id = id;
    }

    public Order(Integer id, BigDecimal amount, Date dateCreated, int confirmationNumber, String firstName, String lastName, String email, String phone) {
        this.id = id;
        this.amount = amount;
        this.dateCreated = dateCreated;
        this.confirmationNumber = confirmationNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    @XmlTransient
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(int confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @XmlTransient
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    @XmlTransient
    public List<OrderedTicket> getOrderedTicketCollection() {
        return orderedTicketCollection;
    }

    public void setOrderedTicketCollection(List<OrderedTicket> orderedTicketCollection) {
        this.orderedTicketCollection = orderedTicketCollection;
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
        if (!(object instanceof Order)) {
            return false;
        }
        Order other = (Order) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Order[ id=" + id + " ]";
    }

}
