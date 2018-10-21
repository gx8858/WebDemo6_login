package cn.itcast.service;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.UserDaoImpl;
import cn.itcast.vo.User;

/**
 * 用户相关的业务类
 */
public class UserService {

	/**
	 * 用户是否可以登陆
	 * @param user
	 * @return
	 */
	public User loginUser(User user){
		UserDao dao = new UserDaoImpl();
		// 防止SQL注入
		return dao.findUserByPrepared(user);
	}
	
}
