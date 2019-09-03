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
   
    <title>设备详细信息</title>
   
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
    <h4>设备详细信息</h4>
     <form id="form" class="form-inline" action="${pageContext.request.contextPath}/manage/saveOrupdate.action">     
            <input type="hidden" name="assetId" value="${asset.assetId}"/>
            <table class="table table-hover">
              	<tr>
                	<td><label for="equipmentName">设备名称</label></td>
                	<td><input type="text" class="form-control" name="assetName" value="${asset.assetName}"/></td>               
                	<td><label for="equipmentNum">设备编号</label></td>
                	<td><input type="text" class="form-control" name="assetNo"  value="${asset.assetNo}"/></td>
             	 	<td><label for="equipmentModel">型号</label></td>
                	<td><input type="text" class="form-control" name="assetType" value="${asset.assetType}"/></td>                
    			    
    			</tr>
              	<tr>
              	    <td><label for="position">位置</label></td>
                	<td><input type="text" class="form-control" name="position" value="${asset.position}"/></td>   
                	<td><label for="startTime">启用时间</label></td>
                	<td><div class="input-group date form_date"  data-date-format="yyyy-mm-dd">
                        <input class="form-control" size="16" type="text" name="activeTime" value="${asset.activeTime}"/>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
               		</div></td>               
                      
                    <td><label for="isCommon">是否通用</label></td>
                	<td><select class="form-control" style="width: 180px;"
						name="isCommon" >
						<c:if test="${asset.isCommon=='0'}">
						    <option value="1">是</option>
							<option value="0" selected="selected">否</option>
						</c:if>
						<c:if test="${asset.isCommon=='1'}">
						    <option value="1" selected="selected">是</option>
							<option value="0">否</option>
						</c:if>
			            </select>
			            	
			           
			       </td>   
    			</tr>        
			</table>
			    <div class="container" style="margin-top: 50px;">
			    	    <h4>备注</h4>
			    	   
		    	       <textarea class="form-control" style="width: 1110px;" rows="4" name="remark" >${asset.remark}</textarea>
		    
		        </div> 
			<div class="container" style = "text-align:right">
			<input type="submit"  class="btn btn-primary" value="提交" />
			</div>
        </form>
</div>
</body>
</html>

