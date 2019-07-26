<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>拍卖概况</title>
<link href="css/common.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="wrap">
		<h2>
			<a style="font-style:italic;color: red;"
				href="javascript:history.go(-1)">返回</a>
		</h2>
		<c:if test="${fn:length(requestScope.map['end'])!=0}">
			<h1>拍卖结束的商品</h1>
			<div class="items records">
				<ul class="rows even strong rowh">
					<li>名称</li>
					<li>开始时间</li>
					<li>结束时间</li>
					<li>起拍价</li>
					<li class="borderno record">图片</li>
					<div class="cl"></div>
				</ul>
				   
				<c:forEach items="${requestScope.map['end'] }" var="notend">
					<ul class="rows">
						<li>${notend.auctionName }</li>
						<li>${notend.auctionStartTime }</li>
						<li>${notend.auctionEndTime }</li>
						<li>${notend.auctionStartPrice }</li>
						<li><img src="<%=basePath %>upload/${notend.auctionPICPath }"
							width="270" alt="" />
						</li>
						<li class="borderno blue record"></li>

					</ul>
				</c:forEach>
			</div>
		</c:if>

		<c:if test="${fn:length(requestScope.map['notEnd'])!=0}">
			<h1>拍卖中的商品</h1>
			<div class="items records">
				<ul class="rows even strong rowh">
					<li>名称</li>
					<li>开始时间</li>
					<li>结束时间</li>
					<li>起拍价</li>
					<li class="borderno record">图片</li>
					<div class="cl"></div>
				</ul>
				<c:forEach items="${requestScope.map['notEnd'] }" var="notend">
					<ul class="rows">
						<li>${notend.auctionName }</li>
						<li>${notend.auctionStartTime }</li>
						<li>${notend.auctionEndTime }</li>
						<li>${notend.auctionStartPrice }</li>
						<li><img src="<%=basePath %>upload/${notend.auctionPICPath }"
							width="270" alt="" />
						</li>
						</li>
					</ul>
				</c:forEach>
			</div>
		</c:if>

		<!-- main end-->
	</div>
</body>
</html>
