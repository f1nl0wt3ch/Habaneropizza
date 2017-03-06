
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<title>Tilaus Lomake</title>
</head>
<body>
<div class="jumbotron text-center">
		<h1>Habanero Pizzarien</h1>
		<h2>Pizzoja saatavilla klo 10-23</h2>
		<h3>Malmin parhaat pizzat</h3>
		<h4>Soita 050-654-3210 ja nouda pizza paikan päältä</h4>
		<a class="btn btn-primary" href="KotiSivuServlet">Home</a>
</div>
<div class="container">
		<form action="SaadaTilausServlet" method="get">
				<input type="button" value="Tilaus Lomake" class="btn btn-default" />
                <div class="row">
                    <div class="col-md-4">
                        <img src="images/cart-icon.gif">
                    </div>
                    <div class="col-md-4">
                           <div class="row">
                               <label>Pizzat</label>
                           </div>
                       <c:forEach var="i" begin="0" end="${requestScope.jStr - 1 }">
                           <div class="row">
                               <c:out value="${requestScope.listStr[i]  } " />
                           </div>
                       </c:forEach>
                           <div class="row">
                                  <label>Yhteensä </label>
                           </div>
                    </div>
                    <div class="col-md-4">
                           <div class="row">
                              <label>Määrä</label>
                           </div>
                        <c:forEach var="i" begin="0" end="${requestScope.jInt - 1 }">
                           <div class="row">
                               <c:out value="${requestScope.listInt[i]  } " />
                           </div>
                       </c:forEach>
                           <div class="row">
                                  <c:out value="${sessionScope.sum } kpl" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                  <c:out value="${sessionScope.yhteensa } €"/>
                           </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-4">
                        <label>Nimi (*)</label>
                    </div>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="name" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-4">
                        <label>Puhelin (*)</label>
                    </div>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="phone" maxlength="10" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-4">
                        <label>Sähköposti (*)</label>
                    </div>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="email" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-4">
                        <label>Maksutapa</label>
                    </div>
                    <div class="col-md-8">
                        <select class="form-control" name="Maksutapa">
								<option value="käteisraha">käteisraha</option>
								<option value="luotokortti">luotokortti</option>
								<option value="pankkikortti">pankkikortti</option>
						</select>
                    </div>
                </div>
                 <div class="form-group text-center">
                    <div class="col-md-4">
                    </div>
                    <div class="col-md-8">
                        <input type="submit" class="btn btn-success" value="Lähettä" />
						<input type="submit" class="btn btn-warning" action="KotiSivuServlet" value="Takasin" />
                    </div>
                </div>
		</form>
</div>
</body>
</html>