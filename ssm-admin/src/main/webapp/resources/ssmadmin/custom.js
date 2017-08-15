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
    parent.layer.alert(msg, {
        icon: 6,
        skin: 'layui-layer-molv',
        closeBtn: 0
    });
    closeWindows();
}


//操作失败弹出框
function showWarm(msg) {
    parent.layer.alert(msg, {
        icon: 5,
        skin: 'layui-layer-lan',
        closeBtn: 0
    });
    closeWindows();
}

//操作错误弹出框
function showError(XmlHttpRequest, textStatus, errorThrown) {
    var errormsg = XMLHttpRequest.status + "\n" + XMLHttpRequest.readyState + "\n" + textStatus;
    parent.layer.alert(errormsg, {
        icon: 2,
        skin: 'layui-layer-lan',
        closeBtn: 0
    });
    closeWindows();
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

function pageBox(url) {
		var index = layer.open({
			type : 2,
			title : false,
			shadeClose : true,
			shade : 0.8,
			area : [ '512px' , '60%'],
			content : url
		});
}
function pageBoxXY(url, width, height) {
		var index = layer.open({
			type : 2,
			title : false,
			shadeClose : true,
			shade : 0.8,
			area : [ width , height],
			content : url
		});
}


function closeWindows() {
	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(index); //再执行关闭
};

    function formatDate(inputTime) {
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        return y + '-' + m + '-' + d;
    };

    function formatDateTime(inputTime) {
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
    };

	function showFormWarm(id, msg) {
		layer.tips(msg, '#' + id, {
			time : 3000,
			tips : [ 3, '#ff0000' ]
		});
		$('html, body').animate({
			scrollTop : $("#" + id).offset().top - 40
		}, 10);
	}