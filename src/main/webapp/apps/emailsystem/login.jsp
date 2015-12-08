<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
  <title>登陆-电子邮件系统</title>
  <link href="<%=path%>/static/css/login.css" rel='stylesheet' type='text/css' />
  <link href="<%=path%>/plugins/scojs/css/sco.message.css" rel='stylesheet' type='text/css' />
  <link href="<%=path%>/plugins/scojs/css/scojs.css" rel='stylesheet' type='text/css' />
  <script src="<%=path%>/plugins/jquery/jquery-2.1.4.min.js"></script>
  <script src="<%=path%>/plugins/scojs/js/sco.message.js"></script>
  <script src="<%=path%>/static/js/main.js"></script>
</head>
<body>

<h1>电子邮件系统</h1>
<div class="login-form">
  <div class="close"> </div>
  <div class="head-info">
    <label class="lbl-1"></label>
    <label class="lbl-2"></label>
    <label class="lbl-3"></label>
  </div>
  <div class="clear"> </div>
  <div class="avtar"><img src="<%=path%>/static/img/avtar.png" /></div>
  <form id="login-form" action="<%=path%>/user/login" method="post" onsubmit="return checkLoginForm()">
    <div class="key">
      <input type="text" id="username" name="username" placeholder="请输入账号" >
    </div>

    <div class="key">
      <input type="password" id="password" name="password" placeholder="请输入密码">
    </div>
    <div class="signin">
      <input type="submit" id="submit" value="Login" >
    </div>
  </form>
</div>

<script>
  $(function(){
    <c:if test="${not empty param.timeout}">
      $.scojs_message("连接超时,请重新登陆!", $.scojs_message.TYPE_ERROR);
    </c:if>

    if(${not empty username}){
      location.href = "<%=path%>/email";
    }

    if("${info}"){
      $('#submit').attr('value',"${info}").css('background','red');
    }
    $('.close').on('click', function(c){
      $('.login-form').fadeOut('slow', function(c){
        $('.login-form').remove();
      });
    });

    $('#username,#password').change(function(){
      $('#submit').attr('value','Login').css('background','#3ea751');
    });
  });
</script>
</body>
</html>