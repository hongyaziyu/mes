<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="headandfoot.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
    <title>产品异常详细信息</title>
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
     <script> 
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
    $(function(){
    		$("#add").click(function(){
    			var x = 0;
    			x = $("#add").parent().find("div").length;
    			console.log(x);
    			var str;
    			str = "<div><br /><select name='department"+(x+1)+"' class='form-control' style='width: 110px;'><option value='品保部' >品保部</option><option value='制造部'>制造部</option><option value='技术部'>技术部</option><option value='其他部门'>其他部门</option></select><textarea name='opinion"+(x+1)+"' class='form-control' style='width: 1110px; margin-top: 10px;' rows='4' ></textarea></div>";
    			if (x<4){
    				$("#add").parent().find("div").last().append(str);
    			}
    			else{
    				alert("超出添加上限！");
    			}

    		});
    		
    		$("#delete").click(function(){
    			var x = 0;
    			x = $("#add").parent().find("div").length;
    			if(x>1){
    				$("#delete").parent().find("div").last().remove();
    			}
    			else{
    				alert("已是最后一项！");
    			}
    		});
    		
    		
    $("#tj").click(function(){
    	   var j=1 , i=1;
           //遍历循环.each函数（对.used的所有指定元素都执行下一操作）
			$(".used").each(function(){
			
			//对所有name为department的数值，j++循环，对其同类的下一个类name为opinion进行i++
		    $(this).attr("name","department" + j++).next().attr("name","opinion" + i++);
			
			});
			
			var num1 = $("#add").parent().find("div").length;
			
			$("#num1").val(num1);
			
			$("#form").submit();
		});
    		
    		
    	});
    	
    
    	
    </script>
    

  </head>
  
  <body>
      <div class="container" style="margin-top: 50px;">
			<div class="row">
				<h4>产品异常详细信息</h4>
			</div>
   	 	
      	  	<form id="form" class="form-inline" action="${pageContext.request.contextPath}/record/updateProductAbnormal.action">
        	   	<input type="hidden" name="abnormalId" value="${productAbnormal.abnormalId}">
        	   	<input type="hidden" name="opinionId" value="${DepOpinion.opinionId}">
        	   	<input  type="hidden" id="num1" name="num1"  value="">
        	   	<table class="table table-hover">
              		<tr>
                		<td><label for="client">客户</label></td>
                		<td><input type="text" class="form-control" name="client" value="${productAbnormal.client}"/></td>                              
               		 	
    					<td><label for="materiaNo">图号</label></td>
    					<td><input type="text" class="form-control" name="clientMaterialNo" value="${productAbnormal.clientMaterialNo}"/></td>
    					
    					<td><label for="client">物料号</label></td>
                		<td><input type="text" class="form-control" name="materialNo" value="${productAbnormal.materialNo}"/></td>                              
               		 	
    					<td><label for="materiaNo">批次号</label></td>
    					<td><input type="text" class="form-control" name="batchNo" value="${productAbnormal.batchNo}"/></td>
    				</tr>
              		<tr>
              		    <td><label for="produceDate">计划单号</label></td>
    					<td><input type="text" class="form-control" name="planNo" value="${productAbnormal.planNo}"/></td>
              		    
              		    <td><label for="inner/out">内部/外部</label></td>
    					<td>
		    					<select class="form-control" style="width: 180px;" name="site">
				    		           <c:if test="${productAbnormal.site=='内部'}">  
				    		             <option value="内部"  selected="selected">内部</option>
				    		             <option value="外部">外部</option>
				    		           </c:if>
				    		            <c:if test="${productAbnormal.site=='外部'}">  
				    		             <option value="内部">内部</option>
				    		             <option value="外部"selected="selected">外部</option>
				    		           </c:if>
			    				</select>
	    				</td>
              		    <td><label for="productName">产品名称</label></td>
    					<td><input type="text" class="form-control" name="productName" value="${productAbnormal.productName}"/></td>
                		
                		<td><label for="productSpecific">产品规格</label></td>
                		<td><input type="text" class="form-control" name="productSpecfic" value="${productAbnormal.productSpecfic}"/></td> 
                		
                		
    					
    					
    				</tr>
    				<tr>
    				<td><label for="exampleInputName2">制定日期</label></td>
						<td> <div class="input-group date form_date"  data-date-format="yyyy-mm-dd">
					              <input class="form-control" style="width: 105px" type="text" name="produceDate"value="${productAbnormal.produceDate}"> 
					              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
					          </div>
					    </td>
    				    <td><label for="abnormalNum">异常数量</label></td>
    					<td><input type="text" class="form-control" name="abnormalNum" value="${productAbnormal.abnormalNum}"/></td>
    					
    					<td><label for="sendPerson">填报人</label></td>
    					<td><input type="text" class="form-control" name="sendPerson" value="${productAbnormal.sendPerson}"/></td>
    				</tr>
           		</table>	
           		<hr />
           		
        <div class="container" style="margin-top: 50px;">
	    	<h4>产品描述</h4>
	    	<hr />
		    
		    	<textarea class="form-control" style="width: 1110px;" rows="4" name="abnormalDesc"  placeholder="注意：异常描述时要描述清楚什么时间、什么地点（或工序）、谁在操作。">${productAbnormal.abnormalDesc}</textarea>
		    
		</div>
		
		<div class="container" style="margin-top: 50px;">
	    	<h4 style="float: left">部门意见</h4>
	    	<input type="button" style="float: right;" class="btn btn-primary" id="delete" value="删除" />
	    	<input type="button" style="float: right;margin-right: 10px;" class="btn btn-primary" id="add" value="插入" />
	    	<br />
	    	<br />
	    	<hr />
	    	<c:forEach items="${listDepOpinion}" var="a">
	    	<div>
				<select class="form-control used" style="width: 110px;" name="department1">
		    		<c:if test="${a.department=='品保部'}">  
		    		<option value="品保部"  selected="selected">品保部</option>
		    		<option value="制造部">制造部</option>
		    		<option value="技术部">技术部</option>
		    		<option value="其他部门">其他部门</option>
		    		</c:if>
		    		<!-- test为条件 ，option中 selected="selected"时该选项被选中，（即if条件为真时，显示selected="selected"中的value）-->
		    		<c:if test="${a.department=='制造部'}">  
		    		<option value="品保部" >品保部</option>
		    		<option value="制造部" selected="selected">制造部</option>
		    		<option value="技术部">技术部</option>
		    		<option value="其他部门">其他部门</option>
		    		</c:if>
		    		<c:if test="${a.department=='技术部'}">  
		    		<option value="品保部" >品保部</option>
		    		<option value="制造部" >制造部</option>
		    		<option value="技术部" selected="selected">技术部</option>
		    		<option value="其他部门">其他部门</option>
		    		</c:if>
		    		<c:if test="${a.department=='其他部门'}">  
		    		<option value="品保部">品保部</option>
		    		<option value="制造部">制造部</option>
		    		<option value="技术部">技术部</option>
		    		<option value="其他部门" selected="selected">其他部门</option>
		    		</c:if>
	    		</select>
	    		<textarea class="form-control" style="width: 1110px; margin-top: 10px;" rows="4" name="opinion1">${a.opinion}</textarea>
		     </div>
		     </c:forEach>
		        <br/>
		    	<br/>

		</div>
		
		<div class="container" style="margin-top: 50px;">
	    	<h4>跟踪验证</h4>
	    	<hr />
		    
		    	<textarea class="form-control" style="width: 1110px;" rows="4" name="trackVerification" >${productAbnormal.trackVerification}</textarea>
		   
		</div>
         
         <div style="margin-top: 50px;">
         <input  id="tj" class="btn btn-primary" style="float: right"  type="button"  value="提交" />
         </div>
	</form>
	</div>
  </body>
</html>