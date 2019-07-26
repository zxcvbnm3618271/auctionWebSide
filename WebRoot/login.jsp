<%@page import="com.qf.tesla.enums.AuctionLoginStateEnum"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>用户登录</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="css/common.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript">
	
<%String msg = (String) request.getAttribute("msg");
			if (AuctionLoginStateEnum.AUCTION_lOGIN_SUCCESS.getValue().equals(
					msg)) {
				out.print("alert('登录成功');");
			}
			if (AuctionLoginStateEnum.AUCTION_lOGIN_FAIL.getValue().equals(msg)) {
				out.print("alert('用户名密码错误，请重新登录');");
			}
			if (AuctionLoginStateEnum.AUCTION_lOGIN_VALIDATACOMDE_ERROR
					.getValue().equals(msg)) {
				out.print("alert('验证码错误，请重新输入');");
			}%>
	
</script>
</head>
<body>
	<div class="wrap">
		<!-- main begin-->
		<div class="main">
			<div class="sidebar">
				<p>
					<img src="images/img1.jpg" width="443" height="314" alt="" />
				</p>
			</div>
			<div class="sidebarg">
				<!-- form 表单代表向服务器提交数据的表单 之前我们知道了 用户向服务器提交数据的方式
     localhost:8080/auction_chapter/******?usname=test1&pwd=123
     
     FORM中的
     INPUT表单 代表向服务器提交  数据的元素 ！
     INPUT 也是以键值的方式去提交  提交数据到 请求行中
   
     -->
				<form action="login.action" method="post">
					<div class="login">
						<dl>
							<dt class="blues">用户登陆</dt>
							<dd style="width:600px;">
								<label for="name">用户名：</label> <input type="text"
									name="userName" class="inputh" value="${username}" id="name" />
								<span style="color:red;"> <s:fielderror>
										<s:param>usNameMsg</s:param>
									</s:fielderror> </span>
							</dd>
							<dd style="width:600px;">
								<label for="password">密 码：</label> <input type="password"
									name="userPassWord" class="inputh" value="${userpassword}"
									id="password" /> <span style="color:red;"> <s:fielderror>
										<s:param>pwdMsg</s:param>
									</s:fielderror> </span>
							</dd>
							<dd>
								<label class="lf" for="passwords">验证码：</label> <input
									type="text" name="userInputcode" class="inputh inputs lf"
									value="验证码" id="passwords" /> <span class="wordp lf"><img
									id="validateCode" src="Number.jsp" width="96" height="27"
									alt="" />
								</span> <span class="blues lf"><a id="changeCode"
									href="javascript:void(0);" title="">看不清</a>
								</span>
							</dd>
							<dd>
								<input name="remeberMe" type="checkbox" id="rem_u" /> <span
									class="rem_u">下次自动登录</span>
							</dd>
							<dd class="buttom">
								<input name="" type="submit" value="登 录"
									class="spbg buttombg f14 lf" /> <input id="register" name=""
									type="button" value="注 册" class="spbg buttombg f14 lf" /> <span
									class="blues  lf"><a href="" title="">忘记密码?</a>
								</span>
								<div class="cl"></div>
							</dd>
						</dl>
					</div>
				</form>
			</div>
			<div class="cl"></div>
		</div>
		<!-- main end-->
		<!-- footer begin-->
	</div>
	<!--footer end-->
</body>
</html>
