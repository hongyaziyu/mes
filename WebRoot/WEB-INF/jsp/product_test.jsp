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
    <title>产品检测计划</title>
    <link href="../css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="../css/bootstrap.min.new.css"/>
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="../css/main.css"/>
</head>
<div class="container">
    <h4>产品检测计划</h4>
        <form id="form" class="form-inline" action="${pageContext.request.contextPath}/plan/addProductTest.action" method="post">
            <table class="table table-hover">
              <tr>
              	   <td><label for="exampleInputName2">客户</label></td>
                   <td><input  type="text" class="form-control" name="client"  value="${listProductTest[0].client}"></td>
                             
                   <td><label for="exampleInputName2">图号</label></td>
                   <td><input id="mjwl" type="text" class="form-control" name="clientMaterialNo"  value="${listProductTest[0].clientMaterialNo }"></td>
                
                   <td><label for="exampleInputName2">物料号</label></td>
	               <td><select id="wl" name="materialNo" class="mini-input" >
	                 <!-- 一开始下拉框应该为禁止选中状态，<option disabled selected style="display: none;"> -->
				      		<option disabled selected style="display: none;">--请选择--</option>
				      		<c:if test="${listProductTest[0].materialNo!=null}">
				      		<option value="${listProductTest[0].materialNo}" selected="selected">${listProductTest[0].materialNo}</option>
				      		</c:if>			     
			            </select>
		           </td>
                  <td><label for="exampleInputName2">计划单号</label></td>
					<td><select id="jihuadanhao" name="planNo" class="mini-input" >
							<option disabled selected style="display: none;">--请选择--</option>
							<c:if test="${listProductTest[0].planNo!=null}">
				      		<option value="${listProductTest[0].planNo}" selected="selected">${listProductTest[0].planNo}</option>
				      		</c:if>
					</select>
					</td>
              </tr>
              <tr>
             
                   <td><label for="exampleInputName2">批次号</label></td>
                    <td><select id="picihao" name="batchNo" class="mini-input" >
							<option disabled selected style="display: none;">--请选择--</option>
							<c:if test="${listProductTest[0].batchNo!=null}">
				      		<option value="${listProductTest[0].batchNo}" selected="selected">${listProductTest[0].batchNo}</option>
				      		</c:if>
					</select>
					</td>
             
              </tr>
          </table>
        <h4>产品测量记录</h4>
        <div id="div">
        	<jsp:include page="productTestDiv.jsp"></jsp:include>
        </div>
                <br />
            <input id="testId"  name="testId" type="hidden" value="${listProductTest[0].testId }"/>
            <input  type="hidden" id="num" name="num"  value="">
            <input  type="hidden" id="testId" name="testId"  value=""> 
            <button id="tijiao" type="button" class="btn btn-primary navbar-right">提交</button>  
           <br/><br/> <br/><br/>   
             <!-- 警告信息弹窗 -->
        <div class="alert alert-danger hidden alert-dismissible" >	 
		  <strong>警告信息：</strong> 该图号不存在，请检查！
		</div> 
        </form>
        
</div>



<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="../js/jquery-3.2.1.min.js"></script>  
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/bootstrap-datetimepicker.js"></script>
        <script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
        <script src="../js/my.js"></script>
