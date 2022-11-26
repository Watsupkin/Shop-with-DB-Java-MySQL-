<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Каталог товаров</title>
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
	function remove(id){
		 let str = "good_id="+id;
        $.ajax({
              type: "POST",
              url: "Cart",
              data: str,
              success: function(answer){
                alert(answer);
				window.location.href = "Cart";
              }
        });
	}
</script>

</head>
<body>
	<h1>Корзина покупок</h1>
	<table border="1">
		<tr>
			<th>АйДи товара</th>
			<th>Название товара</th>
			<th>Стоимость товара</th>
			<th>Колличество товара</th>
			<th>Общая сумма заказа</th>
			<th></th>
		</tr>
		<c:forEach var="cart" items="${goodsCart}" varStatus="num">
			<tr>
				<td>${cart.good_id}</td>
				<td>${cart.title}</td>
				<td>${cart.price}</td>
				<td>${cart.count}</td>
				<td>${cart.sum}</td>
				<td>
					<button onclick=remove(${cart.good_id})>Убрать</button>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<th colspan=5 align='right'>
			<c:if test="${goodsCart.size()>0}">
				<button onclick="window.location.href= 'Order'">Оформить заказ</button>
			</c:if>
				<button onclick="window.location.href= 'Catalog'">На главную</button>
			</th>
		</tr>
	</table>
</body>
</html>