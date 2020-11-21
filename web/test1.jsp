<%--
  Created by IntelliJ IDEA.
  User: jia'hao
  Date: 2020/10/20
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        #dd{width: 400px; height: 300px; border: solid 5px coral}
    </style>
</head>
<body>
<div id="dd" onmouseover="show3()"></div>
<button onclick="show()">插入</button>
<button onclick="show2()">清空</button>
    <script type="text/javascript">
        function show(){
            var dd = document.getElementById("dd");
            dd.innerHTML="你好";
            alert("你点击了它");
            // // confirm("煞笔");
            // prompt("你爱学习吗?","爱");
        }
        function show2(){
            var dd = document.getElementById("dd")
            dd.innerHTML=""
            alert("你点击了他")
        }
        var c = 400;
        function show3(){
        var tag = document.getElementById("dd");
        c += 50;
        tag.style.height= c + "px";
        tag.style.width= c + "px";
        }
    </script>
</body>
</html>
