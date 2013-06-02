<%@ page language="java" contentType="text/html; charset=windows-1251"
    pageEncoding="windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--    <td>
         <span class="button">
          <input type="submit" value="Добавить сезон" name="addSeason" class="button-in">
         </span>
        </td>
         -->
<form action="${pageContext.request.contextPath}/edit" method="POST">
	<table class="controls">
		<tr>
			<td>
				<span class="button">
					<input type="submit" name="${constants.edit}" value="Редактировать серию" class="button-in">
				</span>
			</td>
			<td>
				<span class="button">
					<input type="submit" name="${constants.delete}" value="Удалить серию" class="button-in">
				</span>
			</td>
		</tr>
	</table>
	<input type="hidden" name="type" value="${constants.series}">
	<input type="hidden" name="id" value="${form.series.seriesId}">
</form>
<br />
<p class="plainText">Серия "${form.series.seriesTitle}" сезона ${form.season.seasonTitle}; сериал ${form.serial.serialTitle}.
	Дата выхода: ${form.series.date}</p>