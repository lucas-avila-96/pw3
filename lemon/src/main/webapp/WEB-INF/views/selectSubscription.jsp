<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seleccionar Plan</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2 class="my-4 text-center">Planes Disponibles</h2>

    <!-- Mostrar mensaje de éxito o error -->
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success">${successMessage}</div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>

    <!-- Formulario para ingresar el DNI del cliente -->
    <div class="card mb-4">
        <div class="card-header">Buscar Cliente por DNI</div>
        <div class="card-body">
            <form action="searchUserByDNI" method="POST">
                <div class="form-group">
                    <label for="dni">DNI del Cliente:</label>
                    <input type="text" class="form-control" id="dni" name="dni" placeholder="Ingrese el DNI del cliente" required>
                </div>
                <button type="submit" class="btn btn-primary">Buscar Cliente</button>
            </form>
        </div>
    </div>

    <!-- Mostrar planes si el usuario fue encontrado -->
    <c:if test="${not empty user}">
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
                            <!-- Formulario para seleccionar plan y cargarlo al usuario -->
                            <form action="loadSubscription" method="POST">
                                <input type="hidden" name="user_id" value="${user.id}">
                                <input type="hidden" name="plan_id" value="${plan.id}">
                                <button type="submit" class="btn btn-primary btn-block">Seleccionar Plan</button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
