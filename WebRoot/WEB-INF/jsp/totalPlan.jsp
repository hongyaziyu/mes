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
    <title>月生产计划</title>
    <link href="../css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="../css/bootstrap.min.new.css"/>
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="../css/main.css"/>
</head>
<div class="container">
    <h4>月生产计划</h4>
        <form id="form" class="form-inline" action="${pageContext.request.contextPath}/plan/addTotalPlan.action" method="post">
            <table class="table table-hover">
              <tr>

                <td><label for="exampleInputName2">客户</label></td>
                <td><input id="kh" type="text" class="form-control" name="client" value="${productionPlan.client }"/></td>
                
                <td><label for="exampleInputName2">下单日期</label></td>
                <td><div class="input-group date form_date"  data-date-format="yyyy-mm-dd">
                            <input class="form-control" size="16" type="text" name="orderDate" value="${productionPlan.orderDate }"/>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div></td>
                
             	 <td><label for="exampleInputName2">图号</label></td>
                <td><input id="mjwl" type="text" class="form-control" name="clientMaterialNo" value="${productionPlan.clientMaterialNo }"/></td>
             	
                
                <td><label for="exampleInputName2">物料号</label></td>
                <td><select id="wl" name="materialNo" class="mini-input">	
			      		<option disabled selected style="display: none;">--请选择--</option>
			      		<c:if test="${productionPlan.materialNo!=null }"><option value="${productionPlan.materialNo}" selected="selected">${productionPlan.materialNo}</option></c:if>			     
		            </select> 
		        </td>
            </tr>
            <tr>
                <td><label for="exampleInputName2">产品名称</label></td>    
                <td><input id="cpmc" type="text" class="form-control" name="productName" value="${productionPlan.productName}"/></td>
              
              	<td><label for="exampleInputName2">产品规格</label></td>    
                <td><input id="cpgg" type="text" class="form-control" name="productSpec" value="${productionPlan.productSpec}"/></td>
              	
              	<td><label for="exampleInputName2">计划单号</label></td>    
                <td><input id="jhdh" type="text" class="form-control" name="planNo" value="${productionPlan.planNo}"/></td>
              
            
                <td><label for="exampleInputName2">计划数量</label></td>
                <td><input type="text" class="form-control" name="planNum" value="${productionPlan.planNum}"/></td>         
                          
          
             </tr>
             <tr> 
	            <td><label for="exampleInputName2">起初数量</label></td>
	            <td><input type="text" class="form-control" name="startNum" value="${productionPlan.startNum}"/></td> 
	                           
	            <td><label for="exampleInputName2">原材料库存数量</label></td>
	            <td><input type="text" class="form-control" name="materialNum" value="${productionPlan.materialNum}"/></td>
	          
	          
	            <td><label for="exampleInputName2">成品库存数量</label></td>
	            <td><input type="text" class="form-control" name="productNum" value="${productionPlan.productNum}"/></td>
	          	
	          	<td><label for="exampleInputName2">半成品库存数量</label></td>
	            <td><input type="text" class="form-control" name="unproductNum" value="${productionPlan.unproductNum}"/></td>                              
             </tr>
          
          </table>
             <input  type="hidden"  id="planId" name="planId"  value="${productionPlan.planId }">  
        </form>
        <button id="tijiao" type="button" class="btn btn-primary navbar-right">提交</button><br><br><br>
		<div class="alert alert-danger hidden alert-dismissible" > 
		  <strong>警告信息：</strong> 该图号不存在，请检查！
		</div>
		 <!-- <h4>物料需求信息</h4>
       
            <table class="table table-hover">
              <thead>
				<tr>
					<th>子件编码</th>
					<th>子件名称</th>					
					<th>基本用量</th>
					<th>基础用量</th>
					<th>单位</th>
					
				</tr>
			</thead>
			<tbody>
			
				
					<tr  >
					<td>5H0100374</td>
					<td>底板</td>					
					<td>1</td>
					<td>1</td>
					<td>支</td>
				</tr>
					<tr  >
					<td>5H0100375</td>
					<td>支架</td>					
					<td>1</td>
					<td>1</td>
					<td>支</td>
				</tr>
				
				
			</tbody>
            
          </table>  -->        
</div>
           

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="../js/jquery-3.2.1.min.js"></script>  
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/bootstrap-datetimepicker.js"></script>
        <script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
        
<script type="text/javascript" >//$(document).ready(function(){
	//window.onload = orderjs();
	$(function(){
	//图号下自动显示物料号
		if($("#planId").val()!=null&&$("#planId").val()!=""&&$("#wl").val()!=null&&$("#wl").val()!=""){
			
			$("#wl").mousedown(function(){	
				$.ajax({
					 	 url:"${pageContext.request.contextPath}/plan/shopMjwlAjax.action",						 
						 data:{
							clientMaterialNo:$("#mjwl").val(),							
						 },
						 type:"POST",
						 success:function(responseText){
							
							if(responseText!=null&&responseText!=""){
							//给id为wl的value赋值为html
							$("#wl").html(responseText);
							}
				    		
						 }
				});
			});
		 }
		 $("#wl").mousemove(function(){
			if(($("#wl").val()==null||$("#wl").val()=="")&&($("#planId").val()==null||$("#planId").val()=="")){
			
			$.ajax({
					 	 url:"${pageContext.request.contextPath}/plan/shopMjwlAjax.action",						 
						 data:{
							clientMaterialNo:$("#mjwl").val(),							
						 },
						 type:"POST",
						 success:function(responseText){
							
							if(responseText!=null&&responseText!=""){
							$("#wl").html(responseText);
							}
				    		
						 }
			});
			
			}  
		});
		
		//物料号自动显示产品名称、产品规格
		if($("#planId").val()!=null&&$("#planId").val()!=""&&$("#cpmc").val()!=null&&$("#cpmc").val()!=""&&$("#cpgg").val()!=null&&$("#cpgg").val()!=""){
			
			 $("#cpmc").mousedown(function(){	
				$.ajax({
					 	 url:"${pageContext.request.contextPath}/plan/shopMCorGGAjax.action",						 
						 data:{
							materialNo:$("#wl").val(),							
						 },
						 type:"POST",
						 success:function(data){
							$("#cpmc").val(data.Cpmc);
							$("#cpgg").val(data.Cpgg);
				    		
						 
						 }
				});
			});
		 } 
		  $("#cpmc").mousemove(function(){
			if(($("#cpmc").val()==null||$("#cpmc").val()=="")&&($("#cpgg").val()==null||$("#cpgg").val()=="")&&($("#planId").val()==null||$("#planId").val()=="")){
			
			$.ajax({
					 	 url:"${pageContext.request.contextPath}/plan/shopMCorGGAjax.action",						 
						 data:{
							materialNo:$("#wl").val(),							
						 },
						 type:"POST",
						 success:function(data){
						 
							$("#cpmc").val(data.Cpmc);
							$("#cpgg").val(data.Cpgg);
				    		
						 }
			});
			
			}  
		});
		 
		 
		$("#mjwl").blur(function(){
			 $.ajax({
					 	 url:"${pageContext.request.contextPath}/plan/shopWlAjax.action",						 
						 data:{
							clientMaterialNo:$("#mjwl").val(),							
						 },
						 type:"POST",
						 success:function(responseText){
							
							if(responseText!=null&&responseText!=""){
								$(".alert").removeClass("hidden");
							}else{
								$(".alert").addClass("hidden");
							}
				    		
						 }
			});
		});
		$("#tijiao").click(function() {
			
			$("form").submit();
		});
	
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
    
</body>
</html>