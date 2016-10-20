package com.jereh.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jereh.entity.ComboBox;
import com.jereh.entity.Company;
import com.jereh.entity.Users;

public class ComDao extends BaseDao{
	public int delete(String id){
		int ret=0;
		String sql="delete from company where comId=?";
		
		ret=super.executeUpdate(sql,id);
		
		return ret;
		
	}
	public int updateCom(Company com){
		String sql = " update company " +
			     	"    set comName    = ?,"+
			     	"        comType    = ?"+
			     	"  where comId      = ? ";
		Object[] parm = new Object[3];
		parm[0] = com.getComName();
		parm[1] = com.getComType();
		parm[2] = com.getComId();
		return super.executeUpdate(sql, parm);
	}
	public int insertUser(Company com){
		int ret = 0;
		String sql = "insert into company " +
					" values(?,?,?)";
		Object parm[] = new Object[3];
		parm[0] = com.getComId();
		parm[1] = com.getComName();
		parm[2] = com.getComType();
		ret = super.executeUpdate(sql, parm);
		return ret;
	}
	public boolean checkUser(String comId){
		String sql = " select count(*) com_count " +
				     "   from company " +
				     "  where comId ='"+comId+"'";
		ResultSet rs = super.executeQuery(sql);
		try {
			if(rs.next()){
				int count = rs.getInt("com_count");
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
	public List<Company> searchComs(){
		List<Company>  comList=new ArrayList<Company>();
		String sql="select *  from company order by comId ";
		ResultSet rs=super.executeQuery(sql);
		Users user=null;
		try {
			while(rs.next()){
				String comId      =rs.getString("comId");
				String comName    =rs.getString("comName");
				String comType    =rs.getString("comType");
				Company com = new Company();
				com.setComId(comId);
				com.setComName(comName);
				com.setComType(comType.equals("A")?"客户":"供应商");
				comList.add(com);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return comList;
	}	
	public List<ComboBox> searchComsForBox(){
		List<ComboBox>  boxList=new ArrayList<ComboBox>();
		String sql="select comId,comName  " +
					" from company " +
					" where comType='B' " +
					" order by comId ";
		ResultSet rs=super.executeQuery(sql);
		try {
			while(rs.next()){
				String comId      =rs.getString("comId");
				String comName    =rs.getString("comName");
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
