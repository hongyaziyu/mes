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
    <title>订单详细信息</title>
    <link href="../css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="../css/bootstrap.min.new.css"/>
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="../css/main.css"/>
    <script src="../js/jquery-3.2.1.min.js"></script>  
    <script src="../js/bootstrap.min.js"></script>
    <style type="text/css">
      table,table tr th, table tr td { border:1px solid  }
    </style>    
</head>
<body>
	
	<div class="container">	
		<div class="table-responsive">
		  	   <h4>订单详细信息</h4>
		  	 <table class="table">
				<thead>
					<tr>
						<th>订单号</th>					
						<th>客户</th>
						<th>制单人</th>
						<th>制单日期</th>
					</tr>
				</thead>
				<tbody>
					<tr>  	
						<td>${saleOrder.code}</td>				
						<td>${saleOrder.client}</td>
						<td>${saleOrder.maker}</td>
						<td>${saleOrder.madedate1}</td>							
					</tr>
				</tbody>		
			</table>
		</div>
	</div>
	<br/>
	<div class="container">
	  <h4>具体产品信息</h4>
		<div class="table-responsive">
		  	<table class="table">
				<thead>
					<tr>
						<th>图号</th>					
						<th>物料号</th>
						<th>产品名称</th>
						<th>产品规格</th>
						<th>订购数量</th>
						<th>单位</th>			
					</tr>
				</thead>
				<tbody>
				 <c:forEach items="${listsaleOrderDetail}" var="a">
					<tr>
						<td>${a.clientMaterialNo}</td>					
						<td>${a.materialNo}</td>
						<td>
						<c:if test="${(empty a.productName)||(a.productName=='')}">暂无描述信息</c:if>
						<c:if test="${(!empty a.productName)&&(a.productName!='')}">${a.productName}</c:if>
						</td>
						<td>
						<c:if test="${(empty a.productSpec)||(a.productSpec=='')}">&nbsp;&nbsp;&nbsp;&nbsp;/</c:if>
						<c:if test="${(!empty a.productSpec)&&(a.productSpec!='')}">${a.productSpec}</c:if>
						</td>
						<td><fmt:formatNumber value="${a.ordernum}" type="number" maxFractionDigits="2"/></td>	
						<td>${a.unit}</td>	
					</tr>
					</c:forEach>										
				</tbody>
			</table>
		
		</div>
	</div>
	<br />
	<div class="container" style = "text-align:right">
		<%-- <a class="btn btn-primary"  href="${pageContext.request.contextPath}/jdbc/toSaleOrderList.action" >返回</a> --%>
		<a class="btn btn-primary"  href="#" onclick="javascript:history.back(-1);" >返回</a>
	</div>
	
</body>
</html>

