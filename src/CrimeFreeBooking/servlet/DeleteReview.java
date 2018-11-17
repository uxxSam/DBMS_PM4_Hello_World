package CrimeFreeBooking.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CrimeFreeBooking.model.Reviews;
import CrimeFreeBooking.dal.ReviewsDao;

/**
 * Servlet implementation class FindUser
 */
@WebServlet("/DeleteReview")
public class DeleteReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteReview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Writer out=response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		Reviews review = null;
		
		try {
			review = ReviewsDao.getInstance().getReviewById(Integer.parseInt(id));
			ReviewsDao.getInstance().delete(review);
		} catch (SQLException e) {
			out.write("Failed to delete review!");
		}

		out.write("Successfully deleted review! Log out and login again to see update");
	}
}
