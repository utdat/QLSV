package StudentController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StuSubjectDao;
import dao.SubjectDao;
import entity.Accounts;
import entity.StuSubject;
import entity.Subjects;

/**
 * Servlet implementation class StudentScoreController
 */
@WebServlet("/StudentScore")
public class StudentScoreController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentScoreController() {
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
		String sub_id = request.getParameter("sub_id");
		String message = "";

		if (acc_cur == null) {
			response.sendRedirect(this.getServletContext().getContextPath() + "/Home");
			return;
		} else if (acc_cur.getType() == 2) {
			response.sendRedirect(this.getServletContext().getContextPath() + "/TeacherProfile");
			return;
		} else if (acc_cur.getType() == 3) {
			response.sendRedirect(this.getServletContext().getContextPath() + "/AdminProfile");
			return;
		} else {
			if ("delete".equals(action)) {
				int kq = new StuSubjectDao().cancelSubRegis(Integer.parseInt(sub_id), acc_cur.getId());
				if (kq == 0) {
					message = "Failed to cancel subject registration!";
				} else {
					message = "Successfully canceled subject registration!";
				}
			}

			ArrayList<StuSubject> stu_point = new StuSubjectDao().showStudentSubject(acc_cur.getId());
			ArrayList<Subjects> stu_sub = new SubjectDao().showStudentSubject(acc_cur.getId());

			session.setAttribute("stu_point", stu_point);
			session.setAttribute("stu_sub", stu_sub);
			request.setAttribute("mess", message);
			request.getRequestDispatcher("WEB-INF/view/student/student_score.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
