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
   
    <title>辅料详细信息</title>
    
    <script src="../js/jquery-3.2.1.min.js"></script>  
        <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>
  
</head>
<body>

<div class="container">
    <h4>辅料详细信息</h4>
        <form id="form" class="form-inline" action="${pageContext.request.contextPath}/manage/saveOrupdateSec.action">     
            <input type="hidden" name="materialsId" value="${sec.materialsId}"/>
            <table class="table table-hover">
              	<tr>
              		<td><label for="sideNo">辅料编号</label></td>
                	<td><input type="text" class="form-control" name="productNo" value="${sec.productNo}"/></td>   
                	<td><label for="client">辅料名称</label></td>
                	<td><input type="text" class="form-control" style="width: 250px" name="secMaterialsName" value="${sec.secMaterialsName}"/></td>               
                	<td><label for="figureNo">规格型号</label></td>
                	<td><input type="text" class="form-control" name="type" value="${sec.type}"/></td>
             	 	<td><label for="libraryNo">主计量</label></td>
                	<td><input type="text" class="form-control" name="unit" value="${sec.unit}"/></td>             
                
    			</tr>
              	 <tr>
              		<%-- <td><label for="moldName">计价方式</label></td>
                	<td><input type="text" class="form-control"style="width: 150px" name="money" value="${sec.money}"/></td> --%>   
                	<td><label for="moldNo">库存数量</label></td>
    				<td><input type="text" class="form-control" name="number" value="${sec.number}"/></td>           
    			</tr>              
			</table>
			 <div class="container" style="margin-top: 50px;">
			    	    <h4>存货描述</h4>
			    	    <hr />
		    
		    	       <textarea class="form-control" style="width: 1110px;" rows="4" name="productDescribe" >${sec.productDescribe}</textarea>
		    
		        </div>  
		        
			<div class="container" style = "text-align:right">
			<input type="submit"  class="btn btn-primary" value="提交" />
			</div>
        </form>
</div>
</body>
</html>