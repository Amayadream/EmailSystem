<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%=path%>/style/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/style/css/scojs.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/style/css/sco.message.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/style/css/mosto.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/style/css/bootstrap-table.css">
    <script src="<%=path%>/style/js/jquery-2.1.4.min.js"></script>
    <script src="<%=path%>/style/js/jquery.form.js"></script>
    <script src="<%=path%>/style/js/bootstrap.min.js"></script>
    <script src="<%=path%>/style/js/sco.valid.js"></script>
    <script src="<%=path%>/style/js/sco.modal.js"></script>
    <script src="<%=path%>/style/js/sco.confirm.js"></script>
    <script src="<%=path%>/style/js/sco.collapse.js"></script>
    <script src="<%=path%>/style/js/sco.ajax.js"></script>
    <script src="<%=path%>/style/js/sco.message.js"></script>
    <script src="<%=path%>/style/js/sco.tooltip.js"></script>
    <script src="<%=path%>/style/js/bootstrap-table.js"></script>
</head>

<body>
<a data-trigger="confirm" href="/delete/me" class="btn">Launch demo confirm</a><br>

<a href="#" data-trigger="tooltip" data-content="file.zip,file.txt">Hover me</a><br>

<a data-trigger="modal" href="about.jsp" data-title="Modal title" class="btn">Launch demo modal</a><br>

<a href="#" data-trigger="collapse">Click for default behaviour</a><br>
<div class="collapsible hide">
    Lorem ipsum...
</div>

<a id="message_trigger_ok" href="#">Click to see the info message</a>
<a id="message_trigger_err" href="#">Click to see the error message</a>
<script>
    $('#message_trigger_ok').on('click', function(e) {
        e.preventDefault();
        $.scojs_message('This is an info message', $.scojs_message.TYPE_OK);
    });
    $('#message_trigger_err').on('click', function(e) {
        e.preventDefault();
        $.scojs_message('This is an error message', $.scojs_message.TYPE_ERROR);
    });
</script>

<form action="valid.json" id="valid_form" class="form-horizontal">
    <label><div>Email</div> <input type="email" name="email"></label><br>
    <label><div>Password</div> <input type="password" name="pass"></label><br>
    <label><div>Password again</div> <input type="password" name="pass2"></label><br>
    <button type="submit" class="btn">Try</button>
</form>

<table id="table"></table>

<script>
    $('#valid_form').scojs_valid({
        rules:{
            email: ['not_empty', 'email'],
            pass: ['not_empty', {'min_length': 4}],
            pass2: [{matches: 'pass'}]
        },
        messages:{
            email : {
                not_empty : "邮箱不能为空!",
                email : "邮箱格式不正确!"
            },
            pass : {
                not_empty : "密码不能为空!",
                min_length : "密码必须为4位以上!"
            },
            pass2 : {
                matches : "两次输入的密码不匹配!"
            }
        }
    });

    $('#table').bootstrapTable({
        url: '<%=path%>/resource/tableExport.json', // 接口 URL 地址
        cache: false, // 不缓存
        height: 400, // 设置高度，会启用固定表头的特性
        striped: true, // 隔行加亮
        pagination: true, // 开启分页功能
        pageSize: 50, // 设置默认分页为 50
        pageList: [10, 25, 50, 100, 200], // 自定义分页列表
        search: true, // 开启搜索功能
        showColumns: true, // 开启自定义列显示功能
        showRefresh: true, // 开启刷新功能
        minimumCountColumns: 2, // 设置最少显示列个数
        clickToSelect: true, // 单击行即可以选中
        sortName: 'name', // 设置默认排序为 name
        sortOrder: 'desc', // 设置排序为反序 desc
        smartDisplay: true, // 智能显示 pagination 和 cardview 等
        columns: [{ // 列设置
            field: 'state',
            checkbox: true // 使用复选框
        }, {
            field: 'id',
            title: 'Item ID',
            align: 'right',
            valign: 'bottom',
            sortable: true // 开启排序功能
        }, {
            field: 'name',
            title: 'Item Name',
            align: 'center',
            valign: 'middle',
            sortable: true,
            formatter: nameFormatter
        }, {
            field: 'price',
            title: 'Item Price',
            align: 'left',
            valign: 'top',
            sortable: true,
            formatter: priceFormatter,
            sorter: priceSorter
        }, {
            field: 'operate',
            title: 'Item Operate',
            align: 'center',
            valign: 'middle',
            clickToSelect: false,
            formatter: operateFormatter,
            events: operateEvents
        }]
    });
</script>
</body>
</html>
