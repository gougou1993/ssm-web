<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/includeTag.jsp"%>
<section class="content-header">
    <h1>
        角色管理
        <small>
            系统管理
        </small>
    </h1>
    <ol class="breadcrumb">
        <li>
            <a href="#">
                <i class="fa fa-dashboard">
                </i>
                首页
            </a>
        </li>
        <li>
            <a href="#">
                系统管理
            </a>
        </li>
        <li class="active">
            角色管理
        </li>
    </ol>
</section>
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">
                        角色列表
                    </h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <table id="datatable" class="table table-bordered table-hover" class="display"
                    cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th>
                                </th>
                                <th>
                                    角色
                                </th>
                                <th>
                                    说明
                                </th>
                                <th>
                                    状态
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </div>
        <!-- /.box -->
    </div>
    <!-- /.col -->
    </div>
    <!-- /.row -->
</section>
<script type="text/javascript">
$(function() {

    var se_role_list_datatable;
    var dataurl = "se_role_data_list.do";

        se_role_list_datatable = $('#datatable').DataTable({
            "processing": true,
            "serverSide": true,
            "language": lang,
            "ajax": {
                "url": dataurl,
                "type": "POST"
            },
            "columns": [{
                "data": "id"
            },
            {
                "data": "roledesc",
                "width": "30%"
            },
            {
                "data": "content",
                "width": "60%"
            },
            {
                "data": "visible",
                "render": function(data, type, full, meta) {
                    return data == 1 ? "正常": "禁用";
                },
                "width": "10%"
            }],
            "order": [[0, "asc"]],
            "dom": "<'row'<'#lefttool.col-xs-11 text-left'><'#righttool.col-xs-1 text-right'>r>" + "t" + "<'row'<'col-xs-2 text-left'i><'col-xs-8 text-center'p><'col-xs-2 text-right'l>>",
            "initComplete": function() {
                $("#lefttool").append('<div class="input-group"><span class="input-group-addon">角色名称</span><input name="roledesc" id="roledesc" class="form-control" type="text"></div>&nbsp');
                $("#lefttool").append('<div class="input-group"><span class="input-group-addon">状态</span><select name="visible" id="visible" class="form-control"><option  value="1">正常</option><option  value="0">禁用</option></select></div>&nbsp');
                $("#righttool").append('<button id="searchbutton" action_type="search" type="button" class="btn btn-primary btn-sm">查询</button>');
                $('#searchbutton').click(function() {
                    reload();
                });
            },
            "columnDefs": [{"targets": [0],"visible": false}]
        });

        $('#datatable tbody').on('click', 'tr',
        function() {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
            } else {
                se_role_list_datatable.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        });

    function reload() {
        var param = {
                "roledesc": $("#roledesc").val(),
                "visible": $("#visible").val()
            };
    se_role_list_datatable.settings()[0].ajax.data = param;
    se_role_list_datatable.ajax.reload();

    }
});
</script>