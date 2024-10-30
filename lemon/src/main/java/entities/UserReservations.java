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
@Table(name = "user_reservations", catalog = "dblemon", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserReservations.findAll", query = "SELECT u FROM UserReservations u"),
    @NamedQuery(name = "UserReservations.findByReservationId", query = "SELECT u FROM UserReservations u WHERE u.reservationId = :reservationId"),
    @NamedQuery(name = "UserReservations.findByReservationDate", query = "SELECT u FROM UserReservations u WHERE u.reservationDate = :reservationDate"),
    @NamedQuery(name = "UserReservations.findByReservationTime", query = "SELECT u FROM UserReservations u WHERE u.reservationTime = :reservationTime")})
public class UserReservations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reservation_id", nullable = false)
    private Integer reservationId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reservation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date reservationDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reservation_time", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date reservationTime;
    @JoinColumn(name = "schedule_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private AvailableSchedule scheduleId;
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Users userId;

    public UserReservations() {
    }

    public UserReservations(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public UserReservations(Integer reservationId, Date reservationDate, Date reservationTime) {
        this.reservationId = reservationId;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Date getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Date reservationTime) {
        this.reservationTime = reservationTime;
    }

    public AvailableSchedule getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(AvailableSchedule scheduleId) {
        this.scheduleId = scheduleId;
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
        hash += (reservationId != null ? reservationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserReservations)) {
            return false;
        }
        UserReservations other = (UserReservations) object;
        if ((this.reservationId == null && other.reservationId != null) || (this.reservationId != null && !this.reservationId.equals(other.reservationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UserReservations[ reservationId=" + reservationId + " ]";
    }
    
}
