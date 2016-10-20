package com.jereh.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jereh.entity.Users;

/**
 * 实现对sys_user表进行操作
 * @author Administrator
 *
 */
public class UserDao extends BaseDao {

	/**
	 * 根据用户名和密码验证用户信息	
	 * @param uname
	 * @param upwd
	 * @return true 登录成功 false 登录失败
	 */
	public boolean login(String uname,String upwd){
		boolean ret=false;
		String sql="select * from sys_user where " +
				"user_name='"+uname+"' and user_pwd='"+upwd+"'";
		ResultSet rs=super.executeQuery(sql);
		try {
			if(rs.next()){
				ret=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	public int delete(int id){
		int ret=0;
		String sql="delete from sys_user where user_id=?";
		
		ret=super.executeUpdate(sql,Integer.valueOf(id));
		
		return ret;
		
	}
	public int updateUser(Users user){
		String sql = " update sys_user " +
			     	"    set user_name    = ?,"+
			     	"        real_name    = ?,"+
			     	"        company_name = ?,"+
			     	"        depart_name  = ? "+
			     	"  where user_id      = ? ";
		Object[] parm = new Object[5];
		parm[0] = user.getUserName();
		parm[1] = user.getRealName();
		parm[2] = user.getCompanyName();
		parm[3] = user.getDepartName();
		parm[4] = user.getId();
		return super.executeUpdate(sql, parm);
	}
	public int insertUser(Users user){
		int ret = 0;
		String sql = "insert into sys_user " +
					" (user_name," +
					"  user_pwd," +
					"  real_name," +
					"  company_name," +
					"  depart_name," +
					"  enable) " +
					" values(?,?,?,?,?,?)";
		Object parm[] = new Object[6];
		parm[0] = user.getUserName();
		parm[1] = user.getUserPwd();
		parm[2] = user.getRealName();
		parm[3] = user.getCompanyName();
		parm[4] = user.getDepartName();
		parm[5] = Integer.valueOf(1);
		ret = super.executeUpdate(sql, parm);
		return ret;
	}
	public boolean checkUser(String realName){
		String sql = " select count(*) user_count " +
				     "   from sys_user " +
				     "  where real_name ='"+realName+"'";
		ResultSet rs = super.executeQuery(sql);
		try {
			if(rs.next()){
				int count = rs.getInt("user_count");
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
	public List<Users> searchUsers(){
		List<Users>  userList=new ArrayList<Users>();
		String sql="select *  from sys_user order by user_id ";
		ResultSet rs=super.executeQuery(sql);
		Users user=null;
		try {
			while(rs.next()){
				int id=rs.getInt("user_id");
				String userName=rs.getString("user_name");
				String realName=rs.getString("real_name");
				String companyName=rs.getString("company_name");
				String departName=rs.getString("depart_name");
				System.out.println(id+" "+userName);
				user=new Users();
				user.setId(id);
				user.setUserName(userName);
				user.setRealName(realName);
				user.setCompanyName(companyName);
				user.setDepartName(departName);
		
				userList.add(user);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return userList;
	}
	public List<Users> searchUsersCom(){
		List<Users>  userList=new ArrayList<Users>();
		String sql="select *  from sys_user order by user_id ";
		ResultSet rs=super.executeQuery(sql);
		Users user=null;
		try {
			while(rs.next()){
				int id=rs.getInt("user_id");
				String userName=rs.getString("user_name");
					user=new Users();
				user.setId(id);
				user.setUserName(userName);
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return userList;
	}
	public static void main(String[] args) {
		UserDao userDao=new UserDao();
		List<Users> userList=userDao.searchUsers();
		for(int i=0;i<userList.size();i++){
			Users user=userList.get(i);
			System.out.println(user.getId()+" "+user.getUserName());
		}
		
	}
}
