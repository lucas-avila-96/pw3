/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facade;

import entities.UserReservations;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lucas
 */
@Stateless
public class UserReservationsFacade extends AbstractFacade<UserReservations> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserReservationsFacade() {
        super(UserReservations.class);
    }
        public List<UserReservations> getAttendanceForToday() {
        Date today = new Date();
        return em.createQuery("SELECT ur FROM UserReservations ur WHERE DATE(ur.reservationDate) = DATE(:today)", UserReservations.class)
                 .setParameter("today", today)
                 .getResultList();
    }
}
