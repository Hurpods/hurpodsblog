<%--
  Created by IntelliJ IDEA.
  User: Hurpods
  Date: 2020/5/5
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${article.articleTitle}</title>
    <link rel="shortcut icon" href="/img/logo/black_128.png" type="image/x-icon"/>
    <link rel="icon" href="/img/logo/black_64.png" type="image/x-icon"/>
    <link rel="stylesheet" href="/css/defaultpart.css" type="text/css"/>
    <link rel="stylesheet" href="/css/article.css" type="text/css"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="description" content="Hurpods的个人Blog">
    <meta name="keywords" content="摸鱼，Java，数据库，Spring">
</head>
<body>
<%@ include file="../public/header.jsp" %>
<div class="inner">
    <div class="author-detail">

    </div>
    <div class="article-detail">
        <div class="ck-content">
            ${article.articleContent}
        </div>
    </div>
    <div class="article-navigation">
        <div class="pre-nav">
            <img src="/img/icon/pre.png" height="24px" style="vertical-align: bottom;"/>上一篇文章<br>
            <span>此处是上一篇文章标题</span>
        </div>
        <div class="next-nav">
            下一篇文章<img src="/img/icon/next.png" height="24px" style="vertical-align: bottom;"/><br>
            <span>此处是下一篇文章标题</span>
        </div>
    </div>
    <div class="comment-relative">
        <div class="comment-textarea">

        </div>
        <div class="comment-detail">

        </div>
    </div>
</div>
<%@ include file="../public/footer.jsp" %>
</body>
<script type="text/javascript" src="<c:url value="/js/jquery-3.2.1.js"/>"></script>
<script type="text/javascript" src="/plugin/ckeditor/ckeditor.js"></script>
</html>
