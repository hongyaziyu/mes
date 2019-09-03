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
   
    <title>辅料管理</title>
    <script src="../js/jquery-3.2.1.min.js"></script>  
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/bootstrap-datetimepicker.js"></script>
    <script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
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
		location.href="${pageContext.request.contextPath}/manage/updateSec.action?materialsId="+id;
	}
	
	function revice(obj){
	 	var id=$(obj).prev().val();
		location.href="${pageContext.request.contextPath}/manage/deleteSec.action?materialsId="+id;
	}
	
	
	//excel导入
	 function excel() {
   
	 $.ajax({
					url : "${pageContext.request.contextPath}/manage/importSecExcel.action",
					type : "POST",
					data:new FormData(document.getElementById("forms")),
					processData :false,
					contentType :false,
					success : function(data) {
					if(data==1){
					layer.alert("辅料表excel导入成功!", {
		                skin: 'layui-layer-molv', //样式类名
		                closeBtn: 0
		            }, function(){
		               layer.closeAll();
		            });
		            }else if(data==0){
		            	layer.alert("辅料表excel导入失败!", {
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
								layer.alert("辅料表excel导入失败!", {
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
    <h4 class="myClass navbar-left">辅料管理</h4>
   
		<form id="forms" class="form-inline "  method="post" enctype="multipart/form-data"  action="${pageContext.request.contextPath}/manage/toSecMaterial.action">
		   &nbsp; &nbsp; &nbsp; &nbsp;
		  
		  <div class="form-group">
		  	<label for="secMaterialsName">辅料名称</label>
		    <input type="text" style="width: 120px" name="secMaterialsName" class="form-control" >
		  </div>
		  <div class="form-group">
		  	<label for="type">规格型号</label>
		    <input type="text" style="width: 150px" name="type" class="form-control" >
		  </div>
		  &nbsp;
		  
		  <button  type="submit" class="btn btn-primary">查询</button>
		  
		  &nbsp;
		  	<!--以下第一行为添加按钮实现的网页的跳转-->	  
		  <a class="btn btn-primary"  href="${pageContext.request.contextPath}/manage/toinsertSec.action" >添加</a> 
		   <!-- excel导入文件action执行语句导入excel的语句 -->
			 
			<%--  &nbsp; &nbsp; &nbsp;
			       <a  class="btn btn-danger" href="${pageContext.request.contextPath}/manage/SecSample.action" ><span class="glyphicon glyphicon-save"></span>&nbsp;&nbsp;生成辅料表模板</a> 
			       <input type="file" id="excelPath" name="excelPath" class="form-control" style="width:180px;"/>
			      <input type="button" id="newmj" value="导入Excel" class="btn btn-primary" onclick="excel()" /> --%>
			       
		
		</form>
		<br />
	<div class="table-responsive">
	  <table class="table">
			<thead>
				<tr>
				    
				    <th>辅料名称</th>
					<th>规格型号</th>					
					<th>库存数量</th>
					<th>主计量</th>
					<th>查看详情</th>
					<!-- <th>删除</th> -->
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${listSec}" var="sec">
			<!-- 循环输出forEach -->
				<tr>  
					<td>${sec.secMaterialsName}</td>
					<td>${sec.type }</td>
					<td>${sec.number}</td>
					<td>${sec.unit }</td>
					
					<td>
					<input type="hidden" name="id" value="${sec.materialsId}"/><input class="btn  btn-xs btn-primary" type="button" value="查看详情" onclick="revise(this)"> 
					</td>
					<%-- <td>
					<input type="hidden" name="id" value="${sec.materialsId}"/><input class="btn  btn-xs btn-primary" type="button" value="删除" onclick="revice(this)">
					</td> --%>
				</tr>
				</c:forEach>
			</tbody>
			</table>	
</div>

<!-- 分页功能 start -->    
    <div align="center" >    
        <font size="2">共 ${page.totalPageCount} 页</font> <font size="2">第    
            ${page.pageNow} 页</font> <a href="${pageContext.request.contextPath}/manage/toSecMaterial.action?pageNow=1&secMaterialsName=${secMaterialsName}&type=${type}">首页</a>    
        <c:choose>  
            <c:when test="${page.pageNow - 1 > 0}">    
                <a href="${pageContext.request.contextPath}/manage/toSecMaterial.action?pageNow=${page.pageNow - 1}&secMaterialsName=${secMaterialsName}&type=${type}">上一页</a>    
            </c:when>    
            <c:when test="${page.pageNow - 1 <= 0}">    
                <a href="${pageContext.request.contextPath}/manage/toSecMaterial.action?pageNow=1&secMaterialsName=${secMaterialsName}&type=${type}">上一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath}/manage/toSecMaterial.action?pageNow=${page.pageNow}&secMaterialsName=${secMaterialsName}&type=${type}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 < page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/manage/toSecMaterial.action?pageNow=${page.pageNow + 1}&secMaterialsName=${secMaterialsName}&type=${type}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/manage/toSecMaterial.action?pageNow=${page.totalPageCount}&secMaterialsName=${secMaterialsName}&type=${type}">下一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath }/manage/toSecMaterial.action?pageNow=${page.pageNow}&secMaterialsName=${secMaterialsName}&type=${type}">尾页</a>    
            </c:when>    
            <c:otherwise>    
                <a href="${pageContext.request.contextPath }/manage/toSecMaterial.action?pageNow=${page.totalPageCount}&secMaterialsName=${secMaterialsName}&type=${type}">尾页</a>    
            </c:otherwise>    
        </c:choose>  
        跳到<select name='topage' size='1' onchange="window.location=' ${pageContext.request.contextPath }/manage/toSecMaterial.action?pageNow='+this.value+'&secMaterialsName=${secMaterialsName}&type=${type}'">
       <c:forEach var="i" begin="1" end="${page.totalPageCount}">
       <c:if test="${i==page.pageNow}"><option value='${i}' selected>${i}</option></c:if>
       <c:if test="${i!=page.pageNow}"><option value='${i}'>${i}</option></c:if>
       </c:forEach></select> 页
    </div>    
    <!-- 分页功能 End -->

</div>
</body>
</html>

