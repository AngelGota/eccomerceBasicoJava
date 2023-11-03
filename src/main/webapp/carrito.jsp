<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Carrito de compras</title>
<link href="DataTables/datatables.min.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="DataTables/datatables.min.js"></script>
<script>
		$(document).ready( function () {
		 
		    $('#table').DataTable({
		    	 "language": {
		             "lengthMenu": "Mostrar _MENU_ registros",
		             "zeroRecords": "No se encontraron resultados",
		             "info": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
		             "infoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
		             "infoFiltered": "(filtrado de un total de _MAX_ registros)",
		             "sSearch":"Buscar:",
		             "oPaginate":{
		            	 "sFirst":"Primero",
		            	 "sLast":"Último",
		            	 "sNext":"Siguiente",
		            	 "sPrevious":"Anterior",
		             },
		             "sProcessing":"Procesando..."
		         }
		    	
		    });
		} );
</script>
</head>
<body>
	<%@ include file="./comunMenu.jsp"%>
	<%
	String msg = (String) request.getAttribute("mensaje");
	if (msg == null)
		msg = "";
	%>

	<div class="container mt-4 my-4">
		<h1 class="">Carrito de compras:</h1>
		<br>
		<div class="row">
			<div class="col-sm-8">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Item</th>
							<th>Titulo</th>
							<th>Imagen</th>
							<th>Precio</th>
							<th>Cantidad</th>
							<th>SubTotal</th>
							<th>Accion</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${listaCarrito}" var="l">
							<tr>
								<td>${l.getItem()}</td>
								<td>${l.getDescripcion()}</td>
								<td><img src="images/${l.getIdProducto()}.jpg" width="100"
									height="100"></td>
								<td>${l.getPrecioCompra()}</td>
								<td><input type="number" id="cantidad"
									value="${l.getCantidad()}" class="form-control text-center"
									min="1" onchange="validar(${p.getIdProducto()},this);">
								</td>
								<td>${l.getSubTotal()}</td>
								<td><input type="hidden" id="idp"
									value="${l.getIdProducto()}"> <img
									src="images/delete32.png"><a
									href="${pageContext.request.contextPath}/compra?opcion=eliCar&cod=${l.getIdProducto()}">
										Eliminar </a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>

			<div class="card my-4 shadow" style="width: 18rem;">

				<h3 class="card-header text-center">Generar Compra</h3>

				<div class="card-body">
					<label>Subtotal: </label> <input type="text"
						value="S/. ${totalPagar}" readonly="" class="form-control">
					<label>Descuento: </label> <input type="text" value="S/. 0.00"
						readonly="" class="form-control"> <label>Total
						Pagar: </label> <input type="text" value="S/. ${totalPagar}" readonly=""
						class="form-control">
				</div>
				<div class="bd-example m-1 border-0">
					<a href="producto?menu=Catalogo&accion=Listar"
						class="btn btn-primary">Seguir comprando</a> <a
						href="${pageContext.request.contextPath}/compra?opcion=generarCompra&idUsuario=${datosusu.idUsuario}"
						class="btn btn-success">Finalizar Compra</a>
				</div>
			</div>


		</div>


	</div>

	<script>
	 	function validar(id,val){
  	
	 		$.ajax({
	 			type:'POST',
	 			url: "compra?opcion=actCar",
	 			data: "cod="+id+"&cantidad="+val.value,		
	 			success: function(data, textStatus, jqXHR){
	 				location.href="compra?opcion=carrito"
	 			}		
	 		});
	 		
	 </script>
</body>
<%@ include file="./comunFooter.jsp"%>
</html>