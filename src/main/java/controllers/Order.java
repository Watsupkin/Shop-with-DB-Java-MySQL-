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

@WebServlet("/Order")
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		var userId = (int)request.getSession().getAttribute("userId");
		try {
			if(BaseModel.doOrder(userId)) {
				request.setAttribute("last", BaseModel.findLastOrder(userId));
				request.setAttribute("orders", BaseModel.getOrdersById(userId));
				request.getRequestDispatcher("WEB-INF/views/order_page.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException | XPathExpressionException | FileNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
