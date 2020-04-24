<%--
  Created by IntelliJ IDEA.
  User: Hurpods
  Date: 2020/4/24
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<header style="margin-left:0">
    <div class="header">
        <div class="logo">
            <a href="/">
                <img src="/img/logo/grey_32.png">
            </a>
        </div>
        <div class="nav" id="nav">
            <a href="/" class="active">首页</a>
            <a href="developlog.jsp">开发日志</a>
        </div>
        <div class="header-right">
            <c:choose>
                <c:when test="${sessionScope.user==null}">
                    <a href="/loginPage" id="login">登陆</a>
                </c:when>
                <c:otherwise>
                    <div class="user-avatar">
                        <img src="${sessionScope.user.userAvatar}" id="user-avatar" width="32px"/>
                    </div>
                    <div class="user-operator">
                        <a href="/profile">个人信息</a>
                        <a href="/logout" id="logout">退出</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</header>

