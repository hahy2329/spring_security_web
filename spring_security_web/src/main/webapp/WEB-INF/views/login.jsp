<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<form action="${contextPath }/login.do" method="post">
		<div>
			<label for="id">id:</label>
			<input type="text" id="id" name="id"/>
		</div>
		
		<div>
			<label for="password">pw:</label>
			<input type="password" id="password" name="pw"/>
		</div>
		
		<button type="submit">submit</button>
	</form>
	
	<!-- 로그인 페이지로 security-context.xml에서의 username-parameter, password-parameter
	값 (id,pw)을 name 태그를 이용하여 설정 후 전달
	
	Action 위치는 security-context.xml에서의 login-processing-url(login.do)로
	AuthenticationProvider에 접근하여 인증 절차를 진행  
	-->
	
</body>
</html>