/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import entity.Category;
import entity.OrderedTicket;
import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author OLEG
 */
@Entity
@Table(name = "supporting_document")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SupportingDocument.findAll", query = "SELECT s FROM SupportingDocument s"),
    @NamedQuery(name = "SupportingDocument.findById", query = "SELECT s FROM SupportingDocument s WHERE s.id = :id"),
    @NamedQuery(name = "SupportingDocument.findByName", query = "SELECT s FROM SupportingDocument s WHERE s.name = :name"),
    @NamedQuery(name = "SupportingDocument.findByDocType", query = "SELECT s FROM SupportingDocument s WHERE s.docType = :docType")})
public class SupportingDocument implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DOC_TYPE")
    private int docType;
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Category categoryId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supportingDocumentId")
    private Collection<OrderedTicket> orderedTicketCollection;

    public SupportingDocument() {
    }

    public SupportingDocument(Integer id) {
        this.id = id;
    }

    public SupportingDocument(Integer id, String name, int docType) {
        this.id = id;
        this.name = name;
        this.docType = docType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDocType() {
        return docType;
    }

    public void setDocType(int docType) {
        this.docType = docType;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    @XmlTransient
    public Collection<OrderedTicket> getOrderedTicketCollection() {
        return orderedTicketCollection;
    }

    public void setOrderedTicketCollection(Collection<OrderedTicket> orderedTicketCollection) {
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
        if (!(object instanceof SupportingDocument)) {
            return false;
        }
        SupportingDocument other = (SupportingDocument) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity2.SupportingDocument[ id=" + id + " ]";
    }
    
}
