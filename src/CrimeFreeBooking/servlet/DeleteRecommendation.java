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

import CrimeFreeBooking.model.Recommendations;
import CrimeFreeBooking.model.Users;
import CrimeFreeBooking.dal.PreferencesDao;
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
		
		out.write("<html>");
        out.write("<head>");
        out.write("<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">");
        out.write("</head>");
        out.write("<body>");
        out.write("<style> body { background-color: #ccebff; } <div class=\"jumbotron\"> </style>");
        out.write("<br>");
		
		try {
			pref = RecommendationsDao.getInstance().getRecommendationById(Integer.parseInt(id));
			RecommendationsDao.getInstance().delete(pref);
			out.write("<font size=\"3\" class=\"alert alert-success\">Successfully deleted recommendation! Log out and login again to see update!<br></font><br><br>");
		} catch (SQLException e) {
			out.write("<font size=\"3\" class=\"alert alert-danger\">Failed to delete recommendation!<br></font><br><br>");
		}

		out.write("<a href=\"login.jsp\"><font size=\"3\" class=\"alert alert-danger\"><u>Log Out</u></font></a>");
		out.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js\"></script> <!-- Include all compiled plugins (below), or include individual files as needed --> <script src=\"js/bootstrap.min.js\"></script>");
		out.write("</body>");
        out.write("</html>");
	}
}
