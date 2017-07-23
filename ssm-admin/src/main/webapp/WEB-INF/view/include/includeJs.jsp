<%@ page language="java" pageEncoding="UTF-8"%>
<!-- jQuery 2.2.3 -->
<script src="resources/plugins/jQuery/jquery-2.2.3.min.js"></script>

<!-- Bootstrap 3.3.6 -->
<script src="resources/bootstrap/js/bootstrap.min.js"></script>

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
			url : url,
			type : "get",
			async : true,
			dataType : 'html',
			cache : false,
			success : function(data) {
				//data是返回的html对象。
				//现在我要在这个位置取 页面中的id为test的div
				$("#center-div").html(data);
				closeLoad();
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				closeLoad();
				showError(XmlHttpRequest, textStatus, errorThrown);
			}
		});
	}

	//显示加载层
	function showLoad() {
		parent.layer.load(0, {
			shade : [ 0.2, '#0A0A0A' ]
		});
	}
	//关闭加载层
	function closeLoad() {
		parent.layer.closeAll('loading');
	}

	//操作成功弹出框
	function showSuccess(msg) {
		layer.alert(msg, {
			icon : 6,
			skin : 'layui-layer-molv',
			closeBtn : 0
		});
		layer.close();
	}
	//操作失败弹出框
	function showWarm(msg) {
		parent.layer.alert(msg, {
			icon : 5,
			skin : 'layui-layer-lan',
			closeBtn : 0
		});
		layer.close();
	}

	//操作错误弹出框
	function showError(XmlHttpRequest, textStatus, errorThrown) {
		var errormsg = XMLHttpRequest.status + "\n" + XMLHttpRequest.readyState
				+ "\n" + textStatus;
		parent.layer.alert(errormsg, {
			icon : 2,
			skin : 'layui-layer-lan',
			closeBtn : 0
		});
		layer.close();
	}

</script>