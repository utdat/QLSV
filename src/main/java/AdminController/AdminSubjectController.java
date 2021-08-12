package AdminController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDao;
import dao.DepartmentDao;
import dao.SubjectDao;
import dao.SubjectGroupDao;
import entity.Accounts;
import entity.SubjectGroup;
import entity.Subjects;

/**
 * Servlet implementation class AdminSubjectController
 */
@WebServlet("/AdminSubject")
public class AdminSubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSubjectController() {
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
		String page = "WEB-INF/view/admin/admin_listsub.jsp";
		Subjects sub_cur = null;

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
				if(new SubjectDao().DeleteSubject(Integer.parseInt(sub_id)) == 0) {
					message = "Failed to delete subject!";
				}else {
					message = "Successfully to delete subject!";
				}
			}else if("detail".equals(action)) {
				if(sub_id != null) {
					sub_cur = new SubjectDao().FindBySubID(Integer.parseInt(sub_id));
				}
				
				page = "WEB-INF/view/admin/admin_detailsub.jsp";
			}
			
			
			ArrayList<Subjects> sub_list = new SubjectDao().getSubList();
			HashMap<Integer, String> sg_map = new SubjectGroupDao().getSubGroupMap();
			HashMap<String, String> acc_map = new AccountDao().getAccountMap(2);

			session.setAttribute("sub_cur", sub_cur);
			session.setAttribute("acc_map", acc_map);
			session.setAttribute("sg_map", sg_map);
			session.setAttribute("sub_list", sub_list);
			
			request.setAttribute("mess", message);
			request.getRequestDispatcher(page).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Subjects sub = (Subjects) session.getAttribute("sub_cur");

		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		String room = request.getParameter("room");
		String tea_id = request.getParameter("tea_id");
		String schedule = request.getParameter("schedule");
		String sg_id = request.getParameter("sg_id");

		String page = "WEB-INF/view/admin/admin_detailsub.jsp";
		String message = "";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			Date start = sdf.parse(startdate);
			Date end = sdf.parse(enddate);
			
			if(tea_id == null) {
				message = "Please select teacher!";
			}else if(sg_id == null) {
				message = "Please select subject group!";
			}else if(end.before(start)) {
				message = "Invalid enddate!";
			}else {
				if(id != null && !id.equals("")) {
					if(new SubjectDao().UpdateSubject(Integer.parseInt(id), name, schedule, room, startdate, enddate, tea_id, Integer.parseInt(sg_id)) == 0) {
						message = "Subject update failed!";
					}else {
						message = "Subject update succeed!";
						sub = new SubjectDao().FindBySubID(Integer.parseInt(id));
						session.setAttribute("sub_cur", sub);
					}
				}else {
					if(new SubjectDao().InsertSubject(name, schedule, room, startdate, enddate, tea_id, Integer.parseInt(sg_id)) == 0) {
						message = "Subject registration failed!";
					}else {
						message = "Subject registration succeed!";
					}
				}
			}
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
	
		request.setAttribute("mess", message);
		request.getRequestDispatcher(page).forward(request, response);
	}

}
