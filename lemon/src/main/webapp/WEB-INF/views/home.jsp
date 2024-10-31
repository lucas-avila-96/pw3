<%
    // Simulación de obtener el usuario de sesión y suscripciones
    //Usuario usuario = (Usuario) session.getAttribute("usuario");
    //Map<String, Object> suscripciones = (Map<String, Object>) session.getAttribute("suscripciones"); // Ejemplo de suscripciones
    int clasesTotales = 10; // Asumiendo un valor por defecto o proveniente de la base de datos
    int clasesRestantes = 5; // Asumiendo un valor por defecto o proveniente de la base de datos
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">

    <style>
        body {
            background-color: #f8f9fa;
        }
        .sidebar {
            width: 280px;
            height: 100vh;
            position: fixed;
            top: 0;
            left: 0;
            background-color: #343a40;
            padding: 20px;
        }
        .main-content {
            margin-left: 300px;
            padding: 20px;
        }
        
        
.mis-clases-card {
    background-color: #fff;
    border-radius: 12px;
    padding: 16px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    margin: 16px 0;
}

.mis-clases-card h2 {
    font-size: 20px;
    font-weight: bold;
    color: #4A4A4A; /* Color similar al título en Flutter */
}

.no-suscripcion {
    margin: 12px 0;
}

.no-suscripcion p {
    font-size: 16px;
    color: #757575; /* Color de información */
}

.btn {
    display: inline-block;
    background-color: #13212E;
    color: white;
    padding: 10px 20px;
    border-radius: 8px;
    text-decoration: none;
    margin-top: 12px;
}

.progress-bar {
    background-color: #e0e0e0;
    border-radius: 4px;
    height: 6px;
    overflow: hidden;
    margin: 8px 0;
}

.progress {
    background-color: #13212E; /* Color de progreso */
    height: 100%;
    transition: width 0.3s;
}



.main-content {
    padding: 20px;
}

.plan-list {
    display: flex;
    flex-wrap: wrap;
    gap: 16px;
}

.plan-card {
    background-color: #fff;
    border-radius: 12px;
    padding: 16px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    flex: 1 1 300px; /* Flex para que se ajusten en diferentes tamaños de pantalla */
}

.plan-card h2 {
    font-size: 20px;
    font-weight: bold;
    color: #4A4A4A;
}

.plan-card p {
    font-size: 16px;
    color: #757575;
}

.btn {
    display: inline-block;
    background-color: #13212E;
    color: white;
    padding: 10px 20px;
    border-radius: 8px;
    text-decoration: none;
    margin-top: 12px;
}


    </style>
    
</head>
<body>

<div class="sidebar d-flex flex-column flex-shrink-0 p-3 text-white bg-dark">
    <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
    <img src="images/logo.jpg" alt="Logo de Lemon" width="40" height="32" class="me-2"> 
    <span class="fs-4">Lemon</span>
</a>

    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
        <li class="nav-item">
            <a href="#" class="nav-link active" aria-current="page">
            <i class="bi bi-house" style="font-size: 18px;"></i>
                Inicio
            </a>
        </li>
        <li>
            <a href="#" class="nav-link text-white">
            <i class="bi bi-calendar" style="font-size: 18px;"></i>
                Mis Reservas
            </a>
        </li>
    </ul>
    <hr>
    <div class="dropdown">
        <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
            <img src="https://github.com/mdo.png" alt="" width="32" height="32" class="rounded-circle me-2">
            <strong>Lucas</strong>
        </a>
        <ul class="dropdown-menu dropdown-menu-dark text-small shadow" aria-labelledby="dropdownUser1">
            <li><a class="dropdown-item" href="#">Cerrar Sesion</a></li>
        </ul>
    </div>
</div>


    <div class="main-content">
    
    <div class="mis-clases-card">
        <h2>Mis Clases</h2>
        <%
            if (false) {
        %>
            <div class="no-suscripcion">
                <p>No tienes suscripciones activas</p>
                <a href="plans" class="btn">Comprar clases</a>
            </div>
        <%
            } else {
        %>
            <p><%= clasesRestantes %> clases de <%= clasesTotales %> disponibles</p>
            <div class="progress-bar">
                <div class="progress" style="width: <%= clasesTotales > 0 ? (clasesRestantes * 100 / clasesTotales) : 0 %>%"></div>
            </div>
            <a href="plans" class="btn">Comprar clases</a>
        <%
            }
        %>
    </div>
    
<a href="programacionClases" class="btn btn-primary btn-block mb-3 d-flex align-items-center">
    <i class="bi bi-pencil" style="font-size: 30px;"></i>
    <h5 class="card-title ms-3 mb-0">Programar entrenamiento</h5>
    <i class="bi bi-caret-right ms-auto" style="font-size: 30px;"></i>
</a>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
