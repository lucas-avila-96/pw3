<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="paquete.Usuarios" %>
<!DOCTYPE html>

<html>
<jsp:useBean id="usuario" scope="session" class="paquete.Usuarios" />
<jsp:setProperty name="usuario" property="*" />

<%
    usuario.processRequest(request);  // Asegúrate de que "request" sea del tipo correcto.
%>

<head>
    <title>Registro de Visitantes</title>
</head>
<body>
    <h1>Bienvenido a mi sitio web</h1>
    <p>Autor: [Lucas]</p>

    <h2>Ingresa tu nombre</h2>
    <form action="alcance.jsp" method="post">
        <input type="text" name="nombre" placeholder="Tu nombre" />
        <input type="hidden" name="submit" value="agregar" />
        <input type="submit" value="Registrar" />
    </form>

    <h2>Visitantes anteriores:</h2>
    <ol>
        <%
            String[] visitantes = usuario.getVisitantes();
            for (int i = 0; i < visitantes.length; i++) {
        %>
        <li><%= visitantes[i] %></li>
        <%
            }
        %>
    </ol>
</body>
</html>
