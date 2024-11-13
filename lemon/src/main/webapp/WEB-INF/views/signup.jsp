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
        body {
            font-family: Arial, sans-serif;
            background-color: #12202D;
            margin: 0;
        }
        .signup-page {
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
        
        .image-container {
            width: 100%;
            height: 100%;
            background: url('images/Logo.jpg') no-repeat center center;
            background-size: contain;
            background-position: center;
        }
    </style>
</head>
<body>
    <div class="signup-page">
        <div class="container">
            <div class="row">
                <!-- Formulario de creación de cuenta -->
                <div class="col-md-7">
                    <div class="form-container">
                        <h2 class="text-center">Crear Cuenta</h2>
                        <form action="addUser" method="post">
                            <div class="mb-3">
                                <label for="firstName" class="form-label">Nombre</label>
                                <div class="input-group">
                                    <div class="input-group-text"><i class="bi bi-person-fill"></i></div>
                                    <input type="text" id="firstName" name="first_name" class="form-control" placeholder="Ingrese su nombre" required>
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="lastName" class="form-label">Apellido</label>
                                <div class="input-group">
                                    <div class="input-group-text"><i class="bi bi-person-fill"></i></div>
                                    <input type="text" id="lastName" name="last_name" class="form-control" placeholder="Ingrese su apellido" required>
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="dni" class="form-label">DNI</label>
                                <div class="input-group">
                                    <div class="input-group-text"><i class="bi bi-person-badge"></i></div>
                                    <input type="text" id="dni" name="dni" class="form-control" placeholder="Ingrese su DNI" required>
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">Correo</label>
                                <div class="input-group">
                                    <div class="input-group-text"><i class="bi bi-envelope-fill"></i></div>
                                    <input type="email" id="email" name="email" class="form-control" placeholder="Ingrese su correo electrónico" required>
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">Contraseña</label>
                                <div class="input-group">
                                    <div class="input-group-text"><i class="bi bi-lock-fill"></i></div>
                                    <input type="password" id="password" name="password" class="form-control" placeholder="Ingrese su contraseña" required>
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="userType" class="form-label">Tipo de Usuario</label>
                                <select class="form-select" id="userType" name="user_type" required>
                                    <option value="client">Cliente</option>
                                    <option value="admin">Admin</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary mt-4">Registrarme</button>
                        </form>
                    </div>
                    <div class="signup-link">
                        <a href="login">¿Ya tienes cuenta? Inicia sesión aquí</a>
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
