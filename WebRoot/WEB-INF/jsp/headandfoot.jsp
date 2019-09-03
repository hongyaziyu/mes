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
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1 , user-scalable=no">
<link href="../css/bootstrap-multiselect.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" href="../css/bootstrap.min.new.css" />
<link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet"
	media="screen">
<link rel="stylesheet" href="../css/main.css" />
<!-- 三级导航自定义样式 -->
<link rel="stylesheet" href="../css/three_nav.css" />
</head>
<body>
	<!--导航-->

	<nav class="navbar navbar-default">
		<div class="container">
			<!--小屏幕导航按钮和logo-->
			<div class="navbar-header">
				<button class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<img src="../img/logo.png" style="opacity: 0.8;" />
			</div>
			<!--小屏幕导航按钮和logo-->
			<!--导航-->
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a
						href="${pageContext.request.contextPath}/record/toHomePage.action"><span
							class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;首页</a>
					</li>
					<li class="dropdown"><a id="dLabel" type="button"
						data-toggle="dropdown"> <span
							class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;计划 <span
							class="caret"></span> </a>
								
						<ul class="dropdown-menu">
						   
							<li><a href="${pageContext.request.contextPath}/plan/toProductionPlan.action"><span
													class="glyphicon glyphicon-file"></span>&nbsp;&nbsp;周生产计划</a>
							</li>
							
							<li><a
								href="${pageContext.request.contextPath}/plan/toApplyMaterial.action"><span
									class="glyphicon glyphicon-th"></span>&nbsp;&nbsp;申请领料计划</a>
							</li>
							<li><a
								href="${pageContext.request.contextPath}/plan/toinsertTrackCard.action"><span
									class="glyphicon glyphicon-sort"></span>&nbsp;&nbsp;跟踪单计划</a>
							</li>
							<li><a
								href="${pageContext.request.contextPath}/plan/toProductabnormal.action"><span
									class="glyphicon glyphicon-floppy-remove"></span>&nbsp;&nbsp;产品异常单计划</a>
							</li>
							<li><a
								href="${pageContext.request.contextPath}/plan/toProductTest.action"><span
									class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;产品检测计划</a>
							</li>
							<li><a
								href="${pageContext.request.contextPath}/plan/toSelectSpc.action"><span
									class="glyphicon glyphicon-picture"></span>&nbsp;&nbsp;制定Spc曲线计划</a>
							</li>
							<li><a
								href="${pageContext.request.contextPath}/plan/tojhorpcorQR.action"><span
									class="glyphicon glyphicon-qrcode"></span>&nbsp;&nbsp;二维码制定计划</a>
							</li>
							
							<li><a href="${pageContext.request.contextPath}/plan/toSpareProductionPlan.action"><span
													class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;备用:周生产计划</a>
							</li>
						</ul></li>
					<li class="dropdown"><a id="dLabel" type="button"
						data-toggle="dropdown"> <span
							class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp;记录 <span
							class="caret"></span> </a>
						<ul class="dropdown-menu">
							<li><a
								href="${pageContext.request.contextPath}/jdbc/toSaleOrderList.action"><span
									class="glyphicon glyphicon-shopping-cart"></span>&nbsp;&nbsp;订单记录</a>
							</li>
							
							<li><a href="${pageContext.request.contextPath}/record/toMonthPlan.action"><span
													class="glyphicon glyphicon-time"></span>&nbsp;&nbsp;月计划记录</a>
							</li>
							
							<li><a href="${pageContext.request.contextPath}/record/toProductionPlanList.action"><span
												class="glyphicon glyphicon-file"></span>&nbsp;&nbsp;周计划记录</a>
							</li>
							
							<li><a href="${pageContext.request.contextPath}/record/toShopPlanList.action"><span 
							 	class="glyphicon glyphicon-road"></span>&nbsp;&nbsp;车间排产记录</a></li>
							
							<li class="dropdown-submenu1">  
                                    <a href="#"><span
									class="glyphicon glyphicon-thumbs-up"></span>&nbsp;&nbsp;审批记录</a>
                                    <ul class="dropdown-menu">  
							            <li><a
											href="${pageContext.request.contextPath}/record/SelectMaterial.action"><span
												class="glyphicon glyphicon-thumbs-up"></span>&nbsp;&nbsp;领料审批记录</a>
										</li>
										<li><a
											href="${pageContext.request.contextPath}/record/toApproveSecMaterial.action"><span
												class="glyphicon glyphicon-thumbs-up"></span>&nbsp;&nbsp;领辅料审批记录</a>
										</li>
										<li><a
											href="${pageContext.request.contextPath}/record/toTemprice.action"><span
												class="glyphicon glyphicon-thumbs-up"></span>&nbsp;&nbsp;临时工价审批记录</a>
										</li>
                                    </ul>  
                            </li>
							
							
						    <li class="dropdown-submenu2">  
                                    <a href="#"><span
									class="glyphicon glyphicon-th"></span>&nbsp;&nbsp;原材料领/退记录</a>
                                    <ul class="dropdown-menu">  
				                        <li><a
												href="${pageContext.request.contextPath}/record/InputMaterialsRecord.action"><span
													class="glyphicon glyphicon-th"></span>&nbsp;&nbsp;领原材料记录</a>
							            </li>
										<li><a
											href="${pageContext.request.contextPath}/record/OutputMaterialsRecord.action"><span
												class="glyphicon glyphicon-th"></span>&nbsp;&nbsp;退原材料记录</a>
										</li>
                                    </ul>  
                            </li>
							
							
							<li class="dropdown-submenu3">  
                                    <a href="#"><span
									class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;辅料领/退记录</a>
                                    <ul class="dropdown-menu">  
							            <li><a
											href="${pageContext.request.contextPath}/record/toInputSecMaterialsRecord.action"><span
												class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;领辅料记录</a>
										</li>
										<li><a
											href="${pageContext.request.contextPath}/record/toOutputSecMaterialsRecord.action"><span
												class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;退辅料记录</a>
										</li>
                                    </ul>  
                            </li>
							
							
							<li class="dropdown-submenu4">  
                                    <a href="#"><span
									class="glyphicon glyphicon-th-large"></span>&nbsp;&nbsp;半成品出入库记录</a>
                                    <ul class="dropdown-menu">  
									    <li><a
											href="${pageContext.request.contextPath}/record/InputMiddleMaterialsRecord.action"><span
												class="glyphicon glyphicon-th-large"></span>&nbsp;&nbsp;半成品出库记录</a>
										</li>
										<li><a
											href="${pageContext.request.contextPath}/record/OutputMiddleMaterialsRecord.action"><span
												class="glyphicon glyphicon-th-large"></span>&nbsp;&nbsp;半成品入库记录</a>
										</li>
                                    </ul>  
                            </li>
							
							
                            <li><a
								href="${pageContext.request.contextPath}/record/toOutputFullMaterialsRecord.action"><span
									class="glyphicon glyphicon-book"></span>&nbsp;&nbsp;成品入库记录</a>
							</li>
							<li><a
								href="${pageContext.request.contextPath}/record/getTrackCard.action"><span
									class="glyphicon glyphicon-sort"></span>&nbsp;&nbsp;跟踪单记录</a>
							</li>
							
							
							<li class="dropdown-submenu5">  
                                    <a href="#"><span
									class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;检验相关记录</a>
                                    <ul class="dropdown-menu">  
										<li><a
											href="${pageContext.request.contextPath}/record/SelectProductTest.action"><span
												class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;产品检测记录</a>
										</li>
											<li><a
												href="${pageContext.request.contextPath}/record/DailyCheck.action"><span
											class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;日检记录</a>
										</li>
										<li><a
											href="${pageContext.request.contextPath}/record/selectProductAbnormal.action"><span
												class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;产品异常单记录</a>
										</li>
										<li><a
											href="${pageContext.request.contextPath}/record/toSpcrecord.action"><span
												class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;Spc记录</a>
										</li>
										
										<li><a
											href="${pageContext.request.contextPath}/record/selectWorkCard.action"><span
												class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;次品率记录</a>
										</li>
										<li><a
											href="${pageContext.request.contextPath}/record/SelectProductionRecordInquiry.action"><span
												class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;生产记录</a>
										</li>
                                    </ul>  
                            </li>
                            
                            <li class="dropdown-submenu1">  
                                    <a href="#"><span
									class="glyphicon glyphicon-share"></span>&nbsp;&nbsp;外协单据记录</a>
                                    <ul class="dropdown-menu">  
							            <li><a
											href="${pageContext.request.contextPath}/record/toMaterialsAssociation"><span
												class="glyphicon glyphicon-share"></span>&nbsp;&nbsp;原材料外协出库记录</a>
										</li>
										 <li><a
											href="${pageContext.request.contextPath}/record/toIntputMaterialsAssociation.action"><span
												class="glyphicon glyphicon-share"></span>&nbsp;&nbsp;原材料外协入库记录</a>
										</li>
										<li><a
											href="${pageContext.request.contextPath}/record/toExterAssociation.action"><span
												class="glyphicon glyphicon-share"></span>&nbsp;&nbsp;部分外协出/入库记录</a>
										</li>
									
                                    </ul>  
                            </li>
							<li class="dropdown-submenu6">  
                                    <a href="#"><span
									class="glyphicon glyphicon-yen"></span>&nbsp;&nbsp;工资记录</a>
                                    <ul class="dropdown-menu">  
									    <li><a
											href="${pageContext.request.contextPath}/record/SalarySelect.action"><span
												class="glyphicon glyphicon-yen"></span>&nbsp;&nbsp;工资单记录</a>
										</li>
										<li><a
											href="${pageContext.request.contextPath}/record/SalaryDetailSelect.action"><span
												class="glyphicon glyphicon-yen"></span>&nbsp;&nbsp;工资单详细记录</a>
										</li>
                                    </ul>  
                            </li>
                            
                            
							<li><a
									href="${pageContext.request.contextPath}/record/toselectProcessTransition.action"><span
								class="glyphicon glyphicon-check"></span>&nbsp;&nbsp;工序交接记录</a>
							</li>
							
							<li><a
								href="${pageContext.request.contextPath}/record/moldRecord.action"><span
									class="glyphicon glyphicon-inbox"></span>&nbsp;&nbsp;模具出入库记录</a>
							</li>
							
							<li><a
								href="${pageContext.request.contextPath}/record/tobreakdown.action"><span
									class="glyphicon glyphicon-remove-sign"></span>&nbsp;&nbsp;设备故障记录</a>
							</li>
							
							<li><a
								href="${pageContext.request.contextPath}/record/toselectProductionPlan.action"><span
									class="glyphicon glyphicon-eye-open"></span>&nbsp;&nbsp;订单进度记录</a>
							</li>
							
							
						</ul></li>
					<li class="dropdown"><a id="dLabel" type="button"
						data-toggle="dropdown"> <span
							class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;管理 <span
							class="caret"></span> </a>
						<ul class="dropdown-menu">
							<li><a
								href="${pageContext.request.contextPath}/manage/togetAsset.action"><span
									class="glyphicon glyphicon-remove-sign"></span>&nbsp;&nbsp;设备管理</a>
							</li>
							<%-- <li><a
								href="${pageContext.request.contextPath}/manage/togetMolds.action"><span
									class="glyphicon glyphicon-inbox"></span>&nbsp;&nbsp;模具管理</a>
							</li> --%>
							<li><a
								href="${pageContext.request.contextPath}/manage/toSecMaterial.action"><span
									class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;辅料管理</a>
							</li>
							
							<li class="dropdown-submenu">  
                                    <a href="#"><span
									class="glyphicon glyphicon-wrench"></span>&nbsp;&nbsp;检具管理</a>
                                    <ul class="dropdown-menu">  
									    <li><a
											href="${pageContext.request.contextPath}/manage/togetHeightGauge.action"><span
												class="glyphicon glyphicon-wrench"></span>&nbsp;&nbsp;高度规管理</a>
										</li>
										<li><a
											href="${pageContext.request.contextPath}/manage/togetSpecialGauge.action"><span
												class="glyphicon glyphicon-wrench"></span>&nbsp;&nbsp;特殊规管理</a>
										</li>
                                    </ul>  
                            </li>
                            
							<li><a
								href="${pageContext.request.contextPath}/manage/toSelectProduct.action"><span
									class="glyphicon glyphicon-file"></span>&nbsp;&nbsp;产品表管理</a>
							</li>
							<li><a
								href="${pageContext.request.contextPath}/manage/toMiddleInventory.action"><span
									class="glyphicon glyphicon-th-large"></span>&nbsp;&nbsp;半成品库存管理</a>
							</li>
							<li><a
								href="${pageContext.request.contextPath}/manage/toCipin.action"><span
									class="glyphicon glyphicon-floppy-remove"></span>&nbsp;&nbsp;不良品类型管理</a>
							</li>
							<li><a
								href="${pageContext.request.contextPath}/manage/toAssetCheckRecord.action"><span
									class="glyphicon glyphicon-check"></span>&nbsp;&nbsp;设备点检项管理</a>
							</li>
							<li><a
								href="${pageContext.request.contextPath}/manage/LimitManage.action"><span
									class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;人员权限管理</a>
							</li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
				
				
				
				
				<!-- 车间主任显示未完成审批数量 -->
				<c:if test="${user.position=='车间主任'}">
				<li class="dropdown">
					<c:if test="${num1+num2+num3+num4!='0'}">
					<a id="user" type="button" style="color:red;" data-toggle="dropdown">未完成审批:(${num1+num2+num3+num4})<span class="caret"></span></a>
					</c:if>	
						<ul class="dropdown-menu">
						<c:if test="${num1!='0'}">
							<li><a  href="${pageContext.request.contextPath}/record/SelectMaterial.action"><span
									class="glyphicon glyphicon-exclamation-sign"></span>&nbsp;&nbsp;领料审批 :(${num1})</a>
							</li>
						</c:if>
						<c:if test="${num2!='0'}">
                            <li><a  href="${pageContext.request.contextPath}/record/toApproveSecMaterial.action"><span
									class="glyphicon glyphicon-exclamation-sign"></span>&nbsp;&nbsp;领辅料审批 :(${num2})</a>
							</li>
						</c:if>
						<c:if test="${num3!='0'}">
							<li><a  href="${pageContext.request.contextPath}/record/toTemprice.action"><span
									class="glyphicon glyphicon-exclamation-sign"></span>&nbsp;&nbsp;临时工价审批 :(${num3})</a>
							</li>
						</c:if>
						</ul>
					</li>
				</c:if>
				  
				  
				  
				  
					<li class="dropdown"><a id="user" type="button"
						data-toggle="dropdown"> 当前用户： ${user.personName} <span
							class="caret"></span> </a>
						<ul class="dropdown-menu">
							<li><a id="xiugaimima" href="#" data-toggle="modal"
								data-target="#myModal"><span
									class="glyphicon glyphicon-lock"></span>&nbsp;&nbsp;修改密码</a>
							</li>

						</ul>
					</li>
					<li><a
						href="${pageContext.request.contextPath}/user/logout.action"><span
							class="glyphicon glyphicon-off"></span>&nbsp;&nbsp;退出</a>
					</li>
				</ul>
			</div>

			<!--导航-->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div id="cont" style="position:relative;top:220px; width:66%;"
						class="modal-content">
						<div id="edit" class="">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">修改密码</h4>
							</div>

							<div class="modal-body">
								<label for="PASSWORD">旧密码</label>&nbsp;&nbsp; <input
									type="password" id="Passwd1" class="form-control"
									name="oldPassword" placeholder="请在此输入旧密码" required /> <br/> <label
									for="PASSWORD">新密码</label>&nbsp;&nbsp; <input type="password"
									id="Passwd2" class="form-control" name="newPassword"
									placeholder="请在此输入新密码" required /> <br/> <label for="PASSWORD">确认新密码</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label
									id="error" class="hidden" style="color:#0024dd;font-size:14px;">两次密码输入不一致！</label>
								<input type="password" id="Passwd3" class="form-control"
									name="newPassword2" placeholder="请在此输入新密码" required />
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">关闭</button>
								<button type="submit" id="sub" class="btn btn-primary">
									提交更改</button>
							</div>
						</div>
						<div id="result" class="hidden">
							<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">修改密码</h4>
							</div>

							<div class="modal-body">
								<div class='modal-body'>
									<h3 id="flag">修改密码成功！</h3>
								</div>
							</div>
							<div class="modal-footer">

								<button type="submit" id="sub" class="btn btn-primary"
									data-dismiss='modal'>关闭</button>
							</div>
						</div>
					</div>

				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		
	</nav>
	<!--导航-->




	<!--footer-->
	<footer class="navbar-fixed-bottom">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<p>
						Copyright&nbsp;©&nbsp;<span id="year"></span>&nbsp;&nbsp;安徽红桥金属制造有限公司&nbsp;&nbsp;电话：+86-563-2279508
					</p>
				</div>
			</div>
		</div>
	</footer>
	</body>
	<!--footer-->
	<script src="../js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript">
		$(function() {
			var myDate = new Date();
			var year = myDate.getFullYear();
			$("#year").html("2016-" + year);
			$("#xiugaimima").click(function() {
				$("#edit").removeClass("hidden");
				$("#result").addClass("hidden");
				$("#error").addClass("hidden");
				$("#Passwd1").val(null);
				$("#Passwd2").val(null);
				$("#Passwd3").val(null);
			});
			$("#sub")
					.click(
							function() {
								if ($("#Passwd2").val() == $("#Passwd3").val()) {
									$("#error").addClass("hidden");
									$
											.ajax({
												url : "${pageContext.request.contextPath}/user/editPassword.action",
												data : {
													oldPassword : $("#Passwd1")
															.val(),
													newPassword : $("#Passwd2")
															.val(),
												},
												type : "POST",
												success : function(responseText) {

													if (responseText == 1) {

														$("#edit").addClass(
																"hidden");
														$("#flag").text(
																"修改密码成功!");
														$("#result")
																.removeClass(
																		"hidden");
													} else {

														$("#edit").addClass(
																"hidden");
														$("#flag").text(
																"修改密码失败!");
														$("#result")
																.removeClass(
																		"hidden");
													}

												}
											});
								} else {
									$("#error").removeClass("hidden");
								}
							});


         //前提条件：只对于车间主任才实现刷新(定时执行该任务：60秒间隔)车间主任需求：添加对审批数量的查询
		var position="${user.position}";
		if(position=='车间主任'){
				setInterval(function(){  
			          $.getJSON("${pageContext.request.contextPath}/user/refresh.action");
			          //重新刷新页面
			           location.reload();
                 },60000);
		
		
		
		}
		
		
		});
		
	</script>