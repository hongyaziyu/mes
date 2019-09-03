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
    <title>日检记录详情</title>
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
	
	<div class="container">	
	 <h4>日检记录详情</h4>
		<div class="table-responsive">
		
		  	<table class="table">
		  	  
				<thead>
					<tr>
						<!-- <th>批次号</th>					
						<th>车间名称</th>
						<th>工序名称</th> -->
						<th>设备名称</th>
						<th>设备编号</th>
						<th>操作工</th>	
					</tr>
				</thead>
				<tbody>
					<tr>
						<%-- <td>${dailyCheck.batchNo}</td>					
						<td>${dailyCheck.shopName}</td>
						<td>${dailyCheck.processName}</td> --%>
						<td>${dailyCheck.assetName}</td>
						<td>${dailyCheck.assetNo}</td>
						<td>${dailyCheck.operator}</td>
														
					</tr>
		
				</tbody>	
					
				
			</table>
		</div>
	</div>
	<hr/>
	<div class="container">
		<div class="table-responsive">
		  	<table class="table">
				<thead>
					<tr>
						<th>点检内容</th>
						<th>结果1</th>					
						<th>时间1</th>
						<th>备注1</th>
						<th>结果2</th>					
						<th>时间2</th>
						<th>备注2</th>
									
					</tr>
				</thead>
				<tbody>
		<c:forEach items="${listCheckRecord}" var="a"> 
					<tr>
						<td>${a.checkContent}</td>
						<td>${a.results1}</td>					
						<td>${a.time1}</td>
						<td><c:choose><c:when test="${a.remarks1=='null'}"></c:when><c:otherwise>${a.remarks1}</c:otherwise></c:choose></td>
						<td>${a.results2}</td>
						<td>${a.time2}</td>
						<td><c:choose><c:when test="${a.remarks2=='null'}"></c:when><c:otherwise>${a.remarks2}</c:otherwise></c:choose></td>
																	
					</tr>
					 </c:forEach>										
				</tbody>
				<thead>
					<tr>
					    <th>结果3</th>					
						<th>时间3</th>
						<th>备注3</th>
						<th>结果4</th>					
						<th>时间4</th>
						<th>备注4</th>	
						<th>结果5</th>					
						<th>时间5</th>
						<th>备注5</th>
										
					</tr>
				</thead>
				<tbody>
			  <c:forEach items="${listCheckRecord}" var="a">  
					<tr>
					    <td>${a.results3}</td>
						<td>${a.time3}</td>
                        <td><c:choose><c:when test="${a.remarks3=='null'}"></c:when><c:otherwise>${a.remarks3}</c:otherwise></c:choose></td>
                        <td>${a.results4}</td>
						<td>${a.time4}</td>					
						<td><c:choose><c:when test="${a.remarks4=='null'}"></c:when><c:otherwise>${a.remarks4}</c:otherwise></c:choose></td>
						<td>${a.results5}</td>					
						<td>${a.time5}</td>
						<td><c:choose><c:when test="${a.remarks5=='null'}"></c:when><c:otherwise>${a.remarks5}</c:otherwise></c:choose></td>
																
					</tr>
					  </c:forEach>  								
				</tbody>
				
				
				<thead>
					<tr>
						<th>结果6</th>					
						<th>时间6</th>
						<th>备注6</th>
						<th>结果7</th>					
						<th>时间7</th>
						<th>备注7</th>
						<th>结果8</th>					
						<th>时间8</th>
						<th>备注8</th>			
					</tr>
				</thead>
				<tbody>
			  <c:forEach items="${listCheckRecord}" var="a">  
					<tr>
					    <td>${a.results6}</td>
						<td>${a.time6}</td>
						<td><c:choose><c:when test="${a.remarks6=='null'}"></c:when><c:otherwise>${a.remarks6}</c:otherwise></c:choose></td>
						<td>${a.results7}</td>
						<td>${a.time7}</td>
                        <td><c:choose><c:when test="${a.remarks7=='null'}"></c:when><c:otherwise>${a.remarks7}</c:otherwise></c:choose></td>
                        <td>${a.results8}</td>
						<td>${a.time8}</td>					
						<td><c:choose><c:when test="${a.remarks8=='null'}"></c:when><c:otherwise>${a.remarks8}</c:otherwise></c:choose></td>						
					</tr>
					  </c:forEach>  								
				</tbody>     
			</table>
		</div>
	</div>
	
	<br />
	<div class="container" style = "text-align:right">
		 <a class="btn btn-primary"  href="${pageContext.request.contextPath}/record/DailyCheck.action" >返回</a> 
	</div>

</body>
</html>

