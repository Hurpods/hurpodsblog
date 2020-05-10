<%--
  Created by IntelliJ IDEA.
  User: Hurpods
  Date: 2020/5/4
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<rapid:override name="content">
    <div class="article-relative">
        <div class="article-overview">
            <span>概览</span>
        </div>
        <div class="most-view">
            <span>浏览最多</span>
        </div>
        <div class="most-comment">
            <span>评论最多</span>
        </div>
        <div class="update-recent">
            <span>最近更新</span>
        </div>
    </div>
    <div class="user-relative">
        <div class="user-overview">
            <span>概览</span>
        </div>
    </div>
    <div class="comment-relative">

    </div>
</rapid:override>
<rapid:override name="script">

</rapid:override>
<%@ include file="backstage.jsp" %>