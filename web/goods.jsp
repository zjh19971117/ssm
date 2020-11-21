<%--
  Created by IntelliJ IDEA.
  User: jia'hao
  Date: 2020/11/2
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="static/bootstrap/css/bootstrap.min.css"/>
    <style>
        .pic{
            width: 100px;
            height: 80px;
        }
    </style>
</head>
<body id="bb">
<script type="text/javascript" src="static/bootstrap/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="static/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(function () {
        $(".catalog1").each(function (index) {
            var opt = $(".catalog1:eq(" + index + ") option:selected");
            $.ajax({
                url: "CatalogServlet",
                data: {action: 'load', id: opt.val()},
                dataType: "json",
                type: "get",
                success: function (res) {
                    for (var i in res) {
                        if (parseInt(opt.attr("name")) != res[i].cid) {
                            $(".catalog2:eq(" + index + ")").append("<option>" + res[i].cname + "</option>");
                        } else {
                            $(".catalog2:eq(" + index + ")").append("<option selected>" + res[i].cname + "</option>");
                        }
                    }
                }
            });
            $(this).change(function () {
                var xb = this.selectedIndex;
                var opt = $(".catalog1:eq(" + index + ") option:eq(" + xb + ")");
                $.ajax({
                    url: 'GoodsServlet',
                    data: {action: 'updateC1', gid: opt.attr("class"), c1: opt.val()},//将二级类别的内容再作修改
                    type: 'get',
                    dataType: 'json',
                    success: function (data) {//返回的数值是当前一级类别所对应的所有二级分类 [{},{}]s
                        $(".catalog2:eq(" + index + ")").find("option").remove();
                        for (var i in data) {
                            //data[i].cid  data[i].cname
                            $(".catalog2:eq(" + index + ")").append("<option>" + data[i].cname + "</option>");
                        }
                    }
                });
            });
        });
        $(".catalog2").each(function (index) {
            $(this).change(function () {
                var xb = this.selectedIndex;//被选中的option中类别的下标
                var opt = $(".catalog2:eq(" + index + ") option:eq(" + xb + ")");//option中被选中的名称
                var opt1 = $(".catalog1:eq(" + index + ") option:selected");
                $.ajax({
                    url: 'GoodsServlet',
                    data: {action:'updateC2',name: opt.val(),gid: opt1.attr("class")},
                    dataType: 'json',
                    type: 'get',
                    success: function () {

                    }
                });
            });
        });
    })
    function updateGoods(gid){
        $("#myModal").modal();
        $.ajax({
            url:'GoodsServlet',
            data:{action:'select1',gid:gid},
            type:'get',
            dataType:'json',
            success: function (goods){
                $("#goodsid").val(goods.gid);
                $("#goodsname").val(goods.gname);
                $("#goodsprice").val(goods.price);
                $("#info").val(goods.info);

                $("#g_photo").attr("src", goods.photo);
                $("#g_pic1").attr("src", goods.pic1);
                $("#g_pic2").attr("src", goods.pic2);
                $("#g_pic3").attr("src", goods.pic3);
                $("#g_pic4").attr("src", goods.pic4);
            }
        })
    }
    function addGoods(){
        $("#myModal").modal();
    }
function deleteGoods(gid){
        $.ajax({
            url:"GoodsServlet",
            data:{action:'delete',gid:gid},
            type:"get",
            dataType:"json",
            success: function (data){
                if(data==1){
                    // location.href='GoodsServlet?action=selectGoodsAll';
                    $("#bb").load("GoodsServlet?action=selectGoodsAll");

                }
            }
        })
}

</script>
<table class="table table-bordered">
  <tr>
      <th>商品id</th>
      <th>商品名称</th>
      <th>商品价格</th>
      <th>商品图片</th>
      <th>小图1</th>
      <th>小图2</th>
      <th>小图3</th>
      <th>小图4</th>
      <th>备注信息</th>
      <th>一级分类</th>
      <th>二级分类</th>
      <th>操作</th>
  </tr>
    <c:forEach var="goods" items="${glist}">
        <tr>
            <td>${goods.gid}</td>
            <td>${goods.gname}</td>
            <td>${goods.price}</td>
            <td><img class="pic" src="${goods.photo}"></td>
            <td><img class="pic" src="${goods.pic1}"></td>
            <td><img class="pic" src="${goods.pic2}"></td>
            <td><img class="pic" src="${goods.pic3}"></td>
            <td><img class="pic" src="${goods.pic4}"></td>
            <td>${goods.info}</td>
           <td>
               <select class="catalog1">
                   <c:forEach items="${clist}" var="catalog">
                       <option value="${catalog.cid}" name="${goods.c2}"  class="${goods.gid}" <c:if test="${catalog.cid==goods.c1}">selected</c:if>>${catalog.cname}</option>
                   </c:forEach>
               </select>
           </td>
            <td>
               <select class="catalog2">

               </select>
           </td>
            <td><span style="cursor:pointer;" class="update" onclick="updateGoods(${goods.gid})">修改</span>&nbsp;<span style="cursor:pointer;" class="delete" onclick="deleteGoods(${goods.gid})">删除</span></td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="12">
            <span style="cursor:pointer;" class="add" onclick="addGoods()">添加</span>
        </td>
    </tr>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">修改商品</h4>
            </div>
            <div class="modal-body">
                <form action="GoodsServlet" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="action" value="updateGoods">
                    <div class="form-group">
                        <label for="goodsid">商品编号</label>
                        <input type="text" name="gid" class="form-control" id="goodsid" placeholder="id" value="${goods.gid}">
                    </div>
                    <div class="form-group">
                        <label for="goodsname">商品名称</label>
                        <input type="text" name="gname" class="form-control" id="goodsname"
                               placeholder="name">
                    </div>

                    <div class="form-group">
                        <label for="goodsprice">商品价格</label>
                        <input type="text" name="price" class="form-control" id="goodsprice" placeholder="price">
                    </div>
                    <div class="form-group">
                        <label for="goodsphoto">商品主图</label>
                        <img id="g_photo" src="" height="50px" width="50px">
                        <input type="file" name="photo" class="form-control" id="goodsphoto"
                               placeholder="Passphoto">
                    </div>

                    <div class="form-group">
                        <label for="goodspic2">商品副图1</label>
                        <img id="g_pic1" src="" height="50px" width="50px">
                        <input type="file" name="pic1" class="form-control" id="goodspic1" placeholder="pic2">
                    </div>
                    <div class="form-group">
                        <label for="goodspic2">商品副图2</label>
                        <img id="g_pic2" src="" height="50px" width="50px">
                        <input type="file" name="pic2" class="form-control" id="goodspic2" placeholder="pic2">
                    </div>

                    <div class="form-group">
                        <label for="goodspic2">商品副图3</label>
                        <img id="g_pic3" src="" height="50px" width="50px">
                        <input type="file" name="pic3" class="form-control" id="goodspic3" placeholder="pic2">
                    </div>
                    <div class="form-group">
                        <label for="goodspic2">商品副图4</label>
                        <img id="g_pic4" src="" height="50px" width="50px">
                        <input type="file" name="pic4" class="form-control" id="goodspic4" placeholder="pic2">
                    </div>
                    <div class="form-group">
                        <label for="info">备注</label>
                        <input type="text" name="info" class="form-control" id="info"
                               placeholder="info">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>
</table>
</body>
</html>
