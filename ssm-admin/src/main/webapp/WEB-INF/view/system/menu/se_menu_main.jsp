<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/includeTag.jsp"%>
<link rel="stylesheet" href="resources/plugins/zTree_v3-3.5.24/css/metroStyle/metroStyle.css" type="text/css"></link>

<script src="resources/plugins/zTree_v3-3.5.24/js/jquery.ztree.all.min.js"></script>
<section class="content-header">
    <h1>菜单管理
        <small>系统管理</small></h1>
    <ol class="breadcrumb">
        <li>
            <a href="#">
                <i class="fa fa-dashboard"></i>首页</a>
        </li>
        <li>
            <a href="#">系统管理</a></li>
        <li class="active">菜单管理</li></ol>
</section>
<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-body table-responsive">
                    <div class="col-xs-2">
                        <ul id="menuTree" class="ztree"></ul>
                    </div>
                    <div class="col-xs-10">
                        <table id="datatable" class="table table-bordered table-hover" class="display" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th>菜单名称</th>
                                    <th>URL</th>
                                    <th>状态</th></tr>
                            </thead>
                            <tbody></tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- /.content -->
<script type="text/javascript">
var treeObj;
var se_menu_list_datatable;

var setting = {
    callback: {
        onClick: onClickCallback,
        onAsyncSuccess: onAsyncSuccessCallback
    },
    async: {
        enable: true,
        url: "se_menu_tree.do",
        dataType: "text",
        contentType: "application/json",
        type: "post"
    },
    data: {
        key: {
            name: "captionname"
        },
        simpleData: {
            enable: true,
            idKey: "menucode",
            pIdKey: "parentcode"
        }
    }
};

$(document).ready(function() {
    treeObj = $.fn.zTree.init($("#menuTree"), setting);
});

function onAsyncSuccessCallback(event, treeId, treeNode) {
    treeObj.expandAll(true);
}

function onClickCallback(event, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj(treeId);
    var nodes = zTree.getSelectedNodes();
    var menucode = nodes[0].menucode;
    menuManage.reloadItemInit(menucode);
}

/*刷新zTree*/
function refreshzTree() {

    var treeObj = $.fn.zTree.getZTreeObj("menuTree");
    treeObj.expandAll(true);
    treeObj.reAsyncChildNodes(null, "refresh", false);
}

se_menu_list_datatable = $('#datatable').DataTable({
    "processing": true,
    "serverSide": true,
    "language": lang,
    "ajax": {
        "url": "se_menu_list_data.do",
        "type": "POST"
    },
    "columns": [{
        "data": "id"
    },
    {
        "data": "captionname",
        "width": "20%"
    },
    {
        "data": "sourceurl",
        "width": "70%"
    },
    {
        "data": "visible",
        "render": function(data, type, full, meta) {
            return data == 1 ? "正常": "禁用";
        },
        "width": "10%"
    }],
    "order": [[0, "asc"]],
    "dom": "<'row'<'col-xs-8 text-left'><'#righttool.col-xs-4 text-right'>r>" + "t" + "<'row'<'col-xs-2 text-left'i><'col-xs-8 text-center'p><'col-xs-2 text-right'l>>",
    "initComplete": function() {
        $("#righttool").append('<button id="addbutton" type="button" class="btn btn-primary btn-sm">新增</button>&nbsp');
        $("#righttool").append('<button id="editbutton" type="button" class="btn btn-primary btn-sm">修改</button>&nbsp');
        $("#righttool").append('<button id="delbutton" type="button" class="btn btn-primary btn-sm">删除</button>&nbsp');
        $('#addbutton').click(function() {
            menuManage.addItemInit();
        });
        $('#editbutton').click(function() {
            menuManage.editItemInit();
        });
        $('#delbutton').click(function() {
            menuManage.delItemInit();
        });
    },
    "columnDefs": [{
        "targets": [0],
        "visible": false
    }]
});

$('#datatable tbody').on('click', 'tr',
function() {
    if ($(this).hasClass('selected')) {
        $(this).removeClass('selected');
        menuManage.currentItem = null;
    } else {
        se_menu_list_datatable.$('tr.selected').removeClass('selected');
        $(this).addClass('selected');
        menuManage.currentItem = se_menu_list_datatable.row($(this).closest('tr')).data();
    }
});

var menuManage = {
    currentItem: null,
    reloadItemInit: function(menucode) {
        var param = {
            "parentcode": menucode
        };
        se_menu_list_datatable.settings()[0].ajax.data = param;
        se_menu_list_datatable.ajax.reload();
    },
    addItemInit: function() {
        var currtntTreeItem = $.fn.zTree.getZTreeObj("menuTree").getSelectedNodes()[0];
        if (!currtntTreeItem) {
            showWarm("未选中");
            return;
        }
        pageBoxXY('se_menu_add.do?parentcode=' + currtntTreeItem.menucode, '500px', '520px');
    },
    editItemInit: function() {
        if (!menuManage.currentItem) {
            showWarm("未选中");
            return;
        }
        pageBoxXY('se_menu_edit.do?menucode=' + menuManage.currentItem.menucode, '500px', '520px');
    },
    delItemInit: function() {
        if (!menuManage.currentItem) {
            showWarm("未选中");
            return;
        }
        layer.confirm('确认删除？ 删除后将无法恢复！', {
            btn: ['确定', '取消']
        },
        function() {
            $.ajax({
                url: 'se_menu_del_action.do?menucode=' + menuManage.currentItem.menucode,
                type: "post",
                async: false,
                dataType: "json",
                cache: false,
                success: function(data) {
                    menuManage.reloadItemInit();
                    refreshzTree();
                    closeLoad();
                    if(data.status ==1){
                        showSuccess(data.msg);
                    }else{
                        showWarm(data.msg);
                    }
                },
                error: function(XmlHttpRequest, textStatus, errorThrown) {
                    menuManage.reloadItemInit();
                    refreshzTree();
                    closeLoad();
                    showError(XmlHttpRequest, textStatus, errorThrown);
                }
            });
        },
        function() {});
    }

}
</script>