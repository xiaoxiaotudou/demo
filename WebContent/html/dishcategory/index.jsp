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
<title>菜品类别</title>
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
		<div>
         	<h3 style="display: inline-block;">餐厅名称：</h3>
         	<span>
          		<select id="restaurantId"></select>
         	</span>
        </div>
		<table id="table" style="width: 100%;text-align: center;">
			<tr>
    			<td style="width: 15%">编号</td>
    			<td style="width: 40%">类别名称</td>
    			<td style="width: 20%">创建时间</td>
    			<td style="width: 25%">操作</td>
  			</tr>
		</table>
		<div class="pageCode">
			<ul class="pager">
			    <li id="prev"><a href="javascript:void(0)">前一页</a></li>
			    <li id="next"><a href="javascript:void(0)">后一页</a></li>
			</ul>
		</div>
		<div style="width: 100%; height: 300px;margin-top: 50px;">
			<h3 style="display: inline-block;">类别名称：</h3><input type="text" id="dishCategoryName" />
			<div>
				<input type="hidden" id="dishCategoryId"/>
				<input type="button" value="修改" id="dishCategoryEdit" style="margin-left: 24%;padding: 5px 10px; border-radius: 5px" />
				<input type="button" value="新增" id="dishCategoryAdd" style="padding: 5px 10px; border-radius: 5px" />
			</div>
		</div>
	</div>
	<jsp:include page="/html/footer.jsp" flush="true"/>
</body>
<script>
    $(document).ready(function(){
    	$('#dishCategoryList').parent().addClass("active");
    	var currentPageIndex = 1;
    	
    	$.ajax({
            type: "GET",
            url: "<%=urlPath %>"+"/restaurant/getAll",
            success: function(data){
            	var htmlStr = "";
                var result = eval("(" + data + ")");
                for (var i = 0; i < result.length; i++) {
                    htmlStr+= '<option value ="' + result[i].pkId + '">' + result[i].name + '</option>'
                }
                $('#restaurantId').append(htmlStr);
                
                getDishCategory(1, $('#restaurantId option:selected').val());
            }
        });

    	function getDishCategory(index, restaurantId) {
	    	$(".data").remove();
	    	$.ajax({
	            type: "GET",
	            url: "<%=urlPath %>"+"/dishCategory/getAllByPagination",
	            data: {index : index, pageSize : 5, restaurantId : restaurantId},
	            datatype: "json",
	            success: function(data) {
	            	var htmlStr = "";
	            	var temp = eval("(" + data + ")");
	                var result = temp.dishCategories;
	                currentPageIndex = temp.index;
	                for (var i = 0; i < result.length; i++) {
	                	htmlStr+= '<tr class="data"><th>' + result[i].pkId
	                	+ '</th><th>' + result[i].name
	                	+ '</th><th>' + result[i].createdTime
	                	+ '</th><th><a class="edit" data-id="'+ result[i].pkId +'">编辑</a><a class="delete" data-id="'+ result[i].pkId +'">删除</a></th></tr>'
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

	                bindEditEvent();
	                bindDeleteEvent();
	            }
	        });
    	}

    	function bindEditEvent() {
    		$('.edit').on('click', function() {
    			var id = $(this).data('id');
    			
    			$.ajax({
    	            type: "GET",
    	            url: "<%=urlPath %>"+"/dishCategory/get",
    	            data: {id : id},
    	            datatype: "json",
    	            success: function(data){
    	            	var result = eval("(" + data + ")");
    	            	$('#dishCategoryId').val(result.pkId);
    	            	$('#dishCategoryName').val(result.name);
    	            }
    	        });
    		});
    	}
    	
    	function bindDeleteEvent() {
    		$('.delete').on('click', function() {
    			var id = $(this).data('id');
    			
    			$.ajax({
    	            type: "GET",
    	            url: "<%=urlPath %>"+"/dishCategory/delete",
    	            data: {id : id},
    	            datatype: "json",
    	            success: function(data){
    	            	var result = eval("(" + data + ")");
    	            	
    	            	if (result) {
    	            		getDishCategory(currentPageIndex, $('#restaurantId option:selected').val());
    	            		alert("删除成功！");
    	            	} else {
    	            		alert("删除失败，请稍后再试！");
    	            	}
    	            }
    	        });
    		});
    	}

    	$('#restaurantId').on('change', function() {
    		getDishCategory(1, $('#restaurantId option:selected').val());
    	});

    	$('#dishCategoryEdit').on('click', function() {
    		$.ajax({
	            type: "POST",
	            url: "<%=urlPath %>"+"/dishCategory/edit",
	            data: {dishCategoryId : $('#dishCategoryId').val(), dishCategoryName : $('#dishCategoryName').val()},
	            datatype: "json",
	            success: function(data){
	            	var result = eval("(" + data + ")");
	            	
	            	if (result) {
	            		$('#dishCategoryId').val("");
	            		$('#dishCategoryName').val("");
	            		getDishCategory(currentPageIndex, $('#restaurantId option:selected').val());
	            		alert("编辑成功！");
	            	} else {
	            		alert("编辑失败，请稍后再试！");
	            	}
	            }
	        });
    	});

		$('#dishCategoryAdd').on('click', function() {
			$.ajax({
	            type: "POST",
	            url: "<%=urlPath %>"+"/dishCategory/create",
	            data: {restaurantId : $('#restaurantId option:selected').val(), dishCategoryName : $('#dishCategoryName').val()},
	            datatype: "json",
	            success: function(data){
	            	var result = eval("(" + data + ")");
	            	
	            	if (result) {
	            		$('#dishCategoryId').val("");
	            		$('#dishCategoryName').val("");
	            		getDishCategory(currentPageIndex, $('#restaurantId option:selected').val())
	            		alert("添加成功！");
	            	} else {
	            		alert("添加失败，请稍后再试！");
	            	}
	            }
	        });
    	});

		function bindPrevEvent() {
			$('#prev').on('click', function() {
				getDishCategory(Number(currentPageIndex) - Number(1), $('#restaurantId option:selected').val());
			});
		}

		function bindNextEvent() {
	    	$('#next').on('click', function() {
	    		getDishCategory(Number(currentPageIndex) + Number(1), $('#restaurantId option:selected').val());
			});
		}
    });
</script>
</html>