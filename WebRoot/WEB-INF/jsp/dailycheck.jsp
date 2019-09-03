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
   
    <title>日检记录</title>
   
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../js/jquery-3.2.1.min.js"></script>  
        <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>
    <script type="text/javascript" >//$(document).ready(function(){
	function detail(obj){

	 	var id=$(obj).prev().val();
	 	//alert(id);
		location.href="${pageContext.request.contextPath}/record/lookCheck.action?checkId="+id;	
	}
	
</script>
</head>

<body>
 <div class="container">
    <h4 class="myClass navbar-left">日检记录</h4>
        <form id="form" class="form-inline" action="${pageContext.request.contextPath}/record/DailyCheck.action">
		
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
		  	<div class="form-group">
		  		<label for="equipmentName">批次号</label>
		    	<input type="text" name="batchNo" class="form-control" id="batchNo">
		  	</div>
		  	<div class="form-group">
		  		<label for="equipmentNum">工序名称</label>
		    	<input type="text"  name="processName"  class="form-control" id="processName">
		  	</div>
		  	<div class="form-group">
		  		<label for="equipmentNum">设备编号</label>
		    	<input type="text"  name="assetNo" class="form-control" id="assetNo">
		  	</div>
		  	&nbsp;&nbsp;&nbsp;&nbsp;
		  	<button type="submit" class="btn btn-primary">查询</button>
		 	 &nbsp;&nbsp;&nbsp;&nbsp;		  
		 </form>
	
	<br />
	
	<div class="table-responsive">
	  	<table class="table">
			<thead>
				<tr>
					<!-- <th>批次号</th> -->
					<!-- <th>车间</th> -->
					<!-- <th>工序</th> -->
					<th>设备名称</th>
					<th>设备编号</th>
					<th>操作工</th>
					<!-- <th>故障状态</th> -->
					<th>故障日期</th>
					<!-- <th>故障内容</th>
					<th>发送人</th>	 -->			
					<th>查看详情</th>
				</tr>
			</thead>
			<tbody>
	<c:forEach items="${listDailyCheck}" var="dailycheck"> 
			<!-- 循环输出forEach -->
				<tr>
					<%-- <td>${dailycheck.batchNo}</td>
					<td>${dailycheck.shopName}</td>
					<td>${dailycheck.processName}</td> --%>
					<td>${dailycheck.assetName}</td>
					<td>${dailycheck.assetNo}</td>
					<td>${dailycheck.operator}</td>
					<%-- <td>${dailycheck.state}</td> --%>
					<td>${dailycheck.errorDate}</td>
				<%-- 	<td>${dailycheck.errorContent}</td>
					<td>${dailycheck.sendPerson}</td> --%>
					<td>				
				     <input type="hidden" name="id" value="${dailycheck.checkId}"/><input class="btn  btn-xs btn-primary" type="button" value="查看详情" onclick="detail(this)"> 
					</td>
					
				</tr>
				 </c:forEach> 
			</tbody>
		</table>
</div>

<!-- 分页功能 start -->    
    <div align="center" >    
        <font size="2">共 ${page.totalPageCount} 页</font> <font size="2">第    
            ${page.pageNow} 页</font> <a href="${pageContext.request.contextPath}/record/DailyCheck.action?pageNow=1&batchNo=${batchNo}&processName=${processName}&assetNo=${assetNo}">首页</a>    
        <c:choose>    
            <c:when test="${page.pageNow - 1 > 0}">    
                <a href="${pageContext.request.contextPath}/record/DailyCheck.action?pageNow=${page.pageNow - 1}&batchNo=${batchNo}&processName=${processName}&assetNo=${assetNo}">上一页</a>    
            </c:when>    
            <c:when test="${page.pageNow - 1 <= 0}">    
                <a href="${pageContext.request.contextPath}/record/DailyCheck.action?pageNow=1&batchNo=${batchNo}&processName=${processName}&assetNo=${assetNo}">上一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath}/record/DailyCheck.action?pageNow=${page.pageNow}&batchNo=${batchNo}&processName=${processName}&assetNo=${assetNo}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 < page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/DailyCheck.action?pageNow=${page.pageNow + 1}&batchNo=${batchNo}&processName=${processName}&assetNo=${assetNo}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/DailyCheck.action?pageNow=${page.totalPageCount}&batchNo=${batchNo}&processName=${processName}&assetNo=${assetNo}">下一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath }/record/DailyCheck.action?pageNow=${page.pageNow}&batchNo=${batchNo}&processName=${processName}&assetNo=${assetNo}">尾页</a>    
            </c:when>    
            <c:otherwise>    
                <a href="${pageContext.request.contextPath }/record/DailyCheck.action?pageNow=${page.totalPageCount}&batchNo=${batchNo}&processName=${processName}&assetNo=${assetNo}">尾页</a>    
            </c:otherwise>    
        </c:choose>  
        跳到<select name='topage' size='1' onchange="window.location=' ${pageContext.request.contextPath }/record/DailyCheck.action?pageNow='+this.value+'&batchNo=${batchNo}&processName=${processName}&assetNo=${assetNo}'">
       <c:forEach var="i" begin="1" end="${page.totalPageCount}">
       <c:if test="${i==page.pageNow}"><option value='${i}' selected>${i}</option></c:if>
       <c:if test="${i!=page.pageNow}"><option value='${i}'>${i}</option></c:if>
       </c:forEach></select> 页
    </div>    
    <!-- 分页功能 End -->

</div>
</body>
</html>

