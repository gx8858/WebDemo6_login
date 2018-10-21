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
	 * ��ɵ�½�Ĳ�����daoʵ���ࣩ
	 * (��ֹSQLע��)
	 */
	public User findUserByPrepared(User user) {
		// ��ɵ�½�Ĺ��ܣ�ͨ���û����������ѯ���ݿ⣩
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// �Ȼ�ȡ���Ӷ���
			conn = MyJdbcUtil.getConnection();
			// ��д��SQL��䲻ͬ
			String sql = "select * from t_user where username = ? and password = ? ";
			// Ԥ���루�Ѿ���SQL���͸�����������Ԥ���룬��ʽ�͹̶��ˣ�
			stmt = conn.prepareStatement(sql);
			// ���ò�����ֵ
			stmt.setString(1, user.getUsername());  // bbb ' or ' 1 = 1����ʶor�ؼ���
			stmt.setString(2, user.getPassword());
			// ִ��SQL������SQL���ģ�
			rs = stmt.executeQuery();
			// �����
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
			// �ͷ���Դ
			MyJdbcUtil.release(rs, stmt, conn);
		}
		return null;
		
	}
	

	/**
	 * ��ɵ�½�Ĳ���
	 * (���ܷ�ֹSQLע��)
	 */
	public User findUser(User user) {
		// ��ɵ�½�Ĺ��ܣ�ͨ���û����������ѯ���ݿ⣩
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// �Ȼ�ȡ���Ӷ���
			conn = MyJdbcUtil.getConnection();
			// ��дSQL���
			String sql = "select * from t_user where username = '"+user.getUsername()+"' and password= '"+user.getPassword()+"' ";
			// ִ��SQL
			stmt = conn.createStatement();
			// ִ��SQL��䣬����л��ѯ��һ����¼
			rs = stmt.executeQuery(sql);
			// �����
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
			// �ͷ���Դ
			MyJdbcUtil.release(rs, stmt, conn);
		}
		return null;
	}
	
	
	/**
	 * �����ӵĹ���
	 */
	public void insert(User user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// ��ȡ����
			conn = MyJdbcUtil.getConnection();
			// ��дSQL���
			String sql = "insert into t_user values (null,?,?)";
			// Ԥ����SQL
			stmt = conn.prepareStatement(sql);
			// ���ò���
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			// ִ��SQL
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			// �ͷ���Դ
			MyJdbcUtil.release(stmt, conn);
		}
	}

	/**
	 * �޸��û�������
	 */
	public void update(User user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// ��ȡ����
			conn = MyJdbcUtil.getConnection();
			// ��дSQL���
			String sql = "update t_user set username = ? , password = ? where id = ?";
			// Ԥ����SQL
			stmt = conn.prepareStatement(sql);
			// ���ò���
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setInt(3, user.getId());
			// ִ��SQL
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			// �ͷ���Դ
			MyJdbcUtil.release(stmt, conn);
		}
	}


	/**
	 * ɾ���û��ļ�¼
	 */
	public void delete(User user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// ��ȡ����
			conn = MyJdbcUtil.getConnection();
			// ��дSQL���
			String sql = "delete from t_user where id = ?";
			// Ԥ����SQL
			stmt = conn.prepareStatement(sql);
			// ���ò���
			stmt.setInt(1, user.getId());
			// ִ��SQL
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			// �ͷ���Դ
			MyJdbcUtil.release(stmt, conn);
		}
	}

	/**
	 * ͨ���û�����������ȡ�û�����ϸ��Ϣ
	 */
	public User findUserById(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// ��ȡ����
			conn = MyJdbcUtil.getConnection();
			// ��дSQL���
			String sql = "select * from t_user where id = ?";
			// Ԥ����SQL
			stmt = conn.prepareStatement(sql);
			// ���ò���
			stmt.setInt(1, id);
			// ִ��SQL
			rs = stmt.executeQuery();
			// һ����¼
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
			// �ͷ���Դ
			MyJdbcUtil.release(rs, stmt, conn);
		}
		return null;
	}

	/**
	 * ��ȡ���е��û���Ϣ
	 */
	public List<User> findUsers() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<User>();
		try {
			// ��ȡ����
			conn = MyJdbcUtil.getConnection();
			// ��дSQL���
			String sql = "select * from t_user";
			// Ԥ����SQL
			stmt = conn.prepareStatement(sql);
			// ִ��SQL
			rs = stmt.executeQuery();
			// һ����¼
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
			// �ͷ���Դ
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













