<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<jsp:include page="headandfoot.jsp"></jsp:include>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
    <title>工资单记录</title>
    <link href="../css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="../css/bootstrap.min.new.css"/>
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="../css/main.css"/>
</head>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
       <script src="../js/jquery-3.2.1.min.js"></script>  
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/bootstrap-datetimepicker.js"></script>
        <script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
        
        
<div class="container">
    <h4 class="myClass navbar-left">工资单记录</h4>
		<form class="form-inline top"  action="${pageContext.request.contextPath}/record/SalarySelect.action">
		   <div class="form-group">
		  	<label for="exampleInputName2">操作工</label>
		    <input type="text" class="form-control" id="cz" name="operator"value="${HX.operator}" >
		  </div>
		  <div class="form-group">
		  	<label for="exampleInputName2">车间</label>
		    <input type="text" class="form-control" id="cj" name="shop_name" value="${HX.shopName}" >
		  </div>
		  <label for="exampleInputName2">月份</label>
          <div class="input-group date form_date"  data-date-format="yyyy-mm">
              <input class="form-control" size="16" type="text" id="month" name="date" value="${HX.produceDate}"> 
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
          </div> 
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  <button type="submit" class="btn btn-primary ">查询</button>
		  &nbsp;&nbsp;
		   <input class="btn btn-warning" type="button" value="工资excel导出" onclick="revise(this)">
		</form>
		<br />
    <div class="table-responsive  center">
	  <table class="table">
			<thead>
				<tr>
					<th>操作工</th>
					<th>工资</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${listsalary}" var="a">
				<tr  >
					<td>${a.operator}</td>
					<td><fmt:formatNumber value="${a.shopName== '仪表工段' ? (a.hegeNum+a.totalCipinNum)*a.price : a.hegeNum*a.price }" pattern="#.###" type="number"/></td>
                   <%--  <td><fmt:formatNumber value="${a.hegeNum*a.price}" pattern="#.###" type="number"/></td>     
					<td>${a.shopName== "仪表工段" ? a.totalNum*a.price : a.hegeNum*a.price } </td> --%>
				</tr>
		    </c:forEach>
			</tbody>
	</table>
	</div>  
	
	<!-- 分页功能 start -->    
    <div align="center" >    
        <font size="2">共 ${page.totalPageCount} 页</font> <font size="2">第    
            ${page.pageNow} 页</font> <a href="${pageContext.request.contextPath}/record/SalarySelect.action?pageNow=1&operator=${operator}&shop_name=${shop_name}&date=${date}">首页</a>    
        <c:choose>    
            <c:when test="${page.pageNow - 1 > 0}">    
                <a href="${pageContext.request.contextPath}/record/SalarySelect.action?pageNow=${page.pageNow - 1}&operator=${operator}&shop_name=${shop_name}&date=${date}">上一页</a>    
            </c:when>    
            <c:when test="${page.pageNow - 1 <= 0}">    
                <a href="${pageContext.request.contextPath}/record/SalarySelect.action?pageNow=1&operator=${operator}&shop_name=${shop_name}&date=${date}">上一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath}/record/SalarySelect.action?pageNow=${page.pageNow}&operator=${operator}&shop_name=${shop_name}&date=${date}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 < page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/SalarySelect.action?pageNow=${page.pageNow + 1}&operator=${operator}&shop_name=${shop_name}&date=${date}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/SalarySelect.action?pageNow=${page.totalPageCount}&operator=${operator}&shop_name=${shop_name}&date=${date}">下一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath }/record/SalarySelect.action?pageNow=${page.pageNow}&operator=${operator}&shop_name=${shop_name}&date=${date}">尾页</a>    
            </c:when>    
            <c:otherwise>    
                <a href="${pageContext.request.contextPath }/record/SalarySelect.action?pageNow=${page.totalPageCount}&operator=${operator}&shop_name=${shop_name}&date=${date}">尾页</a>    
            </c:otherwise>    
        </c:choose>  
        跳到<select name='topage' size='1' onchange="window.location=' ${pageContext.request.contextPath }/record/SalarySelect.action?pageNow='+this.value+'&operator=${operator}&shop_name=${shop_name}&date=${date}'">
       <c:forEach var="i" begin="1" end="${page.totalPageCount}">
       <c:if test="${i==page.pageNow}"><option value='${i}' selected>${i}</option></c:if>
       <c:if test="${i!=page.pageNow}"><option value='${i}'>${i}</option></c:if>
       </c:forEach></select> 页
    </div>    
    <!-- 分页功能 End -->
	
</div>


<script type="text/javascript" >//$(document).ready(function(){
	
	function revise(obj){
	    
	    
		var id=$(obj).prev().prev().val();
		//location.href="order.html?id="+id;
	}
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
	
	// 打印功能
 
	function revise(){
	 	var operator=document.getElementById("cz").value;
	 	var shop_name=document.getElementById("cj").value;
	 	var date=document.getElementById("month").value;
		location.href="${pageContext.request.contextPath}/record/Salaryexport.action?operator="+operator+"&shop_name="+shop_name+"&date="+date;
	
	}
	
</script>
    
</body>
</html>