<%@ page language="java" pageEncoding="UTF-8"%>
<!-- jQuery 2.2.3 -->
<script src="resources/plugins/jQuery/jquery-2.2.3.min.js"></script>

<!-- Bootstrap 3.3.6 -->
<script src="resources/bootstrap/js/bootstrap.min.js"></script>

<!-- DataTables -->
<script src="resources/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="resources/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="resources/plugins/datatables/extensions/Select/js/dataTables.select.min.js"></script>

<!-- Bootstrap WYSIHTML5 -->
<script src="resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>

<!-- Slimscroll -->
<script src="resources/plugins/slimScroll/jquery.slimscroll.min.js"></script>

<!-- AdminLTE App -->
<script src="resources/dist/js/app.js"></script>

<!-- AdminLTE for demo purposes -->
<script src="resources/dist/js/demo.js"></script>

<!-- bootstrap My97DatePicker -->
<script src="resources/plugins/My97DatePicker/WdatePicker.js"></script>

<!-- layer -->
<script src="resources/plugins/layer/layer.js"></script>

<script type="text/javascript">
function redirectPage(menuid, url) {
    showLoad();
    $("#center-div").empty();
    //$("#center-div").children().remove();
    $.ajax({
        url: url,
        type: "get",
        async: true,
        dataType: 'html',
        cache: false,
        success: function(data) {
            //data是返回的html对象。
            //现在我要在这个位置取 页面中的id为test的div
            $("#center-div").html(data);
            closeLoad();
        },
        error: function(XmlHttpRequest, textStatus, errorThrown) {
            closeLoad();
            showError(XmlHttpRequest, textStatus, errorThrown);
        }
    });
}

//显示加载层
function showLoad() {
    parent.layer.load(0, {
        shade: [0.2, '#0A0A0A']
    });
}
//关闭加载层
function closeLoad() {
    parent.layer.closeAll('loading');
}

//操作成功弹出框
function showSuccess(msg) {
    layer.alert(msg, {
        icon: 6,
        skin: 'layui-layer-molv',
        closeBtn: 0
    });
    layer.close();
}
//操作失败弹出框
function showWarm(msg) {
    parent.layer.alert(msg, {
        icon: 5,
        skin: 'layui-layer-lan',
        closeBtn: 0
    });
    layer.close();
}

//操作错误弹出框
function showError(XmlHttpRequest, textStatus, errorThrown) {
    var errormsg = XMLHttpRequest.status + "\n" + XMLHttpRequest.readyState + "\n" + textStatus;
    parent.layer.alert(errormsg, {
        icon: 2,
        skin: 'layui-layer-lan',
        closeBtn: 0
    });
    layer.close();
}

//dataTable提示信息
var lang = {
    "sProcessing": "处理中...",
    "sLengthMenu": "每页 _MENU_ 项",
    "sZeroRecords": "没有匹配结果",
    "sInfo": "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
    "sInfoEmpty": "当前显示第 0 至 0 项，共 0 项",
    "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
    "sInfoPostFix": "",
    "sSearch": "搜索:",
    "sUrl": "",
    "sEmptyTable": "表中数据为空",
    "sLoadingRecords": "载入中...",
    "sInfoThousands": ",",
    "oPaginate": {
        "sFirst": "首页",
        "sPrevious": "上页",
        "sNext": "下页",
        "sLast": "末页",
        "sJump": "跳转"
    },
    "oAria": {
        "sSortAscending": ": 以升序排列此列",
        "sSortDescending": ": 以降序排列此列"
    }
};

</script>

<script id="tpl" type="text/x-handlebars-template">
    {{#each func}}
    <button type="button" class="btn btn-{{this.type}} btn-sm" onclick="{{this.fn}}">{{this.name}}</button>
    {{/each}}
</script>