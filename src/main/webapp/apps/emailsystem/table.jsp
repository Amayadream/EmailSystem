<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="<%=path%>/style/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="<%=path%>/style/css/bootstrap-table.css">
  <script src="<%=path%>/style/js/jquery-2.1.4.min.js"></script>
  <script src="<%=path%>/style/js/bootstrap.min.js"></script>
  <script src="<%=path%>/style/js/bootstrap-table.js"></script>
  <script src="<%=path%>/style/js/bootstrap-table-zh-CN.js"></script>
</head>

<body>
  <div class="col-md-8 col-md-offset-2">
    <button class="btn btn-danger" id="button">删除</button>
    <table id="table"
           data-toggle="table"
           data-url="<%=path%>/contact/asd"
           data-side-pagination="server">
    </table>
  </div>


  <script>
    $('#table').bootstrapTable({
      cache: true, // 不缓存
      height : 400,
      striped: true, // 隔行加亮
      pagination: true, // 开启分页功能
      pageSize: 5, // 设置默认分页为 50
      pageList: [5, 10, 20], // 自定义分页列表
      search: true, // 开启搜索功能
      showRefresh: true, // 开启刷新功能
      clickToSelect: true, // 单击行即可以选中
      sortName: 'cid', // 设置默认排序为 name
      sortOrder: 'desc', // 设置排序为反序 desc
      smartDisplay: true, // 智能显示 pagination 和 cardview 等
      columns: [{ // 列设置
        field: 'state',
        checkbox: true // 使用复选框
      },{
        field: 'cid',
        title: '编号',
        align: 'center',
        valign: 'middle',
        sortable: true // 开启排序功能
      },{
          field: 'cname',
          title: '姓名',
          align: 'center',
          valign: 'middle',
          sortable: true // 开启排序功能
        }, {
          field: 'email',
          title: '邮箱',
          align: 'center',
          valign: 'middle',
          sortable: true,
        }, {
          field: 'groupsname',
          title: '分组',
          align: 'center',
          valign: 'middle',
          sortable: true,
//          formatter : groupsFormatter,
//      formatter: priceFormatter,
//      sorter: priceSorter
        },{
        field: 'operate',
        title: '操作',
        align: 'center',
        valign: 'middle',
        clickToSelect: false,
        events: window.operateEvents
      }]
    })
//
//    function groupsFormatter(value){
//        if(value == 1){
//            value = "朋友";
//            return "<span class=\"label label-success\">"+ value +"</span>";
//        }
//    }

    var table = $("#table");
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

    window.operateEvents = {
      'click .like': function (e, value, row) {
        alert('You click like action, row: ' + JSON.stringify(row));
      },
      'click .remove': function (e, value, row) {
        alert('You click remove action, row: ' + JSON.stringify(row));
      }
    };

  </script>
</body>
</html>
