/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author Lucas
 */
@Entity
@Table(name = "plans", catalog = "dblemon", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plans.findAll", query = "SELECT p FROM Plans p"),
    @NamedQuery(name = "Plans.findById", query = "SELECT p FROM Plans p WHERE p.id = :id"),
    @NamedQuery(name = "Plans.findByName", query = "SELECT p FROM Plans p WHERE p.name = :name"),
    @NamedQuery(name = "Plans.findByClassQuantity", query = "SELECT p FROM Plans p WHERE p.classQuantity = :classQuantity"),
    @NamedQuery(name = "Plans.findByPrice", query = "SELECT p FROM Plans p WHERE p.price = :price")})
public class Plans implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "class_quantity", nullable = false)
    private int classQuantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planId")
    private Collection<Subscriptions> subscriptionsCollection;

    public Plans() {
    }

    public Plans(Integer id) {
        this.id = id;
    }

    public Plans(Integer id, String name, int classQuantity, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.classQuantity = classQuantity;
        this.price = price;
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

    public int getClassQuantity() {
        return classQuantity;
    }

    public void setClassQuantity(int classQuantity) {
        this.classQuantity = classQuantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @XmlTransient
    public Collection<Subscriptions> getSubscriptionsCollection() {
        return subscriptionsCollection;
    }

    public void setSubscriptionsCollection(Collection<Subscriptions> subscriptionsCollection) {
        this.subscriptionsCollection = subscriptionsCollection;
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
        if (!(object instanceof Plans)) {
            return false;
        }
        Plans other = (Plans) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Plans[ id=" + id + " ]";
    }
    
}
