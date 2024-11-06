package controllers;

import entities.Plans;
import entities.Subscriptions;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import facade.SubscriptionsFacade;
import jakarta.inject.Inject;
import java.util.concurrent.Flow.Subscription;

@WebServlet(name = "HomeController", 
            loadOnStartup = 1, 
            urlPatterns = {"/MisClases"})
public class HomeController extends HttpServlet {

    @Resource
    private UserTransaction utx;

    // Simulamos un facade para acceder a la información de las suscripciones
@Inject
    private SubscriptionsFacade subscriptionFacade;
    
    // Método para obtener la suscripción y el plan de un usuario
    private Subscription getSubscriptionForUser(int userId) {
        // Usamos el facade para obtener la suscripción del usuario
        return subscriptionFacade.getSubscriptionByUserId(userId); // Este método debe ser implementado en el facade
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("Entró en /MisClases");

        try {
            String pathUsuario = request.getServletPath();
            String url = null;

            switch (pathUsuario) {
                case "/MisClases":
                Integer userId = (Integer) request.getSession().getAttribute("userId");
                if (userId != null) {
                    // Obtenemos la suscripción del usuario
                    Subscriptions subscription = (Subscriptions) getSubscriptionForUser(userId);

                    if (subscription != null) {
                        // Pasamos los datos de la suscripción al JSP
                        request.setAttribute("subscription", subscription);

                        // Obtener el plan desde la suscripción
                        Plans plan = subscription.getPlanId();  // Aquí accedemos al objeto Plan

                        if (plan != null) {
                            int classQuantity = plan.getClassQuantity(); // Accedemos a la cantidad de clases del plan

                            request.setAttribute("classQuantity", classQuantity); // Pasamos la cantidad de clases al JSP
                            url = "/clientHome.jsp"; // Redirigimos a la página de inicio donde se muestran las clases
                        } else {
                            request.setAttribute("message", "El plan de tu suscripción no está disponible.");
                            url = "/clientHome.jsp";
                        }
                    } else {
                        // Si no hay suscripción, mostramos un mensaje
                        request.setAttribute("message", "No tienes ninguna suscripción activa.");
                        url = "/home.jsp";
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User ID is required");
                    return;
                }
                break;

            }

            if (url != null) {
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Página no encontrada");
            }

        } catch (ServletException | IOException | IllegalStateException | NumberFormatException | SecurityException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            try {
                if (utx.getStatus() == jakarta.transaction.Status.STATUS_ACTIVE) {
                    utx.rollback();
                }
            } catch (SystemException | IllegalStateException | SecurityException e) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, e);
            }
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrió un error en el servidor.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Manejador Principal de Alumnos y Facultades";
    }
}
