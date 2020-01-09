<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<%--<div id="main" style="width: 1000px;height:400px;"></div>--%>
<%--  form组  --%>

聊天栏
<div  id="title" name="title" style="height: 500px;border: solid 1px"></div>
发送栏

<input type="text" id="word" name="word"/>
<button type="button" name="btn" id="btn">发送</button>


<script type="text/javascript">



    var goEasy = new GoEasy({
        host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
        appkey: "BC-0ba8670ad5894f69b4ee326915c50e93", //替换为您的应用appkey
    });
    $(function () {
        $("#btn").click(function () {
            var word=$("#word").val();
            goEasy.publish({
                channel: "cmfz", //替换为您自己的channel
                message: word, //替换为您想要发送的消息内容
            });

        })
    });


    goEasy.subscribe({
        channel: "cmfz", //替换为您自己的channel
        onMessage: function (message) {
            console.log(message.content);
            $("#title").append(message.content+"<br/>");
        }
    });
</script>
