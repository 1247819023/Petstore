<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户</title>
</head>
<body>
<label for="userName">用户名　:</label><input type="text" name="userName" id="userName"><br>
<label for="userLastName">姓　　氏:</label><input type="text" name="userLastName" id="userLastName"><br>
<label for="userFirstName">名　　字:</label><input type="text" name="userFirstName" id="userFirstName"><br>
<label for="userEmail">邮　　箱:</label><input type="text" name="userEmail" id="userEmail"><br>
<label for="userPassword">密　　码:</label><input type="text" name="userPassword" id="userPassword"><br>
<label for="userPhone">联系电话:</label><input type="text" name="userPhone" id="userPhone"><br>
用户状态:<input type="radio" name="userStatus" id="available" value="0" checked="true">可用&nbsp;
<input type="radio" name="userStatus" id="unavailable" value="1">不可用
<input type="button" id="insertBtn" value="确认添加">

<script src="/js/jquery-3.3.1.min.js"></script>
<script>
    $("#insertBtn").click(function () {
        if (window.confirm("是否确认添加？")) {
            $.ajax({
                url: "/user/createUser",
                type: "post",
                data: {
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
</script>
</body>
</html>
