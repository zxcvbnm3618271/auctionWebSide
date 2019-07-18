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
<title>拍卖商品详情</title>
<link href="css/common.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script>

	$(function(){
		$("#showError").hide();
		$("#saleForm").submit(function(){
			//每次竞拍最大价格
			var maxPrice=$("#details ul:first li:eq(1)").text();
			//竞拍价格
			var inputPrice=$("#sale").val();
			//起拍价格
			var startPrice=$("#startPrice").text();
			if(inputPrice<=startPrice){
				$("#showError").html("不能低于起拍价");
				$("#showError").show();
				return false;
			}else{
				$("#showError").hide();
			}
			if(inputPrice<=maxPrice){
				$("#showError").html("不能低于最高竞拍价");
				$("#showError").show();
				return false;
			}else{
				$("#showError").hide();
			}
			return true;
		});
	});

</script>
<script type="text/javascript">
	<%
String pageIndex=request.getParameter("pageIndex");
String msg=request.getParameter("msg");
if("success".equals(msg))
{
	out.print("alert('竞拍成功')");
}else if("error".equals(msg)){
out.print("alert('竞拍失败')");
}
 %>
</script>
</head>
<body>
	<div class="wrap">
		<!-- main begin-->
		<div class="sale">
			<h1 class="lf">在线拍卖系统</h1>
			<c:if test="${sessionScope.user != null}">
					<div class="logout right">
						<a href="LoginOutServlet" title="注销">注销</a>
					</div>
				</c:if>
				<c:if test="${sessionScope.user == null}">
					<div class="logout right">
						<a href="login.jsp" title="登录">登录</a>
					</div>
				</c:if>
		</div>
		<div class="items sg-font lf">
			<ul class="rows">
				<li>名称：</li>
				<li class="borderno">${AuctionObj.auctionName }</li>
			</ul>
			<ul class="rows">
				<li>描述：</li>
				<li class="borderno">${AuctionObj.auctionDESC }</li>
			</ul>
			<ul class="rows">
				<li>开始时间：</li>
				<li class="borderno">${AuctionObj.auctionStartTime }</li>
			</ul>
			<ul class="rows">
				<li>结束时间：</li>
				<li class="borderno">${AuctionObj.auctionEndTime }</li>
			</ul>
			<ul class="rows border-no">
				<li>起拍价：</li>
				<li class="borderno" id="startPrice">${AuctionObj.auctionStartPrice }</li>
			</ul>
		</div>
		<div class="rg borders">
			<img src="<%=basePath%>upload/${AuctionObj.auctionPICPath }" width="270" alt="" />
		</div>
		<div class="cl"></div>
		<div class="top10 salebd">
			<form action="AuctionRecordAddServlet"
				method="post" id="saleForm">
				<p>
					<label for="sale">出价：</label> <input name="auctionPrice"
						type="text" class="inputwd" id="sale" value=""/> <input
						type="submit" value="竞 拍" class="spbg buttombg f14  sale-buttom" 
						/>
						<input name="auctionid" value="${AuctionObj.auctionID}" style="display:none;">
						<input name="userid" value="${sessionScope.user.userID}" style="display:none;">
						
				</p>
			</form>
			<p class="f14 red" id="showError">不能低于最高竞拍价</p>

		</div>
		<div class="top10">
			<input name="" type="button" value="刷 新" class="spbg buttombg f14" 
			onclick="javascript:location='AuctionRecordServlet?auctionId=${AuctionObj.auctionID }&pageIndex=<%=pageIndex%>'"/>
			<input name="" type="button" value="返回列表" class="spbg buttombg f14"
				onclick="javascript:location='AuctionListServlet?pageIndex=<%=pageIndex%>'" />
		</div>

		<div class="offer">
			<h3>出价记录</h3>
			<div class="items sg-font">
				<c:if test="${record_list eq null || record_list eq ''}">
					<h1>此拍卖品没有人竞拍</h1>
				</c:if>
				
				<c:if test="${record_list!=null}">
					<ul class="rows even strong">
						<li>竞拍时间</li>
						<li>竞拍价格</li>
						<li class="borderno">竞拍人</li>
					</ul>
					<div id="details">
					<c:forEach items="${record_list}" var="record" varStatus="index">
						<ul class="rows">
							<li>${record.auctionTime }</li>
							<li id="price${index.index }">${record.auctionPrice }</li>
							<li class="borderno">${record.auctionUser.userName }</li>
						</ul>
					</c:forEach>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>
