package com.jereh.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.jereh.dao.ComDao;
import com.jereh.dao.UserDao;
import com.jereh.entity.Company;
import com.jereh.entity.Users;

public class CompanyServlet extends HttpServlet {

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
		String action = request.getParameter("action");
		try {
			if(action==null){
				selectCom(request,response);
			}
			if(action.equals("add")){
				this.insertCom(request, response);
			}else if(action.equals("update")){
				this.updateCom(request, response);
			}else if(action.equals("delete")){
				this.deleteCom(request, response);
			}else{
				selectCom(request,response);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public void deleteCom(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//������������
		String[] uid = 
			request.getParameterValues("uid[]");
		//����ҵ��
		ComDao cDao=new ComDao();
		int ret = 0;
		for(int i=0;i<uid.length;i++){
			ret += cDao.delete(uid[i]);
		}
		//������
		if(ret>0){
			response.getWriter().print(1);
		}else{
			response.getWriter().print(0);
		}
		
		
	}
	public void insertCom(HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
			response.setContentType("text/html");
			request.setCharacterEncoding("utf-8");
			
			String comId = 
			request.getParameter("comId");
			String comName = 
			request.getParameter("comName");
			String comType = 
			request.getParameter("comType");
		
			//����װ��ʵ����
			Company com = new Company();
			com.setComId(comId);
			com.setComName(comName);
			com.setComType(comType);
			
			//����SQL���,�������ݿ�
			ComDao cDao = new ComDao();
			//��鵥λID���Ƿ����
			if(cDao.checkUser(comId)){
			  response.getWriter().print(2);
			}else{
		 	  int ret = cDao.insertUser(com);
			response.getWriter().print(ret);
			}
		}
	public void updateCom(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
			response.setContentType("text/html");
			request.setCharacterEncoding("utf-8");
			String comId = 
				request.getParameter("comId");
				String comName = 
				request.getParameter("comName");
				String comType = 
				request.getParameter("comType");
			
				//����װ��ʵ����
				Company com = new Company();
				com.setComId(comId);
				com.setComName(comName);
				com.setComType(comType.equals("A")?"�ͻ�":"��Ӧ��");
			//����SQL���,�������ݿ�
			ComDao cDao = new ComDao();
			int ret = cDao.updateCom(com);
			response.getWriter().print(ret);
			
		}

	public void selectCom(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//ִ�д�������ҵ��
		ComDao comDao=new ComDao();
		List<Company> comList=comDao.searchComs();
		JSONArray json=JSONArray.fromObject(comList);
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
