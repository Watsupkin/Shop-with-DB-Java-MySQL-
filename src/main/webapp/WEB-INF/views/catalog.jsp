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
		function getCatalog(){
	         $.ajax({
	               type: "GET",
	               url: "Catalog"
	         });
		}
	</script>
</head>
<body onload="getCatalog()">
	<c:if test="${userId == null}">
		<a class="auth" href="Auth">Регистрация/авторизация</a><br>
	</c:if>
	<c:if test="${userId != null}">
		<a class="auth" href="?drop=1">Exit</a><br>
	</c:if>
	<h1>Каталог товаров</h1>
	<c:if test="${userId == null}">
		<h4>ВНИМАНИЕ: для того, чтобы совершать покупки, вы должны
			авторизоваться!</h4>
	</c:if>
	<c:if test="${userId != null}">
		<h4>Здравствуйте, ${login}</h4>
	</c:if>
	<c:if test="${userId == 1}">
		<table border="1">
			<tr><td>Административная панель</td></tr>
			<tr>
				<td colspan=5 align='right'>
					<button onclick="window.location.href='Admin'">Перейти к заказам</button>
				</td>
			</tr>
		</table>
	</c:if>
	<table border="1">
		<tr>
			<th>Номер товара</th>
			<th>Название товара</th>
			<th>Стоимость товара</th>
			<th>Фото товара</th>
			<c:if test="${userId != null}">
				<th>Действие</th>
			</c:if>
		</tr>
		<c:forEach var="good" items="${goods}" varStatus="num">
			<tr>
				<td>${num.count}</td>
				<c:if test="${userId != null}">
					<td><a href="?goodId=${good.id}&userId=${userId}">${good.title}</a></td>
				</c:if>
				<c:if test="${userId == null}">
					<td><a href="?goodId=${good.id}">${good.title}</a></td>
				</c:if>
				<td>${good.price}</td>
				<td><img width="200" src="pics/${good.img}"></td>
				<c:if test="${userId != null}">
					<td><button onclick=add(${good.id})>Добавить в корзину</button></td>
				</c:if>
			</tr>
		</c:forEach>
		<c:if test="${userId != null}">
			<tr>
				<td colspan=5 align='right'>
					<button onclick="window.location.href='Cart'">Перейти в корзину покупок</button>
				</td>
			</tr>
			<tr>
				<td colspan=5 align='right'>
					<button onclick="window.location.href='ClientsOrders'">Ваши заказы</button>
				</td>
			</tr>
		</c:if>
	</table>
</body>
</html>