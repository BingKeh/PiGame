package pigame;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

/**
 * Servlet implementation class SpaceServlet
 */
@WebServlet("/SpaceServlet")
public class SpaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SpaceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pWriter = response.getWriter();
		try {
			String logged = request.getSession().getAttribute("logged").toString();
			if (logged.equals("yes")) {
				String uString = request.getSession().getAttribute("usr").toString();
				//pWriter.append("Welcome to your space " + uString);
				response.sendRedirect("space.jsp");
			} else {
				pWriter.append("There is a error!");
			}
		} catch (Exception ex) {
			pWriter.append("There is a error!");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
