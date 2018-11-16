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

import CrimeFreeBooking.model.Preferences;
import CrimeFreeBooking.model.Users;
import CrimeFreeBooking.dal.PreferencesDao;

/**
 * Servlet implementation class CreateUser
 */
@WebServlet("/CreatePreference")
public class CreatePreference extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePreference() {
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
		
		try {
			PreferencesDao.getInstance().create(newPreference);
			messageList.add("Successfully created preference! Log out and login to see the update");
		} catch (SQLException e) {
			messageList.add("Failed to create preference");
		} finally {
			request.setAttribute("messages", messageList);
			request.getRequestDispatcher("/create-preference").forward(request, response);
		}			
	}
}
