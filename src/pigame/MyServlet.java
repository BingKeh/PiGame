package pigame;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.xml.ws.Response;

@WebServlet(name ="MyServlet", urlPatterns = { "/my" })
public class MyServlet implements Servlet {
	
	private ServletConfig servletConfig;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return this.servletConfig;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		this.servletConfig = arg0;
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ServletName = getServletConfig().getServletName();
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		printWriter.println("The Servelet is " + ServletName + "</br>");
	}

}
