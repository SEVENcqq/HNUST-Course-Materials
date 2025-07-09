<%--
  Created by IntelliJ IDEA.
  User: cqq
  Date: 2023/3/27
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
    <title>order_info</title>
</head>
<body>
  <form action="${pageContext.request.contextPath}/orderInfo" method="post">
    <table border="1">
      <tr>
        <td colspan="2">
          订单id：<input type="text" name="orderId" value="1">
        </td>
      </tr>
      <tr>
        <td>商品Id</td>
        <td>商品名称</td>
      </tr>
      <tr>
        <td>
          <input type="text" name="productInfo['生鲜'].proId" value="1">
        </td>
        <td>
          <input type="text" name="productInfo['生鲜'].proName" value="三文鱼">
        </td>
      </tr>
      <tr>
        <td>
          <input type="text" name="productInfo['酒水'].proId" value="2">
        </td>
        <td>
          <input type="text" name="productInfo['酒水'].proName" value="茅台">
        </td>
      </tr>
    </table>
    <input type="submit" value="提交">
  </form>
</body>
</html>
