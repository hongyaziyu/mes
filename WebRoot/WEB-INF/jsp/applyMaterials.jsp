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
   
    <title>申请领料计划</title>
   
</head>
<div class="container">
    <h4>申请领料计划</h4>
        <form id="form" class="form-inline"  action="${pageContext.request.contextPath}/plan/ApplyMaterial.action" >
               <input  type="hidden" id="getMaterialId" name="getMaterialId"  value="">
              <table class="table table-hover">
              <tr>              
              	<td><label for="exampleInputName2">图号</label></td>
                <td><input id="mjwl" type="text" class="form-control" name="clientMaterialNo"  value="${shopPlan.clientMaterialNo}"></td>
                
                <td><label for="exampleInputName2">物料号</label></td>
                <td><select id="wl" name="materialNo" class="mini-input">
                 <!-- 一开始下拉框应该为禁止选中状态，<option disabled selected style="display: none;"> -->
			      		<option disabled selected style="display: none;">--请选择--</option>
			      		<c:if test="${shopPlan.materialNo!=null }">
			      		<option value="${shopPlan.materialNo}" selected="selected">${shopPlan.materialNo}</option>
			      		</c:if>			     
		            </select>
		        </td>
		        <td><label for="exampleInputName2">计划单号</label></td>
					<td><select id="jihuadanhao" name="planNo" class="mini-input"  value="${shopPlan.planNo}">
							<option disabled selected style="display: none;">--请选择--</option>
					</select>
					</td>
		        <td><label for="exampleInputName2">批次号</label></td>
                <td><input id="pch" type="text" class="form-control" name="batchNo" value="${shopPlan.batchNo}"/></td>
                
			</tr>
             <tr>
                  <td><label for="exampleInputName2">车间名称</label></td>  
				<td>
				<select class="form-control" id="sn" style="width: 180px;" name="shopName" value="${shopPlan.shopName}">
							<option disabled selected style="display: none;"value="--请选择--">--请选择--</option>
							<option value="冲压工段">冲压工段</option>
							<option value="仪表工段">仪表工段</option>
							<option value="焊接工段">焊接工段</option>
							<option value="后道工段">后道工段</option>
							<option value="外协">外协</option>
						
			    </select>
			    </td>
            </tr>
          </table>
         
        <h4>需领取材料</h4><br /> 
            <input  type="hidden" id="num" name="num"  value="">
            <input  type="hidden" id="getMaterialId" name="getMaterialId"  value="">
            
            <div id="div">
        	<jsp:include page="applyMatelDiv.jsp"></jsp:include>
            </div>
                
              <br />      
        </form>
        <button id="tijiao" type="button" class="btn btn-primary navbar-right">提交</button>
        <br/><br/><br/> <br/>   
       <!-- 警告信息弹窗 -->
        <div  id="jg1" class="alert alert-danger hidden alert-dismissible" >	 
		  <strong>警告信息：</strong> 该图号不存在，请检查！
		</div>
		 <div  id="jg2" class="alert alert-danger hidden alert-dismissible" >	 
		  <strong>警告信息：</strong> 该批次号已领过料，请重新填写新的批次号！
		</div>
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
	//在申请页面获取时根据.each(function()使得每个子件信息的name具有唯一标识，以便后面获取插入到数据库
		$("#tijiao").click(function() {
			var j=1;
			$(".used").each(function(){
				$(this).attr("id","div_"+j);
				var cailiaoMc = $(this).children().first();	
				var cailiaoBh = cailiaoMc.next();					
				var materialNum =cailiaoBh.next();
				var referNum=materialNum.next();
				var unit =referNum.next();
				var remark =unit.next();	
				//给下面这些name值循环加1			
				cailiaoMc.attr("name","cailiaoMc" + j);
				cailiaoBh.attr("name","cailiaoBh" + j);
				materialNum.attr("name","materialNum" + j);
				referNum.attr("name","referNum" + j);
				unit.attr("name","unit" + j);
				remark.attr("name","remark" + j);
				j++;
				
			});
		  
			$("#num").val(j-1);
			$("form").submit();
		});
	

	});
	
	
			 
	//物料号：根据图号得到对应的物料号，并下拉框显示
	if($("#getMaterialId").val()!=null&&$("#getMaterialId").val()!=""&&$("#wl").val()!=null&&$("#wl").val()!=""){
			
			$("#wl").mousedown(function(){	
				$.ajax({
					 	 url:"${pageContext.request.contextPath}/plan/shopMjwlAjax1.action",						 
						 data:{
							clientMaterialNo:$("#mjwl").val(),							
						 },
						 type:"POST",
						 success:function(responseText){
							
							if(responseText!=null&&responseText!=""){
							//成功后返回responseText到id为wl的内容中
							$("#wl").html(responseText);
							}
				    		
						 }
				});
			});
		 }
		 
		 
	//物料号：根据图号得到对应的物料号，并下拉框显示
	 $("#wl").mousemove(function(){
			if(($("#wl").val()==null||$("#wl").val()=="")&&($("#getMaterialId").val()==null||$("#getMaterialId").val()=="")){
			
			$.ajax({
					 	 url:"${pageContext.request.contextPath}/plan/shopMjwlAjax1.action",						 
						 data:{
							clientMaterialNo:$("#mjwl").val(),							
						 },
						 type:"POST",
						 success:function(responseText){
							
							if(responseText!=null&&responseText!=""){
							//成功后返回responseText到id为wl的内容中
							$("#wl").html(responseText);
							
							
							 //bootstrap框架-multiselect下拉框组件
							$(".multiselect1").multiselect({
							  //1、显示默认的提示信息。
								placeholder: '--请选择--',
						      //2.下拉列表的最大高度,默认值为 250。
							    maxHeight: 300,
							    enableFiltering: true,
							    enableCaseInsensitiveFiltering: true,
							    filterPlaceholder: '',
							    nonSelectedText: '--请选择--',
							    nSelectedText: '个已选择',
							    numberDisplayed: 4 
				    		});
							}
				    		
						 }
			});
			
			}  
		}); 
		
		//图号显示：检查有无这条图号信息
		//返回的回调函数为：有则移除这个类Strong中的警告，无则添加这个警告类
		//.blur(function()为失去焦点事件
		$("#mjwl").blur(function(){
			 $.ajax({
					 	 url:"${pageContext.request.contextPath}/plan/shopWlAjax1.action",						 
						 data:{
							clientMaterialNo:$("#mjwl").val(),							
						 },
						 type:"POST",
						 success:function(responseText){
							
							if(responseText!=null&&responseText!=""){
							
						//removeClass()移除这个类
								$("#jg1").removeClass("hidden");
							}else{
						//addClass()添加这个类	
								$("#jg1").addClass("hidden");
							}
				    		
						 }
			}); 
			
			
		});
		
		//批次号显示判断：检查该批次号是否已经用过
		//返回的回调函数为：有则移除这个类Strong中的警告，无则添加这个警告类
		//.blur(function()为失去焦点事件
		$("#pch").blur(function(){
			 $.ajax({
					 	 url:"${pageContext.request.contextPath}/plan/shopPCHAjax.action",						 
						 data:{
							batchNo:$("#pch").val(),							
						 },
						 type:"POST",
						 success:function(responseText){
							
							if(responseText!=null&&responseText!=""){
							
						//addClass()添加这个类		
								$("#jg2").addClass("hidden");
							}else{
						//removeClass()移除这个类
								$("#jg2").removeClass("hidden");
							}
				    		
						 }
			}); 
			
			
		});
		
		
		//当填完图号和物料号后查询子键名称，编号，理论值参考的信息
		 	$("#sn").blur(function(){
			 $.ajax({
					 	 url:"${pageContext.request.contextPath}/plan/shopCjAjax1.action",						 
						 data:{
						 	clientMaterialNo:$("#mjwl").val(),
							materialNo:$("#wl").val(),
							batchNo:$("#pch").val(),
							shopName:$("#sn").val(),
						 },
						 type:"POST",
						 success:function(responseText){
							$("#div").html(responseText);
							
							
						 }
			}); 
			
			
		}); 
		
		//未完成的计划单号查询
		if ($("#jihuadanhao").val() != null && $("#jihuadanhao").val() != "") {
			$("#jihuadanhao")
					.mousedown(
							function() {
								alert("aa");
								$
										.ajax({
											url : "${pageContext.request.contextPath}/record/jihuadanhaoAjax.action",
											data : {
												clientMaterialNo : $("#mjwl")
														.val(),
												materialNo : $("#wl").val(),
											},
											type : "POST",
											success : function(responseText) {

												if (responseText != null
														&& responseText != "") {

													$("#jihuadanhao").html(
															responseText);
												}

											}
										});

							});
		}

		$("#jihuadanhao")
				.mousemove(
						function() {

							if ($("#jihuadanhao").val() == null
									|| $("#jihuadanhao").val() == "") {
								$
										.ajax({
											url : "${pageContext.request.contextPath}/record/jihuadanhaoAjax.action",
											data : {
												clientMaterialNo : $("#mjwl")
														.val(),
												materialNo : $("#wl").val(),
											},
											type : "POST",
											success : function(responseText) {

												if (responseText != null
														&& responseText != "") {

													$("#jihuadanhao").html(
															responseText);

												}

											}
										});
							}

						});
		
	    function jia(obj){
			var sourceNode = $("#div_1");
			var cloneNode = sourceNode.clone();
			cloneNode.removeClass("first");
			sourceNode.parent().append(cloneNode);
		};
		
		
		function jian(obj){
			var delNode = $(obj).parent();
			var firstdiv=$("#div").children().first();
			firstdiv.addClass("first");	
			if(delNode.attr("class")!=firstdiv.attr("class"))
			{
				delNode.remove();
			}
		};
</script>
    
</body>
</html>