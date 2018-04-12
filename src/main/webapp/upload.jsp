<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>图片上传</title>
</head>
<body>
<form action="/upload" enctype="multipart/form-data" method="post">
    上传图片：<input type="file" name="file" value="选择图片"><br>
    <input type="submit" value="上传">
</form>
</body>
</html>