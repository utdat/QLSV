package AdminController;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class AdminAccount
 */
@WebServlet("/AdminAccount")
public class AdminAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Accounts acc_cur = (Accounts) session.getAttribute("acc_cur");
		String action = request.getParameter("action");
		String acc_id = request.getParameter("acc_id");
		String message = "";

		if (acc_cur == null) {
			response.sendRedirect(this.getServletContext().getContextPath() + "/Home");
			return;
		} else if (acc_cur.getType() == 1) {
			response.sendRedirect(this.getServletContext().getContextPath() + "/StudentProfile");
			return;
		} else if (acc_cur.getType() == 2) {
			response.sendRedirect(this.getServletContext().getContextPath() + "/TeacherProfile");
			return;
		} else {
			if ("delete".equals(action)) {
				if (new AccountDao().DeleteAccount(acc_id) == 0) {
					message = "Failed to delete account!";
				} else {
					message = "Successfully deleted account!";
				}
			}

			ArrayList<Accounts> acc_list = new AccountDao().showAllUser();
			HashMap<Integer, String> dep_map = new DepartmentDao().getDepMap();

			session.setAttribute("dep_map", dep_map);
			session.setAttribute("acc_list", acc_list);
			request.setAttribute("mess", message);
			request.getRequestDispatcher("WEB-INF/view/admin/admin_account.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String depart = request.getParameter("depart");
		String type = request.getParameter("type");

		String page = "WEB-INF/view/admin/admin_account.jsp";
		String message = "";

		if (type == null) {
			message = "Please select your user type!";
		} else if (depart == null) {
			message = "Please select your department!";
		} else if (new AccountDao().FindByEmail(email) != null) {
			message = "Email already exists!";
		} else if (new AccountDao().FindByID(id) != null) {
			message = "ID already exists!";
		} else {
			int rs = new AccountDao().InsertAccount(id, firstname, lastname, Integer.parseInt(depart), email,
					Integer.parseInt(type), password);
			if (rs == 0) {
				message = "Account registration failed!";
			} else {
				message = "Account registration succeed!";
				ArrayList<Accounts> acc_list = new AccountDao().showAllUser();
				session.setAttribute("acc_list", acc_list);
			}
		}
		request.setAttribute("mess", message);
		request.getRequestDispatcher(page).forward(request, response);
	}

}
