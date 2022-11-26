<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Заказ</title>
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
<script>
	function confirm(orderId){
		 let str = "orderId="+orderId;
        $.ajax({
              type: "POST",
              url: "Admin",
              data: str,
              success: function(answer){
                alert(answer);
				window.location.href = "Admin";
              }
        });
	}
</script>
</head>
<body>
	<h1>Заказы</h1>
	<table border="1">
		<tr>
			<th>ID заказа</th>
			<th>ID заказчика</th>
			<th>Имя заказчика</th>
			<th>Телефон заказчика</th>
			<th>Дата заказа</th>
			<th>Общая стоимость заказа</th>
			<th>Статус заказа</th>
			<th></th>
		</tr>
		<c:forEach var="order" items="${orders}" varStatus="num">
			<tr>
				<td>${order.orderId}</td>
				<td>${order.userId}</td>
				<td>${order.fio}</td>
				<td>${order.phone}</td>
				<td>${order.dateOfOrder}</td>
				<td>${order.sumOfOrder}</td>
				<td>${order.status}</td>
				<c:if test="${order.status == \"Оформлен\" }">
					<td>
						<button onclick=confirm(${order.orderId})>Подтвердить</button>
					</td>
				</c:if>
			</tr>
		</c:forEach>
		<tr>
			<th colspan=7 align='right'>
				<button onclick="window.location.href= 'Catalog'">На главную</button>
			</th>
		</tr>
	</table>
</body>
</html>