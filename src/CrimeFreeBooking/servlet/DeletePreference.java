package CrimeFreeBooking.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;
import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CrimeFreeBooking.model.Preferences;
import CrimeFreeBooking.model.Users;
import CrimeFreeBooking.dal.PreferencesDao;

/**
 * Servlet implementation class FindUser
 */
@WebServlet("/DeletePreference")
public class DeletePreference extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePreference() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		Preferences pref = null;
		
		out.println("<html>");
        out.println("<head>");
        out.println("<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<style> body { background-color: #ccebff; } <div class=\"jumbotron\"> </style>");

		
		try {
			pref = PreferencesDao.getInstance().getPreferenceById(Integer.parseInt(id));
			PreferencesDao.getInstance().delete(pref.getPreferenceId());
		} catch (SQLException e) {
			out.println("Failed to delete preference!");
		}

		out.println("Successfully deleted preference! Log out and login again to see update<br>");
		out.println("<a href=\"login.jsp\"><font size=\"3\" class=\"alert alert-danger\"><u>Log Out</u></font></a>");
		out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js\"></script> <!-- Include all compiled plugins (below), or include individual files as needed --> <script src=\"js/bootstrap.min.js\"></script>");
		out.println("</body>");
        out.println("</html>");
	}
}
