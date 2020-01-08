<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    $(function () {
        //   添加模态框定义
        $("#addModal").modal({
            "keybord": false,
            "backdrop": true,
            "show": false
        });
        //   添加模态框的保存按钮单击事件
        $("#addEmployeeBtnSave").click(function () {
            $("#addModal").modal("hide");
        });
        //  添加员工按钮单击事件
        $("#addEmployeeBtn").click(function () {
            $("#addModal").modal("show");
        });

    });
</script>
<%--  标签页  --%>
<ul class="nav nav-tabs">
    <li class="active"><a href="#list" data-toggle="tab">文章列表</a></li>
    <li><a id="addEmployeeBtn">添加文章</a></li>
</ul>
<div class="tab-content">
    <div class="tab-pane active" id="list">
        <%--&lt;%&ndash;  面板  &ndash;%&gt;
        <div class="panel panel-default">
            <div class="panel-body text-center">
                <form class="form-inline">
                    <div class="form-group">
                        <label class="control-label">姓名：</label>
                        <input type="text" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label class="control-label">类型：</label>
                        <select class="form-control">
                            <option>请选择</option>
                        </select>
                    </div>
                    <button type="button" class="btn btn-default btn-primary">查询</button>
                </form>
            </div>
        </div>--%>
        <%--  信息列表面板  --%>
        <div class="panel panel-default">
            <div class="panel-heading">文章信息列表</div>

            <table class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th class="text-center">ID</th>
                    <th class="text-center">客户名称</th>
                    <th class="text-center">客户来源</th>
                    <th class="text-center">客户所属行业</th>
                    <th class="text-center">客户级别</th>
                    <th class="text-center">固定电话</th>
                    <th class="text-center">手机</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td>小黑</td>
                    <td>阿里</td>
                    <td>互联网</td>
                    <td>普通客户</td>
                    <td>047900020</td>
                    <td>13434343434</td>
                    <td>
                        <button type="button" class="btn btn-default btn-primary">修改</button>
                        <button type="button" class="btn btn-default btn-danger" id="deleteEmployeeBtn">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>小黑</td>
                    <td>阿里</td>
                    <td>互联网</td>
                    <td>普通客户</td>
                    <td>047900020</td>
                    <td>13434343434</td>
                    <td>
                        <button type="button" class="btn btn-default btn-primary">修改</button>
                        <button type="button" class="btn btn-default btn-danger">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>小黑</td>
                    <td>阿里</td>
                    <td>互联网</td>
                    <td>普通客户</td>
                    <td>047900020</td>
                    <td>13434343434</td>
                    <td>
                        <button type="button" class="btn btn-default btn-primary">修改</button>
                        <button type="button" class="btn btn-default btn-danger">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>小黑</td>
                    <td>阿里</td>
                    <td>互联网</td>
                    <td>普通客户</td>
                    <td>047900020</td>
                    <td>13434343434</td>
                    <td>
                        <button type="button" class="btn btn-default btn-primary">修改</button>
                        <button type="button" class="btn btn-default btn-danger">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>小黑</td>
                    <td>阿里</td>
                    <td>互联网</td>
                    <td>普通客户</td>
                    <td>047900020</td>
                    <td>13434343434</td>
                    <td>
                        <button type="button" class="btn btn-default btn-primary">修改</button>
                        <button type="button" class="btn btn-default btn-danger">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>小黑</td>
                    <td>阿里</td>
                    <td>互联网</td>
                    <td>普通客户</td>
                    <td>047900020</td>
                    <td>13434343434</td>
                    <td>
                        <button type="button" class="btn btn-default btn-primary">修改</button>
                        <button type="button" class="btn btn-default btn-danger">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>小黑</td>
                    <td>阿里</td>
                    <td>互联网</td>
                    <td>普通客户</td>
                    <td>047900020</td>
                    <td>13434343434</td>
                    <td>
                        <button type="button" class="btn btn-default btn-primary">修改</button>
                        <button type="button" class="btn btn-default btn-danger">删除</button>
                    </td>
                </tr>
                </tbody>
            </table>

        </div>
        <%--  分页  --%>
        <div align="right">
            <ul class="pagination">
                <li><a href="">上一页</a></li>
                <li class="active"><a href="">1</a></li>
                <li><a href="">2</a></li>
                <li><a href="">3</a></li>
                <li><a href="">4</a></li>
                <li><a href="">5</a></li>
                <li><a href="">上一页</a></li>
            </ul>
        </div>

    </div>
</div>

<%--  添加模态框  --%>
<div class="modal fade" tabindex="-1" id="addModal">
    <%--  modal-dialog  --%>
    <div class="modal-dialog">
        <%--  modal-content  --%>
        <div class="modal-content">

            <%--  头  --%>
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <div class="modal-title"><h4>添加文章</h4></div>
            </div>
        <%--<form action="" style="" enctype="multipart/form-data">--%>
            <%--  内容  --%>
            <div class="modal-body">
                <%--  forom表单  --%>
                <form class="form-horizontal" enctype="multipart/form-data">
                    <%--  栅格系统row行  --%>
                    <div class="row">
                        <%--  form组  --%>
                        <div class="form-group">
                            <label class="control-label col-xs-2 col-xs-offset-1">标题</label>
                            <div class="col-xs-6">
                                <input type="text" class="form-control"/>
                            </div>
                        </div>
                        <%--  form组  --%>
                        <div class="form-group">
                            <label class="control-label col-xs-2 col-xs-offset-1">图片</label>
                            <div class="col-xs-6">
                                <input type="file" class="form-control"/>
                            </div>
                        </div>
                        <%--  form组  --%>
                        <div class="form-group">
                            <label class="control-label col-xs-2 col-xs-offset-1">内容</label>
                            <div class="col-xs-6">
                                <textarea  class="form-control"></textarea>
                            </div>
                        </div>
                        <%--  form组  --%>
                        <div class="form-group">
                            <label class="control-label col-xs-2 col-xs-offset-1">创作时间</label>
                            <div class="col-xs-6">
                                <input type="date" class="form-control"/>
                            </div>
                        </div>
                        <%--  form组  --%>
                        <div class="form-group">
                            <label class="control-label col-xs-2 col-xs-offset-1">出版时间</label>
                            <div class="col-xs-6">
                                <input type="date" class="form-control"/>
                            </div>
                        </div>
                        <%--  form组  --%>
                        <div class="form-group">
                            <label class="control-label col-xs-2 col-xs-offset-1">状态</label>
                            <div class="col-xs-6">
                                <select  class="form-control">
                                    <option value ="volvo">0</option>
                                    <option value ="volvo">1</option>
                                </select>
                            </div>
                        </div>
                        <%--  form组  --%>
                        <div class="form-group">
                            <label class="control-label col-xs-2 col-xs-offset-1">所属上师</label>
                            <div class="col-xs-6">
                                <select  class="form-control">
                                    <option value ="volvo">0</option>
                                    <option value ="volvo">1</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <%--  脚  --%>
            <div class="modal-footer">
                <button class="btn btn-primary" id="addEmployeeBtnSave">添加</button>
                <button class="btn btn-default" data-dismiss="modal">取消</button>
            </div>

        <%--</form>--%>
        </div>
    </div>
</div>