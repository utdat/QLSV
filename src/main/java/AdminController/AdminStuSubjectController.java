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
import dao.StuSubjectDao;
import dao.SubjectDao;
import dao.SubjectGroupDao;
import entity.Accounts;
import entity.StuSubject;
import entity.SubjectGroup;

/**
 * Servlet implementation class AdminStuSubjectController
 */
@WebServlet("/AdminStuSubject")
public class AdminStuSubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminStuSubjectController() {
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
		String sub_id = request.getParameter("sub_id");
		String stu_id = request.getParameter("stu_id");
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
				if (new StuSubjectDao().cancelSubRegis(Integer.parseInt(sub_id), stu_id) == 0) {
					message = "Failed to cancel student's subject registration!";
				} else {
					message = "Successfully canceled student's subject registration!";
				}
			}
			ArrayList<StuSubject> stusub_list = new StuSubjectDao().showAllList();
			HashMap<Integer, String> sub_map = new SubjectDao().getSubMap();
			HashMap<String, String> stu_map = new AccountDao().getAccountMap(1);

			session.setAttribute("sub_map", sub_map);
			session.setAttribute("stu_map", stu_map);
			session.setAttribute("stusub_list", stusub_list);
			request.setAttribute("mess", message);
			request.getRequestDispatcher("WEB-INF/view/admin/admin_stusubject.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String sub_id = request.getParameter("sub_id");
		String stu_id = request.getParameter("stu_id");

		String page = "WEB-INF/view/admin/admin_stusubject.jsp";
		String message = "";

		if (sub_id == null) {
			message = "Please select subject!";
		}else if(stu_id == null) {
			message = "Please select student!";
		}else {
			if(new StuSubjectDao().submitSubject(Integer.parseInt(sub_id), stu_id) == 0) {
				message = "Subject registration failed!";
			}else {
				message = "Subject registration succeed!";
				ArrayList<StuSubject> stusub_list = new StuSubjectDao().showAllList();
				session.setAttribute("stusub_list", stusub_list);
			}
		}
		request.setAttribute("mess", message);
		request.getRequestDispatcher(page).forward(request, response);
	}

}
