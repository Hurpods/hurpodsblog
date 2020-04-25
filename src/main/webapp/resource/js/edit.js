let password_flag = Boolean(false);

function getProvince() {
    let province = $("#province");
    $.ajax({
        type: "get",
        url: "/getProvince",
        async: "false",
        dataType: "json",
        success: function (data) {
            let str = "";
            $.each(data, function (key, value) {
                str += "<option value=" + value.provinceCode + ">" + value.provinceName + "</option><br>";
            });
            province.empty();
            province.append("<option value = 0>--请选择--</option>");
            province.append(str);
        }
    });
}

$(document).ready(function () {
    getProvince();
    $("#province").change(function () {
        let city = $("#city");
        let code = $("#province option:selected").val();
        if (code !== "0") {
            $.ajax({
                type: "get",
                data: {
                    "code": code
                },
                url: "/getCityByProvince",
                async: "false",
                dataType: "json",
                success: function (data) {
                    let str = "";
                    $.each(data, function (key, value) {
                        str += "<option value=" + value.cityCode + ">" + value.cityName + "</option><br>";
                    });
                    city.empty();
                    city.append("<option value = 0>--请选择--</option>");
                    city.append(str);
                }
            });
        } else {
            city.empty();
        }
    });
});

$("#common").click(function () {
    let common = $("#common");
    let update = $("#update-password");
    common.css({"color": "black", "border-bottom": "2px solid"});
    update.css({"color": "#9c9c9c", "border-bottom": "0"});
    $("#new-password").val("");
    $("#old-password").val("");
    $(".basic").fadeIn(400);
    $(".update-password").hide();
});

$("#update-password").click(function () {
    let update = $("#update-password");
    let common = $("#common");
    update.css({"color": "black", "border-bottom": "2px solid"});
    common.css({"color": "#9c9c9c", "border-bottom": "0"});
    $(".edit-other")[0].reset();
    $(".basic").hide();
    $(".update-password").fadeIn(400);
});

$(".edit-avatar").hover(function () {
    $(".avatar-shadow").fadeOut(300);
}, function () {
    $(".avatar-shadow").fadeIn(300);
});

function uploadFile() {
    $("#file").click();
}

$("#file").change(function () {
    $("#submit-button").click();
});

const error = $(".error-box-common");

$("#update-nickname").blur(function () {
    let nickname = $(this).val();
    let reg = /^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
    if (nickname !== "") {
        if (nickname.length < 2) {
            error.html("昵称至少2位");
        } else if (!reg.test(nickname)) {
            error.html("昵称仅能由汉字、字母、数字、下划线组成且不以下划线开头结尾")
        } else {
            error.html("");
        }
    } else {
        error.html("");
    }
});

$("#update-tel").blur(function () {
    let tel = $(this).val();
    if (tel !== "") {
        $.ajax({
            type: "POST",
            url: "/checkToken",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: {"token": tel, "type": "userTel"},
            dataType: "json",
            success: function (data) {
                error.html(data.msg);
            }
        });
    } else {
        error.html("");
    }
});

$("#update-email").blur(function () {
    let email = $(this).val();
    if (email !== "") {
        $.ajax({
            type: "POST",
            url: "/checkToken",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: {"token": email, "type": "userEmail"},
            dataType: "json",
            success: function (data) {
                error.html(data.msg);
            }
        });
    } else {
        error.html("");
    }
});

$("#new-password").blur(function () {
    let oldp = $("#old-password").val();
    let newp = $("#new-password").val();
    let error = $(".error-box-password");
    if (oldp === "" || newp === "") {
        error.html("新密码和原始密码不得为空");
        password_flag = Boolean(false);
    } else if (oldp === newp) {
        error.html("两次密码不得相同");
        password_flag = Boolean(false);
    } else if (newp.length < 6) {
        error.html("新密码不得小于6位");
        password_flag = Boolean(false);
    } else {
        error.html("");
        password_flag = Boolean(true);
    }
});

$("#old-password").blur(function () {
    if ($(this).val() !== "") {
        $(".error-box-password").html("");
        $("#new-password").blur();
    }
});

$("#update-password-save").click(function () {
    let oldp = $("#old-password").val();
    let newp = $("#new-password").val();
    if (password_flag) {
        $.ajax({
            type: "post",
            sync: false,
            url: "update",
            data: {
                "oldp": oldp,
                "newp": newp,
                "method": "updatep"
            },
            cache: false,
            dataType: "text",
            success: function (data) {
                if (data === "error") {
                    alert("原始密码错误");
                    $("#new-password").val("");
                    $("#old-password").val("");
                } else {
                    alert("密码更改成功，请重新登陆");
                    sessionStorage.clear();
                    window.location.replace("home.jsp");
                }
            }
        })
    }
});

$("#update-other-save").click(function () {
    $.ajax({
        type: "POST",
        url: "/updateInfo",
        sync: "false",
        data: $(".edit-other").serialize(),
        success: function () {
            alert("修改资料成功,即将返回个人中心");
            window.location.replace("/profile");
        }
    })
});
