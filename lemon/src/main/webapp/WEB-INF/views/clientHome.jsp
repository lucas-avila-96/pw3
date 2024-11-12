<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Client Home</title>
        <!-- Incluye los estilos de Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">

        <div class="d-flex min-vh-100">
            <!-- Contenido principal -->
            <div class="container-fluid p-4 flex-grow-1">
                <h1 class="mb-4">Bienvenido, ${userName} a Lemon</h1>
                <!-- Aquí puedes agregar más contenido relacionado con la página de inicio del cliente -->
                <div class="card">
                    <div class="card-body">
                        <!-- Muestra la cantidad de clases -->
                        <p class="card-text">Clases disponibles: ${classQuantity}</p>

                        <!-- Muestra las clases restantes -->
                        <p class="card-text">Clases restantes: ${classAvailable}</p>

                        <!-- Muestra la fecha de vencimiento -->
                        <p class="card-text">Fecha de vencimiento: ${expirationDate}</p>

                        <!-- Mensaje en caso de que no haya clases disponibles o el plan no esté disponible -->
                        <c:if test="${not empty message}">
                            <p class="text-danger">${message}</p>
                        </c:if>

                        <div class="card programar-entrenamiento">
                            <a href="scheduleTraining" class="btn btn-primary">
                                Programar Entrenamientos
                            </a>
                        </div>
                    </div>
                </div>

            </div>

        </div>


        <!-- Incluye los scripts de Bootstrap -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
