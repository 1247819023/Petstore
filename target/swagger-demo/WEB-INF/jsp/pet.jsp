<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 9470
  Date: 2018/11/4
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>宠物列表</title>
    <style>
        table.gridtable {
            font-family: verdana, arial, sans-serif;
            font-size: 11px;
            color: #333333;
            border-width: 1px;
            border-color: #666666;
            border-collapse: collapse;
        }

        table.gridtable th {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #666666;
            background-color: #dedede;
        }

        table.gridtable td {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #666666;
            background-color: #ffffff;
        }
    </style>
</head>
<body>
<form style="margin: auto" action="/pet/add" method="post">
    <select name="cid">
        <option value=""> 请选择</option>
        <c:forEach items="${categories}" var="cate">
            <option value="${cate.cid}">
                    ${cate.cname}
            </option>
        </c:forEach>
    </select>
    <input type="text" name="pname" placeholder="名字"/>
    <input type="text" name="photo_urls" placeholder="图片"/>
    <select name="tid">
        <option value=""> 请选择</option>
        <c:forEach items="${tags}" var="tag">
            <option value="${tag.tid}">
                    ${tag.tname}
            </option>
        </c:forEach>
    </select>
    <select name="pstatus">
        <option value=""> 请选择</option>
        <c:forEach items="${pets}" var="pet">
            <option value="${pet.pid}">
                    ${pet.pstatus}
            </option>
        </c:forEach>
    </select>
    <input type="submit"/>
</form>


<form action="/pet/findByStatus" method="get">
    <select name="pstatus" id="pstatus">
        <option value=""> 请选择</option>
        <c:forEach items="${pets}" var="pet">
            <option value="${pet.pid}">
                    ${pet.pstatus}
            </option>
        </c:forEach>
    </select>
    <input type="submit" id="result"/>
</form>


<table class="gridtable" id="resultStatus">
    <thead>
    <th>宠物编号</th>
    <th>类型</th>
    <th>名字</th>
    <th>图片</th>
    <th>描述</th>
    <th>状态</th>
    <th>操作</th>
    </thead>
    <tbody>
    <c:forEach items="${pets}" var="pet">
        <tr>
            <td>${pet.pid}</td>
            <td>${pet.cid}</td>
            <td>${pet.pname}</td>
            <td>${pet.photo_urls}</td>
            <td>${pet.tid}</td>
            <td>${pet.pstatus}</td>
            <td><a href="/pet/del/${pet.pid}">删除</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script>
    $(function () {
        $("#result").click(function () {
            $.ajax({
                type: "get",
                url: "/pet/findByStatus" + $("#pstatus").val(),
                dataType: "json",
                success: function (data) {
                    $("#resultStatus").append(
                        $(
                            "<tr>" + data.pid + "</tr>" +
                            "<tr>" + data.cid + "</tr>" +
                            "<tr>" + data.pname + "</tr>" +
                            "<tr>" + data.photo_urls + "</tr>" +
                            "<tr>" + data.tid + "</tr>" +
                            "<tr>" + data.pstatus + "</tr>"
                        )
                    )
                }
            });
        });
    });
</script>
</body>
</html>
