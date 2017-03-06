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
		<table class="table">
			<tr>
				<td><b>Tilausaika</b></td>
				<td><font color="red">${sessionScope.tilausaikaStr }</font></td>
			</tr>

			<tr>
				<td width="60%"><b>Tilauspäivä</b></td>
				<td><c:out value="${historiatilaus.tilauspaiva }" /></td>
			</tr>

			<tr>
				<td><b>Tilausnumero</b></td>
				<td><font color="red"><c:out
							value="${historiatilaus.tilausNo }" /></font></td>
			</tr>

			<tr>
				<td><b>Määrä</b></td>
				<td><c:out value="${historiatilaus.maara } kpl"></c:out></td>
			</tr>

			<tr>
				<td><b>Yhteensä</b></td>
				<td><c:out value="${historiatilaus.maksut } €"></c:out></td>
			</tr>

			<tr>
				<td><b>Maksutapa</b></td>
				<td><c:out value="${historiatilaus.maksutapa }" /></td>
			</tr>

			<tr>
				<td><b>Sähköposti</b></td>
				<td><font color="blue"><c:out
							value="${historiatilaus.spostiosoite }" /></font></td>
			</tr>

		</table>
	</div>
</body>
</html>