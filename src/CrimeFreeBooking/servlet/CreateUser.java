package CrimeFreeBooking.servlet;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CrimeFreeBooking.model.Users;
import CrimeFreeBooking.dal.UsersDao;

/**
 * Servlet implementation class CreateUser
 */
@WebServlet("/CreateUser")
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String street1 = request.getParameter("street1");
		String street2 = request.getParameter("street2");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipcode = request.getParameter("zipcode");
		String country = request.getParameter("country");
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		Writer out=response.getWriter();
		
		Users newUser = new Users(username, password, firstname, lastname, street1, street2,
				city, state, Integer.parseInt(zipcode), country);
		
		try {
			UsersDao.getInstance().create(newUser);
			out.write("Successfully created user!");
		} catch (SQLException e) {
			out.write("Failed to create user");
		} finally {
			out.flush();
			out.close();
		}			
	}

}
