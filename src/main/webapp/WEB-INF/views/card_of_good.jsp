<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Каталог товаров</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
h1, table, td, h4 {
	text-align: center;
}

h4 {
	color: red;
}

.auth {
	float: right;
}

li {
	text-align: left;
}

table {
	margin: 0 auto;
	width: 100%;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script>
		function add(id){
		
			 let str = "id="+id;
	         $.ajax({
	               type: "POST",
	               url: "Catalog",
	               data: str,
	               success: function(answer){
	                 alert(answer);
	               }
	         });
		}
	</script>

</head>
<body>
	<c:if test="${userId == null}">
		<a class="auth" href="Auth">Регистрация/авторизация</a><br>
	</c:if>
	<h1>Карточка товара</h1>
	<c:if test="${userId == null}">
		<h4>ВНИМАНИЕ: для того, чтобы совершать покупки, вы должны
			авторизоваться!</h4>
	</c:if>
	<table border="1">

		<tr>
			<td width='50%' rowspan=2><img width='400'
				src="pics/${good.img}"></td>
			<th width='50%' height='50'>Характеристики</th>
		</tr>
		<tr>
			<td>
				<ul>
					<li>Название: ${good.title}</li>
					<li>Наличие в продаже: ${good.info}</li>
					<li>Цена: ${good.price}</li>
				</ul>
			</td>
		</tr>
		<tr>
		<c:if test="${userId != null}">
			<td>
				<button onclick=add(${good.id})>Добавить в корзину</button>
			</td>
		</c:if>
			<td>
				<button onclick="window.location.href='Catalog'">На главную</button>
			</td>
		</tr>
	</table>


</body>
</html>