<%--
  Created by IntelliJ IDEA.
  User: cqq
  Date: 2023/3/27
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
    <title>order</title>
</head>

<body>
    <form action="${pageContext.request.contextPath}/findOrderWithUser" method="post">
        所属用户：<input type="text" name="username"/><br/>
        订单编号：<input type="text" name="order.orderId"/><br/>
        <input type="submit" value="查询">
    </form>
</body>
</html>
