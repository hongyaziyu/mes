<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="headandfoot.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>


<title>跟踪单计划</title>
<script src="../js/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.min.js"></script>
<script src="../js/bootstrap-datetimepicker.js"></script>
<script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="../js/bootstrap-multiselect.js" type="text/javascript"></script>


<style type="text/css">
.float-right {
	position: absolute;
}

.box {
	width: 50%;
	margin-top: 10%;
	margin: auto;
	padding: 28px;
	height: 450px;
	border: 1px #111 solid;
	display: none;
}
/* 默认对话框隐藏 */
.
box .x {
	font-size: 18px;
	text-align: right;
	display: block;
}
</style>

</head>
<body>

	<div class="container">

		<form id="form" class="form-inline"
			action="${pageContext.request.contextPath}/plan/insertTrackCard.action">
			<h4>跟踪单计划</h4>
			<table class="table table-hover">
				<input type="hidden" id="cardId" name="cardId" value="">
				<tr>
					<td><label for="planNo">客户:</label>
					</td>
					<td>
					<input type="text" class="form-control" name="client" value="" />
					</td>
					<td><label for="clientMaterialNo">图号:</label>
					</td>
					<td><input type="text" id="mjwl" class="form-control"
						name="clientMaterialNo" value="" />
					</td>
					<!-- 一开始下拉框应该为禁止选中状态，<option disabled selected style="display: none;"> -->
					<td><label for="exampleInputName2">物料号:</label>
					</td>
					<td><select id="wl" name="materialNo" class="mini-input">
							<!-- 默认一开始时的状态 -->
							<option disabled selected style="display: none;">--请选择--</option>
					</select></td>
					<td><label for="exampleInputName2">计划单号:</label>
					</td>
					<td><select id="jihuadanhao" name="planNo" class="mini-input">
							<option disabled selected style="display: none;">--请选择--</option>
					</select></td>

				</tr>
				<tr>
					<td><label for="batchNo">批次号:</label></td>
					<td>
					<select id="picihao" name="batchNo" class="mini-input">
							<option disabled selected style="display: none;">--请选择--</option>
					</select>
					</td>
					
					<td><label for="productionOrder">计划数量:</label>
					</td>
					<td><input type="text" id="number" class="form-control"
						name="productionOrder" value="" />
					</td>
					<td><label for="productNo">产品号:</label>
					</td>
					<td><input type="text" class="form-control" name="productNo"
						value="" />
					</td>
					<td><label for="productDes">产品描述:</label>
					</td>
					<td><input type="text" class="form-control" name="productDes"
						value="" />
					</td>
				</tr>
				<tr>
					<td><label for="productFig">产品图号:</label>
					</td>
					<td><input type="text" class="form-control" name="productFig"
						value="" />
					</td>
					<!-- <td><label for="materialSpec">材料牌号规格</label>
					</td> 
					<td><input type="text" class="form-control"
						name="materialSpec" value="" />
					</td>-->
					
					 <td><label for="lupihao">材料炉批号:</label>
					</td>
					<td><input type="text" class="form-control" name="lupihao"
						value="" />
					</td>
					<td><label for="materialBatchNo">材料批次号/材料牌号规格</label>
					</td>
					<td style="width:250px">
					<span id="sp">
						    <select id="cailiaopicihao" 
								name="materialBatchNo"
								class="mini-input multiselect multiselect1 " multiple="multiple">
									<option disabled selected style="display: none;">--请选择--</option>
							</select>
					</span>	
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span onClick="change(1)" class="glyphicon glyphicon-pencil"></span>	
				    </td>
				   
					
				</tr>
				
			</table>

			<div id='inputbox' class="box">
				<a class='x' href='' ; onclick="change(0); return false;">关闭</a>
				<div id="main">
					<div class="add">
						<span>组件1</span><input type="text" class="form-control"
							name="text1" value=""style="width:330px"> <a id="addTextImput" href="#">添加组件</a>&nbsp;&nbsp;
						<a class="del-text" href='#'>删除组件</a> <br> <br>
					</div>
				</div>
				<br> <input id="submit" type="button" value="确定">
			</div>

			<div class="container" style="text-align:right">
				<input type="submit" class="btn btn-primary" id="tj" value="提交" />
			</div>
			<br /> <br />
			<!-- 警告信息弹窗 -->
			<div class="alert alert-danger hidden alert-dismissible">
				<strong>警告信息：</strong> 该图号不存在，请检查！
			</div>

		</form>
	</div>

	<script type="text/javascript">
		//添加文本框
		$(function() {
		
			var i = 2;
			$("#addTextImput").click(
							function() {
								//alert();
								if (i < 7) {
									$("#main")
											.append(
													"<div><span>组件"
															+ i
															+ "</span><input type='text' name='text"+i+"' id='del-text"+i+"'  class='form-control' value=''/><br><br></div>");
									i++;
								} else {
									alert("最多能添加6个");
								}
							});

			$(".del-text").click(function() {
				var x = 0;
				x = $(".add").parent().find("div").length;
				//alert(x);
				if (x > 1) {
					$(".add").parent().find("div").last().remove();
					i--;
				} else {
					alert("已是最后一个了！");
				}
			});

		});

		//弹出窗口
		function change(n) {
			document.getElementById('inputbox').style.display = n ? 'block'
					: 'none'; /* 点击按钮打开/关闭 对话框 */
		}

		$("#submit").click(function() {
			var numArr = []; // 定义一个空数组
			var txt = $('#inputbox').find(':text'); // 获取所有文本框
			for ( var i = 0; i < txt.length; i++) {
				numArr.push(txt.eq(i).val()); // 将文本框的值添加到数组中
			}
	
            var html="<select id='cailiaopicihao' name='materialBatchNo' class='mini-input multiselect multiselect1' multiple='multiple'>";
			
			//获取多选框中的值，循环拼接
			  var str=$("#cailiaopicihao").val();	          
		          
			for ( var i = 0; i < str.length; i++) {
			html+="<option value=" + str[i] + " selected='selected'>" + str[i] + "</option>";
			
			}
			
			//循环拼接文本框中的值
			for ( var i = 0; i < numArr.length; i++) {
				html+="<option value=" + numArr[i] + " selected='selected'>" + numArr[i] + "</option>";
			}
			html=html+"</select>";
			alert(html);
			$("#sp").html(html);
			
			$(".multiselect").multiselect({
				maxHeight : 300,
				enableFiltering : true,
				enableCaseInsensitiveFiltering : true,
				filterPlaceholder : '',
				nonSelectedText : '--请选择--',
				nSelectedText : '个已选择',
				numberDisplayed : 2
			});
			

		});

			//生产指令查询（车间排产表中的计划数量）
			       if($("#cardId").val()!=null&&$("#cardId").val()!=""&&$("#picihao").val()!=null&&$("#picihao").val()!=""){
						
						 $("#number").mousedown(function(){	
							$.ajax({
								 	 url:"${pageContext.request.contextPath}/plan/NumberAjax.action",						 
									 data:{
										batchNo:$("#picihao").val(),							
									 },
									 type:"POST",
									 success:function(data){
										$("#number").val(data.number);
									 
									 }
							});
						});
					 } 
					 
				 if($("#picihao").val()==null||$("#picihao").val()==""){
						
						 $("#number").mousemove(function(){	
							$.ajax({
								 	 url:"${pageContext.request.contextPath}/plan/NumberAjax.action",						 
									 data:{
										batchNo:$("#picihao").val(),							
									 },
									 type:"POST",
									 success:function(data){
										$("#number").val(data.number);
									 
									 }
							});
						});
					 } 
					 
					  
		//材料批次号查询	
		if ($("#cailiaopicihao").val() != null&& $("#cailiaopicihao").val() != "") {
			$("#sp").mousedown(
							function() {
							//alert(1);

								$.ajax({
											url : "${pageContext.request.contextPath}/record/cailiaopicihaoAjax.action",
											data : {
												clientMaterialNo : $("#mjwl").val(),
												materialNo : $("#wl").val(),
												batchNo : $("#picihao").val(),
											},
											type : "POST",
											success : function(responseText) {

												if (responseText != null
														&& responseText != "") {
														
											$("#sp").html(responseText);
													
													$(".multiselect1").multiselect({
														maxHeight : 300,
														enableFiltering : true,
														enableCaseInsensitiveFiltering : true,
														filterPlaceholder : '',
														nonSelectedText : '--请选择--',
														nSelectedText : '个已选择',
														numberDisplayed : 2
													});
												}

											}
										});

							});
		}

		$("#sp").mousemove(function() {
							//alert(1);	
							if ($("#cailiaopicihao").val() == null|| $("#cailiaopicihao").val() == "") {
								$.ajax({
											url : "${pageContext.request.contextPath}/record/cailiaopicihaoAjax.action",
											data : {
												clientMaterialNo : $("#mjwl").val(),
												materialNo : $("#wl").val(),
												batchNo : $("#picihao").val(),
											},
											type : "POST",
											success : function(responseText) {
												if (responseText != null
														&& responseText != "") {
												
												//赋拼接后的整个多选框内容（包括<select><option>）
													$("#sp").html(responseText);
													 $(".multiselect1").multiselect({
														maxHeight : 300,
														enableFiltering : true,
														enableCaseInsensitiveFiltering : true,
														filterPlaceholder : '',
														nonSelectedText : '--请选择--',
														nSelectedText : '个已选择',
														numberDisplayed : 2
													}); 
													
												}

											}
										});
							}
						});

		//未完成的计划单号查询
		if ($("#jihuadanhao").val() != null && $("#jihuadanhao").val() != "") {
			$("#jihuadanhao").mousedown(
							function() {
								alert("aa");
								$.ajax({
											url : "${pageContext.request.contextPath}/record/jihuadanhaoAjax.action",
											data : {
												clientMaterialNo : $("#mjwl").val(),
												materialNo : $("#wl").val(),
											},
											type : "POST",
											success : function(responseText) {

												if (responseText != null
														&& responseText != "") {

													$("#jihuadanhao").html(responseText);
												}

											}
										});

							});
		}

		$("#jihuadanhao").mousemove(
						function(){
							if ($("#jihuadanhao").val() == null|| $("#jihuadanhao").val() == "") {
								$.ajax({
											url : "${pageContext.request.contextPath}/record/jihuadanhaoAjax.action",
											data : {
												clientMaterialNo : $("#mjwl").val(),
												materialNo : $("#wl").val(),
											},
											type : "POST",
											success : function(responseText) {

												if (responseText != null
														&& responseText != "") {

													$("#jihuadanhao").html(responseText);

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
						
						
						
			//物料号：根据图号得到对应的物料号，并下拉框显示
			if ($("#cardId").val() != null && $("#cardId").val() != ""&& $("#wl").val() != null && $("#wl").val() != "") {

				$("#wl").mousedown(
								function() {
									$.ajax({
												url : "${pageContext.request.contextPath}/plan/shopMjwlAjax1.action",
												data : {
													clientMaterialNo : $("#mjwl").val(),
												},
												type : "POST",
												success : function(responseText) {

													if (responseText != null
															&& responseText != "") {
														//成功后返回responseText到id为wl的内容中
														$("#wl").html(responseText);
													}

												}
											});
								});
			}

			//物料号：根据图号得到对应的物料号，并下拉框显示
			$("#wl").mousemove(
							function() {
								if (($("#wl").val() == null || $("#wl").val() == "")&& ($("#cardId").val() == null || $("#cardId").val() == "")) {

									$.ajax({
												url : "${pageContext.request.contextPath}/plan/shopMjwlAjax1.action",
												data : {
													clientMaterialNo : $("#mjwl").val(),
												},
												type : "POST",
												success : function(responseText) {

													if (responseText != null
															&& responseText != "") {
														//成功后返回responseText到id为wl的内容中
														$("#wl").html(responseText);

													}

												}
											});

								}
							});

			//图号显示：检查有无这条图号信息
			//返回的回调函数为：有则移除这个类Strong中的警告，无则添加这个警告类
			//.blur(function()为失去焦点事件
			$("#mjwl").blur(
							function() {
								$.ajax({
											url : "${pageContext.request.contextPath}/plan/shopWlAjax1.action",
											data : {
												clientMaterialNo : $("#mjwl").val(),
											},
											type : "POST",
											success : function(responseText) {

												if (responseText != null&& responseText != "") {

													//removeClass()移除这个类
													$(".alert").removeClass("hidden");
												} else {
													//addClass()添加这个类	
													$(".alert").addClass("hidden");
												}

											}
										});

							});
		

		
	</script>
	<script type="text/javascript">
				
		//下拉框显示
		$(function() {
			$(".multiselect").multiselect({
				maxHeight : 300,
				maxWidth : 100,
				enableFiltering : true,
				enableCaseInsensitiveFiltering : true,
				filterPlaceholder : '',
				nonSelectedText : '--请选择--',
				nSelectedText : '个已选择',
				numberDisplayed : 2
			});
		});
	</script> 
</body>
</html>

