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
    </head>
    <body>
    <form action="<%=basePath %>/advertisement/create" method="post" id="advertisementForm">
        <input type="text" name="weight" />
        <input type="hidden" name="detail" id="detail" />
        <!-- 加载编辑器的容器 -->
	    <script id="container" name="content" type="text/plain">
        这里写你的初始化内容
    	</script>
	    <!-- 配置文件 -->
	    <script type="text/javascript" src="<%=basePath %>/ueditor/ueditor.config.js"></script>
	    <!-- 编辑器源码文件 -->
	    <script type="text/javascript" src="<%=basePath %>/ueditor/ueditor.all.js"></script>
	    <!-- 实例化编辑器 -->
	    <script type="text/javascript">
	        var ue = UE.getEditor('container');
	    </script>
        <input type="button" id="submit" value="提交" />
    </form>
    </body>
</html>