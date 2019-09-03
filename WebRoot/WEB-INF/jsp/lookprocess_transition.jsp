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

<title>工序交接记录次品详细信息</title>
<script src="../js/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.min.js"></script>

</head>
<body>
	<form id="form" class="form-inline"
		action="${pageContext.request.contextPath}/record/toselectProcessTransition.action">
		<div class="container" style="margin-top: 50px;">
			<div class="row">
				<h2>工序交接记录次品详细信息</h2>
			</div>

			<div class="container">
			
				<table class="table table-hover" id="workTable">
					<thead>
						<tr>
							<th>次品类别</th>
							<th>次品种类</th>
							<th>次品数量</th>
						</tr>
					</thead>
					<c:forEach items="${listTransitionCipin}" var="c" varStatus="stauts">
						<tbody>
							<tr>
								<td><input type="text" class="form-control" readonly
									name="cipinType" value="${c.cipinType}" />
								</td>
								<td><input type="text" class="form-control" readonly
									name="cipinSpecies" value="${c.cipinSpecies} " />
								</td>
								<td><input type="text" class="form-control" readonly
									name="cipinNum" value="${c.cipinNum}" />
								</td>
							</tr>
					</c:forEach>
					</tbody>
				</table>

			</div>

			<div class="container" style="text-align:right">
				<input type="submit" class="btn btn-primary" value="返回" />
			</div>
		</div>
	</form>
</body>
</html>

