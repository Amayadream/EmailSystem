<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>通讯录</title>
  <link rel="stylesheet" type="text/css" href="<%=path%>/plugins/bootstrap/css/bootstrap.min.css">
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
      <li class="active"><a href="<%=path%>/contact"><span class="glyphicon glyphicon-list-alt"></span> 通讯录</a></li>
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
      <span class="glyphicon glyphicon-list-alt"></span> 通讯录/<small>分组</small>
      <div class="pull-right btn-group">
        <button id="add-contactor" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span> 添加分组</button>
      </div>
    </h1>
  </div>

  <div id="content" class="col-md-12" style="display:none;margin-bottom: 50px">
    <form class="col-md-5 well general" action="groups/add" method="post" onsubmit="return checkGroupsAddForm();">
      <div class="col-md-12">
        <label for="add-name">分组名</label>
        <input type="text" class="form-control" id="add-name" name="name" placeholder="这里输入分组名">
      </div>
      <div class="col-md-12"  style="margin-top:20px;margin-bottom:0px" >
        <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-send"></span> 保存</button>
      </div>
    </form>
  </div>

  <div class="col-md-3">
    <ul class="nav nav-pills nav-stacked">
      <li><a href="<%=path%>/contact">联系人</a></li>
      <li class="active"><a href="<%=path%>/groups">分组</a></li>
    </ul>
  </div>

  <div class="col-md-9">
    <div class="panel panel-info">
      <div class="panel-heading">
        <div class="col-md-5"><span class="glyphicon glyphicon-list-alt"></span> 分组列表</div>
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
        <th>分组名</th>
        <th>组员数</th>
        <th width="10%">操作</th>
        <tr>
          <td>朋友</td>
          <td><span class="label label-success">5</span></td>
          <td>
            <div class="btn-group">
              <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                <span class="glyphicon glyphicon-menu-hamburger"></span> 操作
                <span class="caret"></span>
              </button>
              <ul class="dropdown-menu" role="menu">
                <li>
                  <a href="#" data-toggle="modal" data-target="#edit-model">
                    <span class="glyphicon glyphicon-edit"></span> 编辑
                  </a>
                </li>
                <li>
                  <a href="#" data-toggle="modal" data-target="#search-model">
                    <span class="glyphicon glyphicon-search"></span> 查看
                  </a>
                </li>
                <li class="divider"></li>
                <li>
                  <a href="#" data-toggle="modal" data-target="#delete-model">
                    <span class="glyphicon glyphicon-trash"></span> 删除
                  </a>
                </li>
              </ul>
            </div>
          </td>
        </tr>
        <tr>
          <td>同事</td>
          <td><span class="label label-default">0</span></td>
          <td>
            <div class="btn-group">
              <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                <span class="glyphicon glyphicon-menu-hamburger"></span> 操作
                <span class="caret"></span>
              </button>
              <ul class="dropdown-menu" role="menu">
                <li>
                  <a href="#" data-toggle="modal" data-target="#edit-model">
                    <span class="glyphicon glyphicon-edit"></span> 编辑
                  </a>
                </li>
                <li>
                  <a href="#" data-toggle="modal" data-target="#search-model">
                    <span class="glyphicon glyphicon-search"></span> 查看
                  </a>
                </li>
                <li class="divider"></li>
                <li>
                  <a href="#" data-toggle="modal" data-target="#delete-model">
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
<div class="modal fade" id="edit-model" tabindex="-1" role="dialog" aria-labelledby="model1" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title" id="model1">
          <span class="glyphicon glyphicon-edit"></span> 编辑
        </h4>
      </div>
      <form action="groups/edit" method="post" onsubmit="return checkGroupsEditForm();">
        <div class="modal-body">
          <div>
            <label for="edit-name">分组名</label>
            <input type="text" class="form-control" id="edit-name" name="name" placeholder="这里输入分组名">
          </div>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> 保存</button>
          <button type="button" class="btn btn-primary" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> 关闭</button>
        </div>
      </form>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 删除模态框 -->
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
        确认删除这个分组吗?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-ok"></span> 确认</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> 关闭</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- 删除模态框 -->
<div class="modal fade" id="search-model" tabindex="-1" role="dialog" aria-labelledby="model3" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title" id="model3">
          <span class="glyphicon glyphicon-search"></span> 查看
        </h4>
      </div>
      <div class="modal-body">
        <table class="table">
          <caption>同事</caption>
          <th>姓名</th>
          <th>邮箱</th>
          <th>来往邮件</th>
          <tr>
            <td>Amaya</td>
            <td>524806599@qq.com</td>
            <td><span class="label label-default">0</span></td>
          </tr>

          <tr>
            <td>Amaya</td>
            <td>524806599@qq.com</td>
            <td><span class="label label-success">10</span></td>
          </tr>
        </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> 关闭</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<script src="<%=path%>/plugins/jquery/jquery-2.1.4.min.js"></script>
<script src="<%=path%>/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=path%>/static/js/main.js"></script>
<script src="<%=path%>/plugins/scojs/js/sco.message.js"></script>

<script type="text/javascript">
  $(function(){
    if("${INFO}"){
      $.scojs_message("${INFO}", $.scojs_message.TYPE_OK);
    }
    if("${ERROR}"){
      $.scojs_message("${ERROR}", $.scojs_message.TYPE_ERROR);
    }
    $('#add-contactor').click(function() {
      $('#content').slideToggle('fast');
      return false;
    });
    $('#editor-contactor').click(function(){
      $('#content').slideToggle('fase');
      return false;
    });
  });

</script>
</body>
</html>