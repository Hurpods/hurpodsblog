function getUserDetail() {
    $.ajax({
        type: "get",
        url: "query",
        data: "method=getUserDetail",
        dataType: "json",
        sync: false,
        success: function (data) {
            $.each(data, function (key, value) {
                $("#UID").html(value.uid);
                if (value.sex === "male") {
                    $("#sex").html("男");
                } else {
                    $("#sex").html("女")
                }
                $("#register_date").html(value.register_date);
                $("#location").html(value.location);
                $("#telnumber").html(value.telnumber);
                $("#email").html(value.email);
            });
        }
    });
}

function getUserComment() {
    $.ajax({
        type: "get",
        url: "query",
        data: "method=getUserComment",
        dataType: "json",
        sync: false,
        success: function (data) {
            var ul = $("#recent-comment");
            var str = "";
            $.each(data, function (key, value) {
                if (!(value.status === "none")) {
                    str += "<li style='font-size:20px;font-weight:bold;'>"
                        + value.words
                        + "<br><span style='font-size:15px;font-weight:normal;'>评论发布时间："
                        + value.time
                        + "</span>"
                        + "</li>";
                } else {
                    str += "<li>目前没有评论，快去试试评论吧！</li>"
                }
                ul.empty();
                ul.append(str);
            })
        }
    });
}

$("#show-detail").click(function () {
    var detail = $(".user-detail");
    detail.slideDown(400);
    $("#show-detail").hide();
    $("#hide-detail").show();
});
$("#hide-detail").click(function () {
    var detail = $(".user-detail");
    detail.slideUp(400);
    $("#show-detail").show();
    $("#hide-detail").hide();
});

$("#edit").click(function () {
    window.location.replace("edit.jsp");
});

$("#delete").click(function () {
    $(".delete-confirm").slideDown();
    $(".delete-shadow").show();
});

$("#delete-cancel").click(function () {
    $(".delete-confirm").hide();
    $(".delete-shadow").hide();
});

$("#delete-confirm").click(function () {
    var password = $("#delete-password").val();
    $.ajax({
        type: "post",
        url: "change",
        data: {
            "method":"delete",
            "password":password
        },
        sync: "false",
        dataType: "text",
        success: function (result) {
            if (result === "error") {
                alert("密码错误，请重新输入");
                $("#delete-password").html("");
            } else {
                sessionStorage.clear();
                alert("账号成功删除，即将返回首页");
                window.location.replace("home.jsp");
            }
        }
    })
});
