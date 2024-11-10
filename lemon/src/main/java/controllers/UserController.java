package controllers;

import entities.Plans;
import entities.Subscriptions;
import entities.Users;
import facade.SubscriptionsFacade;
import facade.UsersFacade;
import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.UserTransaction;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Flow;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.jshell.JShell.Subscription;

@WebServlet(name = "UserController",
        loadOnStartup = 1,
        urlPatterns = {"/registerUser", "/login", "/addUser", "/misClases"})
public class UserController extends HttpServlet {

    @Inject
    private UsersFacade userFacade;

    @Inject
    private SubscriptionsFacade subscriptionFacade;// Inyección de UserFacade

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
        String url = null;

        try {
            switch (pathUsuario) {

                case "/registerUser":
                    url = "/WEB-INF/views/signup.jsp";
                    break;

                case "/addUser":
                    // Obtener datos del formulario de registro
                    String firstName = request.getParameter("first_name");
                    String lastName = request.getParameter("last_name");
                    String dni = request.getParameter("dni");
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
                    String userType = request.getParameter("user_type");

                    // Intentar registrar el usuario
                    boolean registered = userFacade.registerUser(firstName, lastName, dni, email, password, userType);
                    if (registered) {
                        response.sendRedirect(request.getContextPath() + "/login");
                        return;
                    } else {
                        request.setAttribute("errorMessage", "El usuario ya existe.");
                        url = "/WEB-INF/views/signup.jsp"; // Volver a la página de registro en caso de error
                    }

                    break;

                case "/login":
                    String dniLogin = request.getParameter("dni");
                    String passwordLogin = request.getParameter("password");

                    Users userLogin = userFacade.loginUser(dniLogin, passwordLogin); // Método para obtener usuario

                    if (userLogin != null) {
                        request.getSession().setAttribute("userId", userLogin.getId());
                        request.getSession().setAttribute("userType", userLogin.getUserType());

                        if (userLogin.getUserType().equals("admin")) {
                            url = "/WEB-INF/views/adminHome.jsp";
                        } else {
                            // Redirigir al controlador HomeController para ejecutar la lógica de MisClases
                            response.sendRedirect(request.getContextPath() + "/misClases");
                            return; // Termina el proceso para evitar el reenvío adicional
                        }
                    } else {
                        request.setAttribute("errorMessage", "DNI o contraseña incorrectos");
                        url = "/WEB-INF/views/login.jsp";
                    }
                    break;

                case "/misClases":

                    Integer userId = (Integer) request.getSession().getAttribute("userId");
                    System.out.println(userId);

                    if (userId != null) {
                        Users user = userFacade.findUserById(userId);
                        request.setAttribute("userName", user.getFirstName());

                        // Obtenemos la suscripción del usuario
                        Subscriptions subscription = subscriptionFacade.getSubscriptionByUserId(userId);

                        if (subscription != null) {
                            // Pasamos la suscripción completa al JSP
                            request.setAttribute("subscription", subscription);

                            // Obtener el plan desde la suscripción
                            Plans plan = subscription.getPlanId(); // Aquí accedemos al objeto Plan

                            if (plan != null) {
                                int classQuantity = plan.getClassQuantity(); // Cantidad de clases del plan
                                Date expirationDate = subscription.getExpirationDate(); // Fecha de vencimiento de la suscripción
                                int classAvailable = subscription.getRemainingClasses(); // clases disponibles

                                // Pasamos la cantidad de clases y fecha de vencimiento al JSP
                                request.setAttribute("classQuantity", classQuantity);
                                request.setAttribute("expirationDate", expirationDate);
                                request.setAttribute("classAvailable", classAvailable);


                                url = "/WEB-INF/views/clientHome.jsp"; // Redirigimos a la página de inicio
                            } else {
                                request.setAttribute("message", "El plan de tu suscripción no está disponible.");
                                url = "/WEB-INF/views/clientHome.jsp";
                            }
                        } else {
                            // Si no hay suscripción, mostramos un mensaje
                            request.setAttribute("message", "No tienes ninguna suscripción activa.");
                            url = "/WEB-INF/views/clientHome.jsp";
                        }
                    } else {
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User ID is required");
                        return;
                    }
                    break;

            }

            if (url != null) {
                request.getRequestDispatcher(url).forward(request, response);
            }

        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            try {
                if (utx.getStatus() == jakarta.transaction.Status.STATUS_ACTIVE) {
                    utx.rollback();
                }
            } catch (Exception e) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, e);
            }
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrió un error en el servidor.");
        }
    }

    @Override
    public String getServletInfo() {
        return "Controlador para manejar operaciones de usuarios";
    }
}
