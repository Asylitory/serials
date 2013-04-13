<%@ page language="java" contentType="text/html; charset=windows-1251"
    pageEncoding="windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table>
<form action="${pageContext.request.contextPath}/edit" method="POST">
	<tr>
		<td>
			Редактировать
		</td>
		<td>
			<input type="submit" name="${constants.edit}" value=">>">
		</td>
	</tr>
	<tr>
		<td>
			Удалить
		</td>
		<td>
			<input type="submit" name="${constants.delete}" value=">>">
		</td>
	</tr>
	<input type="hidden" name="type" value="${constants.season}">
	<input type="hidden" name="id" value="${form.season.seasonId}">
</form>
<form action="${pageContext.request.contextPath}/edit" method="POST">
	<tr>
		<td>
			Добавить серию
		</td>
		<td>
			<input type="submit" name="${constants.new_entry}" value=">>">
		</td>
	</tr>
	<input type="hidden" name="type" value="${constants.series}">
	<input type="hidden" name="id" value="${form.season.seasonId}">
</form>
</table>
<br />
<table id="data">
	<c:forEach var="series" items="${form.seriesList}">
		<tr>
			<td>
				<a href="${pageContext.request.contextPath}/main?seriesId=${series.seriesId}">
					${series.seriesTitle}
				</a>
			</td>
			<td>
				${series.date}
			</td>
		</tr>
	</c:forEach>
</table>