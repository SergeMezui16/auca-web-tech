package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CookiesService;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/register.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String confirm = req.getParameter("confirmPassword");
		String role = req.getParameter("role");
		boolean persistent = req.getParameter("persistent") != null ? true : false;
		
		if(!password.equalsIgnoreCase(confirm)) {
			res.sendRedirect("register?error=teyeme");
			return;
		}
		
		res.addCookie(CookiesService.createCookie("username", username, persistent));
		res.addCookie(CookiesService.createCookie("password", password, persistent));
		res.addCookie(CookiesService.createCookie("role", role, persistent));
		
		res.sendRedirect("login?message=AccountCreated");
	}

}
