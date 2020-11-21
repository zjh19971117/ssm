<%--
  Created by IntelliJ IDEA.
  User: jia'hao
  Date: 2020/10/15
  Time: 20:51
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
<script type="text/javascript" src="static/bootstrap/jquery-3.4.1.min.js"></script>
<div class="container">
    <jsp:include page="head.jsp"/>
    <div class="row">
        **商城名称/一级类别/二级类别
    </div>
    <div class="row">
        <div class="col-md-5 col-md-offset-1">
            <img src="${goods.photo}">
        </div>
        <div class="col-md-5">
            <div>商品详情</div>
            <div>名称: ${goods.gname}</div>
            <div>价格: ${goods.price}</div>
            <div>上市时间: ${goods.info}</div>
            <div><button id="btn" name="${goods.gid}">加入购物车</button><button>加入收藏</button></div>
        </div>
    </div>
</div>

<script type="text/javascript">
    // function addCart(id){
    //     //id传给controller处理
    //     //ajax/jQuery
    // }
    $(function () {//页面加载完成后
        var xhr = new XMLHttpRequest();
        //var btn=$("#btn"); //获得对象
        $("#btn").click(function () {
            //this是DOM对象
            var gid=$(this).attr("name");
            $.ajax({
                url:'CartServlet',
                data:{action:"add",id:gid},
                dataType:'json',
                headers:{xhr2:xhr},
                type:'get',
                success:function (data){
                    if(data.code==1){
                        alert("加入购车成功");
                    }else if(data.code==-1){
                        window.location.href="login.jsp";
                    }else{
                        alert("添加失败");
                    }
                },
                error:function (){
                    alert("请求失败");
                }
            });
            //发起请求
            //请求地址/提交参数/回调函数(当请求成功后的执行函数，处理responseText)
            // $.get("CartServlet",{action:"add",id:gid},function (data) {
            //     if(data.code==1){
            //         alert("加入购物车成功!");
            //     }else if(data.code==-1){
            //         window.location.href="login.jsp";
            //         alert("添加失败");
            //     }
            // },"json");
        });
    })
</script>
</body>
</html>
