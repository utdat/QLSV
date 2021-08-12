package TeacherController;

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
import dao.StuSubjectDao;
import dao.SubjectDao;
import entity.Accounts;
import entity.StuSubject;
import entity.Subjects;

/**
 * Servlet implementation class TeacherMarkController
 */
@WebServlet("/TeacherMark")
public class TeacherMarkController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TeacherMarkController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Accounts acc_cur = (Accounts) session.getAttribute("acc_cur");
		String sub_id = request.getParameter("sub_id");
		String stu_id = request.getParameter("stu_id");
		String midterm = request.getParameter("midterm");
		String endterm = request.getParameter("endterm");
		String action = request.getParameter("action");
		String message = "";

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
			if("update".equals(action)) {
				if(midterm != null) {
					if(Float.parseFloat(midterm) < 0 || Float.parseFloat(midterm) > 10) {
						message = "Invalid Midterm Score!";
						request.setAttribute("mess", message);
						request.getRequestDispatcher("WEB-INF/view/teacher/teacher_mark.jsp").forward(request, response);
						return;
					}else {
						if(new StuSubjectDao().updateMidScore(Integer.parseInt(sub_id), stu_id, Float.parseFloat(midterm)) == 0) {
							message = "Failed to update student score!";
							request.setAttribute("mess", message);
							request.getRequestDispatcher("WEB-INF/view/teacher/teacher_mark.jsp").forward(request, response);
							return;
						}else {
							message = "Successfully updated student score!";
						}
					}		
				}
				if(endterm != null) {
					if(Float.parseFloat(endterm) < 0 || Float.parseFloat(endterm) > 10) {
						message = "Invalid Endterm Score!";
						request.setAttribute("mess", message);
						request.getRequestDispatcher("WEB-INF/view/teacher/teacher_mark.jsp").forward(request, response);
						return;
					}else {
						if(new StuSubjectDao().updateEndScore(Integer.parseInt(sub_id), stu_id, Float.parseFloat(endterm)) == 0) {
							message = "Failed to update student score!";
							request.setAttribute("mess", message);
							request.getRequestDispatcher("WEB-INF/view/teacher/teacher_mark.jsp").forward(request, response);
							return;
						}else {
							message = "Successfully updated student score!";
						}
					}
				}
			}
			
			ArrayList<StuSubject> mark_list = new StuSubjectDao().showMarkSubject(acc_cur.getId(), sub_id);
			HashMap<String, String> stu_map = new AccountDao().showMarkStudent(acc_cur.getId());
			HashMap<Integer, Subjects> sub_map = new SubjectDao().showMarkSubject(acc_cur.getId());

			session.setAttribute("mark_list", mark_list);
			session.setAttribute("stu_map", stu_map);
			session.setAttribute("sub_map", sub_map);
			request.setAttribute("mess", message);
			request.getRequestDispatcher("WEB-INF/view/teacher/teacher_mark.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
