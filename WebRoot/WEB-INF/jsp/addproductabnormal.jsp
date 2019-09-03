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

<title>产品异常单计划</title>

<script src="../js/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.min.js"></script>
<script src="../js/bootstrap-datetimepicker.js"></script>
<script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>

<script>
	$(function() {
		$('.date').datetimepicker({
			language : 'zh-CN',
			weekStart : 0,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 3,
			minView : 2,
			forceParse : 0
		});

	});
	$(function() {
		$("#add")
				.click(
						function() {
							var x = 0;
							x = $("#add").parent().find("div").length;
							console.log(x);
							var str;
							str = "<div><br /><select name='department"
									+ (x + 1)
									+ "' class='form-control' style='width: 110px;'><option value='品保部' >品保部</option><option value='制造部'>制造部</option><option value='技术部'>技术部</option><option value='其他部门'>其他部门</option></select><textarea name='opinion"
									+ (x + 1)
									+ "' class='form-control' style='width: 1110px; margin-top: 10px;' rows='4' ></textarea></div>";
							if (x < 4) {
								$("#add").parent().find("div").last().append(
										str);
							} else {
								alert("超出添加上限！");
							}

						});

		$("#delete").click(function() {
			var x = 0;
			x = $("#add").parent().find("div").length;
			if (x > 1) {
				$("#delete").parent().find("div").last().remove();
			} else {
				alert("已是最后一项！");
			}
		});

		$("#tj").click(function() {
			//			console.log($(this).length);
			var num1 = $("#add").parent().find("div").length;

			$("#num1").val(num1);
			//alert(num1);
			$("#form").submit();
		});

	});
