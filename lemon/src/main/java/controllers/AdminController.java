package controllers;

import entities.Users;
import entities.Plans;
import facade.UsersFacade;
import facade.PlansFacade;
import facade.SubscriptionsFacade;

import jakarta.annotation.Resource;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.UserTransaction;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "AdminController",
            urlPatterns = {"/searchUserByDNI", "/loadSubscription"})
public class AdminController extends HttpServlet {

    @Inject
    private UsersFacade userFacade;
    
    @Inject
    private PlansFacade planFacade;
    
    @Inject
    private SubscriptionsFacade subscriptionFacade;
    
    
    @Resource
    private UserTransaction utx;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathUsuario = request.getServletPath();
        String url = "/WEB-INF/views/selectSubscription.jsp"; // Única vista JSP utilizada

        try {
            switch (pathUsuario) {
                case "/searchUserByDNI":
                    String dni = request.getParameter("dni");
                    Users user = userFacade.findUserByDni(dni);
                    List<Plans> plans = planFacade.findAll(); // Obtener todos los planes disponibles
                    request.setAttribute("plans", plans);

                    if (user != null) {
                        request.setAttribute("user", user); // Usuario encontrado
                    } else {
                        request.setAttribute("errorMessage", "Usuario no encontrado"); // Error si no se encuentra
                    }
                    break;

                case "/loadSubscription":
                    String userId = request.getParameter("user_id");
                    String planId = request.getParameter("plan_id");
                    
                    boolean subscriptionLoaded = loadSubscriptionForUser(userId, planId);
                    if (subscriptionLoaded) {
                        request.setAttribute("successMessage", "Abono cargado exitosamente");
                    } else {
                        request.setAttribute("errorMessage", "No se pudo cargar el abono");
                    }
                    break;
            }

            // Redirigir siempre a `selectSubscription.jsp`
            request.getRequestDispatcher(url).forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            try {
                if (utx.getStatus() == jakarta.transaction.Status.STATUS_ACTIVE) {
                    utx.rollback();
                }
            } catch (Exception e) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, e);
            }
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrió un error en el servidor.");
        }
    }
    
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public boolean loadSubscriptionForUser(String userId, String planId) {
    try {
        return subscriptionFacade.assignSubscriptionToUser(userId, planId);
    } catch (Exception e) {
        Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, "Error al cargar el abono", e);
        return false;
    }
}


    @Override
    public String getServletInfo() {
        return "Controlador para manejar las operaciones administrativas relacionadas con usuarios y abonos.";
    }
}
