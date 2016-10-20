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
import com.jereh.entity.Company;
import com.jereh.entity.Goods;

public class GoodsServlet extends HttpServlet {

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
				selectGoods(request,response);
			}
			if(action.equals("add")){
				insertGoods(request, response);
			}else if(action.equals("update")){
				updateGoods(request, response);
			}else if(action.equals("delete")){
				deleteGood(request, response);
			}else{
				selectGoods(request,response);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	public void deleteGood(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		//接受请求数据
		String[] uid = 
			request.getParameterValues("uid[]");
		//调用业务
		GoodsDao gDao=new GoodsDao();
		int ret = 0;
		for(int i=0;i<uid.length;i++){
			ret += gDao.delete(uid[i]);
		}
		//输出结果
		if(ret>0){
			response.getWriter().print(1);
		}else{
			response.getWriter().print(0);
		}
		
		
	}
	public void insertGoods(HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
			response.setContentType("text/html");
			request.setCharacterEncoding("utf-8");
			
			String goodsId = 
			request.getParameter("goodsId");
			String goodsName = 
			request.getParameter("goodsName");
			String price = 
			request.getParameter("price");
			String goodsCom =
			request.getParameter("goodsCom");
		
			//数据装入实体类
			Goods goods = new Goods();
			goods.setGoodsId(goodsId);
			goods.setGoodsCom(goodsCom);
			goods.setGoodsName(goodsName);
			goods.setPrice(Double.parseDouble(price));
			
			//调用SQL语句,插入数据库
			GoodsDao gDao = new GoodsDao();
			//检查单位ID名是否存在
			if(gDao.checkGoods(goodsId)){
			  response.getWriter().print(2);
			}else{
		 	  int ret = gDao.insertGoods(goods);
			response.getWriter().print(ret);
			}
		}
	public void updateGoods(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
			response.setContentType("text/html");
			request.setCharacterEncoding("utf-8");
			String goodsId = 
				request.getParameter("goodsId");
				String goodsName = 
				request.getParameter("goodsName");
				String price = 
				request.getParameter("price");
				String goodsCom =
				request.getParameter("goodsCom");
			
				//数据装入实体类
				Goods goods = new Goods();
				goods.setGoodsId(goodsId);
				goods.setGoodsCom(goodsCom);
				goods.setGoodsName(goodsName);
				goods.setPrice(Double.parseDouble(price));
				
			//调用SQL语句,插入数据库
			GoodsDao gDao = new GoodsDao();
			int ret = gDao.updateGoods(goods);
			response.getWriter().print(ret);
			
		}

	public void selectGoods(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//执行处理，调用业务
		GoodsDao gDao=new GoodsDao();
		List<Goods> goodsList=gDao.searchGoods();
		JSONArray json=JSONArray.fromObject(goodsList);
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
