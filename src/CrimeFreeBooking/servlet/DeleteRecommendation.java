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

import CrimeFreeBooking.model.Recommendations;
import CrimeFreeBooking.model.Users;
import CrimeFreeBooking.dal.RecommendationsDao;

/**
 * Servlet implementation class FindUser
 */
@WebServlet("/DeleteRecommendation")
public class DeleteRecommendation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteRecommendation() {
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
		Recommendations pref = null;
		
		try {
			pref = RecommendationsDao.getInstance().getRecommendationById(Integer.parseInt(id));
			RecommendationsDao.getInstance().delete(pref);
		} catch (SQLException e) {
			out.write("Failed to delete recommendation!");
		}

		out.write("Successfully deleted recommendation! Log out and login again to see update");
	}
}
