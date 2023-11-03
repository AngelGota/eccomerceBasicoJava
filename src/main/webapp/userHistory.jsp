<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="interfaces.EstadoDAO"%>
<%@page import="beans.ProductoDTO"%>
<%@page import="beans.EstadoDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.DAOFactory"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tu historial</title>
<!-- Jquery y datatables-->
<link href="DataTables/datatables.min.css" rel="stylesheet" />
<script src="js/jquery.js"></script>
<script src="DataTables/datatables.min.js"></script>
<script>
	$(document)
			.ready(
					function() {
						$('#table')
								.DataTable(
										{
											"language" : {
												"lengthMenu" : "Mostrar _MENU_ registros",
												"zeroRecords" : "No se encontraron resultados",
												"info" : "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
												"infoEmpty" : "Mostrando registros del 0 al 0 de un total de 0 registros",
												"infoFiltered" : "(filtrado de un total de _MAX_ registros)",
												"sSearch" : "Buscar:",
												"oPaginate" : {
													"sFirst" : "Primero",
													"sLast" : "Último",
													"sNext" : "Siguiente",
													"sPrevious" : "Anterior",
												},
												"sProcessing" : "Procesando..."
											}
										});
					});
</script>
</head>
<body class="d-flex flex-column h-100">
	<%@ include file="./comunMenu.jsp"%>
	<div class="container p-md-5">
		<div class="card mb-3 shadow">
			<div class="row g-0">
				<div class="card-body">
						<div class="text-center title">
							<h1>Historial de compras</h1>
							<hr>
						</div>
						<div class="table-responsive">
							<table id="table" class="table">
								<thead>
									<tr>
										<th>IdVenta</th>
										<th>IdUsuario</th>
										<th>FechaRegistro</th>
										<th>Total</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="gs" items="${lstHistory}">
										<tr>
											<td>${gs.getIdDetalle()}</td>
											<td>${gs.getIdUsuario()}</td>
											<td>${gs.getFregistro()}</td>
											<td>${gs.getTotal()}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
			</div>
		</div>
	</div>
</body>
<%@ include file="./comunFooter.jsp"%>
</html>