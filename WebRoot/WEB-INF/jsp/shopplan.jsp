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
    <title>车间排产计划</title>
    <link href="../css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="../css/bootstrap.min.new.css"/>
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="../css/main.css"/>
</head>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="../js/jquery-3.2.1.min.js"></script>  
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/bootstrap-multiselect.js" type="text/javascript"></script>
        <script src="../js/bootstrap-datetimepicker.js"></script>
        <script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
        <script src="../js/my.js"></script>
<div class="container">
    <h4>车间排产计划</h4>
        <form id="form" class="form-inline" action="${pageContext.request.contextPath}/plan/addShopPlan.action" method="post">
            <table class="table table-hover">
              <tr>                                          
              	
              	<td><label for="exampleInputName2">图号</label></td>
                <td><input id="mjwl" type="text" class="form-control" readonly name="clientMaterialNo"  value="${shopPlan.clientMaterialNo }"></td>
                
                <td><label for="exampleInputName2">物料号</label></td>
               <%--  <td><select id="wl" name="materialNo" readonly class="mini-input">
			      		<option disabled selected style="display: none;">--请选择--</option>
			      		<c:if test="${shopPlan.materialNo!=null }"><option value="${shopPlan.materialNo}" selected="selected">${shopPlan.materialNo}</option></c:if>			     
		            </select>--%>
		           <td> <input id="wl" type="text" class="form-control" readonly name="materialNo"  value="${shopPlan.materialNo }">
		        </td> 
		            
                <td><label for="exampleInputName2">批次号</label></td>
                <td><input id="pch" type="text" class="form-control" readonly name="batchNo" value="${shopPlan.batchNo }"/></td>
               
                <td><label for="exampleInputName2">车间名称</label></td>  
                <td><input id="cj" type="text" class="form-control" readonly name="shopName" value="${shopPlan.shopName }"/></td>           
             </tr>
             <tr>
                <td><label for="exampleInputName2">计划单号</label></td>
                <td><input  type="text" class="form-control" name="planNo" readonly value="${shopPlan.planNo}"></td>                
                
                <td><label for="exampleInputName2">计划数量</label></td>
                <td><input  type="text" class="form-control" name="planNum" readonly value="${shopPlan.planNum }"></td>
              </tr>
          </table>
        <h4>生产车间计划安排</h4>
        <input  type="hidden" id="num" name="num"  value="">
        <input  type="hidden" id="planId" name="planId"  value="${planId}"> 
        <div id="div">
        	<jsp:include page="shopdiv.jsp"></jsp:include>
        </div>
                <br />
               
        </form>
        <button id="tijiao" type="button" class="btn btn-primary navbar-right">提交</button><br><br><br>
        <div class="alert alert-danger hidden alert-dismissible" >	 
		  <strong>警告信息：</strong> 该物料号不存在，请检查！
		</div>
</div>


<script type="text/javascript" >//$(document).ready(function(){
	//window.onload = orderjs();
	$(function(){
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
		 $("#cj").blur(function(){
			 $.ajax({
					 	 url:"${pageContext.request.contextPath}/plan/shopCjAjax.action",						 
						 data:{
						 	clientMaterialNo:$("#mjwl").val(),
							materialNo:$("#wl").val(),
							shopName:$("#cj").val(),
						 },
						 type:"POST",
						 success:function(responseText){
							$("#div").html(responseText);
							$(".multiselect1").multiselect({
								placeholder: '--请选择--',
							    maxHeight: 300,
							    enableFiltering: true,
							    enableCaseInsensitiveFiltering: true,
							    filterPlaceholder: '',
							    nonSelectedText: '--请选择--',
							    nSelectedText: '个已选择',
							    numberDisplayed: 4
				    		});
						 }
			}); 
			
			
		}); 
		$("#tijiao").click(function(){
			var j=1;
			$(".used").each(function(){
				$(this).attr("id","div_"+j);
				var gongxu = $(this).children().first();
				gongxu.attr("name","gongxu" + j);
				var shebei =gongxu.next();
				shebei.attr("name","shebei" + j);
				var muju =shebei.next();
				muju.attr("name","muju" + j);
				var caozuogong =muju.next();
				caozuogong.attr("name","caozuogong" + j);
				j++;
			});
			$("#num").val(j-1);
			$("form").submit();
		});
		$(".multiselect").multiselect({
		    maxHeight: 300,
		    enableFiltering: true,
		    enableCaseInsensitiveFiltering: true,
		    filterPlaceholder: '',
		    nonSelectedText: '--请选择--',
		    nSelectedText: '个已选择',
		    numberDisplayed: 4
    	});
	});
	function jia(obj){
				var sourceNode = $("#div_0");
			var cloneNode = sourceNode.clone();
			var currentNode = $(obj).parent();
			var sourceNode = $("#div_0");
			cloneNode.addClass("used");
			cloneNode.removeClass("hidden");
		    cloneNode.children().first().next().next().next().addClass("multiselect1");
			cloneNode.children().first().next().next().next().attr("multiple","multiple");
			cloneNode.children().first().next().next().next().children().first().remove();	 
			currentNode.after("<div id='' class='used form-group'></div>");		
			currentNode.next().html(cloneNode.html());			
			$(".multiselect1").multiselect({
				placeholder: '--请选择--',
			    maxHeight: 300,
			    enableFiltering: true,
			    enableCaseInsensitiveFiltering: true,
			    filterPlaceholder: '',
			    nonSelectedText: '--请选择--',
			    nSelectedText: '个已选择',
			    numberDisplayed: 4
    		});
			
	}
	function jian(obj){
			
			var delNode = $(obj).parent();
			
			var firstdiv=$("#div").children().first().next();
			
			if(delNode.html()!=firstdiv.html()){
				delNode.remove();
			}
					
	}
</script>
    
</body>
</html>