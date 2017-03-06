<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="orderstatus.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.1.1.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<title>Order status</title>
</head>
<body>
	<div class="container">
		<hr>
		<c:forEach var="i" begin="0" end="${requestScope.j - 1}">
			<table class="container">
				<tr>
					<td><b>Tilausaika</b></td>
					<td><font color="red"><c:out
								value="${sessionScope.tilausaikaStr }" /></font></td>
				</tr>

				<tr>
					<td><b>Tilausnumero</b></td>
					<td><font color="red"><c:out
								value="${sessionScope.kaikkitilaukset[i].tilausNo }" /></font></td>
				</tr>

				<tr>
					<td width="60%"><b>Tilauspäivä</b></td>
					<td><c:out
							value="${sessionScope.kaikkitilaukset[i].tilauspaiva }" /></td>
				</tr>

				<tr>
					<td><b>Määrä</b></td>
					<td><c:out
							value="${sessionScope.kaikkitilaukset[i].maara } kpl"></c:out></td>
				</tr>

				<tr>
					<td><b>Yhteensä</b></td>
					<td><c:out
							value="${sessionScope.kaikkitilaukset[i].maksut } €"></c:out></td>
				</tr>

				<tr>
					<td><b>Maksutapa</b></td>
					<td><c:out
							value="${sessionScope.kaikkitilaukset[i].maksutapa }" /></td>
				</tr>
				<br>
				<br>

			</table>
		</c:forEach>
	</div>
</body>
</html>