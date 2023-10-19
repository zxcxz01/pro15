<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="file1" value="${param.param1}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${file1}<br>
	<a href="http://localhost:8090/pro15/downLoad.do?fileName=${file1}">판다 파일 내려받기</a><br>

</body>
</html>