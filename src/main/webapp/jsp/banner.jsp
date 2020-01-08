<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    $(function(){
        pageInit();
    });
    function pageInit(){
        $("#bannerTable").jqGrid(
            {
                url : "${pageContext.request.contextPath}/banner/queryByPage",
                datatype : "json",
                autowidth:true,
                colNames : [ 'ID', 'Title', 'Url', 'Href', 'CreateDate','Description', 'status'],
                colModel : [
                    {name : 'id',index : 'id',width : 55,hidden:true},
                    {name : 'title',width : 90,editable:true,editrules:{required:true}},
                    {name : 'url',width : 100,editable:true,edittype:"file",editoptions:{enctype:'multipart/form-data'},
                            formatter:function (data) {
                                return "<img width='120px' src='"+data+"'>"
                            }
                    },
                    {name : 'href',width : 100,editable:true},
                    {name : 'createDate',width : 100,editable:true,editrules: {required:true},edittype:"date"},
                    {name : 'description',width : 100,editable:true},
                    {name : 'status',width : 80,editable:true,editrules: {required:true},
                        formatter:function (data) {
                            if(data=="1"){
                                return "展示";
                            }else{
                                return "冻结";
                            }
                        },edittype: "select",editoptions: {value:"1:展示;0:冻结"}
                    }
                ],
                rowNum : 5,
                rowList : [ 10, 20, 30 ],
                pager : '#bannerPage',
                sortname : 'id',
                height: "500px",
                mtype : "post",
                viewrecords : true,
                sortorder : "desc",
                caption : "轮播图信息",
                multiselect:true,
                styleUI:"Bootstrap",
                editurl:"${pageContext.request.contextPath}/banner/modify"
            }).jqGrid("navGrid","#bannerPage",{edit:true,add:true,del:true,reflush:true},
            {closeAfterEdit:true,
                afterSubmit:function (response,postData) {
                    var bannerId=response.responseJSON.bannerId;
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/banner/upload",
                        type:"post",
                        dataType:"json",
                        data:{bannerId:bannerId},
                        fileElementId:"url",
                        success:function (data) {
                            $("#bannerTable").trigger("reloadGrid");
                        }
                    });
                    return postData;
                }
            },
            {closeAfterAdd:true,
            afterSubmit:function (response,postData) {
                var bannerId=response.responseJSON.bannerId;
                $.ajaxFileUpload({
                    url: "${pageContext.request.contextPath}/banner/upload",
                    type:"post",
                    datatype:"json",
                    data:{bannerId:bannerId},
                    fileElementId:"url",
                    success:function (data) {
                        $("#bannerTable").trigger("reloadGrid");
                    }
                });
                return postData;
            }
            },
            {closeAfterDel:true}
            );
        //uery("#bannerTable").jqGrid('navGrid', '#bannerPage', {edit : false,add : false,del : false});

        $("#outBanner").click(function () {
           location.href="${pageContext.request.contextPath}/banner/outBanner"
        })
        $("#inBanner").click(function () {
            $("#inBannerModal").modal("show");
            $("#inBannerFrom")[0].reset();
            //location.href="${pageContext.request.contextPath}/banner/inBanner"
        })

    }

    function addBannerExcel() {
        $.ajaxFileUpload({
            url:"${pageContext.request.contextPath}/banner/inBanner",
            datatype: "json",
            type:"post",
            fileElementId:"file",
            success:function (data) {
                $("#articleTable").trigger("reloadGrid");
            }
        })
        $("#inBannerModal").modal("hide");
    }



</script>
<ul class="nav nav-tabs">
    <li class="active"><a href="#list" data-toggle="tab">轮播图列表</a></li>
    <li><a id="outBanner">导出轮播图</a></li>
    <li><a id="inBanner">导入轮播图</a></li>
</ul>
<table id="bannerTable" style="width: 100%"></table>
<div id="bannerPage" style="height: 50px"></div>