/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Lucas
 */
public class MiServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Establecer el tipo de contenido de la respuesta
        response.setContentType("text/html;charset=UTF-8");

        // Obtener el parámetro "nombre" enviado desde el formulario
        String nombre = request.getParameter("nombre");

        // Escribir la respuesta HTML
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Saludo</title>");
            out.println("</head>");
            out.println("<body>");
            if (nombre != null && !nombre.isEmpty()) {
                out.println("<h1>Hola, " + nombre + "!</h1>");
            } else {
                out.println("<h1>No ingresaste tu nombre. Por favor, vuelve e ingresa tu nombre.</h1>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // Sobrescribir los métodos doGet y doPost para que llamen a processRequest
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
        return "Servlet que muestra saludo";
    }
}