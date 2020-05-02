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

$("#delete").click(function(){
    $.confirm({
        title:'删除确认!',
        content:'此操作不可逆，且为敏感操作！若要继续操作，请输入密码：' +
            '<form action="" class="formName">' +
            '<div class="form-group">' +
            '<input type="password" placeholder="请输入密码" class="name form-control" required id="delete-password"/>' +
            '</div>' +
            '</form>',
        type:'red',
        confirmButtonClass:'btn-danger',
        cancelButtonClass:'btn-info',
        buttons:{
            ok:{
                text:"确认",
                action:function(){
                    $.ajax({
                        type: "POST",
                        url: "/deleteUser",
                        data: {
                            "password":$("#delete-password").val()
                        },
                        sync: "false",
                        dataType: "JSON",
                        success: function (data) {
                            if (data.status === "false") {
                                $.alert({
                                    title:"提示",
                                    content:data.msg
                                });
                                $("#delete-password").html("");
                            } else {
                                $.alert({
                                    title:"提示",
                                    content:data.msg
                                });
                                setTimeout(function(){window.location.replace("/");},3000);
                            }
                        }
                    })
                }
            },
            cancel:{
                text:"取消"
            }
        }
    })
});
