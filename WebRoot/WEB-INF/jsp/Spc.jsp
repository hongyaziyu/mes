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
    <title>Spc曲线计划</title>
   
<!-- link是引入css的包 -->
<link href="../css/bootstrap-multiselect.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" href="../css/bootstrap.min.new.css" />
<link rel="stylesheet" href="../css/main.css" />
<link rel="stylesheet" href="../css/easyui.css" />
<link rel="stylesheet" href="../css/icon.css" />
<link rel="stylesheet" href="../css/layer.css" />

<!-- script是引入js的包 -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../js/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/echarts.common.min.js"></script>
<script type="text/javascript" src="../js/layer.js"></script>
</head>

</head>
<body>
	<div class="container">
		<h4 class="myClass navbar-left">Spc曲线计划</h4>
		<form class="form-inline top" method="post"
			action="${pageContext.request.contextPath}/plan/SelectSpc.action">
			<input type="hidden" id="spcId" name="spcId" value="">
			<div class="form-group">
				<label for="equipmentName">图号</label> <input type="text" id="mjwl"
					name="clientMaterialNo" style="width:120px" class="form-control"
					id="mjwl">
			</div>
			<div class="form-group">
				<label for="equipmentNum">物料号</label> 
				<select id="wl" name="materialNo" style="width:150px" class="mini-input">
							<!-- 默认一开始时的状态 -->
							<option disabled selected style="display: none;">--请选择--</option>
				</select>
			</div>
			<div class="form-group">
				<label for="equipmentNum">批次号</label>
				<select id="pc" name="batchNo" class="mini-input" style="width:150px;">
						<option disabled selected style="display: none;">--请选择--</option>
				</select>
			</div>
			
			<div class="form-group">
				<label for="equipmentNum">工序</label> 
				<select id="gx" name="process" style="width:120px;" class="mini-input">
							<!-- 默认一开始时的状态 -->
							<option disabled selected style="display: none;">--请选择--</option>
				</select>
				<!-- <input type="text"
					name="process" style="width:120px" class="form-control" id="gx">-->
			</div> 
			 <div class="form-group">
				<label for="equipmentNum">特征值</label> <input type="text"
					name="characterVal" style="width:120px" class="form-control"
					id="tzz">
			</div> 
			&nbsp;&nbsp;
			<button type="submit" class="btn btn-primary">查询</button>

			&nbsp;&nbsp;&nbsp;&nbsp;

		</form>
		<br />
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th>图号</th>
						<th>物料号</th>
						<th>批次号</th>
						<th>产品名称</th>
						<th>工序</th>
						<th>特征值</th>
						<th>采集时间(起始日期-截止日期)</th>
						<th>中值</th>
						<th>上公差限</th>
						<th>下公差限</th>
						<th>生成Spc曲线</th>
						<th>过程能力评估</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${spc1.clientMaterialNo}</td>
						<td>${spc1.materialNo}</td>
						<td>${spc1.batchNo}</td>
						<td>${spc1.productName}</td>
						<td>${spc1.processName}</td>
						<td>${spc1.characterVal}</td>
						<td>
						<c:choose>
							<c:when test="${(!empty spc1.makeStartDate)&&(spc1.makeStartDate!=' ')}">
							     ${spc1.makeStartDate}—${spc1.makeEndDate}
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>	
						</td>
						<td>${spc1.mid}</td>
						<td><fmt:formatNumber value="${spc1.usl}" pattern="#.##" type="number"/></td>
						<td><fmt:formatNumber value="${spc1.lsl}" pattern="#.##" type="number"/></td>
						<td>
							<c:if test="${(!empty spc1.batchNo)&&(spc1.batchNo!=' ')}">
							    <input class="btn  btn-sm btn-primary" type="button" value="生成Spc曲线" onclick="revise(this)">
							</c:if>		
						</td>
						<td>
							<c:if test="${(!empty spc1.batchNo)&&(spc1.batchNo!=' ')}">
							    <input class="btn  btn-sm btn-danger" type="button" value="过程能力评估"  onclick="revice(this)">
							</c:if>	
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<!-- Spc的X均值控制图 -->
		<div id="bight1" style="width: 1140px;height:400px; "></div>
		<br /> <br /> <br /> <br />
		<!-- Spc表格显示的jsp -->
		
		
		<div id="table">
			<jsp:include page="Spc_table.jsp"></jsp:include>
		</div>
		<br /> <br /> <br /> <br />
		
		
		<!-- Spc的R极差控制图 -->
		<div id="bight2" style="width: 1140px;height:400px; "></div>
		
	</div>
