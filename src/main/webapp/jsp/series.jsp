<%@ page language="java" contentType="text/html; charset=windows-1251"
    pageEncoding="windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="${pageContext.request.contextPath}/edit" method="POST">
	<table>
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
	</table>
	<input type="hidden" name="type" value="${constants.series}">
	<input type="hidden" name="id" value="${form.series.seriesId}">
</form>
<br />
<p>Серия "${form.series.seriesTitle}" сезона ${form.season.seasonTitle}; сериал ${form.serial.serialTitle}.
	Дата выхода: ${form.series.date} </p>