<%@page import="com.wtu.demo.model.Advertisement"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String basePath = request.getContextPath();
	String urlPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/demo";
	List<Advertisement> advertisements = (List<Advertisement>)request.getAttribute("advertisements");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>广告列表</title>
<style type="text/css">
	#table th,td {   
        border: 1px solid #000;   
    } 
</style>
</head>
<body>
    <jsp:include page="/html/header.jsp" flush="true"/>
	<jsp:include page="/html/left.jsp" flush="true"/>
	<div style="width: 70%; margin: auto;display: inline-block;margin-left: 5%;margin-bottom: 20px;height:500px;border:1px solid;">
		<table id="table" style="width: 100%;text-align: center;">
			<tr>
    			<td style="width: 15%">编号</td>
    			<td style="width: 30%">标题</td>
    			<td style="width: 15%">分类</td>
    			<td style="width: 15%">创建时间</td>
    			<td style="width: 25%">操作</td>
  			</tr>
  			<%-- <c:forEach items="advertisement" var="${request.advertisements}">
  				<!-- <tr>
	    			<th>advertisement.getPkId()</th>
	    			<th>advertisement.getPkId()</th>
	    			<th>advertisement.getPkId()</th>
	    			<th>advertisement.getPkId()</th>
	    			<th>advertisement.getPkId()</th>
  				</tr> -->
  			</c:forEach> --%>
		</table>
		<div class="pagination">
			<ul style="">
			    <li><a href="#">Prev</a></li>
			    <li><a href="#">1</a></li>
			    <li><a href="#">2</a></li>
			    <li><a href="#">3</a></li>
			    <li><a href="#">4</a></li>
			    <li><a href="#">5</a></li>
			    <li><a href="#">Next</a></li>
			</ul>
		</div>
	</div>
	<jsp:include page="/html/footer.jsp" flush="true"/>
</body>
<script>
    $(document).ready(function(){
    	$('#adList').parent().addClass("active");
    });
</script>
</html>