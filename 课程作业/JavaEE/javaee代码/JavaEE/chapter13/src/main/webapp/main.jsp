<%--
  Created by IntelliJ IDEA.
  User: cqq
  Date: 2023/3/30
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统主页</title>
</head>
<body>
    <li>您好：${user.username} </li>
    <li><a href="${pageContext.request.contextPath}/logout">退出</a></li>
    <li><a href="${pageContext.request.contextPath}/orderInfo">订单信息</a></li>
</body>
</html>
