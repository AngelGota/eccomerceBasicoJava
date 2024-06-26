<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html class="h-100" lang="en" data-bs-theme="dark">
<head>
<meta charset="ISO-8859-1">
<title>Acceso|Registro</title>
	<!-- Custom Google font-->
    <link rel="icon" type="image/x-icon" href="./images/paragon.ico" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@100;200;300;400;500;600;700;800;900&amp;display=swap" />
    <!-- CDN Iconos BootStrap-->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
    <style>
		
        @media (min-width: 916px) {
            body {
                width: 30%; 
                margin: 0 auto;
            }
        }
        @media (max-width: 915px) {
            body {
                width: 100%;
            }
        }
    </style>
</head>
<body class="cover-container d-flex h-100 p-3 mx-auto flex-column">
    <header class="mb-auto">
    </header>
    <main class="px-3">
        <div class="cover-container p-3 rounded-5 shadow">
            <div class="container">
                <h1 class="text-center">Crea tu cuenta</h1>
                <form action="${pageContext.request.contextPath}/acceso" method="post">
                    <div class="mb-3">
                        <label for="Email" class="form-label">Correo</label>
                        <input name="txtCorreo" type="email" class="form-control" id="email" aria-describedby="emailHelp">
                    </div>
                    <div class="mb-3">
                        <label for="Clave" class="form-label">Nombres</label>
                        <input name="txtNombre" type="text" class="form-control" id="nombre">
                    </div>
                    <div class="mb-3">
                        <label for="Clave" class="form-label">Apellidos</label>
                        <input name="txtApellido" type="text" class="form-control" id="apellido">
                    </div>
                    <div class="mb-3">
                        <label for="Clave" class="form-label">Contraseņa</label>
                        <input name="txtClave" type="password" class="form-control" id="clave">
                    </div>
                    <div class="mb-3">
                        <label for="Clave" class="form-label">Direccion</label>
                        <input name="txtDireccion" type="text" class="form-control" id="direccion">
                    </div>
                    <div class="mb-3">
                        <label for="Clave" class="form-label">Telefono</label>
                        <input name="txtTelefono" type="text" class="form-control" id="telefono">
                    </div>
                    <div class="text-center">
                    	<button type="submit" class="btn btn-primary" name="opcion" value="reg">Crear cuenta</button>
                    	<p>
                    		Ya tienes una cuenta?<a href="${pageContext.request.contextPath}/accesoIniciarSesion.jsp">Inicia sesion</a>            
                    	</p>    
                    </div>     
                </form>
            </div>
        </div>
    </main>
    <footer class="mt-auto">
    </footer>
</body>
</html>