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
<title>用户权限修改</title>
<!-- <link rel="stylesheet" href="../css/editperson.css"/> -->
<script src="../js/jquery-3.2.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/bootstrap-multiselect.js" type="text/javascript"></script>

</head>
<body>
	<div style="position:relative;left:20%;" class="container">

		<h1>&nbsp&nbsp&nbsp&nbsp&nbsp用户权限修改</h1>
		<form type="post" style="width:30%" class="form-inline"
			action="${pageContext.request.contextPath}/manage/UpdateLimitManage.action">

			<input type="hidden" name="personId" value="${person.personId}" /> <input
				type="hidden" id="num" name="num" value="">
			<div>
				<label>工号</label> <input type="text" style="width:100%"
					class="form-control" name="number" readonly  value="${person.number}"
					required="required" />
			</div>
			<div>
				<label>姓名</label> <input type="text" style="width:100%"
					class="form-control" name="personName" readonly value="${person.personName}"
					required="required" />
			</div>
			<div>
				<label>职位</label> <input type="text" style="width:100%"
					class="form-control" name="position" readonly value="${person.position}"
					required="required" />
			</div>
			<div>
				<label>部门</label> <input type="text" style="width:100%"
					class="form-control" name="department" readonly value="${person.department}"
					required="required" />
			</div>
			<div>
				<label>邮箱</label> <input type="text" style="width:100%"
					class="form-control" name="email" value="${person.email}"
					required="required" />
			</div>
			<div class="used">
				</br> <label>权限类型</label>
				 <select style="width:100%" name="type" class="form-control multiselect multiselect1 " multiple="multiple">
					<c:forEach items="${listType}" var="type">
						<c:choose>
						<%-- <c:when test="${fn:contains(permission, type)}">所有的type循环遍历，如果存在type与permission相同则显示permission --%>
							<c:when test="${fn:contains(permission, type)}">
								<option value="${type}" selected="selected">${type}</option>
							</c:when>
							<c:otherwise>
								<option value="${type}">${type}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<div>
				<br />
				<button type="submit" style="width:100%"
					class="form-control btn btn-primary ">保存</button>
			</div>

		</form>
	</div>

	<script type="text/javascript">
		//下拉框显示
		$(function() {
			$(".multiselect").multiselect({
				maxHeight : 300,
				enableFiltering : true,
				enableCaseInsensitiveFiltering : true,
				filterPlaceholder : '',
				nonSelectedText : '--请选择--',
				nSelectedText : '个已选择',
				numberDisplayed : 4
			});
		});
	</script>


</body>

</html>
