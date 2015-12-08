<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>设置</title>
  <link rel="stylesheet" type="text/css" href="<%=path%>/plugins/bootstrap/css/bootstrap.min.css">
  <style>
    b{
      color:red;
    }
  </style>
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
    <a class="navbar-brand" href="<%=path%>/email"><span class="glyphicon glyphicon-envelope"> </span> 电子邮件系统</a>
  </div>
  <div class="collapse navbar-collapse" id="example-navbar-collapse">
    <ul class="nav navbar-nav">
      <li><a href="<%=path%>/email"><span class="glyphicon glyphicon-send"></span> 发信箱</a></li>
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
      <li><a href="<%=path%>/setting">常规设置</a></li>
      <li class="active"><a href="<%=path%>/othersetting">其他设置</a></li>
    </ul>
  </div>
  <div class="col-md-9 settings">
      <div class="well general" style="margin-top:0">
        <div class="control-group">
          <label for="email" class="control-label">附件目录</label>
          <div class="controls">
            <input type="text" class="form-control" placeholder="输入您选择附件上传的目录,默认为根目录下upload文件夹" name="email" id="email">
          </div>
        </div>
        <div class="control-group">
          <label for="sendname" class="control-label">分页大小</label>
          <div class="controls">
            <input type="text" class="form-control" placeholder="输入您选择的分页大小,默认为15条记录/页" name="sendname" id="sendname">
          </div>
        </div>
        <div class="control-group">
          <label for="pass" class="control-label">邮箱密码</label>
          <div class="controls">
            <input type="password" class="form-control" placeholder="发信人邮箱密码" name="pass" id="pass">
          </div>
        </div>

        <div class="control-group">
          <label for="smtp" class="control-label">SMTP服务器</label>
          <div class="controls">
            <input type="text" class="form-control" placeholder="格式为smtp.xx.com" name="smtp" id="smtp">
          </div>
        </div>

        <div class="control-group">
          <label for="smtp_port" class="control-label">SMTP端口</label>
          <div class="controls">
            <input type="text" class="form-control" placeholder="默认为25" name="smtp_port" id="smtp_port">
          </div>
        </div>

      </div>
      <div>
        <button class="btn btn-primary " type="submit">保存</button>
        <button class="btn btn-danger" onclick="destory_all_msg();" style="float:right">删除此账号</button>
      </div>
  </div>
</div>	<!-- col end -->

<div class="modal fade" id="delete-model" tabindex="-1" role="dialog" aria-labelledby="model2" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title" id="model2">
          <span class="glyphicon glyphicon-warning-sign"></span> 警告
        </h4>
      </div>
      <div class="modal-body">
        <h4>
          请注意,您正在请求<b>删除</b>你所登陆的账户!<br><br>
          如果您确认删除,这将会从服务器上<b>完全清除</b>您的<b>所有信息</b>,包括发信箱,收信箱,联系人,分组以及您的设置,<br><br>
          但是<b>不包括</b>存储在邮件服务器上的信息!<br><br>
          确认删除?
        </h4>
      </div>
      <div class="modal-footer">
        <form action="<%=path%>/contact/delete" method="post">
          <button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-ok"></span> 摧毁账户</button>
          <button type="button" class="btn btn-primary" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> 让我想想</button>
        </form>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script src="<%=path%>/plugins/jquery/jquery-2.1.4.min.js"></script>
<script src="<%=path%>/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=path%>/static/js/main.js"></script>
<script>
  function destory_all_msg(){
    $("#delete-model").modal({
      backdrop : "static",
      keyboard : false
    });
  }
</script>
</body>
</html>