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
import CrimeFreeBooking.dal.UsersDao;

/**
 * Servlet implementation class FindUser
 */
@WebServlet("/FindUsers")
public class FindUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		List<Users> userList = new ArrayList<>();
		List<String> messageList = new ArrayList<>();
		Users requestedUser = null;
		
		try {
			requestedUser = UsersDao.getInstance().getUserByUserName(username);
		} catch (SQLException e) {
			messageList.add("User does not exist");
		} finally {
			if (requestedUser != null) {
				userList.add(requestedUser);
				messageList.add("Success!");
			} else {
				messageList.add("User does not exist");
			}
			
			request.setAttribute("user", userList);
			request.setAttribute("messages", messageList);
			request.getRequestDispatcher("/find-user").forward(request, response);
		}
	}
}
