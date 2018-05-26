<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>创建广告</title>
<%
	String basePath = request.getContextPath();
	String urlPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/demo";
	String advertisementId = (String)request.getAttribute("advertisementId");
%>
</head>
<body>
	<jsp:include page="/html/header.jsp" flush="true"/>
	<jsp:include page="/html/left.jsp" flush="true"/>
	<div style="width: 75%; margin: auto;display: inline-block;margin-left: 5%;margin-bottom: 20px;">
        <div>
         <h3 style="display: inline-block;">广告类别：</h3>
         <span>
          <select id="categoryId"></select>
         </span>
        </div>
        <div>
            <h3 style="display: inline-block;">广告权重：</h3>
            <input type="text" id="weight"/>
        </div>
        <h3>概要信息：</h3>
        <script id="container1" name="content" type="text/plain"></script>
        <h3>详细信息：</h3>
        <script id="container" name="content" type="text/plain"></script>
        <input type="button" id="submit" value="提交" style="margin-left: 86%;padding: 5px 10px;margin-top: 20px;font-size: 18px;border-radius: 5px;" />
    	<div id="descriptionTemp" style="display: none"></div>
    	<div id="detailTemp" style="display: none"></div>
    </div>
    <jsp:include page="/html/footer.jsp" flush="true"/>
</body>
<script>
    $(document).ready(function(){
    	$('#addAd').parent().addClass("active");
    	var descriptionUE = UE.getEditor('container1', {
            initialFrameWidth: 980
        });
    	var detailUE = UE.getEditor('container', {
            initialFrameWidth: 980
        });
    	if (<%=advertisementId %> == "" || <%=advertisementId %> == null) {
	     	$.ajax({
	             type: "GET",
	             url: "<%=urlPath %>"+"/advertisementCategory/getAll",
	             success: function(data){
	             	var htmlStr = "";
	                 var result = eval("(" + data + ")");
	                 for (var i = 0; i < result.length; i++) {
	                     htmlStr+= '<option value ="' + result[i].pkId + '">' + result[i].categoryName + '</option>'
	                 }
	                 $('#categoryId').append(htmlStr);
	             }
	         });
	         $('#submit').on('click', function() {
	             var categoryId = $('#categoryId option:selected').val();
	             var weight = $('#weight').val();
	             var description = descriptionUE.getAllHtml();
	             var detail = detailUE.getAllHtml();
	
	             if (weight != null) {
	              $.ajax({
	                  type: "POST",
	                  url: "<%=urlPath %>"+"/advertisement/create",
	                  data: {categoryId : categoryId, weight : weight, description : description, detail : detail},
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
	             }
	         });
    	} else {
    		$.ajax({
             type: "GET",
             url: "<%=urlPath %>"+"/advertisementCategory/getAll",
             success: function(data){
             	var htmlStr = "";
                 var result = eval("(" + data + ")");
                 for (var i = 0; i < result.length; i++) {
                     htmlStr+= '<option value ="' + result[i].pkId + '">' + result[i].categoryName + '</option>'
                 }
                 $('#categoryId').append(htmlStr);

                 $.ajax({
     	            type: "GET",
     	            url: "<%=urlPath %>"+"/advertisement/getAdvertisementById",
     	            data: {advertisementId : <%=advertisementId %>},
     	            datatype: "json",
     	            success: function(data){
     	                var result = eval("(" + data + ")");
     	                $('#categoryId').val(result.categoryId);
     	                $('#weight').val(result.weight);
     	                
     	                descriptionUE.ready(function() {
     	                	$('#descriptionTemp').html(result.description);
     	                	descriptionUE.setContent($('#descriptionTemp').html());
     	                });
     	                
     	                detailUE.ready(function() {
     	                	$('#detailTemp').html(result.detail);
     	                	detailUE.setContent($('#detailTemp').html());
     	                });
    	            }
     	        });
             }
         });
    		$('#submit').on('click', function() {
	             var categoryId = $('#categoryId option:selected').val();
	             var weight = $('#weight').val();
	             var description = descriptionUE.getAllHtml();
	             var detail = detailUE.getAllHtml();
	
	             if (weight != null) {
	              $.ajax({
	                  type: "POST",
	                  url: "<%=urlPath %>"+"/advertisement/edit",
	                  data: {id : <%=advertisementId %>,categoryId : categoryId, weight : weight, description : description, detail : detail},
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
	             }
	         });
    	}
    });
</script>
</html>