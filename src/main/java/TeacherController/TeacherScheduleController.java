package TeacherController;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SubjectDao;
import entity.Accounts;
import entity.Subjects;

/**
 * Servlet implementation class TeacherScheduleController
 */
@WebServlet("/TeacherSchedule")
public class TeacherScheduleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TeacherScheduleController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Accounts acc_cur = (Accounts) session.getAttribute("acc_cur");

		if (acc_cur == null) {
			response.sendRedirect(this.getServletContext().getContextPath() + "/Home");
			return;
		} else if (acc_cur.getType() == 1) {
			response.sendRedirect(this.getServletContext().getContextPath() + "/StudentProfile");
			return;
		} else if (acc_cur.getType() == 3) {
			response.sendRedirect(this.getServletContext().getContextPath() + "/AdminProfile");
			return;
		} else {
			HashMap<Subjects, String> sche_map = new SubjectDao().showTeaSchedule(acc_cur.getId(), acc_cur.getDep_id());
			
			session.setAttribute("sche_map", sche_map);
			request.getRequestDispatcher("WEB-INF/view/teacher/teacher_schedule.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
