<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/boot/css/back.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jqgrid/css/jquery-ui.css">
    <script src="${pageContext.request.contextPath}/boot/js/jquery-2.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/boot/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/jqgrid/js/trirand/src/jquery.jqGrid.js"></script>
    <script src="${pageContext.request.contextPath}/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script src="${pageContext.request.contextPath}/boot/js/ajaxfileupload.js"></script>
    <script src="${pageContext.request.contextPath}/kindeditor/kindeditor-all-min.js"></script>
    <script src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>

<script type="text/javascript">
    KindEditor.ready(function(K) {
        window.editor = K.create('#editor_id',
            {
                uploadJson : '${pageContext.request.contextPath}/article/upload',
                allowFileManager : true,
                fileManagerJson : '${pageContext.request.contextPath}/article/showAllImg',
                //width:'500px'
                afterBlur:function () {
                    //同步数据
                    this.sync();
                }
            });
    });

    $.ajax({
        url:"${pageContext.request.contextPath}/guru/queryAll",
        datatype:"json",
        type:"post",
        success:function (data) {
            var option="option value ="+0+">请选择</option>";
            data.forEach(function (guru) {
                option+="<option value ="+guru.id+">"+guru.name+"</option>"
            })
            $("#group_guru").html(option)
        }
    })

    function  sub() {
        $.ajaxFileUpload({
            url: "${pageContext.request.contextPath}/article/insert",
            type: "post",
            // ajaxFileUpload 不支持serialize() 格式化形式
            // 只支持{"id":1,XXX:XX}
            // 解决: 1. 手动封装  2. 更改ajaxFileUpload的源码

            // 异步提交时 无法传输修改后的kindeditor内容,需要刷新
            data: {
                "id": $("#id").val(),
                "title": $("#title").val(),
                "content": $("#editor_id").val(),
                "status": $("#status").val(),
                "guruId": $("#group_guru").val(),
                "createDate":$("#createDate").val(),
                "publishDate":$("#publishDate").val(),
            },
            datatype: "json",
            fileElementId:"inputFile",
            success:function (data) {
            }
        })
    }

</script>
</head>

<form class="form-horizontal" enctype="multipart/form-data" id="kindFrom">
    <%--  栅格系统row行  --%>
    <div class="row">
        <%--  form组  --%>
        <div class="form-group" hidden="hidden">
            <label class="control-label col-xs-2 col-xs-offset-1">ID</label>
            <div class="col-xs-6">
                <input type="text" class="form-control" name="id" id="id"/>
            </div>
        </div>
        <%--  form组  --%>
        <div class="form-group">
            <label class="control-label col-xs-2 col-xs-offset-1">标题</label>
            <div class="col-xs-6">
                <input type="text" id="title" class="form-control" name="title"/>
            </div>
        </div>
        <%--  form组  --%>
        <div class="form-group">
            <label class="control-label col-xs-2 col-xs-offset-1">图片</label>
            <div class="col-xs-6">
                <input type="file" class="form-control" id="inputFile" name="img11"/>
            </div>
        </div>
        <%--  form组  --%>
        <div class="form-group">
            <label class="control-label col-xs-2 col-xs-offset-1">内容</label>
            <div class="col-xs-6">
                                <textarea class="form-control" id="editor_id" name="content" style="width:700px;height:300px;">
                                    &lt;strong&gt;HTML内容&lt;/strong&gt;
                                </textarea>
            </div>
        </div>
        <%--  form组  --%>
        <div class="form-group">
            <label class="control-label col-xs-2 col-xs-offset-1">创作时间</label>
            <div class="col-xs-6">
                <input type="date" id="createDate" class="form-control" name="createDate"/>
            </div>
        </div>
        <%--  form组  --%>
        <div class="form-group">
            <label class="control-label col-xs-2 col-xs-offset-1">出版时间</label>
            <div class="col-xs-6">
                <input type="date" id="publishDate" class="form-control" name="publishDate"/>
            </div>
        </div>
        <%--  form组  --%>
        <div class="form-group">
            <label class="control-label col-xs-2 col-xs-offset-1">状态</label>
            <div class="col-xs-6">
                <select  class="form-control" id="status" name="status">
                    <option value ="1">展示</option>
                    <option value ="0">隐藏</option>
                </select>
            </div>
        </div>
        <%--  form组  --%>
        <div class="form-group">
            <label class="control-label col-xs-2 col-xs-offset-1">所属上师</label>
            <div class="col-xs-6">
                <select  class="form-control" id="group_guru" name="guruId">

                </select>
            </div>
        </div>
    </div>
        <button type="button" onclick="sub()" class="btn btn-default">提交</button>
</form>

