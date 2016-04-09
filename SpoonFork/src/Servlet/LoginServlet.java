package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.UserDao;
import OnlineMealOrder.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		 String username = request.getParameter("username");
		 String password = request.getParameter("password");
		   
	    UserDao userdao = new UserDao();
	 	if(username.isEmpty()||password.isEmpty())
	 	{
	 		session.setAttribute("LoginStatus", "fail"); 
	 		response.sendRedirect("Login.jsp"); 
	 		return;
	 	}
	 	User user = userdao.findUserbyPassword(username, password);
	 	if(user == null)
	 	{
	 		session.setAttribute("LoginStatus", "fail"); 
	 		response.sendRedirect("Login.jsp");
	 		return;
	 	}
	 	session.setAttribute("LoginStatus", "succeed"); 
	 	session.setAttribute("User",user); 
		response.sendRedirect("Home.jsp"); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
