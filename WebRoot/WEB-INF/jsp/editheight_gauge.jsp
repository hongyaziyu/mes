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
   
    <title>检具-高度规详细信息</title>
    
    <script src="../js/jquery-3.2.1.min.js"></script>  
        <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/bootstrap-datetimepicker.js"></script>
    <script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
</head>
<body>
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

	});
</script>

<div class="container">
    <h4>检具-高度规详细信息</h4>
        <form id="form" class="form-inline" action="${pageContext.request.contextPath}/manage/saveOrupdateHeightGauge.action">     
            <input type="hidden" name="id" value="${height.id}"/>
            <table class="table table-hover">
              	<tr>
                	<td><label for="client">所检物料号</label></td>
                	<td><input type="text" class="form-control"   name="gaugeNo" value="${height.gaugeNo}"/></td>               
                	<td><label for="figureNo">产品规格</label></td>
                	<td><input type="text" class="form-control" name="productSpecification" value="${height.productSpecification}"/></td>
             	 	<td><label for="moldName">检具公差范围</label></td>
                	<td><input type="text" class="form-control"  name="toleranceRange" value="${height.toleranceRange}"/></td>               
                	<td><label for="moldNo">数量</label></td>
    				<td><input type="text" class="form-control" name="num" value="${height.num}"/></td>
    			</tr>
              	<tr>
                	<td><label for="exampleInputName2">申报日期</label></td>
					<td><div class="input-group date form_date"
							data-date-format="yyyy-mm-dd">
							<input class="form-control" style="width: 105px" type="text" value="${height.declarationDate}" name="declarationDate"> <span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"></span> </span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span> </span>
						</div></td>  
                	<td><label for="sideNo">用于何处</label></td>
                	<td><input type="text" class="form-control" name="purpose" value="${height.purpose}"/></td>  
                	<td><label for="sideNo">完成日期人</label></td>
                	<td><input type="text" class="form-control" name="completionDate" value="${height.completionDate}"/></td> 
                	<td><label for="sideNo">领用人员</label></td>
                	<td><input type="text" class="form-control" name="collarWorkers" value="${height.collarWorkers}"/></td>              
    			</tr>
    			                    
			</table>
			 
			<div class="container" style = "text-align:right">
			<input type="submit"  class="btn btn-primary" value="提交" />
			</div>
        </form>
</div>
</body>
</html>