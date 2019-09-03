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
   
    <title>设备管理</title>
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
		location.href="${pageContext.request.contextPath}/manage/updateAsset.action?assetId="+id;
	}
	
	function revice(obj){
	 	var id=$(obj).prev().val();
		location.href="${pageContext.request.contextPath}/manage/deleteAsset.action?assetId="+id;
	}
	
	
	//excel导入
	 function excel() {
   
	 $.ajax({
					url : "${pageContext.request.contextPath}/manage/importAssetExcel.action",
					type : "POST",
					data:new FormData(document.getElementById("forms")),
					processData :false,
					contentType :false,
					success : function(data) {
					if(data==1){
					layer.alert("设备表excel导入成功!", {
		                skin: 'layui-layer-molv', //样式类名
		                closeBtn: 0
		            }, function(){
		               layer.closeAll();
		            });
		            }else if(data==0){
		            	layer.alert("设备表excel导入失败!", {
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
								layer.alert("设备表excel导入失败!", {
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
    <h4 class="myClass navbar-left">设备管理</h4>
   
		<form id="forms" class="form-inline "  method="post" enctype="multipart/form-data"  action="${pageContext.request.contextPath}/manage/togetAsset.action">
		   &nbsp; &nbsp;
		  
		  <div class="form-group">
		  	<label for="equipmentName">设备名称</label>
		    <input type="text"  name="asset_name" class="form-control"style="width:150px;" id="equipmentName">
		  </div>
		  <div class="form-group">
		  	<label for="equipmentNum">设备编号</label>
		    <input type="text" name="asset_no" class="form-control" style="width:150px;"id="equipmentNum">
		  </div>
		  &nbsp;
		  
		  <button  type="submit" class="btn btn-primary">查询</button>
		  
		  &nbsp; 
		  	<!--以下第一行为添加按钮实现的网页的跳转-->	  
		  <a class="btn btn-primary"  href="${pageContext.request.contextPath}/manage/toinsertAsset.action" >添加</a> 
		   <!-- excel导入文件action执行语句导入excel的语句 -->
			 
			 &nbsp;
			       <a  class="btn btn-danger" href="${pageContext.request.contextPath}/manage/AssetSample.action" ><span class="glyphicon glyphicon-save"></span>&nbsp;&nbsp;生成设备表模板</a> 
			       <input type="file" id="excelPath" name="excelPath" class="form-control" style="width:180px;"/>
			      <input type="button" id="newmj" value="导入Excel" class="btn btn-primary" onclick="excel()" />
			       
		
		</form>
		<br />
	<div class="table-responsive">
	  <table class="table">
			<thead>
				<tr>
				    
				    <th>设备名称</th>
					<th>设备编号</th>					
					<th>型号</th>
					<!-- <th>设备分级</th> -->
					<th>位置</th>
					<!-- <th>生产产家</th> -->
					<!-- <th>负责人</th> -->
					<th>修改</th>
					<th>删除</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${listAsset}" var="asset">
			<!-- 循环输出forEach -->
				<tr>  
					<td>${asset.assetName}</td>
					<td>${asset.assetNo }</td>
					<td>${asset.assetType}</td>
					<%-- <td>${asset.deviceLevel }</td> --%>
					<td>${asset.position }</td>
					<%-- <td>${asset.manufacturer }</td> --%>
				<%-- 	<td>${asset.director }</td> --%>
					
					<td>
					<input type="hidden" name="id" value="${asset.assetId}"/><input class="btn  btn-xs btn-primary" type="button" value="修改" onclick="revise(this)"> 
					</td>
					<td>
					<input type="hidden" name="id" value="${asset.assetId}"/><input class="btn  btn-xs btn-danger" type="button" value="删除" onclick="revice(this)">
					</td>
				</tr>
				</c:forEach>
			</tbody>
			</table>	
</div>

<!-- 分页功能 start -->    
    <div align="center" >    
        <font size="2">共 ${page.totalPageCount} 页</font> <font size="2">第    
            ${page.pageNow} 页</font> <a href="${pageContext.request.contextPath}/manage/togetAsset.action?pageNow=1&asset_name=${asset_name}&asset_no=${asset_no}">首页</a>    
        <c:choose>  
            <c:when test="${page.pageNow - 1 > 0}">    
                <a href="${pageContext.request.contextPath}/manage/togetAsset.action?pageNow=${page.pageNow - 1}&asset_name=${asset_name}&asset_no=${asset_no}">上一页</a>    
            </c:when>    
            <c:when test="${page.pageNow - 1 <= 0}">    
                <a href="${pageContext.request.contextPath}/manage/togetAsset.action?pageNow=1&asset_name=${asset_name}&asset_no=${asset_no}">上一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath}/manage/togetAsset.action?pageNow=${page.pageNow}&asset_name=${asset_name}&asset_no=${asset_no}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 < page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/manage/togetAsset.action?pageNow=${page.pageNow + 1}&asset_name=${asset_name}&asset_no=${asset_no}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/manage/togetAsset.action?pageNow=${page.totalPageCount}&asset_name=${asset_name}&asset_no=${asset_no}">下一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath }/manage/togetAsset.action?pageNow=${page.pageNow}&asset_name=${asset_name}&asset_no=${asset_no}">尾页</a>    
            </c:when>    
            <c:otherwise>    
                <a href="${pageContext.request.contextPath }/manage/togetAsset.action?pageNow=${page.totalPageCount}&asset_name=${asset_name}&asset_no=${asset_no}">尾页</a>    
            </c:otherwise>    
        </c:choose>  
        跳到<select name='topage' size='1' onchange="window.location=' ${pageContext.request.contextPath }/manage/togetAsset.action?pageNow='+this.value+'&asset_name=${asset_name}&asset_no=${asset_no}'">
       <c:forEach var="i" begin="1" end="${page.totalPageCount}">
       <c:if test="${i==page.pageNow}"><option value='${i}' selected>${i}</option></c:if>
       <c:if test="${i!=page.pageNow}"><option value='${i}'>${i}</option></c:if>
       </c:forEach></select> 页
    </div>    
    <!-- 分页功能 End -->

</div>
</body>
</html>

