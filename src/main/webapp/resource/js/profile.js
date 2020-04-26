function getUserComment() {
    $.ajax({
        type: "get",
        url: "query",
        data: "method=getUserComment",
        dataType: "json",
        sync: false,
        success: function (data) {
            let ul = $("#recent-comment");
            let str = "";
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
    let detail = $(".user-detail");
    detail.slideDown(400);
    $("#show-detail").hide();
    $("#hide-detail").show();
});
$("#hide-detail").click(function () {
    let detail = $(".user-detail");
    detail.slideUp(400);
    $("#show-detail").show();
    $("#hide-detail").hide();
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
    let password = $("#delete-password").val();
    $.ajax({
        type: "POST",
        url: "/deleteUser",
        data: {
            "password":password
        },
        sync: "false",
        dataType: "JSON",
        success: function (data) {
            if (data.status === "false") {
                alert(data.msg);
                $("#delete-password").html("");
            } else {
                alert(data.msg);
                window.location.replace("/");
            }
        }
    })
});
