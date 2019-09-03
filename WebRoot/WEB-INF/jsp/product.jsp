<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
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
    <title>产品表管理</title>
    <link href="../css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="../css/bootstrap.min.new.css"/>
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="../css/main.css"/>
	<link rel="stylesheet" href="../css/layer.css"/>
	
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
	function revice(obj){
	//获取materialNo和clientMaterialNo的节点值
	//实现所用的节点是不是客户物料号和物料号
	 	var clientMaterialNo=$(obj).parent().prev().prev().prev().prev().prev().html();
		var materialNo=$(obj).parent().prev().prev().prev().prev().html();
	//两条语句&与	
	location.href="${pageContext.request.contextPath}/manage/DeleteFour.action?client_material_no="+clientMaterialNo+"&material_no="+materialNo;
	}
	
	function revise(obj){
	 	var clientMaterialNo=$(obj).parent().prev().prev().prev().prev().html();
		var materialNo=$(obj).parent().prev().prev().prev().html();
	//两条语句&与	
	location.href="${pageContext.request.contextPath}/manage/UpdateProduct.action?client_material_no="+clientMaterialNo+"&material_no="+materialNo;
	
	}
	//excel导入
	 function excel() {
    
	 $.ajax({
					url : "${pageContext.request.contextPath}/manage/importProductExcel.action",
					type : "POST",
					data:new FormData(document.getElementById("forms")),
					processData :false,
					contentType :false,
					success : function(data) {
					if(data==1){
					layer.alert("产品表excel导入成功!", {
		                skin: 'layui-layer-molv', //样式类名
		                closeBtn: 0
		            }, function(){
		               layer.closeAll();
		            });
		            }else if(data==0){
		            	layer.alert("产品表excel导入失败!", {
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
		            }else if(data==3){
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
								layer.alert("产品表excel导入失败!", {
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
	    <h4 class="myClass navbar-left">产品表管理</h4>
			<form id="forms" class="form-inline" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/manage/toSelectProduct.action">
		  	 &nbsp;&nbsp;
		  	<div class="form-group">
		  		<label for="client">图号</label>
		    	<input type="text" name="client_material_no" style="width: 160px" class="form-control" id="client_material_no">
		  	</div>
		 	<div class="form-group">
		  		<label for="productCode">物料号</label>
		    	<input type="text" name="material_no" style="width: 160px"class="form-control" id="productCode">
		  	</div>
		  	&nbsp;&nbsp;
		  	<button type="submit" class="btn btn-primary">查询</button>
		 	 &nbsp;  
		 		<!--以下第一行为添加按钮实现的网页的跳转-->	  
		  <a class="btn btn-primary"  href="${pageContext.request.contextPath}/manage/toaddProduct.action" >添加</a> 
		  &nbsp;&nbsp;&nbsp;
		
		   <!-- excel导入文件action执行语句导入excel的语句 -->
			    
			      <a  class="btn btn-danger" href="${pageContext.request.contextPath}/manage/BomSample.action" ><span class="glyphicon glyphicon-save"></span>&nbsp;&nbsp;生成BOM表模板</a> 
			        <input type="file" id="excelPath" name="excelPath" class="form-control" style="width:180px;"/>
			       <input type="button" id="newmj" value="导入Excel" class="btn btn-primary" onclick="excel()" />     
			</form>
		<br />
		<div class="table-responsive">
		  	<table class="table">
				<thead>
					<tr>
						<th>图号</th>					
						<th>物料号</th>
						<th>产品名称</th>
						<th>产品规格</th>
						<th>修改</th>
						<th>删除</th>
					</tr>
				</thead>
				<tbody>
				
				<c:forEach items="${listproduct}" var="item">
			<!-- 循环输出forEach -->
			
					<tr>
						<td>${item.clientMaterialNo}</td>					
						<td>${item.materialNo}</td>
						<td>${item.productName}</td>
						<td>${item.productSpec}</td>
						<td>
							<input type="button" class="btn btn-primary btn-xs" id="modify" value="修改" onclick="revise(this)"/>
						</td>
						<td>
							<input type="button" class="btn btn-danger btn-xs" id="delete" value="删除" onclick="revice(this)"/>
						</td>
					</tr>
					
					</c:forEach>
					
				</tbody>
			</table>
		</div>
		
		<!-- 分页功能 start -->    
    <div align="center" >    
        <font size="2">共 ${page.totalPageCount} 页</font> <font size="2">第    
            ${page.pageNow} 页</font> <a href="${pageContext.request.contextPath}/manage/toSelectProduct.action?pageNow=1&client_material_no=${client_material_no}&material_no=${material_no}">首页</a>    
        <c:choose>    
            <c:when test="${page.pageNow - 1 > 0}">    
                <a href="${pageContext.request.contextPath}/manage/toSelectProduct.action?pageNow=${page.pageNow - 1}&client_material_no=${client_material_no}&material_no=${material_no}">上一页</a>    
            </c:when>    
            <c:when test="${page.pageNow - 1 <= 0}">    
                <a href="${pageContext.request.contextPath}/manage/toSelectProduct.action?pageNow=1&client_material_no=${client_material_no}&material_no=${material_no}">上一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath}/manage/toSelectProduct.action?pageNow=${page.pageNow}&client_material_no=${client_material_no}&material_no=${material_no}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 < page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/manage/toSelectProduct.action?pageNow=${page.pageNow + 1}&client_material_no=${client_material_no}&material_no=${material_no}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/manage/toSelectProduct.action?pageNow=${page.totalPageCount}&client_material_no=${client_material_no}&material_no=${material_no}">下一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath }/manage/toSelectProduct.action?pageNow=${page.pageNow}&client_material_no=${client_material_no}&material_no=${material_no}">尾页</a>    
            </c:when>    
            <c:otherwise>    
                <a href="${pageContext.request.contextPath }/manage/toSelectProduct.action?pageNow=${page.totalPageCount}&client_material_no=${client_material_no}&material_no=${material_no}">尾页</a>    
            </c:otherwise>    
        </c:choose>  
        跳到<select name='topage' size='1' onchange="window.location=' ${pageContext.request.contextPath }/manage/toSelectProduct.action?pageNow='+this.value+'&client_material_no=${client_material_no}&material_no=${material_no}'">
       <c:forEach var="i" begin="1" end="${page.totalPageCount}">
       <c:if test="${i==page.pageNow}"><option value='${i}' selected>${i}</option></c:if>
       <c:if test="${i!=page.pageNow}"><option value='${i}'>${i}</option></c:if>
       </c:forEach></select> 页
    </div>    
    <!-- 分页功能 End -->
		
	</div>
</body>
</html>


