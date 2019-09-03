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
   
    <title>模具详细信息</title>
    
    <script src="../js/jquery-3.2.1.min.js"></script>  
        <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/bootstrap-datetimepicker.js"></script>
    <script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
       
	<script type="text/javascript" >	
	$(function(){
		$('.date').datetimepicker({
			language: 'zh-CN',
			weekStart: 0,
			todayBtn: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 3,
			minView: 2,
			forceParse: 0
		});
	});	

	function refer(){
		alert("提交成功");		
	}	
	</script>
</head>
<body>

<div class="container">
    <h4>模具详细信息</h4>
        <form id="form" class="form-inline" action="${pageContext.request.contextPath}/manage/saveOrupdate1.action">     
            <input type="hidden" name="moldId" value="${molds.moldId}"/>
            <table class="table table-hover">
              	<tr>
                	<td><label for="client">客户</label></td>
                	<td><input type="text" class="form-control" name="client" value="${molds.client}"/></td>               
                	<td><label for="figureNo">产品图号</label></td>
                	<td><input type="text" class="form-control" name="figureNo" value="${molds.figureNo}"/></td>
             	 
    			</tr>
    			<tr>
              	<td><label for="moldName">模具名称</label></td>
                	<td><input type="text" class="form-control" name="moldName" value="${molds.moldName}"/></td>               
                	<td><label for="moldNo">模具编号</label></td>
    				<td><input type="text" class="form-control" name="moldNo" style="width: 300px" value="${molds.moldNo}"/></td>
    			</tr>
                	<%-- <td><label for="libraryNo">库位号</label></td>
                	<td><input type="text" class="form-control" name="libraryNo" value="${molds.libraryNo}"/></td>  
                	<td><label for="sideNo">副数</label></td>
                	<td><input type="text" class="form-control" name="sideNo" value="${molds.sideNo}"/></td> --%>               
                	<%-- <td><label for="remark">备注</label></td>
                	<td><input type="text" class="form-control" name="remark" value="${molds.remark}"/></td>
    			                     --%>
			</table>
			<div class="container" style="margin-top: 50px;">
			    	    <h4>备注</h4>
			    	   
		    	       <textarea class="form-control" style="width: 1110px;" rows="4" name="remark" >${molds.remark}</textarea>
		    
		    </div> 
			<div class="container" style = "text-align:right">
			<input type="submit"  class="btn btn-primary" value="提交" />
			</div>
        </form>
</div>
</body>
</html>