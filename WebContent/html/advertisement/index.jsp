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
	<div style="width: 70%; margin: auto;display: inline-block;margin-left: 5%;margin-bottom: 20px;margin-top: 30px;">
		<table id="table" style="width: 100%;text-align: center;">
			<tr>
    			<td style="width: 15%">编号</td>
    			<td style="width: 40%">标题</td>
    			<td style="width: 25%">创建时间</td>
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
    	$('#adList').parent().addClass("active");
    	var currentPageIndex = 1;
    	getAdvertisements(1);

    	function getAdvertisements(index) {
    		$(".data").remove();
    		$.ajax({
	            type: "GET",
	            url: "<%=urlPath %>"+"/advertisement/getAll",
	            data: {index : index, pageSize : 10},
	            datatype: "json",
	            success: function(data){
	            	var htmlStr = "";
	            	var temp = eval("(" + data + ")");
	                var result = temp.advertisements;
	                currentPageIndex = temp.index;
	                for (var i = 0; i < result.length; i++) {
	                	htmlStr+= '<tr class="data"><th>' + result[i].pkId 
	                	+ '</th><th>' + result[i].description
	                	+ '</th><th>' + result[i].createdTime
	                	+ '</th><th><a href="<%=urlPath %>/advertisement/edit?advertisementId=' + result[i].pkId + '">编辑</a><a target="blank" href="<%=urlPath %>/advertisement/getAdvertisementDetailPage?advertisementId=' + result[i].pkId + '">预览</a><a class="delete" data-id="'+ result[i].pkId +'">删除</a></th></tr>'
	                }
	                $('#table').append(htmlStr);
	                
	                if (temp.pageCount <= 1) {
	                	$('.pageCode').css('display', 'none');
	                } else {
	                	if (currentPageIndex == 1 && temp.pageCount > 1) {
	                		$('#prev').addClass("disabled");
	                		$('#next').removeClass("disabled");
	                		$('#prev').unbind('click');
	                		bindNextEvent();
	                	} else if (currentPageIndex == temp.pageCount) {
	                		$('#next').addClass("disabled");
	                		$('#prev').removeClass("disabled");
	                		$('#next').unbind('click');
	                		bindPrevEvent();
	                	} else {
                            $('#prev').addClass("disabled");
                            $('#next').addClass("disabled");
                            $('#prev').unbind('click');
                            $('#next').unbind('click');
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
    	            url: "<%=urlPath %>"+"/advertisement/delete",
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
	    		getAdvertisements(Number(currentPageIndex) - Number(1));
			});
    	}
    	
    	function bindNextEvent() {
	    	$('#next').on('click', function() {
	    		getAdvertisements(Number(currentPageIndex) + Number(1));
			});
    	}
    });
</script>
</html>