<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Compra confirmada</title>
</head>
<body>
<%@ include file="./comunMenu.jsp"%>
<main>
<section class="container">
	<div class="px-5 py-5 my-5 text-center">
		<img class="d-block mx-auto mb-4"
			src="https://i.postimg.cc/C5kQ9pg4/confirm.png" alt="" width="250"
			height="250">
		<h1 class="display-5 fw-bold text-name">Exito</h1>
		<div class="col-lg-6 mx-auto">
			<p class="lead mb-4 text-name fw-bolder">¡Su orden fue confirmada!</p>
			<div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
				<a href="producto?menu=Catalogo&accion=Listar" type="button" class="btn btn-outline-success btn-lg px-4 gap-3">Volver al cátalogo</a>
				<a href="compra?opcion=his&cod=${datosusu.idUsuario}" class="btn btn-outline-info btn-lg px-4 gap-3">Ver el historial</a>
			</div>
		</div>
	</div>
</section>
</main>
</body>
<%@ include file="./comunFooter.jsp"%>
</html>