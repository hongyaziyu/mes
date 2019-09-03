<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
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
 
    <title>产品信息</title>
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
		//产品检测工序对应的删除按钮事件
		$("#delete1").click(function(){
			var z = $("#addin1").next().find("td").length;
			if(z>2){
				$("#addin1").next().find("td").last().remove();
				$("#addin1").next().find("td").last().remove();
			}else {
				alert("已是最后一项！");
			}
			if ($("#addin1").next().find("tr").last().children().length == 0){
				$("#addin1").next().find("tr").last().remove();
			}
		});
		//产品检测工序对应的插入按钮事件
		$("#addin1").click(function(){
			var x = 0;
			x = $("#addin1").next().find("td").length/2+1;
			var str;
			str = "<td><label for='gongxu"+x+"'>工序"+x+"</label></td><td><input type='text' class='form-control' value='' name='process"+x+"'/> </td>";
			if (x>20) {
				alert("超出添加上线！");
			} else if ((x-1)%4==0 && x>4) {
//				console.log(11);
				$(this).next().children().append("<tr></tr>");
				$("#addin1").next().find("tr").last().append(str);
			} else{
				$("#addin1").next().find("tr").last().append(str);
			}
			
			//添加的部分：确定num4值，知道子件输入的个数判断
			$("#num4").val(x);
		});
		
		//生产车间顺序对应插入按钮点击事件
		$("#addin2").click(function(){
			var x = 0;
			x = $("#addin2").next().find("td").length/2+1;
			console.log(x);
			var str;
			str = "<td><label for='chejian"+x+"'>车间"+x+"</label></td><td><input type='text' class='form-control' value='' name='shop"+x+"' /> </td>";
			if (x>20) {
				alert("超出添加上线！");
			} else if ((x-1)%4==0 && x>4) {
//				console.log(11);
				$(this).next().children().append("<tr></tr>");
				$("#addin2").next().find("tr").last().append(str);
			} else{
				$("#addin2").next().find("tr").last().append(str);
			}
			//添加的部分：确定num3值，知道子件输入的个数判断
			$("#num3").val(x);
		});
		//生产车间顺序对应删除按钮点击事件
		$("#delete2").click(function(){
//			console.log(444)
			var y = $("#addin2").next().find("td").length;
			if(y>2){
				$("#addin2").next().find("td").last().remove();
				$("#addin2").next().find("td").last().remove();
			}else {
				alert("已是最后一项！");
			}
			
//			console.log($("#addin2").next().find("tr").last().children().length);
			if ($("#addin2").next().find("tr").last().children().length == 0){
//				console.log(0)
				$("#addin2").next().find("tr").last().remove();
			}
			
		});
		
		//产品所需材料添加按钮点击事件
		$("#addlast").click(function(){
			var x = $("#merTable").find("td").length/5+1;
			var str;
			str = "<tr><td><input type='text' class='form-control' name='zijianName"+x+"' /></td><td><input type='text' value='' class='form-control' name='zijianNo"+x+"'/></td><td><input type='text' class='form-control' name='ratio1"+x+"'/></td><td><input type='text' class='form-control' name='weight"+x+"' /></td><td><input type='text' class='form-control' name='unit"+x+"' /></td></tr>";
			$(this).next().children().append(str);
			//添加的部分：确定num1值，知道子件输入的个数判断
			$("#num1").val(x);
		});
		
		//产品所需材料对应删除按钮点击事件
		$("#deletlast").click(function(){
			var x = $("#merTable").find("td").length;
			if (x<6){
				alert("已是最后一行！");
			}else{
				$(this).next().next().find("tr").last().remove();
			}
		});
		//生产车间工序顺序添加按钮对应点击事件
		$("#addwork").click(function(){		
			var x = $("#workTable").find("td").length/5+1;
//			console.log(x);
			var str;			
			str = "<tr><td><input type='text' class='form-control' name='shop2"+x+"' /></td><td><input type='text' value='' class='form-control' name='process2"+x+"'/></td><td><input type='text' class='form-control' name='price"+x+"' /></td><td><input type='text' class='form-control' name='moldNo"+x+"' /></td><td><input type='text' class='form-control' name='remark"+x+"' /></td></tr>";						
			$(this).next().children().append(str);
		//添加的部分：确定num2值，知道子件输入的个数判断
			$("#num2").val(x);
		});
		
		//生产车间工序顺序删除按钮对应点击事件
		$("#delete3").click(function(){
//			console.log($(this).length);
			var x = $("#workTable").find("td").length;
			if (x<4){
				alert("已是最后一行！");
			}else{
				$(this).next().next().find("tr").last().remove();
			}
			
		});
		$("#tj").click(function(){
//			console.log($(this).length);
			var num1 = $("#merTable").find("td").length/5;
			var num2 = $("#workTable").find("td").length/5;
			var num3 = $("#addin2").next().find("td").length/2;
			var num4 = $("#addin1").next().find("td").length/2;
			$("#num1").val(num1);
			$("#num2").val(num2);
			$("#num3").val(num3);
			$("#num4").val(num4);
			
			$("#form").submit();
		});
	});
		
	</script>
