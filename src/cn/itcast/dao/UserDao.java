package cn.itcast.dao;

import java.util.List;

import cn.itcast.vo.User;

/**
 * dao�ӿ�
 */
public interface UserDao {
	
	public User findUser(User user);
	
	public User findUserByPrepared(User user);
	
	// �������
	public void insert(User user);
	// �޸�����
	public void update(User user);
	// ɾ������
	public void delete(User user);
	// ͨ����������ѯ�����û�
	public User findUserById(int id);
	// ��ѯ�����û�
	public List<User> findUsers();
	
}
