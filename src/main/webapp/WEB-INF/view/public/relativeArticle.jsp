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
    <link rel="stylesheet" href="/css/home.css" type="text/css"/>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8"/>
    <meta name="description" content="Hurpods的个人Blog">
    <meta name="keywords" content="摸鱼，Java，数据库，Spring">
</head>
<body>
<%@ include file="../public/header.jsp" %>
<div style="margin: 110px auto;width: 55%;position: relative;">
    <span>贴上<b>${tag.tagName}</b>标签的文章共有<b>${number}</b>篇：</span>
    <div style="padding-top: 65px;">
        <c:if test="${articleList.size()!=0}">
            <c:forEach items="${articleList}" var="article">
                <c:if test="${article.hasPic==0}">
                    <article class="article-style1">
                        <div class="thumb">
                            <img alt="tag" width="160px"
                                 <c:if test="${article.isError==1}">src="/img/tag/error.png"</c:if>
                                 <c:if test="${article.isError==0}">src="/img/tag/code.png"</c:if>
                            />
                        </div>
                        <div class="content">
                            <a href="/article/${article.articleId}" class="article-title">${article.articleTitle}</a>
                            <div class="summary">
                                    ${article.articleSummary}
                            </div>
                        </div>
                        <div class="tags">
                            <ul class="tagList">
                                <c:forEach items="${article.tagList}" var="tag">
                                    <li>
                                        <img src="/img/tag/tag.png" alt="tag" style="vertical-align: middle"/>
                                        <a class="tag-link" href="/article/tags/${tag.tagId}">#${tag.tagName}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                            <div class="time">
                                最后修改时间：<fmt:formatDate value='${article.articleUpdateTime}' pattern='yyyy-MM-dd'/>
                            </div>
                        </div>
                    </article>
                </c:if>
                <c:if test="${article.hasPic==1}">
                    <article class="article-style2">
                        <img src="${article.firstPicUrl}" alt="首图"
                             style="position: absolute;width: 99.6%;height: 500px;border-radius: 15px;object-fit: cover;filter: brightness(0.5) contrast(80%);"/>
                        <div class="content">
                            <a href="/article/${article.articleId}" class="article-title"
                               style="color: white">${article.articleTitle}</a>
                            <div class="summary">
                                    ${article.articleSummary}
                            </div>
                        </div>
                        <div class="tags">
                            <ul class="tagList">
                                <c:forEach items="${article.tagList}" var="tag">
                                    <li>
                                        <img src="/img/tag/tag.png" alt="tag" style="vertical-align: middle;"/>
                                        <a class="tag-link" href="/article/tags/${tag.tagId}"
                                           style="color: white">#${tag.tagName}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                            <div class="time">最后修改时间：<fmt:formatDate value='${article.articleUpdateTime}'
                                                                     pattern='yyyy-MM-dd'/></div>
                        </div>
                    </article>
                </c:if>
            </c:forEach>
        </c:if>
    </div>
</div>
<%@ include file="../public/footer.jsp" %>
</body>
</html>
