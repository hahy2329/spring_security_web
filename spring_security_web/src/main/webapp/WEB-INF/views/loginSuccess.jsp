<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login Success</title>
</head>
<body>
	<p>${sessionScope.id }님 환영합니다!!!</p>
	
	<a href="${contextPath }/myPage">myPage</a>
</body>
</html>