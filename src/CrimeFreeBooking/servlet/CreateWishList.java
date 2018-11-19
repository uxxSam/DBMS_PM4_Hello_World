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

import CrimeFreeBooking.dal.PreferencesDao;
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
		
		out.write("<html>");
        out.write("<head>");
        out.write("<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">");
        out.write("</head>");
        out.write("<body>");
        out.write("<style> body { background-color: #ccebff; } <div class=\"jumbotron\"> </style>");
        out.write("<br>");
		
		try {
			WishlistsDao.getInstance().create(newWish);
			out.write("<font size=\"3\" class=\"alert alert-success\">Successfully added to your wishlist! Log out and log in to see the update!<br></font><br><br>");

		} catch (SQLException e) {
			out.write("<font size=\"3\" class=\"alert alert-danger\">Failed to add to your wishlist!<br></font><br><br>");

		}
		
		out.write("<a href=\"login.jsp\"><font size=\"3\" class=\"alert alert-danger\"><u>Log Out</u></font></a>");
		out.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js\"></script> <!-- Include all compiled plugins (below), or include individual files as needed --> <script src=\"js/bootstrap.min.js\"></script>");
		out.write("</body>");
        out.write("</html>");
	}

}
