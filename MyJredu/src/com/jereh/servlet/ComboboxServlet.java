package com.jereh.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.jereh.dao.ComDao;
import com.jereh.dao.GoodsDao;
import com.jereh.entity.ComboBox;

public class ComboboxServlet extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		System.out.println("id===="+id);
		if(id==null){
			//执行处理，调用业务
			ComDao cDao=new ComDao();
			List<ComboBox> boxList = cDao.searchComsForBox();
			//输出结果,JSON数据
			JSONArray json=JSONArray.fromObject(boxList);
			String jsonStr=json.toString();
			System.out.println(jsonStr);
			response.getWriter().println(jsonStr);
		}else{
			GoodsDao gDao=new GoodsDao();
			List<ComboBox> boxList = gDao.searchGoodsForBox(id);
			//输出结果,JSON数据
			JSONArray json=JSONArray.fromObject(boxList);
			String jsonStr=json.toString();
			System.out.println(jsonStr);
			response.getWriter().println(jsonStr);
		}
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
