<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Hurpods
  Date: 2019/12/23
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="/img/logo/black_128.png" type="image/x-icon"/>
    <link rel="icon" href="/img/logo/black_64.png" type="image/x-icon"/>
    <link rel="stylesheet" href="/css/edit.css" type="text/css">
    <link rel="stylesheet" href="/css/defaultpart.css" type="text/css">
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>编辑资料</title>
</head>>
<body>
<%@ include file="../public/header.jsp" %>
<div class="edit-header">
    <div class="back-to-profile">
        <a href="/profile">
            <img src="/img/icon/back.png" style="vertical-align: middle"/>
            <span style="position: absolute;width: 108px;height: 24px;top: 2px;">返回个人中心</span>
        </a>
    </div>
    <hr style="width:75%;position:absolute;left:13%;top:135px">
    <ul class="switch-ul">
        <li style="margin-right: 90px;color:black;border-bottom: 2px solid" id="common">基本资料</li>
        <li id="update-password">更改密码</li>
    </ul>
</div>
<div class="basic">
    <div class="tips-info">请填写更改项，留空为不更改</div>
    <div class="edit-avatar">
        <div class="avatar-shadow">
            <span>修改头像</span>
        </div>
        <div id="collect-avatar" style="height:100%;width:100%">
            <form method="post" enctype="multipart/form-data" action="upload">
                <input style="display:none" type="file" name="avatar" accept="image/jpg,image/jpeg,image/png" id="file">
                <input type="submit" style="display:none" id="submit-button">
            </form>
            <img id="user-avatarpic" src="${sessionScope.user.userAvatar}" onclick="uploadFile()" style="position:absolute;top:0">
        </div>
    </div>
    <form class="edit-other">
        <div>
            <span>昵称</span>
            <div>
                <input type="text" maxlength="20" size="25" name="update_nickname" id="update-nickname">
            </div>
        </div>
        <div>
            <span>所在地</span>
            <div style="margin:15px 0">
                <pre style="margin: 5px 0;">省份</pre>
                <select id="province" name="province_code"></select>
                <pre style="margin: 5px 0;">市/区</pre>
                <select id="city" name="city_code"></select>
            </div>
        </div>
        <div>
            <span>手机号</span>
            <div>
                <input type="text" maxlength="11" size="25" id="update-tel" name="update_tel">
            </div>
        </div>
        <div>
            <span>邮箱</span>
            <div>
                <input type="text" size="25" id="update-email" name="update_email">
            </div>
        </div>
    </form>
    <div class="error-box-common"></div>
    <div class="button" style="height: 50px;width: 100px;position: absolute;top:85%;left:515px">
        <button type="button" class="bttn" style="width:100px" id="update-other-save">保存更改</button>
    </div>
</div>
<div class="update-password">
    <div>
        <span>原始密码</span>
        <input name="old-password" type="password" maxlength="20" size="25" id="old-password">
    </div>
    <div>
        <span>新密码</span>
        <input name="new-password" type="password" maxlength="20" size="25" id="new-password">
    </div>
    <div class="error-box-password" style="text-align:center;height:30px;font-size:18px;color:red"></div>
    <div class="button" style="height: 50px;width: 100px;position: absolute;top: 55%;left: 255px;">
        <button type="button" class="bttn" style="width:100px" id="update-password-save">保存更改</button>
    </div>
</div>
<%@ include file="../public/footer.jsp" %>
</body>

<script type="text/javascript" src="<c:url value="js/jquery-3.2.1.js"/>"></script>
<script type="text/javascript" src="/js/defaultpart.js"></script>
<script type="text/javascript" src="/js/edit.js"></script>
</html>
