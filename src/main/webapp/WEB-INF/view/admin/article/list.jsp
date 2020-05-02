<%--
  Created by IntelliJ IDEA.
  User: Hurpods
  Date: 2020/4/28
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

<rapid:override name="content">
    <span style="position: relative;display: block;left: 27px;width: fit-content;">文章总数：${requestScope.status.count}，观看总数：${requestScope.status.view}，评论总数：${requestScope.status.comment}</span>
    <c:if test="${requestScope.articleList!=null}">
        <c:forEach items="${requestScope.articleList}" var="article">
            <div class="article" id="article-${article.articleId}">
                <div class="article-title">${article.articleTitle}</div>
                <div>
                    <c:forEach items="${article.tagList}" var="tag">
                        <img src="/img/icon/tag.png" style="vertical-align: middle;"/>#${tag.tagName}
                    </c:forEach>
                </div>
                <div class="article-create-time">攥写时间：${article.articleCreateTime}</div>
                <div class="article-update-time">修改时间：${article.articleUpdateTime}</div>
                <a href="/admin/article/editPage/${article.articleId}"
                   style="display: block;position: relative;bottom: 132px;left: 3px;">
                    <div class="button" style="top: 10px;left: 66.5%;">
                        <button class="bttn" type="button">修改</button>
                    </div>
                </a>
                <div class="button" style="top: 58%;left: 66.5%;background: red">
                    <button class="bttn" type="button" onclick="deleteArticle(${article.articleId})">删除</button>
                </div>
            </div>
        </c:forEach>
    </c:if>


</rapid:override>
<rapid:override name="script">
    <script type="text/javascript">


        function deleteArticle(articleId) {
            $.confirm({
                title: '删除确认!',
                content: '此操作不可逆，确认删除该文章？',
                type: 'red',
                confirmButtonClass: 'btn-danger',
                cancelButtonClass: 'btn-info',
                buttons: {
                    ok: {
                        text: "确认",
                        action: function () {
                            $.ajax({
                                url: "/admin/article/deleteArticle",
                                data: {
                                    articleId: articleId
                                },
                                async: "false",
                                success: function () {
                                    alert("删除成功")
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