<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <%
        String basePath = request.getContextPath();
        String urlPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/demo";
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>创建广告</title>
        <script type="text/javascript" src="<%=basePath %>/js/lib/jquery.js"></script>
        <script type="text/javascript" src="<%=basePath %>/js/lib/jquery.min.js"></script>
        <script type="text/javascript" src="<%=basePath %>/ueditor/ueditor.config.js"></script>
        <script type="text/javascript" src="<%=basePath %>/ueditor/ueditor.all.js"></script>
    </head>
    <body>
        <div style="width: 980px; margin: auto">
            <div>
	            <span style="font-size: 2em;font-weight: bold;">广告类别：</span>
	            <span>
		            <select id="categoryId">
		                <option value ="volvo">Volvo</option>
		                <option value ="saab">Saab</option>
		                <option value="opel">Opel</option>
		                <option value="audi">Audi</option>
		            </select>
	            </span>
            </div>
            <div>
                <span style="font-size: 2em;font-weight: bold;">广告权重：</span>
                <input type="text" id="weight"/>
            </div>
            <h1>概要信息：</h1>
            <script id="container1" name="content" type="text/plain"></script>
            <h1>详细信息：</h1>
            <script id="container" name="content" type="text/plain"></script>
            <input type="button" id="submit" value="提交" style="float: right;margin-top: 20px;font-size: 2em;border-radius: 10px;">
        </div>
    </body>
    <script>
        $(document).ready(function(){
        	var descriptionUE = UE.getEditor('container1', {
                initialFrameWidth: 980
            });
        	var detailUE = UE.getEditor('container', {
                initialFrameWidth: 980
            });
            $('#submit').on('click', function() {
                var categoryId = $('#categoryId option:selected').val();
                var weight = $('#weight').val();
                var description = descriptionUE.getAllHtml();
                var detail = detailUE.getAllHtml();

                $.ajax({
                    type: "POST",
                    url: "<%=urlPath %>"+"/advertisement/create",
                    data: {categoryId : categoryId, weight : weight, description : description, detail : detail},
                    datatype: "json",
                    success: function(data){
                    	
                    }
                });
            });
        });
    </script>
</html>