package com.jereh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jereh.dao.UserDao;
import com.jereh.entity.Users;

public class UserBizServlet extends HttpServlet {

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
		response.setContentType("text/html");
		doPost(request, response);
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
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		String action = 
			request.getParameter("action");
		try{
			if(action.equals("add")){
				insertUser(request,response);
			}else if(action.equals("update")){
				updateUser(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void insertUser(HttpServletRequest request, 
			            HttpServletResponse response) throws Exception {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		
		String name = 
			request.getParameter("userName");
		String rName = 
			request.getParameter("realName");
		String cName = 
			request.getParameter("companyName");
		String pName = 
			request.getParameter("departName");
		//数据装入实体类
		Users user = new Users();
		user.setUserName(name);
		user.setUserPwd("666");
		user.setRealName(rName);
		user.setCompanyName(cName);
		user.setDepartName(pName);
		
		//调用SQL语句,插入数据库
		UserDao uDao = new UserDao();
		//检查用户名是否存在
		if(uDao.checkUser(rName)){
			response.getWriter().print(2);
		}else{
			int ret = uDao.insertUser(user);
			response.getWriter().print(ret);
		}
	}
	public void updateUser(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		String name  = request.getParameter("userName");
		String rName = request.getParameter("realName");
		String cName = request.getParameter("companyName");
		String pName = request.getParameter("departName");
		String id    = request.getParameter("id");
		//装入实体类
		Users user = new Users();
		user.setId(Integer.parseInt(id));
		user.setUserName(name);
		user.setRealName(rName);
		user.setCompanyName(cName);
		user.setDepartName(pName);
		//调用SQL语句,插入数据库
		UserDao uDao = new UserDao();
		int ret = uDao.updateUser(user);
		response.getWriter().print(ret);
	
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
