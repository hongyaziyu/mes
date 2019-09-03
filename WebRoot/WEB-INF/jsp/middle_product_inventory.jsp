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
   
    <title>半成品库存管理</title>
    <script src="../js/jquery-3.2.1.min.js"></script>  
    <script src="../js/bootstrap.min.js"></script>
      <script type="text/javascript" src="../js/layer.js"></script>
    
    <!-- 文件上传选项css优化 -->
    <style type="text/css">
  #excelPath {
    position: relative;
    display: inline-block;
    background: #D0EEFF;
    border: 1px solid #99D3F5;
    border-radius: 4px;
    padding: 4px 12px;
    overflow: hidden;
    color: #1E88C7;
    text-decoration: none;
    text-indent: 0;
    line-height: 20px;
}

    </style> 
    
    
	<script type="text/javascript" >//$(document).ready(function(){
	function revise(obj){
	 	var id=$(obj).prev().val();
		location.href="${pageContext.request.contextPath}/manage/updateMiddleInventory.action?id="+id;
	}
	
	function revice(obj){
	 	var id=$(obj).prev().val();
		location.href="${pageContext.request.contextPath}/manage/deleteMiddleInventory.action?id="+id;
	} 
	
	//excel导入
	 function excel() {
   
	 $.ajax({
					url : "${pageContext.request.contextPath}/manage/importMiddleInventoryExcel.action",
					type : "POST",
					data:new FormData(document.getElementById("forms")),
					processData :false,
					contentType :false,
					success : function(data) {
					if(data==1){
					layer.alert("半成品库存表excel导入成功!", {
		                skin: 'layui-layer-molv', //样式类名
		                closeBtn: 0
		            }, function(){
		               layer.closeAll();
		            });
		            }else if(data==0){
		            	layer.alert("半成品库存表excel导入失败!", {
		                skin: 'layui-layer-molv', //样式类名
		                closeBtn: 0
		            }, function(){
		               layer.closeAll();
		            });
		            }else if(data==2){
		            	layer.alert("您的excel表格有内容为空,请填写完整!", {
		                skin: 'layui-layer-molv', //样式类名
		                closeBtn: 0
		            }, function(){
		               layer.closeAll();
		            });
		            } else if(data==3){
		            	layer.alert("选择的excel错误!请注意导入excel名称!", {
		                skin: 'layui-layer-molv', //样式类名
		                closeBtn: 0
		            }, function(){
		               layer.closeAll();
		            });
		            } 
					else{
		            	layer.alert("抱歉!没有访问权限...", {
		                skin: 'layui-layer-molv', //样式类名
		                closeBtn: 0
		            }, function(){
		               layer.closeAll();
		            });
		            }
				    
					},
					error : function() {
								layer.alert("半成品库存表excel导入失败!", {
				                skin: 'layui-layer-molv', //样式类名
				                closeBtn: 0
				            }, function(){
				               layer.closeAll();
				            });
				    }
				});

}                                                              
</script>
	
</head>
<body>

<div class="container">
    <h4 class="myClass navbar-left">半成品库存管理</h4>
   
	<form id="forms" class="form-inline"  method="post" enctype="multipart/form-data"  action="${pageContext.request.contextPath}/manage/toMiddleInventory.action">
		   &nbsp; &nbsp; &nbsp; &nbsp;
		  <input type="hidden" name="id" value=""/>
		  <div class="form-group">
		  	<label for="equipmentName">物料号</label>
		    <input type="text" style="width: 120px" name="materialNo" class="form-control">
		  </div>
		  <div class="form-group">
		  	<label for="equipmentNum">产品名称</label>
		    <input type="text" style="width: 120px" name="productName" class="form-control">
		  </div>
		  &nbsp;
		  
		  <button  type="submit" class="btn btn-primary">查询</button>
		  
		
		  	<!--以下第一行为添加按钮实现的网页的跳转-->	  
		  <a class="btn btn-primary"  href="${pageContext.request.contextPath}/manage/toaddMiddleInventory.action">添加</a> 
		   <!-- excel导入文件action执行语句导入excel的语句 -->
			 &nbsp; 
			       <a  class="btn btn-danger" href="${pageContext.request.contextPath}/manage/MiddleroductionSample.action" ><span class="glyphicon glyphicon-save"></span>&nbsp;&nbsp;生成半成库存表模板</a> 
			       <input type="file" id="excelPath" name="excelPath" class="form-control" style="width:160px;"/>
			      <input type="button" id="newmj" value="导入Excel" class="btn btn-primary" onclick="excel()" />
		</form>
		<br />
	<div class="table-responsive">
	  <table class="table">
			<thead>
				<tr>
				    <th>物料号</th>
					<th>产品名称</th>					
					<th>规格型号</th>
					<th>库存数量</th>
					<th>单位</th>
					<th>修改</th>
					<th>删除</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${listMiddleProduct}" var="MiddleProduction">
			<!-- 循环输出forEach -->
				<tr>  
					<td>${MiddleProduction.materialNo}</td>
					<td>${MiddleProduction.productName}</td>
					<td>${MiddleProduction.type}</td>
					<td>${MiddleProduction.num}</td>
					<td>${MiddleProduction.unit}</td>
					<td>
					<input type="hidden" name="id" value="${MiddleProduction.id}"/>
					<input class="btn  btn-xs btn-primary" type="button" value="修改" onclick="revise(this)"> 
					</td>
					<td>
					 <input type="hidden" name="id" value="${MiddleProduction.id}"/>
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
            ${page.pageNow} 页</font> <a href="${pageContext.request.contextPath}/manage/toMiddleInventory.action?pageNow=1&materialNo=${materialNo}&productName=${productName}">首页</a>    
        <c:choose>  
            <c:when test="${page.pageNow - 1 > 0}">    
                <a href="${pageContext.request.contextPath}/manage/toMiddleInventory.action?pageNow=${page.pageNow - 1}&materialNo=${materialNo}&productName=${productName}">上一页</a>    
            </c:when>    
            <c:when test="${page.pageNow - 1 <= 0}">    
                <a href="${pageContext.request.contextPath}/manage/toMiddleInventory.action?pageNow=1&materialNo=${materialNo}&productName=${productName}">上一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath}/manage/toMiddleInventory.action?pageNow=${page.pageNow}&materialNo=${materialNo}&productName=${productName}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 < page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/manage/toMiddleInventory.action?pageNow=${page.pageNow + 1}&materialNo=${materialNo}&productName=${productName}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/manage/toMiddleInventory.action?pageNow=${page.totalPageCount}&materialNo=${materialNo}&productName=${productName}">下一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath }/manage/toMiddleInventory.action?pageNow=${page.pageNow}&materialNo=${materialNo}&productName=${productName}">尾页</a>    
            </c:when>    
            <c:otherwise>    
                <a href="${pageContext.request.contextPath }/manage/toMiddleInventory.action?pageNow=${page.totalPageCount}&materialNo=${materialNo}&productName=${productName}">尾页</a>    
            </c:otherwise>    
        </c:choose>  
        跳到<select name='topage' size='1' onchange="window.location=' ${pageContext.request.contextPath }/manage/toMiddleInventory.action?pageNow='+this.value+'&materialNo=${materialNo}&productName=${productName}'">
       <c:forEach var="i" begin="1" end="${page.totalPageCount}">
       <c:if test="${i==page.pageNow}"><option value='${i}' selected>${i}</option></c:if>
       <c:if test="${i!=page.pageNow}"><option value='${i}'>${i}</option></c:if>
       </c:forEach></select> 页
    </div>    
    <!-- 分页功能 End -->

</div>
</body>
</html>

