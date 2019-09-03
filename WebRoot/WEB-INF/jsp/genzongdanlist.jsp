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
    <title>跟踪单记录</title>
    <link href="../css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="../css/bootstrap.min.new.css"/>
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="../css/main.css"/>
    </head>
<div class="container">
     <h4 class="myClass navbar-left">跟踪单记录</h4>
		<form class="form-inline top"  action="${pageContext.request.contextPath}/record/getTrackCard.action">
		  <div class="form-group">
		  	<label for="exampleInputName2">客户</label>
		    <input type="text" class="form-control" id="client" name="client" >
		  </div>
		  <div class="form-group">
		  	<label for="exampleInputName2">计划单号</label>
		    <input type="text" class="form-control" id="plan_no" name="plan_no" >
		  </div>
          <div class="form-group">
		  	<label for="exampleInputName2">图号</label>
		    <input type="text" class="form-control" id="client_material_no" name="client_material_no" >
		  </div>
		    <div class="form-group">
		  	<label for="exampleInputName2">物料号</label>
		    <input type="text" class="form-control" id="material_no" name="material_no"  >
		  </div>
		    <button type="submit" class="btn btn-primary ">查询</button>
		</form>
		<br />
    <div class="table-responsive">
	  <table class="table">
			<thead>
				<tr>
					<th>客户</th>
					<th>计划单号</th>
					<th>图号</th>
					<th>物料号</th>
					<th>批次号</th>
					<th>产品名称</th>
					<th>产品规格</th>
					<th>修改</th>
					<th>删除</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listTrackCard}" var="track_card">
					<tr>
						<td>${track_card.client }</td>
						<td>${track_card.planNo }</td>					
						<td>${track_card.clientMaterialNo }</td>
						<td>${track_card.materialNo }</td>
						<td>${track_card.batchNo }</td>
						<td>${track_card.materialName }</td>
						<td>${track_card.productSpec }</td>
						<td> <input type="hidden" name="id" value="${track_card.cardId}"/><input class="btn  btn-xs btn-primary" type="button" value="修改" onclick="revise(this)"></td>
						<td> <input type="hidden" name="id" value="${track_card.cardId}"/><input class="btn  btn-xs btn-danger" type="button" value="删除" onclick="revice(this)"></td>
					</tr>
					</c:forEach>
				</tbody>	
	</table>
	</div> 
	   	<!-- 分页功能 start -->    
    <div align="center" >    
        <font size="2">共 ${page.totalPageCount} 页</font> <font size="2">第    
            ${page.pageNow} 页</font> <a href="${pageContext.request.contextPath}/record/getTrackCard.action?pageNow=1&client=${client}&plan_no=${plan_no}&client_material_no=${client_material_no}&material_no=${material_no}">首页</a>    
        <c:choose>    
            <c:when test="${page.pageNow - 1 > 0}">    
                <a href="${pageContext.request.contextPath}/record/getTrackCard.action?pageNow=${page.pageNow - 1}&client=${client}&plan_no=${plan_no}&client_material_no=${client_material_no}&material_no=${material_no}">上一页</a>    
            </c:when>    
            <c:when test="${page.pageNow - 1 <= 0}">    
                <a href="${pageContext.request.contextPath}/record/getTrackCard.action?pageNow=1&client=${client}&plan_no=${plan_no}&client_material_no=${client_material_no}&material_no=${material_no}">上一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath}/record/getTrackCard.action?pageNow=${page.pageNow}&client=${client}&plan_no=${plan_no}&client_material_no=${client_material_no}&material_no=${material_no}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 < page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/getTrackCard.action?pageNow=${page.pageNow + 1}&client=${client}&plan_no=${plan_no}&client_material_no=${client_material_no}&material_no=${material_no}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/getTrackCard.action?pageNow=${page.totalPageCount}&client=${client}&plan_no=${plan_no}&client_material_no=${client_material_no}&material_no=${material_no}">下一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath }/record/getTrackCard.action?pageNow=${page.pageNow}&client=${client}&plan_no=${plan_no}&client_material_no=${client_material_no}&material_no=${material_no}">尾页</a>    
            </c:when>    
            <c:otherwise>    
                <a href="${pageContext.request.contextPath }/record/getTrackCard.action?pageNow=${page.totalPageCount}&client=${client}&plan_no=${plan_no}&client_material_no=${client_material_no}&material_no=${material_no}">尾页</a>    
            </c:otherwise>    
        </c:choose>  
        跳到<select name='topage' size='1' onchange="window.location=' ${pageContext.request.contextPath }/record/getTrackCard.action?pageNow='+this.value+'&client=${client}&plan_no=${plan_no}&client_material_no=${client_material_no}&material_no=${material_no}'">
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
		var id=$(obj).prev().val();
		location.href="${pageContext.request.contextPath}/record/UpdateTrackCard.action?cardId="+id;
	}
	
	function revice(obj){
		var id=$(obj).prev().val();
		location.href="${pageContext.request.contextPath}/record/DeleteTrackCard.action?cardId="+id;
	}
</script>
    
</body>
</html>