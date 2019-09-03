 <%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
    <title>出错了---MES系统</title>
    <link href="../css/bootstrap-multiselect.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="../css/bootstrap.min.new.css"/>
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="../css/main.css"/>	
    <script src="../js/jquery-3.2.1.min.js"></script>      
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/bootstrap-datetimepicker.js"></script>
    <script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
    
    <script>
			//使用匿名函数方法
			function countDown(){
				
				var time = document.getElementById("Time");
				//alert(time.innerHTML);
				//获取到id为time标签中的内容，现进行判断
				if(time.innerHTML == 0){
					//等于0时清除计时
					window.location.href="javascript:history.go(-1);";
				}else{
					time.innerHTML = time.innerHTML-1;
				}
			}
			//1000毫秒调用一次
			window.setInterval("countDown()",1000);
		</script>
		<style>
			#Time,#p{
				font-size: 30px;
				text-align: center;
			}
		</style>
		   
</head>   
</head>
<body>

	<div class="center">	
	<br><br><br><br><br><br><br>
		<div  style="float:left;" >	
		    <img src="../img/error_img.jpg">
		</div>
		<div class="center" >
		<br><br>
			<h2>亲，出错了~~~~(>_<)~~~~</h2>
			<p>
				<span id="Time">10</span>秒后自动跳转到上一页，如不自动跳转，请按
				<a href="javascript:history.go(-1);">返回上一页</a>
			</p>			
		</div>
	
	</div>
	
	
	
	

</body>
</html>