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
    <title>领料审批详情查看</title>
    <link href="../css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="../css/bootstrap.min.new.css"/>
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="../css/main.css"/>
	<link rel="stylesheet" href="../css/layer.css" />
	
	
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
	</script>
	
 <!-- 表单提交submit分为已审批和不审批都提交表单 -->
<script type="text/javascript">
function is_submit(value) {
        var form1 = $("#form1");
        if (value == 1) { 
        layer.alert("审批成功!", {
		                skin: 'layui-layer-molv', //样式类名
		                closeBtn: 0
		            }, function(){
		            //提交表单（0-1已经审批）
		              form1.action = "${pageContext.request.contextPath}/record/approveMaterial.action";
                      $("#form1").attr("action",form1.action);
                      form1.submit();
		            });
             
               
            
        }
        if (value == 2) { 
        layer.alert("审批不通过!", {
		                skin: 'layui-layer-molv', //样式类名
		                closeBtn: 0
		            }, function(){
		            //提交表单（0-2已经审批）
		              form1.action = "${pageContext.request.contextPath}/record/notapproveMaterial.action";
                      $("#form1").attr("action",form1.action);
                      form1.submit();
		            });
             
               
            
        }
   }

</script>
</head>
<body>
	<form class="form-inline"  method="post" name="form1" id="form1" action="">		
	<div class="container">	
		<div class="table-responsive">
		
		  
		  	   <h4>领料审批详情查看</h4>
		  	   	<table class="table">
				<thead>
					<tr>
						<th>计划单号</th>					
						<th>图号</th>
						<th>物料号</th>
						<th>批次号</th>
						<th>车间名称</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${getMaterial.planNo}</td>					
						<td>${getMaterial.clientMaterialNo}</td>
						<td>${getMaterial.materialNo}</td>
						<td>${getMaterial.batchNo}</td>
						<td>${getMaterial.shopName}</td>									
					</tr>
		
				</tbody>	
					<thead>
					<tr>	
						<th>产品名称</th>
						<th>产品规格</th>
						<th>申请人</th>
						<th>申请日期</th>																				
					</tr>
				</thead>
				
				<tbody>
					<tr>
					    <td>${getMaterial.materialName}</td>					
						<td>${getMaterial.productSpec}</td>
						<td>${getMaterial.applyPeople}</td>					
						<td>${getMaterial.applyDate}</td>
					</tr>			
				</tbody>	
				
			</table>
		</div>
	</div>
	
	<div class="container">
		<div class="table-responsive">
		  	<table class="table">
				<thead>
					<tr>	
						<th>材料名称</th>					
						<th>材料编号</th>
						<th>实际用量</th>
						<th>理论值参考</th>
						<th>单位</th>
						<th>提供人</th>
						<th>接受人</th>
						<th>领料日期</th>
						<th>备注</th>					
					</tr>
				</thead>
				<tbody>
				 <c:forEach items="${listGetDetail}" var="a">
					<tr>
						<td>${a.cailiaoMc}</td>
						<td>${a.cailiaoBh}</td>					
						<th>${a.materialNum}</th>
						<th>${a.referNum}</th>
						<th>${a.unit}</th>
						<td>${a.provider}</td>
						<td>${a.acceptor}</td>
						<td>${a.getDate}</td>
						<td>${a.remark}</td>											
					</tr>
					</c:forEach>										
				</tbody>
			</table>
		</div>
	</div>
	
	<br />
	<div class="container" style = "text-align:right">
		<input type="hidden" name="getMaterialId" value="${getMaterial.getMaterialId}"/><input class="btn btn-success" type="button" value="审批" onclick="is_submit(1)" >
		<input class="btn btn-danger" type="button" value="审批不通过" onclick="is_submit(2)" >
	</div>
	</form>
</body>
</html>

