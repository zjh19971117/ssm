<%--
  Created by IntelliJ IDEA.
  User: jia'hao
  Date: 2020/10/23
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
<link type="text/css" rel="stylesheet" href="static/bootstrap/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="static/css/list.css"/>
</head>
<body>
<script type="text/javascript" src="static/bootstrap/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="static/bootstrap/js/bootstrap.min.js"></script>

<div class="container">
    <%--引入head.jsp内容--%>
    <jsp:include page="head.jsp"/>
    <div class="row">
        <div class="cos-md-12">
            <c:forEach items="${list}" var="gd">
                <div class="goods">
                    <div class="row1 r1">
                        <a href="GoodsServlet?action=selectOne&id=${gd.gid}"><img class="pic" src="${gd.photo}"/></a>
                    </div><%--document.getElementsByClssName("pic")--%>
                    <div class="row1 r3">
                            ${gd.gname}
                    </div>
                    <div class="row1 r3">
                            ${gd.price}</div>
                    <div class="row1 r2">
                        <div class="a"><img src="${gd.pic1}" onmouseover="change(this)"></div>
                        <div class="a"><img src="${gd.pic2}" onmouseover="change(this)"></div>
                        <div class="a"><img src="${gd.pic3}" onmouseover="change(this)"></div>
                        <div class="a"><img src="${gd.pic4}" onmouseover="change(this)"></div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<script type="text/javascript">
    function change(x){//x代表当前操作对象
        x.parentNode.parentNode.previousElementSibling.previousElementSibling.previousElementSibling.children[0].children[0].src=x.src;
    }
</script>
</body>
</html>
