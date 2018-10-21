<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>欢迎页面</h3>
<c:if test="${ empty existUser }">
	<h3>亲，您还没有登陆，请您<a href="${ pageContext.request.contextPath }/pages/login.jsp">登陆</a></h3>
</c:if>

<c:if test="${ not empty existUser }">
	<h3>亲，欢迎您：${ existUser.username }</h3>
</c:if>


</body>
</html>