package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.xpath.XPathExpressionException;

import models.BaseModel;

@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		try {
				request.setAttribute("orders", BaseModel.getOrdersAdmin());
				request.getRequestDispatcher("WEB-INF/views/admin.jsp").forward(request, response);
		} catch (ClassNotFoundException | XPathExpressionException | FileNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		var orderId = request.getParameter("orderId");
		try {
			if(BaseModel.confirmOrder(Integer.parseInt(orderId))) {
				response.getWriter().print("Заказ принят в работу!");
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
