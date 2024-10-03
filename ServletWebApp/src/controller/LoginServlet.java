package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CookiesService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/login.html").forward(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		if(req.getCookies() == null) {
			System.out.println("error");
			res.sendRedirect("register?message=NoAccount");
			return;
		}

		Cookie username = CookiesService.getCookie(req, "username");
		Cookie password = CookiesService.getCookie(req, "password");
		
		String reqUsername = req.getParameter("username");
		String reqPassword = req.getParameter("password");
		
		if(
			reqUsername.equalsIgnoreCase(username.getValue()) &&
			reqPassword.equalsIgnoreCase(password.getValue())
		) {
			res.sendRedirect("home?message=Connected");
			return;
		}
		
		doGet(req, res);
	}

}
