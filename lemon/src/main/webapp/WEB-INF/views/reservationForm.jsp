<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Programar Entrenamiento</title>
        <link rel="stylesheet" href="styles.css">
        <style>
            /* Estilos generales */
            body {
                font-family: Arial, sans-serif;
                background-color: #f3f4f6;
                color: #333;
                margin: 0;
                padding: 0;
            }
            .container {
                width: 80%;
                margin: auto;
                padding: 20px;
                border-radius: 12px;
                background-color: #ffffff;
                box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
                margin-top: 40px;
                text-align: center;
            }
            h2 {
                color: #4CAF50;
                font-size: 2em;
                margin-bottom: 20px;
            }
            .day-card {
                margin: 15px 0;
                padding: 20px;
                background-color: #e8f5e9;
                border-radius: 10px;
                box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
            }
            .day-card h3 {
                font-size: 1.4em;
                color: #333;
                margin-bottom: 10px;
            }
            .list-group {
                list-style: none;
                padding: 0;
                margin: 0;
            }
            .list-group-item {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 10px 20px;
                margin: 5px 0;
                border: 1px solid #4CAF50;
                border-radius: 5px;
                background-color: #f0f8ff;
                transition: background-color 0.3s ease;
            }
            .list-group-item:hover {
                background-color: #e0f2e9;
            }
            .btn-reservar {
                padding: 8px 16px;
                background-color: #4CAF50;
                color: white;
                border: none;
                border-radius: 5px;
                text-decoration: none;
                font-size: 0.9em;
                transition: background-color 0.3s ease, transform 0.2s ease;
            }
            .btn-reservar:hover {
                background-color: #388E3C;
                transform: scale(1.05);
            }
            .btn-submit {
                margin-top: 20px;
                padding: 12px 25px;
                background-color: #4CAF50;
                color: white;
                border: none;
                font-size: 1em;
                cursor: pointer;
                border-radius: 5px;
                transition: background-color 0.3s ease, transform 0.2s ease;
            }
            .btn-submit:hover {
                background-color: #388E3C;
                transform: scale(1.05);
            }
        </style>

    </head>
    <body>
        <div class="container">
            <h2>Programar Entrenamiento</h2>

            <form action="createReservation" method="post">
                <input type="hidden" id="userId" name="userId" required>

                <c:forEach var="day" items="${weekDates}">
                    <div class="day-card">
                        <h3>${day.day} - ${day.date}</h3>
                        <c:if test="${not empty horariosPorDia[day.day]}">
                            <ul class="list-group">
                                <c:forEach var="schedule" items="${horariosPorDia[day.day]}">
                                    <li class="list-group-item">
                                        ${schedule.availableTime} <!-- Muestra el tiempo como string directamente -->
                                        <a href="createReservation?userId=${userId}&scheduleId=${schedule.id}&reservationDate=${day.date}&reservationTime=${schedule.availableTime}" class="btn-reservar">Reservar</a>
                                    </li>
                                </c:forEach>

                            </ul>
                        </c:if>
                    </div>
                </c:forEach>
            </form>
        </div>
    </body>
</html>
