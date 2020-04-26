<%--
  Created by IntelliJ IDEA.
  User: Hurpods
  Date: 2020/4/24
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>500 Internal Server Error</title>

    <style>
        html, body {
            margin: 0;
            padding: 0;
        }

        canvas {
            display: block;
        }
    </style>
</head>
<body>
<div style="
    position: absolute;
    left: 38%;
    top: 45%;
    font-size: 50px;
    text-align: center;
    font-weight: bold;
">
    <span>500 Internal Server Error</span>
    <br/>
    <span>3秒后返回上一页</span>
</div>
</body>
<script>
    let canvas = document.createElement('canvas');
    let height = canvas.height = window.innerHeight;
    let width = canvas.width = window.innerWidth;
    let ctx = canvas.getContext('2d');
    document.body.appendChild(canvas);

    function random(min, max) {
        return Math.random() * (max - min + 1) + min;
    }

    function range_map(value, in_min, in_max, out_min, out_max) {
        return (value - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

    let word_arr = [];
    let txt_min_size = 10;
    let txt_max_size = 30;

    for (let i = 0; i < 25; i++) {
        word_arr.push({
            x: random(0, width),
            y: random(0, height),
            text: '500',
            size: random(txt_min_size, txt_max_size)
        });

        word_arr.push({
            x: random(0, width),
            y: random(0, height),
            text: 'Internal',
            size: random(txt_min_size, txt_max_size)
        });


        word_arr.push({
            x: random(0, width),
            y: random(0, height),
            text: 'Server Error',
            size: random(txt_min_size, txt_max_size)
        });

        word_arr.push({
            x: random(0, width),
            y: random(0, height),
            text: '500',
            size: Math.floor(random(txt_min_size, txt_max_size))
        });
    }

    function render() {
        ctx.fillStyle = "rgb(0,156,156)";
        ctx.fillRect(0, 0, width, height);

        ctx.fillStyle = "yellow";
        for (let i = 0; i < word_arr.length; i++) {
            ctx.font = word_arr[i].size + "px sans-serif";
            let w = ctx.measureText(word_arr[i].text);
            ctx.fillText(word_arr[i].text, word_arr[i].x, word_arr[i].y);


            word_arr[i].x += range_map(word_arr[i].size, txt_min_size, txt_max_size, 2, 3);


            if (word_arr[i].x >= width) {
                word_arr[i].x = -w.width * 2;
                word_arr[i].y = random(0, height);
                word_arr[i].size = Math.floor(random(txt_min_size, txt_max_size));
            }
        }
        ctx.fill();
        requestAnimationFrame(render);
    }
    render();
    window.onload = function(){
        setTimeout("history.back()", 3000);
    }
</script>
</html>
