<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Update công việc</h4>
                    </div>
                </div>
                <!-- /.row -->
                <!-- .row -->
                <div class="row">
                    <div class="col-md-2 col-12"></div>
                    <div class="col-md-8 col-xs-12">
                        <div class="white-box">
                            <form action="<%=request.getContextPath()%>/job/update" method="POST" class="form-horizontal form-material">
                                <div class="form-group">
                                    <label class="col-md-12">Tên công việc</label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="${job.name}"
                                           name="txtName" class="form-control form-control-line"> </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Ngày bắt đầu</label>
                                    <div class="col-md-12">
                                        <input type="date"  value="${job.startDate}"
                                           name="txtStartDate" class="form-control form-control-line"> </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Ngày kết thúc</label>
                                    <div class="col-md-12">
                                        <input type="date" value="${job.endDate}"
                                           name="txtEndDate" class="form-control form-control-line"> </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <button type="submit" class="btn btn-success">Lưu lại</button>
                                        <a href="groupwork.html" class="btn btn-primary">Quay lại</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="col-md-2 col-12"></div>
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
    <!--Wave Effects -->
    <script src="<c:url value="/static/js/waves.js"/>"></script>
    <!-- Custom Theme JavaScript -->
    <script src="<c:url value="/static/js/custom.min.js"/>"></script>
</body>

