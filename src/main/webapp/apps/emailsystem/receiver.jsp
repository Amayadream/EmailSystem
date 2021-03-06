<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>收件箱</title>
  <link rel="stylesheet" type="text/css" href="<%=path%>/plugins/bootstrap/css/bootstrap.min.css">

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
      <li class="active"><a href="<%=path%>/receiver"><span class="glyphicon glyphicon-inbox"></span> 收信箱</a></li>
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
    <h1>
      <span class="glyphicon glyphicon-inbox"></span> 收信箱
      <div class="pull-right btn-group">
        <button id="addemail" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span> 立即收信</button>
      </div>
    </h1>
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
        <tr>
          <td>产品 B</td>
          <td>400</td>
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
                  <a href="view.html"><span class="glyphicon glyphicon-eye-open"></span> 详细</a>
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
        <button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-ok"></span> 确认</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> 关闭</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<script src="<%=path%>/plugins/jquery/jquery-2.1.4.min.js"></script>
<script src="<%=path%>/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=path%>/static/js/main.js"></script>

<script type="text/javascript">

  $('#addemail').click(function() {
    $('#content').slideToggle('fast');
    return false;
  });

  $('#showfile button').each(function(){
    $(this).popover({placement: "right",trigger: "hover"});
  });

</script>
</body>
</html>
