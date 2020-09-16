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
      <h3 class="text-center">ĐĂNG NHẬP HỆ THỐNG</h3>
      <div class="p-4 border mt-4">
        <form action='<c:url value="/login"/>' method ="POST">
        	<c:if test="${messageErr!=null}">
        		<p class="text-danger">${messageErr}</p>
        		<!-- jstl de bat su kien tu server gui len -->
        		<!-- react js de bat su kien tu server gui len -->
        	</c:if>
            <div class="form-group">
              <label>Email</label>
              <input type="email" class="form-control" id ="txtEmail" name="txtEmail">
            </div>
            
            <div class="form-group">
              <label>Mật khẩu</label>
              <input type="password" class="form-control" name="txtPassword">
            </div>
             <a href ="#" onclick="this.href='<%=request.getContextPath()%>/login/forget?email='+document.getElementById('txtEmail').value">Forget Password</a>
            <button type="submit" name ="traditionLogin"class="btn btn-primary">Đăng nhập</button>
            
          	<button type="submit" name="mailLogin" class="btn btn-danger sm4" formaction="<%=request.getContextPath()%>/mail">Login With Gmail</button>
          	
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
