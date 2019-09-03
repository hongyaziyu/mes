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
    <title>领料审批记录</title>
    <link href="../css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="../css/bootstrap.min.new.css"/>
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="../css/main.css"/>
</head>
<div class="container">
    <h4 class="myClass navbar-left">领料审批记录</h4>
		<form class="form-inline top" method="post" action="${pageContext.request.contextPath}/record/SelectMaterial.action" >
		  <div class="form-group">
		  	<label for="exampleInputName2">物料号</label>
		    <input type="text" class="form-control" id="exampleInputName2" name="material_no">
		  </div>
		  <label for="exampleInputName2">起始日期</label>
		  <div class="input-group date form_date"  data-date-format="yyyy-mm-dd">
              <input class="form-control" size="16" type="text" name="start_date"> 
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
          </div> 
          <label for="exampleInputName2">截止日期</label>
          <div class="input-group date form_date"  data-date-format="yyyy-mm-dd">
              <input class="form-control" size="16" type="text" name="end_date"> 
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
          </div> 
          &nbsp;
          <button type="submit" class="btn btn-primary">查询</button>
           &nbsp;&nbsp;&nbsp;
		</form>
		<br />
    <div class="table-responsive ">
	  <table class="table">
			<thead>
				<tr>
					<th>图号</th>
					<th>物料号</th>
					<th>计划单号</th>
					<th>批次号</th>
					<th>产品名称</th>
					<th>产品规格</th>
					<th>申请人</th>
					<th>申请日期</th>
					<th>是否审批</th>
					<th>审批人</th>
					<th>审批日期</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${listmaterial}" var="selectmaterial">
			<!-- 循环输出forEach -->
				<tr>	
					<th>${selectmaterial.clientMaterialNo}</th>
					<td>${selectmaterial.materialNo}</td>
					<td>${selectmaterial.planNo}</td>
					<td>${selectmaterial.batchNo}</td>
					<td>${selectmaterial.materialName}</td>
					<td>${selectmaterial.productSpec}</td>
					<td>${selectmaterial.applyPeople}</td>
					<td>${selectmaterial.applyDate}</td>
					<td>
					<c:if test="${selectmaterial.isApproval=='0'}"><p style="color:blue;">未审批</p></c:if>
					<c:if test="${selectmaterial.isApproval=='1'}"><p style="color:green;">已审批</p></c:if>
					<c:if test="${selectmaterial.isApproval=='2'}"><p style="color:red;">审批不通过</p></c:if>
					</td>
					<td>
					<!-- 判断审批人数据库中是否为null或‘ ’ -->
					<c:if test="${(empty selectmaterial.approver)||(selectmaterial.approver==' ')}">———</c:if>
					<c:if test="${(!empty selectmaterial.approver)&&(selectmaterial.approver!=' ')}">${selectmaterial.approver}</c:if>
					</td>
					<td>
					<!-- 判断审批日期数据库中是否为null或‘ ’ -->
					<c:if test="${(empty selectmaterial.approvalDate)||(selectmaterial.approvalDate==' ')}">————</c:if>
					<c:if test="${(!empty selectmaterial.approvalDate)&&(selectmaterial.approvalDate!=' ')}">${selectmaterial.approvalDate}</c:if>
					</td>
					<td>
					 <input type="hidden" name="id" value="${selectmaterial.getMaterialId}"/><input class="btn  btn-xs btn-primary" type="button" value="查看" onclick="revise(this)">
					</td>
				</tr>
			
				</c:forEach>
			</tbody>
	</table>
	<br />
	</div> 
	<div id="div" class="modal fade" tabindex="-1" >
	
        	
     </div>  
     
     <!-- 分页功能 start -->    
    <div align="center" >    
        <font size="2">共 ${page.totalPageCount} 页</font> <font size="2">第    
            ${page.pageNow} 页</font> <a href="${pageContext.request.contextPath}/record/SelectMaterial.action?pageNow=1&material_no=${material_no}&start_date=${start_date}&end_date=${end_date}">首页</a>    
        <c:choose>    
            <c:when test="${page.pageNow - 1 > 0}">    
                <a href="${pageContext.request.contextPath}/record/SelectMaterial.action?pageNow=${page.pageNow - 1}&material_no=${material_no}&start_date=${start_date}&end_date=${end_date}">上一页</a>    
            </c:when>    
            <c:when test="${page.pageNow - 1 <= 0}">    
                <a href="${pageContext.request.contextPath}/record/SelectMaterial.action?pageNow=1&material_no=${material_no}&start_date=${start_date}&end_date=${end_date}">上一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath}/record/SelectMaterial.action?pageNow=${page.pageNow}&material_no=${material_no}&start_date=${start_date}&end_date=${end_date}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 < page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/SelectMaterial.action?pageNow=${page.pageNow + 1}&material_no=${material_no}&start_date=${start_date}&end_date=${end_date}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/SelectMaterial.action?pageNow=${page.totalPageCount}&material_no=${material_no}&start_date=${start_date}&end_date=${end_date}">下一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath }/record/SelectMaterial.action?pageNow=${page.pageNow}&material_no=${material_no}&start_date=${start_date}&end_date=${end_date}">尾页</a>    
            </c:when>    
            <c:otherwise>    
                <a href="${pageContext.request.contextPath }/record/SelectMaterial.action?pageNow=${page.totalPageCount}&material_no=${material_no}&start_date=${start_date}&end_date=${end_date}">尾页</a>    
            </c:otherwise>    
        </c:choose>  
        跳到<select name='topage' size='1' onchange="window.location=' ${pageContext.request.contextPath }/record/SelectMaterial.action?pageNow='+this.value+'&material_no=${material_no}&start_date=${start_date}&end_date=${end_date}'">
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
	
</script>
<script type="text/javascript" >//$(document).ready(function(){
	function revise(obj){
	 	var id=$(obj).prev().val();
		location.href="${pageContext.request.contextPath}/record/lookMaterial.action?getMaterialId="+id;
	}
	
</script>
  
</body>
</html>