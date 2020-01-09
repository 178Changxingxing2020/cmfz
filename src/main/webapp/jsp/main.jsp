<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
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
    <script src="../echarts/echarts.min.js"></script>
    <script type="text/javascript" src="../echarts/china.js" charset="UTF-8"></script>
    <!-- 将https协议改为http协议 -->
    <script type="text/javascript" src="http://cdn.goeasy.io/goeasy-1.0.3.js"></script>
    <script type="text/javascript">
        KindEditor.ready(function(K) {
            window.editor = K.create('#editor_id',
                {
                    uploadJson : '${pageContext.request.contextPath}/article/upload',
                    allowFileManager : true,
                    fileManagerJson : '${pageContext.request.contextPath}/article/showAllImg',
                    width:'500px',
                    afterBlur:function () {
                        //同步数据
                        this.sync();
                    }
                });
        });
    </script>
</head>
<body>
<!-- 导航栏 -->
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">持名法洲后台管理系统</a>
        </div>
        <div>
            <!--向右对齐-->
            <ul class="nav navbar-nav navbar-right">
                <li><a>${admin.username}</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/exit">退出登陆</a></li>
            </ul>
        </div>
    </div>
</nav>
<!-- 栅格系统 -->
<div class="container-fluid">
    <div class="row">
        <!-- 手风琴 -->
        <div class="col-xs-2">
            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseOne">
                                用户模块
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav">
                                <li><a>用户管理</a></li>
                                <li><a href="javascript:$('#centerLay').load('./echarts.jsp')">用户活跃度分析</a></li>
                                <li><a href="javascript:$('#centerLay').load('./map.jsp')">用户地区分布</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseTwo">
                                 专辑模块
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav">
                                <li><a href="javascript:$('#centerLay').load('./subgrid.jsp')">专辑管理</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseThree">
                                文章模块
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav">
                                <li><a href="javascript:$('#centerLay').load('./article2.jsp')">文章管理</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFour">
                                上师模块
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav">
                                <li><a>上师管理</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFive">
                                轮播图模块
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav">
                                <li><a href="javascript:$('#centerLay').load('./banner.jsp')">轮播图管理</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseSix">
                                聊天模块
                            </a>
                        </h4>
                    </div>
                    <div id="collapseSix" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav">
                                <li><a href="javascript:$('#centerLay').load('./chat.jsp')">聊天室</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-10" id="centerLay">
            <div class="jumbotron" >
                <div class="container">
                    <h3>欢迎使用持名法洲系统！</h3>
                </div>
                <div id="myCarousel" class="carousel slide">
                    <!-- 轮播（Carousel）指标 -->
                    <ol class="carousel-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li>
                    </ol>
                    <!-- 轮播（Carousel）项目 -->
                    <div class="carousel-inner">
                        <div class="item active">
                            <img src="${pageContext.request.contextPath}/image1/1.jpg" alt="First slide">
                            <div class="carousel-caption">标题 1</div>
                        </div>
                        <div class="item">
                            <img src="${pageContext.request.contextPath}/image1/2.jpg" alt="Second slide">
                            <div class="carousel-caption">标题 2</div>
                        </div>
                        <div class="item">
                            <img src="${pageContext.request.contextPath}/image1/3.jpg" alt="Third slide">
                            <div class="carousel-caption">标题 3</div>
                        </div>
                    </div>
                    <!-- 轮播（Carousel）导航 -->
                    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="panel-footer">
    <h4 style="text-align: center">@百知教育 @baizhiedu.com.cn</h4>
</div>
<%--  添加模态框  --%>
<div class="modal fade"  id="addModal">
    <%--  modal-dialog  --%>
    <div class="modal-dialog modal-lg">
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
                <form class="form-horizontal" enctype="multipart/form-data" id="kindFrom">
                    <%--  栅格系统row行  --%>
                    <div class="row">
                        <%--  form组  --%>
                        <div class="form-group" >
                            <div class="col-xs-6">
                                <input type="hidden" class="form-control" name="id" id="id"/>
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
                                <input type="file" class="form-control" id="inputFile" name="inputFile"/>
                            </div>
                        </div>
                        <%--  form组  --%>
                        <div class="form-group">
                            <label class="control-label col-xs-2 col-xs-offset-1">内容</label>
                            <div class="col-xs-6">
                                <textarea id="editor_id" name="content" style="width:700px;height:300px;">
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
                                    <%--<option value ="1">展示</option>
                                    <option value ="0">冻结</option>--%>
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
                </form>
            </div>
            <%--  脚  --%>
            <div class="modal-footer">
                <button class="btn btn-primary" id="addEmployeeBtnSave" onclick="sub()">添加</button>
                <button class="btn btn-default" data-dismiss="modal">取消</button>
            </div>

            <%--</form>--%>
        </div>
    </div>
</div>

<%--  导入模态框  --%>
<div class="modal fade"  id="inBannerModal">
    <%--  modal-dialog  --%>
    <div class="modal-dialog modal-lg">
        <%--  modal-content  --%>
        <div class="modal-content">

            <%--  头  --%>
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <div class="modal-title"><h4>导入文件</h4></div>
            </div>
            <%--<form action="" style="" enctype="multipart/form-data">--%>
            <%--  内容  --%>
            <div class="modal-body">
                <%--  forom表单  --%>
                <form class="form-horizontal" enctype="multipart/form-data" id="inBannerFrom">
                    <%--  栅格系统row行  --%>
                    <div class="row">

                        <%--  form组  --%>
                        <div class="form-group">
                            <label class="control-label col-xs-2 col-xs-offset-1">文件</label>
                            <div class="col-xs-6">
                                <input type="file" class="form-control" id="file" name="file"/>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <%--  脚  --%>
            <div class="modal-footer">
                <button class="btn btn-primary" id="BannerOk" onclick="addBannerExcel()">添加</button>
                <button class="btn btn-default" data-dismiss="modal">取消</button>
            </div>

            <%--</form>--%>
        </div>
    </div>
</div>

</body>
</html>