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
import entity.Accounts;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/Home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HomeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/view/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String page = "WEB-INF/view/";
		String message = "";
		
		HttpSession session = request.getSession();
		Accounts acc = new AccountDao().FindByEmail(username);

		if (acc == null || !acc.getPassword().equals(password)) {
			message = "Login failed!";
			page += "login.jsp";
		} else {
			message = "Login successfully!";
			
			HashMap<Integer,String> dep_map = new DepartmentDao().getDepMap();
			
			session.setAttribute("dep_map", dep_map);
			session.setAttribute("acc_cur", acc);
			switch(acc.getType()) {
			case 1:
				page += "student/student_profile.jsp";
				break;
			case 2:
				page += "teacher/teacher_profile.jsp";
				break;
			case 3:
				page += "admin/admin_profile.jsp";
				break;
			default:
				page += "login.jsp";
				break;
			}
		}
		
		request.setAttribute("mess", message);
		request.getRequestDispatcher(page).forward(request, response);
	}

}
