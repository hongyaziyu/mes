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
    <title>月计划记录</title>
    <link href="../css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="../css/bootstrap.min.new.css"/>
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="../css/main.css"/>
    <link rel="stylesheet" href="../css/layer.css" />
</head>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
       <script src="../js/jquery-3.2.1.min.js"></script>  
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/bootstrap-datetimepicker.js"></script>
        <script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
        <script type="text/javascript" src="../js/layer.js"></script>
<script type="text/javascript">
$(function(){ 
             $('.date').datetimepicker({
						language: 'zh-CN',
						weekStart: 0,
						todayBtn: 1,
						autoclose: 1,
						todayHighlight: 1,
						startView: 3,
						minView: "year",
						forceParse: 0,
						format:'yyyy-mm'
		  });
		});
</script>
<script type="text/javascript" >

	function revice(obj){
	 	var id=$(obj).prev().val();
		location.href="${pageContext.request.contextPath}/record/DeleteMonthRecord.action?planId="+id;
	}
	//excel导入
	 function excel() {
	 $.ajax({
					url : "${pageContext.request.contextPath}/record/importMonthExcel.action",
					type : "POST",
					data:new FormData(document.getElementById("forms")),
					processData :false,
					contentType :false,
					success : function(data) {
					if(data==1){
					layer.alert("月计划excel导入成功!", {
		                skin: 'layui-layer-molv', //样式类名
		                closeBtn: 0
		            }, function(){
		               layer.closeAll();
		            });
		            }else if(data==0){
		            	layer.alert("月计划excel导入失败!", {
		                skin: 'layui-layer-molv', //样式类名
		                closeBtn: 0
		            }, function(){
		               layer.closeAll();
		            });
		            }else if(data==2){
		            	layer.alert("您的excel表格有内容为空,请填写完整!", {
		                skin: 'layui-layer-molv', //样式类名
		                closeBtn: 0
		            }, function(){
		               layer.closeAll();
		            });
		            } else if(data==3){
		            	layer.alert("选择的excel错误!请注意导入excel名称!", {
		                skin: 'layui-layer-molv', //样式类名
		                closeBtn: 0
		            }, function(){
		               layer.closeAll();
		            });
		            } 
					else{
		            	layer.alert("抱歉!没有访问权限...", {
		                skin: 'layui-layer-molv', //样式类名
		                closeBtn: 0
		            }, function(){
		               layer.closeAll();
		            });
		            }
				    
					},
					error : function() {
								layer.alert("月计划excel导入失败!", {
				                skin: 'layui-layer-molv', //样式类名
				                closeBtn: 0
				            }, function(){
				               layer.closeAll();
				            });
				    }
				});

}
</script>
 <!-- 文件上传选项css优化 -->
    <style type="text/css">
  #excelPath {
    position: relative;
    display: inline-block;
    background: #D0EEFF;
    border: 1px solid #99D3F5;
    border-radius: 4px;
    padding: 4px 12px;
    overflow: hidden;
    color: #1E88C7;
    text-decoration: none;
    text-indent: 0;
    line-height: 20px;
}

    </style> 
