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
import javax.servlet.http.HttpSession;
import javax.xml.xpath.XPathExpressionException;

import models.BaseModel;
import models.Good;
import models.ShopORM;

@WebServlet("/Catalog")
public class Catalog extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		try {
			ShopORM.getConnection();
		} catch (XPathExpressionException | FileNotFoundException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		var userId = session.getAttribute("userId");
		var goodId = request.getParameter("goodId");
		if(userId != null) {
			try {
				String login = BaseModel.getLoginById((int)userId);
				request.setAttribute("login", login);
			} catch (XPathExpressionException | FileNotFoundException | ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		var drop = request.getParameter("drop");
		if (drop != null) {
			request.getSession().invalidate();
			drop = null;
			try {
				getCatalogPage(goodId, userId + "", request, response);
			} catch (ClassNotFoundException | XPathExpressionException | SQLException | ServletException
					| IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			getCatalogPage(goodId, userId + "", request, response);
		} catch (ClassNotFoundException | SQLException | XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getCatalogPage(String goodId, String userId, HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, XPathExpressionException, SQLException, ServletException, IOException {
		List<Good> goods = BaseModel.getGoods();
		if (goodId == null) {
			request.setAttribute("goods", goods);
			request.getRequestDispatcher("WEB-INF/views/catalog.jsp").forward(request, response);
		} else {
			request.setAttribute("good", goods.get(Integer.parseInt(goodId) - 1));
			request.getRequestDispatcher("WEB-INF/views/card_of_good.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		var id = request.getParameter("id");
		var userId = request.getSession().getAttribute("userId");
		if (id != null) {
			var writer = response.getWriter();
			try {
				if (BaseModel.addToCart(Integer.parseInt(id), (int)userId)) {
					writer.print("Товар успешно добавлен!");
				} else {
					writer.print("Error!");
				}
			} catch (NumberFormatException | ClassNotFoundException | SQLException | XPathExpressionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
