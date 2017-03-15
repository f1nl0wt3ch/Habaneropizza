<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<title>Contact us</title>
</head>
<body>
<div class="jumbotron text-center">
		<h1>Habanero Pizzarien</h1>
		<h2>Pizzoja saatavilla klo 10-23</h2>
		<h3>Malmin parhaat pizzat</h3>
		<h4>Soita 050-654-3210 ja nouda pizza paikan päältä</h4>
		<a class="btn btn-primary" href="/index">Home</a>
</div>
 <div class="container">
<form class="form" action="LahettaSpostiServlet" method="post">
       <div class="row">	
	   <div class="form-group col-md-12">
	       <div class="col-md-3">
	           <label>Lähettäjä</label>
	       </div>
	       <div class="col-md-9">
	           <input type="text" class="form-control" name="sender" placeholder="Syötä lähettäjän sposti"/>
	       </div>
	   </div>
	   <div class="form-group col-md-12">
	       <div class="col-md-3">
	           <label>Aihe</label>
	       </div>
	       <div class="col-md-9">
	           <input type="text" class="form-control" name="subject" placeholder="Syötä palautteen aihe">
	       </div>
	   </div>
	   
	   <div class="form-group col-md-12">
	       <div class="col-md-3">
	           <label>Viesti</label>
	       </div>
	       <div class="col-md-9">
	           <textarea name="message" class="form-control" cols="75" rows="10"></textarea>
	       </div>
	   </div>
	   
	    <div class="form-group col-md-12">
	       <div class="col-md-3">
	           <label></label>
	       </div>
	       <div class="col-md-9">
	           <input type="submit" class="btn btn-primary" value="Lähetä">
	       </div>
	   </div>
	   </div>
	 </div>
</form>
</body>
</html>