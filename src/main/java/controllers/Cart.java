package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.xpath.XPathExpressionException;

import models.BaseModel;
import models.GoodCart;

@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		try {
			List<GoodCart> goodsCart = BaseModel.getGoodsFromCart((int)request.getSession().getAttribute("userId"));

			request.setAttribute("goodsCart", goodsCart);
			request.getRequestDispatcher("WEB-INF/views/cart.jsp").forward(request, response);

		} catch (ClassNotFoundException | SQLException | XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		var goodId = request.getParameter("good_id");
		try {
			if(BaseModel.removeFromCart(Integer.parseInt(goodId))) {
				response.getWriter().print("Товар успешно удален!");
			} else {
				response.getWriter().print("Error");
			}
		} catch (NumberFormatException | ClassNotFoundException | XPathExpressionException | FileNotFoundException
				| SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
