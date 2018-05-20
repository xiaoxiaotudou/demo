<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.wtu.demo.model.Advertisement"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<%
	String basePath = request.getContextPath();
	String urlPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/demo";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>广告列表</title>
<style type="text/css">
	#table th,td {
        border: 1px solid #000;
    }
    #table a {
        cursor: pointer;
        margin-right: 20px;
    }
</style>
</head>
<body>
    <jsp:include page="/html/header.jsp" flush="true"/>
	<jsp:include page="/html/left.jsp" flush="true"/>
	<div style="width: 70%; margin: auto;display: inline-block;margin-left: 5%;margin-bottom: 20px;height:500px;margin-top: 30px;">
		<table id="table" style="width: 100%;text-align: center;">
			<tr>
    			<td style="width: 15%">编号</td>
    			<td style="width: 30%">标题</td>
    			<td style="width: 15%">分类</td>
    			<td style="width: 15%">创建时间</td>
    			<td style="width: 25%">操作</td>
  			</tr>
		</table>
		<div>
			<ul class="pager">
			    <li><a href="javascript:void(0)">前一页</a></li>
			    <li><a href="javascript:void(0)">后一页</a></li>
			</ul>
		</div>
	</div>
	<jsp:include page="/html/footer.jsp" flush="true"/>
</body>
<script>
    $(document).ready(function(){
    	$('#adList').parent().addClass("active");

    	$.ajax({
            type: "GET",
            url: "<%=urlPath %>"+"/advertisement/getAll",
            data: {index : 1, pageSize : 10},
            datatype: "json",
            success: function(data){
            	var htmlStr = "";
                var result = eval("(" + data + ")");
                for (var i = 0; i < result.length; i++) {
                	htmlStr+= '<tr><th>' + result[i].pkId 
                	+ '</th><th>' + result[i].pkId + '</th><th>' 
                	+ result[i].pkId + '</th><th>' + result[i].pkId
                	+ '</th><th><a>编辑</a><a>预览</a><a>删除</a></th></tr>'
                }
                $('#table').append(htmlStr);
                /* $('.pagination').css('display', 'none'); */
            }
        });
    });
</script>
</html>