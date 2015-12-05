<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html >
<html>
<head>
  <script src="<%=path%>/plugins/jquery/jquery-2.1.4.min.js"></script>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Insert title here</title>
  <script type="text/javascript">
    var i = 1;
    var j = 1;
    $(document).ready(function(){
      $("#add-file").click(function(){
        document.getElementById("upload").innerHTML+='<div id="div_'+j+'"><input  name="file_'+j+'" type="file"  /><input type="button" class="btn btn-danger" value="删除"  onclick="del('+j+')"/></div>';
        j = j + 1;
      });
    });
    function del(j){
      document.getElementById("upload").removeChild(document.getElementById("div_"+j));
    }

  </script>
</head>
<body>
<h1>springMVC包装类上传文件</h1>
<form name="form" action="<%=path%>/file/multiUpload" enctype="multipart/form-data" method="post">
  <div id="upload">
    <input type="file" name="file">
  </div>
  <input type="button" id="add-file" value="增加一行" >
  <input type="submit" value="上传" >
</form>
</body>
</html>