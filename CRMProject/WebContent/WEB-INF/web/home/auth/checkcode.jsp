<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
 
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	
</head>
<body>

<div class="container">
  <div class="row mt-5">
    <div class="col-md-5 m-auto mt-5">
      <h3 class="text-center">Check Code</h3>
      <div class="p-4 border mt-4">
        <form action='<c:url value="/login/checkcode"/>' method ="GET">
        	<c:if test="${messageErr!=null}">
        		<p class="text-danger">${messageErr}</p>
        		<!-- jstl de bat su kien tu server gui len -->
        		<!-- react js de bat su kien tu server gui len -->
        	</c:if>
            <div class="form-group">
              <label>Code</label>
              <input type="text" class="form-control" id ="txtCode" name="txtCode">
            </div>
            
            <button type="submit" class="btn btn-primary">Check</button>
          
          	
          </form>
          
      </div>
      </div>
  </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
