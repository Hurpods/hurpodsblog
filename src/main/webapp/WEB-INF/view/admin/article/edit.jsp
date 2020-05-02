<%--
  Created by IntelliJ IDEA.
  User: Hurpods
  Date: 2020/5/2
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<rapid:override name="content">
    <form id="article">
        <div class="article-header">
            <label style="position: relative;left: 10px;line-height: 75px;">标题 <span
                    style="color: #FF5722; ">*</span></label>
            <div class="title-input">
                <input type="text" name="articleTitle" autocomplete="off" placeholder="请输入标题" maxlength="50"
                       value="<c:if test="${article!=null}">${article.articleTitle}</c:if>"
                       style="font-size: 17px;border: solid 1px #9c9c9c;height: 35px;padding: 15px;width: 100%;">
            </div>
            <label class="tags-tag">标签</label>
            <ul class="article-tagList">
                <c:forEach items="${requestScope.tagList}" var="tag">
                    <label>
                        <input type="checkbox" name="articleTagIds" value="${tag.tagId}"
                        <c:if test="${tagIds!=null}">
                        <c:forEach items="${tagIds}" var="tagId">
                               <c:if test="${tagId == tag.tagId}">checked</c:if>
                        </c:forEach>
                        </c:if>
                        >#${tag.tagName}
                    </label>
                </c:forEach>
            </ul>
        </div>

        <div class="article-content">
            <textarea name="articleContent" id="content"><c:if
                    test="${article!=null}">${article.articleContent}</c:if></textarea>
        </div>

    </form>
    <div class="button" style="position: relative;margin: 15px 0 25px;">
        <button class="bttn" type="button" id="article-submit" onclick="modifyArticle(${article.articleId})">提交</button>
    </div>
    <div class="operator-shadow"></div>
</rapid:override>
<rapid:override name="script">
    <script type="text/javascript" src="/plugin/ckeditor/ckeditor.js"></script>
    <script type="text/javascript">
        let myEditor = null;

        ClassicEditor
            .create(document.querySelector('#content'), {
                ckfinder: {
                    uploadUrl: "/uploadPic?command=QuickUpload&type=Files&responseType=json'"
                },
                image: {
                    toolbar: [
                        'imageStyle:full',
                        'imageStyle:side',
                        '|',
                        'imageTextAlternative'
                    ]
                },
                language: 'zh-cn',
                toolbar: [
                    'heading',
                    '|',
                    'bold',
                    'italic',
                    'link',
                    'alignment:left',
                    'alignment:right',
                    'alignment:center',
                    'alignment:justify',
                    'bulletedList',
                    'numberedList',
                    '|',
                    'indent',
                    'outdent',
                    '|',
                    'imageUpload',
                    'blockQuote',
                    'insertTable',
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

        function modifyArticle(articleId) {
            let serializeData = $("#article").serialize();
            serializeData += "&htmlContent=" + myEditor.getData();
            serializeData += "&summary=" + $(myEditor.getData()).text();
            serializeData += "&articleId=" + articleId;
            serializeData = decodeURIComponent(serializeData);
            $.ajax({
                type: "POST",
                url: "/admin/article/modifyArticle",
                data: serializeData,
                dataType: "JSON",
                success: function (data) {
                    $("#article")[0].reset();
                    $.alert({
                        title: "提示",
                        content: data.msg
                    });
                }
            })
        }
    </script>
</rapid:override>
<%@ include file="../backstage.jsp" %>