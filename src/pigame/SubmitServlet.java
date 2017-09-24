package pigame;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.metal.MetalBorders.TableHeaderBorder;

import com.google.gson.Gson;

import sun.misc.BASE64Decoder;

/**
 * Servlet implementation class SubmitServlet
 */
@WebServlet(description = "servlet for submit", urlPatterns = { "/submit" })
public class SubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PATH = "article";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		if (request.getParameter("action") == null) {
			String logged = "no";
			try {
				if (request.getSession().getAttribute("logged").toString().equals("yes")) {
					logged = "yes";
					request.getRequestDispatcher("submit.html").forward(request, response);
				} else {
					response.sendRedirect("index");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				response.sendRedirect("index");
			}
		} else {
			String action = request.getParameter("action");
			switch (action) {
			case "publish" : {
				String usr = request.getSession().getAttribute("usr").toString();
				String data = request.getParameter("data");
				String no = new Date().getTime() + usr;
				String heading = request.getParameter("heading");
				String coverBase64 = request.getParameter("cover");
				BASE64Decoder decoder = new BASE64Decoder();
				coverBase64 = coverBase64.split(",")[1];
				data = URLDecoder.decode(data, "UTF-8");
				String uploadPath = getServletContext().getRealPath("./") + File.separator + PATH;
				File uploadDic = new File(uploadPath);
				if (!uploadDic.exists()) {
					uploadDic.mkdir();
				}
				String coverPath = uploadPath + File.separator + "cover";
				String coverfilePath = coverPath + File.separator + no + ".jpg";
				String filePath = uploadPath + File.separator + no + ".html";
				File file = new File(filePath);
				file.createNewFile();
				FileWriter writer;
				writer = new FileWriter(file);
				writer.write(data);
				writer.flush();
				writer.close();
				
				try {
					byte[] buffer = decoder.decodeBuffer(coverBase64);
					
					File coverDic = new File(coverPath);
					if (!coverDic.exists()) {
						coverDic.mkdir();
					}
					
					File file1 = new File(coverfilePath);
					file1.createNewFile();
					OutputStream oStream = new FileOutputStream(file1);
					oStream.write(buffer);
					oStream.flush();
					oStream.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
				int id = (int) request.getSession().getAttribute("usr_id");
				try {
					new articledb(new dbUtil().getConnection()).putWork(id, "ARTICLE", filePath, heading, coverfilePath);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(data);
				Gson gson = new Gson();
		        HashMap<String, String> map = new HashMap<>();
		        map.put("status", "文章发表成功");
		        String json = gson.toJson(map);
		        sendJson(json, response);
		        break;
			}
			case "get_article": {
				String role = request.getParameter("role");
				
				try {
					List<HashMap<String, String>> list = new articledb(new dbUtil().getConnection()).getWork(0, getServletContext().getRealPath("./"));
					Gson gson = new Gson();
					String json_str = gson.toJson(list);
					sendJson(json_str, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			
			case "black_room": {
				int work_id = Integer.parseInt(request.getParameter("work_id"));
				String value = "权限失败";
				try {
					if (new articledb(new dbUtil().getConnection()).black_room(work_id)) {
						value = "权限成功";	
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				HashMap<String, String> data = new HashMap<>();
				data.put("result", value);
				sendJson(new Gson().toJson(data), response);
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