</head>
<body>
	<form id="form" class="form-inline"  action="${pageContext.request.contextPath}/manage/addProduct.action">
	<div class="container" style="margin-top: 50px;">
		<div class="row">
			<h4 class="col-md-2">产品信息</h4>
   	 		
		</div>
   	 	
      	  
      	  	
      	  	<!-- 添加的num1,2,3,4确定不确定数目的值 -->
      	  	<input  type="hidden" id="num1" name="num1"  value="">
      	  	<input  type="hidden" id="num2" name="num2"  value="">
      	  	<input  type="hidden" id="num3" name="num3"  value="">
      	  	<input  type="hidden" id="num4" name="num4"  value="">
        	   	<table class="table table-hover">
              		<tr>              
                		<td><label for="clientNum">图号</label></td>
                		<td><input type="text" class="form-control" name="clientMaterialNo" value=""/></td>
             		 	<td><label for="productCode">物料号</label></td>
               		 	<td><input type="text" class="form-control" name="materialNo" value=""/></td>                
               		 	<td><label for="productName">产品名称</label></td>
    					<td><input type="text" class="form-control" name="productName" value=""/></td>
    				</tr>
              		<tr>
                		<td><label for="productSize">产品规格</label></td>
                		<td><input type="text" class="form-control" name="productSpec" value=""/></td> 
    				</tr>
           		</table>	
           		<hr />
       
  
    <br />
     <div class="container">
	    	<h4 style="float: left">产品检测工序顺序</h4>
	    	<input type="button" style="float: right;" class="btn btn-primary" id="delete1" value="删除" />
	    	<input type="button" style="float: right;margin-right: 10px;" class="btn btn-primary" id="addin1" value="插入" />
		       
		            <table class="table table-hover">
		            	<tr>
		            		<td><label for="gongxu1">工序1</label></td>
			              	<td><input type="text" class="form-control" name="process1" /> </td>
			              	<td><label for="gongxu2">工序2</label></td>
			              	<td><input type="text" class="form-control" name="process2" /> </td>
			              	<td><label for="gongxu3">工序3</label></td>
			              	<td><input type="text" class="form-control" name="process3" /> </td>
			            </tr>
					</table>                 
		       
		</div>
		
		<div class="container">
	    	<h4 style="float: left">生产车间顺序</h4>
	    	<input type="button" style="float: right;" class="btn btn-primary" id="delete2" value="删除" />
	    	<input type="button" style="float: right;margin-right: 10px;" class="btn btn-primary" id="addin2" value="插入" />
		        
		            <table class="table table-hover">
		            	<tr>
		            		<td><label for="chejian1">车间1</label></td>
			              	<td><input type="text" class="form-control" name="shop1" /> </td>
			              	<td><label for="chejain2">车间2</label></td>
			              	<td><input type="text" class="form-control" name="shop2" /> </td>
			              	<td><label for="chejian3">车间3</label></td>
			              	<td><input type="text" class="form-control" name="shop3" /> </td>
			            </tr>
					</table>                 
		       
		</div>
		
		<div class="container">
    		<h4 style="float: left;">生产车间工序顺序</h4>
    		<input type="button" class="btn btn-primary" id="delete3" style="float: right;" value="删除" />
    		<input class="btn btn-primary" style="float: right;margin-right: 10px;" id="addwork" type="button" value="添加" />
		       
		            <table class="table table-hover" id="workTable">
		            	<tr>
		            		<th>车间号</th>
		            		<th>工序</th>
		            		<th>工价</th>	
		            		<th>模具编码</th>
		            		<th>备注</th>		            		
		            	</tr>
		              	<tr>
		                	<td><input type="text" class="form-control" name="shop21"/></td>	
		                	<td><input type="text" class="form-control" name="process21"/></td>
		                	<td><input type="text" class="form-control" name="price1"/></td> 
		                	<td><input type="text" class="form-control" name="moldNo1"/></td>              
		                	<td><input type="text" class="form-control" name="remark1"/></td>		                	
		    			</tr>		    			
		              	
		    					    			
					</table>  
		       
    </div>
		<br />
		<br />
		<div class="container">
    		<h4 style="float: left">产品所需材料</h4>
    		<input type="button" class="btn btn-primary" id="deletlast" style="float: right;" value="删除" />
    		<input class="btn btn-primary" id="addlast" style="float: right;margin-right: 10px;" type="button" value="添加" />
       		
           			 <table class="table table-hover" id="merTable">
              			<tr>
		            		<th>子件名称</th>
		            		<th>子件编号</th>
		            		<th>基本用量</th>
		            		<!-- <th>基础用量</th> -->
		            		<th>净重</th>
		            		<th>单位</th>		            		
		            	</tr>
		              	<tr>		                	
		                	<td><input type="text" class="form-control" name="zijianName1"/></td>               
		                	<td><input type="text" class="form-control" name="zijianNo1"/></td>
		                	<td><input type="text" class="form-control" name="ratio11"/></td>
		                	<!-- <td><input type="text" class="form-control" name="ratio21"/></td> -->
		                	<td><input type="text" class="form-control" name="weight1"/></td>
		                	<td><input type="text" class="form-control" name="unit1"/></td>
		    			</tr>		    			
           			 </table>			
       		      
		</div>
		<br />
		<br />
		<div class="container" style = "text-align:right">
			<input type="button" class="btn btn-primary" id="tj" value="提交" />
		</div>
		</div>
		 </form>		
	</body>
</html>

