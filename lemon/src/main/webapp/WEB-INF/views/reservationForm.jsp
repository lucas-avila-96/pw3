<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Programar Entrenamiento</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <h2>Programar Entrenamiento</h2>

        <form action="createReservation" method="post">
            <div class="form-group">
                <label for="reservationDate">Fecha de entrenamiento:</label>
                <input type="date" id="reservationDate" name="reservationDate" required>
            </div>

            <div class="form-group">
                <label for="reservationTime">Hora de entrenamiento:</label>
                <input type="time" id="reservationTime" name="reservationTime" required>
            </div>

            <div class="form-group">
                <button type="submit" class="btn-submit">Programar Entrenamiento</button>
            </div>
        </form>
    </div>
</body>
</html>
