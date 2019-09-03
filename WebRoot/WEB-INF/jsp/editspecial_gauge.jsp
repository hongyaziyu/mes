<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<jsp:include page="headandfoot.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
   
    <title>检具-特殊规详细信息</title>
    
    <script src="../js/jquery-3.2.1.min.js"></script>  
        <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>
  
</head>
<body>

<div class="container">
    <h4>检具-特殊规详细信息</h4>
        <form id="form" class="form-inline" action="${pageContext.request.contextPath}/manage/saveOrupdateSpecialGauge.action">     
            <input type="hidden" name="id" value="${special.id}"/>
            <table class="table table-hover">
              	<tr>
                	<td><label for="client">所检产品</label></td>
                	<td><input type="text" class="form-control"  name="inspectionProduction" value="${special.inspectionProduction}"/></td>               
                	<td><label for="figureNo">适用类型</label></td>
                	<td>
                	<c:if test="${(empty special.type)||(special.type==' ')}"><input type="text" class="form-control" name="type" value="暂无标注"/></c:if>
                	<c:if test="${(!empty special.type)&&(special.type!=' ')}"><input type="text" class="form-control" name="type" value="${special.type }"/></c:if>
                	</td>
             	 	<td><label for="moldName">检具名称</label></td>
                	<td><input type="text" class="form-control"   name="gaugeName" value="${special.gaugeName}"/></td>               
                	<td><label for="moldNo">检具编号</label></td>
    				<td><input type="text" class="form-control" name="gaugeNo" value="${special.gaugeNo}"/></td>
    			</tr>
              	
    			                    
			</table>
			 
			<div class="container" style = "text-align:right">
			<input type="submit"  class="btn btn-primary" value="提交" />
			</div>
        </form>
</div>
</body>
</html>