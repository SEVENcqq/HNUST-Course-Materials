<%--
  Created by IntelliJ IDEA.
  User: cqq
  Date: 2023/3/29
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>product_add</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.3.js"></script>
</head>
<body>
    <table id="products" border="1" width="60%">
        <tr align="center"><td>欢迎您：</td><td id="username"></td></tr>
        <tr align="center">
            <td colspan="2" align="center">
                <input type="button" value="添加多个商品" onclick="addProducts()"/>
            </td>
        </tr>
        <tr align="center"><td>商品id</td><td>商品名称</td></tr>
    </table>
<script type="text/javascript">
    // 显示当前用户名
    window.onload=function (){
        var url = "${pageContext.request.contextPath}/getUser";
        $.get(url,function (response){
            $("#username").text(response.username);
        })
    }
    // 添加商品
    function addProducts(){
        var url = "${pageContext.request.contextPath}/addProducts";
        $.get(url,function (products){
            // 将处理器返回的商品列表信息添加到表格中
            for (var i=0; i<products.length; i++){
                $("#products").append("<tr><td align='center'>" + products[i].proId + "</td><td align='center'>" + products[i].proName + "</td></tr>");
            }
        })
    }
</script>
</body>
</html>
