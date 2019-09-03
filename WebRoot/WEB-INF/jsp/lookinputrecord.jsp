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
    <title>领原材料详细信息</title>
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
		  	   <h4>领原材料详细信息</h4>
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
						<th>申请人</th>	
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${inputrecord}" var="a">
					<tr>
						<td>${a.planNo}</td>					
						<td>${a.clientMaterialNo}</td>
						<td>${a.materialNo}</td>
						<td>${a.batchNo}</td>
						<td>${a.materialName}</td>
						<td>${a.productSpec}</td>
						<td>${a.shopName}</td>
					    <td>${a.applyPeople}</td>							
					</tr>
					</c:forEach>
				</tbody>		
			</table>
		</div>
	</div>
	
	<div class="container" >
		<div class="table-responsive">
		  	<table class="table">
				<thead>
					<tr>
					    <th>原材料批次号</th>
						<th>材料名称</th>
						<th>材料编号</th>						
						<th>实际用量</th>
						<th>单位</th>	
						<th>提供人</th>
						<th>领料人</th>
						<th>领料日期</th>
						<th>备注</th>			
					</tr>
				</thead>
				<tbody>
				 <c:forEach items="${inputrecord}" var="a">
					<tr>
					    <td>${a.materialBatchNo}</td>
						<td>${a.cailiaoMc}</td>
						<td>${a.cailiaoBh}</td>					
						<th>${a.materialNum}</th>
						<th>${a.unit}</th>	
						<td>
						<c:if test="${(empty a.provider)||(a.provider==' ')}">————</c:if>
					    <c:if test="${(!empty a.provider)&&(a.provider!=' ')}">${a.provider}</c:if>
					    </td>
						<td>
						<c:if test="${(empty a.acceptor)||(a.acceptor==' ')}">————</c:if>
					    <c:if test="${(!empty a.acceptor)&&(a.acceptor!=' ')}">${a.acceptor}</c:if>
					    </td>
						<td>
						<c:if test="${(empty a.getDate)||(a.getDate==' ')}">————</c:if>
					    <c:if test="${(!empty a.getDate)&&(a.getDate!=' ')}">${a.getDate}</c:if>
					    </td>
						<td>
						<c:if test="${(empty a.remark)||(a.remark==' ')}">无</c:if>
					    <c:if test="${(!empty a.remark)&&(a.remark!=' ')}">${a.remark}</c:if>
					   </td>										
					</tr>
					</c:forEach>										
				</tbody>
			</table>
		</div>
	</div>	 
	<br />
	<div class="container" style = "text-align:right">
		<a class="btn btn-primary"  href="${pageContext.request.contextPath}/record/InputMaterialsRecord.action" >返回</a>
	</div>
	


</body>
</html>

