package pigame;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/comment.do")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
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
		switch(action) {
		case "do_comment": {
			int work_id = Integer.parseInt(request.getParameter("work_id"));
			int user_id = Integer.parseInt(request.getParameter("user_id"));
			String comment = request.getParameter("comment");
			try {
				new commentdb(new dbUtil().getConnection()).put_comment(work_id, user_id, comment);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		case "get_comment": {
			int work_id = Integer.parseInt(request.getParameter("work_id"));
			try {
				List<HashMap<String, String>> list = new commentdb(new dbUtil().getConnection()).get_comment(work_id);
				Gson gson = new Gson();
				String json = gson.toJson(list);
				sendJson(json, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
