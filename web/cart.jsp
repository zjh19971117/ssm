<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jia'hao
  Date: 2020/10/28
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="static/css/bootstrap.min.css">
    <style type="text/css">
        .tips {
            font-size: 20px;
            font-weight: bold;
            margin-top: 15px;
            margin-bottom: 15px;
        }

        .photo {
            width: 100px;
            height: 120px;
        }
    </style>
</head>
<body id="bb">
<script type="text/javascript" src="static/bootstrap/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
    $(function () {
        //遍历每一个减号
        $(".minus").each(function (index) {//i从0开始代表第几个元素
            //针对每一个减号定义一个单击事件
            $(this).click(function () {
                var gid = $(this).attr("name");
                //发送请求到控制器修改session中cart该商品的数量
                $.ajax({
                    url:"CartServlet",
                    data:{action:'modify', id: gid},
                    dataType:"json",
                    type:"get",
                    success:function (data) {
                        //修改页面中显示的数量--
                     $(".nums:eq("+index+")").html(data.num);
                     $("#res").html(data.total);

                    },
                    error: function () {
                        alert("updata num error");
                    }
                });
            });
        });
        //遍历每一个加号号
        $(".plus").each(function (index) {//i从0开始代表第几个元素
            //针对每一个减号定义一个单击事件
            $(this).click(function () {
                var gid = $(this).attr("name");
                //发送请求到控制器修改session中cart该商品的数量
                $.ajax({
                    url:"CartServlet",
                    data:{action:'addCart', id: gid},
                    dataType:"json",
                    type:"get",
                    success:function (data) {
                        //修改页面中显示的数量
                        $(".nums:eq("+index+")").html(data.num);
                        $("#res").html(data.total);
                    },
                    error: function () {
                        alert("updata num error");
                    }
                });
            });
        });
        $(".trash").each(function (i){
            $(this).click(function (){
                var gid = $(this).attr("name");
                $.ajax({
                    url:'CartServlet',
                    data:{action:'del',gid: gid},
                    dataType:'json',
                    type:'get',
                    success:function (data){
                        //删除当前行；remove
                        //更新总价 赋值
                        //$("#tab > tr:eq(i+1)").remove();
                        //$("#res").html(data.total);
                        //局部页面的重新加载 load
                        //alert("load ready");
                        $("#bb").load("CartServlet?action=show")
                    },
                    error:function (){
                        alert("删除失败");
                    }
                });
            });
        });
    })
</script>
<div class="container">
    <jsp:include page="head.jsp"/>
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="row tips">
                请下单支付
            </div>
            <div class="row">
                <table id="tab" class="table table-hover">
                    <tr>
                        <th>商品</th>
                        <th>单价</th>
                        <th>数量</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${items}" var="itms">
                        <tr>
                            <th><a href="GoodsServlet?action=selectOne&id=${itms.goods.gid}"><img class="photo" src="${itms.goods.photo}"></a>
                            </th>
                            <th>¥<span>${itms.goods.price}</span></th>
                            <th>
                                <span name="${itms.goods.gid}" class="glyphicon glyphicon-plus plus"></span>
                                <span class="nums">${itms.num}</span>
                                <span name="${itms.goods.gid}" class="glyphicon glyphicon-minus minus"></span>
                            </th>
<%--                            <th><a href="CartServlet?action=deleteCart"><span class="glyphicon glyphicon-trash"></span></a></th>--%>
                            <th><span name="${itms.goods.gid}" class="glyphicon glyphicon-trash trash"></span></th>
                        </tr>
                    </c:forEach>
                    <tr>
                        <th colspan="4" align="right">
                            总价: ¥<span id="res">${total}</span>
                        </th>
                    </tr>
                    <tr>
                        <th>
                            <a href="CartServlet?action=deleteCart"><button type="button">清空购物车</button></a>
                        </th>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
