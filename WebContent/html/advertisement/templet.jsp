<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%
    String basePath = request.getContextPath();
    String urlPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/demo";
    String advertisementId = (String)request.getAttribute("advertisementId");
%>
<script type="text/javascript" src="<%=basePath %>/js/lib/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/lib/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="<%=basePath %>/ueditor/ueditor.all.js"></script>
</head>
<body id="content">
	<script>
	    $(document).ready(function(){
	        $.ajax({
	            type: "GET",
	            url: "<%=urlPath %>"+"/advertisement/getAdvertisementById",
	            data: {advertisementId : <%=advertisementId %>},
	            datatype: "json",
	            success: function(data){
	                var result = eval("(" + data + ")");
	                $('#content').html(result.detail);
	            }
	        });
	    });
	</script>
</body>
</html>