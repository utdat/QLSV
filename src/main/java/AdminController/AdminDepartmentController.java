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
import dao.SchoolDao;
import entity.Accounts;
import entity.Departments;

/**
 * Servlet implementation class AdminDepartmentController
 */
@WebServlet("/AdminDepartment")
public class AdminDepartmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminDepartmentController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Accounts acc_cur = (Accounts) session.getAttribute("acc_cur");
		String action = request.getParameter("action");
		String dep_id = request.getParameter("dep_id");
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
				if (new DepartmentDao().DeleteDepartment(Integer.parseInt(dep_id)) == 0) {
					message = "Failed to delete department!";
				} else {
					message = "Successfully deleted department!";
				}
			}
			ArrayList<Departments> dep_list = new DepartmentDao().getDepList();
			HashMap<Integer, String> sch_map = new SchoolDao().getSchoolMap();

			session.setAttribute("sch_map", sch_map);
			session.setAttribute("dep_list", dep_list);
			request.setAttribute("mess", message);
			request.getRequestDispatcher("WEB-INF/view/admin/admin_department.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String sch_id = request.getParameter("sch_id");

		String page = "WEB-INF/view/admin/admin_department.jsp";
		String message = "";

		if (sch_id == null) {
			message = "Please select your school!";
		} else {
			if (id != null && !id.equals("")) {
				if (new DepartmentDao().UpdateDepartment(Integer.parseInt(id), name, Integer.parseInt(sch_id)) == 0) {
					message = "Department update failed!";
				} else {
					message = "Department update succeed!";
					ArrayList<Departments> dep_list = new DepartmentDao().getDepList();
					session.setAttribute("dep_list", dep_list);
				}
			} else {
				if (new DepartmentDao().InsertDepartment(name, Integer.parseInt(sch_id)) == 0) {
					message = "Department registration failed!";
				} else {
					message = "Department registration succeed!";
					ArrayList<Departments> dep_list = new DepartmentDao().getDepList();
					session.setAttribute("dep_list", dep_list);
				}
			}
		}
		request.setAttribute("mess", message);
		request.getRequestDispatcher(page).forward(request, response);
	}

}