</body>
<script type="text/javascript">
//过程能力评估
    function revice(obj) {
  //Spc过程能力评估
		$.ajax({
					url : "${pageContext.request.contextPath}/plan/SpcPCIAjax.action",
					data : {
						clientMaterialNo : $(obj).parent().prev().prev().prev()
								.prev().prev().prev().prev().prev().prev()
								.prev().prev().html(),
						materialNo : $(obj).parent().prev().prev().prev()
								.prev().prev().prev().prev().prev().prev().prev()
								.html(),
						batchNo : $(obj).parent().prev().prev().prev().prev()
								.prev().prev().prev().prev().prev()
								.html(),
						process : $(obj).parent().prev().prev().prev()
								.prev().prev().prev().prev().html(),
						characterVal : $(obj).parent().prev().prev().prev()
								.prev().prev().prev().html(),
					},
					type : "POST",
					success : function(data) {
					//接受后端传过来的Cpk与Ppk
					//定义一个变量拼接文字和变量
					var PCI="过程能力指数:Cpk="+data.Cpk+"</br>"+"过程绩效指数:Ppk="+data.Ppk;
					layer.alert(PCI, {
		                skin: 'layui-layer-molv', //样式类名
		                closeBtn: 0
		            }, function(){
		               layer.closeAll();
		            });
				    
					},
					error : function(data) {
								layer.alert("Spc过程能力生成失败!", {
				                skin: 'layui-layer-molv', //样式类名
				                closeBtn: 0
				            }, function(){
				               layer.closeAll();
				            });
				    }
				});

}

