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

@WebServlet("/Auth")
public class Auth extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		var login = request.getParameter("login");
		var pass = request.getParameter("pass");

		if (login != null && pass != null) {
			try {
				if (BaseModel.getIdBySign(login, pass) > 0) {
					request.getSession().setAttribute("userId", BaseModel.getIdBySign(login, pass));
					response.sendRedirect("/lesson23_shop/Catalog");
				} else {
					request.getRequestDispatcher("WEB-INF/views/reg.html").forward(request, response);
				}
			} catch (ClassNotFoundException | XPathExpressionException | FileNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			request.getRequestDispatcher("WEB-INF/views/auth.html").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		doGet(request, response);
	}
}
