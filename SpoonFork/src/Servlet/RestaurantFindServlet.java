package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.RestaurantDao;
import OnlineMealOrder.Restaurant;
import OnlineMealOrder.User;

/**
 * Servlet implementation class RestaurantFindServlet
 */
@WebServlet("/RestaurantFindServlet")
public class RestaurantFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestaurantFindServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		List<Restaurant> Restaurants = new ArrayList<Restaurant>();
		
		RestaurantDao rstDao = new RestaurantDao();
		User user = (User)session.getAttribute("User");
		Restaurants = rstDao.FindRestaurant(user);
		if(Restaurants.isEmpty())
		{
			response.sendRedirect("Home.jsp"); 
			return;
		}
		session.setAttribute("restaurantList",Restaurants);
		response.sendRedirect("RestaurantSelect.jsp"); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}