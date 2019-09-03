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
<div id="div_0" class="hidden">

	工序：<input class="mini-input" name="gongxu0" type="text" value="" readonly/> 
	
	设备：<select name="shebei0" class="form-control" readonly>
		<option disabled selected style="display: none;">--请选择--</option>
		<c:forEach items="${assetNames}" var="asset">
			<option value="${asset}">${asset}</option>
		</c:forEach>
	    </select> 
	
	
	<select name="muju0"  class="form-control hidden" readonly>
		<option disabled selected style="display: none;">--请选择--</option>
		<c:forEach items="${moldNames}" var="mold">
			<option value="${mold}">${mold}</option>
		</c:forEach>
	</select> 
	操作工：<select name="caozuogong0" class="form-control"readonly>
		<option>--请选择--</option>
		<c:forEach items="${personNames}" var="person">
			<option value="${person}">${person}</option>
		</c:forEach>
	</select> &nbsp;&nbsp;&nbsp;&nbsp;
	<button type="button" class="btn btn-default" onclick="jia(this)">插入</button>
	&nbsp;
	<button type="button" class="btn btn-default" onclick="jian(this)">删除</button>
	<br />
	<br />

</div>




<c:forEach items="${listTask}" var="item">
	<div id="div_1" class="used">

		工序：<input class="mini-input" name="gongxu1" type="text"
			value="${item.processName }" readonly/>
						设备：<select name="shebei1"
						class="form-control " >
						<option disabled selected style="display: none;">--请选择--</option>
						<c:forEach items="${assetNames}" var="asset">
							<c:choose>
								<c:when test="${item.asset==asset}">
									<option value="${asset}" selected="selected">${asset}</option>
								</c:when>
								<c:otherwise>
									<option value="${asset}">${asset}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
		
		 <select name="muju1"  class="form-control hidden" readonly>
			<option disabled selected style="display: none;">--请选择--</option>
			<c:forEach items="${moldNames}" var="mold">
				<c:choose>
					<c:when test="${item.mold==mold}">
						<option value="${mold}" readonly selected="selected">${mold}</option>
					</c:when>
					<c:otherwise>
						<option value="${mold}">${mold}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select> 操作工：<select name="caozuogong1" readonly
			class="form-control multiselect multiselect1 "  multiple="multiple">
			<c:forEach items="${personNames}" var="person">
				<c:choose>
					<c:when test="${fn:contains(item.operator, person)}">
						<option value="${person}" selected="selected">${person}</option>
					</c:when>
					<c:otherwise>
						<option value="${person}">${person}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select> &nbsp;&nbsp;&nbsp;&nbsp;
		<button type="button" class="btn btn-default" onclick="jia(this)">插入</button>
		&nbsp;
		<button type="button" class="btn btn-default" onclick="jian(this)">删除</button>
		<br />
		<br />

	</div>

</c:forEach>
