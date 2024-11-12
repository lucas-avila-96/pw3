package controllers;

import entities.AvailableSchedule;
import entities.UserReservations;
import facade.AvailableScheduleFacade;
import facade.UserReservationsFacade;
import facade.UsersFacade;
import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.SystemException;
import jakarta.transaction.UserTransaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ReservationController", urlPatterns = {"/createReservation", "/listReservations", "/scheduleTraining", "/cancelReservation"})
public class ReservationController extends HttpServlet {

    @Inject
    private UserReservationsFacade reservationsFacade;

    @Inject
    private UsersFacade usersFacade;

    @Inject
    private AvailableScheduleFacade availableScheduleFacade;

    @Resource
    private UserTransaction utx;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        String url = null;

        try {
            Integer userId = (Integer) request.getSession().getAttribute("userId");

            switch (action) {
                case "/scheduleTraining":
                    // Generar la semana móvil
                    List<Map<String, String>> weekDates = getMobileWeek();
                    List<AvailableSchedule> allHorarios = availableScheduleFacade.findAll();
                    Map<String, List<AvailableSchedule>> horariosPorDia = new HashMap<>();

                    // Agrupar horarios por día de la semana
                    for (AvailableSchedule horario : allHorarios) {
                        String dia = horario.getDay();
                        horariosPorDia.putIfAbsent(dia, new ArrayList<>());
                        horariosPorDia.get(dia).add(horario);
                    }
                    System.out.println(horariosPorDia);
                    request.setAttribute("userId", userId);
                    request.setAttribute("weekDates", weekDates);
                    request.setAttribute("horariosPorDia", horariosPorDia);

                    url = "/WEB-INF/views/reservationForm.jsp";
                    break;

                case "/createReservation":
                    // Lógica de creación de la reserva
                    String userIdStr = request.getParameter("userId");
                    String scheduleIdStr = request.getParameter("scheduleId");
                    String date = request.getParameter("reservationDate");
                    String time = request.getParameter("reservationTime");

                    int scheduleId = Integer.parseInt(scheduleIdStr);

                    // Debug para ver los valores de la reserva
                    System.out.println("userId: " + userId);
                    System.out.println("scheduleId: " + scheduleId);
                    System.out.println("date: " + date);
                    System.out.println("time: " + time);

                    // Crear la reserva
                    boolean created = reservationsFacade.createReservation(userId, scheduleId, date, time);

                    if (created) {
                        request.setAttribute("errorMessage", "Reserva creada");
                    } else {
                        request.setAttribute("errorMessage", "No se pudo crear la reserva.");
                    }

                    // Redirigir al JSP de reserva
                    url = "/WEB-INF/views/layout.jsp";
                    break;
                case "/listReservations":
                    // Obtener las reservas del usuario
                    List<UserReservations> reservations = reservationsFacade.getReservationsByUserId(userId);
                    System.out.println("Reservations: " + reservations); // Verificar contenido de reservas

                    if (reservations != null && !reservations.isEmpty()) {
                        request.setAttribute("reservations", reservations);
                    } else {
                        request.setAttribute("message", "No tienes reservas activas.");
                    }

                    // Especifica que el contenido será `listReservations.jsp`
                    request.setAttribute("content", "listReservations.jsp");

                    // Redirige a la plantilla base con el sidebar
                    url = "/WEB-INF/views/layout.jsp";
                    break;

                case "/cancelReservation":
                    String reservationIdStr = request.getParameter("reservationId");
                    System.out.println("Reservations: " + reservationIdStr); // Verificar contenido de reservas

                    if (reservationIdStr != null && !reservationIdStr.isEmpty()) {
                        try {
                            int reservationId = Integer.parseInt(reservationIdStr);

                            // Cancelar la reserva llamando al método en reservationsFacade
                            boolean cancelled = reservationsFacade.cancelReservation(reservationId);

                            if (cancelled) {
                                request.setAttribute("message", "Reserva cancelada exitosamente.");
                            } else {
                                request.setAttribute("message", "No se pudo cancelar la reserva. Verifica el ID.");
                            }
                        } catch (NumberFormatException e) {
                            request.setAttribute("message", "ID de reserva no válido.");
                        }
                    } else {
                        request.setAttribute("message", "ID de reserva no proporcionado.");
                    }

                    // Redirigir al JSP de listado de reservas
                    url = "/listReservations"; // Redirige a la página de reservas para mostrar el resultado
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
            } catch (SystemException | IllegalStateException | SecurityException e) {
                Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, e);
            }
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrió un error en el servidor.");
        }
    }

    // Método para generar la semana móvil
    private List<Map<String, String>> getMobileWeek() {
        List<Map<String, String>> weekDates = new ArrayList<>();
        java.util.Calendar calendar = java.util.Calendar.getInstance(); // Fecha actual
        for (int i = 0; i < 7; i++) { // 7 días de la semana
            java.util.Date date = calendar.getTime();
            String dayName = new java.text.SimpleDateFormat("EEEE", java.util.Locale.getDefault()).format(date);
            String dateFormatted = new java.text.SimpleDateFormat("yyyy-MM-dd").format(date);

            Map<String, String> dayMap = new HashMap<>();
            dayMap.put("day", dayName);
            dayMap.put("date", dateFormatted);
            weekDates.add(dayMap);

            // Avanza un día en cada iteración
            calendar.add(java.util.Calendar.DAY_OF_YEAR, 1);
        }
        return weekDates;
    }

    @Override
    public String getServletInfo() {
        return "Controlador para manejar operaciones de reservas";
    }
}
