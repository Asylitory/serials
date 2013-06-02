<%@ page language="java" contentType="text/html; charset=windows-1251"
    pageEncoding="windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/jsp/css/style.css">
<title>${form.title}</title>
</head>
<body>
<div id="wrap">
	<div id="header"></div>
	<div id="formTop"></div>
	
	<div id="formCenter">
		<c:choose>
			<c:when test="${form.action == constants.delete}">
				<form action="${pageContext.request.contextPath}/complete" method="POST">
					<c:choose>
						<c:when test="${form.requestType == constants.serial}">
							<p class="plainText">Удалить сериал ${form.serial.serialTitle}?</p>
							<input type="hidden" name="id" value="${form.serial.serialId}">
							<input type="hidden" name="type" value="${constants.serial}">
						</c:when>
						<c:when test="${form.requestType == constants.season}">
							<p class="plainText">Удалить сезон ${form.season.seasonTitle} из сериала ${form.serial.serialTitle}?</p>
							<input type="hidden" name="id" value="${form.season.seasonId}">
							<input type="hidden" name="type" value="${constants.season}">
						</c:when>
						<c:otherwise>
							<p class="plainText">Удалить серию ${form.series.seriesTitle} из сериала ${form.serial.serialTitle}, сезон ${form.season.seasonTitle}?</p>
							<input type="hidden" name="id" value="${form.series.seriesId}">
							<input type="hidden" name="type" value="${constants.series}">
						</c:otherwise>
					</c:choose>
					<table class="controls">
						<tr>
							<td>
								<span class="button">
									<input type="submit" name="${constants.delete}" value="Удалить" class="button-in">
								</span>
							</td>
							<td>
								<span class="button">
									<input type="submit" name="${constants.cancel}" value="Отмена" class="button-in">
								</span>
							</td>
						</tr>
					</table>
				</form>
			</c:when>
			<c:otherwise>
				<form action="${pageContext.request.contextPath}/complete" method="POST">
					<table class="controls tableMinWidthTd">
						<tr>
							<td>
								Название
							</td>
							<td>
					<c:choose>
						<c:when test="${form.requestType == constants.serial}">
								<span class="editText">
									<input type="text" name="serialTitle" value="${form.serial.serialTitle}" class="editText-in">
								</span>
							</td>
						</tr>
							<input type="hidden" name="id" value="${form.serial.serialId}">
							<input type="hidden" name="type" value="${constants.serial}">
						</c:when>
						<c:when test="${form.requestType == constants.season}">
								<span class="editText">
									<input type="text" name="seasonTitle" value="${form.season.seasonTitle}" class="editText-in">
								</span>
							</td>
						</tr>
							<input type="hidden" name="id" value="${form.season.seasonId}">
							<input type="hidden" name="serialId" value="${form.serial.serialId}">
							<input type="hidden" name="type" value="${constants.season}">							
						</c:when>
						<c:otherwise>
								<span class="editText">
									<input type="text" name="seriesTitle" value="${form.series.seriesTitle}" class="editText-in">
								</span>
							</td>
						</tr>
						<tr>
							<td>
								Дата выхода
							</td>
							<td>
								<span class="editText">
									<input type="text" name="date" value="${form.series.date}" class="editText-in">
								</span>
							</td>
						</tr>
							<input type="hidden" name="id" value="${form.series.seriesId}">
							<input type="hidden" name="seasonId" value="${form.season.seasonId}">
							<input type="hidden" name="type" value="${constants.series}">							
						</c:otherwise>
					</c:choose>
					</table>
					<table class="controls">
					<c:choose>
						<c:when test="${form.action == constants.edit}">
						<tr>
							<td>
								<span class="button">
									<input type="submit" name="${constants.edit}" value="Изменить" class="button-in">
								</span>
							</td>
							<td>
								<span class="button">
									<input type="submit" name="${constants.cancel}" value="Отмена" class="button-in">
								</span>
							</td>
						</tr>
						</c:when>
						<c:otherwise>
						<tr>
							<td>
								<span class="button">
									<input type="submit" name="${constants.new_entry}" value="Создать" class="button-in">
								</span>
							</td>
							<td>		
								<span class="button">
									<input type="submit" name="${constants.new_cancel}" value="Отмена" class="button-in">
								</span>
							</td>
						</tr>				
						</c:otherwise>
					</c:choose>
					</table>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
	
	<div id="formBottom"></div>
	<div id="footer">
			<h3 class="footerText">Powered by Tass @ 2013</h3>
	</div>
</div>
</body>
</html>