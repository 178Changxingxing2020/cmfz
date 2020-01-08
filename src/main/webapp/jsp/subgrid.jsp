<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


    <script type="text/javascript">
        $(function(){
            pageInit();
        });
        function pageInit(){
            $("#ftable").jqGrid(
                {
                    url : "${pageContext.request.contextPath}/album/queryByPage",
                    datatype : "json",
                    height : 500,
                    colNames : [ 'id', 'title', 'url', 'score', 'author','broadcast', 'count','description','status','create_date' ],
                    colModel : [
                        {name : 'id',index : 'id',hidden:true},
                        {name : 'title',width : 90,editable:true},
                        {name : 'url',width : 100,editable:true,edittype:"file",editoptions:{enctype:'multipart/form-data'},
                            formatter:function (data) {
                                return "<img width='120px' src='"+data+"'>"
                            }
                        },
                        {name : 'score',width : 80,editable:true},
                        {name : 'author',width : 80,editable:true},
                        {name : 'broadcast',width : 80,editable:true},
                        {name : 'count',width : 80,editable:true},
                        {name : 'description',width : 150,editable:true},
                        {name : 'status',width : 80,editable:true,editrules: {required:true},
                            formatter:function (data) {
                                if(data=="1"){
                                    return "展示";
                                }else{
                                    return "冻结";
                                }
                            },edittype: "select",editoptions: {value:"1:展示;0:冻结"}
                        },
                        {name : 'createDate',width : 150,editable:true,editrules: {required:true},edittype:"date"}
                    ],
                    rowNum : 5,
                    rowList : [ 8, 10, 20, 30 ],
                    pager : '#fpage',
                    viewrecords : true,
                    sortname : 'id',
                    sortorder : "desc",
                    multiselect : false,
                    autowidth:true,
                    styleUI:"Bootstrap",
                    editurl:"${pageContext.request.contextPath}/album/modify",
                    //开启子表格
                    subGrid : true,
                    subGridRowExpanded : function(subgrid_id, row_id) {
                        addSubgrid(subgrid_id, row_id);
                    },
                    subGridRowColapsed : function(subgrid_id, row_id) {
                    }
                }).jqGrid("navGrid","#fpage",{edit:true,add:true,del:true},
                {closeAfterEdit:true,

                    afterSubmit:function (response,postData) {
                        var albumId=response.responseJSON.albumId;

                        $.ajaxFileUpload({
                            url: "${pageContext.request.contextPath}/album/upload",
                            type:"post",
                            datatype:"json",
                            data:{albumId:albumId},
                            fileElementId:"url",
                            success:function (data) {
                                $("#ftable").trigger("reloadGrid");

                            }
                        })
                        return postData;

                    }
                },

                {closeAfterAdd:true,
                    afterSubmit:function (response,postData) {
                        var albumId=response.responseJSON.albumId;

                        $.ajaxFileUpload({
                            url: "${pageContext.request.contextPath}/album/upload",
                            type:"post",
                            datatype:"json",
                            data:{albumId:albumId},
                            fileElementId:"url",
                            success:function (data) {
                                $("#ftable").trigger("reloadGrid");
                            }
                        })
                        return postData;

                    }
                },

                {closeAfterDel:true}
            );
            // jQuery("#ftable").jqGrid('navGrid', '#pagersg11', {
            //     add : false,
            //     edit : false,
            //     del : false
            // });
        }
        
        //subgrid_id 父行级id
        function addSubgrid(subgrid_id, row_id) {
            //子表格id
            var sid=subgrid_id+"table";
            //子表格工具栏id
            var spage=row_id+"page";

            $("#"+subgrid_id).html("<table id='"+sid+"' class='scroll'></table><div id='"+spage+"' class='scroll'></div>");

            $("#" + sid).jqGrid(
                {
                    url : "${pageContext.request.contextPath}/chapter/queryByPage?albumId=" + row_id,
                    datatype : "json",
                    colNames : [ 'id', '标题', '大小','时长' ,'创建时间','album_id','操作' ],
                    colModel : [
                        {name : "id",width : 70,key : true,hidden:true},
                        {name : "title",width : 70,editable:true},
                        {name : "size",width : 70},
                        {name : "time",width : 70},
                        {name : "createTime",width : 70,editable:true,editrules: {required:true},edittype:"date"},
                        {name : "albumId",width : 70,hidden:true},
                        {name : "url",width : 70,editable:true,edittype:"file",editoptions:{enctype:'multipart/form-data'},
                            formatter:function (cellvalue, options, rowObject) {
                                var button="<button type=\"button\" class=\"btn btn-primary\" onclick=\"onDownload('"+cellvalue+"')\">下载</button>&nbsp;&nbsp;&nbsp;";
                                button+="<button type=\"button\" class=\"btn btn-danger\" onclick=\"onPlay('"+cellvalue+"')\">在线播放</button>";
                                return button;
                            }
                        }
                    ],
                    rowNum : 5,
                    pager : '#'+spage,
                    height : 200,
                    autowidth: true,
                    multiselect:true,
                    styleUI: "Bootstrap",
                    editurl:"${pageContext.request.contextPath}/chapter/modify?albumId=" + row_id,

                }).jqGrid("navGrid","#"+spage,{edit:true,add:true,del:true},
                {closeAfterEdit:true,

                    afterSubmit:function (response,postData) {
                        var chapterId=response.responseJSON.chapterId;

                        $.ajaxFileUpload({
                            url: "${pageContext.request.contextPath}/chapter/upload",
                            type:"post",
                            datatype:"json",
                            data:{chapterId:chapterId},
                            fileElementId:"url",
                            success:function (data) {
                                $("#"+sid).trigger("reloadGrid");

                            }
                        })
                        return postData;

                    }
                },

                {closeAfterAdd:true,
                    afterSubmit:function (response,postData) {
                        var chapterId=response.responseJSON.chapterId;

                        $.ajaxFileUpload({
                            url: "${pageContext.request.contextPath}/chapter/upload",
                            type:"post",
                            datatype:"json",
                            data:{chapterId:chapterId},
                            fileElementId:"url",
                            success:function (data) {
                                $("#"+sid).trigger("reloadGrid");
                            }
                        })
                        return postData;

                    }
                },

                {closeAfterDel:true}
            );
        }

        function onPlay(cellvalue) {
            $("#music").attr("src",cellvalue);
            $("#myModal").modal("show");
        }

        function onDownload(cellvalue){
            location.href="${pageContext.request.contextPath}/chapter/download?url="+cellvalue;
        }
    </script>
</head>

<body>
<table id="ftable"></table>
<div id="fpage" style="height: 50px"></div>


<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <audio  id="music" src="" controls="controls">
            您的浏览器不支持 audio 标签。
        </audio>
    </div><!-- /.modal -->
</div>
</body>