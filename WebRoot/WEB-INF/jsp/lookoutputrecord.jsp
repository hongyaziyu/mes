<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<jsp:include page="headandfoot.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
    <title>退原材料详细信息</title>
    <link href="../css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="../css/bootstrap.min.new.css"/>
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="../css/main.css"/>
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../js/jquery-3.2.1.min.js"></script>  
        <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/bootstrap-datetimepicker.js"></script>
    <script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
       
</head>
<body>
	
	<div class="container">	
		<div class="table-responsive">
			
		  	
		  	   <h4>退原材料详细信息</h4>
		  	 <table class="table">
				<thead>
					<tr>
					    
						<th>计划单号</th>					
						<th>图号</th>
						<th>物料号</th>
						<th>批次号</th>
						<th>产品名称</th>
						<th>产品规格</th>
						<th>车间名称</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${outputrecord}" var="a">
					<tr>
					  	
						<td>${a.planNo}</td>					
						<td>${a.clientMaterialNo}</td>
						<td>${a.materialNo}</td>
						<td>${a.batchNo}</td>
						<td>${a.materialName}</td>
						<td>${a.productSpec}</td>
						<td>${a.shopName}</td>									
					</tr>
					</c:forEach>
				</tbody>		
			</table>
		</div>
	</div>
	
	<div class="container">
		<div class="table-responsive">
		  	<table class="table">
				<thead>
					<tr>
						<th>材料名称</th>					
						<th>材料编号</th>
						<th>材料数量</th>
						<th>退料人</th>
						<th>接受人</th>
						<th>退料日期</th>
						<th>备注</th>				
					</tr>
				</thead>
				<tbody>
				 <c:forEach items="${outputrecord}" var="a">
					<tr>
						<td>${a.cailiaoMc}</td>					
						<td>${a.cailiaoBh}</td>
						<th>${a.materialNum}</th>
						<td>${a.provider}</td>
						<td>${a.acceptor}</td>
						<td>${a.getDate}</td>
						<td>${a.remark}</td>										
					</tr>
					</c:forEach>										
				</tbody>
			</table>
		
		</div>
	</div>
	<br />
	<div class="container" style = "text-align:right">
		<a class="btn btn-primary"  href="${pageContext.request.contextPath}/record/OutputMaterialsRecord.action" >返回</a>
	</div>
	
</body>
</html>

