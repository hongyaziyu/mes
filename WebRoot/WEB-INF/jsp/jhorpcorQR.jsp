<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="headandfoot.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>二维码制定计划</title>
<script src="../js/jquery-3.2.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/jQuery.print.js"></script>
<script type="text/javascript" src="../js/jquery.qrcode.min.js"></script>
<script type="text/javascript" src="../js/layer.js"></script>
<script type="text/javascript">
	//函数function() utf16to8 函数支持将中文utf-16编码格式转换成utf-8（支持二维码的中文转码）
	function utf16to8(str) {
		var out, i, len, c;
		out = "";
		len = str.length;
		for (i = 0; i < len; i++) {
			c = str.charCodeAt(i);
			if ((c >= 0x0001) && (c <= 0x007F)) {
				out += str.charAt(i);
			} else if (c > 0x07FF) {
				out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
				out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
				out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
			} else {
				out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
				out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
			}
		}
		return out;
	}

	$(function() {

		//1.生成二维码
		$("#show").click(function() {
		
			//获取文本框中的内容作为二维码的内容
			var text = $("#print").val();
		
			$("#qrcBody").qrcode({
				width : 250,
				height : 250,
				//调用function() utf16to8 函数支持将中文utf-16编码格式转换成utf-8
				text : utf16to8(text)//二维码的内容
			});

		});

		//2.清空二维码
		$("#remove").click(function() {
			$("#qrcBody").empty();
		});

		//3.打印二维码	
		$("#preview").click(function() {
			var qrcSrc = $("canvas")[0].toDataURL(); //二维码canvas转img
			//var upLogSrc = "img/kaoshen.jpg";//二维码中间的logo
			$("#qrcBodyImg .qrcImg").attr("src", qrcSrc);
			//$("#qrcBodyImg .upLogo").attr("src", upLogSrc);
			$("#qrcBody").hide(); //隐藏canvas部分

			$("#qrcBodyImg").show(); //显示img部分

			$("#qrcBodyImg").print(); //打印当前二维码图片
		});

		

		//4.计划单号查询有无（周计划表中）
		$("#select1").click(function() {
							$.ajax({
										url : "${pageContext.request.contextPath}/plan/JHDHUpBatchNoAjax.action",
										data : {
											planNo : $("#print").val(),
										},
										type : "POST",
										success : function(responseText) {

											if (responseText != null
													&& responseText != "") {
                                                
												$("#div1").html(responseText);
												$("#div1").removeClass("hidden");
											} 

										}
									});

						});
						

	});
	
</script>

<script type="text/javascript">
//循环显示0-99下拉
$(function(){
var a=document.getElementById("pc");
	for(var i=1;i<99;i++){
	a.options[i]=new Option(i+1,i);
	}

});
	
</script>
<!-- 二维码样式设置 -->
<style type="text/css">
#qrcBodyImg {
	text-align: center;
}

#qrcBody {
	text-align: center;
}
</style>

</head>

<body>



	<div class="container">
		<h2>二维码制定计划</h2>
		<br />
		 <form id="form" class="form-inline"  action="${pageContext.request.contextPath}/plan/excelAjax.action" method="post">
		
		<p style="font-size:16px;">
		
		计划单号:/临时二维码制定:
		<input type="text" class="btn btn-sm" style="border-color:#00BBFF" id="print" name="planNo"/>
		<input type="button" class="btn btn-primary" value="计划下批次号查询" id="select1"/>
		分几批：<select class="form-control" id="pc"  name="num" >
						 	<option value="1" selected>1</option>	
			  </select>&nbsp;
		<select class="form-control" name="wxorzc" >
							<option value="正常" selected>正常</option>
							<option value="外协">外协</option>
							
			  </select>
		<input type="submit" class="btn btn-info" value="三码合一excel" id="select2" /> 
		<input type="button" class="btn btn-warning" value="生成二维码" id="show" />
		<input type="button" class="btn btn-danger" value="清空二维码" id="remove" />
		<input type="button" class="btn btn-success" value="打印二维码" id="preview" />
		</p>
		<!-- 下面是给计划单号下的批次号预留的空间 -->
		<div id="div1" style="margin-left: 80px;" class="hidden">
		   <jsp:include page="planNoUpbatchNo.jsp"></jsp:include>
		</div>
	
		<!-- 下面是给二维码预留的空间 -->
		<div class="qrcBody" id="qrcBody"></div>
		<!-- 下面是给二维码转成图片预留的空间 -->
		<div class="qrcBody" id="qrcBodyImg" style="display:none">
			<img class="qrcImg" src=""> <img
				class="align-center-middle upLogo" src="">
		</div>
		</form>
	</div>
</body>
</html>
