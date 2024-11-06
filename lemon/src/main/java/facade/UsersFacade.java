/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facade;

import entities.Users;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Lucas
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    
         public Users loginUser(String dni, String password) {
    TypedQuery<Users> query = em.createQuery(
        "SELECT u FROM Users u WHERE u.dni = :dni AND u.password = :password", Users.class);
    query.setParameter("dni", dni);
    query.setParameter("password", password);
    
    try {
        Users user = query.getSingleResult();
        System.out.println("Usuario encontrado: " + user.getId());
        return user; // Devuelve el usuario si existe
    } catch (NoResultException e) {
        System.out.println("Usuario no encontrado para el DNI y contraseña proporcionados");
        return null; // Retorna null si no se encuentra ningún usuario
    }
}

    @Transactional
    public boolean registerUser(String firstName, String lastName, String dni, String email, String password, String userType) {
        // Verificar si el usuario ya existe por DNI o email
        boolean exists = !em.createQuery("SELECT u FROM Users u WHERE u.dni = :dni OR u.email = :email", Users.class)
                .setParameter("dni", dni)
                .setParameter("email", email)
                .getResultList()
                .isEmpty();
        
        if (exists) {
            return false; // Usuario ya existe
        }

        // Crear la entidad User
        Users usuario = new Users();
        usuario.setFirstName(firstName);
        usuario.setLastName(lastName);
        usuario.setDni(dni);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setUserType(userType);

        // Persistir el usuario en la base de datos
        em.persist(usuario);
        return true; // Registro exitoso
    }
    
    
        public Users findUserByDni(String dni) {
        try {
            return em.createQuery("SELECT u FROM Users u WHERE u.dni = :dni", Users.class)
                     .setParameter("dni", dni)
                     .getSingleResult();
        } catch (Exception e) {
            return null; // Devuelve null si no encuentra el usuario
        }
    }

    public List<Users> findUsersWithReservationsOnDate(LocalDate date) {
        return em.createQuery("SELECT u FROM Users u JOIN u.reservations r WHERE r.reservationDate = :date", Users.class)
                 .setParameter("date", date)
                 .getResultList();
    }
       
public Users findUserById(Integer userId) {
        try {
            return em.find(Users.class, userId); // Busca el usuario por ID
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Manejo de errores simple, puedes mejorarlo según tus necesidades
        }
    }
    

    
}
