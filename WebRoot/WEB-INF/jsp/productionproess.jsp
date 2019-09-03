<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>		
<link href="../css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="../css/bootstrap.min.new.css"/>
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="../css/main.css"/>                        
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<title>订单进度记录</title>
    <script src="../js/jquery-3.2.1.min.js"></script>  
        <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/bootstrap-datetimepicker.js"></script>
    <script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>  
<style type="text/css">
        .black_overlay{ 
            display: none; 
            position: absolute; 
            top: 0%; 
            left: 0%; 
            width: 100%; 
            height: 100%; 
            background-color: black; 
            z-index:1001; 
            -moz-opacity: 0.8; 
            opacity:.80; 
            filter: alpha(opacity=88); 
        } 
        .white_content { 
            display: none; 
            position: absolute; 
            top: 10%; 
            left: 25%; 
            width: 55%; 
            height: 80%; 
            padding: 20px; 
            border: 2px solid blue; 
            background-color: white; 
            z-index:1002; 
            overflow: auto; 
        } 
        .pull-right{
        	position: fixed;
        	left:75%;
  			top:13%;
        } 
</style> 

<body>
<jsp:include page="headandfoot.jsp"></jsp:include>
    <div class="container" style="margin-top: 20px;">
	    	<h4 class="myClass navbar-left">订单进度记录</h4>
			<form class="form-inline top" method="post" action="${pageContext.request.contextPath}/record/selectProductionPlan.action">
				<div class="form-group">
				  	<label for="equipmentName">计划单号</label>
				    <input type="text" class="form-control" name="plan_no">
				</div>
				<div class="form-group">
				  	<label for="equipmentNum">图号</label>
				    <input type="text" class="form-control" name="client_material_no">
				</div>
				<div class="form-group">
				  	<label for="equipmentNum">物料号</label>
				    <input type="text" class="form-control" name="material_no">
				</div>
			    &nbsp;&nbsp;&nbsp;&nbsp;
			    <input class="btn btn-primary" type="submit" name="search" value="查询" />
			</form>
		<br />
		<br />
		<div class="table-responsive">
			<h4 >周计划信息</h4>
		  	<table class="table">
				<thead>
					<tr>						
						<th>计划单号</th>
						<th>客户</th>
						<th>物料号</th>						
						<th>计划数量</th>
						<th>下单日期</th>	
						<th>已完成数量</th>			
					</tr>
				</thead>
				<tbody>
				
					<tr>						
						<td>${listWeekPlan[0].planNo}</td>
						<td>${listWeekPlan[0].client}</td>
						<td>${listWeekPlan[0].materialNo}</td>
						<td>${listWeekPlan[0].planNum}</td>
						<td>${listWeekPlan[0].orderDate}</td> 
					    <td>${listWeekPlan[0].sendNum}</td> 									
					</tr>
				
				</tbody>	
			</table>
		</div>
		<br />
		<br />
		<div class="table-responsive">
			<h4>计划完成进度</h4>	
					
		  	 <table class="table">
				<thead>
					<tr>
						<th>物料号</th>
						<th>批次号</th>
						<!-- <th>合格品数</th> -->
						<th>生产流程</th>
						<th>正在生产车间</th>						
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${listWeekPlan}" var="a">
					<tr>
						<td>${a.materialNo}</td>
						<td>${a.batchNo}</td>
						<td>${a.sort}</td>
						<td>
						<a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'">${a.shop2}</a>
        				<div id="light" class="white_content">
        				<a class="pull-right " href = "javascript:void(0)" onclick = "document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'"><span class="glyphicon glyphicon-remove"></span></a>     			
        				<ul>
						    <li id="lis" style="list-style-type:none">
						       ${a.shop2} 
						    <ul id="ul1" style="list-style-type:none">
						        <c:forEach items="${listShopTransition1}" var="b"> 
						        <li><strong>提供班组：</strong>${b.shop1}</li>
						        <li><strong>接收班组：</strong>${b.shop2}</li>
						        <li><strong>合格品数：</strong>${b.qualifiedNum}</li>
						        <br>						
						        </c:forEach>  
						    </ul> 
						  	</li>
						</ul> 
						<a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'">关闭本窗口</a></div> 
        				<div id="fade" class="black_overlay"></div> 																				

						</td>					
					</tr>
				</c:forEach>  
				</tbody>	
			</table> 
		</div>
		
		<br />
		<br />
	</div>
	
     
  </body>
</html>