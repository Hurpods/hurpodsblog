function getComment() {
    var a = location.href.split("/");
    var page = a[a.length - 1];
    if (page === "2017.jsp" || page === "2019.jsp" || page === "2018.jsp") {
        $.ajax({
            type: "get",
            sync: false,
            url: "comment",
            data: {
                "method": "getComment",
                "page": page
            },
            dataType: "json",
            success: function (data) {
                var comment = $(".comment-box ul");
                var str = "";
                $.each(data, function (key, value) {
                    if (!(value.status === "none")) {
                        var headpic = "img" + value.head.split("/img")[1];
                        if (headpic.indexOf("-large.") === -1) {
                            var temp = headpic.split(".");
                            headpic = temp[0] + "-large." + temp[1];
                        }
                        str +=
                            "<li>"
                            + "<div class=\"comment-detail\">"
                            + "<img src=\"" + headpic + "\">"
                            + " <b  class=\"comment-user\">" + value.name + "</b>"
                            + "<span class=\"comment-words\">" + value.words + "</span>"
                            + "<span class=\"comment-time\" >评论发布时间：" + value.time + "</span>"
                            + "</div>"
                            + "</li>";
                    } else {
                        str += "<li>目前没有评论，快去试试评论吧！</li>"
                    }
                });
                comment.empty();
                comment.append(str);
            }
        });
    } else {

    }
}

function postComment() {
    var textarea = $("#comment-input-words");
    var words = textarea.val();
    var a = location.href.split("/");
    var page = a[a.length - 1];
    var head = $("#user-head").prop("src");
    //var temp = head.split(".");
    //head = temp[0] + "-large." + temp[1];
    $.ajax({
        type: "post",
        sync: false,
        url: "comment",
        data: {
            "method": "postComment",
            "words": words,
            "page": page,
            "head": head
        },
        success: function () {
            textarea.val("");
            getComment();
        }
    })
}

function changeTextarea() {
    var textarea = $("#comment-input-words");
    if (!checkLogin()) {
        textarea.html("登陆后才可以使用评论功能");
        textarea.css("cursor", "not-allowed");
        textarea.prop("readonly", "readonly");
    } else {
        textarea.html("");
        textarea.css("cursor", "auto");
        textarea.removeProp("readonly");
    }
}

$("#user-avatar").hover(function () {
    $(".user-operator").slideDown();
},function(){
    $(".header-right").hover(function(){},function(){
        $(".user-operator").slideUp();
    })
})