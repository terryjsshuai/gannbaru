<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = "//" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <title>验证码输入</title>
    <style>
        body {
            width: 80%;
            background-color: #81d4fa;
        }
        div

        .radius6 {
            border: none;
            border-radius: 30rem !important;
        }

        .sizeB {
            font-size: 1rem;
            width: 3.125rem;
            height: 3.125rem;
            margin-right: 0.375rem;
            margin-top: 0.375rem;
            cursor: pointer;
        }

        .button1 {
            background-color: #4CAF50;
        }

        .button3 {
            background-color: #f44336;
        }
    </style>
    <script src="../js/jQuery3.3/jquery-3.3.1.min.js"></script>
    <script src="../js/jquery.mobile-1.4.5.min.js"></script>
    <script type="text/javascript">
        var websocket = null;
        var result = "";

        function connect() {
            var userid = $("#userId").val();

            //判断当前浏览器是否支持WebSocket
            if ('WebSocket' in window) {
                var wsServer = "ws:<%=basePath%>ws";
                //alert(wsServer);
                websocket = new WebSocket(wsServer);
                //连接发生错误的回调方法
                websocket.onerror = function () {
                    showMessage("WebSocket连接发生错误");
                };
                //连接成功建立的回调方法
                websocket.onopen = function () {
                    showMessage("连接服务器成功，已上线");
                }
                //接收到消息的回调方法
                websocket.onmessage = function (event) {
                    //显示验证码信息
                    showCaptcha($.parseJSON(event.data));
                }
                //连接关闭的回调方法
                websocket.onclose = function () {
                    showMessage("断开与服务器连接，已下线");
                }
            } else {
                alert('当前浏览器不支持websocket')
            }
        }

        //将消息显示在网页上
        function showMessage(innerHTML) {
            //document.getElementById('message').innerHTML += innerHTML + '<br/>';
            $("#message").text("");
            $("#message").append(innerHTML);
        }

        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function () {
            closeWebSocket();
        }

        //关闭WebSocket连接
        function closeWebSocket() {
            websocket.close();
        }

        function gettimestamp() {
            return Math.round(new Date().getTime()).toString()
        }

        //发送消息
        function send() {
            var resultMap = new Map();
            var timestamp = gettimestamp();
            var o = {
                userId: $("#userId").val(),
                imgKey: $("#imgKey").val(),
                captValue: $("#captValue").val(),
                timeStamp: timestamp
            };
            var message = JSON.stringify(o)
            websocket.send(message);
            $("#captValue").val("");
            showMessage("发送数据成功");
        }

        //刷新页面上线
        function reload() {
            location.reload();
        }

        function initButton() {

            var clickEventType = ((document.ontouchstart !== null) ? 'click' : 'touchstart');
            $("input[id^=numberBtn]").bind(clickEventType, function (e) {
                $("#captValue").val($("#captValue").val() + $(this).val());
            });

            $("input[id='clearBtn']").click(function () {
                $("#captValue").val("");
            });

            $("input[id='confirmBtn']").click(function () {
                send();
            });
        }

        function showCaptcha(jsonObj) {
            $("#imgKey").val(jsonObj.imgKey);
            $("#imgNum").attr("src", jsonObj.imgBase64Str);
            $("#prompt").text(jsonObj.prompt);
            //...................

        }

        $(document).ready(function () {
            initButton();
            connect();


        });
        /*
        function testJosnData() {
            var jsonObjTest = {
                imgKey: "imgKey--111",
                imgBase64Str: "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHIAAAAhCAYAAAAFx/8kAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAANvSURBVGhD7ZTBcRsxDEXVRA5uIxXklHpcgiYzObmD1JEeckpNCj7CzwGhT5qy1pbk5Zv5412Qu4bxlj6cDofTKIeXF1lnfv39KuvM9x+/ZZ15enqWdebPl5+yzhyPR1ln9tL/f5ED/EUD/EUDfBADfBADfBADfBADdtH/ErlEVqYHcbTfI3KvIr/ZexH0z2sV9J9rkfsSKQQw/iLeC1xk2J/jInHdoRlEehbxQeC6QzOI9CxS+09QylYi8x7mcUWScl9FxrWAD4LrIlUkIqiDCM/ETPU/YEZkXo+5nciEv6iH7W9EZqzWiBR7pMhAIzKtAfkhBhqRaQ1cKjKC+yyS8B79x3rmMUQaF4tMnIlMe6b6j+tpz5YiM6hRpFoHF4nE5rcGL1J1BoNQdQaDUHUGg1B1BoNQ9dl8mv6HJ9K+Tmzqfa3AvwhF2V9PZIezExn32nVzIgV+Iv0iPM+99rPpX1D75560d3QicYriiYywxhPZI55Itc/7H+D9v/qv1f6g6UFkyjNL5D2KFNmrSA6eIjNcfyyRuM6U9SXyXkWScl9FxjVQalVkXi8MRRqNyLQGXGRcj/vs561EAtQpUu1B7eNFKqzeFQmstrnItH4LkXHw3n8HrEeRCOH9x4g0pl4kBuDYfSNSrL9JZMg9iwRZZM6mInHTC16k6rPBIFSdwSBUncEgVJ3BIFSd2Uv/73sijfpFxz0hUydygJ9IvxDvsJ/ef6wlvH/APWlvPpHx9CAzJ3LEpidyxGYiO2wisve8XS+R5Q+qg+iwuciI3fsg1FphKNJoRKY1cNZ/2hP7jwPn0K8RiXdEkYrbiyz3PohYT3xWkdyD/rk3g/r7iwT2RzWDyFitGUSk1K4SaTQi87rdn4lMnIlMe7ofYuG9RHL9NiLjH1ruuyKB1arIznojUnAmMqURKaiDCM/EDPs3KJLDzkPviQTYR5HxGd4jFNljG5GG/KJD6iAUVm9Exn3lfkok4J4UFzmgGYR4fti/cY1IEEWqXNS/oBGJZnvBi1SdwYtUfTYYhKozGISqMxiEqjN76X/uRA7wL2IAfomTTgIz80WP2OSLHoD+8ymK3EX/0/9aB0yL7PAIIkcskYUlcol0lsgl0lkiC0vkEukskRuKRDO94EWqzuBFqs5gEKo+GwxC1RkMQtWZffT/fPoHWfU783IAAVkAAAAASUVORK5CYII=",
                prompt: "请输入香蕉行！",
                value: ""
            };
            initButton(jsonObjTest);
        }
        */
    </script>
