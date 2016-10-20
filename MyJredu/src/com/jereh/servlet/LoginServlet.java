package com.jereh.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jereh.dao.UserDao;

public class LoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * 处理get方式的请求
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		this.doPost(request, response);
	}

	/**
	 * 处理post方式的请求
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDao userDao=new UserDao();
		//获得用户请求的数据
		String uname=request.getParameter("uname");
		String upass=request.getParameter("upwd");
		//登录验证(业务)
		boolean ret=userDao.login(uname, upass);
		if(ret){
			response.getWriter().print("1");
		}else{
			response.getWriter().println("0");
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
