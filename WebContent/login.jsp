<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.1.1.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<title>Login page</title>
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
   <div class="row">
       <div class="col-md-12 text-center">
            <h3>Kirjaudu sisään</h3>
            <p>Käytä nämä olevat tiedot kirjautumiselle sisään </p>
            <p>Tunnus on  <b>admin</b></p>
            <p>Salasana on <b>admin123</b> </p>
       </div>
       <div class="col-md-12 text-center" style="background:">
            <form class="form" action="KirjauduSisaanServlet" method="post">
                <div class="form-group col-md-12">
                    <div class="col-md-4">
                        <label>Käyttäjätunnus</label>
                    </div>
                    <div class="col-md-8">
                        <input type="text" class="form-control" value="" name="username">
                    </div>
                </div>
                <div class="form-group col-md-12">
                    <div class="col-md-4">
                        <label>Salasana</label>
                    </div>
                     <div class="col-md-8">
                        <input type="password" class="form-control" value="" name="password">
                    </div>
                </div>
                <div class="form-group col-md-12">
                    <div class="col-md-4">
                        <label></label>
                    </div>
                    <div class="col-md-8">
                          <input type="submit" class="btn btn-primary" value="Kirjaudu">
                    </div>
                </div>
            </form>
       </div>
   </div>
</div>
</body>
</html>