<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>通讯录</title>
  <link rel="stylesheet" type="text/css" href="<%=path%>/plugins/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="<%=path%>/plugins/bootstrap-table/css/bootstrap-table.css">
  <link rel="stylesheet" type="text/css" href="<%=path%>/plugins/scojs/css/sco.message.css">
  <link rel="stylesheet" type="text/css" href="<%=path%>/plugins/scojs/css/scojs.css">
  <%--<link rel="stylesheet" type="text/css" href="<%=path%>/style/css/mosto.css">--%>
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
      <span class="glyphicon glyphicon-list-alt"></span> 通讯录/<small>联系人</small>
      <div class="pull-right btn-group">
        <button id="add-contactor" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span> 添加联系人</button>
      </div>
    </h1>
  </div>

  <div id="content" class="col-md-12" style="display:none;margin-bottom: 50px">
    <form class="col-md-5 control-group well general" action="contact/add" method="post" onsubmit="return checkContactAddForm()">
      <div class="col-md-12">
        <label for="add-name">姓名</label>
        <input type="text" class="form-control" id="add-name" name="name" placeholder="这里输入联系人姓名">
      </div>
      <div class="col-md-12">
        <label for="add-email">邮箱</label>
        <input type="text" class="form-control" id="add-email" name="email" placeholder="这里输入联系人邮箱">
      </div>
      <div class="col-md-12">
        <label for="add-groups">分组</label>
        <input type="text" class="form-control" id="add-groups" name="groups" placeholder="这里输入联系人分组">
      </div>
      <div class="col-md-12"  style="margin-top:20px;margin-bottom:0px" >
        <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-send"></span> 保存</button>
      </div>
    </form>
  </div>

  <%--<div id="content" class="col-md-12" style="display:none;">--%>
    <%--<form action="contact/add" method="post" id="valid_form" class="form-horizontal">--%>
      <%--<label>--%>
        <%--<div>姓名</div> <input type="text" name="name">--%>
      <%--</label>--%>
      <%--<label>--%>
        <%--<div>邮箱</div> <input type="text" name="email">--%>
      <%--</label>--%>
      <%--<label>--%>
        <%--<div>分组</div> <input type="text" name="groups">--%>
      <%--</label>--%>
      <%--<label><div>&nbsp;</div><input type="submit" class="btn btn-primary" value="提交"></label>--%>
    <%--</form>--%>
  <%--</div>--%>

  <div class="col-md-3">
    <ul class="nav nav-pills nav-stacked">
      <li class="active"><a href="<%=path%>/contact">联系人</a></li>
      <li><a href="<%=path%>/groups">分组</a></li>
    </ul>
  </div>

  <div class="col-md-9">
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
        <th>姓名</th>
        <th>邮箱</th>
        <th>分组</th>
        <th>交互</th>
        <th width="10%">操作</th>
        <tr>
          <td>Amayadream</td>
          <td>524806599@qq.com</td>
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
                <li class="divider"></li>
                <li>
                  <a href="" data-toggle="modal" data-target="#deleteModel">
                    <span class="glyphicon glyphicon-trash"></span> 删除
                  </a>
                </li>
              </ul>
            </div>
          </td>
        </tr>
        <tr>
          <td>Jack</td>
          <td>13638040@qq.com</td>
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

  <button class="btn btn-danger" id="button">删除</button>
  <table id="auto" data-pagination-first-text="首页"
         data-pagination-pre-text="上一页"
         data-pagination-next-text="下一页"
         data-pagination-last-text="末页"
         data-toggle="table"
         data-url="<%=path%>/contact/asd"
         data-height="400"
         data-side-pagination="server"
         data-pagination="true"
         data-page-list="[5, 10, 20, 50, 100, 200]"
         data-search="true"></table>
  <thead>
  <tr>
    <th data-field="cid">ID</th>
    <th data-field="cname">Item Name</th>
    <th data-field="email">Item Price</th>
    <th data-field="groups">Item Price</th>
  </tr>
  </thead>

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
      <form action="contactor/edit" method="post" onsubmit="return checkContactEditForm();">
        <div class="modal-body">
          <div>
            <label for="edit-name">姓名</label>
            <input type="text" class="form-control" id="edit-name" name="name" placeholder="这里输入联系人姓名">
          </div>
          <div>
            <label for="edit-email">邮箱</label>
            <input type="text" class="form-control" id="edit-email" name="email" placeholder="这里输入联系人邮箱">
          </div>
          <div>
            <label for="edit-groups">分组</label>
            <input type="text" class="form-control" id="edit-groups" name="groups" placeholder="这里输入联系人分组">
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
<div class="modal fade" id="deleteModel" tabindex="-1" role="dialog" aria-labelledby="model2" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title" id="model2">
          <span class="glyphicon glyphicon-warning-sign"></span> 警告
        </h4>
      </div>
      <div class="modal-body">
        确认删除这个用户吗?
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
<%--<script src="<%=path%>/plugins/js/jquery.form.js"></script>--%>
<script src="<%=path%>/plugins/scojs/js/sco.message.js"></script>
<script src="<%=path%>/plugins/scojs/js/sco.valid.js"></script>
<script src="<%=path%>/plugins/bootstrap-table/js/bootstrap-table.js"></script>
<script src="<%=path%>/plugins/bootstrap-table/js/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript">

  $(function(){
    if("${INFO}"){
      $.scojs_message("${INFO}", $.scojs_message.TYPE_OK);
    }
    if("${ERROR}"){
      $.scojs_message("${ERROR}", $.scojs_message.TYPE_ERROR);
    }

    showContactor();
//    $('#valid_form').scojs_valid({
//      rules:{
//        name: ['not_empty', {'min_length': 2}],
//        email: ['not_empty', 'email'],
//        groups: ['not_empty']
//      },
//      messages:{
//        name : {
//          not_empty : "姓名不能为空!!",
//          min_length : "姓名太短!"
//        },
//        email : {
//          not_empty : "邮箱不能为空!",
//          email : "邮箱格式不正确!"
//
//        },
//        groups : {
//          not_empty : "分组不能为空!!"
//        }
//      }
//    });


    $('#add-contactor').click(function() {
      $('#content').slideToggle('fast');
      return false;
    });

    $('#editor-contactor').click(function(){
      $('#content').slideToggle('fase');
      return false;
    });

    $('#showfile button').each(function(){
      $(this).popover({placement: "right",trigger: "hover"});
    });

  })

