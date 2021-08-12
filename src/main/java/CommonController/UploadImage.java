package CommonController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.AccountDao;
import entity.Accounts;

/**
 * Servlet implementation class UploadImage
 */
@WebServlet("/UploadImage")
@MultipartConfig
public class UploadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = "D:\\JWAT\\Workspace\\QLSV\\src\\main\\webapp\\images\\";

	public UploadImage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Accounts acc_cur = (Accounts) session.getAttribute("acc_cur");

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
			request.getRequestDispatcher("WEB-INF/view/student/student_profile.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "";
		String page = "WEB-INF/view/";
		HttpSession session = request.getSession();
		Accounts acc_cur = (Accounts) session.getAttribute("acc_cur");

		if (acc_cur == null) {
			response.sendRedirect(this.getServletContext().getContextPath() + "/Home");
			return;
		}

		switch (acc_cur.getType()) {
		case 1:
			page += "student/student_profile.jsp";
			break;
		case 2:
			page += "teacher/teacher_profile.jsp";
			break;
		case 3:
			page += "admin/admin_profile.jsp";
			break;
		}

		Part filepart = request.getPart("avatar");
		if (filepart == null) {
			message = "No file choosen!";
		} else {
			String filename = filepart.getSubmittedFileName();
			for (Part part : request.getParts()) {
				part.write(UPLOAD_DIRECTORY + filename);
			}

			int kq = new AccountDao().UpdateAccount(filename, acc_cur.getId());

			if (kq == 0) {
				message = "Change avatar failed!";
			} else {
				message = "Change avatar succeed!";
				acc_cur.setAvatar(filename);
				session.setAttribute("acc_cur", acc_cur);
			}
		}

		request.setAttribute("mess", message);
		request.getRequestDispatcher(page).forward(request, response);

	}

}
