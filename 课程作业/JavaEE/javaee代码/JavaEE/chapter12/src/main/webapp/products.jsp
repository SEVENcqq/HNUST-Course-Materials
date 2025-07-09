<%--
  Created by IntelliJ IDEA.
  User: cqq
  Date: 2023/3/27
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2023/3/27
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>products</title>
  </head>
  <body>
    <form action="${pageContext.request.contextPath}/getProducts" method="post">
      <table width="220px" border="1">
        <tr>
          <td>选择</td>
          <td>商品名称</td>
        </tr>
        <tr>
          <td><input name="proIds" value="1" type="checkbox"/></td>
          <td>斯蒂卡</td>
        </tr>
        <tr>
          <td><input name="proIds" value="2" type="checkbox"/></td>
          <td>蝴蝶</td>
        </tr>
        <tr>
          <td><input name="proIds" value="3" type="checkbox"/></td>
          <td>红双喜</td>
        </tr>
      </table>
      <input type="submit" value="提交商品"/>
    </form>
  </body>
  </html>

