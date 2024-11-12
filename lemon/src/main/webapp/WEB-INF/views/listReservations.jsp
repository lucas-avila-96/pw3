<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- Para formatear fechas -->

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Mis Reservas de Entrenamiento</title>
        <link rel="stylesheet" href="styles.css">
        <style>


            h2 {
                text-align: center;
                color: #1a73e8;
            }

            /* Table styling */
            .reservations-table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }

            .reservations-table thead th {
                background-color: #1a73e8;
                color: #fff;
                padding: 12px;
                text-align: left;
                font-size: 1rem;
                border-top-left-radius: 8px;
                border-top-right-radius: 8px;
            }

            .reservations-table tbody tr {
                border-bottom: 1px solid #ddd;
            }

            .reservations-table tbody tr:nth-child(even) {
                background-color: #f9f9f9;
            }

            .reservations-table tbody td {
                padding: 12px;
                text-align: left;
                font-size: 0.95rem;
                color: #333;
            }

            /* Button styling */
            .btn-cancel {
                background-color: #ff4d4f;
                color: #fff;
                border: none;
                border-radius: 4px;
                padding: 6px 12px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .btn-cancel:hover {
                background-color: #d9363e;
            }

            .btn-create-reservation {
                display: inline-block;
                text-align: center;
                background-color: #4CAF50;
                color: white;
                padding: 10px 20px;
                font-size: 1rem;
                border-radius: 5px;
                margin-top: 20px;
                text-decoration: none;
                transition: background-color 0.3s ease;
            }

            .btn-create-reservation:hover {
                background-color: #43a047;
            }

        </style>
    </head>

    <body>
        <div class="d-flex min-vh-100">

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
                                            <form action="cancelReservation" method="post" style="display:inline;">
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
                <a href="scheduleTraining" class="btn-create-reservation">Programar Nuevo Entrenamiento</a>
            </div>
        </div>

    </body>
</html>
