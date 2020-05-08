$("#user-avatar").hover(function () {
    $(".user-operator").slideDown();
}, function () {
    $(".header-right").hover(function () {
    }, function () {
        $(".user-operator").slideUp();
    })
})

function unescapeHTML(a) {
    a = "" + a;
    return a.replace(/&lt;/g, "<").replace(/&gt;/g, ">").replace(/&amp;/g, "&").replace(/&quot;/g, '"').replace(/&apos;/g, "'");
}

window.onload = function () {
    $(".comment-content .ck-content").each(function () {
        $(this).html(unescapeHTML($(this).html()));
    })
}

$("#comment-submit").click(function () {
    let commentContent = myEditor.getData();
    if (commentContent.length === 0 || $(commentContent).text().trim() === "") {
        $.alert("评论内容不得为空");
    } else {
        console.log(commentContent)
        commentContent = encodeURIComponent(commentContent);
        $("input[name=commentContent]").attr("value", commentContent);
        $.ajax({
            url: "/postComment",
            type: "post",
            async: false,
            data: $("#comment-form").serialize(),
            dataType: "JSON",
            success: function (data) {
                $.alert(data.msg);
                if (data.status === "false") {
                    myEditor.setData("");
                }
            }
        });
    }
})

$(".reply-link").click(function () {
    let preNickName = $(this).parent().find("strong").text();
    let preId = $(this).parent().attr("class").match(/\d+/g);
    let rootId=$(this).parents("li").find("div").attr("class").match(/\d+/g);


    $("input[name=commentPreId]").attr("value", preId);
    $("input[name=commentPreNickName]").attr("value", preNickName);
    $("input[name=commentRootId]").attr("value",rootId);

    myEditor.setData("@"+preNickName+"\n");
});