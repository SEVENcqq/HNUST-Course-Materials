<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>首页</title>
    <base href="http://localhost:8088/chapter13/"/>
</head>
<body>
<h1><%= "chapter13 首页" %>
</h1><br/>
<a href="showNullPointer">抛空指针异常</a><br/>
<a href="showIOException">抛IO异常</a><br/>
<a href="showArithmetic">抛算术异常</a><br/>
<a href="showRunTime">网络异常(实质是运行时异常)</a><br/>
<hr/>
<a href="addData">自定义异常处理器--新增数据异常&异常处理注解</a><br/>
<hr/>
这里测试拦截器的执行流程以及多个拦截器的执行流程：<br/>
<a href="interceptorTest">拦截器执行流程测试1</a><br/>
<a href="interceptorTest2">拦截器执行流程测试2</a><br/>
<hr/>
后台系统登录验证：<br/>
<a href="login.jsp">登录入口</a><br/>
</body>
</html>