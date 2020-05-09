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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/plugin/jquery-confirm/jquery-confirm.min.css" type="text/css"/>
    <link rel="shortcut icon" href="/img/logo/black_128.png" type="image/x-icon"/>
    <link rel="icon" href="/img/logo/black_64.png" type="image/x-icon"/>
    <link rel="stylesheet" href="/css/defaultpart.css" type="text/css"/>
    <link rel="stylesheet" href="/css/article.css" type="text/css"/>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8"/>
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
    <div class="comment-relative" id="torch">
        <div class="comment-textarea">
            <form id="comment-form" method="post">
                <c:if test="${sessionScope.user!=null}">
                    <input type="hidden" name="commentAuthorId" value="${sessionScope.user.userId}"/>
                    <input type="hidden" name="commentArticleId" value="${articleId}"/>
                    <input type="hidden" name="commentAuthorNickName" value="${sessionScope.user.userNickName}"/>
                    <input type="hidden" name="commentAuthorAvatar" value="${sessionScope.user.userAvatar}"/>
                    <input type="hidden" name="commentContent" value=""/>
                    <input type="hidden" name="commentPreId" value="0"/>
                    <input type="hidden" name="commentPreNickName" value=""/>
                    <input type="hidden" name="commentRootId" value="0"/>
                </c:if>
                <textarea id="comment-area"></textarea>
            </form>
            <div class="button" id="cancel-reply" style="width: 100px;float: right;position: relative;right: 110px;display:none">
                <button class="bttn" type="button">取消回复</button>
            </div>
            <div class="button" style="width: 100px;position: relative;left: 89.5%;" id="comment-submit">
                <button class="bttn" type="button">发表评论</button>
            </div>
        </div>
        <div class="comment-detail">
            <ul style="padding-left: 0;padding-bottom: 10px;">
                <c:forEach items="${commentList}" var="comment">
                    <c:if test="${comment.commentPreId==0}">
                        <li>
                            <div class="comment-${comment.commentId}" style="padding: 10px 10px 0;margin: 15px 0;height: auto;position: relative;">
                                <div class="comment-author-avatar">
                                    <img src="${comment.commentAuthorAvatar}"
                                         style="width: 35px;border-radius: 50%;vertical-align: middle;"/>
                                    <span style="width: fit-content;"><strong>${comment.commentAuthorNickName}</strong></span>
                                </div>
                                <div class="comment-content">
                                    <div class="ck-content" style="min-height: 0;">
                                            ${comment.commentContent}
                                    </div>
                                </div>
                                <a class="reply-link" href="#torch">回复</a>
                                <div class="comment-post-time">
                                    <span>发表时间：<fmt:formatDate value="${comment.commentTime}" pattern='yyyy-MM-dd'/></span>
                                </div>
                                <div class="locate-div">
                                    <div class="reply-textarea" style="width: 96%;padding-left: 35px;"></div>
                                </div>
                            </div>
                            <ul>
                                <c:forEach items="${commentList}" var="reply">
                                    <c:if test="${reply.commentRootId==comment.commentId}">
                                        <li>
                                            <div class="reply-${reply.commentId}" style="position: relative;border-left: 5px solid skyblue;padding: 10px 10px 0;">
                                                <div class="comment-author-avatar">
                                                    <img src="${reply.commentAuthorAvatar}"
                                                         style="width: 35px;border-radius: 50%;vertical-align: middle;"/>
                                                    <span style="width: fit-content;"><strong>${reply.commentAuthorNickName}</strong></span>
                                                </div>
                                                <div class="comment-content">
                                                    <div class="ck-content" style="min-height: 0;">
                                                        ${reply.commentContent}
                                                    </div>
                                                </div>
                                                <a class="reply-link" href="#torch">回复</a>
                                                <div class="comment-post-time">
                                                    <span>发表时间：<fmt:formatDate value="${reply.commentTime}" pattern='yyyy-MM-dd'/></span>
                                                </div>
                                                <div class="locate-div">
                                                    <div class="reply-textarea" style="width: 96%;padding-left: 35px;"></div>
                                                </div>
                                            </div>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
<%@ include file="../public/footer.jsp" %>
</body>
<script type="text/javascript" src="<c:url value="/js/jquery-3.2.1.js"/>"></script>
<script type="text/javascript" src="/plugin/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="/js/defaultpart.js"></script>
<script type="text/javascript" src="/plugin/jquery-confirm/jquery-confirm.min.js"></script>
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
