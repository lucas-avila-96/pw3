/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facade;

import entities.Plans;
import entities.Subscriptions;
import entities.Users;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.Flow.Subscription;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
@Stateless
public class SubscriptionsFacade extends AbstractFacade<Subscriptions> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubscriptionsFacade() {
        super(Subscriptions.class);
    }
 @TransactionAttribute(TransactionAttributeType.REQUIRED)
public boolean assignSubscriptionToUser(String userId, String planId) {
    try {
        Users user = em.find(Users.class, Integer.valueOf(userId));
        if (user == null) {
            return false; // Usuario no encontrado
        }
        
        Plans plan = em.find(Plans.class, Integer.valueOf(planId));
        if (plan == null) {
            return false; // Plan no encontrado
        }

        Subscriptions subscription = new Subscriptions();
        subscription.setUserId(user);
        subscription.setPlanId(em.find(Plans.class, Integer.valueOf(planId)));
        subscription.setRemainingClasses(plan.getClassQuantity());


        // Establecer fecha de inicio (fecha actual)
        subscription.setStartDate(convertToDate(LocalDate.now()));

        // Establecer fecha de expiración (30 días después de la fecha actual)
        subscription.setExpirationDate(convertToDate(LocalDate.now().plusMonths(1))); // Expira en un mes

        em.persist(subscription);

        return true;
    } catch (Exception e) {
        Logger.getLogger(SubscriptionsFacade.class.getName()).log(Level.SEVERE, "Error al asignar la suscripción", e);
        return false;
    }
}

// Método para obtener la suscripción de un usuario por ID
public Subscriptions getSubscriptionByUserId(int userId) {
    try {
        return em.createQuery("SELECT s FROM Subscriptions s WHERE s.userId.id = :userId", Subscriptions.class)
                 .setParameter("userId", userId)
                 .getSingleResult();
    } catch (Exception e) {
        System.out.println("Error al obtener la suscripción: " + e.getMessage());
        return null; // Si no encuentra la suscripción, retorna null
    }
}

public Subscription findActiveSubscriptionByUserId(int userId) {
    try {
        String query = "SELECT s FROM Subscriptions s WHERE s.userId.id = :userId AND (s.expirationDate > CURRENT_DATE OR s.remainingClasses > 0)";
        return em.createQuery(query, Subscription.class)
                            .setParameter("userId", userId)
                            .setMaxResults(1)
                            .getSingleResult();
    } catch (NoResultException e) {
        return null; // No hay suscripción activa
    }
    
    
    
    
}




    // Método para obtener la cantidad de clases de un plan
    public int getClassQuantityForPlan(int planId) {
        try {
            Plans plan = em.find(Plans.class, planId);
            return plan != null ? plan.getClassQuantity() : 0;
        } catch (Exception e) {
            return 0; // Si no encuentra el plan, retorna 0
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
public void removeExpiredSubscriptions() {
    try {
        String query = "DELETE FROM Subscriptions s WHERE s.expirationDate <= CURRENT_DATE OR s.remainingClasses <= 0";
        em.createQuery(query).executeUpdate();
    } catch (Exception e) {
        System.out.println("Error al eliminar la suscripción: " + e.getMessage());
    }
}


// Método auxiliar para convertir LocalDate a Date
private Date convertToDate(LocalDate localDate) {
    return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
}


    
}
