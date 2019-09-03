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
    <title>退辅料记录</title>
    <link href="../css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="../css/bootstrap.min.new.css"/>
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="../css/main.css"/>
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../js/jquery-3.2.1.min.js"></script>  
        <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/bootstrap-datetimepicker.js"></script>
    <script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="../js/layer.js"></script> 
    
	<script type="text/javascript" >	
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
	//删除退辅料
	 function revise(obj){
	 	var id=$(obj).prev().val();
	 	var detailid=$(obj).prev().prev().val();
		location.href="${pageContext.request.contextPath}/record/DeleteOutputSecRecord.action?getMaterialsId="+id+"&detailId="+detailid;
	}
	
	
	//T+模板的excel导出
	function revine(){
	 	var reshopName=document.getElementById("resp").value;
	 	var start_date=document.getElementById("st").value;
	 	var end_date=document.getElementById("et").value;
		location.href="${pageContext.request.contextPath}/record/outportSecMaterialExcel.action?reshopName="+reshopName+"&start_date="+start_date+"&end_date="+end_date;
	}
			
	</script>

</head>
<body>
	<div class="container">
		<h4 class="myClass navbar-left">退辅料记录</h4>
		<form class="form-inline"  id="forms" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/record/toOutputSecMaterialsRecord.action">
		  	&nbsp;
		  	<div class="form-group">
		  		<label for="">车间名称</label>
		   		<input type="text" class="form-control" style="width:120px;" name="reshopName" id="resp" value="${HX.shopName}">
		  	</div>
		  	<div class="form-group">
		  		<label for="">起始日期</label>
		    	<div class="input-group date form_date"  data-date-format="yyyy-mm-dd">
            		<input class="form-control" size="10" type="text" name="start_date" id="st" value="${HX.cailiaoMc}"/>
            		 <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
            		<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
       			</div>
		  	</div>
		  	<div class="form-group">
		  		<label for="">截止日期</label>
		    	<div class="input-group date form_date"  data-date-format="yyyy-mm-dd">
            		<input class="form-control" size="10" type="text" name="end_date" id="et" value="${HX.materialName}"/>
            		 <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
            		<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
       			</div>
		  	</div>
		  &nbsp;&nbsp;
		  <button type="submit" class="btn btn-primary">查询</button>
		   &nbsp;&nbsp;
		 <input class="btn btn-warning" type="button" value="T+辅料入库excel" onclick="revine(this)">
		</form> 
		<br />
		<div class="container">	   
		<div class="table-responsive">
		  	<table class="table">
				<thead>
					<tr>			
						<th>车间名称</th>
						<th>辅料编号</th>
						<th>辅料名称</th>
						<th>规格型号</th>
						<th>数量</th>
						<th>单位</th>
						<th>退辅料人</th>
						<th>接收人</th>
						<th>退辅料时间</th>
						<th>备注</th>
						<th>删除</th>																	
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listoutputsecmaterial}" var="b">
			<!-- 循环输出forEach -->
					<tr>					
						<td>${b.reshopName}</td>
						<td>${b.secMaterialNo}</td>
						<td>${b.secMaterialName}</td>
						<td>${b.type}</td>
						<td>${b.num}</td>
						<td>${b.unit}</td>
						<td>${b.returner}</td>
						<td>${b.receiver}</td>
					    <td>${b.time}</td> 				
						<td>
						<c:if test="${(empty b.remark)||(b.remark==' ')}">无</c:if>
					    <c:if test="${(!empty b.remark)&&(b.remark!=' ')}">${b.remark}</c:if>
					    </td>														
					<td>
					 <input type="hidden" name="detailid" value="${b.detailId}"/>
					 <input type="hidden" name="id" value="${b.getMaterialsId}"/><input class="btn  btn-xs btn-danger" type="button" value="删除" onclick="revise(this)">
					</td>	
					</tr>
					</c:forEach>	
				</tbody>
			</table>
		</div>
	</div>
	
	 <!-- 分页功能 start -->    
    <div align="center" >    
        <font size="2">共 ${page.totalPageCount} 页</font> <font size="2">第    
            ${page.pageNow} 页</font> <a href="${pageContext.request.contextPath}/record/toOutputSecMaterialsRecord.action?pageNow=1&reshopName=${reshopName}&start_date=${start_date}&end_date=${end_date}">首页</a>    
        <c:choose>    
            <c:when test="${page.pageNow - 1 > 0}">    
                <a href="${pageContext.request.contextPath}/record/toOutputSecMaterialsRecord.action?pageNow=${page.pageNow - 1}&reshopName=${reshopName}&start_date=${start_date}&end_date=${end_date}">上一页</a>    
            </c:when>    
            <c:when test="${page.pageNow - 1 <= 0}">    
                <a href="${pageContext.request.contextPath}/record/toOutputSecMaterialsRecord.action?pageNow=1&reshopName=${reshopName}&start_date=${start_date}&end_date=${end_date}">上一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath}/record/toOutputSecMaterialsRecord.action?pageNow=${page.pageNow}&reshopName=${reshopName}&start_date=${start_date}&end_date=${end_date}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 < page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/toOutputSecMaterialsRecord.action?pageNow=${page.pageNow + 1}&reshopName=${reshopName}&start_date=${start_date}&end_date=${end_date}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/toOutputSecMaterialsRecord?pageNow=${page.totalPageCount}&reshopName=${reshopName}&start_date=${start_date}&end_date=${end_date}">下一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath }/record/toOutputSecMaterialsRecord.action?pageNow=${page.pageNow}&reshopName=${reshopName}&start_date=${start_date}&end_date=${end_date}">尾页</a>    
            </c:when>    
            <c:otherwise>    
                <a href="${pageContext.request.contextPath }/record/toOutputSecMaterialsRecord?pageNow=${page.totalPageCount}&reshopName=${reshopName}&start_date=${start_date}&end_date=${end_date}">尾页</a>    
            </c:otherwise>    
        </c:choose>  
        跳到<select name='topage' size='1' onchange="window.location=' ${pageContext.request.contextPath }/record/toOutputSecMaterialsRecord?pageNow='+this.value+'&reshopName=${reshopName}&start_date=${start_date}&end_date=${end_date}'">
       <c:forEach var="i" begin="1" end="${page.totalPageCount}">
       <c:if test="${i==page.pageNow}"><option value='${i}' selected>${i}</option></c:if>
       <c:if test="${i!=page.pageNow}"><option value='${i}'>${i}</option></c:if>
       </c:forEach></select> 页
    </div>    
    <!-- 分页功能 End -->
	
	</div>
	<br />
</body>
</html>

