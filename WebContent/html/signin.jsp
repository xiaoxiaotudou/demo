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
    <jsp:include page="/html/header.jsp" flush="true"/>
    <div style="height: 620px;width: 100%;background-image: url(<%=basePath %>/images/bg.jpg);background-size:100% 100%;">
		<div style="width: 300px;position: relative;margin-left: 65%;top: 10%;">
			<div>
				<span style="color: white;">帐号：</span><input type="text" id="account" />
			</div>
			<div style="margin-top: 20px;">
				<span style="color: white;">密码：</span><input type="password" id="password" />
			</div>
			<div style="text-align: center;color: red;" id="errorMsg"></div>
			<div style="margin-top: 20px;">
				<input type="button" id="signin" value="登陆" style="width: 70px;height: 30px;border-radius: 5px;margin-left: 105px;" />
				<!-- <a href="#" style="margin-left: 15px;font-size: 14px;">注册</a> -->
			</div>
		</div>
	</div>
	<jsp:include page="/html/footer.jsp" flush="true"/>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$('#account').focus(function() {
			$('#errorMsg').html("");
		});
		$('#password').focus(function() {
			$('#errorMsg').html("");
		});
		
		$('#signin').on('click', function() {
			var account = $('#account').val();
			var password = $('#password').val();
			
			if(account.search(/@/gi) < 0) {
				$('#errorMsg').html("帐号不合法！");
				return;
			}

			$.ajax({
                type: "POST",
                url: "<%=urlPath %>"+"/user/signin",
                data: {account : account, password : password},
                datatype: "json",
                success: function(data){
                     var result = eval("(" + data + ")");
                     if (result == true) {
                    	 window.location.href = "<%=urlPath %>" + "/html/index.jsp";
                     } else {
                    	 $('#errorMsg').html("帐号或者密码错误！");
                     }
                }
            });
		});
	});
</script>
</html>