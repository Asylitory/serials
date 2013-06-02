<%@ page language="java" contentType="text/html; charset=windows-1251"
    pageEncoding="windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="controls">
<form action="${pageContext.request.contextPath}/edit" method="POST">
	<tr>
		<td>
			<span class="button">
				<input type="submit" name="${constants.delete}" value="Удалить сезон" class="button-in">
			</span>
		</td>
		<td>
			<span class="button">
				<input type="submit" name="${constants.edit}" value="Редактировать сезон" class="button-in">
			</span>
		</td>
	<input type="hidden" name="type" value="${constants.season}">
	<input type="hidden" name="id" value="${form.season.seasonId}">
</form>
<form action="${pageContext.request.contextPath}/edit" method="POST">
		<td>
			<span class="button">
				<input type="submit" name="${constants.new_entry}" value="Добавить серию" class="button-in">
			</span>
		</td>
	</tr>
	<input type="hidden" name="type" value="${constants.series}">
	<input type="hidden" name="id" value="${form.season.seasonId}">
</form>
</table>
<br />
<table class="dataTable tableMinWidthTd">
	<c:forEach var="series" items="${form.seriesList}">
		<tr>
			<td>
				<a href="${pageContext.request.contextPath}/main?seriesId=${series.seriesId}" class="link">
					${series.seriesTitle}
				</a>
			</td>
			<td>
				${series.date}
			</td>
		</tr>
	</c:forEach>
</table>