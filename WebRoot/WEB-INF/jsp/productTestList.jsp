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
    <title>产品检测记录</title>
    <link href="../css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="../css/bootstrap.min.new.css"/>
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="../css/main.css"/>
</head>
<div class="container">
    <h4 class="myClass navbar-left">产品检测记录</h4>
		<form class="form-inline top" method="post" action="${pageContext.request.contextPath}/record/SelectProductTest.action">
		  <div class="form-group">
		  	<label for="exampleInputName2">客户</label>
		    <input type="text" class="form-control" style="width: 100px" id="exampleInputName2"  name="client" >
		  </div>
		  <div class="form-group">
		  	<label for="exampleInputName2">物料号</label>
		    <input type="text" class="form-control" id="exampleInputName2"  style="width: 180px" name="materialNo" >
		  </div>
		  
		   <label for="exampleInputName2">起始日期</label>
		  <div class="input-group date form_date"  data-date-format="yyyy-mm-dd">
              <input class="form-control" style="width:110px" size="16" type="text" name="start_date"> 
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
          </div> 
          <label for="exampleInputName2">截止日期</label>
          <div class="input-group date form_date"  data-date-format="yyyy-mm-dd">
              <input class="form-control"  size="16" style="width:110px" type="text" name="end_date"> 
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
          </div>
		 &nbsp;&nbsp;&nbsp;
         <button type="submit" class="btn btn-primary ">查询</button>
		</form>
		<br />
    <div class="table-responsive">
	  <table class="table">
			<thead>
				<tr>
					<th>客户</th>
					<th>计划单号</th>
					<th>批次号</th>
					<th>图号</th>
					<th>物料号</th>
					<th>产品名称</th>
					<th>检测工序</th>
					<th>特征值</th>
					<th>标准值</th>			
					<th>操作工</th>
					<th>检测时间</th>
					<th>修改/删除</th>
				</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${listproductTest}" var="item">
				<tr  >
					<td>${item.client}</td>					  
					<td>${item.planNo}</td>					
					<td>${item.batchNo}</td>
					<td>${item.clientMaterialNo}</td>
					<td>${item.materialNo}</td>
					<td>${item.productName}</td>
				<c:set var="string1" value="${item.processName}"/>
               <c:set var="string2" value="${fn:split(string1, '/')}" />	
					<td>${string2[0]}</td>
					<td>${string2[1]}</td>
					<td>${item.standardVal}</td>
					<td>${item.operator}</td>
					<td>${item.checkDate}</td>		
					<td>
						<input   name="id" type="hidden" value="${item.testId}"/>
						<input class="btn  btn-xs btn-primary" type="button" value="修改" onclick="revise(this)">
						<input class="btn  btn-xs btn-danger" type="button" value="删除" onclick="delet(this)">
					</td>
				</tr>
				</c:forEach>
				
			</tbody>
	</table>
	</div>  
	
	<!-- 分页功能 start -->    
    <div align="center" >    
        <font size="2">共 ${page.totalPageCount} 页</font> <font size="2">第    
            ${page.pageNow} 页</font> <a href="${pageContext.request.contextPath}/record/SelectProductTest.action?pageNow=1&client=${client}&materialNo=${materialNo}&start_date=${start_date}&end_date=${end_date}">首页</a>    
        <c:choose>    
            <c:when test="${page.pageNow - 1 > 0}">    
                <a href="${pageContext.request.contextPath}/record/SelectProductTest.action?pageNow=${page.pageNow - 1}&client=${client}&materialNo=${materialNo}&start_date=${start_date}&end_date=${end_date}">上一页</a>    
            </c:when>    
            <c:when test="${page.pageNow - 1 <= 0}">    
                <a href="${pageContext.request.contextPath}/record/SelectProductTest.action?pageNow=1&client=${client}&materialNo=${materialNo}&start_date=${start_date}&end_date=${end_date}">上一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath}/record/SelectProductTest.action?pageNow=${page.pageNow}&client=${client}&materialNo=${materialNo}&start_date=${start_date}&end_date=${end_date}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 < page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/SelectProductTest.action?pageNow=${page.pageNow + 1}&client=${client}&materialNo=${materialNo}&start_date=${start_date}&end_date=${end_date}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/SelectProductTest.action?pageNow=${page.totalPageCount}&client=${client}&materialNo=${materialNo}&start_date=${start_date}&end_date=${end_date}">下一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath }/record/SelectProductTest.action?pageNow=${page.pageNow}&client=${client}&materialNo=${materialNo}&start_date=${start_date}&end_date=${end_date}">尾页</a>    
            </c:when>    
            <c:otherwise>    
                <a href="${pageContext.request.contextPath }/record/SelectProductTest.action?pageNow=${page.totalPageCount}&client=${client}&materialNo=${materialNo}&start_date=${start_date}&end_date=${end_date}">尾页</a>    
            </c:otherwise>    
        </c:choose>  
        跳到<select name='topage' size='1' onchange="window.location=' ${pageContext.request.contextPath }/record/SelectProductTest.action?pageNow='+this.value+'&client=${client}&materialNo=${materialNo}&start_date=${start_date}&end_date=${end_date}'">
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
	
	function revise(obj)
	{
		var id=$(obj).prev().val();
		url = "${pageContext.request.contextPath}/plan/HuixianProductTest.action?testId="+id;
		location.href=url;	
	}
	
	function delet(obj)
	{
		var id=$(obj).prev().prev().val();
		url = "${pageContext.request.contextPath}/plan/DeleteProductTest.action?testId="+id;
		location.href=url;
	}
	$(function(){ 
             $('.date').datetimepicker({
						language: 'zh-CN',
						weekStart: 0,
						todayBtn: 1,
						autoclose: 1,
						todayHighlight: 1,
						startView: 3,
						minView: 2,
						forceParse: 0
		  });
		   
		  
		});
</script>
    
</body>
</html>