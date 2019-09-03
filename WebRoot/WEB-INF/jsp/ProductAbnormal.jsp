<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="headandfoot.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
    <title>产品异常单记录</title>
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

	</script>
    
    
    <script type="text/javascript" >
	function revise(obj){
	 	var id=$(obj).prev().val();
		location.href="${pageContext.request.contextPath}/record/getProductAbnormal.action?abnormalId="+id;
	}
	</script>



  </head>
  
  <body>
      <div class="container">
	    	<h4 class="myClass navbar-left">产品异常单记录</h4>
			<form class="form-inline top" method="post" action="${pageContext.request.contextPath}/record/selectProductAbnormal.action">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<div class="form-group">
				  	<label for="equipmentNum">客户</label>
				    <input type="text" class="form-control" name="client" style="width:120px;">
				</div>
				<div class="form-group">
				  	<label for="equipmentName">物料号</label>
				    <input type="text" class="form-control" name="material_no" style="width:120px;">
				</div>
				<div class="form-group">
		  		<label for="">起始日期</label>
		    	<div class="input-group date form_date"  data-date-format="yyyy-mm-dd" >
            		<input class="form-control" size="10" type="text" name="start_date" value="" id="st"/>
            		 <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
            		<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
       			</div>
		  	</div>
		  	<div class="form-group">
		  		<label for="">截止日期</label>
		    	<div class="input-group date form_date"  data-date-format="yyyy-mm-dd">
            		<input class="form-control" size="10" type="text" name="end_date" value="" id="et"/>
            		 <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
            		<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
       			</div>
		  	</div>
			    &nbsp;&nbsp;&nbsp;&nbsp;
			    <input class="btn btn-primary" type="submit" name="search" value="查询" />
			    &nbsp;&nbsp;&nbsp;&nbsp;	
			   		    
			</form>
		<br />
		<div class="table-responsive">
		  	<table class="table">
				<thead>
					<tr>
						<th>客户</th>
						<th>图号</th>
						<th>物料号</th>
						<th>计划单号</th>
						<th>批次号</th>
						<th>产品名称</th>
						<th>产品规格</th>					
						<th>内部/外部</th>
						<th>制定时间</th>
						<th>异常数量</th>
						<th>填报人</th>
						<th>修改</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${listProductAbnormal}" var="product_abnormal">
					<tr>
						<td>${product_abnormal.client }</td>
						<td>${product_abnormal.clientMaterialNo }</td>
						<td>${product_abnormal.materialNo }</td>
						<td>${product_abnormal.planNo }</td>
						<td>${product_abnormal.batchNo }</td>	
						<td>${product_abnormal.productName }</td>
						<td>${product_abnormal.productSpecfic }</td>					
						<td>${product_abnormal.site }</td>
						<td>${product_abnormal.produceDate }</td>
						<td>${product_abnormal.abnormalNum}</td>
						<td>${product_abnormal.sendPerson }</td>
						<td> <input type="hidden" name="id" value="${product_abnormal.abnormalId}"/><input class="btn  btn-xs btn-primary" type="button" value="修改" onclick="revise(this)"></td>
					</tr>
					</c:forEach>
				</tbody>	
			</table>
		</div>
		
		<!-- 分页功能 start -->    
    <div align="center" >    
        <font size="2">共 ${page.totalPageCount} 页</font> <font size="2">第    
            ${page.pageNow} 页</font> <a href="${pageContext.request.contextPath}/record/selectProductAbnormal.action?pageNow=1&client=${client}&material_no=${material_no}&start_date=${start_date}&end_date=${end_date}">首页</a>    
        <c:choose>    
            <c:when test="${page.pageNow - 1 > 0}">    
                <a href="${pageContext.request.contextPath}/record/selectProductAbnormal.action?pageNow=${page.pageNow - 1}&client=${client}&material_no=${material_no}&start_date=${start_date}&end_date=${end_date}">上一页</a>    
            </c:when>    
            <c:when test="${page.pageNow - 1 <= 0}">    
                <a href="${pageContext.request.contextPath}/record/selectProductAbnormal.action?pageNow=1&client=${client}&material_no=${material_no}&start_date=${start_date}&end_date=${end_date}">上一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath}/record/selectProductAbnormal.action?pageNow=${page.pageNow}&client=${client}&material_no=${material_no}&start_date=${start_date}&end_date=${end_date}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 < page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/selectProductAbnormal.action?pageNow=${page.pageNow + 1}&client=${client}&material_no=${material_no}&start_date=${start_date}&end_date=${end_date}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/selectProductAbnormal.action?pageNow=${page.totalPageCount}&client=${client}&material_no=${material_no}&start_date=${start_date}&end_date=${end_date}">下一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath }/record/selectProductAbnormal.action?pageNow=${page.pageNow}&client=${client}&material_no=${material_no}&start_date=${start_date}&end_date=${end_date}">尾页</a>    
            </c:when>    
            <c:otherwise>    
                <a href="${pageContext.request.contextPath }/record/selectProductAbnormal.action?pageNow=${page.totalPageCount}&client=${client}&material_no=${material_no}&start_date=${start_date}&end_date=${end_date}">尾页</a>    
            </c:otherwise>    
        </c:choose>  
        跳到<select name='topage' size='1' onchange="window.location=' ${pageContext.request.contextPath }/record/selectProductAbnormal.action?pageNow='+this.value+'&client=${client}&material_no=${material_no}&start_date=${start_date}&end_date=${end_date}'">
       <c:forEach var="i" begin="1" end="${page.totalPageCount}">
       <c:if test="${i==page.pageNow}"><option value='${i}' selected>${i}</option></c:if>
       <c:if test="${i!=page.pageNow}"><option value='${i}'>${i}</option></c:if>
       </c:forEach></select> 页
    </div>    
    <!-- 分页功能 End -->
		
	</div>
  </body>
</html>
