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
    <div class="article">
        <div class="article-title">这是一个测试用的</div>
        <div>
            <img src="/img/icon/tag.png" style="vertical-align: middle;"/>#java
            <img src="/img/icon/tag.png" style="vertical-align: middle;"/>#java
            <img src="/img/icon/tag.png" style="vertical-align: middle;"/>#java
        </div>
        <div class="article-create-time">攥写时间：2020年4月27日</div>
        <div class="article-update-time">修改时间：2020年5月1日</div>
        <div class="button" style="top: 10px;left: 67.5%;">
            <button class="bttn" type="button" >修改</button>
        </div>
        <div class="button" style="top: 60%;left: 67.5%;background: red">
            <button class="bttn" type="button">删除</button>
        </div>
    </div>

</rapid:override>
<rapid:override name="script">

</rapid:override>
<%@ include file="../backstage.jsp" %>