<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    // $(function () {
        //   添加模态框定义
        // $("#addModal").modal({
        //     "keybord": false,
        //     "backdrop": true,
        //     "show": false
        // });
        //   添加模态框的保存按钮单击事件
        // $("#addEmployeeBtnSave").click(function () {
        //     $("#addModal").modal("hide");
        // });
        //  添加员工按钮单击事件
        // $("#addEmployeeBtn").click(function () {
        //     $("#addModal").modal("show");
        // });
    $(function () {
        $("#articleTable").jqGrid(
            {
                url: '${pageContext.request.contextPath}/article/queryByPage',
                datatype: "json",
                // 时间格式的处理在后台进行
                colNames: ['ID', '标题', '图片', '内容', '创建时间', '发布时间', '状态','所属上师','操作'],
                colModel: [
                    {name: 'id', align: "center", hidden: true},
                    {name: 'title', align: "center", editable: true, editrules: {required: true}},
                    {
                        name: 'img', align: "center", formatter: function (data) {
                            return "<img style='width: 180px;height: 80px' src='" + data + "'>"
                        }, editable: true, edittype: "file", editoptions: {enctype: "multipart/form-data"}
                    },
                    {name: 'content', align: "center", editable: true},
                    {
                        name: 'createDate',
                        align: "center",
                        editable: true,
                        editrules: {required: true},
                        edittype: "date"
                    },
                    {name: 'publishDate', align: "center", editable: true, editrules: {required: true}},
                    {
                        name: 'status',
                        align: "center",
                        formatter: function (data) {
                            if (data == "1") {
                                return "展示";
                            } else return "冻结";
                        },
                        editable: true,
                        editrules: {required: true},
                        edittype: "select",
                        editoptions: {value: "1:展示;2:冻结"}
                    },
                    {
                        name: 'guruId',
                        align: "center",
                    },
                    {
                        name:'option',
                        formatter:function (cellvalue, options, rowObject) {
                            var button = "<button type=\"button\" class=\"btn btn-primary\" onclick=\"update('"+rowObject.id+"')\">修改</button>&nbsp;&nbsp;";
                            button+= "<button type=\"button\" class=\"btn btn-danger\" onclick=\"del('"+rowObject.id+"')\">删除</button>";
                            return button;
                        }
                    }
                ],
                rowNum: 10,
                rowList: [10, 20, 30],
                pager: '#articlePage',
                sortname: 'id',
                mtype: "post",
                viewrecords: true,
                sortorder: "desc",
                caption: "文章信息",
                autowidth: true,
                multiselect: false,
                styleUI: "Bootstrap",
                height: "500px",

            });
    });

        //提交
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
                    $("#articleTable").trigger("reloadGrid");
                }
            })
            $("#addModal").modal("hide");
        }
    //添加方法
        function showArticle() {
            $("#kindFrom")[0].reset();
            KindEditor.html("#editor_id","");
            var option = "";
                option += "<option selected value=\"1\">展示</option>";
                option += "<option value=\"2\">冻结</option>";
            $("#status").html(option);
            $.ajax({
                url:"${pageContext.request.contextPath}/guru/queryAll",
                datatype:"json",
                type:"post",
                success:function (data) {
                    var option="<option value ="+0+">请选择</option>";
                    data.forEach(function (guru) {
                        option+="<option value ="+guru.id+">"+guru.name+"</option>"
                    })
                    $("#group_guru").html(option)
                }
            })

            $("#addModal").modal("show");

        }

        //删除
        function del(articleId) {
            $.ajax({
                url: "${pageContext.request.contextPath}/article/remove",
                data:{articleId:articleId},
                datatype: "json",
                type: "post",
                success: function (data) {
                    $("#articleTable").trigger("reloadGrid");
                }
            });
        }

    //修改方法
    function update(id) {
        var data=$("#articleTable").jqGrid("getRowData",id);
        $("#id").val(data.id);
        $("#title").val(data.title);
        $("#createDate").val(data.createDate);
        $("#publishDate").val(data.publishDate);
        // 更替KindEditor 中的数据使用KindEditor.html("#editor_id",data.content) 做数据替换
        KindEditor.html("#editor_id",data.content);
        // 处理状态信息
        $("#status").val(data.status);
        var option = "";
        if(data.status=="展示"){
            option += "<option selected value=\"1\">展示</option>";
            option += "<option value=\"2\">冻结</option>";
        }else{
            option += "<option value=\"1\">展示</option>";
            option += "<option selected value=\"2\">冻结</option>";
        }
        $("#status").html(option);

        $.ajax({
            url: "${pageContext.request.contextPath}/guru/queryAll",
            datatype: "json",
            type: "post",
            success: function (gurulist) {
                // 遍历方法 --> forEach(function(集合中的每一个对象){处理})
                // 一定将局部遍历声明在外部
                var option2 = "<option value=\"0\">请选择所属上师</option>";
                gurulist.forEach(function (guru) {
                    if (guru.id==data.guruId){
                        option2 += "<option selected value=" + guru.id + ">" + guru.name + "</option>"
                    }
                    option2 += "<option value=" + guru.id + ">" + guru.name + "</option>"
                })
                $("#group_guru").html(option2);
            }
        });
        $("#addModal").modal("show");
    }
    // });
</script>
<%--  标签页  --%>
<ul class="nav nav-tabs">
    <li class="active"><a href="#list" data-toggle="tab">文章列表</a></li>
    <li><a id="addEmployeeBtn" onclick="showArticle()">添加文章</a></li>
</ul>
<div class="panel">
    <table id="articleTable"></table>
    <div id="articlePage" style="height: 50px"></div>
</div>

