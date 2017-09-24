package pigame;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		System.out.println("The SessionID is " + request.getSession().getId());
		PrintWriter pWriter = response.getWriter();
		Cookie[] cookies = request.getCookies();
		boolean Loginflag = false;
		String usr = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("remember") && cookie.getValue().equals("yes")) {
					Loginflag = true;
				}
				if (cookie.getName().equals("usr")) {
					usr = cookie.getValue();
				}
			}
		}
		if (Loginflag) {
			int id = -1;
			try {
				id = new userdb(new dbUtil().getConnection()).get_id(usr);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getSession().setAttribute("usr_id", id);
			request.getSession().setAttribute("logged", "yes");
			request.getSession().setAttribute("usr", usr);
			request.setAttribute("usr", usr);
		}
		response.setContentType("text/html; charset=UTF-8");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
