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
    <title>临时工价审批记录</title>
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
        <script type="text/javascript" src="../js/layer.js"></script>
         <!-- 表单提交submit分为已审批和不审批都提交表单 -->
<script type="text/javascript">

function revise(obj) {
      
       var batchNo=$(obj).parents().prev().prev().prev().prev().prev().prev().html();
       var temprice=$(obj).parents().prev().prev().prev().html();
        layer.alert("审批成功!", {
		                skin: 'layui-layer-molv', //样式类名
		                closeBtn: 0
		            }, function(){
                      location.href="${pageContext.request.contextPath}/record/approveTemprice.action?batchNo="+batchNo+"&temPrice="+temprice;
		            });
           
         }
 
function revice(obj) {
      var batchNo=$(obj).parents().prev().prev().prev().prev().prev().prev().html();  
        layer.alert("审批不通过!", {
		                skin: 'layui-layer-molv', //样式类名
		                closeBtn: 0
		            }, function(){
		              location.href="${pageContext.request.contextPath}/record/notapproveTemprice.action?batchNo="+batchNo;
		            });
            
        }
                                                                                                                            

</script>

</head>
<div class="container">
    <h4 class="myClass navbar-left">临时工价审批记录</h4>
   <form class="form-inline " method="post" action="${pageContext.request.contextPath}/record/toTemprice.action" >
         <div class="form-group">
        &nbsp;&nbsp;
		 <label for="exampleInputName2">车间</label>
		    <input type="text" class="form-control" style="width: 120px;" id="exampleInputName2" name="shopName">
		 </div>
		 <div class="form-group">
		  <label for="exampleInputName2">工序</label>
		    <input type="text" class="form-control" style="width: 120px;"id="exampleInputName2" name="processName">
		 </div>
           <label for="exampleInputName2">起始日期</label>
		  <div class="input-group date form_date"  data-date-format="yyyy-mm-dd">
              <input class="form-control" style="width:110px" size="16" type="text" name="start_date"> 
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
          </div> 
          <label for="exampleInputName2">截止日期</label>
          <div class="input-group date form_date"  data-date-format="yyyy-mm-dd">
              <input class="form-control"  size="16" style="width:110px" type="text" name="end_date"> 
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
          </div>  &nbsp;
            <button type="submit" class="btn btn-primary">查询</button>
    </form> 
    <br/>
     <div class="table-responsive ">
	  <table class="table">
			<thead>
				<tr>
				    <th>图号</th>
					<th>物料号</th>
					<th>产品名称</th>
					<th>批次号</th>
					<th>车间名称</th>
					<th>临时工序</th>
					<th>临时工价</th>
					<th>制定日期</th>
					<th>是否审批</th>
					<th>审批/审批不通过</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${listtemprice}" var="temprice" >
			<!-- 循环输出forEach -->
				<tr>
				    <td>${temprice.clientMaterialNo}</td>
				    <td>${temprice.materialNo}</td>
				    <td>${temprice.materialName}</td>
				    <td>${temprice.batchNo}</td>
					<td>${temprice.shopName}</td>
					<th>${temprice.processName}</th>
					<td>${temprice.temPrice}</td>
					<td>${temprice.produceDate}</td>
					<td>
					<c:if test="${temprice.isApproval=='0'}"><p style="color:blue;">未审批</p></c:if>
					<c:if test="${temprice.isApproval=='1'}"><p style="color:green;">已审批</p></c:if>
					<c:if test="${temprice.isApproval=='2'}"><p style="color:red;">审批不通过</p></c:if>
					</td>
					<td>
					<input type="hidden" name="trackId" value="${temprice.trackId}"/><input class="btn  btn-xs btn-success" type="button"  id="aa" value="审批" onclick="revise(this)"/>
					<input class="btn  btn-xs btn-danger" type="button" value="审批不通过" onclick="revice(this)">
					</td>
				</tr>
			  
				</c:forEach>
			</tbody>
	</table>
	<br />
	</div> 
 
     
     <!-- 分页功能 start -->    
    <div align="center" >    
        <font size="2">共 ${page.totalPageCount} 页</font> <font size="2">第    
            ${page.pageNow} 页</font> <a href="${pageContext.request.contextPath}/record/toTemprice.action?pageNow=1&shopName=${shopName}&processName=${processName}&start_date=${start_date}&end_date=${end_date}">首页</a>    
        <c:choose>    
            <c:when test="${page.pageNow - 1 > 0}">    
                <a href="${pageContext.request.contextPath}/record/toTemprice.action?pageNow=${page.pageNow - 1}&shopName=${shopName}&processName=${processName}&start_date=${start_date}&end_date=${end_date}">上一页</a>    
            </c:when>    
            <c:when test="${page.pageNow - 1 <= 0}">    
                <a href="${pageContext.request.contextPath}/record/toTemprice.action?pageNow=1&shopName=${shopName}&processName=${processName}&start_date=${start_date}&end_date=${end_date}">上一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath}/record/toTemprice.action?pageNow=${page.pageNow}&shopName=${shopName}&processName=${processName}&start_date=${start_date}&end_date=${end_date}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 < page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/toTemprice.action?pageNow=${page.pageNow + 1}&shopName=${shopName}&processName=${processName}&start_date=${start_date}&end_date=${end_date}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/toTemprice.action?pageNow=${page.totalPageCount}&shopName=${shopName}&processName=${processName}&start_date=${start_date}&end_date=${end_date}">下一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath }/record/toTemprice.action?pageNow=${page.pageNow}&shopName=${shopName}&processName=${processName}&start_date=${start_date}&end_date=${end_date}">尾页</a>    
            </c:when>    
            <c:otherwise>    
                <a href="${pageContext.request.contextPath }/record/toTemprice.action?pageNow=${page.totalPageCount}&shopName=${shopName}&processName=${processName}&start_date=${start_date}&end_date=${end_date}">尾页</a>    
            </c:otherwise>    
        </c:choose>  
        跳到<select name='topage' size='1' onchange="window.location=' ${pageContext.request.contextPath }/record/toTemprice.action?pageNow='+this.value+'&shopName=${shopName}&processName=${processName}&start_date=${start_date}&end_date=${end_date}'">
       <c:forEach var="i" begin="1" end="${page.totalPageCount}">
       <c:if test="${i==page.pageNow}"><option value='${i}' selected>${i}</option></c:if>
       <c:if test="${i!=page.pageNow}"><option value='${i}'>${i}</option></c:if>
       </c:forEach></select> 页
    </div>    
    <!-- 分页功能 End -->
        
</div>

<script type="text/javascript" >//$(document).ready(function(){
	
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
	
</script>



  
</body>
</html>