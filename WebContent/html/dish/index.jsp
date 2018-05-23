<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	String basePath = request.getContextPath();
	String urlPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/demo";
%>
<title>菜品列表</title>
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
		<div>
         	<h3 style="display: inline-block;">餐厅名称：</h3>
         	<span>
          		<select id="restaurantId"></select>
         	</span>
        </div>
        <div>
         	<h3 style="display: inline-block;">菜品类别：</h3>
         	<span>
          		<select id="dishCategoryId"></select>
         	</span>
        </div>
		<table id="table" style="width: 100%;text-align: center;">
			<tr>
    			<td style="width: 15%">编号</td>
    			<td style="width: 25%">菜品名称</td>
    			<td style="width: 25%">菜品价格</td>
    			<td style="width: 20%">创建时间</td>
    			<td style="width: 15%">操作</td>
  			</tr>
		</table>
		<div class="pageCode">
			<ul class="pager">
			    <li id="prev"><a href="javascript:void(0)">前一页</a></li>
			    <li id="next"><a href="javascript:void(0)">后一页</a></li>
			</ul>
		</div>
		<div style="width: 100%; height: 300px;margin-top: 30px;">
			<div><h3 style="display: inline-block;">菜品名称：</h3><input type="text" id="dishName" /></div>
			<div><h3 style="display: inline-block;">菜品价格：</h3><input type="text" id="dishPrice" /></div>
			<div><h3 style="display: inline-block;">菜品图片：</h3><input type="text" id="dishImage" /></div>
			<div>
				<input type="hidden" id="dishId"/>
				<input type="button" value="修改" id="dishEdit" style="margin-left: 24%;padding: 5px 10px; border-radius: 5px" />
				<input type="button" value="新增" id="dishAdd" style="padding: 5px 10px; border-radius: 5px" />
			</div>
		</div>
	</div>
	<jsp:include page="/html/footer.jsp" flush="true"/>
</body>
<script>
    $(document).ready(function(){
    	$('#dishList').parent().addClass("active");
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
                
                $.ajax({
                    type: "GET",
                    url: "<%=urlPath %>"+"/dishCategory/getAllByRestaurantId",
                    data: {restaurantId : $('#restaurantId option:selected').val()},
                    datatype: "json",
                    success: function(data){
                    	var htmlStr = "";
                        var result = eval("(" + data + ")");
                        for (var i = 0; i < result.length; i++) {
                            htmlStr+= '<option value ="' + result[i].pkId + '">' + result[i].name + '</option>'
                        }
                        $('#dishCategoryId').append(htmlStr);
                        
                        getDish(1, $('#restaurantId option:selected').val(), $('#dishCategoryId option:selected').val());
                    }
                });
            }
        });

    	function getDish(index, restaurantId, dishCategoryId) {
	    	$(".data").remove();
	    	$.ajax({
	            type: "GET",
	            url: "<%=urlPath %>"+"/dish/getAllDishByPagination",
	            data: {index : index, pageSize : 5, dishCategoryId : dishCategoryId},
	            datatype: "json",
	            success: function(data) {
	            	var htmlStr = "";
	            	var temp = eval("(" + data + ")");
	                var result = temp.dishs;
	                currentPageIndex = temp.index;
	                for (var i = 0; i < result.length; i++) {
	                	htmlStr+= '<tr class="data"><th>' + result[i].pkId
	                	+ '</th><th>' + result[i].dishName
	                	+ '</th><th>' + result[i].dishPrice
	                	+ '</th><th>' + result[i].createdTime
	                	+ '</th><th><a class="edit" data-id="'+ result[i].pkId +'">编辑</a><a class="delete" data-id="'+ result[i].pkId +'">删除</a></th></tr>'
	                }
	                $('#table').append(htmlStr);
	                
	                if (temp.pageCount == 1) {
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
    	            url: "<%=urlPath %>"+"/dish/get",
    	            data: {id : id},
    	            datatype: "json",
    	            success: function(data){
    	            	var result = eval("(" + data + ")");
    	            	$('#dishId').val(result.pkId);
    	            	$('#dishName').val(result.dishName);
    	            	$('#dishPrice').val(result.dishPrice);
    	            	$('#dishImage').val(result.dishImage);
    	            }
    	        });
    		});
    	}
    	
    	function bindDeleteEvent() {
    		$('.delete').on('click', function() {
    			var id = $(this).data('id');
    			
    			$.ajax({
    	            type: "GET",
    	            url: "<%=urlPath %>"+"/dish/delete",
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

    	$('#restaurantId').on('change', function() {
    		$('#dishCategoryId option').remove();
    		$.ajax({
                type: "GET",
                url: "<%=urlPath %>"+"/dishCategory/getAllByRestaurantId",
                data: {restaurantId : $('#restaurantId option:selected').val()},
                datatype: "json",
                success: function(data){
                	var htmlStr = "";
                    var result = eval("(" + data + ")");
                    for (var i = 0; i < result.length; i++) {
                        htmlStr+= '<option value ="' + result[i].pkId + '">' + result[i].name + '</option>'
                    }
                    $('#dishCategoryId').append(htmlStr);
                    
                    getDish(1, $('#restaurantId option:selected').val(), $('#dishCategoryId option:selected').val());
                }
            });
    	});
    	
    	$('#dishCategoryId').on('change', function() {
    		getDish(1, $('#restaurantId option:selected').val(), $('#dishCategoryId option:selected').val());
    	});

    	$('#dishCategoryEdit').on('click', function() {
    		$.ajax({
	            type: "POST",
	            url: "<%=urlPath %>"+"/dish/edit",
	            data: {id : $('#dishId').val(), dishName : $('#dishName').val(), dishPrice : $('#dishPrice').val(), dishImage : $('#dishImage').val()},
	            datatype: "json",
	            success: function(data){
	            	var result = eval("(" + data + ")");
	            	
	            	if (result) {
	            		$('#dishId').val("");
	            		$('#dishName').val("");
	            		$('#dishPrice').val("");
	            		$('#dishImage').val("");
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
	            url: "<%=urlPath %>"+"/dish/create",
	            data: {dishCategoryId : $('#dishCategoryId option:selected').val(), dishName : $('#dishName').val(), dishPrice : $('#dishPrice').val(), dishImage : $('#dishImage').val()},
	            datatype: "json",
	            success: function(data){
	            	var result = eval("(" + data + ")");
	            	
	            	if (result) {
	            		$('#dishId').val("");
	            		$('#dishName').val("");
	            		$('#dishPrice').val("");
	            		$('#dishImage').val("");
	            		alert("添加成功！");
	            	} else {
	            		alert("添加失败，请稍后再试！");
	            	}
	            }
	        });
    	});

		function bindPrevEvent() {
			$('#prev').on('click', function() {
				getDish(Number(currentPageIndex) - Number(1), $('#restaurantId option:selected').val(), $('#dishCategoryId option:selected').val());
			});
		}
		
		function bindNextEvent() {
	    	$('#next').on('click', function() {
	    		getDish(Number(currentPageIndex) + Number(1), $('#restaurantId option:selected').val(), $('#dishCategoryId option:selected').val());
			});
		}
    });
</script>
</html>