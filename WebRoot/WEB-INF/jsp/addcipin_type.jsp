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

<title>添加不良品类型信息</title>
<script src="../js/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.min.js"></script>
<script src="../js/bootstrap-datetimepicker.js"></script>
<script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>

<script type="text/javascript">
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

		//生产车间工序顺序添加按钮对应点击事件
		$("#addwork")
				.click(
						function() {
							var x = $("#workTable").find("td").length / 3 + 1;
							//console.log(x);
							var str;
							str = "<tr><td><input type='text' class='form-control' name='shopName"+x+"' /></td><td><input type='text' value='' class='form-control' name='cipinType"+x+"'/></td><td><input type='text' class='form-control' name='cipinDetail"+x+"' /></td></tr>";
							$(this).next().children().append(str);
							//添加的部分：确定num2值，知道子件输入的个数判断
							$("#num").val(x);
						});

		//生产车间工序顺序删除按钮对应点击事件
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

			var num = $("#workTable").find("td").length / 3;
			$("#num").val(num);
			$("#form").submit();
		});
	});
</script>
</head>
<body>
	<form id="form" class="form-inline"
		action="${pageContext.request.contextPath}/manage/insertCipin.action">
		<div class="container" style="margin-top: 50px;">
			<div class="row">
				<h2 class="col-md-2">添加不良品类型信息</h2>
			</div>

			<!-- 添加的num确定不确定数目的值 -->
			<input type="hidden" id="num" name="num" value="">

			<div class="container">
				<input type="button" class="btn btn-primary" id="delete3"
					style="float: right;" value="删除" /> <input class="btn btn-primary"
					style="float: right;margin-right: 10px;" id="addwork" type="button"
					value="添加" />

				<table class="table table-hover" id="workTable">
					<tr>
						<th>车间</th>
						<th>不良品类别</th>
						<th>不良品种类</th>
					</tr>
					<tr>
						<td><input type="text" class="form-control" name="shopName1" />
						</td>
						<td><input type="text" class="form-control" name="cipinType1" />
						</td>
						<td><input type="text" class="form-control"
							name="cipinDetail1" />
						</td>
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

