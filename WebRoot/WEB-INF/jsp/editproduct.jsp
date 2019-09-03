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
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
    <title>产品详细信息</title>
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
			str = "<td style='width: 62px;'><label for='gongxu"+x+"'>工序"+x+"</label></td><td><input type='text' class='form-control' name='gongxu"+x+"' style='text-align: left;'/> </td>";
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
			str = "<td style='width: 62px;'><label for='chejian"+x+"'>车间"+x+"</label></td><td><input type='text' class='form-control' name='chejian"+x+"' style='text-align: left;' /> </td>";
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
			str = "<tr><td><input type='text' class='form-control' name='zijianName"+x+"' /></td><td><input type='text' class='form-control' name='zijianNo"+x+"'/></td><td><input type='text' class='form-control' name='ratio1"+x+"'/></td><td><input type='text' class='form-control' name='weight"+x+"' /></td><td><input type='text' class='form-control' name='unit"+x+"' /></td></tr>";
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
 	<form id="form" class="form-inline"  action="${pageContext.request.contextPath}/manage/SaveOrUpdate.action">
	<div class="container" style="margin-top: 50px;">
		<div class="row">
			<h4 class="col-md-2">产品详细信息</h4>
   	 		
		</div>
   	 	
      	  	<!-- 添加的num1,2,3,4确定不确定数目的值 -->
      	  	<input  type="hidden" id="num1" name="num1"  value="">
      	  	<input  type="hidden" id="num2" name="num2"  value="">
      	  	<input  type="hidden" id="num3" name="num3"  value="">
      	  	<input  type="hidden" id="num4" name="num4"  value="">
        	   	<table class="table table-hover">
              		<tr>
              		<!-- 因为为集合对象，所以不能直接用controller层返回的listproduct对象回显，公共部分用listproduct[0]，循环部分用foreach遍历循环输出 -->
                		<td><label for="clientNum">图号</label></td>
                		<td><input type="text" class="form-control" readonly id="clientMaterialNo"  name="clientMaterialNo" value="${listproduct[0].clientMaterialNo}"/></td>
             		 	<td><label for="productCode">物料号</label></td>
               		 	<td><input type="text" class="form-control" readonly id="materialNo" name="materialNo" value="${listproduct[0].materialNo}"/></td>                
               		 	<td><label for="productName">产品名称</label></td>
    					<td><input type="text" class="form-control" readonly name="productName" value="${listproduct[0].productName}"/></td>
    				</tr>
              		<tr>
                		<td><label for="productSize">产品规格</label></td>
                		<td><input type="text" class="form-control" readonly name="productSpec" value="${listproduct[0].productSpec}"/></td> 
    				</tr>
           		</table>	
    <!-- 给每个输入框加入禁止书写，添加，删除隐藏 -->
  <div class="container">
	    	<h4 style="float: left">产品检测工序顺序</h4>
	        <!-- 	<input type="button" style="float: right;" class="btn btn-primary" id="delete1" value="删除" />
	    	        <input type="button" style="float: right;margin-right: 10px;" class="btn btn-primary" id="addin1" value="插入" /> -->
		       
		            <table class="table table-hover">
		            
		            <!-- 因为为集合对象，所以不能直接用controller层返回的listproduct对象回显，公共部分用listproduct[0]，循环部分用foreach遍历循环输出 -->
		            <!--varStatus：迭代变量的名称,用来进行数字迭代  -->
		            <c:forEach items="${hs}" var="a" varStatus="stauts">
		            <!-- 取4余0的加一行<tr>,其余的都只加列<td> -->
		           
			            <c:if test="${stauts.index%4==0}">
			            	 <tr>
                             <!--index：当前这次迭代从0开始的迭代索引  -->
		            		<td style="width: 62px;"><label for="gongxu">工序 ${stauts.index+1}</label></td>
			              	<td><input type="text" class="form-control" name="process${stauts.index+1}"  value="${a}" style="text-align: left;" disabled/> </td>
			       		
			       		 </c:if>
			             <c:if test="${stauts.index%4==1}">
			            	
			            <td style="width: 62px;"><label for="gongxu">工序 ${stauts.index+1}</label></td>
		            		
			              	<td><input type="text" class="form-control" name="process${stauts.index+1}"  value="${a}" style="text-align: left;"disabled /> </td>
			            </c:if>
			            <c:if test="${stauts.index%4==2}">
			           	
			            <td style="width: 62px;"><label for="gongxu">工序 ${stauts.index+1}</label></td>
		            		
			              	<td><input type="text" class="form-control" name="process${stauts.index+1}"  value="${a}" style="text-align: left;" disabled/> </td>
			            </c:if>
			      		<c:if test="${stauts.index%4==3}">
			      			
			            <td style="width: 62px;"><label for="gongxu">工序 ${stauts.index+1}</label></td>
		            		
			              	<td><input type="text" class="form-control" name="process${stauts.index+1}"  value="${a}" style="text-align: left;"disabled /> </td>
			                
			            </c:if> 
			          
			            </c:forEach>
					</table>                 
		       
		</div>
		
		<div class="container">
	    	<h4 style="float: left">生产车间顺序</h4>
	    	<input type="button" style="float: right;" class="btn btn-primary" id="delete2" value="删除" />
	    	<input type="button" style="float: right;margin-right: 10px;" class="btn btn-primary" id="addin2" value="插入" />
		        
		            <table class="table table-hover">
		            
		            <!-- forEach中items对应为controller中model.addAttribute("key", value);中的key，var为设置的变量已调用可任意-->
		             <c:forEach items="${listshopsort}" var="b"  varStatus="stauts" >
		             <c:if test="${stauts.index%4==0}">
		            	<tr>
		            		<td style="width: 62px;"><label for="chejian1">车间 ${stauts.index+1}</label></td>
			              	<td><input type="text" class="form-control" name="shop${stauts.index+1}" value="${b.shop}" style="text-align: left;"/> </td>
			            
			          </c:if>
			          <c:if test="${stauts.index%4==1}"> 
			           <td style="width: 62px;"><label for="chejian1">车间 ${stauts.index+1}</label></td>
			              	<td><input type="text" class="form-control" name="shop${stauts.index+1}" value="${b.shop}" style="text-align: left;"/> </td>
			           </c:if>   	
			           <c:if test="${stauts.index%4==2}"> 
			           <td style="width: 62px;"><label for="chejian1">车间 ${stauts.index+1}</label></td>
			              	<td><input type="text" class="form-control" name="shop${stauts.index+1}" value="${b.shop}" style="text-align: left;"/> </td>
			           </c:if>  
			           <c:if test="${stauts.index%4==3}"> 
			           <td style="width: 62px;"><label for="chejian1">车间 ${stauts.index+1}</label></td>
			              	<td><input type="text" class="form-control" name="shop${stauts.index+1}" value="${b.shop}" style="text-align: left;"/> </td>
			           
			           </c:if> 
			            </c:forEach>
					</table>                 
		       
		</div>
		
		<div class="container">
    		<h4 style="float: left">生产车间工序顺序</h4>
    		<input type="button" class="btn btn-primary" id="delete3" style="float: right;" value="删除" />
    		<input class="btn btn-primary" style="float: right;margin-right: 10px;" id="addwork" type="button" value="添加" />
		       
		            <table class="table table-hover" id="workTable">
		            	<tr>
		            		<th>车间号</th>
		            		<th>工序</th>
		            		<th>工价</th>
		            		<th>模具编号</th>
		            		<th>备注</th>			            		
		            	</tr>
		            	 <c:forEach items="${listshopprocess}" var="c"  varStatus="stauts">           	 
		              	<tr>
		                	<td><input type="text" class="form-control" name="shop2${stauts.index+1}" value="${c.shop}" /></td>	
		                	<td><input type="text" class="form-control" name="process2${stauts.index+1}" value="${c.process} "/></td> 
		                	<td><input type="text" class="form-control" name="price${stauts.index+1}" value="${c.price}" /></td> 
		                	<td><input type="text" class="form-control" name="moldNo${stauts.index+1}" value="${c.moldNo} "/></td>              	                	
		    			    <td><input type="text" class="form-control" name="remark${stauts.index+1}" value="${c.remark} "/></td> 
		    			</tr>
		    			 </c:forEach>	    			
					</table>  
		       
    </div>
		<br />
		<br />
		<div class="container">
    		<h4 style="float: left">产品所需材料</h4>
    		<!-- <input type="button" class="btn btn-primary" id="deletlast" style="float: right;" value="删除" />
    		<input class="btn btn-primary" id="addlast" style="float: right;margin-right: 10px;" type="button" value="添加" /> -->
       		
           			 <table class="table table-hover" id="merTable">
              			<tr>
		            		<th>子件名称</th>
		            		<th>子件编号</th>
		            		<th>基本用量</th>
		            		<!-- <th>基础用量</th> -->
		            		<th>净重</th>
		            		<th>单位</th>		            		
		            	</tr>
		            	<c:forEach items="${listproduct}" var="d"  varStatus="stauts">
		              	<tr>		                	
		                	<td><input type="text" class="form-control" readonly name="zijianName${stauts.index+1}"  value="${d.zijianName}" /></td>               
		                	<td><input type="text" class="form-control" readonly name="zijianNo${stauts.index+1}"  value="${d.zijianNo} "/></td>
		                	<td><input type="text" class="form-control" readonly name="ratio1${stauts.index+1}"  value="${d.ratio1} "/></td>
		                	<%-- <td><input type="text" class="form-control" name="ratio2${stauts.index+1}"  value="${d.ratio2}" /></td> --%>
		                	<td><input type="text" class="form-control" readonly name="weight${stauts.index+1}"  value="${d.weight}" /></td>
		                	<td><input type="text" class="form-control" readonly name="unit${stauts.index+1}"  value="${d.unit}" /></td>
		    			</tr>
		    			 </c:forEach>		    			
           			 </table>			
       		      
		</div>
		<br />
		<br />
		<div class="container" style = "text-align:right">
		  
			<input type="button" class="btn btn-primary" id="tj" value="提交" onclick="revise(this)"/>
		</div>
		</div>	
		</form> 	
	</body>
</html>

