<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- Custom Google font-->
<link rel="icon" type="image/x-icon" href="./images/paragon.ico" />
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" />
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@100;200;300;400;500;600;700;800;900&amp;display=swap" />
<!-- CDN Iconos BootStrap-->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
<!-- Core theme CSS (includes Bootstrap)-->
<link href="${pageContext.request.contextPath}/css/styles.css"
	rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/contador.js"></script>
</head>
<body>
	<!-- Navigation-->
	<main>
		<nav class="navbar navbar-expand-lg bg-dark py-4 shadow ">
			<div class="container px-5">
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<a class="navbar-brand" href="home.jsp"><span class="fw-bolder">
							SistemaPedido </span></a>
					<ul class="navbar-nav ms-auto mb-2 mb-lg-0 small fw-bolder">
						<li class="nav-item"><a class="nav-link" href="home.jsp">Home</a></li>
						<li class="nav-item"><a class="nav-link"
							href="producto?menu=Catalogo&accion=Listar">Catalogo</a></li>
					</ul>

				</div>
				<div class="justify-content-center">
					<ul class="nav fw-bolder">
						<div class="nav-item-mof px-2">
							<a class="d-flex align-items-center link-cesta"
								href="compra?opcion=carrito" aria-expanded="false"> <svg
									xmlns="http://www.w3.org/2000/svg" width="32" height="32"
									class="bi bi-cart car-sales" viewBox="0 0 16 16">
                        		<path
										d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
                        		</svg> <span id="contador" class="cart-counter">${contador}</span>
								<span class="nav-link-mof">Carrito</span>
							</a>
						</div>
						<div class="nav nav-item dropdown dmenu">
							<a href="#"
								class="link-body-emphasis text-decoration-none dropdown-toggle"
								aria-expanded="false" data-bs-toggle="dropdown"> <img
								src="https://i.postimg.cc/j58FVRPg/soygodbby.png" alt="user"
								width="32" height="32" class="rounded-circle">
								${datosusu.nombre} ${datosusu.apellido}
							</a>
							<ul class="dropdown-menu text-small shadow">

								<li><a
									class="nav-link dropdown-item d-flex align-items-center gap-2"
									href="compra?opcion=his&cod=${datosusu.idUsuario}">Historial
								</a></li>
								<li>
									<hr class="dropdown-divider">
								</li>
								<li>
									<form action="${pageContext.request.contextPath}/acceso" method="post">
										<button type="submit" name="opcion" value="logout" class="nav-link dropdown-item d-flex align-items-center gap-2"
											href="acceso?opcion=logout">Cerrar sesión </button>
									</form>
								</li>
							</ul>
						</div>
					</ul>
				</div>
			</div>
		</nav>
	</main>
</body>
</html>
