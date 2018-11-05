<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
</head>
<body>
<h1>用户中心</h1>
<a href="/user/logout">注销</a>
<a href="/pet">宠物商城</a><br><br>
<a href="/user/insertForm">添加用户</a><br><br>

<input type="text" name="selectName" id="selectName" style="width:300px;margin-bottom: 5px">&nbsp;
<input type="button" value="搜索" id="selectBtn">
<table id="userList" border="1" cellspacing="0" style="width:80%;text-align: center;">
    <tr>
        <th>编号</th>
        <th>用户名</th>
        <th>姓氏</th>
        <th>名字</th>
        <th>邮箱</th>
        <th>密码</th>
        <th>电话</th>
        <th>状态</th>
        <th>操作</th>
    </tr>

    <tbody id="tbody"></tbody>
</table>

<script src="/js/jquery-3.3.1.min.js"></script>
<script>
    var list = function () {
        $.ajax({
            url: "/user/findAllUser",
            type: "get",
            success: function (data) {
                var tbody = $("#tbody");
                $.each(data, function (index, obj) {
                    if (obj.user_status == 0)
                        tbody.append("<tr><td>" + obj.uid + "</td><td>" + obj.user_name + "</td><td>" + obj.last_name + "</td><td>" + obj.first_name + "</td><td>" + obj.email + "</td><td>" + obj.password + "</td><td>" + obj.phone + "</td><td>可用</td><td><a href='/user/updateForm?userId=" + obj.uid + "'>修改</a>&nbsp;<a href='#' value= '" + obj.uid + "' class='delUser'>删除</a></td></tr>")
                    else
                        tbody.append("<tr><td>" + obj.uid + "</td><td>" + obj.user_name + "</td><td>" + obj.last_name + "</td><td>" + obj.first_name + "</td><td>" + obj.email + "</td><td>" + obj.password + "</td><td>" + obj.phone + "</td><td>不可用</td><td><a href='/user/updateForm?userId=" + obj.uid + "'>修改</a>&nbsp;<a href='#' value= '" + obj.uid + "' class='delUser'>删除</a></td></tr>")
                })
            }
        })
    }
    list();
    $("#tbody").on("click", ".delUser", function () {
        if (window.confirm("是否确认删除？")) {
            $.ajax({
                url: "/user/deleteUser",
                type: "get",
                data: {"userId": $(this).attr("value")},
                success: function (data) {
                    alert(data.msg)
                    window.location.href = "/user/success";
                }
            })
        } else {
            return false;
        }
    })
    $("#selectBtn").click(function () {
        $.ajax({
            url: "/user/findByUserName",
            type: "get",
            data: {"user_name": $("#selectName").val()},
            success: function (data) {
                var tbody = $("#tbody");
                $("#tbody tr").remove()
                $.each(data, function (index, obj) {
                    if (obj.user_status == 0)
                        tbody.append("<tr><td>" + obj.uid + "</td><td>" + obj.user_name + "</td><td>" + obj.last_name + "</td><td>" + obj.first_name + "</td><td>" + obj.email + "</td><td>" + obj.password + "</td><td>" + obj.phone + "</td><td>可用</td><td><a href='/user/updateForm?userId=" + obj.uid + "'>修改</a>&nbsp;<a href='#' value= '" + obj.uid + "' class='delUser'>删除</a></td></tr>")
                    else
                        tbody.append("<tr><td>" + obj.uid + "</td><td>" + obj.user_name + "</td><td>" + obj.last_name + "</td><td>" + obj.first_name + "</td><td>" + obj.email + "</td><td>" + obj.password + "</td><td>" + obj.phone + "</td><td>不可用</td><td><a href='/user/updateForm?userId=" + obj.uid + "'>修改</a>&nbsp;<a href='#' value= '" + obj.uid + "' class='delUser'>删除</a></td></tr>")
                })
            }
        })
    })
</script>
</body>
</html>
