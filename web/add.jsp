<%--
  Created by IntelliJ IDEA.
  User: jia'hao
  Date: 2020/10/21
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script type="text/javascript">
    window.onload = function(){
        ips = document.getElementsByTagName("input");
        err = document.getElementsByClassName("err");
    };
    function check(val) {
        var name = val.value;
        var xhr = new XMLHttpRequest();
        xhr.open("get", "UserServlet?action=check&name=" + name);
        xhr.send();
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4) {
                if (xhr.status == 200) {
                    if (xhr.responseText =="ok") {
                        alert(123456);
                        err[1].innerHTML = "可以使用";
                    } else {
                        alert("321231");
                        err[1].innerHTML = "该用户已存在";
                    }
                }
            }
        }
    }
    function ff(){
        var reg =  /^[a-zA-Z]{6,20}$/;
        var reg1 =  /^[0-9]{6,}$/;
        var reg2 = /^[a-zA-Z0-9]\w{5,}@\w{2,}\.\w{2,}(\.\w{2,})?$/;;
        if(!reg.test(ips[1].value)){
            err[1].innerHTML = "格式错误";
            return false;
        }else{
            err[1].innerHTML="";
        }
        if(!reg1.test(ips[2].value)){
            err[2].innerHTML="请输入六位数字";
            return false;
        }else{
            err[1].innerHTML="";
        }
        if(ips[2].value != ips[3].value){
            err[3].innerHTML="两次密码不一致";
            return false;
        }else{
            err[3].innerHTML="";
        }
        if(!reg2.test(ips[4].value)){
            err[4].innerHTML = "格式不正确";
            return false;
        }else{
            err[4].innerHTML = "";
        }
        return true;
    }
</script>
<form action="UserServlet?action=addUser" method="post" onsubmit="return ff()">
    <table>
        <tr>
            <td>id:</td>
            <td><input type="text" name="id" ></td>
            <td class="err"></td>
        </tr>
        <tr>
            <td>用户名:</td>
            <td><input type="text" name="uname" placeholder="6-20个字母" onchange="check(this)"></td>
            <td class="err"></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input type="password" name="upass"></td>
            <td class="err"></td>
        </tr>
        <tr>
            <td>确认密码:</td>
            <td><input type="password" name="reusrpass"></td>
            <td class="err"></td>
        </tr>
        <tr>
            <td>邮箱:</td>
            <td><input type="text" name="uemail"></td>
            <td class="err"></td>
        <tr>
            <td>性别:</td>
            <td><input type="radio" name="ugender" value="male">男
                <input type="radio" name="ugender" value="female">女
                </td>
            <td class="err"></td>
        </tr>
        <tr>
            <td colspan="3"><input type="submit" value="注册"></td>
        </tr>
    </table>
</form>
</body>
</html>
