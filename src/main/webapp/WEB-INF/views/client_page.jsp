<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Заказы</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
h1, table, td {
	text-align: center;
}

table {
	margin: 0 auto;
	width: 100%;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>

</head>
<body>
	<h1>Ваши заказы</h1>
	<table border="1">
		<tr>
			<th>Номер заказа</th>
			<th>Дата заказа</th>
			<th>Общая стоимость заказа</th>
			<th>Статус заказа</th>
		</tr>
		<c:forEach var="order" items="${orders}" varStatus="num">
			<tr>
				<td>${order.orderId}</td>
				<td>${order.dateOfOrder}</td>
				<td>${order.sumOfOrder}</td>
				<td>${order.status}</td>
			</tr>
		</c:forEach>
		<tr>
			<th colspan=5 align='right'>
				<button onclick="window.location.href= 'Catalog'">На главную</button>
			</th>
		</tr>
	</table>
</body>
</html>