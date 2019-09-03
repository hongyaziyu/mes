<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
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

<title>添加半成品库存信息</title>
<script src="../js/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.min.js"></script>
<script src="../js/bootstrap-datetimepicker.js"></script>
<script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>

<script type="text/javascript">
	$(function() {
		//生产车间工序顺序添加按钮对应点击事件
		$("#addwork")
				.click(
						function() {
							var x = $("#workTable").find("td").length / 5 + 1;
							//console.log(x);
							var str;
							str = "<tr><td><input type='text' class='form-control' name='materialNo"+x+"' /></td><td><input type='text' value='' class='form-control' name='productName"+x+"'/></td><td><input type='text' class='form-control' name='type"+x+"' /></td><td><input type='text' class='form-control' name='num"+x+"' /></td><td><input type='text' class='form-control' name='unit"+x+"' /></td></tr>";
							$(this).next().children().append(str);
							//添加的部分：确定quantiy值，知道子件输入的个数判断
							$("#quantiy").val(x);
						});

		//半成品库存删除按钮对应点击事件
		$("#delete3").click(function() {
			//			console.log($(this).length);
			var x = $("#workTable").find("td").length;
			if (x < 4) {
				alert("已是最后一行！");
			} else {
				$(this).next().next().find("tr").last().remove();
			}

		});
		$("#tj").click(function() {

			var quantiy = $("#workTable").find("td").length / 5;
			$("#quantiy").val(quantiy);
			$("#form").submit();
		});
	});
</script>
</head>
<body>
	<form id="form" class="form-inline"
		action="${pageContext.request.contextPath}/manage/insertMiddleInventory.action">
		<div class="container" style="margin-top: 50px;">
			<div class="row">
				<h2 class="col-md-2">添加半成品库存信息</h2>
			</div>

			<!-- 添加的num确定不确定数目的值 -->
			<input type="hidden" id="quantiy" name="quantiy" value="">

			<div class="container">
				<input type="button" class="btn btn-primary" id="delete3"
					style="float: right;" value="删除" /> <input class="btn btn-primary"
					style="float: right;margin-right: 10px;" id="addwork" type="button"
					value="添加" />

				<table class="table table-hover" id="workTable">
					<tr>
						<th>物料号</th>
						<th>产品名称</th>
						<th>产品规格</th>
						<th>库存数量</th>
						<th>单位</th>
					</tr>
					<tr>
						<td><input type="text" class="form-control" name="materialNo1" /></td>
						<td><input type="text" class="form-control" name="productName1" /></td>
						<td><input type="text" class="form-control" name="type1" /></td>
						<td><input type="text" class="form-control" name="num1" /></td>
						<td><input type="text" class="form-control" name="unit1" /></td>
					</tr>

				</table>

			</div>

			<div class="container" style="text-align:right">
				<input type="button" class="btn btn-primary" id="tj" value="提交" />
			</div>
		</div>
	</form>
</body>
</html>

