<%--
  Created by IntelliJ IDEA.
  User: jia'hao
  Date: 2020/10/16
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="static/bootstrap/css/bootstrap.min.css"/>
</head>
<body>
<form action="UserServlet?action=update" method="post">
    <input type="hidden" name="id" value="${users.uid}">
    <div class="form-group">
        <label for="exampleUsername1">Username</label>
        <input type="text" class="form-control" id="exampleUsername1" placeholder="Username" value="${users.uname}" name="uname">
    </div>
    <div class="form-group">
        <label for="exampleInputUgender">Ugender</label>
        <input type="text" class="form-control" id="exampleInputUgender" placeholder="Ugender" value="${users.ugender}" name="ugender">
    </div>
    <div class="form-group">
        <label for="exampleInputEmail1">Email address</label>
        <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Email" value="${users.uemail}" name="uemail">
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
<%--    昵称:<input type="text" name="uname" value="${users.uname}"><br>--%>
<%--    性别:<input type="text" name="ugender" value="${users.ugender}"><br>--%>
<%--    邮箱:<input type="text" name="uemail" value="${users.uemail}"><br>--%>
<%--    <input type="submit" name="提交">--%>
</form>
</body>
</html>
