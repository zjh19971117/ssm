<%--
  Created by IntelliJ IDEA.
  User: jia'hao
  Date: 2020/10/26
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
<link type="text/css" rel="stylesheet" href="static/bootstrap/css/bootstrap.min.css"/>
<link type="text/css" rel="stylesheet" href="static/css/head.css"/>

</head>
<body>
    <script type="text/javascript" src="static/bootstrap/css/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
        $(function (){
            $("#logout").click(function (){
                location.href="UserServlet?action=logout";
            });
            $("#search").click(function () {
                var key = $("#content").val();
                location.href = "GoodsServlet?action=selectGoodsLike&key="+$("#content").val();
            });
            //     $.ajax({
            //         url:'GoodsServlet',
            //         data:{action:'selectGoodsLike',name:key},//String search=request.getParameter("search"); String[] searchs=search.split(" ");
            //         dataType:'json',
            //         type:'get',
            //         success:function (res) {
            //           for(var i in res){
            //              res[i].gname
            //           }
            //         }
            //     });
            // });
        })
            <%--$("#gwc").click(function (){--%>
            <%--    $.get("cart",{action:"show"});--%>
            <%--    var url=${this}.attr("href");--%>
            <%--    $.getJSON(url,function (res){--%>
            <%--       if(res.code==-1){--%>
            <%--           location.href="login.jsp";--%>
            <%--           return false;--%>
            <%--       }else{--%>
            <%--           return true;--%>
            <%--       }--%>
            <%--    });--%>
            <%--});--%>

    </script>
    <div class="row bt_lay1">
        <div class="col-md-1 col-md-offset-2">mi商城</div>
        <div class="col-md-1 scyd">商城移动版</div>
        <div class="col-md-1">问题反馈</div>
        <div class="col-md-1 col-md-offset-3 dl"><c:if test="${empty users.uname}"><a href="login.jsp">登陆</a></c:if><c:if test="${!empty users.uname}">${users.uname}</c:if></div>
        <div class="col-md-1 dl">注册</div>
        <div class="col-md-1 gwc"><a href="CartServlet?action=show" id="gwc">购物车</a></div>
        <div class="col-md-1" id="logout" style="cursor: pointer">注销登录</div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div id="lay1">
                <div class="left1"><img src="static/image/logo_top.png"></div>
                <div class="left2"><img src="static/image/peijian3.jpg"></div>
                <div class="right">
                    <div class="ip"><input type="text" id="content"></div>
                    <div class="pic"><img src="static/image/search.png" id="search"></div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
