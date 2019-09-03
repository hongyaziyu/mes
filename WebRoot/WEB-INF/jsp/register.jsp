<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    

    <title>用户注册</title>

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Custom styles for this template -->
    <link href="../css/register.css" rel="stylesheet">

   <script src="../js/jquery-3.2.1.min.js"></script>
    <script src="../js/ie-emulation-modes-warning.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    
       <script type="text/javascript">
    		window.onload = function() {
			var txt = document.getElementById("inputLOGINID");
			var email = document.getElementById("inputEMAIL");
			//给文本框注册一个失去焦点事件
 			txt.onblur = function() {
    		//获取文本框中的信息
			var value=txt.value;
			//第一步:得到XMLHttpRequest对象.
			var xmlhttp=null;
			if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest(); // 针对于现在的浏览器包括IE7以上版本
			} else if (window.ActiveXObject) {
			// 针对于IE5,IE6版本
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			//2.设置回调函数
			xmlhttp.onreadystatechange = function() {

				//5.处理响应数据  当信息全部返回，并且是成功
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					
					var msg=xmlhttp.responseText;
					
					document.getElementById("s").innerHTML=msg;
					
				}
			};			
			//post请求方式，参数设置
			xmlhttp.open("POST", "${pageContext.request.contextPath}/user/ajax.action");
			xmlhttp.setRequestHeader("content-type",
					"application/x-www-form-urlencoded");
			xmlhttp.send("loginid="+value);
		}; 
		email.onblur = function() {
			$.ajax({
					 	 url:"${pageContext.request.contextPath}/user/ajax.action",						 
						 data:{
							email:$("#inputEMAIL").val(),
						 },
						 type:"POST",
						 success:function(data){
					
							document.getElementById("t").innerHTML=data;
						 }
							
		});
		
		};
		
		
	};
    
    </script>

  </head>
  <body >
   <div class="divcss">
	
			
	<div >
	<form class="form-signin"  method = "post" action="${pageContext.request.contextPath }/user/register.action" onsubmit="return checkpasswd()">
        
                <label for="inputLOGINID" class="sr-only">LOGINID</label>
		<input type="text" id="inputLOGINID" name="number" class="form-control" placeholder="工号" required autofocus> <span id="s"></span>
                             
		<label for="inputDISPLAYNAME" class="sr-only">DISPLAYNAME</label>
		<input type="text" id="inputDISPLAYNAME" name="personName" class="form-control" placeholder="姓名" required autofocus> 
						 
		<label for="inputPASSWORD" class="sr-only">PASSWORD</label>
		<input type="password" id="inputPASSWORD" name="password" class="form-control" placeholder="密码" required autofocus>
                                
		<label for="confirmPASSWORD" class="sr-only">Repeat your password</label>
		<input type="password" id="confirmPASSWORD" name="repassword" class="form-control" placeholder="确认密码" required autofocus>
                
                <label for="inputPROFESSION" class="sr-only">PROFESSION</label>
		<input type="text" id="inputPROFESSION" name="position" class="form-control" placeholder="职位" required autofocus>	
                
                <label for="inputDEPARTMENT" class="sr-only">DEPARTMENT</label>
		<input type="text" id="inputDEPARTMENT" name="department" class="form-control" placeholder="车间" required autofocus>
		
                <label for="inputEMAIL" class="sr-only">EMAIL ADDRESS</label>
		<input type="Email" id="inputEMAIL" name="email" class="form-control" placeholder="邮箱地址" required autofocus> <span id="t"></span>
            
				
		<button class="btn btn-lg btn-primary btn-block" type="submit" id="submit">注册</button>
			   
		<h4><a href="${pageContext.request.contextPath }/user/login.action"><b>如果你已注册, 请点此登陆</b></a></h4>			
	</form>
	
	</div>
   </div>
     <script>
     //两次密码是否相同的判断函数以及密码是不是6-20位的判断
        function checkpasswd(){
          var passwd = document.getElementById("inputPASSWORD").value;
          var repasswd = document.getElementById("confirmPASSWORD").value;
          if(passwd.length < 20 && passwd.length > 5){
            if(passwd != repasswd){        
              alert("两次密码不一样，请重试！");
              return false;          
            };  
          }else{
            alert("密码长度必须是6-20位");
            return false;
          } ;       
        };
    </script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>