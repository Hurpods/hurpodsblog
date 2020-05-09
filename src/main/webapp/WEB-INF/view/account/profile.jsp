<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Hurpods
  Date: 2019/12/22
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <link rel="shortcut icon" href="/img/logo/black_128.png" type="image/x-icon"/>
    <link rel="icon" href="/img/logo/black_64.png" type="image/x-icon"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/plugin/jquery-confirm/jquery-confirm.min.css" type="text/css"/>
    <link rel="stylesheet" href="/css/profile.css" type="text/css"/>
    <link rel="stylesheet" href="/css/defaultpart.css" type="text/css">
    <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
    <title>个人中心</title>
</head>
<body>

<%
    if (request.getSession().getAttribute("user") == null) {
        response.sendRedirect("/loginPage");
    }
%>

<%@ include file="../public/header.jsp" %>
<div class="user-index">
    <div class="banner">

    </div>
    <div class="user-info">
        <div class="user-avatarpic">
            <img src="${sessionScope.user.userAvatar}" id="user-avatarpic"/>
        </div>
        <div class="user-name">
            ${sessionScope.user.userNickName}
        </div>
        <div class="show-or-hide-user-detail" id="show-detail">
            详细资料
        </div>
        <div class="show-or-hide-user-detail" style="display:none" id="hide-detail">
            收起
        </div>
        <div class="user-edit">
            <div class="button" style="width: 102px;height: 36px;position: static;border-radius: 40px;">
                <a href="/update">
                    <button class="bttn" id="edit" type="button" style="width: 102px;height: 36px;">修改资料</button>
                </a>
            </div>
        </div>
        <div class="user-delete">
            <div class="button" style="width: 102px;height: 36px;position: static;border-radius: 40px;background:red">
                <button class="bttn" id="delete" type="button" style="width: 102px;height: 36px;">删除账号</button>
            </div>
        </div>
    </div>
    <div class="user-detail">
        <ul>
            <li>
                <div class="detail-left">
                    <span>UID</span>
                    <p id="UID">${sessionScope.user.userId}</p>
                </div>
                <div class="detail-right">
                    <span>用户名</span>
                    <p id="user_name">${sessionScope.user.userName}</p>
                </div>
            </li>
            <li>
                <div class="detail-left">
                    <span>注册时间</span>
                    <p id="register_date"><fmt:formatDate value='${sessionScope.user.userRegisterTime}' pattern='yyyy-MM-dd'/></p>
                </div>
            </li>
            <li>
                <div>
                    <span>所在地</span>
                    <p id="location">${sessionScope.user.province}${sessionScope.user.city}</p>
                </div>
            </li>
            <li>
                <div class="detail-left">
                    <span>手机号</span>
                    <p id="telnumber">${sessionScope.user.userTel}</p>
                </div>
                <div class="detail-right">
                    <span>邮箱</span>
                    <p id="email">${sessionScope.user.userEmail}</p>
                </div>
            </li>
        </ul>
    </div>
    <div class="user-comment">
        <label>近期回复</label>
        <hr>
        <div class="recent-comment">
            <ul id="recent-comment">
                <c:choose>
                    <c:when test="${commentList.size()>0}">
                        <c:forEach items="${commentList}" var="comment">
                            <li>
                                <div class="ck-content">
                                        ${comment.commentContent}
                                </div>
                            </li>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <div style="text-align: center;">
                                暂时没有发表过评论，快去试试吧~
                            </div>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</div>
<%@ include file="../public/footer.jsp" %>
</body>

<script type="text/javascript" src="<c:url value="/js/jquery-3.2.1.js"/>"></script>
<script type="text/javascript" src="/plugin/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="/js/defaultpart.js"></script>
<script type="text/javascript" src="/js/profile.js"></script>
<script type="text/javascript" src="/plugin/jquery-confirm/jquery-confirm.min.js"></script>
</html>
