<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>收件箱-电子邮件系统</title>
  <link rel="stylesheet" type="text/css" href="<%=path%>/plugins/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="<%=path%>/plugins/scojs/css/sco.message.css">
  <link rel="stylesheet" type="text/css" href="<%=path%>/plugins/scojs/css/scojs.css">
  <link rel="stylesheet" type="text/css" href="<%=path%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
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
    <a class="navbar-brand" href="<%=path%>/index"><span class="glyphicon glyphicon-envelope"> </span> 电子邮件系统</a>
  </div>
  <div class="collapse navbar-collapse" id="example-navbar-collapse">
    <ul class="nav navbar-nav">
      <li class="active"><a href="<%=path%>/index"><span class="glyphicon glyphicon-send"></span> 发信箱</a></li>
      <li><a href="<%=path%>/receiver"><span class="glyphicon glyphicon-inbox"></span> 收信箱</a></li>
      <li><a href="<%=path%>/contact"><span class="glyphicon glyphicon-list-alt"></span> 通讯录</a></li>
      <li><a href="<%=path%>/setting"><span class="glyphicon glyphicon-cog"></span> 设置</a></li>
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
          <li><a data-trigger="modal" href="<%=path%>/info.jsp" data-title="Modal title"><span class="glyphicon glyphicon-th"></span> 个人信息</a></li>
          <li class="divider"></li>
          <li><a href="<%=path%>/user/logout"><span class="glyphicon glyphicon-off"></span> 注销</a></li>
        </ul>
      </div>
    </div>
  </div>
</nav>

<div class="container-fluid">
  <div class="page-header">
    <h1>
      <span class="glyphicon glyphicon-send"></span> 发信箱
      <div class="pull-right btn-group">
        <button id="addemail" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span> 新建邮件</button>
      </div>
    </h1>
  </div>

  <div id="content" style="display:none;margin-bottom: 50px">
    <form action="<%=path%>/email/send" method="post" enctype="multipart/form-data" onsubmit="return checkSend();">
      <div class="col-md-10 col-md-offset-1">
        <label for="subject">邮件主题</label>
        <input type="text" class="form-control" id="subject" name="subject" placeholder="这里输入邮件主题">
      </div>
      <div class="col-md-4 col-md-offset-1">
        <label for="emails">收信邮箱</label>
        <input type="text" class="form-control" id="emails" name="emails" placeholder="这里输入邮件收件人">
      </div>
      <div class="col-md-4 col-md-offset-2">
        <label for="time">时间</label>
        <div class="input-group date form_date" style="height:40px" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
          <input id="time" class="form-control" type="text" name="time" style="height:40px" value="" readonly>
            <span class="input-group-addon">
                <span class="glyphicon glyphicon-remove"></span>
            </span>
            <span class="input-group-addon">
                <span class="glyphicon glyphicon-calendar"></span>
            </span>
        </div>
      </div>
      <div class="col-md-10 col-md-offset-1">
        <label for="editor">内容:</label>
        <textarea id="editor" name="editor" style="width:auto;height:500px"></textarea>
      </div>
      <input type="hidden" id="hidden"  name="content">
      <div class="col-md-10 col-md-offset-1">
        <div >
          <label for="files">附件</label>
          <input type="button" class="btn btn-sm btn-success" id="add-file" value="增加一行" >
          <input type="button" class="btn btn-sm btn-danger" id="remove-file" value="删除一行" >
          <input type="file" name="files" id="files" style="margin-top: 10px">
        </div>
        <div id="upload"></div>
      </div>
      <div class="col-md-10 col-md-offset-1"  style="margin-top:30px;margin-bottom:30px" >
        <button type="submit" class="btn btn-lg btn-success"><span class="glyphicon glyphicon-send"></span> 发送</button>
      </div>
    </form>
  </div>

  <div class="col-md-12">
    <div class="panel panel-info">
      <div class="panel-heading">
        <div class="col-md-5"><span class="glyphicon glyphicon-list-alt"></span> 发信列表</div>
        <div align="right">
          <div class="btn-group">
            <button type="button" class="btn btn-sm btn-info dropdowm-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <span class="glyphicon glyphicon-filter"></span> 筛选
              <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
              <li><a href="#">2015年</a></li>
              <li><a href="#">2014年</a></li>
              <li><a href="#">2013年</a></li>
              <li><a href="#">2012年</a></li>
            </ul>
          </div>

          <div class="btn-group">
            <button type="button" class="btn btn-sm btn-info dropdowm-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <span class="glyphicon glyphicon-random"></span> 分类
              <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
              <li><a href="#">官方动态</a></li>
              <li><a href="#">行业新闻</a></li>
            </ul>
          </div>
          <button type="button" class="btn btn-success btn-sm"><span class=" glyphicon glyphicon-refresh" ></span> 刷新列表</button>
          <button type="button" class="btn btn-success btn-sm"><span class=" glyphicon glyphicon-search" ></span> 查询热点</button>
        </div>
      </div>

      <table class="table">
        <th>收信人</th>
        <th>标题</th>
        <th>日期</th>
        <th>状态</th>
        <th width="10%">附件</th>
        <th width="10%">操作</th>
        <tr>
          <td>产品 A</td>
          <td>200</td>
          <td>200</td>
          <td><span class="label label-success">Success</span></td>
          <td id="showfile">
            <button type="button" class="btn btn-info" title="附件标题" data-container="body" data-toggle="popover"
                    dataplacement="top" data-content="file.txt">
              <span class="glyphicon glyphicon-paperclip"></span> 附件
            </button>
          </td>
          <td>
            <div class="btn-group">
              <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                <span class="glyphicon glyphicon-menu-hamburger"></span> 操作
                <span class="caret"></span>
              </button>
              <ul class="dropdown-menu" role="menu">
                <li>
                  <a href="#" data-toggle="modal" data-target="#detailModel">
                    <span class="glyphicon glyphicon-eye-open"></span> 详细
                  </a>
                </li>
                <li class="divider"></li>
                <li>
                  <a href="#" data-toggle="modal" data-target="#deleteModel">
                    <span class="glyphicon glyphicon-trash"></span> 删除
                  </a>
                </li>
              </ul>
            </div>
          </td>
        </tr>
      </table>	<!-- table end -->
    </div>	<!-- panel end -->
  </div>	<!-- container end -->
