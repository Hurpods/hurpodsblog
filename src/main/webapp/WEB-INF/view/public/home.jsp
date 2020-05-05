<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <link rel="shortcut icon" href="/img/logo/black_128.png" type="image/x-icon"/>
    <link rel="icon" href="/img/logo/black_64.png" type="image/x-icon"/>
    <link rel="stylesheet" href="/css/home.css" type="text/css"/>
    <link rel="stylesheet" href="/css/defaultpart.css" type="text/css">
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="description" content="Hurpods的个人Blog">
    <meta name="keywords" content="摸鱼，Java，数据库，Spring">
    <title>HurpodsBlog——成长的道路</title>
</head>
<body>
<%@ include file="../public/header.jsp" %>
<div class="inner">
    <div class="top-box">
        <div class="daily">
            <div class="daily-img">
                <img class="img-cover" id="daily-img" src="https://api.dujin.org/bing/1920.php" alt="每日一言图片">
            </div>
            <div class="daily-words" id="daily-words">

            </div>
            <div class="daily-sign" id="daily-sign">

            </div>
        </div>
        <div class="daily-life">
            <div class="daily-life-img">
                <%--                <img class="img-cover" id="daily-life-img" alt="日常生活配图" src="">--%>
            </div>
            <div class="daily-life-summary" id="daily-life-summary">

            </div>
        </div>
    </div>
    <main id="main" class="site-main">
        <c:if test="${articleList!=null}">
            <c:forEach items="${articleList}" var="article">
                <c:if test="${article.hasPic==0}">
                    <article class="article-style1">
                        <div class="thumb">
                            <img alt="tag" width="160px"
                                    <c:if test="${article.isError==1}">src="/img/tag/error.png"</c:if>
                                    <c:if test="${article.isError==0}">src="/img/tag/code-1.png"</c:if>
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
                                        <a class="tag-link" href="/tags/${tag.tagId}">#${tag.tagName}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                            <div class="time">最后修改时间：<fmt:formatDate value='${article.articleUpdateTime}'
                                                                     pattern='yyyy-MM-dd'/></div>
                        </div>
                    </article>
                </c:if>
                <c:if test="${article.hasPic==1}">
                    <article class="article-style2">
                        <img src="${article.firstPicUrl}" alt="首图"
                             style="position: absolute;width: 99.6%;height: 500px;border-radius: 15px;"/>
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
                                        <a class="tag-link" href="/tags/${tag.tagId}">#${tag.tagName}</a>
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
    </main>
</div>
<%@ include file="../public/footer.jsp" %>
</body>

<script type="text/javascript" src="<c:url value="/js/jquery-3.2.1.js"/>"></script>
<script type="text/javascript" src="/js/defaultpart.js"></script>
<script type="text/javascript" src="/js/home.js"></script>
<script type="text/javascript">
</script>
</html>
