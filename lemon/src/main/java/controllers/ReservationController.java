package controllers;

import entities.UserReservations;
import facade.UserReservationsFacade;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ReservationController",
        urlPatterns = {"/createReservation", "/listReservations", "/scheduleTraining"})
public class ReservationController extends HttpServlet {

    @Inject
    private UserReservationsFacade reservationsFacade;

    @Inject
    private UsersFacade usersFacade;

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
        String action = request.getServletPath();
        String url = null;

        try {
            switch (action) {
                case "/scheduleTraining":
                    url = "/WEB-INF/views/reservationForm.jsp";
                    break;

                case "/createReservation":
                    int userId = Integer.parseInt(request.getParameter("userId"));
                    int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
                    String date = request.getParameter("reservationDate");
                    String time = request.getParameter("reservationTime");

                    // Llamada al método de crear reserva en el facade
                    boolean created = reservationsFacade.createReservation(userId, scheduleId, date, time);

                    if (created) {
                        request.setAttribute("errorMessage", "Reserva creada");
                        url = "/WEB-INF/views/reservationForm.jsp";
                    } else {
                        request.setAttribute("errorMessage", "No se pudo crear la reserva.");
                        url = "/WEB-INF/views/reservationForm.jsp";
                    }
                    break;

                // Case para listar reservas de un usuario
                case "/listReservations":
                    userId = Integer.parseInt(request.getParameter("userId"));

                    List<UserReservations> reservations = reservationsFacade.getReservationsByUserId(userId);

                    if (reservations != null && !reservations.isEmpty()) {
                        request.setAttribute("reservations", reservations);
                        url = "/WEB-INF/views/listReservations.jsp";
                    } else {
                        request.setAttribute("message", "No tienes reservas activas.");
                        url = "/WEB-INF/views/listReservations.jsp";
                    }
                    break;

            }

            if (url != null) {
                request.getRequestDispatcher(url).forward(request, response);
            }

        } catch (Exception ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
            try {
                if (utx.getStatus() == jakarta.transaction.Status.STATUS_ACTIVE) {
                    utx.rollback();
                }
            } catch (Exception e) {
                Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, e);
            }
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrió un error en el servidor.");
        }
    }

    @Override
    public String getServletInfo() {
        return "Controlador para manejar operaciones de reservas";
    }
}
