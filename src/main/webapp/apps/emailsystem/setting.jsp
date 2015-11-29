<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>设置</title>
  <link rel="stylesheet" type="text/css" href="<%=path%>/plugins/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="<%=path%>/plugins/scojs/css/scojs.css">
  <link rel="stylesheet" type="text/css" href="<%=path%>/plugins/scojs/css/sco.message.css">

</head>
<body>
<nav class="navbar navbar-inverse" role="navigation">
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#example-navbar-collapse">
      <span class="sr-only">切换导航</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
    <a class="navbar-brand" href="<%=path%>/index.jsp"><span class="glyphicon glyphicon-envelope"> </span> 电子邮件系统</a>
  </div>
  <div class="collapse navbar-collapse" id="example-navbar-collapse">
    <ul class="nav navbar-nav">
      <li><a href="<%=path%>/index"><span class="glyphicon glyphicon-send"></span> 发信箱</a></li>
      <li><a href="<%=path%>/receiver"><span class="glyphicon glyphicon-inbox"></span> 收信箱</a></li>
      <li><a href="<%=path%>/contact"><span class="glyphicon glyphicon-list-alt"></span> 通讯录</a></li>
      <li class="active"><a href="<%=path%>/setting"><span class="glyphicon glyphicon-cog"></span> 设置</a></li>
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-flag"></span> 帮助 <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="<%=path%>/about#about-web"><span class="glyphicon glyphicon-home"></span> 关于此站</a></li>
          <li><a href="<%=path%>/about#about-tech"><span class="glyphicon glyphicon-leaf"></span> 所用技术</a></li>
          <li><a href="<%=path%>/about#about-opensource"><span class="glyphicon glyphicon-tint"></span> 获取源码</a></li>
          <li><a href="<%=path%>/about#about-bug"><span class="glyphicon glyphicon-comment"></span> 反馈Bug</a></li>
          <li class="divider"></li>
          <li><a href="<%=path%>/about#about-author"><span class="glyphicon glyphicon-heart"></span> 关于作者</a></li>
        </ul>
      </li>
    </ul>
    <div class="navbar-right" style="margin-right:5px">
      <div class="btn-group">
        <button type="button" class="btn  navbar-btn dropdown-toggle" data-toggle="dropdown">
          <span class="glyphicon glyphicon-user"></span> ${username}
          <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
          <li><a href="#"><span class="glyphicon glyphicon-th"></span> 个人信息</a></li>
          <li class="divider"></li>
          <li><a href="<%=path%>/user/logout"><span class="glyphicon glyphicon-off"></span> 注销</a></li>
        </ul>
      </div>
    </div>
  </div>
</nav>

<div class="container-fluid">

  <div class="page-header">
    <h1><span class="glyphicon glyphicon-cog"></span> 设置</h1>
  </div>
  <div class="col-md-3">
    <ul class="nav nav-pills nav-stacked">
      <li class="active"><a href="<%=path%>/setting">常规设置</a></li>
      <li><a href="<%=path%>/othersetting">其他设置</a></li>
    </ul>
  </div>
  <div class="col-md-9 settings">
    <form class="form-horizontal" action="setting/edit" method="post" onsubmit="return checkSettingEditForm();">
      <div class="well general" style="margin-top:0">
        <div class="control-group">
          <label for="sendmail" class="control-label">邮箱账号</label>
          <div class="controls">
            <input type="text" class="form-control" placeholder="发信人邮箱账号" name="sendmail" id="sendmail">
          </div>
        </div>
        <div class="control-group">
          <label for="sendname" class="control-label">发信昵称</label>
          <div class="controls">
            <input type="text" class="form-control" placeholder="发信昵称" name="sendname" id="sendname">
          </div>
        </div>
        <div class="control-group">
          <label for="sendpass" class="control-label">邮箱密码</label>
          <div class="controls">
            <input type="password" class="form-control" placeholder="发信人邮箱密码" name="sendpass" id="sendpass">
          </div>
        </div>
        <div class="control-group">
          <label for="smtpserver" class="control-label">SMTP服务器</label>
          <div class="controls">
            <input type="text" class="form-control" placeholder="格式为smtp.xx.com" name="smtpserver" id="smtpserver">
          </div>
        </div>
        <div class="control-group">
          <label for="smtpport" class="control-label">SMTP端口</label>
          <div class="controls">
            <input type="text" class="form-control" placeholder="默认为25" name="smtpport" id="smtpport">
          </div>
        </div>

      </div>
      <div class="form-actions">
        <button class="btn btn-primary " type="submit">保存</button>
      </div>
    </form>
  </div>
</div>	<!-- col end -->

<script src="<%=path%>/plugins/jquery/jquery-2.1.4.min.js"></script>
<script src="<%=path%>/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=path%>/static/js/main.js"></script>
<script src="<%=path%>/plugins/scojs/js/sco.message.js"></script>
<script>
  $(function(){
    showSetting();
    if("${INFO}"){
      $.scojs_message("${INFO}", $.scojs_message.TYPE_OK);
    }
    if("${ERROR}"){
      $.scojs_message("${ERROR}", $.scojs_message.TYPE_ERROR);
    }
    $("#sendpass").mouseenter(function(){
      $("#sendpass").attr("type","text");
    })

    $("#sendpass").mouseleave(function(){
      $("#sendpass").attr("type","password");
    })
  });
</script>
</body>
</html>