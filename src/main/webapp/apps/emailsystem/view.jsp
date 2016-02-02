<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>邮件详情</title>
  <link rel="stylesheet" type="text/css" href="<%=path%>/plugins/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="<%=path%>/plugins/scojs/css/sco.message.css">
  <link rel="stylesheet" type="text/css" href="<%=path%>/plugins/scojs/css/scojs.css">

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
      <li class="active"><a href="<%=path%>/email"><span class="glyphicon glyphicon-send"></span> 发信箱</a></li>
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
    <h1><span class="glyphicon glyphicon-envelope"></span> 邮件详情
      <button id="addemail" class="btn btn-primary" onclick="javascript:window.location.href='<%=path%>/email'"><span class="glyphicon glyphicon-arrow-left"></span> 返回</button></h1>
  </div>

  <div class="col-md-12">
    <h2>${email.subject}</h2>
    <hr>
    <table class="table table-bordered">
      <th>收件人</th>
      <th>发送时间</th>
      <th>状态</th>
      <th>附件</th>
      <tr>
        <td>
          <c:forEach items="${fn:split(email.emails,';')}" var="receiver" varStatus="index">
            <c:if test="${email.status == 1}">
              <span class="label label-success">${receiver}</span>
            </c:if>
            <c:if test="${email.status == 0}">
              <span class="label label-default">${receiver}</span>
            </c:if>
          </c:forEach>
        </td>
        <td>
          <c:if test="${email.status == 1}">
            <span class="label label-success">${email.sendtime}</span>
          </c:if>
          <c:if test="${email.status == 0}">
            <span class="label label-default">${email.sendtime}</span>
          </c:if>
        </td>
        <td>
          <c:if test="${email.status == 1}">
            <span class="label label-success">发送成功</span>
          </c:if>
          <c:if test="${email.status == 0}">
            <span class="label label-default">发送失败</span>
          </c:if>
        </td>
        <td>
          <c:if test="${email.files != ''}">
            <c:forEach items="${fn:split(email.files,';')}" var="f" varStatus="index">           <!-- 遍历按照分号切割好的文件名称 -->
              <c:if test="${email.status == 1}">
                <span class="label label-info"><a href="<%=path%>/email/download?id=${f}">${file[index.count - 1]}</a></span>
              </c:if>
              <c:if test="${email.status == 0}">
                <span class="label label-default"><a href="<%=path%>/email/download?id=${f}">${file[index.count - 1]}</a></span>
              </c:if>
            </c:forEach>
          </c:if>
        </td>
      </tr>
    </table>

    <a href="" id="showcontent"><span class="glyphicon glyphicon-eye-open"></span> 查看邮件内容</a>
    <div id="content" style="display: none;">
      <div>
        ${email.content}
      </div>
    </div>
  </div>	<!-- col end -->
</div>	<!-- container end -->


<script src="<%=path%>/plugins/jquery/jquery-2.1.4.min.js"></script>
<script src="<%=path%>/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=path%>/plugins/scojs/js/sco.tooltip.js"></script>
<script src="<%=path%>/static/js/main.js"></script>

<script type="text/javascript">
  $('#showcontent').click(function() {
    $('#content').slideToggle('fast');
    return false;
  });

  $('#showfile button').each(function(){
    $(this).popover({placement: "right",trigger: "hover"});
  });

</script>
</body>
</html>