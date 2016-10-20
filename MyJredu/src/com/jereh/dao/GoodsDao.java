package com.jereh.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jereh.entity.ComboBox;
import com.jereh.entity.Company;
import com.jereh.entity.Goods;
import com.jereh.entity.Users;

public class GoodsDao extends BaseDao{
	public int delete(String id){
		int ret=0;
		String sql="delete from goods where goodsId=?";
		
		ret=super.executeUpdate(sql,id);
		
		return ret;
		
	}
	public int updateGoods(Goods goods){
		String sql = " update goods " +
			     	"    set goodsName    = ?,"+
			     	"        goodsCom     = ?,"+
			     	"        price        = ?"+
			     	"  where goodsId      = ? ";
		Object[] parm = new Object[4];
		parm[0] = goods.getGoodsName();
		parm[1] = goods.getGoodsCom();
		parm[2] = goods.getPrice();
		parm[3] = goods.getGoodsId();
		return super.executeUpdate(sql, parm);
	}
	public int insertGoods(Goods goods){
		int ret = 0;
		String sql = "insert into goods " +
					" values(?,?,?,?)";
		Object parm[] = new Object[4];
		parm[0] = goods.getGoodsId();
		parm[1] = goods.getGoodsName();
		parm[2] = goods.getGoodsCom();
		parm[3] = goods.getPrice();
		ret = super.executeUpdate(sql, parm);
		return ret;
	}
	public boolean checkGoods(String goodsId){
		String sql = " select count(*) goods_count " +
				     "   from goods " +
				     "  where goodsId ='"+goodsId+"'";
		ResultSet rs = super.executeQuery(sql);
		try {
			if(rs.next()){
				int count = rs.getInt("goods_count");
				if(count > 0){
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public List<Goods> searchGoods(){
		List<Goods>  goodsList=new ArrayList<Goods>();
		String sql="select *  from goods order by goodsId ";
		ResultSet rs=super.executeQuery(sql);
		try {
			while(rs.next()){
				String goodsId    =rs.getString("goodsId");
				String goodsName  =rs.getString("goodsName");
				double price      =rs.getDouble("price");
				String goodsCom   =rs.getString("goodsCom");
				Goods goods = new Goods();
				goods.setGoodsId(goodsId);
				goods.setGoodsName(goodsName);
				goods.setGoodsCom(goodsCom);
				goods.setPrice(price);
				goodsList.add(goods);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return goodsList;
	}	
	public List<ComboBox> searchGoodsForBox(String company){
		List<ComboBox>  boxList=new ArrayList<ComboBox>();
		String sql="select goodsId,goodsName  " +
					" from goods " +
					" where goodsCom='"+company+"' " +
					" order by goodsId ";
		ResultSet rs=super.executeQuery(sql);
		try {
			while(rs.next()){
				String comId      =rs.getString("goodsId");
				String comName    =rs.getString("goodsName");
				ComboBox com = new ComboBox();
				com.setValue(comId);
				com.setText(comName);
				boxList.add(com);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return boxList;
	}	
}
