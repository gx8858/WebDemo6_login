package cn.itcast.service;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.UserDaoImpl;
import cn.itcast.vo.User;

/**
 * �û���ص�ҵ����
 */
public class UserService {

	/**
	 * �û��Ƿ���Ե�½
	 * @param user
	 * @return
	 */
	public User loginUser(User user){
		UserDao dao = new UserDaoImpl();
		// ��ֹSQLע��
		return dao.findUserByPrepared(user);
	}
	
}
