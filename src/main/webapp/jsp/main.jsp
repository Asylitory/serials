<%@ page language="java" contentType="text/html; charset=windows-1251"
    pageEncoding="windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/jsp/css/main.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/jsp/css/table.css">
<title>${form.title}</title>
</head>
<body>
<div id="wrap">
	<div id="header"><img src="${pageContext.request.contextPath}/jsp/img/logo.gif"></div>
	<div id="top"></div>
	<div id="center">
		<div id="navigation">
			<h3>Список сериалов:</h3>
			<ul>
				<li>
					<form action="${pageContext.request.contextPath}/edit" method="POST">
						<input type="hidden" name="type" value="${constants.serial}">
						<input type="submit" name="${constants.new_entry}" value="Добавить сериал">
					</form>
				</li><br />
				<c:forEach var="serial" items="${form.serialList}">
					<li><a href="${pageContext.request.contextPath}/main?serialId=${serial.serialId}">${serial.serialTitle}</a></li>
				</c:forEach>
			</ul>
		</div>
		<div id="body">
			<c:choose>
				<c:when test="${form.requestType == constants.serial}">
					<c:import url="/jsp/serial.jsp">
						<c:param name="form" value="${form}" />
						<c:param name="constants" value="${constants}" />
					</c:import>
				</c:when>
				<c:when test="${form.requestType == constants.season}">
					<c:import url="/jsp/season.jsp">
						<c:param name="form" value="${form}" />
						<c:param name="constants" value="${constants}" />
					</c:import>
				</c:when>
				<c:when test="${form.requestType == constants.series}">
					<c:import url="/jsp/series.jsp">
						<c:param name="form" value="${form}" />
						<c:param name="constants" value="${constants}" />
					</c:import>
				</c:when>
				<c:otherwise>
				Выберите сериал
				</c:otherwise>
			</c:choose>
		</div>
		<div id="clearer"></div>
	</div>
	
	<div id="bottom"></div>
	<div id="footer">
		<h3>Powered by Tass @ 2013</h3>
	</div>
</div>
</body>
</html>