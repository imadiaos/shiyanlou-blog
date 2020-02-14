<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>注册</title>
    <script src="jquery.min.js"></script>
</head>
<body>
<h1>用户注册</h1>
<form id="form" method="post">
    <p>
        用户名：<input name="username" type="text">
    </p>
    <p>
        密码：<input name="password1" type="password">
    </p>
    <p>
        再次输入密码：<input name="password2", type="password">
    </p>
    <p>
        <input id="submit" type="button" value="提交">
    </p>
</form>
<script>
    $(document).ready(function () {
        $('#submit').click(function(){
            $.post('/do_register', $('#form').serialize(), function (res) {
                alert(res);
                if (res === '注册成功') {
                    window.location.href='login'
                }
            })
        })
    })
</script>
</body>
</html>