</script>
<!--Ajax 点击生成将4个数据从前端传到后台 -->
<script type="text/javascript">
    //一开始隐藏Spc表和Spc图
	$("#bight1").hide();
	$("#table").hide();
	$("#bight2").hide();
	function revise(obj) {
		//Spc表格
		$.ajax({
					url : "${pageContext.request.contextPath}/plan/SpcTableShowAjax.action",
					data : {
						clientMaterialNo : $(obj).parent().prev().prev().prev()
								.prev().prev().prev().prev().prev().prev()
								.prev().html(),
						materialNo : $(obj).parent().prev().prev().prev()
								.prev().prev().prev().prev().prev().prev()
								.html(),
						batchNo : $(obj).parent().prev().prev().prev()
								.prev().prev().prev().prev().prev()
								.html(),
						process : $(obj).parent().prev().prev().prev()
								.prev().prev().prev().html(),
						characterVal : $(obj).parent().prev().prev().prev()
								.prev().prev().html(),
					},
					type : "POST",
					success : function(responseText) {
					//显示Spc表格
                        $("#table").show();
						$("#table").html(responseText);
						//表格样式引入需要的js文件
						$.getScript("../js/jquery.easyui.min.js");
					},
					error : function(responseText) {
						layer.alert("Spc表格生成失败!", {
				                skin: 'layui-layer-molv', //样式类名
				                closeBtn: 0
				            }, function(){
				               layer.closeAll();
				            });
					}
				});



		//Spc曲线
		$.ajax({
					url : "${pageContext.request.contextPath}/plan/SpcBightShowAjax.action",
					data : {
						clientMaterialNo : $(obj).parent().prev().prev().prev()
								.prev().prev().prev().prev().prev().prev()
								.prev().html(),
						materialNo : $(obj).parent().prev().prev().prev()
								.prev().prev().prev().prev().prev().prev()
								.html(),
					    batchNo : $(obj).parent().prev().prev().prev()
								.prev().prev().prev().prev().prev()
								.html(),
						process : $(obj).parent().prev().prev().prev()
								.prev().prev().prev().html(),
						characterVal : $(obj).parent().prev().prev().prev()
								.prev().prev().html(),
					},
					type : "POST",
					success : function(data) {
					//1.X均值控制图
				
					//显示Spc的X均值控制图
						$("#bight1").show();
					//定义曲线变量，给表格变量赋值
						var optionsAjax1 = myChart1.getOption();
						var dataArr1 = new Array();
						var dataArr2 = new Array();
						var dataArr3 = new Array();
						var dataArr4 = new Array();
						//var dataArr4 = new Array();(如何赋于数组不同的值)
						for(var i=0;i<25;i++){
						//将数组date（相当于date中的UCLx）的值赋于dateArr数组
							dataArr1.push(data.UCLx);
							dataArr2.push(data.Mid_x);
							dataArr3.push(data.LCLx);
						}
						
						//.push同.add都是按顺序添加进数组
						dataArr4.push(data.Ave_0);
						dataArr4.push(data.Ave_1);
						dataArr4.push(data.Ave_2);
						dataArr4.push(data.Ave_3);
						dataArr4.push(data.Ave_4);
						dataArr4.push(data.Ave_5);
						dataArr4.push(data.Ave_6);
						dataArr4.push(data.Ave_7);
						dataArr4.push(data.Ave_8);
						dataArr4.push(data.Ave_9);
						dataArr4.push(data.Ave_10);
						dataArr4.push(data.Ave_11);
						dataArr4.push(data.Ave_12);
						dataArr4.push(data.Ave_13);
						dataArr4.push(data.Ave_14);
						dataArr4.push(data.Ave_15);
						dataArr4.push(data.Ave_16);
						dataArr4.push(data.Ave_17);
						dataArr4.push(data.Ave_18);
						dataArr4.push(data.Ave_19);
						dataArr4.push(data.Ave_20);
						dataArr4.push(data.Ave_21);
						dataArr4.push(data.Ave_22);
						dataArr4.push(data.Ave_23);
						dataArr4.push(data.Ave_24); 
						
						
						//再将数组值赋于serier[0]-[3]的date
						optionsAjax1.series[0].data = dataArr1;
						optionsAjax1.series[1].data = dataArr2;
						optionsAjax1.series[2].data = dataArr3;
						optionsAjax1.series[3].data = dataArr4;
						//给yAxis的最小值赋值
						optionsAjax1.yAxis[0].min=data.min;
						//再将optionsAjax1类放进曲线中
						myChart1.setOption(optionsAjax1);
						
					
					
					
						
					//2.R极差控制图	
						
						//显示Spc的R极差控制图
						$("#bight2").show();
						//定义曲线变量，给表格变量赋值
						var optionsAjax2 = myChart2.getOption();
						var dataArr5 = new Array();
						var dataArr6 = new Array();
						var dataArr7 = new Array();
						
						for(var i=0;i<25;i++){
						//将数组date（相当于date中的UCLx）的值赋于dateArr数组
							dataArr5.push(data.UCLr);
							dataArr6.push(data.Ave_R);
						}
					 	
						//.push同.add都是按顺序添加进数组
						dataArr7.push(data.R_0);
						dataArr7.push(data.R_1);
						dataArr7.push(data.R_2);
						dataArr7.push(data.R_3);
						dataArr7.push(data.R_4);
						dataArr7.push(data.R_5);
						dataArr7.push(data.R_6);
						dataArr7.push(data.R_7);
						dataArr7.push(data.R_8);
						dataArr7.push(data.R_9);
						dataArr7.push(data.R_10);
						dataArr7.push(data.R_11);
						dataArr7.push(data.R_12);
					    dataArr7.push(data.R_13);
						dataArr7.push(data.R_14);
						dataArr7.push(data.R_15);
						dataArr7.push(data.R_16);
						dataArr7.push(data.R_17);
						dataArr7.push(data.R_18);
						dataArr7.push(data.R_19);
						dataArr7.push(data.R_20);
						dataArr7.push(data.R_21);
						dataArr7.push(data.R_22);
						dataArr7.push(data.R_23);
						dataArr7.push(data.R_24); 
						
						
						//再将数组值赋于serier[0]-[3]的date
						optionsAjax2.series[0].data = dataArr5;
						optionsAjax2.series[1].data = dataArr6;
						optionsAjax2.series[2].data = dataArr7;
						
						//再将optionsAjax1类放进曲线中
						myChart2.setOption(optionsAjax2);
					},
					error : function(data) {
						layer.alert("Spc曲线生成失败!", {
				                skin: 'layui-layer-molv', //样式类名
				                closeBtn: 0
				            }, function(){
				               layer.closeAll();
				            });
					}
				});
				
				
	}
	
	

