<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.8.1/font/bootstrap-icons.min.css">
    <title>Crear Cuenta</title>
    <style>
        .container {
            display: flex;
            height: 100vh; /* Ocupa toda la altura de la ventana */
        }
        .form-section {
            flex: 1; /* Ocupa la mitad de la pantalla */
            padding: 30px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            background-color: #f8f9fa; /* Color de fondo */
        }
        .image-section {
            flex: 1; /* Ocupa la otra mitad de la pantalla */
            background-image: url('assets/images/lemon_background.png'); /* Cambia la ruta a tu imagen */
            background-size: cover;
            background-position: center;
        }
    </style>
</head>
<body>
<div class="container">
    <!-- Sección de formulario -->
    <div class="form-section">
        <h1 class="text-center">Crear Cuenta</h1>
        <form action="addUser" method="post">
            <div class="mb-3">
                <label for="firstName" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="firstName" name="first_name" required>
            </div>
            <div class="mb-3">
                <label for="lastName" class="form-label">Apellido</label>
                <input type="text" class="form-control" id="lastName" name="last_name" required>
            </div>
            <div class="mb-3">
                <label for="dni" class="form-label">DNI</label>
                <input type="text" class="form-control" id="dni" name="dni" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Correo</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Contraseña</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <div class="mb-3">
                <label for="userType" class="form-label">Tipo de Usuario</label>
                <select class="form-select" id="userType" name="user_type">
                    <option value="client">Cliente</option>
                    <option value="admin">Admin</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Registrarme</button>
        </form>
    </div>

    <!-- Sección de imagen -->
    <div class="image-section"></div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
