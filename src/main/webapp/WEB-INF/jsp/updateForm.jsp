<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户修改</title>
</head>
<body>
<label for="userId">用户编号:</label><input type="text" readonly name="userId" id="userId" value="${userId}"><br>
<label for="userName">用户名　:</label><input type="text" name="userName" id="userName"><br>
<label for="userLastName">姓　　氏:</label><input type="text" name="userLastName" id="userLastName"><br>
<label for="userFirstName">名　　字:</label><input type="text" name="userFirstName" id="userFirstName"><br>
<label for="userEmail">邮　　箱:</label><input type="text" name="userEmail" id="userEmail"><br>
<label for="userPassword">密　　码:</label><input type="text" name="userPassword" id="userPassword"><br>
<label for="userPhone">联系电话:</label><input type="text" name="userPhone" id="userPhone"><br>
用户状态:<input type="radio" name="userStatus" id="available" value="0">可用&nbsp;
<input type="radio" name="userStatus" id="unavailable" value="1">不可用
<input type="button" id="updateBtn" value="确认修改">

<script src="/js/jquery-3.3.1.min.js"></script>
<script>
    var list = function () {
        $.ajax({
            url: "/user/finByIdUser",
            type: "get",
            data: {"userId": $("#userId").val()},
            success: function (data) {
                console.log(data)
                $("#userId").val(data.uid)
                $("#userName").val(data.user_name)
                $("#userFirstName").val(data.first_name)
                $("#userLastName").val(data.last_name)
                $("#userEmail").val(data.email)
                $("#userPassword").val(data.password)
                $("#userPhone").val(data.phone)
                if (data.user_status == 0)
                    $("#available").prop("checked", "true");
                else
                    $("#unavailable").prop("checked", "true");
            }
        })
        $("#updateBtn").click(function () {
            if (window.confirm("是否确认修改？")) {
                $.ajax({
                    url: "/user/updateUser",
                    type: "post",
                    data: {
                        "uid": $("#userId").val(),
                        "user_name": $("#userName").val(),
                        "first_name": $("#userFirstName").val(),
                        "last_name": $("#userLastName").val(),
                        "email": $("#userEmail").val(),
                        "password": $("#userPassword").val(),
                        "phone": $("#userPhone").val(),
                        "user_status": $("input[name=userStatus]:checked").prop("value")
                    },
                    success: function (data) {
                        alert(data.msg)
                        window.location.href = "/user/success";
                    }
                })
            } else {
                return false;
            }
        })
    }

    list();
</script>
</body>
</html>
