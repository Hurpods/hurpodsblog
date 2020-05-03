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
        title:'警告',
        content:'此操作不可逆，且为敏感操作！若要继续操作，' +
            '<div class="form-group">' +
            '<label>请输入密码：</label>'+
            '<input type="password" placeholder="请输入密码" class="name form-control" required id="delete-password"/>' +
            '</div>'+
            '<p class="text-danger" style="display:none"></p>',
        type:'red',
        keyboardEnabled: true,
        confirmButtonClass:'btn-danger',
        cancelButtonClass:'btn-info',
        buttons:{
            ok:{
                text:"确认",
                action:function(){
                    let password=$("#delete-password").val();
                    let errorText=$('.text-danger');
                    if(password===""){
                        errorText.html("密码不能为空");
                        errorText.show();
                        return false;
                    }else{
                        errorText.hide();
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
                                    $.alert("密码错误，请重试");
                                    return false;
                                } else {
                                    $.alert({
                                        title:"提示",
                                        content:data.msg
                                    });
                                    setTimeout(function(){window.location.replace("/");},3000);
                                }
                            }
                        });
                    }
                }
            },
            cancel:{
                text:"取消"
            }
        }
    })
});
