<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<c:forEach items="${list}" var="memo">
	<tr>
		<td>${memo.num}</td>
		<td>${memo.contents}</td>
		<td>${memo.writer}</td>
		<td>${memo.regDate}</td>
	</tr>

</c:forEach>

