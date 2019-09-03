<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
  <head>
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">


    <title>系统登录</title>

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" type="text/css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../css/signin.css" type="text/css" rel="stylesheet">


  </head>

  <body>
			
    <div>
      <form class="form-signin" method = "post" action="${pageContext.request.contextPath }/user/relogin.action">      	 	
       	 <h1>红桥mes管理系统</h1><br/>
       	<p style="color:red;font-size:16px;">${error}</p>
        <p style="color:red;font-size:16px;">${relogin_errror}</p>
        <label for="LOGINID" class="sr-only">工号</label>       
        <input type="text" id="Username" class="form-control" name="loginid" placeholder="请在此输入工号" required autofocus />
        <label for="PASSWORD" class="sr-only">密码</label>
        <input type="password" id="Passwd" class="form-control" name="password" placeholder="请在此输入密码" required />
        <div class="checkbox">
          <div style="float:left;">
            <label><input type="checkbox" value="remember-me"> 记录工号 </label>
          </div>
          <div style="float:right;">
            <a href="#"><b>忘记密码？</b></a>
          </div>
        </div>
        <div style="margin-top:25px">
        <button class="btn btn-lg btn-primary btn-block" type="submit">登陆</button>
        <input type="button" value="点此进行注册" class="btn btn-lg btn-primary btn-block" onclick="window.location.href='${pageContext.request.contextPath }/user/toreg.action'">
        </div>
      </form>        
    </div>

  </body>
</html>
