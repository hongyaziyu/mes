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
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
    <title>模具详细信息</title>
    <link href="../css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="../css/bootstrap.min.new.css"/>
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="../css/main.css"/>
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
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
        <form id="form" class="form-inline" action="${pageContext.request.contextPath}/manage/insertMolds.action">
            <table class="table table-hover">
              	<tr>
                	<td><label for="client">客户</label></td>
                	<td><input type="text" class="form-control" name="client" value=""/></td>               
                	<td><label for="figureNo">产品图号</label></td>
                	<td><input type="text" class="form-control" name="figureNo" value=""/></td>
                	
    			</tr>
              	<tr>
              	<td><label for="moldName">模具名称</label></td>
                	<td><input type="text" class="form-control" name="moldName" value=""/></td>
              	<td><label for="moldNo">模具编号</label></td>
    				<td><input type="text" class="form-control" style="width: 300px" name="moldNo" value=""/></td>
              	</tr>
                	<!-- <td><label for="libraryNo">库位号</label></td>
                	<td><input type="text" class="form-control" name="libraryNo" value=""/></td>  
                	<td><label for="sideNo">副数</label></td> 
                	<td><input type="text" class="form-control" name="sideNo" value=""/></td>    -->          
                	                 
			</table>
			<div class="container" style="margin-top: 50px;">
			    	    <h4>备注</h4>
			    	   
		    	       <textarea class="form-control" style="width: 1110px;" rows="4" name="remark" ></textarea>
		    
		    </div> 
			<div class="container" style = "text-align:right">
			<input type="submit"  class="btn btn-primary" value="提交" />
			</div>
        </form>
</div>
</body>
</html>