<%@page import="com.qf.tesla.enums.AuctionStateEnum"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
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
<title>商品列表</title>
<link href="css/common.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function goToPage(pageIndex) {
		document.forms[1].action = document.forms[1].action + "?pageIndex="
				+ pageIndex + "&pageNum=${auctionPageVO.pageNum}";
		document.forms[1].submit();
	}
	/*ajas-放回json,ajax 返回xml */
	function deleteAuction(arg) {
		var $usarg = $(arg); //
		var auctionId = $usarg.attr("auctionId");
		auctionIds = {
			"auctionId" : auctionId
		};

		/*删除功能一定要有确认的操作  */
		if (confirm("确认删除？"))//用户点击确认就会放回true,否则false;
		{
			$.ajax({
				url : "auctionDel.action",
				data : auctionIds,
				/*成功回调函数的处理格式  */
				dataType : "json",
				success : function(result) {
					if (result) {
						$usarg.parent().parent().remove();
					}
				},
				error : function() {
					alert("删除失败");
				}
			});
		}
	}

	function changePageNum(value) {
		location.href = "auctionListByPage.action?pageIndex=${auctionPageVO.pageIndex}&pageNum="
				+ value + "";
	}
	//实现元素回显要等文档流加载结束。
	//一下是文档流加载结束后实现的
</script>
<script type="text/javascript">
	$(function() {
		//找到vaule为pageNum的使它被选择，find经常用，可以解决一些被封装的元素的修改
		$("#pageNum").find("option[value=${auctionPageVO.pageNum}]").attr(
				"selected", true);

	});
</script>

<script type="text/javascript">
	
<%String msg = request.getParameter("msg");
			if (AuctionStateEnum.AUCTION_ADD_SUCCESS.getVaule().equals(msg)) {
				out.print("alert('添加成功');");
			}
			if (AuctionStateEnum.AUCTION_ADD_FAIL.getVaule().equals(msg)) {
				out.print("alert('添加失败');");
			}
			if (AuctionStateEnum.AUCTION_UPDATE_SUCCESS.getVaule().equals(msg)) {
				out.print("alert('修改成功');");
			}
			if (AuctionStateEnum.AUCTION_UPDATE_FAIL.getVaule().equals(msg)) {
				out.print("alert('修改失败');");
			}%>
	
</script>
</head>

<body>
	<form action="searchAuctionList.action" method="post">
		<div class="forms">
			<label for="name">名称</label> <input name="auctionName" type="text"
				class="nwinput" id="name" /> <label for="time">开始时间</label> <input
				name="auctionStartTime" type="text" id="time" class="nwinput" /> <label
				for="end-time">结束时间</label> <input name="auctionEndTime" type="text"
				id="end-time" class="nwinput" /> <label for="price">起拍价</label> <input
				name="auctionStartPrice" type="text" id="price" class="nwinput" />
			<input type="submit" value="查询"
				class="spbg buttombg f14  sale-buttom" />
			<shiro:hasPermission name="add">
				<input type="button" onclick="location='addAuction.jsp'" value="发布"
					class="spbg buttombg f14  sale-buttom buttomb" />
			</shiro:hasPermission>
			<br /> &nbsp;&nbsp;&nbsp;&nbsp;<a href="auctionResult.action"><b>查看竞拍结果</b>
			</a>
		</div>
	</form>


	<form method="post" action="auctionListByPage.action">
		<div class="wrap">
			<!-- main begin-->
			<div class="sale">
				<h1 class="lf">在线拍卖系统</h1>
				
				<shiro:user>
				<div class="logout right">
						<a href="logout.action" title="注销">注销</a>
					</div>
				</shiro:user>
				
				<shiro:guest>
				<div class="logout right">
						<a href="login.jsp" title="登录">登录</a>
					</div>
				</shiro:guest>
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
				<c:forEach items="${auctionPageVO.lists }" var="auction">
					<ul class="rows">
						<li>${auction.auctionName }</li>
						<li class="list-wd">${auction.auctionDESC }</li>
						<li>${auction.auctionStartTime }</li>
						<li>${auction.auctionEndTime }</li>
						<li>${auction.auctionStartPrice }</li>
						<li class="borderno red"><shiro:hasPermission name="update">
								<a
									href="findAuctionById.action?auctionId=${auction.auctionId }&pageIndex=${auctionPageVO.pageIndex}">修改</a>
							</shiro:hasPermission> <!-- this指的是当前的整个元素 --> <!-- auctionId 是我们自定义的属性，通常出现在动态的元素列表操作时 -->

							<shiro:hasPermission name="delete">
								<a href="" auctionId="${auction.auctionId }"
									onclick="deleteAuction(this)">删除</a>
							</shiro:hasPermission> <shiro:hasPermission name="auction">
								<a
									href="auctionDetail.action?auctionId=${auction.auctionId }&pageIndex=${auctionPageVO.pageIndex}">竞拍</a>
							</shiro:hasPermission></li>
					</ul>
				</c:forEach>
				<%
					int count = 0;
				%>
				<div class="page">
					<select id="pageNum" onchange="changePageNum(this.value)">
						<option value="5">5</option>
						<option value="10">10</option>
						<option value="15">15</option>
						<option value="20">20</option>
						<option value="30">30</option>
					</select> <a href="javascript:goToPage(1)">首页</a>
					<c:if test="${auctionPageVO.pageIndex!=1}">
						<a href="javascript:goToPage(${auctionPageVO.pageIndex-1 })">上一页</a>
					</c:if>
					<c:forEach step="1" begin="1" end="${auctionPageVO.endPage }"
						var="pageIndex">
						<%
							count++;
						%>
						<a href="javascript:goToPage(<%=count%>)"> <%=count%></a>
					</c:forEach>
					<c:if test="${auctionPageVO.pageIndex!=auctionPageVO.endPage}">
						<a href="javascript:goToPage(${auctionPageVO.pageIndex+1 })">下一页</a>
					</c:if>
					<a href="javascript:goToPage(${auctionPageVO.endPage })">尾页</a>
				</div>
			</div>
			<!-- main end-->
		</div>
	</form>


</body>
</html>
