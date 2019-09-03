<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="headandfoot.jsp"></jsp:include>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

<title>修改半成品库存信息</title>
<script src="../js/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.min.js"></script>
<script src="../js/bootstrap-datetimepicker.js"></script>
<script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
</head>
<body>
	<form id="form" class="form-inline" action="${pageContext.request.contextPath}/manage/saveOrupdateMiddle.action">
			<input type="hidden" name="id" value="${middleProduct.id}"/>
		<div class="container" style="margin-top: 50px;">			
		<h2 class="navbar-left">修改半成品库存信息</h2>
				<table class="table table-hover" id="workTable">
					<tr>
						<th>物料号</th>
						<th>产品名称</th>
						<th>产品规格</th>
						<th>库存数量</th>
						<th>单位</th>
						<!-- <th>修改人</th> -->
					</tr>
					<tr>
						<td><input type="text" class="form-control" name="materialNo" value="${middleProduct.materialNo }" readonly="readonly" /></td>
						<td><input type="text" class="form-control" name="productName"  value="${middleProduct.productName }" readonly="readonly"/></td>
						<td><input type="text" class="form-control" name="type"  value="${middleProduct.type }" readonly="readonly"/></td>
						<td><input type="text" class="form-control" name="num"  value="${middleProduct.num }" /></td>
						<td><input type="text" class="form-control" name="unit"  value="${middleProduct.unit }" readonly="readonly" /></td>
					<!-- 	<td><input type="text" class="form-control" name="unit"  /></td> -->
					</tr>

				</table>

			</div>
			
		
			
			<div class="container">
			
	       <h4 class="navbar-left">修改记录单</h4>	
	       <br/><br/><br/>	
		
				<!-- 这个div显示按钮的负责展开具体的内容 -->
			<div style="width:1210px"><input type="button"  style="width:1100px;background-color:#F0F8FF;"
				 value="${middleProduct.productName}(${size}条修改记录 )       (点击查看详情)" onclick="show(this)"></div>	
				<br/>
				
				<!-- 下面一个div负责默认时把具体内容隐藏起来 -->
				<div class="hidden"> 
				<table class="table table-hover used">
				 <c:forEach items="${middleProductDetail}" var="a">		
						<tr>
							<td><label for="oldNum">修改前库存数量</label>
							</td>
							<td><input type="text" class="form-control "
								name="" value="${a.oldNum}" disabled= "true " />
							</td>
							
							<td><label for="newNum">修改后库存数量</label>
							</td>
							<td><input type="text" class="form-control"
								name="" value="${a.newNum}" disabled= "true " />
							</td>
							
							<td><label for="editTime">修改时间</label>
							</td>
							<td><input type="text" class="form-control"
								name="" value="${a.editTime}" disabled= "true "/>
							</td>
							
							<td><label for="editPerson">修改人</label>
							</td>
							<td><input type="text" class="form-control"
								name="" value="${a.editPerson}" disabled= "true "/>
							</td>
						</tr>						
				</c:forEach> 
				</table>					
				</div>
             
			
			
			<div class="container" style="text-align:right">
				<input type="submit" class="btn btn-primary"  value="提交" />
			</div>
		</div>
	</form>
	
	
	<script>
	//负责将上面显示的<div></div>中的class的hidden移除和显示
	function show(obj){
			
				var show = $(obj).parent().next().next();
				
				//切换样式（有则删除，没有则添加）
				show.toggleClass("hidden");
					
	}
	
	</script>
	
</body>
</html>

