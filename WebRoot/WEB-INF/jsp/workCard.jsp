<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="headandfoot.jsp"></jsp:include>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
    <title>次品率记录</title>
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
    <script type="text/javascript">
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
    </script>
</head>
<body>

   <div class="container">
	    <h4 class="myClass navbar-left" style="margin-top: 10px;">次品率记录</h4>
			<form class="form-inline top" action="${pageContext.request.contextPath}/record/selectWorkCard.action">
		  	<div class="form-group">
				<label for="operator">起始时间</label>
				<div class="input-group date form_date"  data-date-format="yyyy-mm-dd">
                            <input class="form-control" size="16" type="text" name="produceDate" /> 
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>	
			</div>&nbsp;&nbsp;&nbsp;
			<div class="form-group">
				<label for="operator">截止时间</label>
				<div class="input-group date form_date"  data-date-format="yyyy-mm-dd">
                            <input class="form-control" size="16" type="text" name="produceDate1" /> 
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>			
			</div>
				&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="submit" class="btn btn-primary">查询</button>
			</form>
		<br />
		<div class="table-responsive">
		  	<table class="table">
				<thead>
					<tr>										
						<th>车间名称</th>
						<th>工序名称</th>
						<th>操作工</th>
						<th>总数</th>
						<th>合格数</th>
						<th>次品数</th>
					    <th>次品率</th> 
						
					</tr>
				</thead>
				<tbody>		
			<c:forEach items="${listWorkCard}" var="b"> 
					<tr>						
						<td>${b.shopName}</td>
						<td>${b.processName}</td>
						<td>${b.operator}</td>
						<td>${b.totalNum}</td>
						<td>${b.hegeNum}</td>
						<td>${b.totalCipinNum}</td>
						<td><fmt:formatNumber value="${b.totalCipinNum/b.totalNum}" pattern="#.###" type="number"/></td>				
					</tr>
			</c:forEach>   
				</tbody>
			</table>
		</div>
		
		<!-- 分页功能 start -->    
    <div align="center" >    
        <font size="2">共 ${page.totalPageCount} 页</font> <font size="2">第    
            ${page.pageNow} 页</font> <a href="${pageContext.request.contextPath}/record/selectWorkCard.action?pageNow=1&produceDate=${produceDate}&produceDate1=${produceDate1}">首页</a>    
        <c:choose>    
            <c:when test="${page.pageNow - 1 > 0}">    
                <a href="${pageContext.request.contextPath}/record/selectWorkCard.action?pageNow=${page.pageNow - 1}&produceDate=${produceDate}&produceDate1=${produceDate1}">上一页</a>    
            </c:when>    
            <c:when test="${page.pageNow - 1 <= 0}">    
                <a href="${pageContext.request.contextPath}/record/selectWorkCard.action?pageNow=1&produceDate=${produceDate}&produceDate1=${produceDate1}">上一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath}/record/selectWorkCard.action?pageNow=${page.pageNow}&produceDate=${produceDate}&produceDate1=${produceDate1}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 < page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/selectWorkCard.action?pageNow=${page.pageNow + 1}&produceDate=${produceDate}&produceDate1=${produceDate1}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/selectWorkCard.action?pageNow=${page.totalPageCount}&produceDate=${produceDate}&produceDate1=${produceDate1}">下一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath }/record/selectWorkCard.action?pageNow=${page.pageNow}&produceDate=${produceDate}&produceDate1=${produceDate1}">尾页</a>    
            </c:when>    
            <c:otherwise>    
                <a href="${pageContext.request.contextPath }/record/selectWorkCard.action?pageNow=${page.totalPageCount}&produceDate=${produceDate}&produceDate1=${produceDate1}">尾页</a>    
            </c:otherwise>    
        </c:choose>  
        跳到<select name='topage' size='1' onchange="window.location=' ${pageContext.request.contextPath }/record/selectWorkCard.action?pageNow='+this.value+'&produceDate=${produceDate}&produceDate1=${produceDate1}'">
       <c:forEach var="i" begin="1" end="${page.totalPageCount}">
       <c:if test="${i==page.pageNow}"><option value='${i}' selected>${i}</option></c:if>
       <c:if test="${i!=page.pageNow}"><option value='${i}'>${i}</option></c:if>
       </c:forEach></select> 页
    </div>    
    <!-- 分页功能 End -->
		
	</div>
	
  </body>
</html>
