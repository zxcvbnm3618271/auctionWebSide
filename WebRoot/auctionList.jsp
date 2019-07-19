<%@page import="java.math.BigDecimal"%>
<%@page import="com.qianfeng.enums.AuctionStateEnum"%>
<%@page import="com.qianfeng.servlet.AuctionListServlet"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
	function goToPage(pageIndex) {
		document.forms[1].action = document.forms[1].action + "?pageIndex="
				+ pageIndex + "&pageNum=${auctionPageInfo.pageNum}";
		document.forms[1].submit();
	}
	//在使用ajax的时候 要导入jquery的包
	function delAuction(arg) {
		var $usarg = $(arg);
		//获取自定义属性
		var auctionid = $usarg.attr("auctionid");
		auctionid = {
			"auctionid" : auctionid
		};
		//删除一定要做确认的操作
		if (confirm("您确定要删除吗?")) {
			$.ajax({
				url : "AuctionDelByIdServlet",
				data : auctionid,
				dataType : "json",
				success : function(data) {
					if (data) {
						$usarg.parent().parent().remove();
					}
				},
				error : function() {
					alert("删除失败");
				}
			});
		}
	}
//通过参数重传的方式刷新页面,缺点是响应速度没有局部刷新快,优点是易于实现
	function ChangepageNum(arg) {
	
		location.href = "AuctionListServlet?pageIndex=1&pageNum="
				+ arg + "";
	}
	//find这个函数在jquery中用的非常多可以解决一些被封装的元素的样式修改
	$(function() {
		$("#pagenum").find("option[value=${auctionPageInfo.pageNum}]").attr(
				"selected", true);
	})
</script>

<script type="text/javascript">
	
<%String msg = request.getParameter("msg");
			if (AuctionStateEnum.AUCTION_ADD_SUCCESS.getValue().equals(msg)) {
				out.print("alert('添加成功');");
			} else if (AuctionStateEnum.AUCTION_ADD_FAIL.getValue().equals(msg)) {
				out.print("alert('添加失败');");
			} else if (AuctionStateEnum.AUCTION_UPDATE_SUCCESS.getValue()
					.equals(msg)) {
				out.print("alert('编辑成功');");
			} else if (AuctionStateEnum.AUCTION_UPDATE_FAIL.getValue().equals(
					msg)) {
				out.print("alert('编辑失败');");
			}%>
	
</script>
</head>

<body>
	<form action="SearchAuctionServlet" method="post">
		<div class="forms">
			<label for="name">名称</label> <input name="auctionName" type="text"
				class="nwinput" id="name" /> <label for="time">开始时间</label> <input
				name="auctionStartTime" type="text" id="time" class="nwinput" /> <label
				for="end-time">结束时间</label> <input name="auctionEndTime" type="text"
				id="end-time" class="nwinput" /> <label for="price">起拍价</label> <input
				name="auctionStartPrice" type="text" id="price" class="nwinput" />
			<input type="submit" value="查询"
				class="spbg buttombg f14  sale-buttom" />
			<c:if test="${sessionScope.user.userIsAdmin==true}">
				<input type="button" onclick="location='addAuction.jsp'" value="发布"
					class="spbg buttombg f14  sale-buttom buttomb" />
			</c:if>
			<br /> &nbsp;&nbsp;&nbsp;&nbsp;<a href="auctionResultServlet"><b>查看竞拍结果</b>
			</a>
		</div>
	</form>


	<form method="post" action="AuctionListServlet">
		<div class="wrap">
			<!-- main begin-->
			<div class="sale">
				<h1 class="lf">在线拍卖系统</h1>
				<c:if test="${not empty sessionScope.user}">
					<div class="logout right">
						<a href="AuctionLogoutServlet" title="注销">注销</a>
					</div>
				</c:if>
				<c:if test="${empty sessionScope.user}">
					<div class="logout right">
						<a href="login.jsp" title="登录">登录</a>
					</div>
				</c:if>
			</div>
			<div class="items">
				<ul class="rows even strong">
					<li>名称</li>
					<li class="list-wd">描述</li>
					<li>开始时间</li>
					<li>结束时间</li>
					<li>起拍价</li>
					<li class="borderno">操作</li>
				</ul>
				<c:forEach items="${auctionPageInfo.lists }" var="auction">
					<ul class="rows">
						<li>${auction.auctionName }</li>
						<li class="list-wd">${auction.auctionDESC }</li>
						<li>${auction.auctionStartTime }</li>
						<li>${auction.auctionEndTime }</li>
						<li>${auction.auctionStartPrice }</li>
						<li class="borderno red"><c:if
								test="${sessionScope.user.userIsAdmin==true }">
								<a
									href="AuctionFindByIdServlet?auctionid=${auction.auctionID}&pageIndex=${auctionPageInfo.pageIndex}">修改</a>
								<a auctionid="${auction.auctionID}" onclick="delAuction(this)"
									href="#">删除</a>
							</c:if> <c:if test="${sessionScope.user.userIsAdmin==false }">
								<a
									href="AuctionRecordServlet?auctionId=${auction.auctionID }&pageIndex=${auctionPageInfo.pageIndex}">竞拍</a>
							</c:if></li>
					</ul>
				</c:forEach>
				<%
					int count = 0;
				%>
				<div class="page">
					<select id="pagenum" onchange="ChangepageNum(this.value)">
						<option value="5">5</option>
						<option value="10">10</option>
						<option value="15">15</option>
						<option value="20">20</option>
						<option value="30">30</option>
					</select> <a href="javascript:goToPage(1)">首页</a>
					<c:if test="${auctionPageInfo.pageIndex!=1}">
						<a href="javascript:goToPage(${auctionPageInfo.pageIndex-1 })">上一页</a>
					</c:if>
					<c:forEach step="1" begin="1" end="${auctionPageInfo.endPage }"
						var="pageIndex">
						<%
							count++;
						%>
						<a href="javascript:goToPage(<%=count%>)"> <%=count%></a>
					</c:forEach>
					<c:if test="${auctionPageInfo.pageIndex!=auctionPageInfo.endPage}">
						<a href="javascript:goToPage(${auctionPageInfo.pageIndex+1 })">下一页</a>
					</c:if>
					<a href="javascript:goToPage(${auctionPageInfo.endPage })">尾页</a>
				</div>
			</div>
			<!-- main end-->
		</div>
	</form>


</body>
</html>
