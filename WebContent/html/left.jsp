<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<%
		String basePath = request.getContextPath();
		String urlPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/demo";
	%>
	<div style="width: 10%;display: inline-block;margin-left: 30px;vertical-align: top;height:610px">
		<ul class="nav nav-list" style="margin-top: 20px;" id="left-manage">
  			<li class="nav-header" style="font-size: 18px;">用户管理</li>
  			<li><a href="javascript:void(0)" style="margin-left: 10px;">用户列表</a></li>
  			<li><a href="javascript:void(0)" style="margin-left: 10px;">添加用户</a></li>
  			<li class="nav-header" style="font-size: 18px;">餐厅管理</li>
  			<li><a href="<%=basePath %>/restaurant/index" style="margin-left: 10px;" id="restaurantList">餐厅列表</a></li>
  			<li class="nav-header" style="font-size: 18px;">菜品管理</li>
  			<li><a href="<%=basePath %>/dish/index" style="margin-left: 10px;" id="dishList">菜品列表</a></li>
  			<li><a href="<%=basePath %>/dishCategory/index" style="margin-left: 10px;" id="dishCategoryList">菜品类别</a></li>
  			<li class="nav-header" style="font-size: 18px;">订单管理</li>
  			<li><a href="javascript:void(0)" style="margin-left: 10px;">订单列表</a></li>
  			<li class="nav-header" style="font-size: 18px;">广告管理</li>
  			<li><a href="<%=basePath %>/advertisement/index" style="margin-left: 10px;" id="adList">广告列表</a></li>
  			<li><a href="<%=basePath %>/advertisementCategory/index" style="margin-left: 10px;" id="adCategoryList">广告类别</a></li>
  			<li><a href="<%=basePath %>/advertisement/create" style="margin-left: 10px;" id="addAd">添加/编辑</a></li>
  			<li><a href="javascript:void(0)" style="margin-left: 10px;">流量统计</a></li>
  			<li class="nav-header" style="font-size: 18px;">帮助</li>
  			<li><a href="javascript:void(0)" style="margin-left: 10px;">联系方式</a></li>
		</ul>
	</div>
</body>
</html>