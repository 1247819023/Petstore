<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>

<label for="userName">用户名:</label><input type="text" id = userName name="userName"><br>
<label for="userPassword">密码:</label> <input type="password" id="userPassword" name="userPassword"><br>
<button name="login" id="login">登录</button>

<script src="/js/jquery-3.3.1.min.js"></script>
<script>
    $("#login").click(function () {

        $.ajax({
            url: "/user/login",
            type: "get",
            data: {"user_name":$("#userName").val(), "password":$("#userPassword").val()},
            success: function (data) {
                if(data.msg == "success"){
                    alert("登录成功");
                    window.location.href = "/user/success";
                }else{
                    alert("密码或用户名错误");
                }
            }
        });

    })
</script>
</body>
</html>
