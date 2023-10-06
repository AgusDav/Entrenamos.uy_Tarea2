<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width", initial-scale=1,shrink-to-fit=no">
    <%@include file="header.jsp" %>
    <title>Agregar Usuario</title>
</head>

<body>
<form action="AgregarUsuario" method="post">
    <div class="form-group">
        <label for="inputNickname">Nickname</label>
        <input type="text"
                name="nickname"
                class="form-control"
                id="inputNickname"
                aria-describedby="emailHelp"
                placeholder="Nickname de usuario"
                value=""
        >
    </div>
    <div class="form-group">
        <label for="inputPassword">Password</label> <input type="text" name="password"
                                                      class="form-control" id="inputPasword"
                                                      placeholder="Password de usuario">
    </div>
    </div>
    <div class="form-group">
        <label for="inputEmail">E-mail</label> <input type="text" name="email"
                                                                 class="form-control" id="inputEmail"
                                                                 placeholder="E-mail de usuario">
    </div>
    </div>
    <div class="form-group">
        <label for="inputNombre">Nombre</label> <input type="text" name="nombre"
                                                                 class="form-control" id="inputNombre"
                                                                 placeholder="Nombre de usuario">
    </div>
    </div>
    <div class="form-group">
        <label for="inputFechaNacim">Fecha de nacimiento</label> <input type="date" name="fecNac"
                                                                              class="form-control" id="inputFechaNacim"
                                                                              placeholder="Fecha de nacimiento de usuario">
    </div>
    <div class="form-group">
        <label for="tipoUsuario">Tipo de Usuario</label>
        <select name="tipoUsuario" id="tipoUsuario" class="form-control">
            <option value=""selected disabled>Selecciona una opción</option>
            <option value="socio">Socio</option>
            <option value="profesor">Profesor</option>
        </select>
    </div>

    <div id="camposProfesor" style="display: none;">
        <!-- Aquí puedes agregar los campos adicionales para el profesor -->
        <div class="form-group">
            <label for="inputDesc">Descripcion</label> <input type="text" name="descripcion"
                                                           class="form-control" id="inputDesc"
                                                           placeholder="Descripcion de profesor"
                                                           value="">
        </div>
        <div class="form-group">
            <label for="inputBio">Biografia</label> <input type="text" name="biografia"
                                                              class="form-control" id="inputBio"
                                                              placeholder="Biografia de profesor">
        </div>
        <div class="form-group">
            <label for="inputWeb">Web</label> <input type="text" name="web"
                                                              class="form-control" id="inputWeb"
                                                              placeholder="Web del profesor">
        </div>
        <div class="form-group">
            <label for="inputInst">Institucion</label> <input type="text" name="institucion"
                                                              class="form-control" id="inputInst"
                                                              placeholder="implementar instituciones disponibles">
        </div>
        <!-- Agregar más campos según sea necesario para el profesor -->
    </div>



    <!--SECCION DE SCRIPTS-->
    <script>
        document.getElementById("tipoUsuario").addEventListener("change", function () {
            var camposProfesor = document.getElementById("camposProfesor");
            if (this.value === "profesor") {
                camposProfesor.style.display = "block"; // Mostrar campos adicionales
            } else {
                camposProfesor.style.display = "none"; // Ocultar campos adicionales
            }
        });
    </script>
    <script>
        // JavaScript para deseleccionar la opción al cargar la página
        document.addEventListener("DOMContentLoaded", function () {
            var selectTipoUsuario = document.getElementById("tipoUsuario");
            selectTipoUsuario.selectedIndex = -1; // Deseleccionar todas las opciones
        });
    </script>

    <button type="submit" class="btn btn-primary">Agregar Socio</button>
</form>

<%@include file="footer.jsp" %>
</body>
</html>