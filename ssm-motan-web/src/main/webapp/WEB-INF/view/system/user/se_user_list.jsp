<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/includeTag.jsp"%>
<section class="content-header">
    <h1>用户管理
        <small>系统管理</small></h1>
    <ol class="breadcrumb">
        <li>
            <a href="#">
                <i class="fa fa-dashboard"></i>首页</a>
        </li>
        <li>
            <a href="#">系统管理</a></li>
        <li class="active">用户管理</li></ol>
</section>
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">用户列表</h3></div>
                <!-- /.box-header -->
                <div class="box-body">
                    <table id="datatable" class="table table-bordered table-hover" class="display" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th></th>
                                <th>账号</th>
                                <th>昵称</th>
                                <th>出生年月</th>
                                <th>邮箱</th>
                                <th>状态</th></tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
                <!-- /.box-body --></div>
            <!-- /.box --></div>
        <!-- /.box --></div>
    <!-- /.col --></div>
    <!-- /.row --></section>
<script type="text/javascript">
var se_user_list_datatable;

se_user_list_datatable = $('#datatable').DataTable({
    "processing": true,
    "serverSide": true,
    "language": lang,
    "ajax": {
        "url": "se_user_list_data.do",
        "type": "POST"
    },
    "columns": [{
        "data": "id"
    },
    {
        "data": "uname",
        "width": "20%"
    },
    {
        "data": "pname",
         "width": "20%"
     },
         {
             "data": "birthym",
                     "render": function(data, type, full, meta) {
                         return formatDate(data);  ;
                      },
             "width": "10%"
         },
    {
        "data": "email",
        "width": "30%"
    },
    {
        "data": "visible",
        "render": function(data, type, full, meta) {
            return data == 1 ? "正常": "禁用";
        },
        "width": "10%"
    }],
    "order": [[0, "asc"]],
    "dom": "<'row'<'#lefttool.col-xs-8 text-left'><'#righttool.col-xs-4 text-right'>r>" + "t" + "<'row'<'col-xs-2 text-left'i><'col-xs-8 text-center'p><'col-xs-2 text-right'l>>",
    "initComplete": function() {
        $("#lefttool").append('<div class="input-group"><span class="input-group-addon">账号</span><input name="uname" id="uname" class="form-control" type="text"></div>&nbsp');
        $("#lefttool").append('<div class="input-group"><span class="input-group-addon">昵称</span><input name="pname" id="pname" class="form-control" type="text"></div>&nbsp');
        $("#lefttool").append('<div class="input-group"><span class="input-group-addon">状态</span><select name="visible" id="visible" class="form-control"><option  value=""></option><option  value="1">正常</option><option  value="0">禁用</option></select></div>&nbsp');
        $("#righttool").append('<button id="searchbutton" action_type="search" type="button" class="btn btn-info btn-sm">查询</button>&nbsp');
        $("#righttool").append('<button id="addbutton" type="button" class="btn btn-primary btn-sm">新增</button>&nbsp');
        $("#righttool").append('<button id="editbutton" type="button" class="btn btn-primary btn-sm">修改</button>&nbsp');
        $("#righttool").append('<button id="delbutton" type="button" class="btn btn-primary btn-sm">删除</button>&nbsp');
        $('#searchbutton').click(function() {
            userManage.reloadItemInit();
        });
        $('#addbutton').click(function() {
            userManage.addItemInit();
        });
        $('#editbutton').click(function() {
            userManage.editItemInit();
        });
        $('#delbutton').click(function() {
            userManage.delItemInit();
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
        userManage.currentItem = null;
    } else {
        se_user_list_datatable.$('tr.selected').removeClass('selected');
        $(this).addClass('selected');
        userManage.currentItem = se_user_list_datatable.row($(this).closest('tr')).data();
    }
});

var userManage = {
    currentItem: null,
    reloadItemInit: function() {
        var param = {
            "uname": $("#uname").val(),
            "pname": $("#pname").val(),
            "visible": $("#visible").val()
        };
        se_user_list_datatable.settings()[0].ajax.data = param;
        se_user_list_datatable.ajax.reload();
    },
    addItemInit: function() {
        pageBoxXY('se_user_add.do', '800px', '450px');
    },
    editItemInit: function() {
        if (!userManage.currentItem) {
            showWarm("未选中");
            return;
        }
        pageBoxXY('se_user_edit.do?usercode=' + userManage.currentItem.usercode, '800px', '450px');
    },
    delItemInit: function() {
        if (!userManage.currentItem) {
            showWarm("未选中");
            return;
        }
        layer.confirm('确认删除？ 删除后将无法恢复！', {
            btn: ['确定', '取消']
        },
        function() {
            $.ajax({
                url: 'se_user_del_action.do?usercode=' + userManage.currentItem.usercode,
                type: "post",
                async: false,
                dataType: "json",
                cache: false,
                success: function(data) {
                	userManage.reloadItemInit();
                    closeLoad();
                    showSuccess(data.msg);
                },
                error: function(XmlHttpRequest, textStatus, errorThrown) {
                	userManage.reloadItemInit();
                    closeLoad();
                    showError(XmlHttpRequest, textStatus, errorThrown);
                }
            });
        },
        function() {});
    }

}
</script>