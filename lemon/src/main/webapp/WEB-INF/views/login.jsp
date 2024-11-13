<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #12202D;
            margin: 0;
        }
        .login-page {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .form-container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 450px;
        }
        
        .input-group-text {
            background-color: #f0f0f0;
        }
        .input-group input {
            border-radius: 4px;
            font-size: 16px;
        }
        button {
            width: 100%;
            padding: 10px;
            border: none;
            background-color: #28a745;
            color: white;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #218838;
        }
        .signup-link {
            text-align: center;
            margin-top: 20px;
        }
        .signup-link a {
            color: #007bff;
            text-decoration: none;
        }
        .signup-link a:hover {
            text-decoration: underline;
        }
        
        /* Asegurando que la imagen de fondo cubra todo el área */
        .image-container {
            width: 100%;
            height: 100%;
            background: url('images/Logo.jpg') no-repeat center center;
            background-size: cover;
            background-position: center;
        }
    </style>
</head>
<body>

    <div class="login-page">
        <div class="container">
            <div class="row">
                <!-- Formulario de inicio de sesión -->
                <div class="col-md-7">
                    <div class="form-container">
                        <h2>Iniciar Sesión</h2>
                        <form action="login" method="post">
                            <div class="mb-3">
                                <label for="dni" class="form-label">DNI:</label>
                                <div class="input-group">
                                    <div class="input-group-text"><i class="bi bi-person-fill"></i></div>
                                    <input type="text" id="dni" name="dni" class="form-control" placeholder="Ingrese su DNI" required>
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">Contraseña:</label>
                                <div class="input-group">
                                    <div class="input-group-text"><i class="bi bi-lock-fill"></i></div>
                                    <input type="password" id="password" name="password" class="form-control" placeholder="Ingrese su contraseña" required>
                                </div>
                            </div>
                            
                            <button type="submit" class="btn btn-primary mt-4">Iniciar sesión</button>
                        </form>
                        <div class="signup-link">
                            <a href="registerUser">¿No tienes cuenta? Regístrate aquí</a>
                        </div>
                    </div>
                </div>

                <!-- Imagen de fondo a la derecha -->
                <div class="col-md-5 d-none d-md-block">
                    <div class="form-right h-100 d-flex flex-column justify-content-center">
                        <div class="image-container"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
