<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Virhellinen</title>
</head>
<body>
<h3><% out.print("Nimi ei ole olemassa, tarkistaa uudelleen!"); %></h3>
<form action = "admin.jsp">
<input type="submit" value="Takaisin">
</form>
</body>
</html>