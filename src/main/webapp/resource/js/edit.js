let password_flag = Boolean(false);
let flag_name = Boolean(false);
let flag_telnumber = Boolean(false);
let flag_email = Boolean(false);
let flag_location = Boolean(false);

function getProvince() {
    let province = $("#province");
    $.ajax({
        type: "get",
        url: "/getProvince",
        async: "false",
        dataType: "json",
        success: function (data) {
            console.log(data);
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
                data: "code=" + code,
                url: "/getCityByProvince",
                async: false,
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
    let change = $("#change-password");
    common.css({"color": "black", "border-bottom": "2px solid"});
    change.css({"color": "#9c9c9c", "border-bottom": "0"});
    $("#new-password").val("");
    $("#old-password").val("");
    $(".basic").fadeIn(400);
    $(".change-password").hide();
});

$("#change-password").click(function () {
    let change = $("#change-password");
    let common = $("#common");
    change.css({"color": "black", "border-bottom": "2px solid"});
    common.css({"color": "#9c9c9c", "border-bottom": "0"});
    $(".edit-other")[0].reset();
    $(".basic").hide();
    $(".change-password").fadeIn(400);
});

$(".edit-head").hover(function () {
    $(".head-shadow").fadeOut(300);
}, function () {
    $(".head-shadow").fadeIn(300);
});

function uploadFile() {
    $("#file").click();
}

$("#file").change(function () {
    $("#submit-button").click();
});

$("#change-username").blur(function () {
    let name = $(this).val();
    let error = $(".error-box-common");
    if (name !== "") {
        error.load(
            "register",
            {"name": name, "method": "checkName"},
            function (response, status, xhr) {
                if (response !== "available") {
                    error.html(response);
                    flag_name = Boolean(false);
                } else {
                    error.html("");
                    flag_name = Boolean(true);
                }
            });
    } else {
        error.html("");
        flag_name = Boolean(true);
        return true;
    }
});

$("#city").blur(function () {
    let error = $(".error-box-common");
    let city = $("#city option:selected").text();
    if (city === "--请选择--") {
        error.html("请选择市/区");
        flag_location = Boolean(false);
    } else {
        error.html("");
        flag_location = Boolean(true);
    }
});

$("#change-telnumber").blur(function () {
    let telnumber = $(this).val();
    let error = $(".error-box-common");
    if (telnumber !== "") {
        error.load(
            "register",
            {"telnumber": telnumber, "method": "checkTel"},
            function (response, status, xhr) {
                if (response !== "available") {
                    error.html(response);
                    flag_telnumber = Boolean(false);
                } else {
                    error.html("");
                    flag_telnumber = Boolean(true);
                }
            });
    } else {
        error.html("");
        flag_telnumber = Boolean(true);
    }
});

$("#change-email").blur(function () {
    let email = $(this).val();
    let error = $(".error-box-common");
    if (email !== "") {
        error.load(
            "register",
            {"email": email, "method": "checkEmail"},
            function (response, status, xhr) {
                if (response !== "available") {
                    error.html(response);
                    flag_email = Boolean(false);
                } else {
                    error.html("");
                    flag_email = Boolean(true);
                }
            });
    } else {
        error.html("");
        flag_email = Boolean(true);
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

$("#change-password-save").click(function () {
    let oldp = $("#old-password").val();
    let newp = $("#new-password").val();
    if (password_flag) {
        $.ajax({
            type: "post",
            sync: false,
            url: "change",
            data: {
                "oldp": oldp,
                "newp": newp,
                "method": "changep"
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

$("#change-other-save").click(function () {
    $("#change-username").blur();
    $("#change-telnumber").blur();
    $("#change-email").blur();
    $("#city").blur();
    let province = $("#province option:selected").text();
    if (flag_name && flag_telnumber && flag_email && flag_location) {
        $.ajax({
            type: "post",
            url: "change?method=changeo&province=" + province,
            sync: false,
            data: $(".edit-other").serialize(),
            success: function () {
                alert("修改资料成功,即将返回个人中心");
                window.location.replace("profile.jsp");
            }
        })
    }
});
