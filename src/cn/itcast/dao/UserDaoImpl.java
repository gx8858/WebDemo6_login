package cn.itcast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.itcast.utils.MyJdbcUtil;
import cn.itcast.vo.User;

public class UserDaoImpl implements UserDao {
	
	/**
	 * 完成登陆的操作（dao实现类）
	 * (防止SQL注入)
	 */
	public User findUserByPrepared(User user) {
		// 完成登陆的功能（通过用户名和密码查询数据库）
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// 先获取链接对象
			conn = MyJdbcUtil.getConnection();
			// 编写的SQL语句不同
			String sql = "select * from t_user where username = ? and password = ? ";
			// 预编译（已经把SQL发送给服务器进行预编译，格式就固定了）
			stmt = conn.prepareStatement(sql);
			// 设置参数的值
			stmt.setString(1, user.getUsername());  // bbb ' or ' 1 = 1不认识or关键字
			stmt.setString(2, user.getPassword());
			// 执行SQL（不带SQL语句的）
			rs = stmt.executeQuery();
			// 如果有
			if(rs.next()){
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				return u;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			// 释放资源
			MyJdbcUtil.release(rs, stmt, conn);
		}
		return null;
		
	}
	

	/**
	 * 完成登陆的操作
	 * (不能防止SQL注入)
	 */
	public User findUser(User user) {
		// 完成登陆的功能（通过用户名和密码查询数据库）
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 先获取链接对象
			conn = MyJdbcUtil.getConnection();
			// 编写SQL语句
			String sql = "select * from t_user where username = '"+user.getUsername()+"' and password= '"+user.getPassword()+"' ";
			// 执行SQL
			stmt = conn.createStatement();
			// 执行SQL语句，如果有会查询出一条记录
			rs = stmt.executeQuery(sql);
			// 如果有
			if(rs.next()){
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				return u;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			// 释放资源
			MyJdbcUtil.release(rs, stmt, conn);
		}
		return null;
	}
	
	
	/**
	 * 完成添加的功能
	 */
	public void insert(User user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// 获取链接
			conn = MyJdbcUtil.getConnection();
			// 编写SQL语句
			String sql = "insert into t_user values (null,?,?)";
			// 预编译SQL
			stmt = conn.prepareStatement(sql);
			// 设置参数
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			// 执行SQL
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			// 释放资源
			MyJdbcUtil.release(stmt, conn);
		}
	}

	/**
	 * 修改用户的数据
	 */
	public void update(User user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// 获取链接
			conn = MyJdbcUtil.getConnection();
			// 编写SQL语句
			String sql = "update t_user set username = ? , password = ? where id = ?";
			// 预编译SQL
			stmt = conn.prepareStatement(sql);
			// 设置参数
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setInt(3, user.getId());
			// 执行SQL
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			// 释放资源
			MyJdbcUtil.release(stmt, conn);
		}
	}


	/**
	 * 删除用户的记录
	 */
	public void delete(User user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// 获取链接
			conn = MyJdbcUtil.getConnection();
			// 编写SQL语句
			String sql = "delete from t_user where id = ?";
			// 预编译SQL
			stmt = conn.prepareStatement(sql);
			// 设置参数
			stmt.setInt(1, user.getId());
			// 执行SQL
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			// 释放资源
			MyJdbcUtil.release(stmt, conn);
		}
	}

	/**
	 * 通过用户的主键来获取用户的详细信息
	 */
	public User findUserById(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// 获取链接
			conn = MyJdbcUtil.getConnection();
			// 编写SQL语句
			String sql = "select * from t_user where id = ?";
			// 预编译SQL
			stmt = conn.prepareStatement(sql);
			// 设置参数
			stmt.setInt(1, id);
			// 执行SQL
			rs = stmt.executeQuery();
			// 一条记录
			if(rs.next()){
				User u = new User();
				u.setId(id);
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				return u;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			// 释放资源
			MyJdbcUtil.release(rs, stmt, conn);
		}
		return null;
	}

	/**
	 * 获取所有的用户信息
	 */
	public List<User> findUsers() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<User>();
		try {
			// 获取链接
			conn = MyJdbcUtil.getConnection();
			// 编写SQL语句
			String sql = "select * from t_user";
			// 预编译SQL
			stmt = conn.prepareStatement(sql);
			// 执行SQL
			rs = stmt.executeQuery();
			// 一条记录
			while(rs.next()){
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				list.add(u);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			// 释放资源
			MyJdbcUtil.release(rs, stmt, conn);
		}
		return null;
	}
	
	
	@Test
	public void run(){
//		User u = new User("ddd","333");
		// insert(u);
		
//		User u = new User(6,"eee","333");
		// update(u);
		// delete(u);
		
		//User u2 = findUserById(2);
		//System.out.println(u2);
		
//		List<User> list = findUsers();
//		for (User user : list) {
//			System.out.println(user);
//		}
	}

}