<script type="text/javascript" >//$(document).ready(function(){
	//window.onload = orderjs();
	$(function(){
		/* order_js();
		calender(); */
		//未完成的计划单号查询
		if ($("#jihuadanhao").val() != null && $("#jihuadanhao").val() != "") {
			$("#jihuadanhao").mousedown(function() {		
								$.ajax({
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
						
						
						
		//未完成的批次号查询
		if ($("#picihao").val() != null && $("#picihao").val() != "") {
			$("#picihao").mousedown(
							function() {
								$.ajax({
											url : "${pageContext.request.contextPath}/plan/picihaoAjax.action",
											data : {
												materialNo : $("#wl").val(),
											},
											type : "POST",
											success : function(responseText) {

												if (responseText != null
														&& responseText != "") {

													$("#picihao").html(responseText);
												}

											}
										});

							});
		}

		$("#picihao").mousemove(
						function(){
							if ($("#picihao").val() == null|| $("#picihao").val() == "") {
								$.ajax({
											url : "${pageContext.request.contextPath}/plan/picihaoAjax.action",
											data : {
												materialNo : $("#wl").val(),
											},
											type : "POST",
											success : function(responseText) {

												if (responseText != null
														&& responseText != "") {

													$("#picihao").html(responseText);

												}

											}
										});
							}

						});
						
		if($("#testId").val()!=null&&$("#testId").val()!=""){
			$(".btn-default").addClass("hidden");
		}
		$("#wl").blur(function(){
			 $.ajax({
					 	 url:"${pageContext.request.contextPath}/plan/productTestAjax.action",						 
						 data:{
							clientMaterialNo:$("#mjwl").val(),
							materialNo:$("#wl").val(),
						 },
						 type:"POST",
						 success:function(responseText){
							$("#div").html(responseText);
							
						 }
			}); 
			
			
		});
		//物料号：根据图号得到对应的物料号，并下拉框显示
	if($("#testId").val()!=null&&$("#testId").val()!=""&&$("#wl").val()!=null&&$("#wl").val()!=""){
			
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
			if(($("#wl").val()==null||$("#wl").val()=="")&&($("#testId").val()==null||$("#testId").val()=="")){
			
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
								$(".alert").removeClass("hidden");
							}else{
						//addClass()添加这个类	
								$(".alert").addClass("hidden");
							}
				    		
						 }
			}); 
			
			
		});
		$("#tijiao").click(function(){
			var j=1;
			$(".used").each(function(){
					//alert($(this).children().first().next().children().first().children().first().children().first().html());		
				var gongxu =$(this).children().first().next().children().first().children().first().children().first().children().first();				
				gongxu.attr("name","gongxu" + j);
				
				var tezheng = gongxu.parent().next().children().first();
				tezheng.attr("name","tezheng" + j); 
				
				var biaozhunzhi =tezheng.parent().next().children().first().next();
				biaozhunzhi.attr("name","biaozhunzhi" + j);
				
				var biaozhunzhiPlus =biaozhunzhi.next();
				biaozhunzhiPlus.attr("name","biaozhunzhiPlus" + j);
				var biaozhunzhiMinus =biaozhunzhiPlus.next();	
				biaozhunzhiMinus.attr("name","biaozhunzhiMinus" + j);			
				
				
				var clz1 =biaozhunzhi.parent().next().children().first();
				clz1.attr("name","clz1" + j);
				var clz2 =clz1.parent().next().children().first();
				clz2.attr("name","clz2" + j);
				var clz3 =clz2.parent().next().children().first();
				clz3.attr("name","clz3" + j);
				var clz4 = clz3.parent().parent().next().children().first().children().first();
				clz4.attr("name","clz4" + j);
				
				
				var clz5 = clz4.parent().next().children().first();
				clz5.attr("name","clz5" + j);
				var caozuogong =clz5.parent().next().children().first();
				caozuogong.attr("name","caozuogong" + j);
				var pihao =caozuogong.parent().next().children().first();
				pihao.attr("name","pihao" + j);
				var panding =pihao.parent().next().children().first();
				panding.attr("name","panding" + j);
				//alert(panding.html());
				var processId =panding.next();
				processId.attr("name","processId" + j);
				j++;
				$("#num").val(j-1);
			});
			
			$("form").submit();
			
		});
	});
	function jia(obj){			
			var sourceNode = $(obj).parent().parent().parent().parent().parent();	
			var cloneNode = sourceNode.clone();
			cloneNode.children().first().next().children().first().children().first().next().children().last().prev().children().last().val(null);
			sourceNode.after("<div  class='used'></div>");
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
	}
	function jian(obj){
			if($("#testId").val()==null||$("#testId").val()==""){
				var delNode = $(obj).parent().parent().parent().parent().parent();
				var firstdiv=$("#div").children().first();
				firstdiv.addClass("first");	
				if(delNode.attr("class")!=firstdiv.attr("class")){
					delNode.remove();
				}
				/* var gongxu = $(obj).parent().parent().prev().children().first().next().children().first();				
				var tezhengzhi = gongxu.parent().next().children().first();	 */
				var processId=tezhengzhi.parent().parent().next().children().first().next().next().next().next().next().children().first().next();
				
				$.ajax({
						url:"${pageContext.request.contextPath}/plan/productTestAjax2.action",						 
						data:{
							processId:processId.val(),
							
						},
						type:"POST",
						success:function(responseText){
								
								
						}
				}); 
			}		 	
	}
</script>
    
</body>
</html>