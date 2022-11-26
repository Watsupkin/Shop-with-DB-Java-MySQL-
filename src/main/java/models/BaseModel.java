package models;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

public class BaseModel {
	static List<Good> goods = new ArrayList<Good>();
	static List<GoodCart> goodsCart = new ArrayList<GoodCart>();
	static List<User> users = new ArrayList<User>();
	static List<OrderModel> orders = new ArrayList<OrderModel>();
	static List<AdminsInfo> admins = new ArrayList<AdminsInfo>();

	public static List<Good> getGoods()
			throws ClassNotFoundException, SQLException, XPathExpressionException, FileNotFoundException {
		var sql = "select * from goods";
		var rs = ShopORM.select(sql);
		goods.clear();
		while (rs.next()) {
			var id = rs.getInt("good_id");
			var title = rs.getString("title");
			var price = rs.getInt("price");
			var info = rs.getString("info");
			var img = rs.getString("img");
			goods.add(new Good(id, title, price, info, img));
		}
		return goods;
	}

	private static List<User> getUsers()
			throws ClassNotFoundException, SQLException, XPathExpressionException, FileNotFoundException {
		var rs = ShopORM.select("select * from users");
		users.clear();
		while (rs.next()) {
			var userId = rs.getInt("user_id");
			var role = rs.getInt("role");
			var login = rs.getString("login");
			var pass = rs.getString("pass");
			var fio = rs.getString("fio");
			var phone = rs.getString("phone");
			users.add(new User(userId, role, login, pass, fio, phone));
		}
		return users;
	}

	public static int getIdBySign(String login, String pass)
			throws ClassNotFoundException, XPathExpressionException, FileNotFoundException, SQLException {
		for (var user : getUsers()) {
			if (user.getLogin().equals(login) && user.getPass().equals(pass)) {
				return user.getUserId();
			}
		}
		return 0;
	}

	public static boolean addUser(String login, String pass, String fio, String phone)
			throws ClassNotFoundException, SQLException, XPathExpressionException, FileNotFoundException {
		var ps = ShopORM.getStatement("insert into users(login,pass,fio,phone) values(?,?,?,?)");
		ps.setString(1, login);
		ps.setString(2, pass);
		ps.setString(3, fio);
		ps.setString(4, phone);
		if (ps.executeUpdate() > 0) {
			return true;
		}
		return false;
	}
	public static String getLoginById(int userId) throws XPathExpressionException, FileNotFoundException, ClassNotFoundException, SQLException {
		String login = "";
		var rs = ShopORM.select("select login from users where user_id="+userId);
		while(rs.next()) {
			login = rs.getString("login");
		}
		return login;
	}

	public static List<GoodCart> getGoodsFromCart(int userId)
			throws ClassNotFoundException, SQLException, XPathExpressionException, FileNotFoundException {
		var sql = "SELECT `title`,`price`,goods.good_id,`count`,`user_id`,(price*count) as 'sum' FROM goods inner join cart ON goods.good_id=cart.good_id WHERE user_id="
				+ userId;
		var rs = ShopORM.select(sql);
		goodsCart.clear();
		while (rs.next()) {
			var goodId = rs.getInt("good_id");
			var title = rs.getString("title");
			var price = rs.getInt("price");
			var count = rs.getInt("count");
			userId = rs.getInt("user_id");
			var sum = rs.getInt("sum");
			goodsCart.add(new GoodCart(goodId, count, userId, price, title, sum));
		}
		return goodsCart;
	}

	public static boolean addToCart(int id, int userId)
			throws ClassNotFoundException, SQLException, XPathExpressionException, FileNotFoundException {
		var ps = ShopORM.getStatement("select id from cart where good_id=? and user_id=?");
		ps.setInt(1, id);
		ps.setInt(2, userId);
		var rs = ps.executeQuery();
		if (rs.next()) {
			var ps1 = ShopORM.getStatement("update cart set count=count+1 where good_id=? and user_id=?");
			ps1.setInt(1, id);
			ps.setInt(2, userId);
			if (ps1.executeUpdate() > 0) {
				return true;
			}
		} else {
			var ps1 = ShopORM.getStatement("insert into cart(user_id,good_id,count) values(?,?,?)");
			ps1.setInt(1, userId);
			ps1.setInt(2, id);
			ps1.setInt(3, 1);
			if (ps1.executeUpdate() > 0) {
				return true;
			}
		}
		return false;
	}

