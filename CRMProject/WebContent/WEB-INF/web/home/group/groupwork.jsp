<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Danh sách công việc</h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
                        <a href="<%=request.getContextPath()%>/job/add" class="btn btn-sm btn-success">Thêm mới</a>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /row -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="white-box">
                            <div class="table-responsive">
                                <table class="table" id="example">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Tên Công Việc</th>
                                            <th>Ngày Bắt Đầu</th>
                                            <th>Ngày Kết Thúc</th>
                                            <th>Hành Động</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    
                                    	<c:forEach items="${jobList}" var="job">
                                    		<tr>
												<td>${job.id}</td>
												<td>${job.name}</td>
												<td>${job.startDate }</td>
												<td>${job.endDate }</td>
												<td>
													<a href="<%=request.getContextPath()%>/job/update?id=${job.id}" class="btn btn-sm btn-primary">Sửa</a>
													<a href="<%=request.getContextPath()%>/job/remove?id=${job.id}" class="btn btn-sm btn-danger">Xóa</a>
													<a href="<%=request.getContextPath()%>/job/view?id=${job.id}"class="btn btn-sm btn-info">Xem</a>
												</td>
											</tr>
                                    	
                                    	
                                    	</c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
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
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <!--Wave Effects -->
    <script src="<c:url value="/static/js/waves.js"/>"></script>
    <!-- Custom Theme JavaScript -->
    <script src="<c:url value="/static/js/custom.min.js"/>"></script>
    <script>
        $(document).ready(function () {
            $('#example').DataTable();
        });
    </script>
</body>

</html>