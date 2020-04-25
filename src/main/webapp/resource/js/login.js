let name_flag = Boolean(false);
let psw_flag = Boolean(false);
let tel_flag = Boolean(false);
let email_flag = Boolean(false);
let check = Boolean(false);

$("#switch-checkbox").change(function () {
    let register_page = $(".register_right");
    let login_page = $(".login_right");
    let title = $("title");
    if ($("#switch-checkbox").prop("checked")) {
        register_page.slideDown(500);
        login_page.slideUp();
        title.html("用户注册");
        $("#login")[0].reset();

    } else {
        login_page.slideDown(500);
        register_page.slideUp();
        title.html("用户登录");
        $("#register")[0].reset();
    }
});

let tokenData;

function checkToken(token, type) {
    $.ajax({
        async: false,
        type: "POST",
        url: "/checkToken",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: {"token": token, "type": type},
        dataType: "json",
        success: function (data) {
            tokenData = data;
        }
    })
}

//用户名判定
$("#username").blur(function () {
    let name = $("#username").val();
    let error = $("#usnerror");
    checkToken(name, "userName");
    if (tokenData.status === "true") {
        name_flag = Boolean(true);
        $("#register-usnbox").css("-webkit-box-shadow", "0px 0px 0px");
    } else {
        name_flag = Boolean(false);
        error.css("color", "red");
        $("#register-usnbox").css("-webkit-box-shadow", "0px 0px 10px red");
    }
    error.html(tokenData.msg);
});

//密码判定
$("#repassword").blur(function () {
    let error = $("#pswerror");
    let psw = $("#password").val();
    let repsw = $("#repassword").val();
    if (psw.length < 6) {
        psw_flag = Boolean(false);
        error.css("color", "red");
        $("#register-pswbox").css("-webkit-box-shadow", "0px 0px 10px red");
        $("#register-repswbox").css("-webkit-box-shadow", "0px 0px 10px red");
        error.html("密码不得小于6位")
    } else if (psw !== repsw) {
        psw_flag = Boolean(false);
        error.css("color", "red");
        $("#register-pswbox").css("-webkit-box-shadow", "0px 0px 10px red");
        $("#register-repswbox").css("-webkit-box-shadow", "0px 0px 10px red");
        error.html("两次密码不一致");
    } else {
        psw_flag = Boolean(true);
        error.html("");
        $("#register-pswbox").css("-webkit-box-shadow", "0px 0px 0px");
        $("#register-repswbox").css("-webkit-box-shadow", "0px 0px 0px");
    }
});

//电话判定
$("#telnumber").blur(function () {
    let tel = $(this).val();
    let error = $("#telerror");
    checkToken(tel, "userTel");
    if (tokenData.status === "true") {
        tel_flag = Boolean(true);
        $("#register-telbox").css("-webkit-box-shadow", "0px 0px 0px");
    } else {
        tel_flag = Boolean(false);
        error.css("color", "red");
        $("#register-telbox").css("-webkit-box-shadow", "0px 0px 10px red");
    }
    error.html(tokenData.msg);
});

//邮箱判定
$("#e_mail").blur(function () {
    let email = $("#e_mail").val();
    let error = $("#emerror");
    checkToken(email, "userEmail");
    if (tokenData.status === "true") {
        email_flag = Boolean(true);
        $("#register-emailbox").css("-webkit-box-shadow", "0px 0px 0px");
    } else {
        email_flag = Boolean(false);
        error.css("color", "red");
        $("#register-emailbox").css("-webkit-box-shadow", "0px 0px 10px red");
    }
    error.html(tokenData.msg);
});

//protocol判定
$("#protocol").blur(function () {
    if (!$("#protocol").is(":checked")) {
        check = Boolean(false);
        $("#register-protocolbox").css("-webkit-box-shadow", "0px 0px 10px red");
    } else {
        check = Boolean(true);
        $("#register-protocolbox").css("-webkit-box-shadow", "0px 0px 0px");
    }
});

function register() {
    //let url = location.search.substr(1).split("=")[1];
    if (!check) {
        alert("请勾选同意用户使用协议");
    } else if (!(name_flag && psw_flag && email_flag)) {
        alert("请完成必填项");
    } else {
        $.ajax({
            type: "POST",
            url: "/register",
            data: $("#register").serialize(),
            cache: false,
            success: function () {
                window.location.href="/profile";
            }
        })
    }
}

function login() {
    //let url = location.search.substr(1).split("=")[1];
    let token = $("#login-identity").val();
    let password = $("#login-password").val();
    if (token === "") {
        alert("登录名不能为空");
    } else if (password === "") {
        alert("密码不能为空");
    } else {
        $.ajax({
            type: "POST",
            url: "/login",
            data: $("#login").serialize(),
            dataType: "json",
            success: function (result) {
                console.log(result);
                if (result.status === "false") {
                    alert(result.msg);
                } else {
                    window.location.href="/";
                }
            }
        })
    }
}

$(document).keyup(function (event) {
    let display = $(".login_right").css('display');
    if (event.keyCode === 13) {
        if (display === "none") {
            $("#register-button").click();
        } else {
            $("#login-button").click();
        }
    }
});

$(document).ready(function () {
    let picli = $(".pic .img li");
    let img = $(".pic .img");
    let index = 0;
    let clone = picli.first().clone();
    img.append(clone);
    let size = picli.length;
    let list = $(".pic .num li");
    for (let j = 0; j < size; j++) {
        $(".pic .num").append("<li></li>")
    }

    list.first().addClass("on");

    let auto = setInterval(function () {
        index++;
        move();
    }, 2000);

    $(".pic").hover(function () {
        list.css("display", "inline-block");
        clearInterval(auto);
    }, function () {
        list.hide();
        auto = setInterval(function () {
            index++;
            move();
        }, 2000);
    });

    list.hover(function () {
        let thisIndex = $(this).index();
        index = thisIndex;
        img.stop().animate({left: -thisIndex * 200}, 500);
        $(this).addClass("on").siblings().removeClass("on");
    });

    $(".pic .btn-left").click(function () {
        index--;
        move();
    });

    $(".pic .btn-right").click(function () {
        index++;
        move();
    });

    function move() {
        if (index === size + 1) {
            img.css("left", 0);
            index = 1;
        }

        if (index === -1) {
            img.css("left", -(size) * 200);
            index = size - 1;
        }

        img.stop().animate({left: -index * 200}, 200);

        if (index === size) {
            list.eq(0).addClass("on").siblings().removeClass("on");
        } else {
            list.eq(index).addClass("on").siblings().removeClass("on");
        }
    }
});