<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%
	String basePath = request.getContextPath();
	String urlPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/demo";
%>
<title>用户列表</title>
<style type="text/css">
	#table th,td {
        border: 1px solid #000;
        height: 30px;
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
	<div style="width: 70%; margin: auto;display: inline-block;margin-left: 5%;margin-bottom: 20px;height:700px;margin-top: 30px;">
		<table id="table" style="width: 100%;text-align: center;">
			<tr>
    			<td style="width: 15%">编号</td>
    			<td style="width: 20%">姓名</td>
    			<td style="width: 10%">性别</td>
    			<td style="width: 15%">联系方式</td>
    			<td style="width: 20%">创建时间</td>
    			<td style="width: 20%">操作</td>
  			</tr>
		</table>
		<div class="pageCode">
			<ul class="pager">
			    <li id="prev"><a href="javascript:void(0)">前一页</a></li>
			    <li id="next"><a href="javascript:void(0)">后一页</a></li>
			</ul>
		</div>
	</div>
	<jsp:include page="/html/footer.jsp" flush="true"/>
</body>
<script>
    $(document).ready(function(){
    	$('#userList').parent().addClass("active");
    	var currentPageIndex = 1;
    	getUsers(1);

    	function getUsers(index) {
    		$(".data").remove();
    		$.ajax({
	            type: "GET",
	            url: "<%=urlPath %>"+"/user/getAllUserByPagination",
	            data: {index : index, pageSize : 10},
	            datatype: "json",
	            success: function(data){
	            	var htmlStr = "";
	            	var temp = eval("(" + data + ")");
	                var result = temp.users;
	                currentPageIndex = temp.index;
	                for (var i = 0; i < result.length; i++) {
	                	htmlStr+= '<tr class="data"><th>' + result[i].pkId 
	                	+ '</th><th>' + (result[i].userName == null || result[i].userName == '' ? 'N/A' : result[i].userName)
	                	+ '</th><th>' + (result[i].gender == 'male' ? '男' : '女')
	                	+ '</th><th>' + (result[i].tel == null || result[i].tel == '' ? 'N/A' : result[i].tel)
	                	+ '</th><th>' + result[i].createdTime
	                	+ '</th><th><a href="<%=urlPath %>/user/edit?userId=' + result[i].pkId + '">编辑</a><a class="delete" data-id="'+ result[i].pkId +'">删除</a></th></tr>'
	                }
	                $('#table').append(htmlStr);
	                
	                if (temp.pageCount <= 1) {
	                	$('.pageCode').css('display', 'none');
	                } else {
	                	if (currentPageIndex == 1) {
	                		$('#prev').addClass("disabled");
	                		$('#next').removeClass("disabled");
	                		$('#prev').unbind('click');
	                		bindNextEvent();
	                	} else if (currentPageIndex == temp.pageCount) {
	                		$('#next').addClass("disabled");
	                		$('#prev').removeClass("disabled");
	                		$('#next').unbind('click');
	                		bindPrevEvent();
	                	}
	                }
	                
	                bindDeleteEvent();
	            }
	        });
    	}
    	
    	function bindDeleteEvent() {
    		$('.delete').on('click', function() {
    			var id = $(this).data('id');
    			
    			$.ajax({
    	            type: "GET",
    	            url: "<%=urlPath %>"+"/user/delete",
    	            data: {id : id},
    	            datatype: "json",
    	            success: function(data){
    	            	var result = eval("(" + data + ")");
    	            	
    	            	if (result) {
    	            		alert("删除成功！");
    	            	} else {
    	            		alert("删除失败，请稍后再试！");
    	            	}
    	            }
    	        });
    		});
    	}
    	
    	function bindPrevEvent() {
	    	$('#prev').on('click', function() {
	    		getUsers(Number(currentPageIndex) - Number(1));
			});
    	}
    	
    	function bindNextEvent() {
	    	$('#next').on('click', function() {
	    		getUsers(Number(currentPageIndex) + Number(1));
			});
    	}
    });
</script>
</html>