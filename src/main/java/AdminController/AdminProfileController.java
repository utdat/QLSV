package AdminController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDao;
import entity.Accounts;

/**
 * Servlet implementation class AdminProfileController
 */
@WebServlet("/AdminProfile")
public class AdminProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProfileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Accounts acc_cur = (Accounts) session.getAttribute("acc_cur");
		Accounts acc_new = null;
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
			if("detail".equals(action)) {
				acc_new = new AccountDao().FindByID(acc_id);
			}
			
			session.setAttribute("acc_new", acc_new);
			request.setAttribute("mess", message);
			request.getRequestDispatcher("WEB-INF/view/admin/admin_profile.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Accounts acc_cur = (Accounts) session.getAttribute("acc_cur");
		Accounts acc_new = (Accounts) session.getAttribute("acc_new");

		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String birthdate = request.getParameter("birthdate");
		String dep_id = request.getParameter("depart");

		String page = "WEB-INF/view/admin/admin_profile.jsp";
		String message = "";
		int rs = 0;
		
		if(acc_new != null) {
			acc_cur = acc_new;
		}

		if (!password.equalsIgnoreCase(acc_cur.getPassword())) {
			if (!password.equalsIgnoreCase(repassword)) {
				message = "Password does not match!";
				request.setAttribute("mess", message);
				request.getRequestDispatcher(page).forward(request, response);
				return;
			}
		}

		if (!id.equalsIgnoreCase(acc_cur.getId())) {
			if (new AccountDao().FindByID(id) != null) {
				message = "ID already exists!";
				request.setAttribute("mess", message);
				request.getRequestDispatcher(page).forward(request, response);
				return;
			}
		}

		if (!email.equalsIgnoreCase(acc_cur.getEmail())) {
			if (new AccountDao().FindByEmail(email) != null) {
				message = "Email already exists!";
				request.setAttribute("mess", message);
				request.getRequestDispatcher(page).forward(request, response);
			}
			return;
		}
		
		if(acc_new != null) {
			rs = new AccountDao().UpdateAccount(firstname, lastname, id, email, password, gender, phone, address,
					birthdate, acc_cur.getId(), Integer.parseInt(dep_id));
		}else {
			rs = new AccountDao().UpdateAccount(firstname, lastname, id, email, password, gender, phone, address,
					birthdate, acc_cur.getId());
			session.setAttribute("acc_cur", new AccountDao().FindByID(id));
		}
		
		if (rs == 0) {
			message = "Account update failed!";
		} else {
			message = "Account update succeed!";
			session.removeAttribute("acc_new");
		}
		
		request.setAttribute("mess", message);
		request.getRequestDispatcher(page).forward(request, response);
	}

}
