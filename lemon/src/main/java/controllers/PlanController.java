package controllers;

import entities.Plans;
import facade.PlansFacade;
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

@WebServlet(name = "PlanController", urlPatterns = {"/plans", "/addPlan"})
public class PlanController extends HttpServlet {

    @Inject
    private PlansFacade planFacade;

    @Resource
    private UserTransaction utx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        String url = null;

        try {
            switch (action) {
                case "/plans":
                    // Obtén todos los planes de la base de datos y envíalos a la vista
                    List<Plans> plans = planFacade.findAll();
                    request.setAttribute("plans", plans);
                    url = "/WEB-INF/views/select_plan.jsp";
                    break;
/*
                case "/addPlan":
                    if ("POST".equalsIgnoreCase(request.getMethod())) {
                        // Procesa la adición de un nuevo plan
                        String name = request.getParameter("name");
                        int classQuantity = Integer.parseInt(request.getParameter("class_quantity"));
                        double price = Double.parseDouble(request.getParameter("price"));

                        Plans newPlan = new Plans();
                        newPlan.setName(name);
                        newPlan.setClassQuantity(classQuantity);
                        newPlan.setPrice(price);

                        utx.begin();
                        planFacade.create(newPlan);
                        utx.commit();

                        response.sendRedirect("plans"); // Redirige a la lista de planes
                        return;
                    } else {
                        // Muestra el formulario de agregar plan
                        url = "/WEB-INF/views/add_plan.jsp";
                    }
                    break;
*/
            }

            if (url != null) {
                request.getRequestDispatcher(url).forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(PlanController.class.getName()).log(Level.SEVERE, null, ex);
            try {
                utx.rollback();
            } catch (Exception rollbackEx) {
                Logger.getLogger(PlanController.class.getName()).log(Level.SEVERE, null, rollbackEx);
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
        return "Controlador para manejar operaciones de planes";
    }
}
