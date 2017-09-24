package pigame;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.StreamingNotifiable;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet(description = "get real article", urlPatterns = { "/article.do" })
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleServlet() {
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
		String url = request.getParameter("url");
		String work_id = request.getParameter("work_id");
		int usr_id;
		try {
			usr_id = (int) request.getSession().getAttribute("usr_id");
		} catch (Exception ex) {
			usr_id = -1;
		}
		request.setAttribute("article_url", url);
		request.setAttribute("article_id", Integer.parseInt(work_id));
		request.setAttribute("usr_id", usr_id);
		request.getRequestDispatcher("text.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
