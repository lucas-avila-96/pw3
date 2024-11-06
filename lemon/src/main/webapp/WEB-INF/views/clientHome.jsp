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
            <!-- Barra lateral -->
            <div class="d-flex flex-column flex-shrink-0 p-3 text-white bg-dark" style="width: 280px;">
                <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                    <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"></use></svg>
                    <span class="fs-4">Lemon Gym</span>
                </a>
                <hr>
                <ul class="nav nav-pills flex-column mb-auto">
                    <li class="nav-item">
                        <a href="/clientHome" class="nav-link active" aria-current="page">
                            <svg class="bi me-2" width="16" height="16"><use xlink:href="#home"></use></svg>
                            Inicio
                        </a>
                    </li>
                    <li>
                        <a href="/reservas" class="nav-link text-white">
                            <svg class="bi me-2" width="16" height="16"><use xlink:href="#calendar"></use></svg>
                            Mis Reservas
                        </a>
                    </li>
                </ul>
                <hr>
                <div class="dropdown">
                    <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                        <img src="https://github.com/mdo.png" alt="" width="32" height="32" class="rounded-circle me-2">
                        <strong>Usuario</strong>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark text-small shadow" aria-labelledby="dropdownUser1">
                        <li><a class="dropdown-item" href="#">Perfil</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="/logout">Cerrar sesión</a></li>
                    </ul>
                </div>
            </div>

            <!-- Contenido principal -->
            <div class="container-fluid p-4 flex-grow-1">
    <h1 class="mb-4">Bienvenido, ${userName} a Lemon</h1>
                <!-- Aquí puedes agregar más contenido relacionado con la página de inicio del cliente -->
                <div class="card">
                    <div class="card-body">
                        <!-- Muestra la cantidad de clases -->
                        <p class="card-text">Clases disponibles: ${classQuantity}</p>

                        <!-- Muestra la fecha de vencimiento -->
                        <p class="card-text">Fecha de vencimiento: ${expirationDate}</p>

                        <!-- Mensaje en caso de que no haya clases disponibles o el plan no esté disponible -->
                        <c:if test="${not empty message}">
                            <p class="text-danger">${message}</p>
                        </c:if>
                    </div>
                </div>

            </div>
        </div>

        <!-- Incluye los scripts de Bootstrap -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
