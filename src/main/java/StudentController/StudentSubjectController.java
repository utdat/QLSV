package StudentController;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StuSubjectDao;
import dao.SubjectDao;
import entity.Accounts;
import entity.Subjects;

/**
 * Servlet implementation class StudentSubjectController
 */
@WebServlet("/StudentSubject")
public class StudentSubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentSubjectController() {
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
			if ("submit".equals(action)) {
				int kq = new StuSubjectDao().submitSubject(Integer.parseInt(sub_id), acc_cur.getId());
				if (kq == 0) {
					message = "Subject registration failed!";
				} else {
					message = "Subject registration succeed!";
				}
			}
			
			HashMap<Subjects, String> sub_regis = new SubjectDao().showRegisSubject(acc_cur.getId(), acc_cur.getDep_id());
			
			session.setAttribute("sub_regis", sub_regis);
			request.setAttribute("mess", message);
			request.getRequestDispatcher("WEB-INF/view/student/student_subject.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
