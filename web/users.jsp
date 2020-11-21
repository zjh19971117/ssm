<%--
  Created by IntelliJ IDEA.
  User: jia'hao
  Date: 2020/10/16
  Time: 19:27
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
<form action="UserServlet?action=selectAll" method="post">
    <input type="text" name="search" value="${search}"/><input type="submit" value="搜索">
</form>
    <table class="table table-striped">
        <tr>
            <th>uid</th>
            <th>uname</th>
            <th>uemail</th>
            <th>ugender</th>
            <th>编辑</th>
        </tr>
        <c:forEach items="${list}" var="user">
            <tr>
                <th>${user.uid}</th>
                <th>${user.uname}</th>
                <th>${user.ugender}</th>
                <th>${user.uemail}</th>
                <th><a href="UserServlet?action=selectOne&id=${user.uid}">update</a>&nbsp;&nbsp;<a
                        href="UserServlet?action=delete&id=${user.uid}" onclick="return delUser()" >delete</a></th>
            </tr>
        </c:forEach>
        <tr>
            <th colspan="5">
                <a href="UserServlet?action=selectAll&page=1&search=${search}">首页</a>
                <c:if test="${pb.page>1}">
                    <a href="UserServlet?action=selectAll&page=${pb.page-1}&search=${search}">上一页</a>
                </c:if>
                <c:if test="${pb.page==1}">
                    <a>上一页</a>
                </c:if>
                <c:if test="${pb.page < pb.pageNum}">
                    <a href="UserServlet?action=selectAll&page=${pb.page+1}&search=${search}">下一页</a>
                </c:if>
                <c:if test="${pb.page==pb.pageNum}">
                    <a>下一页</a>
                </c:if>
                <a href="UserServlet?action=selectAll&page=${pb.pageNum}&search=${search}">尾页</a>
            </th>
        </tr>
        <tr>
            <th colspan="5">
                当前${pb.page}页/共${pb.pageNum}页(共${pb.rowsNum}条)
            </th>
        </tr>
        <%--  共三页, 当前第2页
        1.总页码<=3(总页码): 1 到 当前页;
        总页码>3
        2.当前页是最后一页:当前页-2 到 当前页
        3.当前页是第一页: 当前页 到 当前页+2
        4.剩余情况:当前-1 到 当前页+1

  --%>
        <tr>
            <th colspan="5" align="center">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <li>
                             <a href="#" aria-label="Previous">
                                 <span aria-hidden="true">&laquo;</span></a>
                        </li>
                <c:choose>
                    <c:when test="${pb.pageNum<=3}">
                        <c:forEach begin="1" step="1" end="${pb.pageNum}" var="p">
                            <c:if test="${p!=pb.page}">
                               <li><a href="UserServlet?action=selectAll?page=${p}&search=${search}">${p}</a></li>
                            </c:if>
                            <c:if test="${p==pb.page}">
                            <li><a href="">${p}</a></li>
                            </c:if>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${pb.page==pb.pageNum}">
                                <c:forEach begin="${pb.page-2}" step="1" end="${pb.page}" var="p">
                                    <c:if test="${p!=pb.page}">
                                       <li><a href="UserServlet?action=selectAll&page=${p}&search=${search}">${p}</a>  </li>
                                    </c:if>
                                    <c:if test="${p==pb.page}">
                                        <li><a href="">${p}</a></li>
                                    </c:if>
                                </c:forEach>
                            </c:when>
                            <c:when test="${pb.page==1}">
                                <c:forEach begin="${pb.page}" step="1" end="${pb.page+2}" var="p">
                                    <c:if test="${p!=pb.page}">
                                       <li> <a href="UserServlet?action=selectAll&page=${p}&search=${search}">${p}</a> </li>
                                    </c:if>
                                    <c:if test="${p==pb.page}">
                                       <li><a href="">${p}</a> </li>
                                    </c:if>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:forEach begin="${pb.page-1}" step="1" end="${pb.page+1}" var="p">
                                    <c:if test="${p!=pb.page}">
                                       <li><a href="UserServlet?action=selectAll&page=${p}&search=${search}">${p}</a></li>
                                    </c:if>
                                    <c:if test="${p==pb.page}">
                                        <li><a href="">${p}</a></li>
                                    </c:if>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
                        <li>
                        <a href="#" aria-label="Next">
                         <span aria-hidden="true">&raquo;</span>
                       </a>
                  </li>
                </ul>
             </nav>
            </th>
        </tr>
<%--        <tr colspan="5">--%>
<%--            <nav aria-label="Page navigation">--%>
<%--                <ul class="pagination">--%>
<%--                    <li>--%>
<%--                        <a href="UserServlet?action=selectA" aria-label="Previous">--%>
<%--                            <span aria-hidden="true">&laquo;</span>--%>
<%--                        </a>--%>
<%--                    </li>--%>
<%--                    <li><a href="#">1</a></li>--%>
<%--                    <li><a href="#">2</a></li>--%>
<%--                    <li><a href="#">3</a></li>--%>
<%--                    <li>--%>
<%--                        <a href="#" aria-label="Next">--%>
<%--                            <span aria-hidden="true">&raquo;</span>--%>
<%--                        </a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </nav>--%>
<%--        </tr>--%>
    </table>
<script type="text/javascript">
    function delUser(){
        if(confirm("确定要删除吗?")){
            return true;
        }
        return false;
    }
</script>
</body>
</html>


