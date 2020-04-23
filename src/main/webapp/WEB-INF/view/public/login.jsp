<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Hurpods
  Date: 2019/12/18
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <link rel="shortcut icon" href="/img/logo/black_128.png" type="image/x-icon"/>
    <link rel="icon" href="/img/logo/black_64.png" type="image/x-icon"/>
    <link rel="stylesheet" href="/css/login.css" type="text/css"/>
    <link rel="stylesheet" href="/css/defaultpart.css" type="text/css"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="discription" content="Hurpods的个人Blog">
    <meta name="keywords" content="摸鱼，Java，数据库，Spring">
    <title>用户登陆</title>
</head>
<body style="overflow: hidden;height: 100%;">
<div style="width:1920px; height:980px;padding:0;margin:0;">
    <div class="left">
        <div class="pic">
            <ul class="img">
                <li><img src="/img/lunbo/1.png"></li>
                <li><img src="/img/lunbo/2.png"></li>
                <li><img src="/img/lunbo/3.png"></li>
                <li><img src="/img/lunbo/4.png"></li>
                <li><img src="/img/lunbo/5.png"></li>
            </ul>
            <ul class="num">

            </ul>
            <div class="btn btn-left"></div>
            <div class="btn btn-right"></div>
        </div>
        <div class="words">
            <h3>本站由Java Web驱动</h3>
            <h3>使用SSM框架、Java提供服务</h3>
        </div>
    </div>
    <div class="right">
        <div class="logo">
            <img src="/img/logo/black_64.png" width="45px" height="45px" style="vertical-align:middle;">
            <a href="/">
                <span>HurpodsBlog</span>
            </a>
        </div>
        <div id="switch">
            <label class="switch">
                <input type="checkbox" id="switch-checkbox">
                <span class="slider round"></span>
            </label>
        </div>
        <div class="login_right">
            <label style="font-size: 24px;position: absolute;left: 60px;top: 8px;">登陆</label>
            <form id="login">
                <div class="inputbox" id="login-tokenbox">
                    <div class="block" style="float:left">
                        <img src="/img/icon/user.png" width="45px" height="45px" style="margin:5px 3px 3px 2px">
                    </div>
                    <input class="inputbar" id="login-token"
                           name="token" type="text" size="38" style="font-size:15px" placeholder="用户名/手机号/邮箱">
                </div>
                <br>
                <div class="inputbox" id="login-passwordbox">
                    <div class="block" style="float:left">
                        <img src="/img/icon/password.png" width="45px" height="45px" style="margin:5px 3px 3px 2px">
                    </div>
                    <input class="inputbar" id="login-password" name="password" type="password" size="38"
                           style="font-size:15px" placeholder="密码">
                </div>
                <br>
            </form>
            <div class="button" style="top: 74%;left: 28%;">
                <button class="bttn" type="button" onclick="login()" id="login-button">登陆</button>
            </div>
        </div>
        <div class="register_right">
            <label style="font-size: 24px;position: absolute;left: 60px;top: 8px;">注册</label>
            <form id="register">
                <div class="inputbox" id="register-usnbox">
                    <div class="block" style="float:left">
                        <img src="/img/icon/user.png" width="45px" height="45px" style="margin:5px 3px 3px 2px">
                    </div>
                    <input class="inputbar" name="username" type="text" id="username" size="38" style="font-size:15px"
                           maxlength="20"
                           placeholder="请输入6-20位的用户名">
                </div>
                <div class="tips" id="usnerror"></div>

                <div class="inputbox" id="register-pswbox">
                    <div class="block" style="float:left">
                        <img src="/img/icon/password.png" width="45px" height="45px" style="margin:4px 3px 3px 2px">
                    </div>
                    <input class="inputbar" name="password" type="password" id="password" size="38"
                           style="font-size:15px" maxlength="18"
                           placeholder="请输入密码">
                </div>
                <div class="tips" id="pswerror"></div>

                <div class="inputbox" id="register-repswbox">
                    <div class="block" style="float:left">
                        <img src="/img/icon/password.png" width="45px" height="45px" style="margin:4px 3px 3px 2px">
                    </div>
                    <input class="inputbar" name="repassword" type="password" id="repassword" size="38"
                           style="font-size:15px" maxlength="18"
                           placeholder="请重复密码">
                </div>
                <div class="tips" id="repswerror"></div>

                <div class="inputbox" id="register-telbox">
                    <div class="block" style="float:left">
                        <img src="/img/icon/tel.png" width="45px" height="45px" style="margin:4px 3px 3px 2px">
                    </div>
                    <input class="inputbar" name="telnumber" type="text" id="telnumber" size="38" style="font-size:15px"
                           maxlength="11"
                           placeholder="请输入电话号码（可选）">
                </div>
                <div class="tips" id="telerror"></div>

                <div class="inputbox" id="register-emailbox">
                    <div class="block" style="float:left">
                        <img src="/img/icon/email.png" width="45px" height="45px" style="margin:4px 3px 3px 2px">
                    </div>
                    <input class="inputbar" name="email" type="email" id="e_mail" size="38" style="font-size:15px"
                           placeholder="请输入邮箱">
                </div>
                <div class="tips" id="emerror"></div>

                <div class="inputbox" id="register-protocolbox">
                    <div class="block" style="float:left">
                        <img src="/img/icon/protocol.png" width="45px" height="45px" style="margin:4px 3px 3px 2px">
                    </div>
                    <input type="checkbox" id="protocol" style="margin-top:19px;margin-left:8px">
                    <label for="protocol">
                        我已阅读并同意<a href="/" target="_blank">《用户使用协议》</a>
                    </label>
                </div>
                <br/>
            </form>
            <div class="button" style="left: 28%;top: 89%;">
                <button class="bttn" onclick="register()" type="button" id="register-button">立即注册</button>
            </div>
        </div>
        <div class="copyright">
            <span>Copyright&nbsp;©&nbsp;2020&nbsp;Hurpods</span>
        </div>
    </div>
</div>
</body>

<script type="text/javascript" src="<c:url value="/js/jquery-3.2.1.js"/>"></script>
<script type="text/javascript" src="/js/login.js"></script>
</html>
