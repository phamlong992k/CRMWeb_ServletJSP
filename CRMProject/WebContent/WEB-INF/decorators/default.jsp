<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
         <%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="dec" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16" href="<c:url value="/plugins/images/favicon.png"/>">
    <title><dec:title /></title>
    <!-- Bootstrap Core CSS -->
    <link href="<c:url value="/static/bootstrap/dist/css/bootstrap.min.css"/>" rel="stylesheet">
    <!-- Menu CSS -->
    <link href="<c:url value="/static/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css"/>" rel="stylesheet">
    <!-- toast CSS -->
    <link href="<c:url value="/static/plugins/bower_components/toast-master/css/jquery.toast.css"/>" rel="stylesheet">
    <!-- morris CSS -->
    <link href="<c:url value="/static/plugins/bower_components/morrisjs/morris.css"/>" rel="stylesheet">
    <!-- animation CSS -->
    <link href="<c:url value="/static/css/animate.css"/>" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="<c:url value="/static/css/style.css"/>" rel="stylesheet">
    <!-- color CSS -->
    <link href="<c:url value="/static/css/colors/blue-dark.css"/>" id="theme" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/static/css/custom.css"/>">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body>
	
	<div>
	<div>
   		<!-- header -->
   		<jsp:include page="/WEB-INF/layout/header.jsp"></jsp:include>
   		<!--  -->
        <!-- Page Content -->
        <dec:body></dec:body>
        <!-- /.container-fluid --><!--  -->
        <footer class="footer text-center"> 2018 &copy; myclass.com </footer>
    </div>
    <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->
    <!-- jQuery -->
    <script src="<c:url value="/static/plugins/bower_components/jquery/dist/jquery.min.js"/>"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value="/static/bootstrap/dist/js/bootstrap.min.js"/>"></script>
    <!-- Menu Plugin JavaScript -->
    <script src="<c:url value="/static/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"/>"></script>
    <!--slimscroll JavaScript -->
    <script src="<c:url value="/static/js/jquery.slimscroll.js"/>"></script>
    <!--Wave Effects -->
    <script src="<c:url value="/static/static/js/waves.js"/>"></script>
    <!--Counter js -->
    <script src="<c:url value="/static/plugins/bower_components/waypoints/lib/jquery.waypoints.js"/>"></script>
    <script src="<c:url value="/static/plugins/bower_components/counterup/jquery.counterup.min.js"/>"></script>
    <!--Morris JavaScript -->
    <script src="<c:url value="/static/plugins/bower_components/raphael/raphael-min.js"/>"></script>
    <script src="<c:url value="/static/plugins/bower_components/morrisjs/morris.js"/>"></script>
    <!-- Custom Theme JavaScript -->
    <script src="<c:url value="/static/js/custom.min.js"/>"></script>
    <script src="<c:url value="/static/js/dashboard1.js"/>"></script>
    <script src="<c:url value="/static/plugins/bower_components/toast-master/js/jquery.toast.js"/>"></script>
</body>

</html>