<div class="container">
    <h4 class="myClass navbar-left">月计划记录</h4>
        <form class="form-inline" id="forms" method="post" name="form"  action="${pageContext.request.contextPath}/record/toMonthPlan.action">	
		 &nbsp;
		 <div class="form-group">
		  	<label for="exampleInputName2">客户</label>
		    <input type="text" class="form-control" style="width:85px" id="exampleInputName2" name="company">
		  </div>
		  <div class="form-group">
		  	<label for="exampleInputName3">图号</label>
		    <input type="text" class="form-control" id="exampleInputName3" style="width:110px"name="clientMaterialNo">
		  </div>
		   <label for="exampleInputName4">月份</label>
		  <div class="input-group date form_date"  data-date-format="yyyy-mm">
              <input class="form-control"  style="width:110px" size="16" type="text" name="month" id="exampleInputName4"> 
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
          </div> 
         
		  &nbsp;
		
			<button type="submit" class="btn btn-primary " >
				查询			
			</button>
			  <!-- excel导入文件action执行语句导入excel的语句 -->
			 
			 &nbsp;
			       <a  class="btn btn-danger" href="${pageContext.request.contextPath}/record/toMonthSample.action" ><span class="glyphicon glyphicon-save"></span>&nbsp;&nbsp;生成月计划模板</a> 
			       <input type="file" id="excelPath" name="excelPath" class="form-control" style="width:180px;"/>
			      <input type="button" id="newmj" value="导入Excel" class="btn btn-primary" onclick="excel()" />	
	
		</form>
		<br />
    <div class="table-responsive">
	  <table class="table">
			<thead>
				<tr>	
					<th>月份</th>
					<th>物料名称</th>				   
					<th>客户</th>
					<th>图号</th>
					<th>产品规格</th>
					<th>计划数量</th>
					<th>备注</th>
					<th>删除</th>					
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${listMonthPlan}" var="item">
				<tr> 
					<td>${item.month }</td>
					<td>${item.productName }</td>
				    <td>${item.company }</td>
					<td>${item.clientMaterialNo }</td>
					<td>${item.spec }</td>
					<td>${item.monthNum }</td>
					<td>${item.remark }</td>
					<td>
					 <input type="hidden" name="id" value="${item.planId}"/><input class="btn  btn-xs btn-danger" type="button" value="删除" onclick="revice(this)">
					</td>
				</tr> 									
			</c:forEach>				
		   </tbody>
	</table>
	</div> 
	<!-- 分页功能 start -->    
    <div align="center" >    
        <font size="2">共 ${page.totalPageCount} 页</font> <font size="2">第    
            ${page.pageNow} 页</font> <a href="${pageContext.request.contextPath}/record/toMonthPlan.action?pageNow=1&company=${company}&clientMaterialNo=${clientMaterialNo}&month=${month}">首页</a>    
        <c:choose>    
            <c:when test="${page.pageNow - 1 > 0}">    
                <a href="${pageContext.request.contextPath}/record/toMonthPlan.action?pageNow=${page.pageNow - 1}&company=${company}&clientMaterialNo=${clientMaterialNo}&month=${month}">上一页</a>    
            </c:when>    
            <c:when test="${page.pageNow - 1 <= 0}">    
                <a href="${pageContext.request.contextPath}/record/toMonthPlan.action?pageNow=1&company=${company}&clientMaterialNo=${clientMaterialNo}&month=${month}">上一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath}/record/toMonthPlan.action?pageNow=${page.pageNow}&company=${company}&clientMaterialNo=${clientMaterialNo}&month=${month}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 < page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/toMonthPlan.action?pageNow=${page.pageNow + 1}&company=${company}&clientMaterialNo=${clientMaterialNo}&month=${month}">下一页</a>    
            </c:when>    
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}">    
                <a href="${pageContext.request.contextPath}/record/toMonthPlan.action?pageNow=${page.totalPageCount}&company=${company}&clientMaterialNo=${clientMaterialNo}&month=${month}">下一页</a>    
            </c:when>    
        </c:choose>    
        <c:choose>    
            <c:when test="${page.totalPageCount==0}">    
                <a href="${pageContext.request.contextPath }/record/toMonthPlan.action?pageNow=${page.pageNow}&company=${company}&clientMaterialNo=${clientMaterialNo}&month=${month}">尾页</a>    
            </c:when>    
            <c:otherwise>    
                <a href="${pageContext.request.contextPath }/record/toMonthPlan.action?pageNow=${page.totalPageCount}&company=${company}&clientMaterialNo=${clientMaterialNo}&month=${month}">尾页</a>    
            </c:otherwise>    
        </c:choose>  
        跳到<select name='topage' size='1' onchange="window.location=' ${pageContext.request.contextPath }/record/toMonthPlan.action?pageNow='+this.value+'&company=${company}&clientMaterialNo=${clientMaterialNo}&month=${month}'">
       <c:forEach var="i" begin="1" end="${page.totalPageCount}">
       <c:if test="${i==page.pageNow}"><option value='${i}' selected>${i}</option></c:if>
       <c:if test="${i!=page.pageNow}"><option value='${i}'>${i}</option></c:if>
       </c:forEach></select> 页
    </div>    
    <!-- 分页功能 End -->
</div>




    
</body>
</html>