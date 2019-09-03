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
    <title>周生产计划</title>
    <link href="../css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="../css/bootstrap.min.new.css"/>
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="../css/main.css"/>
    <link rel="stylesheet" href="../css/layer.css"/>
    
   
</head>
<div class="container">
    <h4>周生产计划</h4>
        <form id="form" class="form-inline" enctype="multipart/form-data" action="${pageContext.request.contextPath}/plan/addProductionPlan.action" method="post">
           
            <table class="table table-hover">
              <tr>
              	
              	<td><label for="exampleInputName2">订单号</label></td>
                <td><input id="ddh" type="text" class="form-control" name="orderNo" value="${productionPlan.orderNo}"/></td> 
                
                <td><label for="exampleInputName2">客户</label></td>
                <td><input id="kh" type="text" class="form-control" name="client" value="${productionPlan.client }"/></td>
                
              
                
             	 <td><label for="exampleInputName2">图号</label></td>
                 <td><input id="mjwl" type="text" class="form-control" name="clientMaterialNo" value="${productionPlan.clientMaterialNo }"/></td>
             	
             	 <td><label for="exampleInputName2">物料号</label></td>
                 <td><select id="wl" name="materialNo" class="mini-input">	
			      		<option disabled selected style="display: none;">--请选择--</option>
			      		<c:if test="${productionPlan.materialNo!=null }"><option value="${productionPlan.materialNo}" selected="selected">${productionPlan.materialNo}</option></c:if>			     
		            </select> </td>
               
              </tr>
              <tr>
		            
                <td><label for="exampleInputName2">产品名称</label></td>    
                <td><input id="cpmc" type="text" class="form-control" name="productName" value="${productionPlan.productName}"/></td>
              
              	<td><label for="exampleInputName2">产品规格</label></td>    
                <td><input id="cpgg" type="text" class="form-control" name="productSpec" value="${productionPlan.productSpec}"/></td>
              
              	
              	<td><label for="exampleInputName2">计划单号</label></td>    
                <td><input id="jhdh" type="text" class="form-control" name="planNo" value="${productionPlan.planNo}"/></td>
                
                <td><label for="exampleInputName2">周计划完成日期</label></td>
                <td><div class="input-group date form_date"  data-date-format="yyyy-mm-dd">
                            <input class="form-control" size="15" type="text" name="orderDate" value="${productionPlan.orderDate }"/>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div></td>
            
             </tr>
             <tr>
                <td><label for="exampleInputName2">周计划数量</label></td>
                <td><input type="text" class="form-control" name="planNum" value="${productionPlan.planNum}"/></td> 
             <%--       
	            <td><label for="exampleInputName2">起初数量</label></td>
                <td><input  type="text" class="form-control" name="startNum" value="${productionPlan.startNum}"/></td>  --%>
	           
          	</tr>
            
            
          </table>
        <h4>制定生产计划</h4>
           <input  type="hidden" id="num" name="num"  value="">
        	<input  type="hidden"  id="planId" name="planId"  value="${productionPlan.planId }">
            <div id="div">
        	<jsp:include page="productionPlandiv.jsp"></jsp:include>
        </div>
                <br /> 
                      
        </form>
        <button id="tijiao" type="button" class="btn btn-primary navbar-right">提交</button><br><br><br>
		<div id="jg1" class="alert alert-danger hidden alert-dismissible" > 
		  <strong>警告信息：</strong> 该订单号不存在，请检查！
		</div>
		<div id="jg2" class="alert alert-danger hidden alert-dismissible" > 
		  <strong>警告信息：</strong> 该图号不存在，请检查！
		</div>
</div>



<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="../js/jquery-3.2.1.min.js"></script>  
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/bootstrap-datetimepicker.js"></script>
        <script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
        <script type="text/javascript" src="../js/layer.js"></script>
