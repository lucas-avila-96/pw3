<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Planes</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2 class="my-4 text-center">Planes Disponibles</h2>
    <div class="row">
        <c:forEach var="plan" items="${plans}">
            <div class="col-md-4 mb-4">
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title">${plan.name}</h5>
                        <p class="card-text">
                            <strong>Cantidad de Clases:</strong> ${plan.classQuantity}
                        </p>
                        <p class="card-text">
                            <strong>Precio:</strong> $${plan.price}
                        </p>
                    </div>
                    <div class="card-footer">
                        <a href="#" class="btn btn-primary btn-block">Seleccionar Plan</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
