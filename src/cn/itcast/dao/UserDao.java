package cn.itcast.dao;

import java.util.List;

import cn.itcast.vo.User;

/**
 * dao接口
 */
public interface UserDao {
	
	public User findUser(User user);
	
	public User findUserByPrepared(User user);
	
	// 添加数据
	public void insert(User user);
	// 修改数据
	public void update(User user);
	// 删除数据
	public void delete(User user);
	// 通过主键来查询单个用户
	public User findUserById(int id);
	// 查询所有用户
	public List<User> findUsers();
	
}
