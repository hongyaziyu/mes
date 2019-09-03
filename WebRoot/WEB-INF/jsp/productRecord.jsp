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
    <title>生产记录</title>
    <link href="../css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="../css/bootstrap.min.new.css"/>
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="../css/main.css"/>
</head>
<div class="container">
    <h4 class="myClass navbar-left">生产记录</h4>
		<form class="form-inline top" method="post" action="${pageContext.request.contextPath}/record/SelectProductionRecordInquiry.action">
		  <div class="form-group">
		  	<label for="exampleInputName2">车间名称</label>
		    <input type="text" class="form-control" name="shop_name" >
		  </div>
		  <div class="form-group">
		  	<label for="exampleInputName2">操作工</label>
		    <input type="text" class="form-control" style="width: 120px" name="operator" >
		  </div>
          <div class="form-group">
		  	<label for="exampleInputName2">图号</label>
		    <input type="text" class="form-control" name="client_material_no" >
		  </div>
		  <div class="form-group">
		  	<label for="exampleInputName2">物料号</label>
		    <input type="text" class="form-control" name="material_no" >
		  </div>&nbsp;&nbsp;&nbsp;&nbsp;
		  <button type="submit" class="btn btn-primary pull-right">查询</button>
		</form>
		<br />
    <div class="table-responsive">
	  <table class="table">
			<thead>
				<tr>
					<th>生产日期</th>
					<th>计划单号</th>
					<th>批次号</th>					
					<th>产品名称</th>
					<th>车间</th>
					<th>工序</th>
					<th>操作工</th>
					<th>合格数量</th>
					<th>次品数量</th>
					<th>设备名称</th>
					<th>设备故障</th>
					<th>磨具名称</th>
					<th>磨具故障</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listproductionRecordInquiry}" var="item">
				<tr  >
					<td>${item.produceDate }</td>					
					<td>${item.planNo }</td>
					<td>${item.batchNo }</td>
					<td>${item.materialName }</td>					
					<td>${item.shopName }</td>
					<td>${item.processName }</td>
					<td>${item.operator }</td>
					<td>${item.hegeNum }</td>
					<td>${item.cipinNum }</td>
					<td>${item.asset }</td>
					<td>${item.assetState }</td>		
					<td>${item.mold }</td>
					<td>${item.moldState }</td>			
				</tr>
				</c:forEach>
			</tbody>
	</table>
	</div>  

<!-- 分页功能 start -->    
    <div align="center" >    
        <font size="2">共 ${page.totalPageCount} 页</font> <font size="2">第    
            ${page.pageNow} 页</font> <a href="${pageContext.request.contextPath}/record/SelectProductionRecordInquiry.action?pageNow=1&shop_name=${shop_name}&operator=${operator}&client_material_no=${client_material_no}&material_no=${material_no}">首页</a>    
        <c:choose>    
            <c:when test="${page.pageNow - 1 > 0}">    
                <a href="${pageContext.request.contextPath}/record/SelectProductionRecordInquiry.action?pageNow=${page.pageNow - 1}&shop_name=${shop_name}&operator=${operator}&client_material_no=${client_material_no}&material_no=${material_no}">上一页</a>    
            </c:when>    
            <c:when test="${page.pageNow - 1 <= 0}">    
                <a href="${pageContext.request.contextPath}/record/SelectProductionRecordInquiry.action?pageNow=1&shop_name=${shop_name}&operator=${operator}&client_material_no=${client_material_no}&material_no=${material_no}">上一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath}/record/SelectProductionRecordInquiry.action?pageNow=${page.pageNow}&shop_name=${shop_name}&operator=${operator}&client_material_no=${client_material_no}&material_no=${material_no}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 < page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/SelectProductionRecordInquiry.action?pageNow=${page.pageNow + 1}&shop_name=${shop_name}&operator=${operator}&client_material_no=${client_material_no}&material_no=${material_no}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/SelectProductionRecordInquiry.action?pageNow=${page.totalPageCount}&shop_name=${shop_name}&operator=${operator}&client_material_no=${client_material_no}&material_no=${material_no}">下一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath }/record/SelectProductionRecordInquiry.action?pageNow=${page.pageNow}&shop_name=${shop_name}&operator=${operator}&client_material_no=${client_material_no}&material_no=${material_no}">尾页</a>    
            </c:when>    
            <c:otherwise>    
                <a href="${pageContext.request.contextPath }/record/SelectProductionRecordInquiry.action?pageNow=${page.totalPageCount}&shop_name=${shop_name}&operator=${operator}&client_material_no=${client_material_no}&material_no=${material_no}">尾页</a>    
            </c:otherwise>    
        </c:choose>  
        跳到<select name='topage' size='1' onchange="window.location=' ${pageContext.request.contextPath }/record/SelectProductionRecordInquiry.action?pageNow='+this.value+'&shop_name=${shop_name}&operator=${operator}&client_material_no=${client_material_no}&material_no=${material_no}'">
       <c:forEach var="i" begin="1" end="${page.totalPageCount}">
       <c:if test="${i==page.pageNow}"><option value='${i}' selected>${i}</option></c:if>
       <c:if test="${i!=page.pageNow}"><option value='${i}'>${i}</option></c:if>
       </c:forEach></select> 页
    </div>    
    <!-- 分页功能 End -->

</div>
  <script src="../js/jquery-3.2.1.min.js"></script>  
  <script src="../js/bootstrap.min.js"></script>
</body>
</html>
