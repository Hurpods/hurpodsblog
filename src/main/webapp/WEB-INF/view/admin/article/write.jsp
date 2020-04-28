<%--
  Created by IntelliJ IDEA.
  User: Hurpods
  Date: 2020/4/28
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<rapid:override name="content">
    <div class="article-title">
        <label style="position: relative;left: 10px;line-height: 75px;">标题 <span
                style="color: #FF5722; ">*</span></label>
        <div class="title-input">
            <input type="text" name="articleTitle" autocomplete="off" placeholder="请输入标题" maxlength="50"
                   style="font-size: 17px;border: solid 1px #9c9c9c;height: 35px;padding: 15px;width: 100%;">
        </div>
    </div>
    <div class="article-content">
        <textarea name="articleContent" id="content"></textarea>
    </div>
</rapid:override>
<rapid:override name="script">
    <script type="text/javascript" src="/plugin/HandyEditor/HandyEditor.min.js"></script>
    <script type="text/javascript">
        let he = HE.getEditor("content", {
            height: '1010px',
            autoHeight: true,
            autoFloat: false,
            topOffset: 10,
            uploadPhoto: true,
            uploadPhotoHandler: '',
            uploadPhotoSize: 5120,
            uploadPhotoType: 'gif,png,jpg,jpeg',
            item: ['bold', 'italic', 'strike', 'underline', 'fontSize', 'fontName', 'paragraph', 'color', 'backColor', '|', 'center', 'left', 'right', 'full', 'indent', 'outdent', '|', 'link', 'unlink', 'textBlock', 'code', 'selectAll', 'removeFormat', 'trash', '|', 'image', 'expression', 'subscript', 'superscript', 'horizontal', 'orderedList', 'unorderedList', '|', 'undo', 'redo', '|', 'html']
        });
    </script>
</rapid:override>
<%@ include file="../backstage.jsp" %>