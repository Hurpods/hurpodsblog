<%--
  Created by IntelliJ IDEA.
  User: Hurpods
  Date: 2020/4/28
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<html>
<head>
    <link rel="shortcut icon" href="/img/logo/black_128.png" type="image/x-icon"/>
    <link rel="icon" href="/img/logo/black_64.png" type="image/x-icon"/>
    <link rel="stylesheet" href="/css/defaultpart.css" type="text/css">
    <link rel="stylesheet" href="/css/backstage.css" type="text/css">
    <title>HurpodsBlog后台</title>
</head>
<body>
<main style="width: 100%">
    <div class="side-menu">
        <div id="contain">
            <div class="admin-info">
                <div class="admin-avatar">
                    <img src="${sessionScope.user.userAvatar}" alt="admin-avatar" style="border-radius: 50%">
                </div>
                <div class="admin-nickname">
                    <span>${sessionScope.user.userNickName}</span>
                </div>
            </div>
            <ul class="side-list">
                <li class="side-list-meta">
                    <a class="side-cat">文章</a>
                    <dl class="side-list-child">
                        <dd><a class="menu-link">全部文章</a></dd>
                        <dd><a class="menu-link" href="/admin/article/write">攥写文章</a></dd>
                        <dd><a class="menu-link">Tag管理</a></dd>
                        <dd><a class="menu-link">修改文章</a></dd>
                    </dl>
                </li>
                <li class="side-list-meta">
                    <a class="side-cat">评论</a>
                    <dl class="side-list-child">
                        <dd><a class="menu-link">全部评论</a></dd>
                        <dd><a class="menu-link">评论管理</a></dd>
                    </dl>
                </li>
                <li class="side-list-meta">
                    <a class="side-cat">用户</a>
                    <dl class="side-list-child">
                        <dd><a class="menu-link">全部用户</a></dd>
                        <dd><a class="menu-link">用户管理</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <div class="main-content">
        <rapid:block name="content">

        </rapid:block>
    </div>
</main>

<%@ include file="../public/footer.jsp" %>
<rapid:block name="script">

</rapid:block>
</body>
<script type="text/javascript" src="<c:url value="/js/jquery-3.2.1.js"/>"></script>
</html>
