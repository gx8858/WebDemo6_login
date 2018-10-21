package cn.itcast.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.service.UserService;
import cn.itcast.vo.User;

/**
 * 登录的servlet
 */
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -5933952794357976456L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 接收数据
		 * 封装数据
		 * 处理数据
		 * 显示结果
		 */
		// 设置编码
		request.setCharacterEncoding("UTF-8");
		// 接收数据
		Map<String, String []> map = request.getParameterMap();
		User user = new User();
		try {
			// 封装数据
			BeanUtils.populate(user, map);
			// 调用业务层的代码
			UserService us = new UserService();
			// 判断是否登陆
			User existUser = us.loginUser(user);
			// 判断是否为null
			if(existUser == null){
				// 说明失败
				// 向域中存值
				request.setAttribute("msg", "用户名或者密码错误");
				// 使用转发
				request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
			}else{
				// 把用户的信息保存到session中
				request.getSession().setAttribute("existUser", existUser);
				// 重定向到页面
				response.sendRedirect(request.getContextPath()+"/pages/success.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
