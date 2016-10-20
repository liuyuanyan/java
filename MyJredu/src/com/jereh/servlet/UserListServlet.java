package com.jereh.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.jereh.dao.UserDao;
import com.jereh.entity.Users;

public class UserListServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserListServlet() {
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

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//执行处理，调用业务
		UserDao userDao=new UserDao();
		List<Users> userList=userDao.searchUsers();
		
		//输出结果,JSON数据
		//userList-->Json "[
		//{id:27, userName:'admin'},
		//{id:43, userName:'user01'}
		//	]"
		//json-lib
		JSONArray json=JSONArray.fromObject(userList);
		String jsonStr=json.toString();
		System.out.println(jsonStr);
		response.getWriter().println(jsonStr);
		
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
