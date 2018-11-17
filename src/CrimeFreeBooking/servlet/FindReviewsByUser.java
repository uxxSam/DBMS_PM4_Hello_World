package CrimeFreeBooking.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CrimeFreeBooking.dal.ReviewsDao;
import CrimeFreeBooking.model.Reviews;

/**
 * Servlet implementation class FindListingsByBB
 */
@WebServlet("/FindReviewsByUser")
public class FindReviewsByUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindReviewsByUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		List<Reviews> reviewList = new ArrayList<>();
		List<String> messageList = new ArrayList<>();
		
		try {
			reviewList = ReviewsDao.getInstance().getReviewByUserName(username);
		} catch (SQLException e) {
			messageList.add("No Reviews found");
		} finally {
			if (reviewList.size() != 0) {
				messageList.add("Success!");
			} else {
				messageList.add("No Reviews found");
			}
			
			request.setAttribute("reviewList", reviewList);
			request.setAttribute("messagesReviews", messageList);
			request.getRequestDispatcher("/loggedIn.jsp?username=" + username).forward(request, response);
		}
	}
}
