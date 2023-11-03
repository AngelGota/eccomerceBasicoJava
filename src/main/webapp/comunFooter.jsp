<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>include footer</title>

</head>
<body>

</body>
<div class="shadow">
	<div class="container py-3 my-0">
		<footer class="py-3 my-4">
			<ul class="nav justify-content-center pb-3 mb-3">
				<li class="ms-3"><a href="#" aria-expanded="false"> <img
						onMouseOver="pasar(this)" onMouseOut="salir(this)" width="32"
						height="32" src="./images/instagram.png" alt="" target="_blank" />
				</a></li>
				<li class="ms-3"><a class="text-body-secondary" href="#"> <img
						onMouseOver="pasar(this)" onMouseOut="salir(this)" width="32"
						height="32" src="./images/twitter.png" alt="" target="_blank" />
				</a></li>
				<li class="ms-3"><a class="text-body-secondary" href="#"> <img
						onMouseOver="pasar(this)" onMouseOut="salir(this)" width="32"
						height="32" src="./images/facebook.png" alt="" target="_blank" />
				</a></li>
			</ul>
			<hr class="shadow">
			<p class="text-center text-light">
				&copy; 2023 SistemaPedido, designed and development by 
				<a class="text-decoration-none text-name" href="https://www.linkedin.com/in/angelogota/" target="_blank">
					Yo pes
				</a>
			</p>
		</footer>
	</div>
</div>

<script src="${pageContext.request.contextPath}/js/script_focusfooter.js"></script>
</html>