</div>	<!-- col end -->

<!-- 详情模态框 -->
<div class="modal fade" id="detailModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title" id="myModalLabel">
          <span class="glyphicon glyphicon-envelope"></span> 邮件详情
        </h4>
      </div>
      <div class="modal-body">
        这是邮件的内容,building......
        <a href="view.html">邮件详情</a>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> 关闭</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 删除模态框 -->
<div class="modal fade" id="deleteModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title" id="myModalLabel">
          <span class="glyphicon glyphicon-warning-sign"></span> 警告
        </h4>
      </div>
      <div class="modal-body">
        确认删除这封邮件吗?
      </div>
      <div class="modal-footer">
        <button type="button" id="delete-email" class="btn btn-danger"><span class="glyphicon glyphicon-ok"></span> 确认</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> 关闭</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- 进度条模态框 -->
<div class="modal fade" id="progress-model" tabindex="-1" role="dialog" aria-labelledby="progress" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <%--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>--%>
        <h4 class="modal-title" id="progress">
          <span class="glyphicon glyphicon-globe"></span> 发送中
        </h4>
      </div>
      <div class="modal-body">
        <div class="progress">
          <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 45%">
            <span class="sr-only">60% Complete</span>
          </div>
        </div>
      </div>
      <%--<div class="modal-footer">--%>
        <%--<button type="button" id="delete-email" class="btn btn-danger"><span class="glyphicon glyphicon-ok"></span> 确认</button>--%>
        <%--<button type="button" class="btn btn-primary" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> 关闭</button>--%>
      <%--</div>--%>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script src="<%=path%>/plugins/jquery/jquery-2.1.4.min.js"></script>
<script src="<%=path%>/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=path%>/static/js/main.js"></script>
<script src="<%=path%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script src="<%=path%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=path%>/plugins/ueditor/ueditor.config.js"></script>
<script src="<%=path%>/plugins/ueditor/ueditor.all.js"></script>
<script src="<%=path%>/plugins/scojs/js/sco.message.js"></script>
<script src="<%=path%>/plugins/scojs/js/sco.modal.js"></script>

<script type="text/javascript">
  var index = 1;
  $(document).ready(function(){
    $("#add-file").click(function(){
      document.getElementById("upload").innerHTML+='<div><input type="file" name="file_'+index+'"/></div>';
      index += 1;
    });
  });
  $("#remove-file").click(function(){
    $("#upload div:last").remove();
  });


  var x = 101;
  var stop = 1;
  function run() {
    x += 8;
    $(".progress-bar").css("width",x+"%");
    var timer = setTimeout("run()", 500);
    if (x >100&&stop<1) {
      x = 0;
    }
  }

  function checkSend(){
    var content;
    var subject = $("#subject").val();
    var emails = $("#emails").val();
    ue.ready(function(){
      content = ue.getContent();
    });
    $("#hidden").val(content);
    if(subject == null || subject == ""){
      $.scojs_message("邮件主题不能为空!", $.scojs_message.TYPE_ERROR);
      return false;
    }
    else if(emails == null || emails == ""){
      $.scojs_message("收件人不能为空!", $.scojs_message.TYPE_ERROR);
      return false;
    }
    else if(content == null || content == ""){
      $.scojs_message("邮件内容不能为空!", $.scojs_message.TYPE_ERROR);
      return false;
    }
    $("#progress-model").modal({
      backdrop : "static",
      keyboard : false
    });
    x = 0;
    stop = 0;
    run();
    return true;
  }


  if("${INFO}"){
    $.scojs_message("${INFO}", $.scojs_message.TYPE_OK);
  }
  if("${ERROR}"){
    $.scojs_message("${ERROR}", $.scojs_message.TYPE_ERROR);
  }

  var ue = UE.getEditor("editor");
  $('#addemail').click(function() {
    $('#content').slideToggle('fast');
    return false;
  });

  $('.form_date').datetimepicker({
    language: 'zh-CN',
    weekStart: 1,
    todayBtn: 1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    minView: 2,
    forceParse: 0
  });

  $('#showfile button').each(function(){
    $(this).popover({placement: "right",trigger: "hover"});
  });

  $('#delete-email').on('click', function(e) {
    e.preventDefault();
    $('#deleteModel').modal('hide');
    $.scojs_message('删除成功!', $.scojs_message.TYPE_OK);
  });

</script>
</body>
</html>