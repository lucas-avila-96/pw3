/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Lucas
 */
@Entity
@Table(name = "subscriptions", catalog = "dblemon", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subscriptions.findAll", query = "SELECT s FROM Subscriptions s"),
    @NamedQuery(name = "Subscriptions.findBySubscriptionId", query = "SELECT s FROM Subscriptions s WHERE s.subscriptionId = :subscriptionId"),
    @NamedQuery(name = "Subscriptions.findByStartDate", query = "SELECT s FROM Subscriptions s WHERE s.startDate = :startDate"),
    @NamedQuery(name = "Subscriptions.findByExpirationDate", query = "SELECT s FROM Subscriptions s WHERE s.expirationDate = :expirationDate"),
    @NamedQuery(name = "Subscriptions.findByRemainingClasses", query = "SELECT s FROM Subscriptions s WHERE s.remainingClasses = :remainingClasses")})
public class Subscriptions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "subscription_id", nullable = false)
    private Integer subscriptionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expiration_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date expirationDate;
    @Column(name = "remaining_classes")
    private Integer remainingClasses;
    @JoinColumn(name = "plan_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Plans planId;
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Users userId;

    public Subscriptions() {
    }

    public Subscriptions(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Subscriptions(Integer subscriptionId, Date startDate, Date expirationDate) {
        this.subscriptionId = subscriptionId;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
    }

    public Integer getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getRemainingClasses() {
        return remainingClasses;
    }

    public void setRemainingClasses(Integer remainingClasses) {
        this.remainingClasses = remainingClasses;
    }

    public Plans getPlanId() {
        return planId;
    }

    public void setPlanId(Plans planId) {
        this.planId = planId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subscriptionId != null ? subscriptionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subscriptions)) {
            return false;
        }
        Subscriptions other = (Subscriptions) object;
        if ((this.subscriptionId == null && other.subscriptionId != null) || (this.subscriptionId != null && !this.subscriptionId.equals(other.subscriptionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Subscriptions[ subscriptionId=" + subscriptionId + " ]";
    }
    
}
