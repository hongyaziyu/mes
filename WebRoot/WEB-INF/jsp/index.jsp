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
   
	<link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="../css/signin.css" type="text/css" rel="stylesheet">
  </head>

  <body>
			
    <div>
     
      <form class="form-signin" method = "post" action="${pageContext.request.contextPath }/user/signin.action">      	 	
       <h1>红桥mes管理系统</h1><br/>
       	<p style="color:red;font-size:16px;">${error}</p>
        <p style="color:green;font-size:22px;">${e3 }</p>
        <label for="LOGINID" class="sr-only">工号</label>       
        <input type="text" id="Username" class="form-control" name="loginid" placeholder="请在此输入工号" required autofocus />
        <label for="PASSWORD" class="sr-only">密码</label>
        <input type="password" id="Passwd" class="form-control" name="password" placeholder="请在此输入密码" required />
		
        <div class="checkbox">
          <div style="float:left;">
            <label><input type="checkbox" value="remember-me"> 记录工号 </label>
          </div>
          <div style="float:right;">
            <a href="#" id="czmima" data-toggle="modal" data-target="#myModal"><b >忘记密码？</b></a>
          </div>
        </div>
        <div style="margin-top:25px">
        <button class="btn btn-lg btn-primary btn-block" type="submit">登陆</button>
        <input type="button" value="点此进行注册" class="btn btn-lg btn-primary btn-block" onclick="window.location.href='${pageContext.request.contextPath }/user/toreg.action'">
        </div>
      </form>        
    </div>
	<div  class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div id="cont" style="position:relative;top:220px; width:66%;" class="modal-content">
					 <div id="edit" class="">
						 <div  class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
								&times;
							</button>
							<h4 class="modal-title" id="myModalLabel">
								重置密码
							</h4>
						</div>
						
							<div class="modal-body">
								<label for="PASSWORD" >工号</label>&nbsp;&nbsp;
	        					<input type="text" id="number" name="number" class="form-control" placeholder="工号" required autofocus>
	        					</br>	        		
								<label for="PASSWORD" >注册邮箱</label>&nbsp;&nbsp; 
	        					<input type="email" id="inputEMAIL" name="email" class="form-control" placeholder="邮箱地址" required autofocus>
	        					</br>
	        					<label for="PASSWORD" >新密码</label>&nbsp;&nbsp;
	        					<input type="password" id="Passwd2" class="form-control" name="newPassword" placeholder="请在此输入新密码" required />
	        					</br>
	        					<label for="PASSWORD" >确认新密码</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label id="error" class="hidden" style="color:#0024dd;font-size:14px;">两次密码输入不一致！</label>
	        					<input type="password" id="Passwd3" class="form-control" name="newPassword2" placeholder="请在此输入新密码" required />
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭
								</button>
								<button type="submit" id="sub" class="btn btn-primary">
									提交更改
								</button>
							</div>
					</div>
					<div id="result" class="hidden">
						<div  class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
								&times;
							</button>
							<h4 class="modal-title" id="myModalLabel">
								重置密码
							</h4>
						</div>
						
						<div class="modal-body">
							<div  class='modal-body'><h3 id="flag">重置密码成功！</h3></div>
						</div>
						<div class="modal-footer">
								
							<button type="submit" id="sub" class="btn btn-primary" data-dismiss='modal'>
									关闭
							</button>
						</div>
					</div>
				</div>
				
			</div>
		</div><!-- /.modal-content -->
		<script src="../js/jquery-3.2.1.min.js"></script>  
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="../js/bootstrap.min.js"></script>
        <script type="text/javascript" >
		$(function(){
			$("#czmima").click(function(){
				$("#edit").removeClass("hidden");
				$("#result").addClass("hidden");
				$("#error").addClass("hidden");
				$("#number").val(null);
				$("#inputEMAIL").val(null);
				$("#Passwd2").val(null);
				$("#Passwd3").val(null);
			});
			$("#sub").click(function(){
				if($("#Passwd2").val()==$("#Passwd3").val()){
					$("#error").addClass("hidden");
					$.ajax({
							 	 url:"${pageContext.request.contextPath}/user/resetPassword.action",						 
								 data:{
								 	number:$("#number").val(),
									inputEMAIL:$("#inputEMAIL").val(),
									newPassword:$("#Passwd2").val(),							
								 },
								 type:"POST",
								 success:function(responseText){
									
									if(responseText==1){								
										
										$("#edit").addClass("hidden");
										$("#flag").text("重置密码成功!");
										$("#result").removeClass("hidden");
									}else{
										
										
										$("#edit").addClass("hidden");
										$("#flag").text("重置密码失败!");
										$("#result").removeClass("hidden");
									}
						    		
								 }
					});
				}else{
					$("#error").removeClass("hidden");
				} 
			});
			
			
		});
	</script>
  </body>
</html>
