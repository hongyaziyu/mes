<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
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
    <title>首页</title>
    <script src="../js/jquery-3.2.1.min.js"></script>  
    <script src="../js/bootstrap.min.js"></script>
	</head>
	<body>
	<div class="container">
		<div data-ride="carousel" id="carousel_container" class="carousel slide">
			<!--图片容器-->
			<div class="carousel-inner">
				<div class="item active"><img src="../img/01.png"/></div>
				<div class="item"><img src="../img/02.png"/></div>
				<div class="item"><img src="../img/03.png"/></div>
			</div>
			<!--小圆圈容器-->
			<ol class="carousel-indicators">
				<li data-slide-to="0" data-target="#carousel_container"></li>
				<li data-slide-to="1" data-target="#carousel_container"></li>
				<li data-slide-to="2" data-target="#carousel_container"></li>
			</ol>
			<!--左右按钮容器-->
			<a href="#carousel_container" data-slide="prev" class="left carousel-control">
		       <span class="glyphicon glyphicon-chevron-left"></span>
		    </a>
		    <a href="#carousel_container" data-slide="prev" class="right carousel-control">
		       <span class="glyphicon glyphicon-chevron-right"></span>
		    </a>
		</div>
		<br/><br/><br/>
		<span style="font-size: 50px;text-align:center;display: block;" >
				<strong>
				<span style="font-family: 微软雅黑,'Microsoft YaHei'; color: rgb(34,76,162);">---<img  src="../img/logo.png">&nbsp;欢迎进入红桥mes管理系统---</span>
				</strong>
		</span>
		<br/><br/>
		<span style="font-size: 18px;" >
				<strong>
				<span style="font-family: 微软雅黑,'Microsoft YaHei'; color: rgb(34,76,162);">关于红桥:</span>
				</strong>
		</span>
		<br/>
		<span style="color: rgb(63,63,63);font-family: 微软雅黑,'Microsoft YaHei';font-size: 14px;">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"安徽红桥金属制造有限公司(原宁国市红桥弹簧制造有限公司）创建于1992年，坐落于风景秀丽的江南小镇安徽省宁国市中溪镇，占地面积4万平方米，建筑面积9千余平方米，现有员工200人，资产总额7000万元。公司主要经营冲压件产品、弹簧和金属表面处理，产品使用于汽车零部件及家用电器。国内主要配套比亚迪、海马、奇瑞、美的、恩福斯凯孚、亚太、亚新科、中鼎...... "
		</span>
		
    </div> <!-- /container -->

	</body>
</html>
  