<%--
  Created by IntelliJ IDEA.
  User: cqq
  Date: 2023/3/27
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>orders</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/showOrders" method="post">
        <table width="220px" border="1">
            <tr>
                <td>订单号</td>
                <td>订单名称</td>
                <td>配送地址</td>
            </tr>
            <tr>
                <td>
                    <input type="text" value="1" name="orders[0].orderId">
                </td>
                <td>
                    <input type="text" value="斯蒂卡" name="orders[0].orderName">
                </td>
                <td>
                    <input type="text" value="北京" name="address">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" value="2" name="orders[1].orderId">
                </td>
                <td>
                    <input type="text" value="蝴蝶" name="orders[1].orderName">
                </td>
                <td>
                    <input type="text" value="上海" name="address">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" value="3" name="orders[2].orderId">
                </td>
                <td>
                    <input type="text" value="红双喜" name="orders[2].orderName">
                </td>
                <td>
                    <input type="text" value="武汉" name="address">
                </td>
            </tr>
        </table>
        <input type="submit" value="订单信息">
    </form>

</body>
</html>
