<%@ page import="com.service.IDepService" %>
<%@ page import="com.serviceImpl.DepServiceImpl" %><%--
  Created by IntelliJ IDEA.
  User: sheli
  Date: 2020/2/8
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%
        IDepService fbiz=new DepServiceImpl();
    %>

</head>
<body>
<form action="add_Food.do" method="post">
    <table border="0" width="600" height="400" align="center">
        <tr align="center" bgcolor=" #FFE4B5">
            <td colspan="2">添加</td>
        </tr>
        <tr>
            <td>城市</td>
            <td>
                <input type="text" name="f_city" id="f_city">
            </td>
        </tr>
        <tr>
            <td>店铺名</td>
            <td>
                <input type="text" name="f_shopname" id="f_shopname">
            </td>
        </tr>
        <tr>
            <td>招牌菜</td>
            <td>
                <input type="text" name="f_special" id="f_special">
            </td>
        </tr>
        <tr>
            <td>地址</td>
            <td>
                <input type="text" name="f_address" id="f_address">
            </td>
        </tr>
        <tr align="center" bgcolor=" #FFE4B5">
            <td colspan="2">
                <!--  <input type="hidden" id="op" name="op" value="1" > -->
                <input type="submit" value="提交">
                <input type="reset" value="重置">
            </td>
        </tr>
    </table>
</form>
</body>
</html>