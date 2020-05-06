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
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link rel="shortcut icon" href="/img/logo/black_128.png" type="image/x-icon"/>
    <link rel="icon" href="/img/logo/black_64.png" type="image/x-icon"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/defaultpart.css" type="text/css">
    <link rel="stylesheet" href="/css/backstage.css" type="text/css">
    <link rel="stylesheet" href="/plugin/jquery-confirm/jquery-confirm.min.css" type="text/css"/>
    <script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="/plugin/jquery-confirm/jquery-confirm.min.js"></script>

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
                    <span><a href="/profile">${sessionScope.user.userNickName}</a></span>
                </div>
            </div>
            <ul class="side-list">
                <li class="side-list-meta">
                    <a class="side-cat">文章</a>
                    <dl class="side-list-child">
                        <dd><a class="menu-link" href="/admin/article/getAllArticle">全部文章</a></dd>
                        <dd><a class="menu-link" href="/admin/article/write">攥写文章</a></dd>
                        <dd><a class="menu-link" href="/admin/article/tags">Tag管理</a></dd>
                    </dl>
                </li>
                <li class="side-list-meta">
                    <a class="side-cat">评论</a>
                    <dl class="side-list-child">
                        <dd><a class="menu-link" href="/admin/comment/getAllComment">全部评论</a></dd>
                        <dd><a class="menu-link" href="/admin/comment/commentManager">评论管理</a></dd>
                    </dl>
                </li>
                <li class="side-list-meta">
                    <a class="side-cat">用户</a>
                    <dl class="side-list-child">
                        <dd><a class="menu-link" href="/admin/user/list">全部用户</a></dd>
                        <dd><a class="menu-link" href="/admin/user/userManager">用户管理</a></dd>
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
</html>
