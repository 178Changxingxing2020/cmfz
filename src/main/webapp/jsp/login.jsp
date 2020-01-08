<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>love</title>
    <link href="favicon.ico" rel="shortcut icon" />
    <link href="${pageContext.request.contextPath}/boot/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/boot/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript">

        <%--function changeImage(){--%>
        <%--    $("#imgVcode").prop("src","${pageContext.request.contextPath}/admin/checkCode?code="+Math.random());--%>
        <%--}--%>

       $(function () {

           $("#imgVcode").click(function () {
               $("#imgVcode").prop("src","${pageContext.request.contextPath}/admin/checkCode?code="+Math.random());
           })

           $("#log").click(function () {
               $.post({
                   url:"${pageContext.request.contextPath}/admin/login",
                   data:"username="+$("#name1").val()+"&password="+$("#password1").val()+"&checkCode="+$("#checkCode1").val(),
                   success:function (data) {
                       $("#msg").text(data)
                       if(data=="登陆成功"){
                           location.href="${pageContext.request.contextPath}/jsp/main.jsp"
                       }
                   },
                   dataType:"json"
               })
           })


       })


    </script>
</head>
<body style="  background-size: 100%;">


<div class="modal-dialog" style="margin-top: 10%;">
    <div class="modal-content">
        <div class="modal-header">

            <h4 class="modal-title text-center" id="myModalLabel">持明法洲</h4>
        </div>
        <div><span id="message1"></span></div>
        <div class="modal-body" id = "model-body">
            <div class="form-group">
                <input type="text" id="name1" class="form-control"placeholder="用户名" autocomplete="off" name="username">
            </div>
            <div class="form-group">
                <input type="password" id="password1" class="form-control" placeholder="密码" autocomplete="off" name="password">
            </div>
            <div class="Captcha-imageConatiner">
                <input type="text" name="checkCode" id="checkCode1"/>
                <a class="code_pic" id="vcodeImgWrap" name="change_code_img" href="javascript:void(0);">
                    <img id="imgVcode" src="${pageContext.request.contextPath}/admin/checkCode" class="Ucc_captcha Captcha-image" >
                </a>

                <span id="spn_vcode_ok" class="icon_yes pin_i" style="display: none;"></span>
                <span id="J_tipVcode" class="cue warn"></span>
            </div>
            <span id="msg" style="color: red"></span>
        </div>
        <div class="modal-footer">
            <div class="form-group">
                <button type="button" class="btn btn-primary form-control" id="log">登录</button>
            </div>
            <div class="form-group">
                <button type="button" class="btn btn-default form-control">注册</button>
            </div>

        </div>

    </div>
</div>
</body>
</html>
