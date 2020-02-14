<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>登入</title>
    <script src="jquery.min.js"></script>
</head>
<body>
<h1>登入</h1>
<form id="form">
    <p>
        用户名：<input type="text" name="username">
    </p>
    <p>
        密码：<input type="password" name="password">
    </p>
    <p>
        <input id="submit" type="button" value="登入">
    </p>
</form>
<script>
    $(document).ready(function () {
        $('#submit').click(function(){
            $.post('/do_login', $('#form').serialize(), function (res) {
                alert(res);
                if (res === '登入成功') {
                    window.location.href='/';
                }
            })
        })
    })
</script>
</body>
</html>