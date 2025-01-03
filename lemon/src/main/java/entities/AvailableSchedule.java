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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author Lucas
 */
@Entity
@Table(name = "available_schedule", catalog = "dblemon", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AvailableSchedule.findAll", query = "SELECT a FROM AvailableSchedule a"),
    @NamedQuery(name = "AvailableSchedule.findById", query = "SELECT a FROM AvailableSchedule a WHERE a.id = :id"),
    @NamedQuery(name = "AvailableSchedule.findByDay", query = "SELECT a FROM AvailableSchedule a WHERE a.day = :day"),
    @NamedQuery(name = "AvailableSchedule.findByAvailableTime", query = "SELECT a FROM AvailableSchedule a WHERE a.availableTime = :availableTime")})
public class AvailableSchedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "day", nullable = false, length = 9)
    private String day;
    @Size(max = 255)
    @Column(name = "available_time", length = 255)
    private String availableTime;

    public AvailableSchedule() {
    }

    public AvailableSchedule(Integer id) {
        this.id = id;
    }

    public AvailableSchedule(Integer id, String day) {
        this.id = id;
        this.day = day;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(String availableTime) {
        this.availableTime = availableTime;
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
        if (!(object instanceof AvailableSchedule)) {
            return false;
        }
        AvailableSchedule other = (AvailableSchedule) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AvailableSchedule[ id=" + id + " ]";
    }
    
}
