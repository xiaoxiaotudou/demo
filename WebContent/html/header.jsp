<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String basePath = request.getContextPath();
	String urlPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/demo";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath %>/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="<%=basePath %>/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/lib/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/lib/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="<%=basePath %>/ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/common.js"></script>
</head>
<body style="margin: 0;">
<div style="width: 100%;height: 150px;background-color: #2E4358;">
    <div style="display: inline-block;margin: 25px 70px;"><img src="<%=basePath %>/images/logo.jpg" style="height: 100px;width: 100px;"/></div>
    <div style="display: inline-block;margin-left: 40%;"><h2 style="text-align: center;color: wheat;">共赢视角下的广告-点餐系统</h2></div>
</div>
</body>
</html>