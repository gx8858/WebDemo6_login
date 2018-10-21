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
 * ��¼��servlet
 */
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -5933952794357976456L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * ��������
		 * ��װ����
		 * ��������
		 * ��ʾ���
		 */
		// ���ñ���
		request.setCharacterEncoding("UTF-8");
		// ��������
		Map<String, String []> map = request.getParameterMap();
		User user = new User();
		try {
			// ��װ����
			BeanUtils.populate(user, map);
			// ����ҵ���Ĵ���
			UserService us = new UserService();
			// �ж��Ƿ��½
			User existUser = us.loginUser(user);
			// �ж��Ƿ�Ϊnull
			if(existUser == null){
				// ˵��ʧ��
				// �����д�ֵ
				request.setAttribute("msg", "�û��������������");
				// ʹ��ת��
				request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
			}else{
				// ���û�����Ϣ���浽session��
				request.getSession().setAttribute("existUser", existUser);
				// �ض���ҳ��
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
