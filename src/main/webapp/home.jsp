<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	<%@ include file="/comunMenu.jsp"%>
<section class="container">
	<div class="px-5 py-5 my-5 text-center">
		<h1 class="display-5 fw-bold text-name">SistemaPedido</h1>
		<div class="col-lg-6 mx-auto">
			<p class="lead mb-4 text-name">El sistema de gestion de librerias para potenciar tu negocio.
				Paragon es la solucion integral que necesitas para administrar eficientemente tu libreria. 
				Disenado con las ultimas tecnologias y pensado en tus necesidades, nuestro sistema te brinda 
				un conjunto completo de herramientas para simplificar tus operaciones diarias y maximizar tu exito.</p>
			<div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
				<a class="btn btn-outline-light btn-lg px-4 gap-3" href="producto?menu=Catalogo&accion=Listar">Ver catalogo</a>
			</div>
		</div>
	</div>
</section>
<%@ include file="comunFooter.jsp"%>
</html>