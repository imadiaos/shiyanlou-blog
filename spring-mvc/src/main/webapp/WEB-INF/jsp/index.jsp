<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // 获取 session
    String username = (String) session.getAttribute("username");
%>
<html>
<body>
<h1>
    <%
        if (username == null) {
            out.println("Hello 游客");
            out.println("<a href='/register'>注册</a> <a href='/login'>登入</a>");
        } else {
            out.println("Hello " + username);
            out.println("<a href='/logout'>退出</a>");
        }
    %>
</h1>
</body>
</html>