<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<jsp:include page="headandfoot.jsp"></jsp:include>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
    <title>车间排产记录</title>
    <link href="../css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="../css/bootstrap.min.new.css"/>
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="../css/main.css"/>
</head>

  <script src="../js/jquery-3.2.1.min.js"></script>  
  <script src="../js/bootstrap.min.js"></script>
  <script type="text/javascript" src="../js/layer.js"></script>
   <!-- 周计划审批！！ -->
<script>
    $(function(){
        $("#sp").click(function(){
        
        layer.alert("周计划完成审批成功!", {
		                skin: 'layui-layer-molv', //样式类名
		                closeBtn: 0
		            }, function(){
		            var planId=document.getElementById("spid").value;
		             form1.action = "${pageContext.request.contextPath}/record/approveShopPlanList.action?planId="+planId;
			         $("#form1").attr("action",form1.action);
			         form1.submit();
		            });
		            
		     
        });
       
   
    }); 
</script>  


<!-- 表单提交submit分为已完成查询和未完成查询都提交表单 -->
<script type="text/javascript">
function is_submit(value) {
        var form1 = $("#form1");
        if (value == 1) {          
            form1.action = "${pageContext.request.contextPath}/record/toShopPlanList.action";
             $("#form1").attr("action",form1.action);
            form1.submit();
        }
        if (value == 2) {
            form1.action = "${pageContext.request.contextPath}/record/toFinishShopPlanList.action";
            $("#form1").attr("action", form1.action);
            form1.submit();

        }
   }

