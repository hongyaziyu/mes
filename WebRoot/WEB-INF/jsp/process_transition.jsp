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
    <title>工序交接记录</title>
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
 
 	function revice(obj){
	 	var id=$(obj).prev().val();
		location.href="${pageContext.request.contextPath}/record/toProcessTransitionDetail.action?transitionId="+id;
	} 
	
	function revise(obj){
	 	var id=$(obj).prev().val();
		location.href="${pageContext.request.contextPath}/record/DeleteProcessTransition.action?transitionId="+id;
	}
	
		function revine(){
	 	var provider=document.getElementById("pd").value;
	 	var batchNo=document.getElementById("bn").value;
	 	var shopName=document.getElementById("sn").value;
	 	var start_date=document.getElementById("st").value;
	 	var end_date=document.getElementById("et").value;
		location.href="${pageContext.request.contextPath}/record/toprocessTransitionExcel.action?provider="+provider+"&batchNo="+batchNo+"&shopName="+shopName+"&start_date="+start_date+"&end_date="+end_date;
	}
</script> 

  </head>
  
  <body>
      <div class="container">
	    	<h4 class="myClass navbar-left">工序交接记录</h4>
			<form class="form-inline" method="post" action="${pageContext.request.contextPath}/record/toselectProcessTransition.action">
			&nbsp;
				<div class="form-group">
				  	<label for="equipmentNum">人员</label>
				    <input type="text" class="form-control" name="provider" style="width:85px;" id="pd" value="${HX.provider}">
				</div>
				<div class="form-group">
				  	<label for="equipmentNum">批次号</label>
				    <input type="text" class="form-control" name="batchNo" style="width:150px;" id="bn" value="${HX.batchNo}">
				</div>
				<div class="form-group">
				  	<label for="equipmentName">车间</label>
				    <input type="text" class="form-control" name="shopName" style="width:85px;" id="sn" value="${HX.shop1}">
				</div>
				<div class="form-group">
		  		<label for="">起始</label>
		    	<div class="input-group date form_date"  data-date-format="yyyy-mm-dd" >
            		<input class="form-control" size="10" type="text" name="start_date" value="${HX.shop2}" id="st"/>
            		<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
       			</div>
		  	</div>
		  	<div class="form-group">
		  		<label for="">截止</label>
		    	<div class="input-group date form_date"  data-date-format="yyyy-mm-dd">
            		<input class="form-control" size="10" type="text" name="end_date" value="${HX.tranDate}" id="et"/>
            	<!-- <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span> -->
            		<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
       			</div>
		  	</div>
			    <input class="btn btn-primary" type="submit" name="search" value="查询" />&nbsp;
			   	<input class="btn btn-warning" type="button" value="excel导出" onclick="revine(this)">
		    
			</form>
		<br />
		<div class="table-responsive">
		  	<table class="table">
				<thead>
					<tr>
						<th>计划单号</th>
						<th>批次号</th>
						<th>图号</th>
						<th>物料号</th>
						<th>提供车间</th>
						<th>提供工序</th>					
						<th>提供人</th>
						<th>接收车间</th>
						<th>接收工序</th>
						<th>接收人</th>
						<th>接收日期</th>
						<th>合格品数</th>
						<th>次品数</th>
						<th>查看</th>
						<th>删除</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${listProcessTransition}" var="ProcessTransition">
					<tr>
						<td>${ProcessTransition.planNo }</td>
						<td>${ProcessTransition.batchNo }</td>
						<td>${ProcessTransition.clientMaterialNo }</td>
						<td>${ProcessTransition.materialNo }</td>	
						<td>${ProcessTransition.shopName }</td>
						<td>${ProcessTransition.process1 }</td>					
						<td>${ProcessTransition.provider }</td>
						<td>${ProcessTransition.shopName1 }</td>
						<td>${ProcessTransition.process2 }</td>
						<td>${ProcessTransition.acceptor}</td>
						<td>${ProcessTransition.tranDate }</td>
						<td>${ProcessTransition.qualifiedNum }</td>
						<td>${ProcessTransition.unqualifiedNum }</td>
						<td>
					 	<input type="hidden" name="id" value="${ProcessTransition.transitionId}"/><input class="btn  btn-xs btn-primary" type="button" value="查看" onclick="revice(this)">
						</td>	
						<td>
					 	<input type="hidden" name="id" value="${ProcessTransition.transitionId}"/><input class="btn  btn-xs btn-danger" type="button" value="删除" onclick="revise(this)">
						</td>
					</tr>
					</c:forEach>
				</tbody>	
			</table>
		</div>
		
		<!-- 分页功能 start -->    
    <div align="center" >    
        <font size="2">共 ${page.totalPageCount} 页</font> <font size="2">第    
            ${page.pageNow} 页</font> <a href="${pageContext.request.contextPath}/record/toselectProcessTransition.action?pageNow=1&provider=${provider}&batchNo=${batchNo}&shopName=${shopName}&start_date=${start_date}&end_date=${end_date}">首页</a>    
        <c:choose>    
            <c:when test="${page.pageNow - 1 > 0}">    
                <a href="${pageContext.request.contextPath}/record/toselectProcessTransition.action?pageNow=${page.pageNow - 1}&provider=${provider}&batchNo=${batchNo}&shopName=${shopName}&start_date=${start_date}&end_date=${end_date}">上一页</a>    
            </c:when>    
            <c:when test="${page.pageNow - 1 <= 0}">    
                <a href="${pageContext.request.contextPath}/record/toselectProcessTransition.action?pageNow=1&batchNo=${batchNo}&provider=${provider}&shopName=${shopName}&start_date=${start_date}&end_date=${end_date}">上一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath}/record/toselectProcessTransition.action?pageNow=${page.pageNow}&provider=${provider}&batchNo=${batchNo}&shopName=${shopName}&start_date=${start_date}&end_date=${end_date}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 < page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/toselectProcessTransition.action?pageNow=${page.pageNow + 1}&provider=${provider}&batchNo=${batchNo}&shopName=${shopName}&start_date=${start_date}&end_date=${end_date}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/toselectProcessTransition.action?pageNow=${page.totalPageCount}&provider=${provider}&batchNo=${batchNo}&shopName=${shopName}&start_date=${start_date}&end_date=${end_date}">下一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath }/record/toselectProcessTransition.action?pageNow=${page.pageNow}&provider=${provider}&batchNo=${batchNo}&shopName=${shopName}&start_date=${start_date}&end_date=${end_date}">尾页</a>    
            </c:when>    
            <c:otherwise>    
                <a href="${pageContext.request.contextPath }/record/toselectProcessTransition.action?pageNow=${page.totalPageCount}&provider=${provider}&batchNo=${batchNo}&shopName=${shopName}&start_date=${start_date}&end_date=${end_date}">尾页</a>    
            </c:otherwise>    
        </c:choose>  
        跳到<select name='topage' size='1' onchange="window.location=' ${pageContext.request.contextPath }/record/toselectProcessTransition.action?pageNow='+this.value+'&provider=${provider}&batchNo=${batchNo}&shopName=${shopName}&start_date=${start_date}&end_date=${end_date}'">
       <c:forEach var="i" begin="1" end="${page.totalPageCount}">
       <c:if test="${i==page.pageNow}"><option value='${i}' selected>${i}</option></c:if>
       <c:if test="${i!=page.pageNow}"><option value='${i}'>${i}</option></c:if>
       </c:forEach></select> 页
    </div>    
    <!-- 分页功能 End -->
		
	</div>
  </body>
</html>
