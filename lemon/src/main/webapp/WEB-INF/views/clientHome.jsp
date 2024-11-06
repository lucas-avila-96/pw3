<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi Página de Inicio - Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <h1 class="mt-5">Bienvenido a tu página de inicio</h1>

    <!-- Verificar si el usuario tiene una suscripción activa -->
    <c:if test="${not empty classQuantity}">
        <div class="alert alert-success mt-4">
            <h4 class="alert-heading">¡Tu suscripción está activa!</h4>
            <p>Tu plan te permite realizar <strong>${classQuantity}</strong> clases.</p>
        </div>
    </c:if>

    <c:if test="${empty classQuantity}">
        <div class="alert alert-warning mt-4">
            <h4 class="alert-heading">No tienes un plan activo</h4>
            <p>Por favor, contacta al administrador para obtener un plan.</p>
        </div>
    </c:if>

    <!-- Mostrar un mensaje si no se obtiene la información -->
    <c:if test="${not empty message}">
        <div class="alert alert-danger mt-4">
            <p>${message}</p>
        </div>
    </c:if>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
