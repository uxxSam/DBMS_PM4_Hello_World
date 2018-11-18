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

import CrimeFreeBooking.model.Users;
import CrimeFreeBooking.dal.ListingsDao;
import CrimeFreeBooking.dal.UsersDao;

/**
 * Servlet implementation class FindUser
 */
@WebServlet("/CreateAnalysis")
public class CreateAnalysis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAnalysis() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String listingid = request.getParameter("listingid");
		String radius = request.getParameter("radius");
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		List<String> resList = new ArrayList<>();
		List<String> messageList = new ArrayList<>();
		List<List<String>> ret = new ArrayList<>();
		
		try {
			int crimeCount = ListingsDao.getInstance().getCrimeAroundListing(Integer.parseInt(listingid), Integer.parseInt(radius));
			List<List<String>> top3 = ListingsDao.getInstance().getMostFrequentCrimeAroundListing(Integer.parseInt(listingid), Integer.parseInt(radius));
			resList.add(String.valueOf(crimeCount));
			
			for (int i = 0; i < 3; i++) {
				resList.add(top3.get(i).get(0));
				resList.add(top3.get(i).get(1));
			}
			ret.add(resList);
		} catch (SQLException e) {
			messageList.add("Crime does not exist");
		} finally {
			if (!resList.isEmpty()) {
				messageList.add("Success!");
			} else {
				messageList.add("Crime does not exist");
			}
			
			request.setAttribute("crime", ret);
			request.setAttribute("messages", messageList);
			request.getRequestDispatcher("/create-analysis").forward(request, response);
		}
	}
}