</script>
<script>
	$(function() {
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
						
		//未完成的计划单号查询
		if ($("#jihuadanhao").val() != null && $("#jihuadanhao").val() != "") {
			$("#jihuadanhao")
					.mousedown(
							function() {
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
							}

						});
		//物料号：根据图号得到对应的物料号，并下拉框显示
		if ($("#cardId").val() != null && $("#cardId").val() != ""
				&& $("#wl").val() != null && $("#wl").val() != "") {

			$("#wl").mousedown(
							function() {
								$.ajax({
											url : "${pageContext.request.contextPath}/plan/shopMjwlAjax1.action",
											data : {
												clientMaterialNo : $("#mjwl")
														.val(),
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
							if (($("#wl").val() == null || $("#wl").val() == "")
									&& ($("#cardId").val() == null || $(
											"#cardId").val() == "")) {

								$.ajax({
											url : "${pageContext.request.contextPath}/plan/shopMjwlAjax1.action",
											data : {
												clientMaterialNo : $("#mjwl")
														.val(),
											},
											type : "POST",
											success : function(responseText) {

												if (responseText != null
														&& responseText != "") {
													//成功后返回responseText到id为wl的内容中
													$("#wl").html(responseText);

													//bootstrap框架-multiselect下拉框组件
													$(".multiselect1")
															.multiselect(
																	{
																		//1、显示默认的提示信息。
																		placeholder : '--请选择--',
																		//2.下拉列表的最大高度,默认值为 250。
																		maxHeight : 300,
																		enableFiltering : true,
																		enableCaseInsensitiveFiltering : true,
																		filterPlaceholder : '',
																		nonSelectedText : '--请选择--',
																		nSelectedText : '个已选择',
																		numberDisplayed : 4
																	});
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

											if (responseText != null
													&& responseText != "") {

												//removeClass()移除这个类
												$(".alert").removeClass(
														"hidden");
											} else {
												//addClass()添加这个类	
												$(".alert").addClass("hidden");
											}

										}
									});

						});

	});
</script>

</head>

<body>
	<div class="container" style="margin-top: 50px;">
		<div class="row">
			<h4>产品异常单计划</h4>
		</div>

		<form id="form" class="form-inline"
			action="${pageContext.request.contextPath}/plan/insertProductAbnormal.action">
			<input type="hidden" id="num1" name="num1" value=""> <input
				type="hidden" id="abnormalId" name="abnormalId" value="">

			<table class="table table-hover">
				<tr>
					<td><label for="clientMaterialNo">客户</label>
					</td>
					<td><input type="text" id="" class="form-control"
						name="client" value="" />
					</td>

					<td><label for="clientMaterialNo">图号</label>
					</td>
					<td><input type="text" id="mjwl" class="form-control"
						name="clientMaterialNo" value="" />
					</td>

					<!-- 一开始下拉框应该为禁止选中状态，<option disabled selected style="display: none;"> -->
					<td><label for="exampleInputName2">物料号</label>
					</td>
					<td><select id="wl" name="materialNo" class="mini-input">
							<option disabled selected style="display: none;">--请选择--</option>
					</select></td>


					<td><label for="exampleInputName2">计划单号</label>
					</td>
					<td><select id="jihuadanhao" name="planNo" class="mini-input">
							<option disabled selected style="display: none;">--请选择--</option>
					</select></td>
				</tr>
				<tr>
					<td><label for="materiaNo">批次号</label>
					</td>
					<td>
					<select id="picihao" name="batchNo" class="mini-input">
							<option disabled selected style="display: none;">--请选择--</option>
					</select>
					</td>
					
					<td><label for="site">内部/外部</label>
					</td>
					<td><select class="form-control" style="width: 180px;"
						name="site">
							<option value="内部">内部</option>
							<option value="外部">外部</option>
					</select></td>
					<td><label for="exampleInputName2">制定日期</label>
					</td>
					<td>
						<div class="input-group date form_date"
							data-date-format="yyyy-mm-dd">
							<input class="form-control" style="width: 105px" type="text"
								name="produceDate"> <span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"></span> </span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span> </span>
						</div></td>
					<td><label for="abnormalNum">异常数量</label>
					</td>
					<td><input type="text" class="form-control" name="abnormalNum"
						value="" />
					</td>

				</tr>
				<tr>
					<td><label for="sendPerson">填报人</label>
					</td>
					<td><input type="text" class="form-control" name="sendPerson"
						value="" />
					</td>
				</tr>
			</table>
			<hr />
			<!-- 警告信息弹窗 -->
			<div class="alert alert-danger hidden alert-dismissible">
				<strong>警告信息：</strong> 该图号不存在，请检查！
			</div>
			<div class="container" style="margin-top: 50px;">
				<h4>产品描述</h4>
				<hr />

				<textarea class="form-control" style="width: 1110px;" rows="4"
					name="abnormalDesc" placeholder="注意：异常描述时要描述清楚什么时间、什么地点（或工序）、谁在操作。"></textarea>

			</div>

			<div class="container" style="margin-top: 50px;">
				<h4 style="float: left">部门意见</h4>
				<input type="button" style="float: right;" class="btn btn-primary"
					id="delete" value="删除" /> <input type="button"
					style="float: right;margin-right: 10px;" class="btn btn-primary"
					id="add" value="插入" /> <br /> <br />
				<hr />
				<div>
					<select class="form-control" style="width: 110px;"
						name="department1">
						<option value="品保部">品保部</option>
						<option value="制造部">制造部</option>
						<option value="技术部">技术部</option>
						<option value="其他部门">其他部门</option>
					</select>
					<textarea class="form-control"
						style="width: 1110px; margin-top: 10px;" rows="4" name="opinion1"></textarea>
				</div>
				<br /> <br />

			</div>

			<div class="container" style="margin-top: 50px;">
				<h4>跟踪验证</h4>
				<hr />

				<textarea class="form-control" style="width: 1110px;" rows="4"
					name="trackVerification"></textarea>

			</div>

			<div style="margin-top: 50px;">
				<input id="tj" class="btn btn-primary" style="float: right"
					type="button" value="提交" />
			</div>
		</form>
	</div>
</body>
</html>
