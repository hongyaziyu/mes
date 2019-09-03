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
    <title>订单记录</title>
    <link href="../css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="../css/bootstrap.min.new.css"/>
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="../css/main.css"/>
    <link rel="stylesheet" href="../css/layer.css" />
</head>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
       <script src="../js/jquery-3.2.1.min.js"></script>  
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/bootstrap-datetimepicker.js"></script>
        <script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
        <script type="text/javascript" src="../js/layer.js"></script>
<script type="text/javascript">
$(function(){ 
            $('.date').datetimepicker({
				 minView: "month", //选择日期后，不会再跳转去选择时分秒 
			    language:  'zh-CN',
			    format: 'yyyy-mm-dd',
			    todayBtn:  1,
			    autoclose: 1,
		   }); 
		});
</script>

<script type="text/javascript" >//$(document).ready(function(){
	function revise(obj){
	 	var id=$(obj).prev().val();
		location.href="${pageContext.request.contextPath}/jdbc/toSaleOrderDetail.action?id="+id;
	}
	
</script>

<div class="container">
    <h4 class="myClass navbar-left">订单记录</h4>
		<form class="form-inline top"  method="post" action="${pageContext.request.contextPath}/jdbc/toSaleOrderList.action">
		 <div class="form-group">
		  	<label for="exampleInputName2">订单号</label>
		    <input type="text" class="form-control"  name="code">
		  </div>
		   <label for="exampleInputName2">起始日期</label>
		  <div class="input-group date form_date"  data-date-format="yyyy-mm-dd">
              <input class="form-control"   size="16" type="text" name="start_date"> 
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
          </div> 
          <label for="exampleInputName2">截止日期</label>
          <div class="input-group date form_date"  data-date-format="yyyy-mm-dd">
              <input class="form-control" size="16"  type="text" name="end_date"> 
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
          </div> 
		  &nbsp;&nbsp;&nbsp;&nbsp;
		
		   <div class="btn-group dropdown">
			 <button type="submit" class="btn btn-primary ">查询</button>
		</div>
		</form>
		<br />
    <div class="table-responsive">
	  <table class="table">
			<thead>
				<tr>
					<th>订单号</th>
					<th>客户名称</th>
					<th>制单人</th>
					<th>制单日期</th>
					<th>审批人</th>
					<th>审批日期</th>
					<th>是否关闭</th>
					<th>查看详情</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${listSaleOrder}" var="item">
				<tr  >
					<td>${item.code }</td>
					<td>${item.client }</td>
					<td>${item.maker }</td>
					<td>${item.madedate1 }</td>
					<td>${item.auditor }</td>
					<td>${item.auditeddate1 }</td>
					<td>
					<c:if test="${(empty item.closer)||(item.closer=='')}">否</c:if>
					<c:if test="${(!empty item.closer)&&(item.closer!='')}">是</c:if>
					</td>
					<td>
					 <input type="hidden" name="id" value="${item.id}"/>
					 <input class="btn  btn-xs btn-primary" type="button" value="查看详情" onclick="revise(this)">
				   </td>
				</tr>
				</c:forEach>
			</tbody>
	</table>
	</div> 
		<!-- 分页功能 start -->    
    <div align="center" >    
        <font size="2">共 ${page.totalPageCount} 页</font> <font size="2">第    
            ${page.pageNow} 页</font> <a href="${pageContext.request.contextPath}/jdbc/toSaleOrderList.action?pageNow=1&code=${code}&start_date=${start_date}&end_date=${end_date}">首页</a>    
        <c:choose>    
            <c:when test="${page.pageNow - 1 > 0}">    
                <a href="${pageContext.request.contextPath}/jdbc/toSaleOrderList.action?pageNow=${page.pageNow - 1}&code=${code}&start_date=${start_date}&end_date=${end_date}">上一页</a>    
            </c:when>    
            <c:when test="${page.pageNow - 1 <= 0}">    
                <a href="${pageContext.request.contextPath}/jdbc/toSaleOrderList.action?pageNow=1&code=${code}&start_date=${start_date}&end_date=${end_date}">上一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath}/jdbc/toSaleOrderList.action?pageNow=${page.pageNow}&code=${code}&start_date=${start_date}&end_date=${end_date}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 < page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/jdbc/toSaleOrderList.action?pageNow=${page.pageNow + 1}&code=${code}&start_date=${start_date}&end_date=${end_date}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/jdbc/toSaleOrderList.action?pageNow=${page.totalPageCount}&code=${code}&start_date=${start_date}&end_date=${end_date}">下一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath }/jdbc/toSaleOrderList.action?pageNow=${page.pageNow}&code=${code}&start_date=${start_date}&end_date=${end_date}">尾页</a>    
            </c:when>    
            <c:otherwise>    
                <a href="${pageContext.request.contextPath }/jdbc/toSaleOrderList.action?pageNow=${page.totalPageCount}&code=${code}&start_date=${start_date}&end_date=${end_date}">尾页</a>    
            </c:otherwise>    
        </c:choose>  
        跳到<select name='topage' size='1' onchange="window.location=' ${pageContext.request.contextPath }/jdbc/toSaleOrderList.action?pageNow='+this.value+'&code=${code}&start_date=${start_date}&end_date=${end_date}'">
       <c:forEach var="i" begin="1" end="${page.totalPageCount}">
       <c:if test="${i==page.pageNow}"><option value='${i}' selected>${i}</option></c:if>
       <c:if test="${i!=page.pageNow}"><option value='${i}'>${i}</option></c:if>
       </c:forEach></select> 页
    </div>    
    <!-- 分页功能 End -->
</div>
   
</body>
</html>