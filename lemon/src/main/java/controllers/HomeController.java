package controllers;

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

@WebServlet(name = "HomeController", 
            loadOnStartup = 1, 
            urlPatterns = {"/MisClases", "/ComprarClases"}
)
public class HomeController extends HttpServlet {

    @Resource
    private UserTransaction utx;    

    @Override
    public void init() throws ServletException {
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String pathUsuario = request.getServletPath();
            System.out.println("path = " + pathUsuario);
            String url = null;

            switch (pathUsuario) {
                case "/MisClases":
                    break;
                
                /*
                 
                   
                case "/ComprarClases":
                    int planId = Integer.parseInt(request.getParameter("planId")); // Obtener el ID del plan desde la solicitud
                    // Lógica para realizar la compra del plan
                    // Por ejemplo, podrías llamar a un método en tu fachada para agregar una suscripción
                    boolean success = PlansFacade.comprarClase(planId, usuarioId); // Implementa este método según tu lógica
                    if (success) {
                        request.setAttribute("mensaje", "Compra realizada con éxito!");
                    } else {
                        request.setAttribute("mensaje", "Error al realizar la compra. Intenta nuevamente.");
                    }
                    url = "/WEB-INF/views/resultado_compra.jsp"; // Cambia a tu vista de resultado de compra
                    break;
*/
            }

            request.getRequestDispatcher(url).forward(request, response);

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
