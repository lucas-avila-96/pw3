<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administrador - Página de Inicio</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .card-header {
            background-color: #007bff;
            color: white;
        }
        .card-body {
            background-color: #f8f9fa;
        }
        h2 {
            text-align: center;
            color: #007bff;
        }
        .form-group label {
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h2>Bienvenido, Administrador</h2>        

        <!-- Formulario para cargar abono -->
        <div class="card mt-4">
            <div class="card-header">
                Cargar Abono para Cliente
            </div>
            <div class="card-body">
                <a href="selectSubscription">seleccionar abono</a>

            </div>
        </div>

        <!-- Listar asistencia del día -->
        <div class="card mt-4">
            <div class="card-header">
                Asistencia del Día
            </div>
            <div class="card-body">
                <form action="adminController" method="POST" class="mb-3">
                    <input type="hidden" name="action" value="listAttendance">
                    <button type="submit" class="btn btn-info w-100">Ver Asistencia</button>
                </form>
            </div>
        </div>

    </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>
</html>
