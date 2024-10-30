<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Plan" %>
<%@ page import="java.sql.*" %>

<%
    // Simulación de obtener planes de clases de la base de datos
    List<Plan> planes = null; // Aquí deberías obtener la lista de planes desde tu base de datos
    // Ejemplo: planes = PlanDAO.getAllPlans();

    // Reemplaza esta parte con tu lógica para obtener los planes
    try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tu_base_de_datos", "usuario", "contraseña");
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM plan");
        ResultSet rs = ps.executeQuery();

        planes = new ArrayList<>();
        while (rs.next()) {
            Plan plan = new Plan();
            plan.setId(rs.getInt("id"));
            plan.setName(rs.getString("name"));
            plan.setPrice(rs.getDouble("price"));
            plan.setClassQuantity(rs.getInt("class_quantity"));
            planes.add(plan);
        }
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seleccionar Plan</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="main-content">
        <h1>Selecciona tu Plan de Clases</h1>
        
        <div class="plan-list">
            <%
                if (planes != null && !planes.isEmpty()) {
                    for (Plan plan : planes) {
            %>
                        <div class="plan-card">
                            <h2><%= plan.getName() %></h2>
                            <p>Clases disponibles: <%= plan.getClassQuantity() %></p>
                            <p>Precio: $<%= plan.getPrice() %></p>
                            <form action="comprar_clase.jsp" method="post">
                                <input type="hidden" name="planId" value="<%= plan.getId() %>">
                                <button type="submit" class="btn">Comprar</button>
                            </form>
                        </div>
            <%
                    }
                } else {
            %>
                <p>No hay planes disponibles en este momento.</p>
            <%
                }
            %>
        </div>

        <a href="home.jsp" class="btn">Volver a la Página Principal</a>
    </div>
</body>
</html>
