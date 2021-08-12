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

import dao.DepartmentDao;
import dao.SchoolDao;
import dao.SubjectGroupDao;
import entity.Accounts;
import entity.Departments;
import entity.SubjectGroup;

/**
 * Servlet implementation class AdminSubGroupController
 */
@WebServlet("/AdminSubGroup")
public class AdminSubGroupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSubGroupController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Accounts acc_cur = (Accounts) session.getAttribute("acc_cur");
		String action = request.getParameter("action");
		String sg_id = request.getParameter("sg_id");
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
				if (new SubjectGroupDao().DeleteSubGroup(Integer.parseInt(sg_id)) == 0) {
					message = "Failed to delete subject group!";
				} else {
					message = "Successfully deleted subject group!";
				}
			}
			ArrayList<SubjectGroup> sg_list = new SubjectGroupDao().getSubGroupList();
			HashMap<Integer, String> dep_map = new DepartmentDao().getDepMap();

			session.setAttribute("dep_map", dep_map);
			session.setAttribute("sg_list", sg_list);
			request.setAttribute("mess", message);
			request.getRequestDispatcher("WEB-INF/view/admin/admin_subgroup.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String dep_id = request.getParameter("dep_id");

		String page = "WEB-INF/view/admin/admin_subgroup.jsp";
		String message = "";

		if (dep_id == null) {
			message = "Please select your department!";
		} else {
			if (id != null && !id.equals("")) {
				if (new SubjectGroupDao().UpdateSubGroup(Integer.parseInt(id), name, Integer.parseInt(dep_id)) == 0) {
					message = "Subject group update failed!";
				} else {
					message = "Subject group update succeed!";
					ArrayList<SubjectGroup> sg_list = new SubjectGroupDao().getSubGroupList();
					session.setAttribute("sg_list", sg_list);
				}
			} else {
				if (new SubjectGroupDao().InsertSubGroup(name, Integer.parseInt(dep_id)) == 0) {
					message = "Subject group registration failed!";
				} else {
					message = "Subject group registration succeed!";
					ArrayList<SubjectGroup> sg_list = new SubjectGroupDao().getSubGroupList();
					session.setAttribute("sg_list", sg_list);
				}
			}
		}
		request.setAttribute("mess", message);
		request.getRequestDispatcher(page).forward(request, response);
	}

}