//1.X均值控制图
	// 基于准备好的dom，初始化echarts实例
	var myChart1 = echarts.init(document.getElementById('bight1'));
	// 指定图表的配置项和数据
	option = {
		title : {
			text : 'X均值控制图'
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : ['UCLx','X中值','LCLx','X平均']
		},
		grid : {
			left : '3%',
			right : '4%',
			bottom : '3%',
			containLabel : true
		},
		 toolbox : {
			feature : {
				saveAsImage : {}
			}
		}, 
		xAxis : {
			type : 'category',
			boundaryGap : false,
			data : [ '1', '2', '3', '4', '5', '6', '7', '8', '9',
			 '10', '11', '12', '13', '14','15', '16', '17', '18', 
			 '19', '20', '21','22', '23', '24', '25']
		},
		yAxis : {
			type : 'value',
			name :'X均值',
			 nameLocation: 'center',
		        nameGap: '45',
		        nameRotate:90,
		        nameTextStyle: {     
		        	fontSize: 14,
		        	fontWeight: 'bold'
		       }
		},
		series : [ 
		{
			name : 'UCLx',
			type : 'line',
			data : []
		},
		{
			name : 'X中值',
			type : 'line',
			data : []
		},
		{
			name : 'LCLx',
			type : 'line',
			data : []
		},
		{
			name : 'X平均',
			type : 'line',
			data:[]
		}
		]
	};
	// 使用刚指定的配置项和数据显示图表。
	myChart1.setOption(option);
	
	
	
//2.R极差控制图
	// 基于准备好的dom，初始化echarts实例
	var myChart2 = echarts.init(document.getElementById('bight2'));
	// 指定图表的配置项和数据
	option = {
		title : {
			text : 'R极差控制图'
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : ['UCLr','R中值','R']
		},
		grid : {
			left : '3%',
			right : '4%',
			bottom : '3%',
			containLabel : true
		},
		toolbox : {
			feature : {
				saveAsImage : {}
			}
		},
		xAxis : {
			type : 'category',
			boundaryGap : false,
			data : [ '1', '2', '3', '4', '5', '6', '7', '8', '9',
			 '10', '11', '12', '13', '14','15', '16', '17', '18', 
			 '19', '20', '21','22', '23', '24', '25']
		},
		yAxis : {
			type : 'value',
			name :'R极差',
			 nameLocation: 'center',
		        nameGap: '45',
		        nameRotate:90,
		        nameTextStyle: {
		        	fontSize: 14,
		        	fontWeight: 'bold'
		       }
		},
		series : [ 
		{
			name : 'UCLr',
			type : 'line',
			data : []
		},
		{
			name : 'R中值',
			type : 'line',
			data : []
		},
		{
			name : 'R',
			type : 'line',
			data : []
		}
		]
	};
	// 使用刚指定的配置项和数据显示图表。
	myChart2.setOption(option);
	
	
	
	$(function() {
	//物料号：根据图号得到对应的物料号，并下拉框显示
			if ($("#spcId").val() != null && $("#spcId").val() != ""&&$("#wl").val() != null && $("#wl").val() != "") {

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
								if (($("#wl").val() == null || $("#wl").val() == "")&& ($("#spcId").val() == null || $("#spcId").val() == "")) {

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
	
	
	  //未完成的批次号查询
		if ($("#pc").val() != null && $("#pc").val() != "") {
			$("#pc").mousedown(
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

		$("#pc").mousemove(
						function(){
							if ($("#pc").val() == null|| $("#pc").val() == "") {
								$.ajax({
											url : "${pageContext.request.contextPath}/plan/picihaoAjax.action",
											data : {
												materialNo : $("#wl").val(),
											},
											type : "POST",
											success : function(responseText) {

												if (responseText != null
														&& responseText != "") {

													$("#pc").html(responseText);

												}

											}
										});
							}

						});
	
	
	//工序：根据物料号得到对应的工序，并下拉框显示
			if ($("#spcId").val() != null && $("#spcId").val() != ""&&$("#gx").val() != null && $("#gx").val() != "") {

				$("#gx").mousedown(
								function() {
									$.ajax({
												url : "${pageContext.request.contextPath}/plan/SpcgxAjax.action",
												data : {
													clientMaterialNo : $("#wl").val(),
												},
												type : "POST",
												success : function(responseText) {

													if (responseText != null
															&& responseText != "") {
														//成功后返回responseText到id为wl的内容中
														$("#gx").html(responseText);
													}

												}
											});
								});
			}


			//工序：根据物料号得到对应的工序，并下拉框显示
			$("#gx").mousemove(
							function() {
								if (($("#gx").val() == null || $("#gx").val() == "")&& ($("#spcId").val() == null || $("#spcId").val() == "")) {

									$.ajax({
												url : "${pageContext.request.contextPath}/plan/SpcgxAjax.action",
												data : {
													materialNo : $("#wl").val(),
												},
												type : "POST",
												success : function(responseText) {

													if (responseText != null
															&& responseText != "") {
														//成功后返回responseText到id为wl的内容中
														$("#gx").html(responseText);

													}

												}
											});

								}
							});
	
	
	});
</script>


</html>

