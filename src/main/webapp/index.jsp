<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Compras - Ingresar</title>
</head>
<body>
<h1>¡Bienvenid@ al sistema de compras!</h1>
<h5>Por favor, identifíquese</h5>
<form action="login" method="POST">
    <label for="name">Apellido y nombre: </label>
    <input type="text" name="name" id="name" value="Leandro Manciagli">
    <br><br>
    <label for="postalCode">Código postal: </label>
    <input type="text" name="postalCode" id="postalCode" value="1900">
    <br><br>
    <label for="user">Usuario: </label>
    <input type="text" name="user" id="user" value="Leandro">
    <br><br>
    <label for="password">Contraseña: </label>
    <input type="password" name="password" id="password" value="BackInBlack1980">
    <br><br>
    <input type="submit" value="Ingresar">
</form>
</body>
</html>