</head>
<body class="body">

<div id="webDiv" align="center">
    <BR>
    <BR> <input type="hidden" id="userId" value='<%=request.getAttribute("userId") %>' style="float:center; width:99%; border:1px"/>
    <BR> <input type="hidden" id="imgKey" value=""/>
    <BR> <img id="imgNum" src="">
    <div id="prompt" style="margin-top: 5px; font-size:16px; color:blue"></div>
    <BR>
    <input type="text" id="captValue" value="" style="width: 180px; height: 30px; font-size: 20px;"/>
    <BR>
    <input type="button" id="numberBtn1" class="radius6 sizeB " value="1"/>
    <input type="button" id="numberBtn2" class="radius6 sizeB " value="2"/>
    <input type="button" id="numberBtn3" class="radius6 sizeB " value="3"/><br>
    <input type="button" id="numberBtn4" class="radius6 sizeB " value="4"/>
    <input type="button" id="numberBtn5" class="radius6 sizeB " value="5"/>
    <input type="button" id="numberBtn6" class="radius6 sizeB " value="6"/><br>
    <input type="button" id="numberBtn7" class="radius6 sizeB " value="7"/>
    <input type="button" id="numberBtn8" class="radius6 sizeB " value="8"/>
    <input type="button" id="numberBtn9" class="radius6 sizeB " value="9"/><br>
    <input type="button" id="clearBtn" class="radius6 sizeB button3" value="清除"/>
    <input type="button" id="numberBtn0" class="radius6 sizeB " value="0"/>
    <input type="button" id="confirmBtn" class="radius6 sizeB button1" value="确定"/><br>
</div>

<BR>
<div id="message" align="center"></div>
</body>
</html>