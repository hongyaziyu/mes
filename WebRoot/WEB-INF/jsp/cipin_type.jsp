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
   
    <title>不良品类型管理</title>
    <script src="../js/jquery-3.2.1.min.js"></script>  
    <script src="../js/bootstrap.min.js"></script>
    
	<script type="text/javascript" >//$(document).ready(function(){
	function revise(obj){
	 	var shopName=$(obj).parent().prev().prev().html();
	 	var cipinType=$(obj).parent().prev().html();
		location.href="${pageContext.request.contextPath}/manage/updateCipin.action?shopName="+shopName+"&cipinType="+cipinType;
	}
	
	function revice(obj){
	 	var shopName=$(obj).parent().prev().prev().prev().html();
	 	var cipinType=$(obj).parent().prev().prev().html();
		location.href="${pageContext.request.contextPath}/manage/deleteCipin.action?shopName="+shopName+"&cipinType="+cipinType;
	}                                                               
</script>
	
</head>
<body>

<div class="container">
    <h4 class="myClass navbar-left">不良品类型管理</h4>
   
		<form id="forms" class="form-inline "  method="post"   action="${pageContext.request.contextPath}/manage/toCipin.action">
		   &nbsp; &nbsp; &nbsp; &nbsp;
		  <input type="hidden" name="typeId" value="${cipin.typeId}"/>
		  <div class="form-group">
		  	<label for="equipmentName">车间名称</label>
		    <input type="text" style="width: 120px" name="shopName" class="form-control" id="equipmentName">
		  </div>
		  <div class="form-group">
		  	<label for="equipmentNum">不良品类别</label>
		    <input type="text" style="width: 150px" name="cipinType" class="form-control" id="equipmentNum">
		  </div>
		  &nbsp;
		  
		  <button  type="submit" class="btn btn-primary">查询</button>
		  
		  &nbsp;
		  	<!--以下第一行为添加按钮实现的网页的跳转-->	  
		  <a class="btn btn-primary"  href="${pageContext.request.contextPath}/manage/toinsertCipin.action" >添加</a> 
		   <!-- excel导入文件action执行语句导入excel的语句 -->
			 &nbsp; &nbsp; &nbsp;
		</form>
		<br />
	<div class="table-responsive">
	  <table class="table">
			<thead>
				<tr>
				    <th>车间名称</th>
					<th>不良品类别</th>					
					<th>修改</th>
					<th>删除</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${listCipin}" var="cipin">
			<!-- 循环输出forEach -->
				<tr>  
					<td>${cipin.shopName}</td>
					<td>${cipin.cipinType}</td>
					<td>
					<input class="btn  btn-xs btn-primary" type="button" value="修改" onclick="revise(this)"> 
					</td>
					<td>
					<input class="btn  btn-xs btn-danger" type="button" value="删除" onclick="revice(this)">
					</td>
				</tr>
				</c:forEach>
			</tbody>
			</table>	
</div>

<!-- 分页功能 start -->    
    <div align="center" >    
        <font size="2">共 ${page.totalPageCount} 页</font> <font size="2">第    
            ${page.pageNow} 页</font> <a href="${pageContext.request.contextPath}/manage/toCipin.action?pageNow=1&shopName=${shopName}&asset_no=${cipinType}">首页</a>    
        <c:choose>  
            <c:when test="${page.pageNow - 1 > 0}">    
                <a href="${pageContext.request.contextPath}/manage/toCipin.action?pageNow=${page.pageNow - 1}&shopName=${shopName}&cipinType=${cipinType}">上一页</a>    
            </c:when>    
            <c:when test="${page.pageNow - 1 <= 0}">    
                <a href="${pageContext.request.contextPath}/manage/toCipin.action?pageNow=1&shopName=${shopName}&cipinType=${cipinType}">上一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath}/manage/toCipin.action?pageNow=${page.pageNow}&shopName=${shopName}&cipinType=${cipinType}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 < page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/manage/toCipin.action?pageNow=${page.pageNow + 1}&shopName=${shopName}&cipinType=${cipinType}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/manage/toCipin.action?pageNow=${page.totalPageCount}&shopName=${shopName}&cipinType=${cipinType}">下一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath }/manage/toCipin.action?pageNow=${page.pageNow}&shopName=${shopName}&cipinType=${cipinType}">尾页</a>    
            </c:when>    
            <c:otherwise>    
                <a href="${pageContext.request.contextPath }/manage/toCipin.action?pageNow=${page.totalPageCount}&shopName=${shopName}&cipinType=${cipinType}">尾页</a>    
            </c:otherwise>    
        </c:choose>  
        跳到<select name='topage' size='1' onchange="window.location=' ${pageContext.request.contextPath }/manage/toCipin.action?pageNow='+this.value+'&shopName=${shopName}&cipinType=${cipinType}'">
       <c:forEach var="i" begin="1" end="${page.totalPageCount}">
       <c:if test="${i==page.pageNow}"><option value='${i}' selected>${i}</option></c:if>
       <c:if test="${i!=page.pageNow}"><option value='${i}'>${i}</option></c:if>
       </c:forEach></select> 页
    </div>    
    <!-- 分页功能 End -->

</div>
</body>
</html>

