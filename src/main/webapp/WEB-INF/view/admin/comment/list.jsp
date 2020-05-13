<%--
  Created by IntelliJ IDEA.
  User: Hurpods
  Date: 2020/5/13
  Time: 9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<rapid:override name="content">
    <span style="position: relative;display: block;left: 27px;width: fit-content;">评论总数：${commentList.size()}</span>
    <c:if test="${commentList.size()!=0}">
        <form id="comments">
            <c:forEach items="${commentList}" var="comment">
                <div class="comment" id="comment-${comment.commentId}">
                    <input type="checkbox" name="commentIds" style="margin-right: 50px;" value="${comment.commentId}">
                    <img src="${comment.commentAuthorAvatar}" width="80px" alt="头像"/>
                    <span style="bottom: 10px;left: 3%;position: relative;">${comment.commentAuthorNickName}</span>
                    <span style="position: relative;left: -60px;top: 15px;">
                        评论文章：
                        <a href="/article/${comment.commentArticleId}">
                        <c:forEach items="${articleList}" var="article">
                            <c:if test="${article.articleId==comment.commentArticleId}">
                                ${article.articleTitle}
                            </c:if>
                        </c:forEach>
                    </a>
                    </span>
                    <div class="button" style="width: 100px;background: red;position: relative;float: right;top: 10px;" onclick="deleteComment(${comment.commentId})">
                        <button class="bttn" type="button">删除</button>
                    </div>
                    <div class="ck-content" style="min-height: 0;margin: 15px;">
                            ${comment.commentContent}
                    </div>
                </div>
            </c:forEach>
            <div class="button" style="width: 100px;background: lightseagreen;position: fixed;top: 6%;right: 10%;" onclick="selectAll()">
                <button class="bttn" type="button">全选</button>
            </div>
            <div class="button" style="width: 100px;background: lightseagreen;position: fixed;top: 16%;right: 10%;" onclick="reserve()">
                <button class="bttn" type="button">反选</button>
            </div>
            <div class="button" style="width: 100px;background: red;position: fixed;top: 26%;right: 10%;" onclick="batchDeleteComments()">
                <button class="bttn" type="button">全部删除</button>
            </div>
        </form>
    </c:if>
</rapid:override>
<rapid:override name="script">
    <script type="text/javascript" src="/plugin/ckeditor/ckeditor.js"></script>
    <script type="text/javascript">
        function unescapeHTML(a) {
            a = "" + a;
            return a.replace(/&lt;/g, "<").replace(/&gt;/g, ">").replace(/&amp;/g, "&").replace(/&quot;/g, '"').replace(/&apos;/g, "'");
        }

        window.onload = function () {
            $(".ck-content").each(function () {
                $(this).html(unescapeHTML($(this).html()));
            })
        }

        function deleteComment(commentId){
            $.confirm({
                title: '警告',
                content: '此操作不可逆，确认删除该评论？',
                type: 'red',
                confirmButtonClass: 'btn-danger',
                cancelButtonClass: 'btn-info',
                buttons: {
                    ok: {
                        text: "确认",
                        action: function () {
                            $.ajax({
                                url: "/admin/article/deleteComment/" + commentId,
                                dataType: "JSON",
                                async: "false",
                                success: function (data) {
                                    $.alert(data.msg);
                                    if (data.status === "true") {
                                        $("#comment-" + commentId).remove();
                                    }
                                }
                            });
                        }
                    },
                    cancel: {
                        text: "取消"
                    }
                }
            });
        }

        let commentIds=$("input[name='commentIds']");
        function selectAll() {
            let flag = true;
            commentIds.each(function () {
                flag = flag && $(this).prop("checked");
            });
            if (flag) {
                commentIds.prop("checked", false);
            } else {
                commentIds.prop("checked", true);
            }
        }
        function reserve() {
            commentIds.each(function () {
                $(this).prop("checked", !$(this).prop("checked"));
            });
        }

        function batchDeleteComments(){
            $.confirm({
                title: '警告',
                content: '此操作不可逆，确认删除该标签？',
                type: 'red',
                confirmButtonClass: 'btn-danger',
                cancelButtonClass: 'btn-info',
                buttons: {
                    ok: {
                        text: "确认",
                        action: function () {
                            $.ajax({
                                url: "/admin/article/batchDeleteComments",
                                data: $("#comments").serialize(),
                                dataType: "JSON",
                                async: "false",
                                success: function (data) {
                                    $.alert(data.msg);
                                    if (data.status === "true") {
                                        commentIds.each(function () {
                                            if ($(this).prop("checked")) {
                                                $(this).parent().remove();
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    },
                    cancel: {
                        text: "取消"
                    }
                }
            });
        }
    </script>
</rapid:override>
<%@ include file="../backstage.jsp" %>
