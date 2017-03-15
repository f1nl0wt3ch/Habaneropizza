<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<style>
fieldset.orderstatus {
	background: !important;
	border: 1px groove #ddd !important;
	padding: 0 10px 10px 10px;
	border-bottom: none;
}

fieldset.orderstatus {
	width: auto !important;
	border: none;
	font-size: 14px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<title>Order status</title>
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
   <form class="form" action="orderstatus" method="post">
       <fieldset class="orderstatus">
            <legend><h1>Tilaus tilanne</h1></legend>
            <div class="form-group">
                <div class="col-md-4">
                    <label>Tarkistetaan tilanne tilausnumerolla tai sähköpostilla:</label>
                </div>
                <div class="col-md-4">
                    <input type="text" class="form-control" placeholder="Syötä tilausnumero tai sähköposti" name="keyword"> 
                </div>
                <div class="col-md-4">
                    <input type="submit" class="btn btn-default" value="Tarkista">
                </div>
            </div>
        </fieldset>
</form>
</div>
</body>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
<script>
  $(function(){
    $("form[action='orderstatus']").validate({
    	   rules: {
    		   keyword: {
    			   required: true,
        		   minlength: 6
    		   } 
    	   },
    	   messages: {
    		   keyword: {
    			   required: "Kentässä ei saa olla tyhja",
    			   minlength: "Hakusanan pituus on vahintaan 6 merkkejä"
    		   }
    	   },
    	   submitHandler: function(form){
    		   form.submit();
    	   }
    });
  });
</script>
</html>