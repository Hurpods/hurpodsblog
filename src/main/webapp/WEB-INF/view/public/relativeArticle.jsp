<%--
  Created by IntelliJ IDEA.
  User: Hurpods
  Date: 2020/5/10
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${tag.tagName}</title>
    <link rel="shortcut icon" href="/img/logo/black_128.png" type="image/x-icon"/>
    <link rel="icon" href="/img/logo/black_64.png" type="image/x-icon"/>
    <link rel="stylesheet" href="/css/defaultpart.css" type="text/css"/>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8"/>
    <meta name="description" content="Hurpods的个人Blog">
    <meta name="keywords" content="摸鱼，Java，数据库，Spring">
</head>
<body>
<%@ include file="../public/header.jsp" %>
<div style="margin: 110px auto;width: 55%;position: relative;">
    <span>贴上<b>${tag.tagName}</b>标签的文章共有<b>${number}</b>篇：</span>
    <div class="article-list">
        <ul style="list-style: none;padding: 0;">
            <c:if test="${articleList!=null}">
                <c:forEach items="${articleList}" var="article">
                    <li style="padding: 15px 15px 15px 35px">
                        <a href="/article/${article.articleId}">${article.articleTitle}</a>
                    </li>
                </c:forEach>
            </c:if>
        </ul>
    </div>
</div>
<%@ include file="../public/footer.jsp" %>
</body>
</html>
