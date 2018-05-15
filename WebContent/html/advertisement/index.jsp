<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <%
        String basePath = request.getContextPath();
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert title here</title>
        <link rel="stylesheet" href="http://www.jq22.com/jquery/bootstrap-3.3.4.css">
        <link href="<%=basePath %>/dist/summernote.css" rel="stylesheet"/>
        <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
        <script src="http://www.jq22.com/jquery/bootstrap-3.3.4.js"></script>
        <script src="<%=basePath %>/dist/summernote.js"></script>
        <script src="<%=basePath %>/dist/lang/summernote-zh-CN.js"></script>
    </head>
    <body>
    <form action="<%=basePath %>/advertisement/create" method="post" id="advertisementForm">
        <div class="summernote"></div>
        <input type="text" name="weight" />
        <input type="hidden" name="detail" id="detail" />
        <input type="button" id="submit" value="提交" />
    </form>
    </body>
    <script type="text/javascript">
        $(function(){
             $('.summernote').summernote({
                    height: 400,
                    tabsize: 2,
                    lang: 'zh-CN'
             });

             $('#submit').on('click', function() {
            	 var detail = $('#summernote').summernote('code');
            	 $('#detail').val(detail);
            	 alert(eval(detail));
             });
        })
    </script>
</html>