<script type="text/javascript" >//$(document).ready(function(){
	//window.onload = orderjs();
	$(function(){
		if($("#planId").val()!=null&&$("#planId").val()!=""&&$("#wl").val()!=null&&$("#wl").val()!=""){
			
			$("#wl").mousedown(function(){	
				$.ajax({
					 	 url:"${pageContext.request.contextPath}/plan/shopMjwlAjax1.action",						 
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
			});
		 }
		 $("#wl").mousemove(function(){
			if(($("#wl").val()==null||$("#wl").val()=="")&&($("#planId").val()==null||$("#planId").val()=="")){
			
			$.ajax({
					 	 url:"${pageContext.request.contextPath}/plan/shopMjwlAjax1.action",						 
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
		 
		$("#wl").blur(function(){
			  $.ajax({
					 	 url:"${pageContext.request.contextPath}/plan/shopAjax.action",						 
						 data:{
							clientMaterialNo:$("#mjwl").val(),
							materialNo:$("#wl").val(),							
						 },
						 type:"POST",
						 success:function(responseText){
							
							if(responseText!=null&&responseText!=""){
								$("#div").html(responseText);
								$('.form_date').datetimepicker({
									language: 'zh-CN',
									weekStart: 0,
									todayBtn: 1,
									autoclose: 1,
									todayHighlight: 1,
									startView: 3,
									minView: 2,
									forceParse: 0
								});
							}
				    		
						 }
			}); 
			 
		});
		$("#mjwl").blur(function(){
			 $.ajax({
					 	 url:"${pageContext.request.contextPath}/plan/shopWlAjax1.action",						 
						 data:{
							clientMaterialNo:$("#mjwl").val(),							
						 },
						 type:"POST",
						 success:function(responseText){
							
							if(responseText!=null&&responseText!=""){
								$("#jg2").removeClass("hidden");
							}else{
								$("#jg2").addClass("hidden");
							}
				    		
						 }
			});
		});
		
	//物料号、订单号自动显示订单数量
	if($("#planId").val()==null&&$("#planId").val()==""&&$("#jhdh").val()!=null&&$("#jhdh").val()!=""){
			
			 $("#jhdh").mousedown(function(){	
				$.ajax({
					 	 url:"${pageContext.request.contextPath}/plan/JhdhAjax.action",						 
						 data:{					
						 },
						 type:"POST",
						 success:function(data){
						   $("#jhdh").val(data.Jhdh);
						 }
				});
			});
		 } 
		  $("#jhdh").mousemove(function(){
			if(($("#jhdh").val()==null||$("#jhdh").val()=="")&&($("#planId").val()==null||$("#planId").val()=="")){
			
			$.ajax({
					 	 url:"${pageContext.request.contextPath}/plan/JhdhAjax.action",						 
						 data:{						
						 },
						 type:"POST",
						 success:function(data){
				    		$("#jhdh").val(data.Jhdh);
						 }
			});
			
			}  
		});
		
		
		//查询订单号是否存在
		 $("#ddh").blur(function(){
			 $.ajax({
					 	 url:"${pageContext.request.contextPath}/jdbc/OrderNoAjax.action",						 
						 data:{
							orderNo:$("#ddh").val(),							
						 },
						 type:"POST",
						 success:function(responseText){
							
							if(responseText!=null&&responseText!=""){
								$("#jg1").removeClass("hidden");
							}else{
								$("#jg1").addClass("hidden");
							}
				    		
						 }
			});
		}); 
		$("#tijiao").click(function() {
			var j=1;
			$(".used").each(function(){
				$(this).attr("id","div_"+j);
				var chejian = $(this).children().children().children().children().children().first();				
				var jiaofu_num =chejian.parent().next().children().first();
				var div_date =jiaofu_num.parent().next().children().first();
				chejian.attr("name","chejian" + j);
				jiaofu_num.attr("name","jiaofu_num" + j);
				var jiaofu_date = div_date.children().first();
				jiaofu_date.attr("name","jiaofu_date" + j);
				var div_date2 =div_date.parent().parent().next().children().children().first();
				var plan_finish_date = div_date2.children().first();
				plan_finish_date.attr("name","plan_finish_date" + j);
				var shijiao_num=div_date2.parent().next().children().first();
				shijiao_num.attr("name","shijiao_num" + j);
				var div_date3=shijiao_num.parent().next().children().first();
				var shijiao_date=div_date3.children().first();
				shijiao_date.attr("name","shijiao_date" + j);
				j++;
			});
			$("#num").val(j-1);
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
	function jia(obj){
			var sourceNode = $(obj).parent().parent();
			var cloneNode = sourceNode.clone();
			sourceNode.after("<div id='' class='used form-group'></div>");	
			sourceNode.next().html(cloneNode.html());
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
	};
		function jian(obj){

			var delNode = $(obj).parent().parent();
			
			var firstdiv=$("#div").children().first();
			firstdiv.addClass("first");
			if(delNode.attr("class")!=firstdiv.attr("class")){
				delNode.remove();
			}
		};
		

</script>
    
</body>
</html>