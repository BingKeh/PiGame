package pigame;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.util.StringManager;

import com.google.gson.Gson;

/**
 * Servlet implementation class do_login
 */
@WebServlet("/do_login")
public class do_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private userdb db;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public do_login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		PrintWriter pWriter = response.getWriter();
		String action = request.getParameter("action");
		if (action == null) {
			action = "do_login";
		}
		if (this.db == null) {
			this.db = new userdb(new dbUtil().getConnection());
		}
		switch (action) {
		case "do_login": {
			String result = "FAILED";
			boolean bRs = false;
			String user_name = request.getParameter("user_name");
			String user_pwd = request.getParameter("user_pwd");
			String check = request.getParameter("rem_check");
			if (check == null) {
				check = "no";
			}
			String value = check.equals("on") ? "yes" : "no";
			try {
				bRs= db.do_login(user_name, user_pwd);
			} catch (NoSuchAlgorithmException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (bRs) {
				Cookie cookie = new Cookie("remember", value);
				cookie.setHttpOnly(true);
				cookie.setPath("/PiGame");
				cookie.setMaxAge(2592000);
				response.addCookie(cookie);
				boolean flag = false;
				if (value.equals("yes")) {
					String token = "usr:" + user_name + "pwd:" + "user_pwd" + "ip:" + request.getRemoteHost();
					try {
						token = MyUtil.getMD5(token);
						flag = db.put_token(token, user_name);
					} catch (NoSuchAlgorithmException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (flag) {
						cookie = new Cookie("token", token);
						cookie.setHttpOnly(true);
						cookie.setPath("/PiGame");
						cookie.setMaxAge(2592000);
						response.addCookie(cookie);
					}	
				}	
				cookie = new Cookie("usr", user_name);
				cookie.setHttpOnly(true);
				cookie.setPath("/PiGame");
				cookie.setMaxAge(2592000);
				response.addCookie(cookie);
				request.getSession().setAttribute("logged", "yes");
				request.getSession().setAttribute("usr", user_name);
				int id = -1;
				try {
					id = new userdb(new dbUtil().getConnection()).get_id(user_name);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getSession().setAttribute("usr_id", id);
				System.out.println("Login Status: " + bRs);
				response.sendRedirect("index");
			}
			break;
		}
		
		case "do_register": {
			String usr = request.getParameter("user_name");
			String pwd = request.getParameter("user_psd");
			String email = request.getParameter("user_email");
			try {
				this.db.do_register(usr, pwd, email);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("The user is " + usr + " psd is " + pwd + " email is " + email);
			pWriter.append("Action: do_register!");
			response.sendRedirect("login.html");
			break;
		}
		
		case "logout": {
			String usr = request.getSession().getAttribute("usr").toString();
			request.getSession().setAttribute("usr", null);
			request.getSession().setAttribute("usr_id", null);
			request.getSession().setAttribute("logged", "no");
			try {
				this.db.do_logout(usr);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Cookie cookie = new Cookie("remember", "no");
			cookie.setHttpOnly(true);
			cookie.setPath("/PiGame");
			cookie.setMaxAge(2592000);
			response.addCookie(cookie);
			response.sendRedirect("index");
			break;
		}
		case "buy": {
			int usr_id = (int) request.getSession().getAttribute("usr_id");
			int cost = Integer.parseInt(request.getParameter("cost"));
			String result = "no";
			try {
				if (this.db.buy_skin(usr_id, cost)) {
					result = "ok";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HashMap<String, String> data = new HashMap<>();
			data.put("result", result);
			sendJson(new Gson().toJson(data), response);
		}
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void sendJson(String json_str, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		System.out.println("Sending json " + json_str);
		PrintWriter out = null;
		try {
		    out = response.getWriter();
		    out.write(json_str);
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    if (out != null) {
		        out.close();
		    }	
		}
	}

}
