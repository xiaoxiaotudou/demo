<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String basePath = request.getContextPath();
	String urlPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/demo";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆</title>
<script type="text/javascript" src="<%=basePath %>/js/lib/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/lib/jquery.min.js"></script>
</head>
<body>
	<div style="width: 250px;position: relative;margin-left: 65%;margin-top: 10%;">
		<div>
			<span>帐号：</span><input type="text" id="email" />
		</div>
		<div style="margin-top: 20px;">
			<span>密码：</span><input type="password" id="password" />
		</div>
		<div style="text-align: center;color: red;" id="errorMsg"></div>
		<div style="margin-top: 20px;">
			<input type="button" id="signin" value="登陆" style="width: 70px;height: 30px;border-radius: 5px;margin-left: 70px;" />
			<a href="#" style="margin-left: 15px;font-size: 14px;">注册</a>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$('#email').focus(function() {
			$('#errorMsg').html("");
		});
		$('#password').focus(function() {
			$('#errorMsg').html("");
		});
		
		$('#signin').on('click', function() {
			var email = $('#email').val();
			var password = $('#password').val();
			
			if(email.search(/@/gi) < 0) {
				$('#errorMsg').html("帐号不合法！");
				return;
			}

			$.ajax({
                type: "POST",
                url: "<%=urlPath %>"+"/user/signin",
                data: {email : email, password : password},
                datatype: "json",
                success: function(data){
                     var result = eval("(" + data + ")");
                     if (result == false) {
                    	 $('#errorMsg').html("帐号或者密码错误！");
                     } else {
                    	 window.location.href = "<%=urlPath %>" + "/html/index.jsp";
                     }
                }
            });
		});
	});
</script>
</html>