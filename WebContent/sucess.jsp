<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Thank You</title>
</head>
<body>


<table border="0">
<tr>
<td width="50%">
<h3>Kiitos <font color="blue"><% out.print(request.getParameter("name")); %></font> ! Sinun tilausnumerosi on <font color="red"> <c:out value="${sessionScope.tilausnumero }" /> </font><br>
Vahvistus viesti tulee sinun sähköposti osoitteeseen kohta.</h3>
</td>
</tr>

<tr>
<td>
<form action="KotiSivuServlet">
<input type="submit" value="Takaisin kotisivusto">
</form> 
</td>

</tr>
</table>
</body>
</html>