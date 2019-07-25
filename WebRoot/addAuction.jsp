<%@ page import="com.qianfeng.enums.AuctionStateEnum" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
			String pageIndex=request.getParameter("pageIndex");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/common.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
function previewFile() {
 var preview = document.querySelector('img');
 var file  = document.querySelector('input[type=file]').files[0];
 var reader = new FileReader();
 reader.onloadend = function () {
  preview.src = reader.result;
 }
 if (file) {
  reader.readAsDataURL(file);
 } else {
  preview.src = "";
 }
}
function updateAuction(){
  document.forms[0].action="auctionUpdate.action?pageIndex=<%=pageIndex%>";
  document.forms[0].submit();
}
</script>
</head>
<body>
	<form id="postForm" action="auctionAdd.action" enctype="multipart/form-data"
		method="post">
		<div class="wrap">
			<!-- main begin-->
			<div class="sale">
				<h1 class="lf">在线拍卖系统</h1>
				<c:if test="${not empty sessionScope.user}">
					<div class="logout right">
						<a href="doLogout" title="注销">注销</a>
					</div>
				</c:if>
				<c:if test="${empty sessionScope.user}">
					<div class="logout right">
						<a href="login.jsp" title="登录">登录</a>
					</div>
				</c:if>
			</div>
			<div class="login logns produce">
				<h1 class="blues">拍卖品信息</h1>
				<dl>
					<input style="display: none;" type="text"
						onblur="checkAuctionName()" name="auctionID" class="inputh lf"
						value="${auction.auctionID}" />
					<dd>
						<label>名称：</label> <input type="text" onblur="checkAuctionName()"
							name="auctionName" class="inputh lf"
							value="${auction.auctionName}" />
						<div id="nameid" class="lf red laba">名称不能为空</div>
					</dd>
					<dd>
						<label>起拍价：</label> <input type="text" onblur="checkStartPrice()"
							name="auctionStartPrice" class="inputh lf"
							value="${auction.auctionStartPrice}" />
						<div id="startPriceid" class="lf red laba">必须为数字</div>
					</dd>
					<dd>
						<label>底价：</label> <input type="text" name="auctionUpset"
							class="inputh lf" onblur="checkUpset()"
							value="${auction.auctionUpset}" />
						<div id="upsetid" class="lf red laba">必须为数字</div>
					</dd>
					<dd>
						<label>开始时间：</label> <input type="text" name="auctionStartTime"
							class="inputh lf" value="${auction.auctionStartTime}" />
						<div id="startTimeid" class="lf red laba">格式：2010-05-05
							12:30:00</div>
					</dd>
					<dd>
						<label>结束时间：</label> <input type="text" name="auctionEndTime"
							class="inputh lf" value="${auction.auctionEndTime}" />
						<div id="endTimeid" class="lf red laba">格式：2010-05-06
							16:30:00</div>
					</dd>
					<dd class="dds">
						<label>拍卖品图片：</label>
						<div class="lf salebd">
							<img id="imgid"
								src="<%=basePath %>upload/${auction.auctionPICPath}" width="100"
								height="100" />
						</div>
						<input id="pic" name="userFile" type="file" class="offset10 lf" onchange="previewFile()" /> <input
							name="beforeFileName" style="display:none;" type="text" class="offset10 lf"
							value="${auction.auctionPICPath}" />
						<div id="picid" class="lf red laba">请上传图片</div>
					</dd>
					<dd class="dds">
						<label>描述：</label>
						<textarea name="auctionDESC" cols="" rows="" class="textarea">${auction.auctionDESC}</textarea>
					</dd>
					<dd class="hegas">
						<input type="submit" value="保 存"
							class="spbg buttombg buttombgs buttomb f14 lf" /> 
							
							<button type="button" onclick="updateAuction()" class="spbg buttombg buttombgs buttomb f14 lf">修改</button>
							<input
							type="reset" value="取 消"
							class="spbg buttombg buttombgs buttomb f14 lf" />
					</dd>
				</dl>
			</div>
			<!-- main end-->
			<!-- footer begin-->
		</div>
		<!--footer end-->
	</form>
</body>
</html>
