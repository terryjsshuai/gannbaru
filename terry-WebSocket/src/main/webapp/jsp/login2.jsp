<%@ page language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>login2</title>
</head>
<body>
<fieldset>
    <legend>用户登录</legend>
    <br/>
    <div><%=request.getAttribute("loginInfo") == null ? "" : request.getAttribute("loginInfo")%>
    </div>
    <!-- form 表单的action 属性值要和配置在web.xml文件中的servlet的url-pattern相同 -->
    <form action="ws" method="post" name="login2">
        用户名：<input type="text" name="username"/> <br/> <br/>
        密&nbsp;码：<input type="password" name="password"/> <br/> <br/>
        <input type="submit" value="登录"/>
    </form>
</fieldset>
</body>
</html>