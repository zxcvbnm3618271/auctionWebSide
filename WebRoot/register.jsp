<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户注册</title>
    
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
	<script type="text/javascript" src="js/register.js"></script>
	
	  <script type="text/javascript">
		<%
			String msg = request.getParameter("msg");
			if("validateCodeError".equals(msg)){
				out.print("alert('验证码错误，请重新输入');");
			}
		%>	
	  </script>
  </head>
  
<body>
<div class="wrap">
  <!-- main begin-->
  <form action="doRegister" method="post">
      <div class="zclf login logns">
        <h1  class="blue">用户注册</h1>
          <dl>
            <dd>
              <label> <small>*</small>用户名</label>
              <input name="username" type="text" class="inputh lf" value="${registerUser.username}" />
             <div  class="lf red laba">用户名要求不低于6个字符</div>
            </dd>
            <dd>
              <label> <small>*</small>密码</label>
              <input name="userpassword" type="text" class="inputh lf" value="${registerUser.userpassword}" />
              <div class="lf red laba">密码要求不低于6个字符</div>
            </dd>
            <dd>
              <label> <small>*</small>身份证号</label>
              <input name="usercardno" type="text" class="inputh lf" value="${registerUser.usercardno}" />
              <div class="lf red laba">身份证号必填</div>
            </dd>
            <dd>
              <label> <small>*</small>电话</label>
              <input name="usertel" type="text" class="inputh lf" value="${registerUser.usertel}" />
              <div class="lf red laba">电话号码必填</div>
            </dd>
            <dd>
              <label> <small>*</small>住址</label>
              <input name="useraddress" type="text" class="inputh lf" value="${registerUser.useraddress}" />
            </dd>
            <dd>
              <label> <small>*</small>邮政编码</label>
              <input name="userpostnumber" type="text" class="inputh lf" value="${registerUser.userpostnumber}" />
            </dd>
            <dd class="hegas">
              <label> <small>*</small>验证码</label>
              <input name="inputCode" type="text" class="inputh inputs lf" value="" />
               <span class="wordp lf"><img id="validateCode" src="Number.jsp" width="96" height="27" alt="" /></span>
               <span class="blues lf"><a id="changeCode" href="javascript:void(0);" title="">看不清</a></span>
            </dd>
            <dd class="hegas">
              <label>&nbsp;</label>
               <input name=""  type="checkbox" id="rem_u" checked="checked"  />
              <label for="rem_u" class="labels">我同意<span class="blues">《服务条款》</span></label>
            </dd>
            <dd class="hegas">
              <label>&nbsp;</label>
              <input name="" type="submit" value="立即注册" class="spbg buttombg buttombgs f14 lf" />
            </dd>
          </dl>
    </div>
   </form>
   
   <img alt="" src="C:\Users\Administrator\Desktop\tomcat7\webapps\auction\upload\20160922112934831.jpg">
<!-- main end-->
<!-- footer begin-->

</div>
 <!--footer end-->
</body>
</html>
