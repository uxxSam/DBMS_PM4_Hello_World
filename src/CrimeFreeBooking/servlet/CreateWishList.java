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

import CrimeFreeBooking.dal.WishlistsDao;
import CrimeFreeBooking.model.Wishlists;

/**
 * Servlet implementation class CreateWishList
 */
@WebServlet("/CreateWishList")
public class CreateWishList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateWishList() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Writer out=response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String listingId = request.getParameter("listingId");
		Wishlists newWish = new Wishlists(new Random().nextInt(Integer.MAX_VALUE), username, Integer.parseInt(listingId));
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		List<String> messageList = new ArrayList<>();
		
		try {
			WishlistsDao.getInstance().create(newWish);
		} catch (SQLException e) {
			out.write("Failed to add to your wishlist");
		}
		out.write("Successfully added to your wishlist! Log out and log in to see the update");
	}

}
