<%--
  Created by IntelliJ IDEA.
  User: cqq
  Date: 2023/3/30
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单信息</title>
</head>
<body>
您好：${user.username}
  <a href="${pageContext.request.contextPath}/logout">退出</a>
  <table border="1" width="80%">
    <tr align="center"><td colspan="2">订单id：Q1</td></tr>
    <tr align="center"><td>商品id</td><td>商品名称</td></tr>
    <tr align="center"><td>P001</td><td>斯蒂卡</td></tr>
    <tr align="center"><td>P002</td><td>蝴蝶</td></tr>
  </table>
</body>
</html>
