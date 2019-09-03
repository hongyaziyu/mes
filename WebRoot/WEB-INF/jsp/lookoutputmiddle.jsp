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
    <title>半成品入库详细信息</title>
    <link href="../css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="../css/bootstrap.min.new.css"/>
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="../css/main.css"/>
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../js/jquery-3.2.1.min.js"></script>  
        <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>
    
</head>
<body>
	<form class="form-inline"  method="post" name="form1"  action="${pageContext.request.contextPath}/record/approveMiddleProblem.action">		
	<div class="container">	
		<div class="table-responsive">
           <input type="hidden" name="recordId" value="${outputmiddlerecord.recordId}"/>
		  	   <h4>半成品入库详细信息</h4>
		  	 <table class="table">
				<thead>
					<tr>	
						<th>计划单号</th>					
						<th>图号</th>
						<th>物料号</th>
						<th>半成品名称</th>
						<!-- <th>组合件批次号</th> -->
						<th>半成品批次号</th>
						<th>入库数量</th>
						<th>入库日期</th>
						
					</tr>
				</thead>
				<tbody>
					<tr>
					  	<td>${outputmiddlerecord.planNo}</td>					
						<td>${outputmiddlerecord.clientMaterialNo}</td>
						<td>${outputmiddlerecord.materialNo}</td>
						<td>${outputmiddlerecord.productMc}</td>
						<%-- <td>${outputmiddlerecord.batchNo1}</td> --%>
						<td>${outputmiddlerecord.batchNo2}</td>
						<td>${outputmiddlerecord.productNum}</td>
						<td>${outputmiddlerecord.transDate}</td>
													
					</tr>
				</tbody>	
				<table class="table">
				<thead>
					<tr>		
					    <th>提供班组</th>
						<th>提供人</th>
						<th>接受班组</th>
						<th>接受人</th>
						<th>备注</th>	
					</tr>
				</thead>	
				<tbody>
					<tr>		
						<td>${outputmiddlerecord.shop1}</td>
						<td>${outputmiddlerecord.provider}</td>
						<td>${outputmiddlerecord.shop2}</td>
						<td>${outputmiddlerecord.acceptor}</td>
						<td>${outputmiddlerecord.remark}</td>								
					</tr>
				</tbody>		
			</table>
		</div>
	</div>
	
	<br />
	<div class="container" style = "text-align:right">
	   <input class="btn btn-success" type="submit" value="问题已解决">
		<a class="btn btn-primary"  href="${pageContext.request.contextPath}/record/OutputMiddleMaterialsRecord.action" >返回</a>
	</div>
</form>	
</body>
</html>

