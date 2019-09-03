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
    <title>工资单详细记录</title>
    <link href="../css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="../css/bootstrap.min.new.css"/>
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="../css/main.css"/>
</head>
<div class="container">
    <h4 class="myClass navbar-left">工资单详细记录</h4>
    
		<form class="form-inline top" action="${pageContext.request.contextPath}/record/SalaryDetailSelect.action">
		  <div class="form-group">
		  	<label for="exampleInputName2">车间</label>
		    <input type="text" class="form-control"  id="cj"  name="shop_name"  value="${HX.shopName}"/>
		  </div>
		  <div class="form-group">
		  	<label for="exampleInputName2">操作工</label>
		    <input type="text" class="form-control"  id="cz" name="operator" value="${HX.operator}" >
		  </div>
		   <label for="exampleInputName2">月份</label>
          <div class="input-group date form_date"  data-date-format="yyyy-mm">
              <input class="form-control" size="16" type="text" name="date" id="month" value="${HX.produceDate}"> 
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
          </div> 
          &nbsp;&nbsp;&nbsp;&nbsp;
		  <button type="submit" class="btn btn-primary ">查询</button>
		  &nbsp;&nbsp;&nbsp;&nbsp;
		 <input class="btn btn-warning" type="button" value="工资详情excel导出" onclick="revise(this)">
            
		
		</form>
		<br />
    <div class="table-responsive">
	  <table class="table">
			<thead>
				<tr>
					<th>生产日期</th>	
					<th>批次号</th>				
					<th>图号</th>
					<th>物料号</th>
					<th>车间名称</th>
					<th>产品规格</th>
					<th>工序</th>
					<th>操作人</th>
					<th>合格数量</th>
					<th>工价</th>
					<th>料废工资</th>
					<th>总工资</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${listdetailsalary}" var="b">
			
				<tr >
					<td>${b.produceDate}</td>
					<td>${b.batchNo}</td>
					<td>${b.clientMaterialNo}</td>
					<td>${b.materialNo}</td>
					<td>${b.shopName}</td>
					<td>${b.productSpec}</td>
					<td>${b.processName}</td>
					<td>${b.operator}</td>
					<td>${b.hegeNum}</td>
					<td>${b.price}</td>
					<td><fmt:formatNumber value="${b.shopName== '仪表工段' ? b.totalCipinNum*b.price : 0 }" pattern="#.###" type="number"/></td>				
					<td><fmt:formatNumber value="${b.shopName== '仪表工段' ? (b.hegeNum+b.totalCipinNum)*b.price : b.hegeNum*b.price }" pattern="#.###" type="number"/></td>
				    
				</tr>
				
				</c:forEach>
			</tbody>
	</table>
	</div>  
	
	<!-- 分页功能 start -->    
    <div align="center" >    
        <font size="2">共 ${page.totalPageCount} 页</font> <font size="2">第    
            ${page.pageNow} 页</font> <a href="${pageContext.request.contextPath}/record/SalaryDetailSelect.action?pageNow=1&operator=${operator}&shop_name=${shop_name}&date=${date}">首页</a>    
        <c:choose>    
            <c:when test="${page.pageNow - 1 > 0}">    
                <a href="${pageContext.request.contextPath}/record/SalaryDetailSelect.action?pageNow=${page.pageNow - 1}&operator=${operator}&shop_name=${shop_name}&date=${date}">上一页</a>    
            </c:when>    
            <c:when test="${page.pageNow - 1 <= 0}">    
                <a href="${pageContext.request.contextPath}/record/SalaryDetailSelect.action?pageNow=1&operator=${operator}&shop_name=${shop_name}&date=${date}">上一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath}/record/SalaryDetailSelect.action?pageNow=${page.pageNow}&operator=${operator}&shop_name=${shop_name}&date=${date}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 < page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/SalaryDetailSelect.action?pageNow=${page.pageNow + 1}&operator=${operator}&shop_name=${shop_name}&date=${date}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/SalaryDetailSelect.action?pageNow=${page.totalPageCount}&operator=${operator}&shop_name=${shop_name}&date=${date}">下一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath }/record/SalaryDetailSelect.action?pageNow=${page.pageNow}&operator=${operator}&shop_name=${shop_name}&date=${date}">尾页</a>    
            </c:when>    
            <c:otherwise>    
                <a href="${pageContext.request.contextPath }/record/SalaryDetailSelect.action?pageNow=${page.totalPageCount}&operator=${operator}&shop_name=${shop_name}&date=${date}">尾页</a>    
            </c:otherwise>    
        </c:choose>  
        跳到<select name='topage' size='1' onchange="window.location=' ${pageContext.request.contextPath }/record/SalaryDetailSelect.action?pageNow='+this.value+'&operator=${operator}&shop_name=${shop_name}&date=${date}'">
       <c:forEach var="i" begin="1" end="${page.totalPageCount}">
       <c:if test="${i==page.pageNow}"><option value='${i}' selected>${i}</option></c:if>
       <c:if test="${i!=page.pageNow}"><option value='${i}'>${i}</option></c:if>
       </c:forEach></select> 页
    </div>    
    <!-- 分页功能 End -->
	
</div>



<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
       <script src="../js/jquery-3.2.1.min.js"></script>  
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/bootstrap-datetimepicker.js"></script>
        <script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" >//$(document).ready(function(){
	
	function revise(obj){
	    
	    
		var id=$(obj).prev().prev().val();
		//location.href="order.html?id="+id;
	}
	
	
	//只显示年和月的日期组件写法
	$(function(){ 
	$('.date').datetimepicker({
			language: 'zh-CN',
			weekStart: 0,
			todayBtn: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 3,
			minView: "year",
			forceParse: 0,
			format:'yyyy-mm'
		});
	});
	
	
	//excel导出
	function revise(){
	 	var operator=document.getElementById("cz").value;
	 	var shop_name=document.getElementById("cj").value;
	 	var date=document.getElementById("month").value;
		location.href="${pageContext.request.contextPath}/record/Salarydetailexport.action?operator="+operator+"&shop_name="+shop_name+"&date="+date;
	}
</script>
    
</body>
</html>