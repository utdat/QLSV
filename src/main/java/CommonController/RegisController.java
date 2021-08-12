package CommonController;

import java.io.IOException;

import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDao;
import dao.DepartmentDao;

/**
 * Servlet implementation class RegisController
 */
@WebServlet("/Register")
public class RegisController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		HashMap<Integer,String> dep_map = new DepartmentDao().getDepMap();
		
		session.setAttribute("dep_map", dep_map);
		request.getRequestDispatcher("WEB-INF/view/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String gender = request.getParameter("gender");
		String depart = request.getParameter("depart");
		String type = request.getParameter("type");

		String page = "WEB-INF/view/";
		String message = "";

		if (!repassword.equalsIgnoreCase(password)) {
			message = "Password does not match!";
			page += "register.jsp";
		} else if (type == null) {
			message = "Please select your user type!";
			page += "register.jsp";
		}else if (depart == null) {
			message = "Please select your department!";
			page += "register.jsp";
		} else if (new AccountDao().FindByEmail(email) != null) {
			message = "Email already exists!";
			page += "register.jsp";
		} else if (new AccountDao().FindByID(id) != null) {
			message = "ID already exists!";
			page += "register.jsp";
		}
		else {
			int rs = new AccountDao().InsertAccount(id, firstname, lastname, Integer.parseInt(depart), email, gender,
					Integer.parseInt(type), password);
			if (rs == 0) {
				message = "Account registration failed!";
				page += "register.jsp";
			} else {
				message = "Account registration succeed!";
				page += "login.jsp";
			}
		}
		request.setAttribute("mess", message);
		request.getRequestDispatcher(page).forward(request, response);
	}

}
