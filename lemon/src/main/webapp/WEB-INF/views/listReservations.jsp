<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- Para formatear fechas -->

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Mis Reservas de Entrenamiento</title>
        <link rel="stylesheet" href="styles.css">
    </head>
    <body>
        <div class="container">
            <h2>Mis Reservas de Entrenamiento</h2>

            <!-- Verificamos si el usuario tiene reservas -->
            <c:choose>
                <c:when test="${not empty reservations}">
                    <table class="reservations-table">
                        <thead>
                            <tr>
                                <th>Fecha</th>
                                <th>Hora</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Iteramos sobre las reservas -->
                            <c:forEach var="reservation" items="${reservations}">
                                <tr>
                                    <!-- Usamos fmt:formatDate para formatear la fecha -->
                                    <td><fmt:formatDate value="${reservation.reservationDate}" pattern="yyyy-MM-dd" /></td>
                                    <!-- Mostramos la hora directamente ya que es un string -->
                                    <td>${reservation.reservationTime}</td>
                                    <td>
                                        <!-- Formulario para cancelar la reserva -->
                                        <form action="/cancelReservation" method="post" style="display:inline;">
                                            <input type="hidden" name="reservationId" value="${reservation.reservationId}">
                                            <button type="submit" class="btn-cancel">Cancelar</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p>No tienes reservas de entrenamiento programadas.</p>
                </c:otherwise>
            </c:choose>


            <!-- Botón para crear una nueva reserva -->
            <a href="/scheduleTraining" class="btn-create-reservation">Programar Nuevo Entrenamiento</a>
        </div>
    </body>
</html>
