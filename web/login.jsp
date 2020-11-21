<%--
  Created by IntelliJ IDEA.
  User: jia'hao
  Date: 2020/10/20
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
<%--    <link type="text/css" rel="stylesheet" href="static/css/index1.css" />--%>
    <style type="text/css">
        #outer{
            width: 1000px;
            height: 400px;
            margin: auto;
        }
        #outer .lay1{
            width: 1000px;
            height: 40px;
            background-color: aqua;
        }
        #outer .lay1 img{
            width: 40px;
            height: 40px;
            margin-left: 100px;
        }
        #lay2{
            height: 360px;
            width: 1000px;
        }
        #lay2 .pic img{
            height: 300px;
            width: 1000px;
            float: left;
            margin-top: 20px;
        }
        #lay2 .pic .layt{
            width: 200px;
            height: 240px;
            background-color: #cccccc;
            margin-left: -270px;
            margin-top: 80px;
            float: left;

        }
        #lay2 .pic .layt .font{
            font-size: 13px;
            color: aliceblue;
        }
        #lay2 .pic .layt .font1{
            font-size: 13px;
            color: aliceblue;
            background-color: coral;
        }
        .err{
            font-size:12px;color:#f00;
        }
       #lay2 .pic .layt .p img{
           width: 30px;
           height: 22px;
       }
    </style>
</head
<body>
<script type="text/javascript" src="static/bootstrap/jquery-3.4.1.min.js"></script>
<div id="outer" align="center">
    <div class="lay1"><img src="static/image/logo_top.png"></div>
    <div id="lay2">
        <div class="pic">
            <img src="static/image/login_bg.jpg">
            <div class="layt"> <form action="UserServlet?action=select" method="post" id="frm">
                <table align="center" border="0" width="200px" height="240px" cellspacing="0">
                    <tr>
                        <td class="font">用户名:</td>
                        <td><input type="text" id="inputname" placeholder="请输入用户名" name="username"><span id="err1" class="err"></span></td>
                    </tr>
                    <tr>
                        <td class="font">密码:</td>
                        <td><input type="password" id="password" placeholder="请输入密码" name="password"></td>
                    </tr>
<%--                    <tr>--%>
<%--                        <td  class="font">邮箱:</td>--%>
<%--                        <td><input type="text" placeholder="请输入邮箱" name="uemail"></td>--%>
<%--                    </tr>--%>
                    <tr>
                        <td class="font">验证码</td>
                        <td><input type="text" name="usrcode" class="form-control" id="usrcode" placeholder="验证码"></td>
                    </tr>
                       <td class="p"><img src="pic" id="pic" style="cursor:pointer"></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input  class="font1" type="submit" name="submit" value="立即登录">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="checkbox" name="nologin" value="free">
                        </td>
                        <td>两周内自动登录</td>
                    </tr>

                </table>
            </form>
            </div>
        </div>

    </div>
</div>
<%--<div style="color:#f00;font-size: 12px;">${err}</div>--%>
<script type="text/javascript">
    //表单验证
    $(function () {
        $("#pic").click(function () {
            $(this).attr("src", "pic?m="+ Math.random());//JS中对象Math，调random方法，产生一个0-1之间的随机数
        });

        $("#inputname").change(function () {
            var val=$(this).val();
            $.ajax({
                url:'UserServlet',
                data:{action:'getCK',name:val},
                type:'get',
                dataType:'json',
                success:function (res) {
                    $("#password").val(res.pass);
                }
            });
        });

        // $("#frm").submit(function () {
        //     var reg = /^\w{6,20}$/; //定义正则
        //     if (!reg.test($(":input[name=username]").val())) {
        //         $("#frm :nth-child(1)").addClass("has-error");
        //         return false;
        //     } else {
        //         $("#frm :nth-child(1)").removeClass("has-error");
        //         $("#frm :nth-child(1)").addClass("has-success");
        //     }
        //     reg = /^\d{6}$/;
        //     if (!reg.test($("#password").val())) {
        //         $("div[class=form-group] :eq(1)").addClass("has-error");
        //         return false;
        //     } else {
        //         $("div[class=form-group]:eq(1)").removeClass("has-error");
        //         $("div[class=form-group]:eq(1)").addClass("has-success");
        //     }
        //     reg = /^[a-zA-Z0-9]{4}$/;
        //     if (!reg.test($("#usrcode").val())) {
        //         $("div[class=form-group]:eq(2)").addClass("has-error");
        //         return false;
        //     } else {
        //         $("div[class=form-group]:eq(2)").removeClass("has-error");
        //         $("div[class=form-group]:eq(2)").addClass("has-success");
        //     }
        //     return true;
        // });
    })
</script>
</body>
</html>
