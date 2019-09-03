<%@ page language="java" import="java.util.*"
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

	<div id="inv" class="form-group">
	 <h4>成品库存信息</h4>
		<c:forEach items="${listFullProductInventory}" var="item">
			<table class="table table-hover">
				<tr>
                    <td><label for="exampleInputName8">成品名称</label></td>
                	<td><input type="text" class="form-control"   readonly value="${item.productName}"/></td> 
                  	<td><label for="exampleInputName8">成品规格</label></td>
                	<td><input type="text" class="form-control"  readonly value="${item.productSpec}"/></td> 
                  	<td><label for="exampleInputName8">库存数量</label></td>
                	<td><input type="text" class="form-control"  readonly value="${item.productNum}"/></td> 
                  	<td><label for="exampleInputName8">单位</label></td>
                	<td><input type="text" class="form-control"  readonly  value="${item.unit}"/></td> 
				</tr>
			
			</table>
		</c:forEach>


	<h4>半成品库存信息</h4>
		<c:forEach items="${listMiddleProductInventory}" var="item">
			<table class="table table-hover">
				<tr>
                    <td><label for="exampleInputName8">半成品名称</label></td>
                	<td><input type="text" class="form-control"   readonly value="${item.productName}"/></td> 
                  	<td><label for="exampleInputName8">半成品规格</label></td>
                	<td><input type="text" class="form-control"  readonly value="${item.productSpec}"/></td> 
                  	<td><label for="exampleInputName8">库存数量</label></td>
                	<td><input type="text" class="form-control"  readonly value="${item.productNum}"/></td> 
                  	<td><label for="exampleInputName8">单位</label></td>
                	<td><input type="text" class="form-control"  readonly  value="${item.unit}"/></td> 
				</tr>
			
			</table>
		</c:forEach>
	
		
	 <h4>原材料库存信息</h4>	
		<c:forEach items="${listMaterialInventory}" var="item">
			<table class="table table-hover">
				<tr>
                    <td><label for="exampleInputName8">原材料名称</label></td>
                	<td><input type="text" class="form-control"   readonly value="${item.productName}"/></td> 
                  	<td><label for="exampleInputName8">原材料规格</label></td>
                	<td><input type="text" class="form-control"  readonly value="${item.productSpec}"/></td> 
                  	<td><label for="exampleInputName8">库存数量</label></td>
                	<td><input type="text" class="form-control"  readonly value="${item.productNum}"/></td> 
                  	<td><label for="exampleInputName8">单位</label></td>
                	<td><input type="text" class="form-control"  readonly  value="${item.unit}"/></td> 
				</tr>
			
			</table>
		</c:forEach>
		
		
	</div>


