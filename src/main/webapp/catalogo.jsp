<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Catalogo</title>
</head>
<body>
	<%@ include file="/comunMenu.jsp"%>


  	<%
		String msg = (String)request.getAttribute("mensaje"); 
			if (msg==null) msg="";
	%>
	<div class="container">
	    
	     <label><%=msg%></label>
	</div>   
	  
	 <br>
	 <div class="container my-4 py-4">
	 <h1 class="">Productos Disponibles</h1>
    	<div class="row">
    		<c:forEach items="${lstProductos}" var="producto">
    		<div class="col-sm-4">
    			<div class="card my-4 shadow">
    				<div class="card-header text-center"> 
    					<label>${producto.nomProducto}</label>
    				</div>
    				<div class="card-body">
    					
    					<img class="mx-auto d-block" src="./images/${producto.idProducto}.jpg"  width="200" height="180">
    				</div>
    				<div class="card-footer text-center">
    					<label>Version: ${producto.nomProducto}</label> <i>Precio: S/. ${producto.precio}</i>
    					<div>
    							<a href="${pageContext.request.contextPath}/compra?opcion=addCar&cod=${producto.idProducto}" class="btn btn-outline-info">Agregar a Carrito</a>
    					</div>
    				</div>
    			</div>   		
    		</div>  
    		</c:forEach>	
    	</div>
  		</div>
<%@ include file="comunFooter.jsp"%>
</body>
</html>