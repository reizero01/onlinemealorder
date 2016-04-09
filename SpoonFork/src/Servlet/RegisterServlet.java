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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		 String email = request.getParameter("email");
		   
	    UserDao userdao = new UserDao();
	 	if(username.isEmpty()||password.isEmpty()||email.isEmpty())
	 	{
	 		session.setAttribute("RegisterStatus", "fail"); 
	 		response.sendRedirect("Login.jsp");
	 		return;
	 	}
	 	User user = userdao.findUserbyUsername(username);
	 	if( user != null )
	 	{
	 		session.setAttribute("RegisterStatus", "fail"); 
	 		response.sendRedirect("Login.jsp");
	 		return;
	 	}
	 	user = new User(username, password, email);
	 	userdao.createUser(user);
	 	session.setAttribute("RegisterStatus", "success"); 
	 	response.sendRedirect("Login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
