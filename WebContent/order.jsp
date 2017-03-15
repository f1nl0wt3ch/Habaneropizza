
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
		<a class="btn btn-primary" href="/index">Home</a>
</div>
<div class="container">
		<form action="thankyou" method="get">
				<input type="button" value="Tilaus Lomake" class="btn btn-default" />
                <div class="row">
                    <div class="col-md-4">
                    </div>
                    <div class="col-md-4">
                           <div class="row">
                               <label></label>
                               <label></label>
                               <label></label>
                               <label></label>
                               <label>Pizzat</label>
                           </div>
                       <c:forEach var="i" begin="0" end="${requestScope.jStr - 1 }">
                           <div class="row">
                               <label></label>
                               <label></label>
                               <label></label>
                               <label></label>
                               <c:out value="${requestScope.listStr[i]  } " />
                           </div>
                       </c:forEach>
                       <hr>
                           <div class="row">
                                  <label></label>
                                  <label></label>
                                  <label></label>
                               <label></label>
                                  <label>Yhteensä </label>
                           </div>
                    </div>
                    <div class="col-md-4">
                           <div class="row">
                              <label>Määrä</label>
                              <label></label>
                              <label></label>
                              <img src="images/cart-icon.gif">
                           </div>
                        <c:forEach var="i" begin="0" end="${requestScope.jInt - 1 }">
                           <div class="row">
                               <c:out value="${requestScope.listInt[i]  } " />
                           </div>
                       </c:forEach>
                         <hr>
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
                        <input type="text" class="form-control" name="phone" />
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
<script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
<script type="text/javascript">
  $(function(){
	 $("form[action='thankyou']").validate({
		rules: {
			name:{
				required: true,
				minlength: 2
			},
			phone:{
				required: true,
				minlength: 10,
				maxlength: 10
			},
			email: {
				required: true,
				email: true
			}
		},
		messages: {
			name:{
				required: "Nimi ei saa olla tyhjänä",
				minlength: "Nimen pituus on vahintaan 2 merkkiä"
			},
			phone:{
				required: "Syötä sinun puhelinnumerosi",
				minlength: "Puhelimen pituus on 10 numeroa",
				maxlength: "Puhelimen pituus on 10 numeroa"
			},
			email: {
				required: "Syötä sinun sähköpostisi",
				email: "Syötedyt tiedot ei ole sähköpostin muotoinen"
			}
		},
		submitHandler: function(form){
			form.submit();
		}
	 });
  });
</script>
</html>