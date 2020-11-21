<%--
  Created by IntelliJ IDEA.
  User: jia'hao
  Date: 2020/10/14
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>$Title$</title>
    <link type="text/css" rel="stylesheet" href="static/css/index1.css"/>
    <link type="text/css" rel="stylesheet" href="static/bootstrap/css/bootstrap.min.css"/>
</head>
<body>
<script type="text/javascript" src="static/js/index.js"></script>
<div class="container">
    <%--第一层--%>
    <jsp:include page="head.jsp"/>
</div>
<div id="outer">
<%--    <div id="lay1">--%>
<%--        <div class="left1"><img src="static/image/logo_top.png"></div>--%>
<%--        <div class="left2"><img src="static/image/peijian3.jpg"></div>--%>
<%--        <div class="de"><a href="login.jsp">登录</a></div>--%>
<%--        <div class="de1"><a href="add.jsp">注册</a></div>--%>
<%--        <div class="right">--%>
<%--            <div><img src="static/image/search.png"></div>--%>
<%--            <div><input type="text" name="input"></div>--%>
<%--        </div>--%>
<%--    </div>--%>
    <%--        第二层--%>
    <div id="lay2">
        <div class="img1"><img src="static/image/banner.jpg"><img src="static/image/banner2.jpg"><img
                src="static/image/banner3.jpg"><img src="static/image/banner4.jpg"></div>
    </div>
    <%--第三层--%>
    <div id="lay3">
        <div class="a1">
            <div><img src="static/image/hjh_01.gif"></div>
            <div><img src="static/image/hjh_02.gif"></div>
            <div><img src="static/image/hjh_03.gif"></div>
            <div><img src="static/image/hjh_04.gif"></div>
            <div><img src="static/image/hjh_05.gif"></div>
            <div><img src="static/image/hjh_06.gif"></div>
        </div>
        <div class="a2"><img src="static/image/hongmi4x.png"></div>
        <div class="a3"><img src="static/image/xiaomi5.jpg"></div>
        <div class="a4"><img src="static/image/pinghengche.jpg"></div>
    </div>
    <%--第四层--%>
    <div id=lay>小米明星单品</div>
    <div id="lay4">
        <div class="m1">
        </div>
        <div class="m2"></div>
        <div class="m3"></div>
        <div class="m4"></div>
        <div class="m5"></div>
    </div>
    <%--第五层--%>
    <div id="lay5">
        <c:forEach var="goods" items="${glist}">
            <div class="items">
                <div class="pic"><a href="GoodsServlet?id=${goods.gid}&action=selectOne"> <img src="${goods.photo}"></a>
                </div>
                <div class="name">${goods.gname}</div>
                <div class="info">${goods.info}</div>
                <div class="price">${goods.price}</div>
            </div>
        </c:forEach>
<%--        <div class="items1">--%>
<%--            <div class="font">--%>
<%--                <a href="GoodsServlet?page=1">首页</a>--%>
<%--                <c:if test="${pb.page>1}">--%>
<%--                    <a href="GoodsServlet?action=selectAll&page=${pb.page-1}">上一页</a>--%>
<%--                </c:if>--%>
<%--                <c:if test="${pb.page==1}">--%>
<%--                    <a>上一页</a>--%>
<%--                </c:if>--%>
<%--                <c:if test="${pb.page < pb.pageNum}">--%>
<%--                    <a href="GoodsServlet?action=selectAll&page=${pb.page+1}">下一页</a>--%>
<%--                </c:if>--%>
<%--                <c:if test="${pb.page==pb.pageNum}">--%>
<%--                    <a>下一页</a>--%>
<%--                </c:if>--%>
<%--                <a href="GoodsServlet?action=selectAll&page=${pb.pageNum}">尾页</a>--%>
<%--            </div>--%>
<%--        </div>--%>
    </div>
    <div id="lay6">
        <div class="c1">
            <div>
                小米商城|MIUI米聊|聊多看书城|小米路由器|小米电话|小米天猫店|小米淘宝直营店|小米移动|关于我们
            </div>
        </div>
    </div>
    <%--    不参与页面布局    --%>
    <div id="mc" onmouseover="clearInterval(A)" onmouseout="A=setInterval('show()',1000);">
        <div class="items" onmouseover="showImage(this)">1</div>
        <div class="items" onmouseover="showImage(this)">2</div>
        <div class="items" onmouseover="showImage(this)">3</div>
        <div class="items" onmouseover="showImage(this)">4</div>
    </div>
    <div id="menu">
        <c:forEach var="catalog" items="${clist}">
        <div class="items" onmouseover="loadItems(1,${catalog.cid})" onmouseout="loadItems(2)">
            <a href="${catalog.url}">${catalog.cname}<span class="gt">&gt;</span></a>
        </div>
        </c:forEach>
    </div>
    <div id="menu_item" onmouseover="this.style.display='block'" onmouseout="this.style.display='block'">
<%--        <div class="inner">--%>
<%--        <div class="a1"><img src="图片"></div>--%>
<%--        <div class="a2">二级分类的名称</div>--%>
<%--        &lt;%&ndash;二级分类&ndash;%&gt;--%>
<%--        </div>--%>
    </div>
</div>
</body>
</html>
