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
   
    <title>修改Spc记录</title>
   
    <script src="../js/jquery-3.2.1.min.js"></script>  
        <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/bootstrap-datetimepicker.js"></script>
    <script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
       
</head>
<body>
<script type="text/javascript" >
//日期显示	
	$(function(){
		$('.date').datetimepicker({
			language: 'zh-CN',
			weekStart: 0,
			todayBtn: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 3,
			minView: 0,
			format: 'yyyy-mm-dd hh:ii',
			minuteStep:1,
			forceParse: 0
		});
				
	});	
</script>

			<div class="container">
			    <h4>Spc基本信息</h4>
			        <form id="form" class="form-inline" action="${pageContext.request.contextPath}/record/saveOrupdateSpc.action">     
			            <input type="hidden" name="spcId" value="${spc.spcId}"/>
			            <table class="table table-hover">
			              	<tr>
			                	<td><label for="equipmentName">图号</label></td>
			                	<td><input type="text" class="form-control" readonly name="clientMaterialNo" value="${spc.clientMaterialNo}"/></td>               
			                	<td><label for="equipmentNum">物料号</label></td>
			                	<td><input type="text" class="form-control" readonly  name="materialNo" value="${spc.materialNo}"/></td>
			             	 	<td><label for="equipmentModel">批次号</label></td>
			                	<td><input type="text" class="form-control" readonly name="batchNo" value="${spc.batchNo}"/></td>                
			                	<td><label for="quipmentLevel">产品名称</label></td>
			    				<td><input type="text" class="form-control" readonly name="productName" value="${spc.productName}"/></td>
			    			</tr>
			              	<tr>
			                	<td><label for="position">工序</label></td>
			                	<td><input type="text" class="form-control" readonly name="processName" value="${spc.processName}"/></td>               
			                	<td><label for="producer">特征值</label></td>
			                	<td><input type="text" class="form-control"  readonly name="characterVal" value="${spc.characterVal}"/></td>
			             	 	<td><label for="produceDate">起始日期</label></td>
			                	<td><div class="input-group date form_date"  data-date-format="yyyy-mm-dd">
			                        <input class="form-control" size="15" type="text" name="makeStartDate" value="${spc.makeStartDate}"/>
			                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
			               		</div>
			                	</td>  
			               		<td><label for="purchaseTime">截止日期</label></td>
			    				<td><div class="input-group date form_date"  data-date-format="yyyy-mm-dd">
			                        <input class="form-control" size="15" type="text" name="makeEndDate" value="${spc.makeEndDate}"/>
			                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
			               		</div>
			    			    </td>  
			    			</tr>
			              	<tr>
			                	<td><label for="startTime">中值</label></td>
			                	<td><input type="text" class="form-control"  name="mid" value="${spc.mid}"/></td>                 
			                	<td><label for="number">上公差限</label></td>
			                	<td><input type="text" class="form-control" name="usl" value="${spc.usl}"/></td>
			             	 	<td><label for="incharge">下公差限</label></td>
			                	<td><input type="text" class="form-control" name="lsl" value="${spc.lsl}"/></td>                
			    			</tr>          
						</table>
			
			</div>
			
			<hr/>
			<div class="container">
			<h4>Spc表格具体信息</h4>
			<br/>
			<table border="1">
			              	<tr>
			              	 <td><input type="text" size="6" class="form-control" readonly="readonly" value="  "/></td> 
			                 <td><input type="text" size="6" class="form-control" readonly="readonly" value="1"/></td> 
			                 <td><input type="text" size="6" class="form-control" readonly="readonly" value="2"/></td> 
			                 <td><input type="text" size="6" class="form-control" readonly="readonly" value="3"/></td> 
			                 <td><input type="text" size="6" class="form-control" readonly="readonly" value="4"/></td> 
			                 <td><input type="text" size="6" class="form-control" readonly="readonly" value="5"/></td> 
			                 <td><input type="text" size="6" class="form-control" readonly="readonly" value="∑"/></td> 
			                 <td><input type="text" size="6" class="form-control" readonly="readonly" value="X平均"/></td> 
			                 <td><input type="text" size="6" class="form-control" readonly="readonly" value="R"/></td> 
			    			</tr>
			    		<c:forEach items="${listSpcTest}" var="listSpcTest" varStatus="stauts">
			    		 <input type="hidden" name="testId${stauts.index+1}" value="${listSpcTest.testId}"/>
			              	<tr>
			              	 <td><input type="text" size="8" class="form-control" readonly="readonly" value="${stauts.index+1}"/></td>
			              	 <td><input type="text" size="6" class="form-control" name="testVal1${stauts.index+1}" value="${listSpcTest.testVal1}"/></td> 
			                 <td><input type="text" size="6" class="form-control" name="testVal2${stauts.index+1}" value="${listSpcTest.testVal2}"/></td> 
			                 <td><input type="text" size="6" class="form-control" name="testVal3${stauts.index+1}" value="${listSpcTest.testVal3}"/></td> 
			                 <td><input type="text" size="6" class="form-control" name="testVal4${stauts.index+1}" value="${listSpcTest.testVal4}"/></td> 
			                 <td><input type="text" size="6" class="form-control" name="testVal5${stauts.index+1}" value="${listSpcTest.testVal5}"/></td> 
			                 <td><input type="text" size="6" class="form-control" readonly="readonly" name="sumX${stauts.index+1}" value="${listSpcTest.sumX}"/></td> 
			                 <td><input type="text" size="6" class="form-control" readonly="readonly" name="aveX${stauts.index+1}" value="${listSpcTest.aveX}"/></td> 
			                 <td><input type="text" size="6" class="form-control" readonly="readonly" name="r${stauts.index+1}"  value="${listSpcTest.r}"/></td> 
			                	
			    			</tr>
			             </c:forEach>	           
						</table>
			</div>
			</div>
			<br/><br/>
         <div class="container" style = "text-align:right">
		<input type="submit"  class="btn btn-primary" value="提交" />
		</div>

 </form>
</body>
</html>

