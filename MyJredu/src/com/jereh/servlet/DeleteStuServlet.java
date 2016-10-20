package com.jereh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jereh.dao.UserDao;

public class DeleteStuServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DeleteStuServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
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
		//接受请求数据
		String[] uid=request.getParameterValues("uid[]");
		//调用业务
		UserDao userDao=new UserDao();
		int ret = 0;
		for(int i= 0; i<uid.length;i++){
			int dId = Integer.parseInt(uid[i]);
			ret += userDao.delete(dId);
		}
		if(ret > 0){
			response.getWriter().print(1);
		}else{
			response.getWriter().print(0);
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