</script>
<script>
  $('#auto').bootstrapTable({
    url: '<%=path%>/contact/asd', // 接口 URL 地址
    cache: true, // 不缓存
    height: 400, // 设置高度，会启用固定表头的特性
    striped: true, // 隔行加亮
    pagination: true, // 开启分页功能
    pageSize: 5, // 设置默认分页为 50
    pageList: [5, 10, 20], // 自定义分页列表
    search: true, // 开启搜索功能
//    showColumns: true, // 开启自定义列显示功能
    showRefresh: true, // 开启刷新功能
    minimumCountColumns: 2, // 设置最少显示列个数
    clickToSelect: true, // 单击行即可以选中
    sortName: 'name', // 设置默认排序为 name
    sortOrder: 'desc', // 设置排序为反序 desc
    smartDisplay: true, // 智能显示 pagination 和 cardview 等
    columns: [{ // 列设置
      field: 'state',
      checkbox: true // 使用复选框
    },{
      field: 'cid',
      title: '编号',
      align: 'right',
      valign: 'bottom',
      sortable: true // 开启排序功能
    },
      {
      field: 'cname',
      title: '姓名',
      align: 'right',
      valign: 'bottom',
      sortable: true // 开启排序功能
    }, {
      field: 'email',
      title: '邮箱',
      align: 'center',
      valign: 'middle',
      sortable: true,
//      formatter: nameFormatter
    }, {
      field: 'groups',
      title: '分组',
      align: 'left',
      valign: 'top',
      sortable: true,
      formatter : groupsFormatter,
//      formatter: priceFormatter,
//      sorter: priceSorter
    }, {
      field: 'operate',
      title: '操作',
      align: 'center',
      valign: 'middle',
      clickToSelect: false,
//      formatter: operateFormatter,
      events: window.operateEvents
    }]
  });

  var table = $("#auto");
  var button = $("#button");

  button.click(function(){
    var cid = $.map(table.bootstrapTable('getSelections'),function(row){
      return row.cid;
    });
    console.log(cid);
    table.bootstrapTable('remove',{
      field : 'cid',
      values : cid
    });
  });

  function groupsFormatter(value){
    if(value == 1){
      value = "朋友";
      return "<span class=\"label label-success\">"+ value +"</span>";
    }
  }

  window.operateEvents = {
    'click .like': function (e, value, row) {
      alert('You click like action, row: ' + JSON.stringify(row));
    },
    'click .remove': function (e, value, row) {
      alert('You click remove action, row: ' + JSON.stringify(row));
    }
  };

//  function operateFormatter(value, row, index) {
//    return [
//      '<div class="pull-left">',
//      '<a href="https://github.com/wenzhixin/' + value + '" target="_blank">' + value + '</a>',
//      '</div>',
//      '<div class="pull-right">',
//      '<a class="like" href="javascript:void(0)" title="Like">',
//      '<i class="glyphicon glyphicon-heart"></i>',
//      '</a>  ',
//      '<a class="remove" href="javascript:void(0)" title="Remove">',
//      '<i class="glyphicon glyphicon-remove"></i>',
//      '</a>',
//      '</div>'
//    ].join('');
//  }
</script>
</body>
</html>