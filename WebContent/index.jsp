<?xml version="1.0" encoding="UTF-8" ?>
<%@ page import="DBHoitaja.*"%>
<%@ page import="Luokat.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="Luokat.*"%>
<%@ page import="java.util.*"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<title>Habanero | Kotisivu</title>
<style>
   h1, h2, h3, h4 {
      color: width;
   }
</style>
</head>
<body>
	<div class="jumbotron text-center text-success">
		<h1>Habanero Pizzarien</h1>
		<h2>Pizzoja saatavilla klo 10-23</h2>
		<h3>Malmin parhaat pizzat</h3>
		<h4>Soita 050-654-3210 ja nouda pizza paikan päältä</h4>
		
	</div>
<div class="container">
	<nav class="navbar navbar-success navbar-right">
		<ul class="nav nav-tabs">
			<li role="presentation" class="active"><a href="/">Kotisivu</a></li>
			<li role="presentation"><a href="orderstatus.jsp">Tilaus tilanne</a></li>
			<li role="presentation"><a href="login.jsp">Ylläpito</a></li>
			<li role="presentation"><a href="contact.jsp">Palaute</a></li>
		</ul>
	</nav>
</div>	

<form class="form" action="AikaPalveluServlet" method="get">
	<div class="container">
	    <div class="row">
		    <div class="form-group col-md-12">
		       <table class="table bordered">
		           <thead>
		              <tr>
		                 <th>Pizzan nimi</th>
		                 <th>Taytteet</th>
		                 <th>Hinta</th>
		                 <th>Määrä</th>
		              </tr>
		           </thead>
		           <tbody>
		               <c:forEach var="pizza" items="${pizzaLista}">
		               <tr>
		                  <td>${pizza.pizzan_nimi}</td>
		                  <td>${pizza.taytteet}</td>
		                  <td>${pizza.hinta }€ </td>
		                  <td><input type="number" class="form-control" min="0" max="10" step="1" value="0" name=${pizza.pizzan_nimi}></td>
		               </tr>
		               </c:forEach>
		           </tbody>
		       </table>
		   </div>    
	       <div class="form-group col-md-12">
	            <div class="col-md-4"></div>
	            <div class="col-md-4 text-center">
	                 <input type="reset" class="btn btn-warning" value="Perutta" name="reset">
				     <input type="submit" class="btn btn-success" value="Tilaa" name="submit">
		        </div>
		        <div class="col-md-4"></div>
		   </div>
	   </div><!-- row -->		
   </div><!-- Container -->
</form>
</body>
<script src="https://code.jquery.com/jquery-3.1.1.js"></script>
<script>
  $( document ).ready(function(){
	  $(':input[type="submit"]').prop('disabled', true);
	  $(':input[type="number"]').on("click", function(){
		 if($(this).val() != 0)
		   $(':input[type="submit"]').prop("disabled",false);
		 else
		   $(':input[type="submit"]').prop('disabled', true);
	 });
  })
</script>
</html>