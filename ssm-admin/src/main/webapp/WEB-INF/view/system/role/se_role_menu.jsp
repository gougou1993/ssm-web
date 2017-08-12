<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/includeTag.jsp"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>关联菜单</title>
        <%@include file="/WEB-INF/view/include/includeCss.jsp" %>
        <link rel="stylesheet" href="resources/plugins/zTree_v3-3.5.24/css/metroStyle/metroStyle.css" type="text/css"></link>

    </head>

<body class="hold-transition skin-blue sidebar-mini">
	<div class="row">
		<div class="col-md-12">
			<form id="editForm" enctype="multipart/form-data">
				<input type="hidden" name="rolecode" id="rolecode" value="${info.rolecode}">
				<div class="box box-info" style="margin-top:10px;">
					<div class="box-header with-border">
						<h3 class="box-title">角色菜单关联</h3>
					</div>
					
					<div class="box-body">
						<div class="form-group">
							<button type="button" class="btn btn-primary" onclick="submintForm('se_role_menuList.do?rolecode=${info.rolecode}');">保存</button>
						</div>
						<div class="box-body table-responsive">
							<div class="col-xs-2">
								<ul id="menuTree" class="ztree"></ul>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>

<%@include file="/WEB-INF/view/include/includeJs.jsp"%>
<script src="resources/plugins/zTree_v3-3.5.24/js/jquery.ztree.all.min.js"></script>
<script type="text/javascript">
	var treeObj;
	var setting = {
		check : {
			enable : true,
			chkStyle : "checkbox"
		},
		data : {
			key : {
				name : "captionname"
			},
			simpleData : {
				enable : true,
				idKey : "menucode",
				pIdKey : "parentcode"
			}
		}
	};
	
	$(document).ready(function() {
		var rolecode = $("#rolecode").val();
		$.ajax({
			dataType : "json",
			type: 'post',
			url : "se_role_menu_data.do",
			async : false,
			cache : false,
			data : {
				'rolecode' : rolecode
			},
			success : function(json) {
				treeObj = $.fn.zTree.init($("#menuTree"), setting, json);
				treeObj.expandAll(true);
			},
            error: function(XmlHttpRequest, textStatus, errorThrown) {
               closeLoad();
               showError(XmlHttpRequest, textStatus, errorThrown);
            }

		});
	});

	function submintForm(url) {
		var zTree = $.fn.zTree.getZTreeObj("menuTree");
		var nodes = zTree.getCheckedNodes(true);
		var menuIds = "";
		for (var i = 0; i < nodes.length; i++) {
			menuIds +=  nodes[i].menucode+",";
		}
		$("#editForm").ajaxSubmit({
			type : 'post',
			url : url,
			data : {
				'action' : 'do',
				'menuIds' : menuIds
			},
			beforeSubmit : function() {
				//验证checkbox是否为空
				if (menuIds == '') {
					showFormWarm('menuTree','没有选中任何菜单');
					return false;
				}
				//提交加载层
				showLoad();
			},
			dataType : "json",
			success : function(data) {
				closeLoad();
				showSuccess(data.msg);

			},
            error: function(XmlHttpRequest, textStatus, errorThrown) {
               closeLoad();
               showError(XmlHttpRequest, textStatus, errorThrown);
            }
		});
	}
</script>