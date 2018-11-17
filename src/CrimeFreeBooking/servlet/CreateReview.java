package CrimeFreeBooking.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CrimeFreeBooking.model.Reviews;
import CrimeFreeBooking.dal.ReviewsDao;

/**
 * Servlet implementation class CreateUser
 */
@WebServlet("/CreateReview")
public class CreateReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateReview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String content = request.getParameter("content");
		String listingid = request.getParameter("listingid");
		String user = request.getParameter("username");
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		List<String> messageList = new ArrayList<>();
		Reviews newReview = new Reviews(new Random().nextInt(Integer.MAX_VALUE), Integer.parseInt(listingid), new Date(System.currentTimeMillis()), user, content);
		
		try {
			ReviewsDao.getInstance().create(newReview);
			messageList.add("Successfully created review! Log out and login to see the update");
		} catch (SQLException e) {
			messageList.add("Failed to create review");
		} finally {
			request.setAttribute("messages", messageList);
			request.getRequestDispatcher("/create-review").forward(request, response);
		}			
	}
}
