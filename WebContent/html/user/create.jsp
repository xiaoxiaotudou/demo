<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>创建用户</title>
<%
	String basePath = request.getContextPath();
	String urlPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/demo";
	String userId = (String)request.getAttribute("userId");
%>

</head>
<body>
	<jsp:include page="/html/header.jsp" flush="true"/>
	<jsp:include page="/html/left.jsp" flush="true"/>
	<div style="width: 75%; margin: auto;display: inline-block;margin-left: 5%;margin-bottom: 20px;">
        <div>
			<h3 style="display: inline-block;width: 150px;text-align: right;text-align-last: justify;">用户名：</h3>
			<%
				if (userId != null || userId != "") {
			%>
			<input type="text" id="userName" />
			<% } else { %>
			<input type="text" id="userName" readonly="readonly" />
			<% } %>
        </div>
        <div>
			<h3 style="display: inline-block;width: 150px;text-align: right;text-align-last: justify;">姓氏：</h3>
			<input type="text" id="firstName"/>
        </div>
        <div>
			<h3 style="display: inline-block;width: 150px;text-align: right;text-align-last: justify;">姓名：</h3>
			<input type="text" id="lastName"/>
        </div>
        <div>
			<h3 style="display: inline-block;width: 150px;text-align: right;text-align-last: justify;">性别：</h3>
			<input type="radio" name="gender" value="male" id="male" checked="checked"style="margin-left: 20px;" />男
			<input type="radio" name="gender" value="female" id="female" style="margin-left: 50px;" />女
        </div>
        <div>
			<h3 style="display: inline-block;width: 150px;text-align: right;text-align-last: justify;">联系电话：</h3>
			<input type="text" id="tel"/>
        </div>
        <div>
			<h3 style="display: inline-block;width: 150px;text-align: right;text-align-last: justify;">密码：</h3>
			<input type="text" id="password"/>
        </div>
        <div>
			<h3 style="display: inline-block;width: 150px;text-align: right;text-align-last: justify;">确认密码：</h3>
			<input type="text" id="confirmPassword"/>
        </div>
        <input type="button" id="submit" value="提交" style="margin-left: 29%;padding: 5px 10px;margin-top: 20px;font-size: 18px;border-radius: 5px;" />
    </div>
    <jsp:include page="/html/footer.jsp" flush="true"/>
</body>
<script>
    $(document).ready(function(){
    	$('#addUser').parent().addClass("active");
    	if (<%=userId %> == "" || <%=userId %> == null) {
	         $('#submit').on('click', function() {
	             var userName = $('#userName').val();
	             var firstName = $('#firstName').val();
	             var lastName = $('#lastName').val();
	             var gender = $('input[name="gender"]:checked').val();
	             var tel = $('#tel').val();
	             var password = $('#password').val();
	             var confirmPassword = $('#confirmPassword').val();
	
	             if (password == confirmPassword) {
		              $.ajax({
		                  type: "POST",
		                  url: "<%=urlPath %>"+"/user/create",
		                  data: {userName : userName, tel : tel, firstName : firstName, lastName : lastName, gender : gender, password : password},
		                  datatype: "json",
		                  success: function(data){
		                	  var result = eval("(" + data + ")");
		                	  
		                	  if (result) {
		    	            	  alert("添加成功！");
			            	  } else {
			            		  alert("添加失败，请稍后再试！");
			            	  }
		                  }
		              });
	             } else {
	            	 alert("添加失败，请稍后再试！");
	             }
	         });
    	} else {
            $.ajax({
	            type: "GET",
	            url: "<%=urlPath %>"+"/user/getUserById",
	            data: {id : <%=userId %>},
	            datatype: "json",
	            success: function(data){
	                var result = eval("(" + data + ")");
	                $('#userName').val(result.userName);
		            $('#firstName').val(result.firstName);
		            $('#lastName').val(result.lastName);
		            $('#tel').val(result.tel);
		            
		            if (result.gender == "male") {
		            	$('#male').attr("checked","checked")
		            } else {
		            	$('#female').attr("checked","checked")
		            }
	            }
	        });
            $('#submit').on('click', function() {
	             var userName = $('#userName').val();
	             var firstName = $('#firstName').val();
	             var lastName = $('#lastName').val();
	             var gender = $('input[name="gender"]:checked').val();
	             var tel = $('#tel').val();
	             var password = $('#password').val();
	             var confirmPassword = $('#confirmPassword').val();
	
	             if (password == confirmPassword) {
		              $.ajax({
		                  type: "POST",
		                  url: "<%=urlPath %>"+"/user/edit",
		                  data: {id : <%=userId %>, tel : tel, firstName : firstName, lastName : lastName, gender : gender, password : password},
		                  datatype: "json",
		                  success: function(data){
		                	  var result = eval("(" + data + ")");
		                	  
		                	  if (result) {
		    	            	  alert("编辑成功！");
			            	  } else {
			            		  alert("编辑失败，请稍后再试！");
			            	  }
		                  }
		              });
	             } else {
	            	 alert("编辑失败，请稍后再试！");
	             }
	         });
    	}
    });
</script>
</html>