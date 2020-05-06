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
    <div class="article-detail">
        <div class="author-detail">
            <div class="author-avatar">
                <img src="${author.userAvatar}" style="border-radius: 50%;width: 70px;"/>
            </div>
            <span style="position: relative;display: block;width: fit-content;margin: 15px auto 5px;">${author.userNickName}</span>
        </div>
        <div class="ck-content" style="padding: 0 10px;">
            ${article.articleContent}
        </div>
    </div>
    <div class="article-navigation">
        <c:if test="${pre!=null}">
            <div class="pre-nav">
                <a href="/article/${pre.articleId}">
                    <img src="/img/icon/pre.png" height="24px" style="vertical-align: bottom;"/>上一篇文章
                </a>
                <br>
                <span>${pre.articleTitle}</span>
            </div>
        </c:if>
        <c:if test="${next!=null}">
            <div class="next-nav">
                <a href="/article/${next.articleId}">
                    下一篇文章<img src="/img/icon/next.png" height="24px" style="vertical-align: bottom;"/>
                </a>
                <br>
                <span>${next.articleTitle}</span>
            </div>
        </c:if>
    </div>
    <div class="comment-relative">
        <div class="comment-textarea">
            <textarea id="comment-area"></textarea>
            <div class="button" style="width: 100px;position: relative;margin: 15px 0;left: 89.5%;">
                <button class="bttn" type="button">提交</button>
            </div>
        </div>
        <div class="comment-detail">
            <div class="comment-box">
                <div class="comment-author-avatar">
                    <img src="/img/avatar/0.png" style="width: 35px;border-radius: 50%;vertical-align: middle;"/>
                    <span style="width: fit-content;">小老虎不做梦</span>
                </div>
                <div class="comment-content">
                    确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实确实
                </div>
                <a style="position: absolute;right: 2%;top: 17.5px;cursor: pointer;">回复</a>
                <div class="comment-post-time">
                    <span>发表时间：2020年5月6日</span>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../public/footer.jsp" %>
</body>
<script type="text/javascript" src="<c:url value="/js/jquery-3.2.1.js"/>"></script>
<script type="text/javascript" src="/plugin/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
    let myEditor = null;

    ClassicEditor
        .create(document.querySelector('#comment-area'), {
            fontSize: {
                options: [
                    9,
                    11,
                    13,
                    'default',
                    17,
                    19,
                    21,
                    24
                ],
            },
            toolbar: [
                'fontSize',
                'fontColor',
                'FontBackgroundColor',
                'bold',
                'italic',
                '|',
                'bulletedList',
                'numberedList',
                'link',
                '|',
                'blockQuote',
                'codeBlock',
                'undo',
                'redo'
            ]
        })
        .then(editor => {
            myEditor = editor;
        })
        .catch(error => {
            console.error(error);
        });
</script>
</html>