</script> 
<div class="container">
    <h4 class="myClass navbar-left" >车间排产记录</h4>
     <form class="form-inline top"  method="post" name="form1" id="form1" action="">
		<%-- <form class="form-inline top" method="post" action="${pageContext.request.contextPath}/record/toShopPlanList.action"> --%>
		  <div class="form-group">
		  	<label for="exampleInputName2">计划单号</label>
		    <input type="text" class="form-control" name="planNo" >
		  </div>
		  <div class="form-group">
		  	<label for="exampleInputName2">车间</label>
		    <input type="text" class="form-control" name="shopName" >
		  </div>
          <div class="form-group">
		  	<label for="exampleInputName2">批次号</label>
		    <input type="text" class="form-control"  name="batchNo">
		  </div>&nbsp;&nbsp;&nbsp;&nbsp;
		<!--   <button type="submit" class="btn btn-primary ">查询</button> -->
		  <div class="btn-group dropdown">
			<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
				查询
				<!-- <span class="caret"></span>为下拉三角的图形-->
				<span class="caret"></span>
			</button>
			<!-- ul-li下拉的菜单内容-->
			<ul class="dropdown-menu">
				<li><input type="button" id="querrybyid" class="btn btn-primary" style="width:180px" value="未完成车间排产计划查询" onclick="is_submit(1)"/></li>
				<li><input type="button" id="querrybyname" class="btn btn-primary" style="width:180px" value="已完成车间排产计划查询" onclick="is_submit(2)"/></li>
			</ul>
		</div>
		</form>
		<br />
    <div class="table-responsive">
	  <table class="table">
			<thead>
				<tr>
					<th>计划单号</th>
					<th>图号</th>					
					<th>物料号</th>
					<th>批次号</th>
					<th>产品名称</th>
					<th>产品规格</th>
					<th>车间名称</th>
					<th>制定计划日期</th>
					<th>计划数量</th>
					<th>生产状况</th>
					<th>查看计划</th>
					<th>车间排产计划完成审批</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${listShopPlan}" var="item">
				<tr  >
					<td>${item.planNo }</td>
					<td>${item.clientMaterialNo }</td>					
					<td>${item.materialNo }</td>
					<td>${item.batchNo }</td>
					<td>${item.materialName }</td>
					<td>${item.productSpec }</td>
					<td>${item.shopName }</td>
					<td>${item.planDate }</td>
					<td>${item.planNum }</td>	
					<td>
					<c:if test="${item.isProduct=='0'}">未完成</c:if>
					<c:if test="${item.isProduct=='1'}">已完成</c:if>
					</td>	
					<td><a class="btn  btn-xs btn-primary" href="${pageContext.request.contextPath}/plan/toShopPlan.action?planId=${item.planId }" target="_blank">查看</a></td>
				    <td><input type="hidden" name="planId" id="spid" value="${item.planId}"/><input class="btn btn-xs btn-success" type="button" style="width: 140px" value="审批完成"  id="sp" ></td>
				</tr>
				</c:forEach>
				
				
			</tbody>
	</table>
	</div> 
	<!-- 分页功能 start -->    
    <div align="center" >    
        <font size="2">共 ${page.totalPageCount} 页</font> <font size="2">第    
            ${page.pageNow} 页</font>
            <!-- 分为已完成和未完成的车间排产计划记录分页 -->
           <c:if test="${is_product=='0'}">   
                <a href="${pageContext.request.contextPath}/record/toShopPlanList.action?pageNow=1&planNo=${planNo}&shopName=${shopName}&batchNo=${batchNo}">首页</a>    
           </c:if>
           <c:if test="${is_product=='1'}"> 
                <a href="${pageContext.request.contextPath}/record/toFinishShopPlanList.action?pageNow=1&planNo=${planNo}&shopName=${shopName}&batchNo=${batchNo}">首页</a>    
           </c:if>
        <c:choose>    
            <c:when test="${page.pageNow - 1 > 0}"> 
             <!-- 分为已完成和未完成的车间排产计划记录分页 -->
             <c:if test="${is_product=='0'}">     
                <a href="${pageContext.request.contextPath}/record/toShopPlanList.action?pageNow=${page.pageNow - 1}&planNo=${planNo}&shopName=${shopName}&batchNo=${batchNo}">上一页</a>    
             </c:if>
             <c:if test="${is_product=='1'}"> 
                 <a href="${pageContext.request.contextPath}/record/toFinishShopPlanList.action?pageNow=${page.pageNow - 1}&planNo=${planNo}&shopName=${shopName}&batchNo=${batchNo}">上一页</a>    
             </c:if>
            </c:when>    
            <c:when test="${page.pageNow - 1 <= 0}">    
             <!-- 分为已完成和未完成的车间排产计划记录分页 -->
             <c:if test="${is_product=='0'}"> 
                <a href="${pageContext.request.contextPath}/record/toShopPlanList.action?pageNow=1&planNo=${planNo}&shopName=${shopName}&batchNo=${batchNo}">上一页</a>    
             </c:if>
             <c:if test="${is_product=='1'}"> 
                 <a href="${pageContext.request.contextPath}/record/toFinishShopPlanList.action?pageNow=1&planNo=${planNo}&shopName=${shopName}&batchNo=${batchNo}">上一页</a>    
             </c:if>
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
             <!-- 分为已完成和未完成的车间排产计划记录分页 -->
             <c:if test="${is_product=='0'}"> 
                <a href="${pageContext.request.contextPath}/record/toShopPlanList.action?pageNow=${page.pageNow}&planNo=${planNo}&shopName=${shopName}&batchNo=${batchNo}">下一页</a>    
             </c:if>
             <c:if test="${is_product=='1'}"> 
                <a href="${pageContext.request.contextPath}/record/toFinishShopPlanList.action?pageNow=${page.pageNow}&planNo=${planNo}&shopName=${shopName}&batchNo=${batchNo}">下一页</a>    
             </c:if>
            </c:when> 
               
            <c:when test="${page.pageNow + 1 < page.totalPageCount}">    
             <!-- 分为已完成和未完成的车间排产计划记录分页 -->
             <c:if test="${is_product=='0'}">  
                <a href="${pageContext.request.contextPath}/record/toShopPlanList.action?pageNow=${page.pageNow + 1}&planNo=${planNo}&shopName=${shopName}&batchNo=${batchNo}">下一页</a>    
             </c:if>
             <c:if test="${is_product=='1'}"> 
                <a href="${pageContext.request.contextPath}/record/toFinishShopPlanList.action?pageNow=${page.pageNow + 1}&planNo=${planNo}&shopName=${shopName}&batchNo=${batchNo}">下一页</a>    
             </c:if>
            </c:when>    
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}">    
             <!-- 分为已完成和未完成的车间排产计划记录分页 -->
             <c:if test="${is_product=='0'}"> 
                <a href="${pageContext.request.contextPath}/record/toShopPlanList.action?pageNow=${page.totalPageCount}&planNo=${planNo}&shopName=${shopName}&batchNo=${batchNo}">下一页</a>    
             </c:if>
             <c:if test="${is_product=='1'}"> 
                 <a href="${pageContext.request.contextPath}/record/toFinishShopPlanList.action?pageNow=${page.totalPageCount}&planNo=${planNo}&shopName=${shopName}&batchNo=${batchNo}">下一页</a>    
             </c:if>
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
             <!-- 分为已完成和未完成的车间排产计划记录分页 -->
             <c:if test="${is_product=='0'}">  
                <a href="${pageContext.request.contextPath }/record/toShopPlanList.action?pageNow=${page.pageNow}&planNo=${planNo}&shopName=${shopName}&batchNo=${batchNo}">尾页</a>    
             </c:if>
             <c:if test="${is_product=='1'}"> 
                <a href="${pageContext.request.contextPath }/record/toFinishShopPlanList.action?pageNow=${page.pageNow}&planNo=${planNo}&shopName=${shopName}&batchNo=${batchNo}">尾页</a>    
             </c:if>
            </c:when>    
            <c:otherwise>    
             <!-- 分为已完成和未完成的车间排产计划记录分页 -->
             <c:if test="${is_product=='0'}">   
                <a href="${pageContext.request.contextPath }/record/toShopPlanList.action?pageNow=${page.totalPageCount}&planNo=${planNo}&shopName=${shopName}&batchNo=${batchNo}">尾页</a>    
             </c:if>
             <c:if test="${is_product=='1'}"> 
                <a href="${pageContext.request.contextPath }/record/toFinishShopPlanList.action?pageNow=${page.totalPageCount}&planNo=${planNo}&shopName=${shopName}&batchNo=${batchNo}">尾页</a>       
             </c:if>
            </c:otherwise>    
        </c:choose> 
  <!-- 分为已完成和未完成的车间排产计划记录分页 -->
  <c:if test="${is_product=='0'}"> 
        跳到<select name='topage' size='1' onchange="window.location=' ${pageContext.request.contextPath }/record/toShopPlanList.action?pageNow='+this.value+'&planNo=${planNo}&shopName=${shopName}&batchNo=${batchNo}'">
       <c:forEach var="i" begin="1" end="${page.totalPageCount}">
       <c:if test="${i==page.pageNow}"><option value='${i}' selected>${i}</option></c:if>
       <c:if test="${i!=page.pageNow}"><option value='${i}'>${i}</option></c:if>
       </c:forEach></select> 页
   </c:if>
   <c:if test="${is_product=='1'}"> 
      跳到<select name='topage' size='1' onchange="window.location=' ${pageContext.request.contextPath }/record/toFinishShopPlanList.action?pageNow='+this.value+'&planNo=${planNo}&shopName=${shopName}&batchNo=${batchNo}'">
       <c:forEach var="i" begin="1" end="${page.totalPageCount}">
       <c:if test="${i==page.pageNow}"><option value='${i}' selected>${i}</option></c:if>
       <c:if test="${i!=page.pageNow}"><option value='${i}'>${i}</option></c:if>
       </c:forEach></select> 页
   </c:if>
    </div>    
    <!-- 分页功能 End -->
</div>

</body>
</html>