	public static boolean removeFromCart(int goodId)
			throws ClassNotFoundException, SQLException, XPathExpressionException, FileNotFoundException {
		if (ShopORM.execute("delete from cart WHERE good_id= " + goodId) > 0)
			return true;
		return false;
	}

	public static List<OrderModel> getOrders()
			throws XPathExpressionException, FileNotFoundException, ClassNotFoundException, SQLException {
		var sql = "SELECT * FROM orders";
		var rs = ShopORM.select(sql);
		orders.clear();
		while (rs.next()) {
			var orderId = rs.getInt("order_id");
			var dateOfOrder = rs.getTimestamp("date_of_order");
			var userId = rs.getInt("user_id");
			var sumOrder = rs.getInt("sum_order");
			var status = rs.getString("status");
			orders.add(new OrderModel(orderId, dateOfOrder, userId, sumOrder, status));
		}
		return orders;
	}

	public static List<OrderModel> getOrdersById(int userId)
			throws XPathExpressionException, FileNotFoundException, ClassNotFoundException, SQLException {
		var sql = "SELECT * FROM orders WHERE user_id=" + userId;
		var rs = ShopORM.select(sql);
		orders.clear();
		while (rs.next()) {
			var orderId = rs.getInt("order_id");
			var dateOfOrder = rs.getTimestamp("date_of_order");
			var sumOrder = rs.getInt("sum_order");
			var status = rs.getString("status");
			orders.add(new OrderModel(orderId, dateOfOrder, userId, sumOrder, status));
		}
		return orders;
	}

	public static boolean doOrder(int userId)
			throws ClassNotFoundException, XPathExpressionException, FileNotFoundException, SQLException {
		var goods = getGoodsFromCart(userId);
		int sum = 0;
		for (var item : goods) {
			sum += item.getSum();
		}
		if (sum > 0 && clearCart(userId)) {
			var ps = ShopORM.getStatement("insert into orders(user_id,sum_order,status) values(?,?,?)");
			ps.setInt(1, userId);
			ps.setInt(2, sum);
			ps.setString(3, "Оформлен");
			if (ps.executeUpdate() > 0) {
				return true;
			}
		}
		return false;
	}
	private static boolean clearCart(int userId) throws XPathExpressionException, FileNotFoundException, ClassNotFoundException, SQLException {
		if (ShopORM.execute("delete from cart WHERE user_id="+ userId) > 0) {
			return true;
		}
		return false;
	}
	public static int findLastOrder(int userId) throws XPathExpressionException, FileNotFoundException, ClassNotFoundException, SQLException {
		int max = 0;
		var rs = ShopORM.select("SELECT MAX(order_id) as max FROM orders WHERE user_id="+userId);
		while(rs.next()) {
			max = rs.getInt("max");
		}
		return max;
	}
	public static List<AdminsInfo> getOrdersAdmin() throws XPathExpressionException, FileNotFoundException, ClassNotFoundException, SQLException{
		var sql = "select * from orders inner join users ON orders.user_id=users.user_id";
		var rs = ShopORM.select(sql);
		admins.clear();
		while (rs.next()) {
			var userId = rs.getInt("user_id");
			var orderId = rs.getInt("order_id");
			var fio = rs.getString("fio");
			var phone = rs.getString("phone");
			var dateOfOrder = rs.getTimestamp("date_of_order");
			var sumOrder = rs.getInt("sum_order");
			var status = rs.getString("status");
			admins.add(new AdminsInfo(userId, orderId, fio, phone, dateOfOrder, sumOrder, status));
		}
		return admins;
	}
	public static boolean confirmOrder(int orderId)
			throws ClassNotFoundException, SQLException, XPathExpressionException, FileNotFoundException {
		String str = "В работе";
		var ps = ShopORM.getStatement("update orders set status=? where order_id=?");
		ps.setString(1, str);
		ps.setInt(2, orderId);
		if (ps.executeUpdate() > 0) {
			return true;
		}
		return false;
	}
}
