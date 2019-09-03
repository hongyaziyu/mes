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
   
    <title>Spc记录</title>
    <script src="../js/jquery-3.2.1.min.js"></script>  
    <script src="../js/bootstrap.min.js"></script>
    
	<script type="text/javascript" >//$(document).ready(function(){
	function revise(obj){
	 	var batchNo=$(obj).parent().prev().prev().prev().prev().prev().prev().prev().prev().html();
	 	var process=$(obj).parent().prev().prev().prev().prev().prev().prev().html();
	 	var characterVal=$(obj).parent().prev().prev().prev().prev().prev().html();
		location.href="${pageContext.request.contextPath}/record/toupdateSpcrecord.action?batchNo="+batchNo+"&process="+process+"&characterVal="+characterVal;
	}
	                                                              
</script>
	
</head>
<body>

<div class="container">
    <h4 class="myClass navbar-left">Spc记录</h4>
   
		<form id="forms" class="form-inline "  method="post"   action="${pageContext.request.contextPath}/record/toSpcrecord.action">
		   &nbsp; &nbsp; &nbsp; &nbsp;
		 
		  <div class="form-group">
		  	<label for="equipmentName">物料号</label>
		    <input type="text" style="width: 120px" name="materialNo" class="form-control" id="equipmentName">
		  </div>
		  <div class="form-group">
		  	<label for="equipmentNum">批次号</label>
		    <input type="text" style="width: 120px" name="batchNo" class="form-control" id="equipmentNum">
		  </div>
		  <div class="form-group">
		  	<label for="equipmentNum">工序</label>
		    <input type="text" style="width: 120px" name="process" class="form-control" id="equipmentNum">
		  </div>
		  <div class="form-group">
		  	<label for="equipmentNum">特征值</label>
		    <input type="text" style="width: 100px" name="characterVal" class="form-control" id="equipmentNum">
		  </div>
		  &nbsp;
		  
		  <button  type="submit" class="btn btn-primary">查询</button>  
		</form>
		<br />
	<div class="table-responsive">
	  <table class="table">
			<thead>
				<tr>
				    <th>图号</th>
					<th>物料号</th>
					<th>批次号</th>
					<th>产品名称</th>
					<th>工序</th>
					<th>特征值</th>
					<th>采集时间(起始日期-截止日期)</th>	
					<th>中值</th>
					<th>上公差限</th>
					<th>下公差限</th>					
					<th>修改</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${listSpcrecord}" var="Spc">
			<!-- 循环输出forEach -->
				<tr>  
					<td>${Spc.clientMaterialNo}</td>
					<td>${Spc.materialNo}</td>
					<td>${Spc.batchNo}</td>
					<td>${Spc.productName}</td>
					<td>${Spc.processName}</td>
					<td>${Spc.characterVal}</td>
					<td>${Spc.makeStartDate}—${Spc.makeEndDate}</td>
					<td>${Spc.mid}</td>
					<td>${Spc.usl}</td>
					<td>${Spc.lsl}</td>
					<td>
					<input class="btn  btn-xs btn-primary" type="button" value="修改" onclick="revise(this)"> 
					</td>
				</tr>
				</c:forEach>
			</tbody>
			</table>	
</div>

<!-- 分页功能 start -->    
    <div align="center" >    
        <font size="2">共 ${page.totalPageCount} 页</font> <font size="2">第    
            ${page.pageNow} 页</font> <a href="${pageContext.request.contextPath}/record/toSpcrecord.action?pageNow=1&materialNo=${materialNo}&batchNo=${batchNo}&process=${process}&characterVal=${characterVal}">首页</a>    
        <c:choose>  
            <c:when test="${page.pageNow - 1 > 0}">    
                <a href="${pageContext.request.contextPath}/record/toSpcrecord.action?pageNow=${page.pageNow - 1}&materialNo=${materialNo}&batchNo=${batchNo}&process=${process}&characterVal=${characterVal}">上一页</a>    
            </c:when>    
            <c:when test="${page.pageNow - 1 <= 0}">    
                <a href="${pageContext.request.contextPath}/record/toSpcrecord.action?pageNow=1&materialNo=${materialNo}&batchNo=${batchNo}&process=${process}&characterVal=${characterVal}">上一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath}/record/toSpcrecord.action?pageNow=${page.pageNow}&materialNo=${materialNo}&batchNo=${batchNo}&process=${process}&characterVal=${characterVal}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 < page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/toSpcrecord.action?pageNow=${page.pageNow + 1}&materialNo=${materialNo}&batchNo=${batchNo}&process=${process}&characterVal=${characterVal}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/toSpcrecord.action?pageNow=${page.totalPageCount}&materialNo=${materialNo}&batchNo=${batchNo}&process=${process}&characterVal=${characterVal}">下一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath }/record/toSpcrecord.action?pageNow=${page.pageNow}&materialNo=${materialNo}&batchNo=${batchNo}&process=${process}&characterVal=${characterVal}">尾页</a>    
            </c:when>    
            <c:otherwise>    
                <a href="${pageContext.request.contextPath }/record/toSpcrecord?pageNow=${page.totalPageCount}&materialNo=${materialNo}&batchNo=${batchNo}&process=${process}&characterVal=${characterVal}">尾页</a>    
            </c:otherwise>    
        </c:choose>  
        跳到<select name='topage' size='1' onchange="window.location=' ${pageContext.request.contextPath }/record/toSpcrecord.action?pageNow='+this.value+'&materialNo=${materialNo}&batchNo=${batchNo}&process=${process}&characterVal=${characterVal}'">
       <c:forEach var="i" begin="1" end="${page.totalPageCount}">
       <c:if test="${i==page.pageNow}"><option value='${i}' selected>${i}</option></c:if>
       <c:if test="${i!=page.pageNow}"><option value='${i}'>${i}</option></c:if>
       </c:forEach></select> 页
    </div>    
    <!-- 分页功能 End -->

</div>
</body>
</html>

