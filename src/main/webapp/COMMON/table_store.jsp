<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="scroll"><table class="store_table">
	<tbody>
		<c:forEach var="i" begin="0" end="10" step="1">
			<tr class="top">
				<td class="image" rowspan="3"  valign="middle"><img src="CSS/image/no_image1.png"></td>
				<td class="cities" colspan="3" align="left" valign="bottom">市町村</td>
			</tr><tr>
				<td class="name" colspan="2" align="left"><a href="details">${fn:escapeXml(selectResult)}</a></td>
				<td>☆☆☆</td>
			</tr><tr class="bottom">
				<td valign="top">カテゴリ1</td>
				<td valign="top">カテゴリ2</td>
				<td valign="top">カテゴリ3</td>
			</tr>
		</c:forEach>
	</tbody>
</table></div>