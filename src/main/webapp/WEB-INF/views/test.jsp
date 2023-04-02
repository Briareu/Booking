<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<title>首页</title>
<style type="text/css">
    #customers {
        text-align: center;
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        width: 100%;
    }

    #customers th {
        height: 50px;
        font-size: 1.1em;
        text-align: center;
        padding-top: 5px;
        padding-bottom: 4px;
        background-color: #A7C942;
        color: #ffffff;
    }

    #customers td {
        height: 50px;
    }
</style>
</head>
<body>
<div style="width: 100%;text-align: center">
    <h1>用户查询查询</h1>
</div>
<table id="customers">
    <tr>
        <th>用户账户</th>
        <th>用户电话</th>
        <th>用户性别</th>
        <th>创建时间</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${userList}" var="userList" varStatus="i">
            <tr style="background-color: powderblue">
                <td>${userList.userAccount}</td>
                <td>${userList.userPhone}</td>
                <c:choose>
                    <c:when test="${userList.userGender == 1}">
                        <td>男</td>
                    </c:when>
                    <c:when test="${userList.userGender == 0}">
                        <td>女</td>
                    </c:when>
                </c:choose>
                <td>${userList.userCreateTime}</td>
                <td><a href="#">查询</a></td>
            </tr>
    </c:forEach>
</table>

</body>
</html>

