<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <link rel="shortcut icon" href="/img/logo/black_128.png" type="image/x-icon"/>
    <link rel="icon" href="/img/logo/black_64.png" type="image/x-icon"/>
    <link rel="stylesheet" href="/css/home.css" type="text/css"/>
    <link rel="stylesheet" href="/css/defaultpart.css" type="text/css">
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="description" content="Hurpods的个人Blog">
    <meta name="keywords" content="摸鱼，Java，数据库，Spring">
    <title>HurpodsBlog——成长的道路</title>
</head>
<body>
<%@ include file="../public/header.jsp" %>
<div class="inner">
    <div class="top-box">
        <div class="daily">
            <div class="daily-img">
                <img class="img-cover" id="daily-img" src="http://bing.getlove.cn/bingImage" alt="每日一言图片">
            </div>
            <div class="daily-words" id="daily-words">

            </div>
            <div class="daily-sign" id="daily-sign">

            </div>
        </div>
        <div class="daily-life">
            <div class="daily-life-img">
                <img class="img-cover" id="daily-life-img" alt="日常生活配图" src="">
            </div>
            <div class="daily-life-summary" id="daily-life-summary">

            </div>
        </div>
    </div>
</div>
<%@ include file="../public/footer.jsp" %>
</body>

<script type="text/javascript" src="<c:url value="js/jquery-3.2.1.js"/>"></script>
<script type="text/javascript" src="/js/defaultpart.js"></script>
<script type="text/javascript" src="/js/home.js"></script>
<script type="text/javascript">
</script>
</html>
