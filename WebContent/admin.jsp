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

<title>Admin page</title>
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
	  <nav class="nav navbar-success navbar-left">
		<form class="form" action="NaytaTilauksetServlet" method="post">
			<div class="form-group">
				<input type="submit" class="btn btn-default"
					value="Nayta kaikki tilaukset" name="action" />
			</div>
			<div class="form-group">
				<input type="submit" class="btn btn-primary"
					value="Nayta viisi lahitilausta" name="action" />
			</div>
		</form>
		<form class="form" action="NaytaAsiakasServlet" method="get">
			<div class="form-group">
				<input type="submit" class="btn btn-success"
					value="Näytä Asiakaslista">
			</div>
		</form>
		<form class="form" action="NaytaPizzaServlet" method="get">
			<div class="form-group">
				<input type="submit" class="btn btn-warning"
					value="Näytä Pizzalista">
			</div>
		</form>
	</nav>

		<table class="table">
			<tr>
				<th>Tilausnumero</th>
				<th>Määrä</th>
				<th>Yhteensä</th>
				<th>Maksutapa</th>
				<th>Sähköposti</th>
				<th>Tilauspäivä</th>
			</tr>
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




			<table class="table">
				<tr>
					<th>Id</th>
					<th>Nimi</th>
					<th>Puhelin</th>
					<th>Sähköposti</th>
				</tr>
				<c:forEach var="asiakas" items="${asiakasLista}">
					<tr>
						<td>${asiakas.asiakas_id}</td>
						<td><c:out value="${asiakas.nimi}" /></td>
						<td>0<c:out value="${asiakas.puhelinnumero}" /></td>
						<td><c:out value="${asiakas.spostiosoite}" /></td>
					</tr>
				</c:forEach>
			</table>


			<br>
			<form action="PoistaAsiakasServlet" method="get">

				Asiakkaan id: <input type="text" value="" name="poistettuID"
					style="width: 30px"><input type="submit" value="Poista">
			</form>


			<br>
			<table class="table">
				<tr>

					<th>Id</th>
					<th>Nimi</th>
					<th>Hinta</th>
					<th>Täytteet</th>
					<th>Poistomerkitty</th>
				</tr>

				<c:forEach var="pizza" items="${pizzaLista}">
					<tr>
						<td><c:out value="${pizza.pizza_id}" /></td>
						<td><c:out value="${pizza.pizzan_nimi}" /></td>
						<td><c:out value="${pizza.hinta}" /></td>
						<td><c:out value="${pizza.taytteet}" /></td>
						<td><c:out value="${pizza.poistomerkitty}" /></td>

					</tr>
				</c:forEach>
			</table>


			<form action="PoistaPizzaServlet" method="get">
				Pizzan id: <input type="text" value="" name="pizzaId"
					style="width: 30px"><input type="submit" value="Poista">
			</form>
			<br>
			<br>
			<form action="PaivitaPizzaTietoServlet" method="post">
				<FIELDSET style="width: 668px">
					<LEGEND>
						<h3>Päivitä pizzan tiedot</h3>
					</LEGEND>

					<table>
						<tr>
							<td>Kumppi tieto haluat päivittää, valitse ala numero? <br>
							<br></td>
						</tr>
						<tr>
							<td>1.Nimi 2.Täytteet 3.Hinta 4.Poistomerkitty</td>
							<td><input type="text" value="" name="numero"
								style="width: 50px"></td>
						</tr>

						<tr>
							<td>Pizzan id:</td>
							<td><input type="text" value="" name="pizzaId"
								style="width: 50px"></td>
						</tr>
						<tr>
							<td>Tiedot:</td>
							<td><input type="text" value="" name="input"
								style="width: 300px"></td>
						</tr>

						<tr>
							<td></td>
							<td><input type="submit" value="Päivitä"></td>
						</tr>

					</table>
				</FIELDSET>
			</form>
			<br>
			<br>
			<form action="LisaaPizzaServlet" method="post">
				<fieldset style="width: 668px">
					<LEGEND>
						<h3>Lisää uusi pizza listaan</h3>
					</LEGEND>
					<table border="0" cellpadding="2" cellspacing="2">
						<tr>
							<td>Pizza nimi:</td>
							<td><input type="text" value="" name="name"
								style="width: 100px"></td>
						</tr>

						<tr>
							<td>Hinta:</td>
							<td><input type="text" value="" name="price"
								style="width: 30px"></td>
						</tr>

						<tr>
							<td>Täytteet:</td>
							<td><input type="text" value="" name="details"
								style="width: 300px"></td>
						</tr>

						<tr>
							<td>Poistomerkitty (yyyy-MM-dd):</td>
							<td><input type="text" value="" name="date"
								style="width: 200px"></td>
						</tr>

						<tr>
							<td></td>
							<td><input type="submit" value="Lisää uusi pizza"
								style="align: center" style="height:50px; width:120px"></td>
						</tr>
					</table>
				</fieldset>
			</form>
			</div>
</body>
<script src="https://code.jquery.com/jquery-3.1.1.js"></script>
</html>