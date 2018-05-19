<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String basePath = request.getContextPath();
	String urlPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/demo";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建广告</title>
</head>
<body>
	<jsp:include page="/html/header.jsp" flush="true"/>
	<jsp:include page="/html/left.jsp" flush="true"/>
	<div style="width: 70%; margin: auto;display: inline-block;margin-left: 5%;margin-bottom: 20px;">
            <div>
	            <h3 style="display: inline-block;">广告类别：</h3>
	            <span>
		            <select id="categoryId">
		                <option value ="1">Volvo</option>
		                <option value ="2">Saab</option>
		                <option value="3">Opel</option>
		                <option value="4">Audi</option>
		            </select>
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
            <input type="button" id="submit" value="提交" style="float: right;margin-top: 20px;font-size: 18px;border-radius: 5px;margin-right: 1.5%;">
        </div>
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
                        $('#test').html(eval(data));
                    }
                });
            });
        });
    </script>
	<jsp:include page="/html/footer.jsp" flush="true"/>
</body>
</html>