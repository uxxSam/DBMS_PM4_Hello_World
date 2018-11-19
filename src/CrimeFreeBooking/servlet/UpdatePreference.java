package CrimeFreeBooking.servlet;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CrimeFreeBooking.model.Preferences;
import CrimeFreeBooking.model.Users;
import CrimeFreeBooking.dal.PreferencesDao;
import CrimeFreeBooking.dal.WishlistsDao;

/**
 * Servlet implementation class CreateUser
 */
@WebServlet("/UpdatePreference")
public class UpdatePreference extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePreference() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String bathroom = request.getParameter("bathroom");
		String bedroom = request.getParameter("bedroom");
		String user = request.getParameter("username");
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		List<String> messageList = new ArrayList<>();
		Preferences newPreference = new Preferences(new Random().nextInt(Integer.MAX_VALUE), user, Integer.parseInt(bathroom), Integer.parseInt(bedroom));
		Writer out=response.getWriter();
		
		out.write("<html>");
        out.write("<head>");
        out.write("<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">");
        out.write("</head>");
        out.write("<body>");
        out.write("<style> body { background-color: #ccebff; } <div class=\"jumbotron\"> </style>");
        out.write("<br>");
        
		try {
			PreferencesDao.getInstance().create(newPreference);
			out.write("<font size=\"3\" class=\"alert alert-success\">Successfully updated preference! Log out and login again to see update!<br></font><br><br>");

		} catch (SQLException e) {
			out.write("<font size=\"3\" class=\"alert alert-danger\">Failed to update preference!<br></font><br><br>");

		}
		
		out.write("<a href=\"login.jsp\"><font size=\"3\" class=\"alert alert-danger\"><u>Log Out</u></font></a>");
		out.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js\"></script> <!-- Include all compiled plugins (below), or include individual files as needed --> <script src=\"js/bootstrap.min.js\"></script>");
		out.write("</body>");
        out.write("</html>");
	}
}
