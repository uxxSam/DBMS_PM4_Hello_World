package CrimeFreeBooking.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

import CrimeFreeBooking.dal.RecommendationsDao;
import CrimeFreeBooking.model.Recommendations;

/**
 * Servlet implementation class CreateWishList
 */
@WebServlet("/CreateRecommendation")
public class CreateRecommendation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRecommendation() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Writer out=response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String listingId = request.getParameter("listingId");
		Recommendations newWish = new Recommendations(new Random().nextInt(Integer.MAX_VALUE), username, Integer.parseInt(listingId));
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		List<String> messageList = new ArrayList<>();
		
		try {
			RecommendationsDao.getInstance().create(newWish);
		} catch (SQLException e) {
			out.write("Failed to recommend this listing");
		}
		out.write("Successfully recommended this listing! Log out and log in to see the update");
	}

}
