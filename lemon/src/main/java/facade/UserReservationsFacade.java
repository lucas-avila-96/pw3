/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facade;

import entities.AvailableSchedule;
import entities.UserReservations;
import entities.Users;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.text.SimpleDateFormat;
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
        
           // Método para obtener las reservas de un usuario por ID
    public List<UserReservations> getReservationsByUserId(int userId) {
        TypedQuery<UserReservations> query = em.createQuery(
            "SELECT r FROM UserReservation r WHERE r.user.id = :userId ORDER BY r.reservationDate DESC",
            UserReservations.class
        );
        query.setParameter("userId", userId);

        return query.getResultList();
    }
    
    // Método para crear una reserva
    @Transactional
    public boolean createReservation(int userId, int scheduleId, String reservationDate, String reservationTime) throws java.text.ParseException {
        // Buscar el usuario y el horario en la base de datos
        Users user = em.find(Users.class, userId);
        AvailableSchedule schedule = em.find(AvailableSchedule.class, scheduleId);
        if (user == null || schedule == null) {
            return false; // Si el usuario o el horario no existen, retornar false
        }
        // Formatear la fecha y hora
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
        Date formattedDate = dateFormatter.parse(reservationDate);
        Date formattedTime = timeFormatter.parse(reservationTime);
        // Crear la reserva
        UserReservations reservation = new UserReservations();
        reservation.setUserId(user);
        reservation.setScheduleId(schedule);
        reservation.setReservationDate(formattedDate);
        reservation.setReservationTime(formattedTime);
        // Persistir la reserva
        em.persist(reservation);
        return true;
    }
}


