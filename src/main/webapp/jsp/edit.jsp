<%@ page language="java" contentType="text/html; charset=windows-1251"
    pageEncoding="windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/jsp/css/edit.css">
<title>${form.title}</title>
</head>
<body>
<div id="wrap">
	<div id="header"><img src="${pageContext.request.contextPath}/jsp/img/logo.gif"></div>
	<div id="top"></div>
	
	<div id="center">
		<c:choose>
			<c:when test="${form.action == constants.delete}">
				<form action="${pageContext.request.contextPath}/complete" method="POST">
					<c:choose>
						<c:when test="${form.requestType == constants.serial}">
							Удалить сериал ${form.serial.serialTitle}?
							<input type="hidden" name="id" value="${form.serial.serialId}">
							<input type="hidden" name="type" value="${constants.serial}">
						</c:when>
						<c:when test="${form.requestType == constants.season}">
							Удалить сезон ${form.season.seasonTitle} из сериала ${form.serial.serialTitle}?
							<input type="hidden" name="id" value="${form.season.seasonId}">
							<input type="hidden" name="type" value="${constants.season}">
						</c:when>
						<c:otherwise>
							Удалить серию ${form.series.seriesTitle} из сериала ${form.serial.serialTitle}, сезон ${form.season.seasonTitle}?
							<input type="hidden" name="id" value="${form.series.seriesId}">
							<input type="hidden" name="type" value="${constants.series}">
						</c:otherwise>
					</c:choose>
					<table>
						<tr>
							<td>
								Удалить
							</td>
							<td>
								<input type="submit" name="${constants.delete}" value=">>">
							</td>
						</tr>
						<tr>
							<td>
								Отмена
							</td>
							<td>
								<input type="submit" name="${constants.cancel}" value=">>">
							</td>
						</tr>
					</table>
				</form>
			</c:when>
			<c:otherwise>
				<form action="${pageContext.request.contextPath}/complete" method="POST">
					<table>
						<tr>
							<td>
								Название
							</td>
							<td>
					<c:choose>
						<c:when test="${form.requestType == constants.serial}">
								<input type="text" name="serialTitle" value="${form.serial.serialTitle}">
							</td>
						</tr>
							<input type="hidden" name="id" value="${form.serial.serialId}">
							<input type="hidden" name="type" value="${constants.serial}">
						</c:when>
						<c:when test="${form.requestType == constants.season}">
								<input type="text" name="seasonTitle" value="${form.season.seasonTitle}">
							</td>
						</tr>
							<input type="hidden" name="id" value="${form.season.seasonId}">
							<input type="hidden" name="serialId" value="${form.serial.serialId}">
							<input type="hidden" name="type" value="${constants.season}">							
						</c:when>
						<c:otherwise>
								<input type="text" name="seriesTitle" value="${form.series.seriesTitle}">
							</td>
						</tr>
						<tr>
							<td>
								Дата выхода
							</td>
							<td>
								<input type="text" name="date" value="${form.series.date}">
							</td>
						</tr>
							<input type="hidden" name="id" value="${form.series.seriesId}">
							<input type="hidden" name="seasonId" value="${form.season.seasonId}">
							<input type="hidden" name="type" value="${constants.series}">							
						</c:otherwise>
					</c:choose>
					</table>
					<table>
					<c:choose>
						<c:when test="${form.action == constants.edit}">
						<tr>
							<td>
								Изменить
							</td>
							<td>
								<input type="submit" name="${constants.edit}" value=">>">
							</td>
						</tr>
						<tr>
							<td>
								Отмена
							</td>
							<td>
								<input type="submit" name="${constants.cancel}" value=">>">
							</td>
						</tr>
						</c:when>
						<c:otherwise>
						<tr>
							<td>
								Создать
							</td>
							<td>
								<input type="submit" name="${constants.new_entry}" value=">>">
							</td>
						</tr>
						<tr>
							<td>
								Отмена
							</td>
							<td>		
								<input type="submit" name="${constants.new_cancel}" value=">>">
							</td>
						</tr>				
						</c:otherwise>
					</c:choose>
					</table>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
	
	<div id="bottom"></div>
	<div id="footer">
			<h3>Powered by Tass @ 2013</h3>
	</div>
</div>
</body>
</html>