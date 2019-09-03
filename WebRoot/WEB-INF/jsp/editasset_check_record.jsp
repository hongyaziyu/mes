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

<title>修改设备点检项信息</title>
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
							var x = $("#workTable").find("td").length / 2 + 1;
							var str;
							var assetName = $("#workTable").children().first()
									.next().children().children().children()
									.val();
							
							str = "<tr><td><input type='text' class='form-control' readonly style='width: 150px;' name='assetName"+x+"' value='"+assetName+"' /></td><td><input type='text'  class='form-control' style='width: 800px;'name='result"+x+"' value=''  /></td></tr>";
							$(this).next().children().last().append(str);
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

			var num = $("#workTable").find("td").length / 2;
			$("#num").val(num);
			$("#form").submit();
		});
	});
</script>
</head>
<body>
	<form id="form" class="form-inline"
		action="${pageContext.request.contextPath}/manage/saveOrupdateAssetCheckRecord.action">
		<div class="container" style="margin-top: 50px;">
			<div class="row">
				<h2 class="col-md-2">修改设备点检项信息</h2>
			</div>

			<!-- 添加的num确定不确定数目的值 -->
			<input type="hidden" id="num" name="num" value="">

			<div class="container">
				<input type="button" class="btn btn-primary" id="delete3"style="float: right;" value="删除" />
				<input class="btn btn-primary" style="float: right;margin-right: 10px;" id="addwork" type="button" value="添加" />

				<table class="table table-hover" id="workTable">
					<thead>
						<tr>
							<th>设备名称</th>
							<th>设备具体点检内容</th>
						</tr>
					</thead>
					<c:forEach items="${listAssetCheckContent}" var="c" varStatus="stauts">
						<tbody>
							<tr>
								<td><input type="text" class="form-control" readonly style="width: 150px;"
									name="assetName${stauts.index+1}" value="${c.assetName}" />
								</td>
								<td><input type="text" class="form-control" style="width: 800px;"
									name="result${stauts.index+1}" value="${c.result} " />
								</td>
								
							</tr>
							<input type="hidden" name="id${stauts.index+1}" value="${c.id}" />
					</c:forEach>
					</tbody>
				</table>

			</div>

			<div class="container" style="text-align:right">
				<input type="button" class="btn btn-primary" id="tj" value="提交" />
			</div>
		</div>
	</form>
</body>
</html>

