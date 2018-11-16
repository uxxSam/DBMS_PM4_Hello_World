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

import CrimeFreeBooking.dal.ListingsDao;
import CrimeFreeBooking.model.Listings;

/**
 * Servlet implementation class FindListingsByBB
 */
@WebServlet("/FindListingsByBB")
public class FindListingsByBB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindListingsByBB() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String bathroom = request.getParameter("bathroom");
		String bedroom = request.getParameter("bedroom");
		String username = request.getParameter("username");
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		List<Listings> listingList = new ArrayList<>();
		List<String> messageList = new ArrayList<>();
		
		try {
			listingList = ListingsDao.getInstance().getListingsBasedOnBedsAndBaths(Integer.parseInt(bedroom), Integer.parseInt(bathroom));
		} catch (SQLException e) {
			messageList.add("No Listing found");
		} finally {
			if (listingList.size() != 0) {
				messageList.add("Success!");
			} else {
				messageList.add("No Listing found");
			}
			
			request.setAttribute("username", username);
			request.setAttribute("listingByBB", listingList);
			request.setAttribute("messagesPriceByBB", messageList);
			request.getRequestDispatcher("/loggedIn.jsp?username=" + username).forward(request, response);
		}
	}
}
