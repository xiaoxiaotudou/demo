<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String basePath = request.getContextPath();
	String urlPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/demo";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<link href="<%=basePath %>/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="<%=basePath %>/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/lib/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/lib/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="<%=basePath %>/ueditor/ueditor.all.js"></script>
</head>
<body style="margin: 0;">
	<jsp:include page="/html/header.jsp" flush="true"/>
	<jsp:include page="/html/left.jsp" flush="true"/>
	<div style="width: 70%;display: inline-block;margin-left: 30px;" id="right-content">
	</div>
	<jsp:include page="/html/footer.jsp" flush="true"/>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$('#left-manage a').on('click', function() {
			$('#left-manage a').parent().removeClass("active");
			$(this).parent().addClass("active");

			$.ajax({
                type: "GET",
                url: "<%=urlPath %>"+"/advertisement/index",
                success: function(data){
                     alert(data);
                     alert("<%=urlPath %>");
                     $('#right-content').html(data);
                }
            });
		});
	});
</script>
</html>