<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="Luokat.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<title>Admin page</title>
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
    <div class="row">
        <form class="form" action="orders" method="post">
			<div class="form-group">
			    <div class="col-md-3">
			        <input type="submit" class="form-control btn btn-default" value="Nayta kaikki tilaukset" name="action" />
			    </div>
			</div>
			<div class="form-group">
			    <div class="col-md-3">
			        <input type="submit" class="form-control btn btn-default" value="Nayta viisi lahintilausta" name="action" />
			    </div>
			</div>
		</form>
		<form class="form" action="customers" method="get">
		    <div class="form-group">
			    <div class="col-md-3">
			        <input type="submit" class="form-control btn btn-default" value="Näytä Asiakaslista">
			    </div>
			</div>
		</form>
		<form class="form" action="pizzas" method="get">
		    <div class="form-group">
			    <div class="col-md-3">
			        <input type="submit" class="form-control btn btn-default" value="Näytä Pizzalista">
			    </div>
			</div>
		</form>
    </div>
</div><!-- container navbar -->

<div class="container">
       <div class="row tilaus">
           <div class="col-md-12">
             <table class="table">
               <thead>
                  <tr>
				    <th>Tilausnumero</th>
				    <th>Määrä</th>
				    <th>Yhteensä</th>
				    <th>Maksutapa</th>
				    <th>Sähköposti</th>
				    <th>Tilauspäivä</th>
			      </tr>
               </thead>
					<tbody>
						<c:forEach var="tilaus" items="${tilauslista }">
							<tr>
								<td><c:out value="${tilaus.tilausNo}" /></td>
								<td><c:out value="${tilaus.maara}" /></td>
								<td><c:out value="${tilaus.maksut} " />€</td>
								<td><c:out value="${tilaus.maksutapa}" /></td>
								<td><c:out value="${tilaus.spostiosoite}" /></td>
								<td><c:out value="${tilaus.tilauspaiva}" /></td>
							</tr>
						</c:forEach>
					</tbody>
		      </table>
		  </div><!-- col-md-12 -->
	 </div><!-- row -->
</div> <!-- container tilaus -->  

<div class="container">
       <div class="row asiakas">
          <table class="table">
              <thead>
                 <tr>
					<th>Id</th>
					<th>Nimi</th>
					<th>Puhelin</th>
					<th>Sähköposti</th>
				 </tr>
              </thead>
			  <tbody>
			     <c:forEach var="asiakas" items="${asiakasLista}">
					<tr>
						<td>${asiakas.asiakas_id}</td>
						<td><c:out value="${asiakas.nimi}" /></td>
						<td>0<c:out value="${asiakas.puhelinnumero}" /></td>
						<td><c:out value="${asiakas.spostiosoite}" /></td>
					</tr>
				</c:forEach>
			  </tbody>
			</table>
       </div><!-- näytä asiakkaat -->
       <div class="row asiakas">
            <div class="col-md-6">
                 <form action="deletecustomer" method="get">
                      <div class="form-inline">
                           <label>Asiakkaan id</label>
                           <input type="text" class="form-control" name="poistettuID" />
                           <input type="submit" class="btn btn-warning" value="Poista">
                      </div>
			    </form>
            </div>
            <div class="col-md-6"></div>
       </div><!-- poista asiakkaan -->
</div><!-- container asiakas-->

<div class="container">  
    <div class="row pizza">
        <table class="table">
				<thead>
					<tr>
						<th>Id</th>
						<th>Nimi</th>
						<th>Hinta</th>
						<th>Täytteet</th>
						<th>Poistomerkitty</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="pizza" items="${pizzaLista}">
						<tr>
							<td><c:out value="${pizza.pizza_id}" /></td>
							<td><c:out value="${pizza.pizzan_nimi}" /></td>
							<td><c:out value="${pizza.hinta}" /></td>
							<td><c:out value="${pizza.taytteet}" /></td>
							<td><c:out value="${pizza.poistomerkitty}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
    </div><!-- row 1 -->
    <div class="row pizza">
         <div class="col-md-6">
              	<form class="form" action="deletepizza" method="get">
						<div class="form-inline">
								<label class="text-right">Pizzan id</label>
								<input type="text" class="form-control" name="pizzaId" />
								<input type="submit" class="btn btn-warning text-left" value="Poista">
					    </div>
			     </form>
         </div>
         <div class="col-md-6"></div>
    </div><!-- row 2 -->
	<div class="row pizza">
	    <div class="col-md-6">
	         <form action="updatepizza" method="post">
						<legend>*Päivitä pizzan tiedot</legend>
						<div class="form-group">
							<label>Kumppi tieto haluat päivittää, valitse ala numero?</label>
							<label>1.Nimi 2.Täytteet 3.Hinta 4.Poistomerkitty</label> <input
								type="text" class="form-control" name="numero" />
						</div>
						<div class="form-group">
							<label>Pizzan id</label> <input type="text" class="form-control"
								name="pizzaId" />
						</div>
						<div class="form-group">
							<label>Tiedot</label> <input type="text" class="form-control"
								name="input" />
						</div>
						<div class="form-group">
							<label></label> <input type="submit" class="btn btn-primary"
								value="Päivitä">
						</div>
			</form>
	    </div><!-- päivittä pizza -->
	    <div class="col-md-6">
	         			<form class="form" action="newpizza" method="post">
						<legend>*Lisää uusi pizza listaan</legend>
						<div class="form-group">
							<label>Pizza nimi</label> <input type="text" class="form-control"
								name="name">
						</div>
						<div class="form-group">
							<label>Hinta</label> <input type="text" class="form-control"
								name="price">
						</div>
						<div class="form-group">
							<label>Täytteet</label> <input type="text" class="form-control" name="details">
						</div>
						<div class="form-group">
							<label>Poistomerkitty (yyyy-MM-dd)</label> <input type="text" id="datepicker" class="form-control" name="date">
						</div>
						<div class="form-group">
							<label></label> <input type="submit" class="btn btn-primary"
								value="Lisää uusi pizza">
						</div>
			 </form>
	    </div><!-- Lisää uusi pizza  -->	
	   </div><!-- row 3 -->
	</div><!-- container pizza -->

</body>
<script src="https://code.jquery.com/jquery-3.1.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
   $(function(event){
	   event.preventDefault();
	   $( "#datepicker" ).datepicker({
		 "dateFormat": "yy-mm-dd" 
	   });
	   
	   $(':input[value="Nayta kaikki tilaukset"]').on("click", function(){
		   $(".asiakas").css("display","none");
		   $(".pizza").css("display","none");
		   $(".tilaus").toggle();
	   });
	   $(':input[value="Nayta viisi lahintilausta"]').on("click", function(){
		   $(".asiakas").css("display","none");
		   $(".pizza").css("display","none");
		   $(".tilaus").toggle();
	   });
	   $(':input[value="Näytä Asiakaslista"]').on("click", function(){
		   $(".pizza").css("display","none");
		   $(".tilaus").css("display","none");
		   $(".asiakas").toggle();
	   });
	   $(':input[value="Näytä Pizzalista"]').on("click", function(){
		   $(".asiakas").css("display","none");
		   $(".tilaus").css("display","none");
		   $(".pizza").toggle();
	   });
	   
   })
</script>
</html>